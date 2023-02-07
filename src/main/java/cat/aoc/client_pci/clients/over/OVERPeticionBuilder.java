package cat.aoc.client_pci.clients.over;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.over.PeticioDocumentacioTramit;

public class OVERPeticionBuilder extends AbstractPeticionBuilder<OVEROperacio> {
    public OVERPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(OVEROperacio operacio) {
        return switch (operacio) {
            case OVER_DOCUMENTACIO -> new Object[]{
                    buildPeticioDocumentacioTramit()
            };
            default -> new Object[]{};
        };
    }

    private PeticioDocumentacioTramit buildPeticioDocumentacioTramit() {
        PeticioDocumentacioTramit peticio = new PeticioDocumentacioTramit();
        peticio.setCodiInstanciaTramit(400547L);
        return peticio;
    }

}
