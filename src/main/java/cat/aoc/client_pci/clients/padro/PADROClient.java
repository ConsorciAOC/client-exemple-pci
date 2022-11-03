package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.*;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.aocat.padro.PeticionDatosTitular;

public class PADROClient extends ClientAOC {
    private static final String CODI_SERVEI = "PADRO";
    private static final String[] PACKAGES = {
            "es.red.padron",
            "net.aocat.padro"
    };

    private final PeticionBuilder peticionBuilder;

    public PADROClient(Entorn entorn, PeticionBuilder peticionBuilder) throws WebServiceSupportException {
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
    public Peticion getPeticion(Operacio operacio, Finalitat finalitat) {
        return peticionBuilder.build(
                CODI_SERVEI,
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
            case RESIDENT:
            case MUNICIPI_RESIDENCIA:
            case RESIDENT_MUNICIPI:
            case NUMERO_CONVIVENTS:
            case COMPROVACIO_CONVIVENTS:
            case TITULAR_PROPI:
            case CONVIVENTS:
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
