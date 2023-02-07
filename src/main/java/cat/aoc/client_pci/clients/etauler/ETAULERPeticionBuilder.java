package cat.aoc.client_pci.clients.etauler;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.etauler.PeticioConsultarEstatEdicte;

public class ETAULERPeticionBuilder extends AbstractPeticionBuilder<ETAULEROperacio> {
    private static final String ID_EDICTE = "client.etauler.id_edicte";

    public ETAULERPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(ETAULEROperacio operacio) {
        return switch (operacio) {
            case CONSULTAR -> new Object[]{
                    buildPeticioConsultarEstatEdicte()
            };
            case PUBLICAR, DADES, AMPLIAR_TERMINI, CANCELAR, DESPENJAR, SINCRONITZAR, DESCARREGAR_DOCUMENT ->
                    new Object[]{};
        };
    }

    private PeticioConsultarEstatEdicte buildPeticioConsultarEstatEdicte() {
        PeticioConsultarEstatEdicte peticio = new PeticioConsultarEstatEdicte();
        peticio.setIdEdicte(builder.getProperties().getProperty(ID_EDICTE));
        return peticio;
    }

}
