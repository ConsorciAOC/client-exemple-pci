package cat.aoc.client_pci.clients.vo.local.padro;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.*;
import cat.aoc.client_pci.model.exceptions.ClientException;
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
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, PADROOperacio.class);
        return switch ((PADROOperacio) operacio) {
            case TITULAR,
                    TITULAR_IDESCAT,
                    CERCA_TITULAR,
                    TITULAR_PROPI,
                    TITULAR_PDF,
                    RESIDENT,
                    MUNICIPI_RESIDENCIA,
                    RESIDENT_MUNICIPI -> clientEmpadronamiento.getFrontal(operacio);
            case CONVIVENTS,
                    NUMERO_CONVIVENTS,
                    CONVIVENTS_PROPI,
                    CONVIVENTS_PDF,
                    COMPROVACIO_CONVIVENTS -> clientConvivencia.getFrontal(operacio);
        };
    }

    @Override
    public Respuesta send(Operacio operacio, Peticion peticion) throws ClientException {
        checkOperacio(operacio, PADROOperacio.class);
        return switch ((PADROOperacio) operacio) {
            case TITULAR,
                    TITULAR_IDESCAT,
                    CERCA_TITULAR,
                    TITULAR_PROPI,
                    TITULAR_PDF,
                    RESIDENT,
                    MUNICIPI_RESIDENCIA,
                    RESIDENT_MUNICIPI -> clientEmpadronamiento.send(operacio, peticion);
            case CONVIVENTS,
                    NUMERO_CONVIVENTS,
                    CONVIVENTS_PROPI,
                    CONVIVENTS_PDF,
                    COMPROVACIO_CONVIVENTS -> clientConvivencia.send(operacio, peticion);
        };
    }
}
