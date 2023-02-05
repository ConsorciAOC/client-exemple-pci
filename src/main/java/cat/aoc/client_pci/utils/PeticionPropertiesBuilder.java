package cat.aoc.client_pci.utils;

import lombok.Getter;
import net.gencat.scsp.esquemes.peticion.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Getter
public class PeticionPropertiesBuilder {
    public static final String CODI_ENS = "client.codi_ens";
    public static final String NIF_EMISOR = "client.emisor.nif";
    public static final String NOMBRE_EMISOR = "client.emisor.nombre";
    public static final String IDENTIFICADOR_SOLICITANTE = "client.solicitante.identificador";
    public static final String NOMBRE_SOLICITANTE = "client.solicitante.nombre";
    public static final String CONSENTIMIENTO = "client.solicitante.consentimiento";
    public static final String NIF_FUNCIONARIO = "client.funcionario.nif";
    public static final String NOMBRE_FUNCIONARIO = "client.funcionario.nombre";

    private final Properties properties;

    public PeticionPropertiesBuilder(Properties properties) {
        this.properties = properties;
    }

    public Peticion build(String producto, String modalidad, String finalidad, Object... datosEspecificos) {
        Peticion peticion = new Peticion();
        peticion.setAtributos(buildAtributos(producto, modalidad, finalidad));
        peticion.setSolicitudes(buildSolicitudes(modalidad, finalidad, datosEspecificos));
        return peticion;
    }

    private Atributos buildAtributos(String producto, String modalidad, String finalidad) {
        Atributos atributos = new Atributos();
        atributos.setIdPeticion(buildIdPeticion(producto));
        atributos.setNumElementos(1);
        atributos.setTimeStamp(LocalDateTime.now().toString());
        atributos.setCodigoProducto(producto);
        atributos.setCodigoCertificado(modalidad);
        atributos.setEmisor(buildEmisor());
        atributos.setDatosAutorizacion(buildDatosAutorizacion(finalidad));
        atributos.setFuncionario(buildFuncionario());
        return atributos;
    }

    private String buildIdPeticion(String producto) {
        return properties.getProperty(CODI_ENS) + "-" + producto + "-" + System.currentTimeMillis();
    }

    private Emisor buildEmisor() {
        Emisor emisor = new Emisor();
        emisor.setNifEmisor(properties.getProperty(NIF_EMISOR));
        emisor.setNombreEmisor(properties.getProperty(NOMBRE_EMISOR));
        return emisor;
    }

    private DatosAutorizacion buildDatosAutorizacion(String finalidad) {
        DatosAutorizacion datosAutorizacion = new DatosAutorizacion();
        datosAutorizacion.setIdentificadorSolicitante(properties.getProperty(IDENTIFICADOR_SOLICITANTE));
        datosAutorizacion.setNombreSolicitante(properties.getProperty(NOMBRE_SOLICITANTE));
        datosAutorizacion.setFinalidad(finalidad);
        return datosAutorizacion;
    }

    private Funcionario buildFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNifFuncionario(properties.getProperty(NIF_FUNCIONARIO));
        funcionario.setNombreCompletoFuncionario(properties.getProperty(NOMBRE_FUNCIONARIO));
        return funcionario;
    }

    private Solicitudes buildSolicitudes(String modalidad, String finalidad, Object... datosEspecificos) {
        Solicitudes solicitudes = new Solicitudes();
        solicitudes.getSolicitudTransmision().add(buildSolicitudTransmision(modalidad, finalidad, datosEspecificos));
        return solicitudes;
    }

    private SolicitudTransmision buildSolicitudTransmision(String modalidad, String finalidad, Object... datosEspecificos) {
        SolicitudTransmision solicitudTransmision = new SolicitudTransmision();
        solicitudTransmision.setDatosGenericos(buildDatosGenericos(modalidad, finalidad));
        solicitudTransmision.setDatosEspecificos(buildDatosEspecificos(datosEspecificos));
        return solicitudTransmision;
    }

    private DatosEspecificos buildDatosEspecificos(Object... datos) {
        DatosEspecificos datosEspecificos = new DatosEspecificos();
        List<Object> any = datosEspecificos.getAny();
        any.addAll(Arrays.asList(datos));
        return datosEspecificos;
    }

    private DatosGenericos buildDatosGenericos(String modalidad, String finalidad) {
        DatosGenericos datosGenericos = new DatosGenericos();
        datosGenericos.setEmisor(buildEmisor());
        datosGenericos.setSolicitante(buildSolicitante(finalidad));
        datosGenericos.setTransmision(buildTransmision(modalidad));
        return datosGenericos;
    }

    private Solicitante buildSolicitante(String finalidad) {
        Solicitante solicitante = new Solicitante();
        solicitante.setIdentificadorSolicitante(properties.getProperty(IDENTIFICADOR_SOLICITANTE));
        solicitante.setNombreSolicitante(properties.getProperty(NOMBRE_SOLICITANTE));
        solicitante.setFinalidad(finalidad);
        solicitante.setConsentimiento(properties.getProperty(CONSENTIMIENTO));
        solicitante.setFuncionario(buildFuncionario());
        return solicitante;
    }

    private Transmision buildTransmision(String modalidad) {
        Transmision transmision = new Transmision();
        transmision.setCodigoCertificado(modalidad);
        transmision.setIdSolicitud("1");
        transmision.setIdTransmision("1");
        return transmision;
    }

}
