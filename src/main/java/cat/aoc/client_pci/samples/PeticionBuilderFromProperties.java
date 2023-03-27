package cat.aoc.client_pci.samples;

import cat.aoc.client_pci.api.model.Finalitat;
import net.gencat.scsp.esquemes.peticion.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static cat.aoc.client_pci.samples.PeticionBuilderFromPropertiesConstants.*;

public abstract class PeticionBuilderFromProperties<O extends Operacio> implements PeticionBuilder<O> {
    protected final Properties properties;

    protected PeticionBuilderFromProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public Peticion build(O operacio, Finalitat finalitat) {
        Peticion peticion = new Peticion();
        peticion.setAtributos(buildAtributos(operacio, finalitat.name()));
        peticion.setSolicitudes(buildSolicitudes(operacio.getCodiModalitat(), finalitat.name(), getDatosEspecificos(operacio)));
        return peticion;
    }

    protected abstract Object[] getDatosEspecificos(O operacio);

    private Atributos buildAtributos(Operacio operacio, String finalitat) {
        Atributos atributos = new Atributos();
        atributos.setIdPeticion(buildIdPeticion(operacio));
        atributos.setNumElementos(1);
        atributos.setTimeStamp(LocalDateTime.now().toString());
        atributos.setCodigoProducto(operacio.getCodiProducte());
        atributos.setCodigoCertificado(operacio.getCodiModalitat());
        atributos.setEmisor(buildEmisor());
        atributos.setDatosAutorizacion(buildDatosAutorizacion(finalitat));
        atributos.setFuncionario(buildFuncionario());
        return atributos;
    }

    private String buildIdPeticion(Operacio operacio) {
        return properties.getProperty(CODI_ENS) + "-" + operacio.getCodiProducte() + "-" + System.currentTimeMillis();
    }

    private Emisor buildEmisor() {
        Emisor emisor = new Emisor();
        emisor.setNifEmisor(properties.getProperty(NIF_EMISOR));
        emisor.setNombreEmisor(properties.getProperty(NOMBRE_EMISOR));
        return emisor;
    }

    private DatosAutorizacion buildDatosAutorizacion(String finalitat) {
        DatosAutorizacion datosAutorizacion = new DatosAutorizacion();
        datosAutorizacion.setIdentificadorSolicitante(properties.getProperty(IDENTIFICADOR_SOLICITANTE));
        datosAutorizacion.setNombreSolicitante(properties.getProperty(NOMBRE_SOLICITANTE));
        datosAutorizacion.setFinalidad(finalitat);
        return datosAutorizacion;
    }

    private Funcionario buildFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNifFuncionario(properties.getProperty(NIF_FUNCIONARIO));
        funcionario.setNombreCompletoFuncionario(properties.getProperty(NOMBRE_FUNCIONARIO));
        return funcionario;
    }

    private Solicitudes buildSolicitudes(String modalidad, String finalitat, Object... datosEspecificos) {
        Solicitudes solicitudes = new Solicitudes();
        solicitudes.getSolicitudTransmision().add(buildSolicitudTransmision(modalidad, finalitat, datosEspecificos));
        return solicitudes;
    }

    private SolicitudTransmision buildSolicitudTransmision(String modalidad, String finalitat, Object... datosEspecificos) {
        SolicitudTransmision solicitudTransmision = new SolicitudTransmision();
        solicitudTransmision.setDatosGenericos(buildDatosGenericos(modalidad, finalitat));
        solicitudTransmision.setDatosEspecificos(buildDatosEspecificos(datosEspecificos));
        return solicitudTransmision;
    }

    private DatosEspecificos buildDatosEspecificos(Object... datos) {
        DatosEspecificos datosEspecificos = new DatosEspecificos();
        List<Object> any = datosEspecificos.getAny();
        any.addAll(Arrays.asList(datos));
        return datosEspecificos;
    }

    private DatosGenericos buildDatosGenericos(String modalitat, String finalitat) {
        DatosGenericos datosGenericos = new DatosGenericos();
        datosGenericos.setEmisor(buildEmisor());
        datosGenericos.setSolicitante(buildSolicitante(finalitat));
        datosGenericos.setTransmision(buildTransmision(modalitat));
        return datosGenericos;
    }

    private Solicitante buildSolicitante(String finalitat) {
        Solicitante solicitante = new Solicitante();
        solicitante.setIdentificadorSolicitante(properties.getProperty(IDENTIFICADOR_SOLICITANTE));
        solicitante.setNombreSolicitante(properties.getProperty(NOMBRE_SOLICITANTE));
        solicitante.setFinalidad(finalitat);
        solicitante.setConsentimiento(properties.getProperty(CONSENTIMIENTO));
        solicitante.setFuncionario(buildFuncionario());
        return solicitante;
    }

    private Transmision buildTransmision(String modalitat) {
        Transmision transmision = new Transmision();
        transmision.setCodigoCertificado(modalitat);
        transmision.setIdSolicitud("1");
        transmision.setIdTransmision("1");
        return transmision;
    }

}
