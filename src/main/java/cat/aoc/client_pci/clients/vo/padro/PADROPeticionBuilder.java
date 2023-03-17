package cat.aoc.client_pci.clients.vo.padro;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.padro.PeticionDatosConvivientes;
import generated.padro.PeticionDatosTitular;

public class PADROPeticionBuilder extends AbstractPeticionBuilder<PADROOperacio> {
    private static final String EXPEDIENT = "client.padro.num_expedient";
    private static final String TIPUS_DOCUMENTACIO = "client.padro.tipus_documentacio";
    private static final String DOCUMENTACIO = "client.padro.documentacio";
    private static final String PROVINCIA = "client.padro.provincia";
    private static final String MUNICIPI = "client.padro.municipi";
    private static final String IDESCAT = "client.padro.idescat";

    public PADROPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(PADROOperacio operacio) {
        return switch (operacio) {
            case CONVIVENTS -> new Object[]{
                    buildPeticionDatosConvivientes()
            };
            case TITULAR -> new Object[]{
                    buildPeticionDatosTitular()
            };
            case RESIDENT, MUNICIPI_RESIDENCIA, RESIDENT_MUNICIPI, NUMERO_CONVIVENTS, COMPROVACIO_CONVIVENTS, TITULAR_PROPI, CONVIVENTS_PROPI, TITULAR_PDF, CONVIVENTS_PDF, TITULAR_IDESCAT, CERCA_TITULAR ->
                    new Object[]{};
        };
    }

    private PeticionDatosConvivientes buildPeticionDatosConvivientes() {
        PeticionDatosConvivientes peticion = new PeticionDatosConvivientes();
        peticion.setNumExpediente(builder.getProperties().getProperty(EXPEDIENT));
        peticion.setTipoDocumentacion(Integer.parseInt(builder.getProperties().getProperty(TIPUS_DOCUMENTACIO)));
        peticion.setDocumentacion(builder.getProperties().getProperty(DOCUMENTACIO));
        peticion.setCodigoProvincia(builder.getProperties().getProperty(PROVINCIA));
        peticion.setCodigoMunicipio(builder.getProperties().getProperty(MUNICIPI));
        peticion.setIdescat(Integer.parseInt(builder.getProperties().getProperty(IDESCAT)));
        return peticion;
    }

    private PeticionDatosTitular buildPeticionDatosTitular() {
        PeticionDatosTitular peticion = new PeticionDatosTitular();
        peticion.setNumExpediente(builder.getProperties().getProperty(EXPEDIENT));
        peticion.setTipoDocumentacion(Integer.parseInt(builder.getProperties().getProperty(TIPUS_DOCUMENTACIO)));
        peticion.setDocumentacion(builder.getProperties().getProperty(DOCUMENTACIO));
        peticion.setCodigoProvincia(builder.getProperties().getProperty(PROVINCIA));
        peticion.setCodigoMunicipio(builder.getProperties().getProperty(MUNICIPI));
        peticion.setIdescat(Integer.parseInt(builder.getProperties().getProperty(IDESCAT)));
        return peticion;
    }

}
