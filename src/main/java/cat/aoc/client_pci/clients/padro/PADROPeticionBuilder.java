package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.utils.PeticionBuilderImpl;
import generated.padro.PeticionDatosConvivientes;
import generated.padro.PeticionDatosTitular;

public class PADROPeticionBuilder extends PeticionBuilderImpl {
    private static final String EXPEDIENT = "client.padro.num_expedient";
    private static final String TIPUS_DOCUMENTACIO = "client.padro.tipus_documentacio";
    private static final String DOCUMENTACIO = "client.padro.documentacio";
    private static final String PROVINCIA = "client.padro.provincia";
    private static final String MUNICIPI = "client.padro.municipi";
    private static final String IDESCAT = "client.padro.idescat";

    public PADROPeticionBuilder(String propertiesPath) throws NotFoundException {
        super(propertiesPath);
    }

    @Override
    public Object[] getDatosEspecificos(Operacio operacio) throws NotDefinedException {
        try {
            return switch ((PADROOperacio) operacio) {
                case CONVIVENTS -> new Object[]{
                        buildPeticionDatosConvivientes()
                };
                case TITULAR -> new Object[]{
                        buildPeticionDatosTitular()
                };
                case RESIDENT, MUNICIPI_RESIDENCIA, RESIDENT_MUNICIPI, NUMERO_CONVIVENTS, COMPROVACIO_CONVIVENTS, TITULAR_PROPI, CONVIVENTS_PROPI, TITULAR_PDF, CONVIVENTS_PDF, TITULAR_IDESCAT, CERCA_TITULAR -> new Object[]{};
            };
        } catch (Exception e) {
            throw new NotDefinedException("Modalitat no definida: " + operacio);
        }
    }

    private PeticionDatosConvivientes buildPeticionDatosConvivientes() {
        PeticionDatosConvivientes peticion = new PeticionDatosConvivientes();
        peticion.setNumExpediente(properties.getProperty(EXPEDIENT));
        peticion.setTipoDocumentacion(Integer.parseInt(properties.getProperty(TIPUS_DOCUMENTACIO)));
        peticion.setDocumentacion(properties.getProperty(DOCUMENTACIO));
        peticion.setCodigoProvincia(properties.getProperty(PROVINCIA));
        peticion.setCodigoMunicipio(properties.getProperty(MUNICIPI));
        peticion.setIdescat(Integer.parseInt(properties.getProperty(IDESCAT)));
        return peticion;
    }

    private PeticionDatosTitular buildPeticionDatosTitular() {
        PeticionDatosTitular peticion = new PeticionDatosTitular();
        peticion.setNumExpediente(properties.getProperty(EXPEDIENT));
        peticion.setTipoDocumentacion(Integer.parseInt(properties.getProperty(TIPUS_DOCUMENTACIO)));
        peticion.setDocumentacion(properties.getProperty(DOCUMENTACIO));
        peticion.setCodigoProvincia(properties.getProperty(PROVINCIA));
        peticion.setCodigoMunicipio(properties.getProperty(MUNICIPI));
        peticion.setIdescat(Integer.parseInt(properties.getProperty(IDESCAT)));
        return peticion;
    }

}
