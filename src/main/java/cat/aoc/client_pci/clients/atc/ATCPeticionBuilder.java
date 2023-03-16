package cat.aoc.client_pci.clients.atc;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;

public class ATCPeticionBuilder extends AbstractPeticionBuilder<ATCOperacio> {
    public ATCPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(ATCOperacio operacio) {
        return switch (operacio) {
            case ATC_INF_DEUTES_TMP -> new Object[]{};
        };
    }


}
