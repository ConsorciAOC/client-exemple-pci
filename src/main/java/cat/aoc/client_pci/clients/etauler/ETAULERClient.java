package cat.aoc.client_pci.clients.etauler;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.*;
import generated.etauler.PeticioConsultarEstatEdicte;
import net.gencat.scsp.esquemes.peticion.Peticion;

public class ETAULERClient extends ClientAOC {
    private static final String CODI_SERVEI = "ETAULER";
    private static final String CODI_MODALITAT = "ETAULER";
    private static final String[] PACKAGES = {
            "generated.etauler"
    };

    private final PeticionBuilder peticionBuilder;

    public ETAULERClient(Entorn entorn, PeticionBuilder peticionBuilder) throws WebServiceSupportException {
        super(entorn, Cluster.APP, PACKAGES);
        this.peticionBuilder = peticionBuilder;
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        switch ((ETAULEROperacio) operacio) {
            case PUBLICAR:
            case DADES:
            case AMPLIAR_TERMINI:
            case CANCELAR:
            case DESPENJAR:
            case SINCRONITZAR:
            case DESCARREGAR_DOCUMENT:
            case CONSULTAR:
                return Frontal.SINCRON;
            default:
                throw new NotDefinedException("Operacio no definida: " + operacio);
        }
    }

    @Override
    public Peticion getPeticion(Operacio operacio, Finalitat finalitat) {
        return peticionBuilder.build(
                CODI_SERVEI,
                CODI_MODALITAT,
                finalitat.name(),
                getDatosEspecificos(operacio)
        );
    }

    private Object[] getDatosEspecificos(Operacio operacio) {
        switch ((ETAULEROperacio) operacio) {
            case CONSULTAR:
                return new Object[]{
                        buildPeticioConsultarEstatEdicte()
                };
            case PUBLICAR:
            case DADES:
            case AMPLIAR_TERMINI:
            case CANCELAR:
            case DESPENJAR:
            case SINCRONITZAR:
            case DESCARREGAR_DOCUMENT:
            default:
                return new Object[]{};
        }
    }

    private static PeticioConsultarEstatEdicte buildPeticioConsultarEstatEdicte() {
        PeticioConsultarEstatEdicte peticio = new PeticioConsultarEstatEdicte();
        peticio.setIdEdicte("ET3-1600672644963");
        return peticio;
    }

}
