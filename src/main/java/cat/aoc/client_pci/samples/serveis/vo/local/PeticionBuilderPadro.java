package cat.aoc.client_pci.samples.serveis.vo.local;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.padro.PeticionDatosConvivientes;
import generated.padro.PeticionDatosTitular;

import java.util.Properties;

public class PeticionBuilderPadro extends PeticionBuilderFromProperties<OperacioPadro> {

    public PeticionBuilderPadro(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioPadro operacio) {
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
        peticion.setNumExpediente("prova");
        peticion.setTipoDocumentacion(1);
        peticion.setDocumentacion("12345678Z");
        peticion.setCodigoProvincia("08");
        peticion.setCodigoMunicipio("001");
        peticion.setIdescat(0);
        return peticion;
    }

    private PeticionDatosTitular buildPeticionDatosTitular() {
        PeticionDatosTitular peticion = new PeticionDatosTitular();
        peticion.setNumExpediente("prova");
        peticion.setTipoDocumentacion(1);
        peticion.setDocumentacion("12345678Z");
        peticion.setCodigoProvincia("08");
        peticion.setCodigoMunicipio("001");
        peticion.setIdescat(0);
        return peticion;
    }

}
