package cat.aoc.client_pci.tfn;

import cat.aoc.client_pci.jaxb.pci.*;
import cat.aoc.client_pci.jaxb.tfn.PeticioDadesCompletes;
import cat.aoc.client_pci.jaxb.tfn.TTipusDocumentacio;

import java.time.LocalDateTime;

public class TFNClient {

    public Peticion buildPeticion(){
        Peticion peticion = new Peticion();
        peticion.setAtributos(buildAtributos());
        peticion.setSolicitudes(buildSolicitudes());
        return peticion;
    }

    // Per fer diferents proves ràpid
    public Peticion setDatosEspecificos(Peticion peticion, Object datosEspecificos){
        peticion.getSolicitudes().getSolicitudTransmision().get(0).setDatosEspecificos(datosEspecificos);
        return peticion;
    }

    private Atributos buildAtributos(){
        Atributos atributos = new Atributos();
        atributos.setIdPeticion("9821920002-TFN-"+System.currentTimeMillis());
        atributos.setNumElementos(0);
        atributos.setTimeStamp(LocalDateTime.now().toString());
        atributos.setCodigoProducto("TFN");
        atributos.setCodigoCertificado("TFN_DADESCOMPLETES");
        atributos.setEmisor(buildEmisor());
        atributos.setDatosAutorizacion(buildDatosAutorizacion());
        atributos.setFuncionario(buildFuncionario());
        return atributos;
    }

    private Emisor buildEmisor(){
        Emisor emisor = new Emisor();
        emisor.setNifEmisor("Q0801175A");
        emisor.setNombreEmisor("CAOC");
        return emisor;
    }

    private DatosAutorizacion buildDatosAutorizacion(){
        DatosAutorizacion datosAutorizacion = new DatosAutorizacion();
        datosAutorizacion.setIdentificadorSolicitante("9821920002");
        datosAutorizacion.setNombreSolicitante("Ajuntament");
        datosAutorizacion.setFinalidad("PROVES");
        return datosAutorizacion;
    }

    private Funcionario buildFuncionario(){
        Funcionario funcionario = new Funcionario();
        funcionario.setNifFuncionario("00000000T");
        funcionario.setNombreCompletoFuncionario("Funcionari");
        return funcionario;
    }

    private Solicitudes buildSolicitudes(){
        Solicitudes solicitudes = new Solicitudes();
        solicitudes.getSolicitudTransmision().add(buildSolicitudTransmision());
        return solicitudes;
    }

    private SolicitudTransmision buildSolicitudTransmision(){
        SolicitudTransmision solicitudTransmision = new SolicitudTransmision();
        solicitudTransmision.setDatosGenericos(buildDatosGenericos());
        solicitudTransmision.setDatosEspecificos(buildDatosEspecificos()); // HERE!!!!!! :(
        return solicitudTransmision;
    }

    private DatosGenericos buildDatosGenericos(){
        DatosGenericos datosGenericos = new DatosGenericos();
        datosGenericos.setEmisor(buildEmisor());
        datosGenericos.setSolicitante(buildSolicitante());
        datosGenericos.setTransmision(buildTransmision());
        return datosGenericos;
    }

    private Solicitante buildSolicitante(){
        Solicitante solicitante = new Solicitante();
        solicitante.setIdentificadorSolicitante("9821920002");
        solicitante.setNombreSolicitante("Ajuntament");
        solicitante.setFinalidad("PROVES");
        solicitante.setConsentimiento("Ley");
        solicitante.setFuncionario(buildFuncionario());
        return solicitante;
    }

    private Transmision buildTransmision(){
        Transmision transmision = new Transmision();
        transmision.setCodigoCertificado("TFN_DADESCOMPLETES");
        transmision.setIdSolicitud("1");
        transmision.setIdTransmision("1");
        return transmision;
    }

    private Object buildDatosEspecificos() {
        DatosEspecificosTFN datosEspecificosTFN = new DatosEspecificosTFN();
        datosEspecificosTFN.setPeticioDadesCompletes(buildPeticioDadesCompletes());
        return new DatosEspecificosTFN();
    }

    private static PeticioDadesCompletes buildPeticioDadesCompletes(){
        PeticioDadesCompletes peticioDadesCompletes = new PeticioDadesCompletes();
        peticioDadesCompletes.setIdentificadorTitular(buildIdentificadorTitular());
        return peticioDadesCompletes;
    }

    private static PeticioDadesCompletes.IdentificadorTitular buildIdentificadorTitular(){
        PeticioDadesCompletes.IdentificadorTitular identificadorTitular = new PeticioDadesCompletes.IdentificadorTitular();
        identificadorTitular.setDocumentacio("38991311D");
        identificadorTitular.setTipusDocumentacio(TTipusDocumentacio.NIF);
        return identificadorTitular;
    }

}
