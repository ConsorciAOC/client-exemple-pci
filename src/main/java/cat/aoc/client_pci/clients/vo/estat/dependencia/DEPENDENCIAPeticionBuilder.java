package cat.aoc.client_pci.clients.vo.estat.dependencia;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;

public class DEPENDENCIAPeticionBuilder extends AbstractPeticionBuilder<DEPENDENCIAOperacio> {
    public DEPENDENCIAPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(DEPENDENCIAOperacio operacio) {
        return switch (operacio) {
            case GRAU_DEPENDENCIA -> new Object[]{};
        };
    }

}
