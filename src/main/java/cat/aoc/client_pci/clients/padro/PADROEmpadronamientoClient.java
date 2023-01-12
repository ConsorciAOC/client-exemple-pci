package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.*;
import generated.padro.PeticionDatosTitular;
import net.gencat.scsp.esquemes.peticion.Peticion;

public class PADROEmpadronamientoClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.padro",
            "generated.padro.empadronamiento",
    };

    private final PeticionBuilder peticionBuilder;

    public PADROEmpadronamientoClient(Entorn entorn, PeticionBuilder peticionBuilder) throws WebServiceSupportException {
        super(entorn, Cluster.IOP, PACKAGES);
        this.peticionBuilder = peticionBuilder;
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
        return peticionBuilder.build(
                getCodiServei(),
                ((PADROOperacio) operacio).name(),
                finalitat.name(),
                getDatosEspecificos(operacio)
        );
    }

    private Object[] getDatosEspecificos(Operacio operacio) {
        switch ((PADROOperacio) operacio) {
            case TITULAR:
                return new Object[]{
                        buildPeticionDatosTitular()
                };
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
                return new Object[]{};
        }
    }

    private static PeticionDatosTitular buildPeticionDatosTitular() {
        PeticionDatosTitular peticionDatosTitular = new PeticionDatosTitular();
        peticionDatosTitular.setNumExpediente("prova");
        peticionDatosTitular.setTipoDocumentacion(1);
        peticionDatosTitular.setDocumentacion("12345678Z");
        peticionDatosTitular.setCodigoProvincia("08");
        peticionDatosTitular.setCodigoMunicipio("001");
        peticionDatosTitular.setIdescat(0);
        return peticionDatosTitular;
    }

}
