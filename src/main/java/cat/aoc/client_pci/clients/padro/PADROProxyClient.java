package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.*;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;

public class PADROProxyClient extends ClientAOC<PADROOperacio> {

    private final PADROEmpadronamientoClient clientEmpadronamiento;
    private final PADROConvivenciaClient clientConvivencia;

    public PADROProxyClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP);
        this.clientEmpadronamiento = new PADROEmpadronamientoClient(keystorePath, entorn);
        this.clientConvivencia = new PADROConvivenciaClient(keystorePath, entorn);
    }

    @Override
    public Frontal getFrontal(PADROOperacio operacio) {
        return switch (operacio) {
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
    public Respuesta send(PADROOperacio operacio, Peticion peticion) {
        return switch (operacio) {
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
