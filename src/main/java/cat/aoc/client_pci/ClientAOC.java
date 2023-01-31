package cat.aoc.client_pci;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.model.*;
import cat.aoc.client_pci.soap.SoapMtomClient;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.openuri.Procesa;
import org.openuri.ProcesaResponse;

import java.util.Arrays;
import java.util.stream.Stream;

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
    private final PeticionBuilder peticionBuilder;

    protected ClientAOC(String keystorePath, Entorn entorn, Cluster cluster, PeticionBuilder peticionBuilder, String... externalPackages) {
        super(keystorePath, processPackages(externalPackages));
        this.entorn = entorn;
        this.cluster = cluster;
        this.peticionBuilder = peticionBuilder;
    }

    public abstract Frontal getFrontal(Operacio operacio) throws NotDefinedException;

    public abstract String getCodiServei();

    public abstract String getCodiModalitat(Operacio operacio);

    public Respuesta send(Operacio operacio, Finalitat finalitat) throws NotDefinedException, NotFoundException {
        String endpoint = getEndpoint(operacio);
        Procesa procesa = buildProcesa(operacio, finalitat);
        ProcesaResponse response = this.send(endpoint, procesa);
        return response.getRespuesta();
    }

    private String getEndpoint(Operacio operacio) throws NotDefinedException {
        return entorn.getEndpoint(cluster) + "/siri-proxy/services/" + getFrontal(operacio).getValue();
    }

    private Procesa buildProcesa(Operacio operacio, Finalitat finalitat) throws NotDefinedException, NotFoundException {
        Procesa procesa = new Procesa();
        procesa.setPeticion(buildPeticion(operacio, finalitat));
        return procesa;
    }

    protected Peticion buildPeticion(Operacio operacio, Finalitat finalitat) throws NotDefinedException, NotFoundException {
        return peticionBuilder.build(
                getCodiServei(),
                operacio,
                getCodiModalitat(operacio),
                finalitat
        );
    }

}
