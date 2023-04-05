package cat.aoc.client_pci.samples.serveis.enotum;

import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.enotum.*;
import generated.pci.peticion.Peticion;
import generated.pci.peticion.Fichero;
import generated.pci.peticion.Ficheros;

import java.util.Properties;

import static cat.aoc.client_pci.samples.PeticionBuilderFromPropertiesConstants.CODI_ENS;
import static cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotumCerca.buildPeticioCerca;
import static cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotumConsulta.buildPeticioConsulta;
import static cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotumParaulaPas.buildPeticioParaulaPas;
import static cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotumProcessarTramesa.buildPeticioProcessarTramesa;
import static cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotumResum.buildPeticioResum;

public class PeticionBuilderEnotum extends PeticionBuilderFromProperties<OperacioEnotum> {

    public PeticionBuilderEnotum(Properties properties) {
        super(properties);
    }

    @Override
    public Peticion build(OperacioEnotum operacio, Finalitat finalitat) {
        Peticion peticion = super.build(operacio, finalitat);
        if (operacio == OperacioEnotum.PROCESSAR_TRAMESA) {
            peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos()
                    .setFicheros(getFicheros());
        }
        return peticion;
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioEnotum operacio) {
        return new Object[]{getDatoEspecifico(operacio)};
    }

    private static Ficheros getFicheros() {
        Fichero fichero = new Fichero();
        fichero.setNombreFichero("sample.pdf");
        fichero.setId("1234");
        fichero.setVia("Salida");
        jakarta.activation.DataSource ds = new jakarta.activation.FileDataSource("src\\main\\resources\\examples\\example.pdf");
        fichero.setContenido(new jakarta.activation.DataHandler(ds));
        Ficheros ficheros = new Ficheros();
        ficheros.getFichero().add(fichero);
        return ficheros;
    }

    private Object getDatoEspecifico(OperacioEnotum operacio) {
        return switch (operacio) {
            case CERCA -> buildPeticioCerca(buildEmissor(), buildUsuari());
            case PROCESSAR_TRAMESA -> buildPeticioProcessarTramesa(buildEmissor(), buildUsuari());
            case CONSULTA -> buildPeticioConsulta(buildEmissor(), buildUsuari());
            case RESUM -> buildPeticioResum(buildEmissor(), buildUsuari());
            case PARAULA_PAS -> buildPeticioParaulaPas(buildEmissor(), buildUsuari());
            case EVIDENCIA,
                    PRACTICAR,
                    RECUPERAR_REPORT -> null;
        };
    }

    private EmissorType buildEmissor() {
        EmissorType emissor = new EmissorType();
        emissor.setCodiDepartament(properties.getProperty(CODI_ENS));
        emissor.setCodiOrganisme(properties.getProperty(CODI_ENS));
        return emissor;
    }

    private static UsuariType buildUsuari() {
        UsuariType usuari = new UsuariType();
        usuari.setRol(RolType.EMPLEAT);
        return usuari;
    }

}
