package cat.aoc.client_pci.clients.rgc;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.rgc.PeticioConsultaPrestacions;

public class RGCPeticionBuilder extends AbstractPeticionBuilder<RGCOperacio> {
    public RGCPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(RGCOperacio operacio) {
        return switch (operacio) {
            case RGC_CONSULTA -> new Object[]{
                    buildPeticioConsultaPrestacions()
            };
            default -> new Object[]{};
        };
    }

    private PeticioConsultaPrestacions buildPeticioConsultaPrestacions() {
        PeticioConsultaPrestacions peticio = new PeticioConsultaPrestacions();
        peticio.setDocumentIdentificador("37657624T");
        peticio.setTipusDocument("00001");
        return peticio;
    }

}
