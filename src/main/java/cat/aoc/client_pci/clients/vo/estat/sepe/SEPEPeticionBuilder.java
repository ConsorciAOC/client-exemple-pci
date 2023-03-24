package cat.aoc.client_pci.clients.vo.estat.sepe;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;

public class SEPEPeticionBuilder extends AbstractPeticionBuilder<SEPEOperacio> {
    public SEPEPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(SEPEOperacio operacio) {
        return switch (operacio) {
            default -> new Object[]{
            };
        };
    }

}
