package cat.aoc.client_pci.api;

import cat.aoc.client_pci.api.model.Cluster;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.soap.LoggerInterceptor;
import cat.aoc.client_pci.api.soap.SignatureInterceptor;
import cat.aoc.client_pci.api.soap.SoapMtomClient;
import generated.pci.peticion.Peticion;
import generated.pci.peticion.Procesa;
import generated.pci.respuesta.ProcesaResponse;
import generated.pci.respuesta.Respuesta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Stream;

@Slf4j
public class ClientPCI extends SoapMtomClient<Procesa, ProcesaResponse> {
    private static final String[] PACKAGES = {
            "generated.pci.peticion",
            "generated.pci.respuesta",
    };

    private static String[] processPackages(String... externalPackages) {
        return Stream.concat(Arrays.stream(PACKAGES), Arrays.stream(externalPackages))
                .toArray(String[]::new);
    }

    private final Entorn entorn;
    private final Cluster cluster;
    private final Frontal frontal;

    public ClientPCI(Entorn entorn, Cluster cluster, Frontal frontal, String[] externalPackages, Properties keystoreProperties) {
        super(processPackages(externalPackages));
        this.entorn = entorn;
        this.cluster = cluster;
        this.frontal = frontal;
        try {
            setInterceptors(new ClientInterceptor[]{
                    new SignatureInterceptor(keystoreProperties),
                    new LoggerInterceptor(getUnmarshaller())
            });
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public Respuesta send(Peticion peticion) throws ClientException {
        Procesa procesa = new Procesa();
        procesa.setPeticion(peticion);
        String endpoint = getEndpoint();
        ProcesaResponse response = this.send(endpoint, procesa);
        return response.getRespuesta();
    }

    public String getEndpoint() throws ClientException {
        return entorn.getEndpoint(cluster) + "/siri-proxy/services/" + frontal.getName();
    }

}
