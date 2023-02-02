package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.utils.PeticionBuilderImpl;
import generated.enotum.*;
import net.gencat.scsp.esquemes.peticion.Fichero;
import net.gencat.scsp.esquemes.peticion.Ficheros;
import net.gencat.scsp.esquemes.peticion.Peticion;

import java.math.BigInteger;

public class ENOTUMPeticionBuilder extends PeticionBuilderImpl {
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

    public ENOTUMPeticionBuilder(String propertiesPath) throws NotFoundException {
        super(propertiesPath);
    }

    @Override
    public Peticion build(String producte, Operacio operacio, Finalitat finalitat) {
        Peticion peticion = super.build(producte, operacio, finalitat);
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
    public Object[] getDatosEspecificos(Operacio operacio) throws NotDefinedException {
        try {
            return switch ((ENOTUMOperacio) operacio) {
                case CERCA -> new Object[]{
                        buildPeticioCerca()
                };
                case PROCESSAR_TRAMESA -> new Object[]{
                        buildPeticioProcessarTramesa()
                };
                case RESUM, EVIDENCIA, PRACTICAR, RECUPERAR_REPORT, CONSULTA, PARAULA_PAS -> new Object[]{};
            };
        } catch (Exception e) {
            throw new NotDefinedException("Operacio no definida: " + operacio);
        }
    }

    private PeticioCerca buildPeticioCerca() {
        PeticioCerca.DadesCerca dadesCerca = new PeticioCerca.DadesCerca();

        IntervalValors valors = new IntervalValors();
        valors.setValorAbsolut(properties.getProperty(REFERENCIA));
        PeticioCerca.DadesCerca.CriterisNotificacio criteris = new PeticioCerca.DadesCerca.CriterisNotificacio();
        criteris.setReferencia(valors);
        dadesCerca.setCriterisNotificacio(criteris);

        PeticioCerca peticio = new PeticioCerca();
        peticio.setDadesCerca(dadesCerca);
        peticio.setUsuari(buildUsuari());
        peticio.setEmissor(buildEmissor());
        return peticio;
    }

    private PeticioProcessarTramesa buildPeticioProcessarTramesa() {
        PeticioProcessarTramesa.Tramesa tramesa = new PeticioProcessarTramesa.Tramesa();
        PeticioProcessarTramesa.Tramesa.DadesAvisos dadesAvisos = new PeticioProcessarTramesa.Tramesa.DadesAvisos();
        dadesAvisos.setPlantilla(properties.getProperty(PLANTILLA));
        tramesa.setDadesAvisos(dadesAvisos);
        PeticioProcessarTramesa.Tramesa.DadesOfici dadesOfici = new PeticioProcessarTramesa.Tramesa.DadesOfici();
        dadesOfici.setCosNotificacio(properties.getProperty(COS_NOTIFICACIO));
        dadesOfici.setPeuRecurs(properties.getProperty(PEU_RECURS));
        tramesa.setDadesOfici(dadesOfici);
        NotificacioType notificacio = new NotificacioType();
        notificacio.setTitol(properties.getProperty(TITOL));
        notificacio.setReferencia(properties.getProperty(REFERENCIA));
        notificacio.setTipusObjecte(TipusObjecteType.NOTIFICACIO);
        notificacio.setTipusAcces(TipusAccesType.PPAS);
        notificacio.setDiesExpiracio(BigInteger.TEN);
        DestinatarisType destinataris = new DestinatarisType();
        DestinatarisType.Destinatari destinatari = new DestinatarisType.Destinatari();
        PersonaFisicaType personaFisica = new PersonaFisicaType();
        BustiesCorreuType bustia = new BustiesCorreuType();
        bustia.getBustiaCorreu().add(properties.getProperty(BUSTIA));
        personaFisica.setBustiesCorreu(bustia);
        DocumentPersonaFisicaType documentPersonaFisica = new DocumentPersonaFisicaType();
        documentPersonaFisica.setNIF(properties.getProperty(NIF));
        personaFisica.setDocumentIdentificatiu(documentPersonaFisica);
        personaFisica.setNom(properties.getProperty(NOM));
        personaFisica.setPrimerCognom(properties.getProperty(PRIMER_COGNOM));
        personaFisica.setSegonCognom(properties.getProperty(SEGON_COGNOM));
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
