package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.model.*;
import net.gencat.scsp.esquemes.respuesta.Respuesta;

public class PADROProxyClient extends ClientAOC {

    private final PADROEmpadronamientoClient clientEmpadronamiento;
    private final PADROConvivenciaClient clientConvivencia;

    public PADROProxyClient(String keystorePath, Entorn entorn, PeticionBuilder peticionBuilder) {
        super(keystorePath, entorn, Cluster.IOP, peticionBuilder);
        this.clientEmpadronamiento = new PADROEmpadronamientoClient(keystorePath, entorn, peticionBuilder);
        this.clientConvivencia = new PADROConvivenciaClient(keystorePath, entorn, peticionBuilder);
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
    public String getCodiServei() {
        return "PADRO";
    }

    @Override
    public String getCodiModalitat(Operacio operacio) {
        return ((PADROOperacio) operacio).name();
    }
    @Override
    public Respuesta send(Operacio operacio, Finalitat finalitat) throws NotDefinedException {
        try {
            return switch ((PADROOperacio) operacio) {
                case TITULAR, TITULAR_IDESCAT, CERCA_TITULAR, TITULAR_PROPI, TITULAR_PDF,
                        RESIDENT, MUNICIPI_RESIDENCIA, RESIDENT_MUNICIPI -> clientEmpadronamiento.send(operacio, finalitat);
                case CONVIVENTS, NUMERO_CONVIVENTS, CONVIVENTS_PROPI, CONVIVENTS_PDF,
                        COMPROVACIO_CONVIVENTS -> clientConvivencia.send(operacio, finalitat);
            };
        } catch (Exception e) {
            throw new NotDefinedException("Modalitat no definida: " + operacio);
        }
    }

}
