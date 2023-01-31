package cat.aoc.client_pci.clients.etauler;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.utils.PeticionBuilderImpl;
import generated.etauler.PeticioConsultarEstatEdicte;

public class ETAULERPeticionBuilder extends PeticionBuilderImpl {
    private static final String ID_EDICTE = "client.etauler.id_edicte";

    public ETAULERPeticionBuilder(String propertiesPath) throws NotFoundException {
        super(propertiesPath);
    }

    @Override
    public Object[] getDatosEspecificos(Operacio operacio) throws NotDefinedException {
        try {
            return switch ((ETAULEROperacio) operacio) {
                case CONSULTAR -> new Object[]{
                        buildPeticioConsultarEstatEdicte()
                };
                case PUBLICAR, DADES, AMPLIAR_TERMINI, CANCELAR, DESPENJAR, SINCRONITZAR, DESCARREGAR_DOCUMENT -> new Object[]{};
            };
        } catch (Exception e) {
            throw new NotDefinedException("Operacio no definida: " + operacio);
        }
    }

    private PeticioConsultarEstatEdicte buildPeticioConsultarEstatEdicte() {
        PeticioConsultarEstatEdicte peticio = new PeticioConsultarEstatEdicte();
        peticio.setIdEdicte(properties.getProperty(ID_EDICTE));
        return peticio;
    }

}
