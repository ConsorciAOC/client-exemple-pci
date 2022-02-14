package cat.aoc.client_pci;

import net.gencat.scsp.esquemes.peticion.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PeticionBuilder {

    public Peticion build(String producto, String modalidad, String finalidad, Object... data){
        Peticion peticion = new Peticion();
        peticion.setAtributos(buildAtributos(producto, modalidad, finalidad));
        peticion.setSolicitudes(buildSolicitudes(modalidad, finalidad, data));
        return peticion;
    }

    private Atributos buildAtributos(String producto, String modalidad, String finalidad){
        Atributos atributos = new Atributos();
        atributos.setIdPeticion("9821920002-" + producto + "-"+System.currentTimeMillis());
        atributos.setNumElementos(1);
        atributos.setTimeStamp(LocalDateTime.now().toString());
        atributos.setCodigoProducto(producto);
        atributos.setCodigoCertificado(modalidad);
        atributos.setEmisor(buildEmisor());
        atributos.setDatosAutorizacion(buildDatosAutorizacion(finalidad));
        atributos.setFuncionario(buildFuncionario());
        return atributos;
    }

    private Emisor buildEmisor(){
        Emisor emisor = new Emisor();
        emisor.setNifEmisor("Q0801175A");
        emisor.setNombreEmisor("CAOC");
        return emisor;
    }

    private DatosAutorizacion buildDatosAutorizacion(String finalidad){
        DatosAutorizacion datosAutorizacion = new DatosAutorizacion();
        datosAutorizacion.setIdentificadorSolicitante("9821920002");
        datosAutorizacion.setNombreSolicitante("Ajuntament");
        datosAutorizacion.setFinalidad(finalidad);
        return datosAutorizacion;
    }

    private Funcionario buildFuncionario(){
        Funcionario funcionario = new Funcionario();
        funcionario.setNifFuncionario("00000000T");
        funcionario.setNombreCompletoFuncionario("Funcionari");
        return funcionario;
    }

    private Solicitudes buildSolicitudes(String modalidad, String finalidad, Object... data){
        Solicitudes solicitudes = new Solicitudes();
        solicitudes.getSolicitudTransmision().add(buildSolicitudTransmision(modalidad, finalidad, data));
        return solicitudes;
    }

    private SolicitudTransmision buildSolicitudTransmision(String modalidad, String finalidad, Object... data){
        SolicitudTransmision solicitudTransmision = new SolicitudTransmision();
        solicitudTransmision.setDatosGenericos(buildDatosGenericos(modalidad, finalidad));
        DatosEspecificos datosEspecificos = new DatosEspecificos();
        List<Object> any = datosEspecificos.getAny();
        any.addAll(Arrays.asList(data));
        solicitudTransmision.setDatosEspecificos(datosEspecificos);
        return solicitudTransmision;
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
        solicitante.setIdentificadorSolicitante("9821920002");
        solicitante.setNombreSolicitante("Ajuntament");
        solicitante.setFinalidad(finalidad);
        solicitante.setConsentimiento("Ley");
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
