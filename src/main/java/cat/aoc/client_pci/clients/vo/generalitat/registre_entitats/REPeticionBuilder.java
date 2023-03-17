package cat.aoc.client_pci.clients.vo.generalitat.registre_entitats;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.registre_entitats.PeticioBasicaConsulta;

public class REPeticionBuilder extends AbstractPeticionBuilder<REOperacio> {
    public REPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(REOperacio operacio) {
        return switch (operacio) {
            case ENTITAT_INSCRIPCIO -> new Object[]{
                    buildPeticioBasicaConsulta()
            };
            default -> new Object[]{};
        };
    }

    private PeticioBasicaConsulta buildPeticioBasicaConsulta() {
        PeticioBasicaConsulta peticio = new PeticioBasicaConsulta();
        peticio.setNomEntitat("Xarxes");
        return peticio;
    }

}
