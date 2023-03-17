package cat.aoc.client_pci.clients.vo.generalitat.padro_historic;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.padro_historic.DocumentacionTitular;
import generated.padro_historic.PeticionDatosConvivientesHistorico;
import generated.padro_historic.PeticionDatosTitularHistorico;

public class PADRO_HISTORICPeticionBuilder extends AbstractPeticionBuilder<PADRO_HISTORICOperacio> {
    public PADRO_HISTORICPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(PADRO_HISTORICOperacio operacio) {
        return switch (operacio) {
            case TITULAR_HISTORIC -> new Object[]{
                    buildPeticionDatosTitularHistorico()
            };
            case CONVIVENTS_HISTORIC -> new Object[]{
                    buildPeticionDatosConvivientesHistorico()
            };
        };
    }

    private PeticionDatosTitularHistorico buildPeticionDatosTitularHistorico() {
        PeticionDatosTitularHistorico peticion = new PeticionDatosTitularHistorico();
        peticion.setNumExpediente("test");
        DocumentacionTitular titular = new DocumentacionTitular();
        titular.setTipoDocumentacion(1); // DNI
        titular.setDocumentacion("10101010P");
        peticion.setDocumentacionTitular(titular);
        return peticion;
    }

    private PeticionDatosConvivientesHistorico buildPeticionDatosConvivientesHistorico() {
        PeticionDatosConvivientesHistorico peticion = new PeticionDatosConvivientesHistorico();
        peticion.setNumExpediente("test");
        DocumentacionTitular titular = new DocumentacionTitular();
        titular.setTipoDocumentacion(1); // DNI
        titular.setDocumentacion("10101010P");
        peticion.setDocumentacionTitular(titular);
        return peticion;
    }

}
