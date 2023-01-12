package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.*;
import generated.enotum.*;
import net.gencat.scsp.esquemes.peticion.Fichero;
import net.gencat.scsp.esquemes.peticion.Ficheros;
import net.gencat.scsp.esquemes.peticion.Peticion;

import java.math.BigInteger;

public class ENOTUMClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.enotum",
    };
    private static final String EXAMPLE_PDF = "C:\\Users\\obernalp\\Documents\\PROJECTS\\demo\\src\\main\\resources\\examples\\example.pdf";

    private final PeticionBuilder peticionBuilder;

    public ENOTUMClient(Entorn entorn, PeticionBuilder peticionBuilder) throws WebServiceSupportException {
        super(entorn, Cluster.NT, PACKAGES);
        this.peticionBuilder = peticionBuilder;
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        switch ((ENOTUMOperacio) operacio) {
            case PROCESSAR_TRAMESA:
            case RESUM:
            case EVIDENCIA:
            case PRACTICAR:
            case RECUPERAR_REPORT:
            case CONSULTA:
            case PARAULA_PAS:
            case CERCA:
                return Frontal.SINCRON;
            default:
                throw new NotDefinedException("Operacio no definida: " + operacio);
        }
    }

    @Override
    public String getCodiServei() {
        return "ENOTUM";
    }

    @Override
    protected String getCodiModalitat(Operacio operacio) {
        return "ENOTUM";
    }

    @Override
    public Peticion getPeticion(Operacio operacio, Finalitat finalitat) {
        Peticion peticion = peticionBuilder.build(
                getCodiServei(),
                getCodiModalitat(operacio),
                finalitat.name(),
                getDatosEspecificos(operacio)
        );
        Fichero fichero = new Fichero();
        fichero.setNombreFichero("sample.pdf");
        fichero.setId("1234");
        fichero.setVia("Salida");
        jakarta.activation.DataSource ds = new jakarta.activation.FileDataSource(EXAMPLE_PDF);
        fichero.setContenido(new jakarta.activation.DataHandler(ds));
        Ficheros ficheros = new Ficheros();
        ficheros.getFichero().add(fichero);
        peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos()
                .setFicheros(ficheros);
        return peticion;
    }

    private Object[] getDatosEspecificos(Operacio operacio) {
        switch ((ENOTUMOperacio) operacio) {
            case CERCA:
                return new Object[]{
                        buildPeticioCerca()
                };
            case PROCESSAR_TRAMESA:
                return new Object[]{
                        buildPeticioProcessarTramesa()
                };
            case RESUM:
            case EVIDENCIA:
            case PRACTICAR:
            case RECUPERAR_REPORT:
            case CONSULTA:
            case PARAULA_PAS:
            default:
                return new Object[]{};
        }
    }

    private static PeticioCerca buildPeticioCerca() {
        PeticioCerca.DadesCerca dadesCerca = new PeticioCerca.DadesCerca();

        PeticioCerca.DadesCerca.Paginacio paginacio = new PeticioCerca.DadesCerca.Paginacio();
        paginacio.setNumeroPagina(BigInteger.valueOf(1));
        paginacio.setResultatsPerPagina(25);
        paginacio.setCampOrdenacio("CodiNotificacio");
        paginacio.setSentitOrdenacio(SentitOrdenacioType.DESCENDENT);
        dadesCerca.setPaginacio(paginacio);

        IntervalValors valors = new IntervalValors();
        valors.setValorAbsolut("prova3");
        PeticioCerca.DadesCerca.CriterisNotificacio criteris = new PeticioCerca.DadesCerca.CriterisNotificacio();
        criteris.setReferencia(valors);
        dadesCerca.setCriterisNotificacio(criteris);

        PeticioCerca peticio = new PeticioCerca();
        peticio.setDadesCerca(dadesCerca);
        peticio.setUsuari(buildUsuari());
        peticio.setEmissor(buildEmissor());
        return peticio;
    }

    private static PeticioProcessarTramesa buildPeticioProcessarTramesa() {
        PeticioProcessarTramesa.Tramesa tramesa = new PeticioProcessarTramesa.Tramesa();
        PeticioProcessarTramesa.Tramesa.DadesAvisos dadesAvisos = new PeticioProcessarTramesa.Tramesa.DadesAvisos();
        dadesAvisos.setPlantilla("Universitat Autònoma de Barcelona");
        tramesa.setDadesAvisos(dadesAvisos);
        PeticioProcessarTramesa.Tramesa.DadesOfici dadesOfici = new PeticioProcessarTramesa.Tramesa.DadesOfici();
        dadesOfici.setCosNotificacio("Cos");
        dadesOfici.setPeuRecurs("Contra aquesta resolució, que no esgota la via administrativa, les persones interessades poden interposar recurs d'alçada davant la rectora de la UAB, en el termini d'un mes, a comptar des del dia següent a la recepció d'aquesta notificació o, si s'escau, des del dia següent de la seva publicació, de conformitat amb el que disposen els articles 121 i 122 de la Llei 39/2015, d'1 d'octubre, del procediment administratiu comú de les administracions públiques.");
        tramesa.setDadesOfici(dadesOfici);
        NotificacioType notificacio = new NotificacioType();
        notificacio.setTitol("Notificació de prova");
        notificacio.setReferencia("REF");
        notificacio.setTipusObjecte(TipusObjecteType.NOTIFICACIO);
        notificacio.setTipusAcces(TipusAccesType.PPAS);
        notificacio.setDiesExpiracio(BigInteger.TEN);
        DestinatarisType destinataris = new DestinatarisType();
        DestinatarisType.Destinatari destinatari = new DestinatarisType.Destinatari();
        PersonaFisicaType personaFisica = new PersonaFisicaType();
        BustiesCorreuType bustia = new BustiesCorreuType();
        bustia.getBustiaCorreu().add("XXXX@XXXXXX.com");
        personaFisica.setBustiesCorreu(bustia);
        DocumentPersonaFisicaType documentPersonaFisica = new DocumentPersonaFisicaType();
        documentPersonaFisica.setNIF("12345678Z");
        personaFisica.setDocumentIdentificatiu(documentPersonaFisica);
        personaFisica.setNom("Friedriche");
        personaFisica.setPrimerCognom("Wilhelm");
        personaFisica.setSegonCognom("Nietzsche");
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

    private static EmissorType buildEmissor() {
        EmissorType emissor = new EmissorType();
        emissor.setCodiDepartament("9821920002");
        emissor.setCodiOrganisme("9821920002");
        return emissor;
    }

    private static UsuariType buildUsuari() {
        UsuariType usuari = new UsuariType();
        usuari.setRol(RolType.EMPLEAT);
        return usuari;
    }

}
