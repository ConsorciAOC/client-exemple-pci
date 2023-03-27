package cat.aoc.client_pci.samples.serveis.vo.estat.dgt;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.dgt.PeticioConsultaVehicle;

import java.util.Properties;

public class PeticionBuilderDgt extends PeticionBuilderFromProperties<OperacioDgt> {
    public PeticionBuilderDgt(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioDgt operacio) {
        return switch (operacio) {
            case DGT_DADES_VEHICLE -> new Object[]{
                    buildPeticioConsultaVehicle()
            };
            case    DGT_DADES_VEHICLE_SANCIONS,
                    DGT_PERMISOS_CONDUCTOR,
                    DGT_TITULAR_VIA,
                    DGT_SANCIONS_CONDUCTOR,
                    DGT_VEHICLES_CONDUCTOR,
                    DGT_DISTINTIU_MEDIAMBIENTAL -> new Object[]{};
        };
    }

    private PeticioConsultaVehicle buildPeticioConsultaVehicle() {
        PeticioConsultaVehicle peticioConsultaVehicle = new PeticioConsultaVehicle();
        peticioConsultaVehicle.setMatricula("AB1234D");
        return peticioConsultaVehicle;
    }

}
