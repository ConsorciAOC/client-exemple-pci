package cat.aoc.client_pci.samples.serveis.vo.generalitat.atc;

import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.peticion.Titular;

import java.util.Properties;

public class PeticionBuilderAtc extends PeticionBuilderFromProperties<OperacioAtc> {
    public PeticionBuilderAtc(Properties properties) {
        super(properties);
    }

    @Override
    public Peticion build(OperacioAtc operacio, Finalitat finalitat) {
        Peticion peticion = super.build(operacio, finalitat);
        peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos().setTitular(getTitular());
        return peticion;
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioAtc operacio) {
        return switch (operacio) {
            case ATC_INF_DEUTES_TMP -> new Object[]{};
        };
    }

    private static Titular getTitular() {
        Titular titular = new Titular();
        titular.setTipoDocumentacion("DNI");
        titular.setDocumentacion("10000949C");
        titular.setNombre("OLGA");
        titular.setApellido1("MIGUEL");
        titular.setApellido2("CHAO");
        titular.setNombreCompleto("OLGA MIGUEL CHAO");
        return titular;
    }

}
