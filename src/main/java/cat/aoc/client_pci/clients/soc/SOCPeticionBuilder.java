package cat.aoc.client_pci.clients.soc;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;

public class SOCPeticionBuilder extends AbstractPeticionBuilder<SOCOperacio> {
    public SOCPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(SOCOperacio operacio) {
        return switch (operacio) {
            default -> new Object[]{};
        };
    }

}
