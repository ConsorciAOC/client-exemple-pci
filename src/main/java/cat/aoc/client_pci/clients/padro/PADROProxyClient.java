package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.*;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;

public class PADROProxyClient extends ClientAOC {

    private final PADROEmpadronamientoClient clientEmpadronamiento;
    private final PADROConvivenciaClient clientConvivencia;

    public PADROProxyClient(Entorn entorn, PeticionBuilder peticionBuilder) throws WebServiceSupportException {
        super(entorn, Cluster.IOP);
        this.clientEmpadronamiento = new PADROEmpadronamientoClient(entorn, peticionBuilder);
        this.clientConvivencia = new PADROConvivenciaClient(entorn, peticionBuilder);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        switch ((PADROOperacio) operacio) {
            case RESIDENT:
            case MUNICIPI_RESIDENCIA:
            case RESIDENT_MUNICIPI:
            case NUMERO_CONVIVENTS:
            case COMPROVACIO_CONVIVENTS:
            case TITULAR:
            case TITULAR_PROPI:
            case CONVIVENTS:
            case CONVIVENTS_PROPI:
            case TITULAR_PDF:
            case CONVIVENTS_PDF:
            case TITULAR_IDESCAT:
            case CERCA_TITULAR:
                return Frontal.SINCRON;
            default:
                throw new NotDefinedException("Modalitat no definida: " + operacio);
        }
    }

    @Override
    public String getCodiServei() {
        return "PADRO";
    }

    @Override
    protected String getCodiModalitat(Operacio operacio) {
        return ((PADROOperacio) operacio).name();
    }

    @Override
    public Peticion getPeticion(Operacio operacio, Finalitat finalitat) {
        switch ((PADROOperacio) operacio) {
            case TITULAR:
                return clientEmpadronamiento.getPeticion(operacio, finalitat);
            case CONVIVENTS:
            case RESIDENT:
            case MUNICIPI_RESIDENCIA:
            case RESIDENT_MUNICIPI:
            case NUMERO_CONVIVENTS:
            case COMPROVACIO_CONVIVENTS:
            case TITULAR_PROPI:
            case CONVIVENTS_PROPI:
            case TITULAR_PDF:
            case CONVIVENTS_PDF:
            case TITULAR_IDESCAT:
            case CERCA_TITULAR:
            default:
                return clientConvivencia.getPeticion(operacio, finalitat);
        }
    }

    @Override
    public Respuesta send(Operacio operacio, Finalitat finalitat) throws NotDefinedException {
        switch ((PADROOperacio) operacio) {
            case TITULAR:
                return clientEmpadronamiento.send(operacio, finalitat);
            case CONVIVENTS:
            case RESIDENT:
            case MUNICIPI_RESIDENCIA:
            case RESIDENT_MUNICIPI:
            case NUMERO_CONVIVENTS:
            case COMPROVACIO_CONVIVENTS:
            case TITULAR_PROPI:
            case CONVIVENTS_PROPI:
            case TITULAR_PDF:
            case CONVIVENTS_PDF:
            case TITULAR_IDESCAT:
            case CERCA_TITULAR:default:
                return clientConvivencia.send(operacio, finalitat);
        }
    }

}
