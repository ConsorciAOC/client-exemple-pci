package cat.aoc.client_pci.samples.serveis.vo.generalitat.tfm;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.tfm.PeticioConsultaVigencia;
import generated.serveis.tfm.PeticioDadesCompletes;
import generated.serveis.tfm.TTipusDocumentacio;

import java.util.Properties;

public class PeticionBuilderTfm extends PeticionBuilderFromProperties<OperacioTfm> {
    public PeticionBuilderTfm(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioTfm operacio) {
        return switch (operacio) {
            case TFM_DADESCOMPLETES -> new Object[]{
                    buildPeticioDadesCompletes()
            };
            case TFM_VIGENCIA -> new Object[]{
                    buildPeticioConsultaVigencia()
            };
            case TFM_DADESCOMPLETES_DIS,
                    TFM_DADESCOMPLETES_MASSIU,
                    TFM_DADESCOMPLETES_DIS_MASSIU -> new Object[]{};
        };
    }

    private PeticioDadesCompletes buildPeticioDadesCompletes() {
        PeticioDadesCompletes peticioDadesCompletes = new PeticioDadesCompletes();
        peticioDadesCompletes.setIdentificadorTitular(buildIdentificadorTitular());
        return peticioDadesCompletes;
    }

    private PeticioDadesCompletes.IdentificadorTitular buildIdentificadorTitular() {
        PeticioDadesCompletes.IdentificadorTitular identificadorTitular = new PeticioDadesCompletes.IdentificadorTitular();
        identificadorTitular.setDocumentacio("55564256M");
        identificadorTitular.setTipusDocumentacio(TTipusDocumentacio.NIF);
        return identificadorTitular;
    }

    private PeticioConsultaVigencia buildPeticioConsultaVigencia() {
        PeticioConsultaVigencia peticioConsultaVigencia = new PeticioConsultaVigencia();
        peticioConsultaVigencia.setIdentificadorTitular(buildIdentificadorTitularConsultaVigencia());
        return peticioConsultaVigencia;
    }

    private PeticioConsultaVigencia.IdentificadorTitular buildIdentificadorTitularConsultaVigencia() {
        PeticioConsultaVigencia.IdentificadorTitular identificadorTitular = new PeticioConsultaVigencia.IdentificadorTitular();
        identificadorTitular.setDocumentacio("55564256M");
        identificadorTitular.setTipusDocumentacio(TTipusDocumentacio.NIF);
        return identificadorTitular;
    }

}
