package cat.aoc.client_pci.clients.vo.estat.dgt;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.dgt.PeticioConsultaVehicle;

public class DGTPeticionBuilder extends AbstractPeticionBuilder<DGTOperacio> {
    public DGTPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(DGTOperacio operacio) {
        return switch (operacio) {
            case DGT_DADES_VEHICLE -> new Object[]{
                    buildPeticioConsultaVehicle()
            };
            default -> new Object[]{};
        };
    }

    private PeticioConsultaVehicle buildPeticioConsultaVehicle() {
        PeticioConsultaVehicle peticioConsultaVehicle = new PeticioConsultaVehicle();
        peticioConsultaVehicle.setMatricula("AB1234D");
        return peticioConsultaVehicle;
    }

}
