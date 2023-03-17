package cat.aoc.client_pci.clients.vo.rpe;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;

public class RPEPeticionBuilder extends AbstractPeticionBuilder<RPEOperacio> {
    public RPEPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(RPEOperacio operacio) {
        return switch (operacio) {
            default -> new Object[]{};
        };
    }

}
