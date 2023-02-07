package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.exceptions.NotDefinedException;
import cat.aoc.client_pci.model.*;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;

public class PADROProxyClient extends ClientAOC {

    private final PADROEmpadronamientoClient clientEmpadronamiento;
    private final PADROConvivenciaClient clientConvivencia;

    public PADROProxyClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP);
        this.clientEmpadronamiento = new PADROEmpadronamientoClient(keystorePath, entorn);
        this.clientConvivencia = new PADROConvivenciaClient(keystorePath, entorn);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        try {
            return switch ((PADROOperacio) operacio) {
                case RESIDENT, MUNICIPI_RESIDENCIA, RESIDENT_MUNICIPI, NUMERO_CONVIVENTS, COMPROVACIO_CONVIVENTS, TITULAR, TITULAR_PROPI, CONVIVENTS, CONVIVENTS_PROPI, TITULAR_PDF, CONVIVENTS_PDF, TITULAR_IDESCAT, CERCA_TITULAR -> Frontal.SINCRON;
            };
        } catch (Exception e) {
            throw new NotDefinedException("Modalitat no definida: " + operacio);
        }
    }

    @Override
    public Respuesta send(Operacio operacio, Peticion peticion) throws NotDefinedException {
        try {
            return switch ((PADROOperacio) operacio) {
                case TITULAR, TITULAR_IDESCAT, CERCA_TITULAR, TITULAR_PROPI, TITULAR_PDF,
                        RESIDENT, MUNICIPI_RESIDENCIA, RESIDENT_MUNICIPI -> clientEmpadronamiento.send(operacio, peticion);
                case CONVIVENTS, NUMERO_CONVIVENTS, CONVIVENTS_PROPI, CONVIVENTS_PDF,
                        COMPROVACIO_CONVIVENTS -> clientConvivencia.send(operacio, peticion);
            };
        } catch (Exception e) {
            throw new NotDefinedException("Modalitat no definida: " + operacio);
        }
    }

}
