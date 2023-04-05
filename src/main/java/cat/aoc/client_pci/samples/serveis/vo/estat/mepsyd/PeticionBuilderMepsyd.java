package cat.aoc.client_pci.samples.serveis.vo.estat.mepsyd;

import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.pci.peticion.Peticion;
import generated.pci.peticion.Titular;

import java.util.Properties;

public class PeticionBuilderMepsyd extends PeticionBuilderFromProperties<OperacioMepsyd> {
    public PeticionBuilderMepsyd(Properties properties) {
        super(properties);
    }

    @Override
    public Peticion build(OperacioMepsyd operacio, Finalitat finalitat) {
        Peticion peticion = super.build(operacio, finalitat);
        peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos().setTitular(getTitular());
        return peticion;
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioMepsyd operacio) {
        return switch (operacio) {
            case TITOLS_UNIVERSITARIS,
                    TITOLS_NO_UNIVERSITARIS,
                    TITOLS_UNIVERSITARIS_LLISTAT,
                    TITOLS_NO_UNIVERSITARIS_LLISTAT -> new Object[]{};
        };
    }

    private static Titular getTitular() {
        Titular titular = new Titular();
        titular.setTipoDocumentacion("NIE");
        titular.setDocumentacion("Y0000000Z");
        return titular;
    }

}
