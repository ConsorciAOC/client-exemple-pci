package cat.aoc.client_pci.soap;

import cat.aoc.client_pci.Cluster;
import cat.aoc.client_pci.Entorn;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.openuri.Procesa;
import org.openuri.ProcesaResponse;

public abstract class SOAPClient extends CustomWebServiceSupport<Procesa, ProcesaResponse> {

    protected Entorn entorn;
    protected Cluster cluster;

    public SOAPClient(Entorn entorn, Cluster cluster, String... externalPackages) throws Exception {
        super(externalPackages);
        this.entorn = entorn;
        this.cluster = cluster;
    }

    public String getEndpoint(SOAPOperation operation) throws Exception {
        switch (entorn){
            case PRO:
                return "https://serveis3." + cluster.name().toLowerCase() +
                        ".aoc.cat:443/siri-proxy/services/" + operation.tipus.getValue();
            case PRE:
                return "https://serveis3-" + entorn.name().toLowerCase() + "." + cluster.name().toLowerCase() +
                        ".aoc.cat:443/siri-proxy/services/" + operation.tipus.getValue();
            default:
                throw new Exception("Entorn no configurat: " + entorn);
        }
    }

    public Respuesta send(Peticion peticion) throws Exception {
        SOAPOperation operation = getOperation(getModalitat(peticion));
        String endpoint = getEndpoint(operation);
        Procesa procesa = new Procesa();
        procesa.setPeticion(peticion);
        ProcesaResponse response = this.send(endpoint, procesa);
        return response.getRespuesta();
    }
    protected abstract SOAPOperation getOperation(String modalitat) throws NotDefinedException;

    private String getModalitat(Peticion peticion){
        return peticion.getAtributos().getCodigoCertificado();
    }
}
