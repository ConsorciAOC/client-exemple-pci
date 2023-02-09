package cat.aoc.client_pci;

import cat.aoc.client_pci.model.*;
import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.soap.LoggerInterceptor;
import cat.aoc.client_pci.soap.SignatureInterceptor;
import cat.aoc.client_pci.soap.SoapMtomClient;
import lombok.extern.slf4j.Slf4j;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.openuri.Procesa;
import org.openuri.ProcesaResponse;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
public abstract class ClientAOC extends SoapMtomClient<Procesa, ProcesaResponse> {
    private static final String[] PACKAGES = {
            "org.openuri",
            "net.gencat.scsp.esquemes.peticion",
            "net.gencat.scsp.esquemes.respuesta"
    };

    private static String[] processPackages(String... externalPackages) {
        return Stream.concat(Arrays.stream(PACKAGES), Arrays.stream(externalPackages))
                .toArray(String[]::new);
    }

    private final Entorn entorn;
    private final Cluster cluster;

    protected ClientAOC(String keystorePath, Entorn entorn, Cluster cluster, String... externalPackages) {
        super(processPackages(externalPackages));
        this.entorn = entorn;
        this.cluster = cluster;
        try {
            setInterceptors(new ClientInterceptor[]{
                    new SignatureInterceptor(keystorePath),
                    new LoggerInterceptor(getUnmarshaller())
            });
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public abstract Frontal getFrontal(Operacio operacio) throws ClientException;

    public Respuesta send(Operacio operacio, Peticion peticion) throws ClientException {
        Procesa procesa = new Procesa();
        procesa.setPeticion(peticion);
        String endpoint = getEndpoint(operacio);
        ProcesaResponse response = this.send(endpoint, procesa);
        return response.getRespuesta();
    }

    private String getEndpoint(Operacio operacio) throws ClientException {
        return entorn.getEndpoint(cluster) + "/siri-proxy/services/" + getFrontal(operacio).getName();
    }

    protected static <T> void checkOperacio(Operacio operacio, Class<T> clazz) throws ClientException {
        try {
            clazz.cast(operacio);
        } catch(ClassCastException e) {
            throw new ClientException("Operacio no definida: " + operacio);
        }
    }

}
