package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import cat.aoc.client_pci.utils.PeticionPropertiesBuilder;
import generated.enotum.*;
import net.gencat.scsp.esquemes.peticion.Fichero;
import net.gencat.scsp.esquemes.peticion.Ficheros;
import net.gencat.scsp.esquemes.peticion.Peticion;

import java.math.BigInteger;

public class ENOTUMPeticionBuilder extends AbstractPeticionBuilder<ENOTUMOperacio> {
    private static final String PLANTILLA = "client.enotum.plantilla";
    private static final String COS_NOTIFICACIO = "client.enotum.cosNotificacio";
    private static final String PEU_RECURS = "client.enotum.peuRecurs";
    private static final String TITOL = "client.enotum.titol";
    private static final String REFERENCIA = "client.enotum.referencia";
    private static final String BUSTIA = "client.enotum.bustia";
    private static final String NIF = "client.enotum.nif";
    private static final String NOM = "client.enotum.nom";
    private static final String PRIMER_COGNOM = "client.enotum.primerCognom";
    private static final String SEGON_COGNOM = "client.enotum.segonCognom";
    private static final String ID_NOTIFICACIO = "client.enotum.idNotificacio";

    public ENOTUMPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    public Peticion build(ENOTUMOperacio operacio, Finalitat finalitat) {
        Peticion peticion = builder.build(operacio.getCodiProducte(), operacio.getCodiModalitat(), finalitat.name(), getDatosEspecificos(operacio));
        Fichero fichero = new Fichero();
        fichero.setNombreFichero("sample.pdf");
        fichero.setId("1234");
        fichero.setVia("Salida");
        jakarta.activation.DataSource ds = new jakarta.activation.FileDataSource("src\\main\\resources\\examples\\example.pdf");
        fichero.setContenido(new jakarta.activation.DataHandler(ds));
        Ficheros ficheros = new Ficheros();
        ficheros.getFichero().add(fichero);
        peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos()
                .setFicheros(ficheros);
        return peticion;
    }

    @Override
    protected Object[] getDatosEspecificos(ENOTUMOperacio operacio) {
        return switch (operacio) {
            case CERCA -> new Object[]{
                    buildPeticioCerca()
            };
            case PROCESSAR_TRAMESA -> new Object[]{
                    buildPeticioProcessarTramesa()
            };
            case CONSULTA -> new Object[]{
                    buildPeticioConsulta()
            };
            case RESUM -> new Object[]{
                    buildPeticioResum()
            };
            case PARAULA_PAS -> new Object[]{
                    buildParaulaPas()
            };
            case EVIDENCIA, PRACTICAR, RECUPERAR_REPORT -> new Object[]{};
        };
    }

    private PeticioCerca buildPeticioCerca() {
        PeticioCerca.DadesCerca dadesCerca = new PeticioCerca.DadesCerca();

        PeticioCerca.DadesCerca.Paginacio paginacio = new PeticioCerca.DadesCerca.Paginacio();
        paginacio.setNumeroPagina(BigInteger.valueOf(1));
        paginacio.setResultatsPerPagina(25);
        paginacio.setSentitOrdenacio(SentitOrdenacioType.DESCENDENT);
        dadesCerca.setPaginacio(paginacio);

        IntervalValors valors = new IntervalValors();
        valors.setValorAbsolut(builder.getProperties().getProperty(REFERENCIA));
        PeticioCerca.DadesCerca.CriterisNotificacio criteris = new PeticioCerca.DadesCerca.CriterisNotificacio();
        criteris.setReferencia(valors);
        dadesCerca.setCriterisNotificacio(criteris);

        PeticioCerca peticio = new PeticioCerca();
        peticio.setDadesCerca(dadesCerca);
        peticio.setUsuari(buildUsuari());
        peticio.setEmissor(buildEmissor());
        return peticio;
    }

    private PeticioConsulta buildPeticioConsulta() {
        PeticioConsulta peticio = new PeticioConsulta();
        peticio.setIdNotificacio(new BigInteger(builder.getProperties().getProperty(ID_NOTIFICACIO)));
        peticio.setUsuari(buildUsuari());
        peticio.setEmissor(buildEmissor());
        return peticio;
    }

    private PeticioResum buildPeticioResum() {
        PeticioResum peticio = new PeticioResum();
        peticio.setUsuari(buildUsuari());
        peticio.setEmissor(buildEmissor());
        return peticio;
    }

    private PeticioParaulaPas buildParaulaPas() {
        PeticioParaulaPas peticio = new PeticioParaulaPas();
        PeticioParaulaPas.DadesEnviament dades = new PeticioParaulaPas.DadesEnviament();
        dades.setBustiaCorreu(builder.getProperties().getProperty(BUSTIA));
        peticio.setDadesEnviament(dades);
        peticio.setUsuari(buildUsuari());
        peticio.setEmissor(buildEmissor());
        return peticio;
    }

    private PeticioProcessarTramesa buildPeticioProcessarTramesa() {
        PeticioProcessarTramesa.Tramesa tramesa = new PeticioProcessarTramesa.Tramesa();
        PeticioProcessarTramesa.Tramesa.DadesAvisos dadesAvisos = new PeticioProcessarTramesa.Tramesa.DadesAvisos();
        dadesAvisos.setPlantilla(builder.getProperties().getProperty(PLANTILLA));
        tramesa.setDadesAvisos(dadesAvisos);
        PeticioProcessarTramesa.Tramesa.DadesOfici dadesOfici = new PeticioProcessarTramesa.Tramesa.DadesOfici();
        dadesOfici.setCosNotificacio(builder.getProperties().getProperty(COS_NOTIFICACIO));
        dadesOfici.setPeuRecurs(builder.getProperties().getProperty(PEU_RECURS));
        tramesa.setDadesOfici(dadesOfici);
        NotificacioType notificacio = new NotificacioType();
        notificacio.setTitol(builder.getProperties().getProperty(TITOL));
        notificacio.setReferencia(builder.getProperties().getProperty(REFERENCIA));
        notificacio.setTipusObjecte(TipusObjecteType.NOTIFICACIO);
        notificacio.setTipusAcces(TipusAccesType.PPAS);
        notificacio.setDiesExpiracio(BigInteger.TEN);
        DestinatarisType destinataris = new DestinatarisType();
        DestinatarisType.Destinatari destinatari = new DestinatarisType.Destinatari();
        PersonaFisicaType personaFisica = new PersonaFisicaType();
        BustiesCorreuType bustia = new BustiesCorreuType();
        bustia.getBustiaCorreu().add(builder.getProperties().getProperty(BUSTIA));
        personaFisica.setBustiesCorreu(bustia);
        DocumentPersonaFisicaType documentPersonaFisica = new DocumentPersonaFisicaType();
        documentPersonaFisica.setNIF(builder.getProperties().getProperty(NIF));
        personaFisica.setDocumentIdentificatiu(documentPersonaFisica);
        personaFisica.setNom(builder.getProperties().getProperty(NOM));
        personaFisica.setPrimerCognom(builder.getProperties().getProperty(PRIMER_COGNOM));
        personaFisica.setSegonCognom(builder.getProperties().getProperty(SEGON_COGNOM));
        destinatari.setPersonaFisica(personaFisica);
        destinatari.setIdioma(Idioma.CA);
        destinataris.getDestinatari().add(destinatari);
        notificacio.setDestinataris(destinataris);
        tramesa.getNotificacio().add(notificacio);
        DocumentsType.Document document = getDocument();
        DocumentsType documentsType = new DocumentsType();
        documentsType.getDocument().add(document);
        tramesa.setDocuments(documentsType);
        PeticioProcessarTramesa peticio = new PeticioProcessarTramesa();
        peticio.setTramesa(tramesa);
        peticio.setUsuari(buildUsuari());
        peticio.setEmissor(buildEmissor());
        return peticio;
    }

    private static DocumentsType.Document getDocument() {
        DocumentsType.Document document = new DocumentsType.Document();
        document.setTipus(TipusDocumentType.RESOLUCIÓ);
        document.setNom("sample.pdf");
        document.setIdFicheroPCI("1234");
        return document;
    }

    private EmissorType buildEmissor() {
        EmissorType emissor = new EmissorType();
        emissor.setCodiDepartament(builder.getProperties().getProperty(PeticionPropertiesBuilder.CODI_ENS));
        emissor.setCodiOrganisme(builder.getProperties().getProperty(PeticionPropertiesBuilder.CODI_ENS));
        return emissor;
    }

    private static UsuariType buildUsuari() {
        UsuariType usuari = new UsuariType();
        usuari.setRol(RolType.EMPLEAT);
        return usuari;
    }
}
