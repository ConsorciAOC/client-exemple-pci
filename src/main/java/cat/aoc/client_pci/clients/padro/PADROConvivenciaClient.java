package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.*;
import generated.padro.PeticionDatosConvivientes;
import net.gencat.scsp.esquemes.peticion.Peticion;

public class PADROConvivenciaClient extends ClientAOC {
    private static final String CODI_SERVEI = "PADRO";
    private static final String[] PACKAGES = {
            "generated.padro",
            "generated.padro.convivencia",
    };

    private final PeticionBuilder peticionBuilder;

    public PADROConvivenciaClient(Entorn entorn, PeticionBuilder peticionBuilder) throws WebServiceSupportException {
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
            case CONVIVENTS:
                return new Object[]{
                        buildPeticionDatosConvivientes()
                };
            case TITULAR:
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

    private static PeticionDatosConvivientes buildPeticionDatosConvivientes() {
        PeticionDatosConvivientes peticion = new PeticionDatosConvivientes();
        peticion.setNumExpediente("prova");
        peticion.setTipoDocumentacion(1);
        peticion.setDocumentacion("12345678Z");
        peticion.setCodigoProvincia("08");
        peticion.setCodigoMunicipio("001");
        peticion.setIdescat(0);
        return peticion;
    }

}
