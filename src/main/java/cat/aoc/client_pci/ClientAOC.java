package cat.aoc.client_pci;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.*;
import cat.aoc.client_pci.soap.CustomWebServiceSupport;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.openuri.Procesa;
import org.openuri.ProcesaResponse;

public abstract class ClientAOC extends CustomWebServiceSupport<Procesa, ProcesaResponse> {

    private final Entorn entorn;
    private final Cluster cluster;

    protected ClientAOC(Entorn entorn, Cluster cluster, String... externalPackages) throws WebServiceSupportException {
        super(externalPackages);
        this.entorn = entorn;
        this.cluster = cluster;
    }

    public abstract Frontal getFrontal(Operacio operacio) throws NotDefinedException;

    public abstract Peticion getPeticion(Operacio operacio, Finalitat finalitat);

    public Respuesta send(Operacio operacio, Finalitat finalitat) throws NotDefinedException {
        String endpoint = getEndpoint(operacio);
        Procesa procesa = new Procesa();
        procesa.setPeticion(getPeticion(operacio, finalitat));
        ProcesaResponse response = this.send(endpoint, procesa);
        return response.getRespuesta();
    }

    private String getEndpoint(Operacio operacio) throws NotDefinedException {
        return entorn.getEndpoint(cluster) + "/siri-proxy/services/" + getFrontal(operacio).getValue();
    }

}
