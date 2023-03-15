package cat.aoc.client_pci.clients.tfm;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.tfm.PeticioConsultaVigencia;
import generated.tfm.PeticioDadesCompletes;
import generated.tfm.TTipusDocumentacio;

public class TFMPeticionBuilder extends AbstractPeticionBuilder<TFMOperacio> {
    private static final String DOCUMENTACIO = "client.tfm.documentacio";

    public TFMPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(TFMOperacio operacio) {
        return switch (operacio) {
            case TFM_DADESCOMPLETES -> new Object[]{
                    buildPeticioDadesCompletes()
            };
            case TFM_VIGENCIA -> new Object[]{
                    buildPeticioConsultaVigencia()
            };
            default -> new Object[]{};
        };
    }

    private PeticioDadesCompletes buildPeticioDadesCompletes() {
        PeticioDadesCompletes peticioDadesCompletes = new PeticioDadesCompletes();
        peticioDadesCompletes.setIdentificadorTitular(buildIdentificadorTitular());
        return peticioDadesCompletes;
    }

    private PeticioDadesCompletes.IdentificadorTitular buildIdentificadorTitular() {
        PeticioDadesCompletes.IdentificadorTitular identificadorTitular = new PeticioDadesCompletes.IdentificadorTitular();
        identificadorTitular.setDocumentacio(builder.getProperties().getProperty(DOCUMENTACIO));
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
        identificadorTitular.setDocumentacio(builder.getProperties().getProperty(DOCUMENTACIO));
        identificadorTitular.setTipusDocumentacio(TTipusDocumentacio.NIF);
        return identificadorTitular;
    }

}
