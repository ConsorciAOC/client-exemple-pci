package cat.aoc.client_pci.utils;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Operacio;
import net.gencat.scsp.esquemes.peticion.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public abstract class PeticionBuilderImpl implements PeticionBuilder {
    protected static final String CODI_ENS = "client.codi_ens";
    protected static final String NIF_EMISOR = "client.emisor.nif";
    protected static final String NOMBRE_EMISOR = "client.emisor.nombre";
    protected static final String IDENTIFICADOR_SOLICITANTE = "client.solicitante.identificador";
    protected static final String NOMBRE_SOLICITANTE = "client.solicitante.nombre";
    protected static final String CONSENTIMIENTO = "client.solicitante.consentimiento";
    protected static final String NIF_FUNCIONARIO = "client.funcionario.nif";
    protected static final String NOMBRE_FUNCIONARIO = "client.funcionario.nombre";

    protected final Properties properties;

    protected PeticionBuilderImpl(String propertiesPath) throws NotFoundException {
        properties = PropertiesReader.load(propertiesPath);
    }

    @Override
    public Peticion build(String producto, Operacio operacio, Finalitat finalidad) {
        Peticion peticion = new Peticion();
        peticion.setAtributos(buildAtributos(producto, operacio.getCodiModalitat(), finalidad.name()));
        peticion.setSolicitudes(buildSolicitudes(operacio.getCodiModalitat(), finalidad.name(), operacio));
        return peticion;
    }

    protected abstract Object[] getDatosEspecificos(Operacio operacio) throws NotDefinedException;

    private Atributos buildAtributos(String producto, String modalidad, String finalidad){
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

    private Emisor buildEmisor(){
        Emisor emisor = new Emisor();
        emisor.setNifEmisor(properties.getProperty(NIF_EMISOR));
        emisor.setNombreEmisor(properties.getProperty(NOMBRE_EMISOR));
        return emisor;
    }

    private DatosAutorizacion buildDatosAutorizacion(String finalidad){
        DatosAutorizacion datosAutorizacion = new DatosAutorizacion();
        datosAutorizacion.setIdentificadorSolicitante(properties.getProperty(IDENTIFICADOR_SOLICITANTE));
        datosAutorizacion.setNombreSolicitante(properties.getProperty(NOMBRE_SOLICITANTE));
        datosAutorizacion.setFinalidad(finalidad);
        return datosAutorizacion;
    }

    private Funcionario buildFuncionario(){
        Funcionario funcionario = new Funcionario();
        funcionario.setNifFuncionario(properties.getProperty(NIF_FUNCIONARIO));
        funcionario.setNombreCompletoFuncionario(properties.getProperty(NOMBRE_FUNCIONARIO));
        return funcionario;
    }

    private Solicitudes buildSolicitudes(String modalidad, String finalidad, Operacio operacio){
        Solicitudes solicitudes = new Solicitudes();
        solicitudes.getSolicitudTransmision().add(buildSolicitudTransmision(modalidad, finalidad, operacio));
        return solicitudes;
    }

    private SolicitudTransmision buildSolicitudTransmision(String modalidad, String finalidad, Operacio operacio){
        SolicitudTransmision solicitudTransmision = new SolicitudTransmision();
        solicitudTransmision.setDatosGenericos(buildDatosGenericos(modalidad, finalidad));
        solicitudTransmision.setDatosEspecificos(buildDatosEspecificos(operacio));
        return solicitudTransmision;
    }

    private DatosEspecificos buildDatosEspecificos(Operacio operacio){
        DatosEspecificos datosEspecificos = new DatosEspecificos();
        try {
            List<Object> any = datosEspecificos.getAny();
            any.addAll(Arrays.asList(getDatosEspecificos(operacio)));
        } catch (NotDefinedException e) {
            e.printStackTrace();
        }
        return datosEspecificos;
    }

    private DatosGenericos buildDatosGenericos(String modalidad, String finalidad){
        DatosGenericos datosGenericos = new DatosGenericos();
        datosGenericos.setEmisor(buildEmisor());
        datosGenericos.setSolicitante(buildSolicitante(finalidad));
        datosGenericos.setTransmision(buildTransmision(modalidad));
        return datosGenericos;
    }

    private Solicitante buildSolicitante(String finalidad){
        Solicitante solicitante = new Solicitante();
        solicitante.setIdentificadorSolicitante(properties.getProperty(IDENTIFICADOR_SOLICITANTE));
        solicitante.setNombreSolicitante(properties.getProperty(NOMBRE_SOLICITANTE));
        solicitante.setFinalidad(finalidad);
        solicitante.setConsentimiento(properties.getProperty(CONSENTIMIENTO));
        solicitante.setFuncionario(buildFuncionario());
        return solicitante;
    }

    private Transmision buildTransmision(String modalidad){
        Transmision transmision = new Transmision();
        transmision.setCodigoCertificado(modalidad);
        transmision.setIdSolicitud("1");
        transmision.setIdTransmision("1");
        return transmision;
    }

}
