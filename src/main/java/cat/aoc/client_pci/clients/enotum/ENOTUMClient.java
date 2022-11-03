package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.*;
import net.gencat.scsp.esquemes.peticion.Peticion;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Operacio;
import net.aocat.nt.v3.*;

import java.math.BigInteger;

public class ENOTUMClient extends ClientAOC {
    private static final String CODI_SERVEI = "ENOTUM";
    private static final String CODI_MODALITAT = "ENOTUM";
    private static final String[] PACKAGES = {
            "net.aocat.nt.v3"
    };

    private final PeticionBuilder peticionBuilder;

    public ENOTUMClient(Entorn entorn, PeticionBuilder peticionBuilder) throws WebServiceSupportException {
        super(entorn, Cluster.NT, PACKAGES);
        this.peticionBuilder = peticionBuilder;
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        switch ((ENOTUMOperacio) operacio) {
            case PeticioProcessarTramesa:
            case PeticioResum:
            case PeticioEvidencia:
            case PeticioPracticar:
            case PeticioRecuperarReport:
            case PeticioConsulta:
            case PeticioParaulaPas:
            case PeticioCerca:
                return Frontal.SINCRON;
            default:
                throw new NotDefinedException("Operacio no definida: " + operacio);
        }
    }

    @Override
    public Peticion getPeticion(Operacio operacio, Finalitat finalitat) {
        return peticionBuilder.build(
                CODI_SERVEI,
                CODI_MODALITAT,
                finalitat.name(),
                getDatosEspecificos(operacio)
        );
    }

    private Object[] getDatosEspecificos(Operacio operacio) {
        switch ((ENOTUMOperacio) operacio) {
            case PeticioCerca:
                return new Object[]{
                        buildPeticioCerca()
                };
            case PeticioProcessarTramesa:
                return new Object[]{
                        buildPeticioProcessarTramesa()
                };
            case PeticioResum:
            case PeticioEvidencia:
            case PeticioPracticar:
            case PeticioRecuperarReport:
            case PeticioConsulta:
            case PeticioParaulaPas:
            default:
                return new Object[]{};
        }
    }

    private static PeticioCercaType buildPeticioCerca() {
        PeticioCercaType.DadesCerca dadesCerca = new PeticioCercaType.DadesCerca();

        PeticioCercaType.DadesCerca.Paginacio paginacio = new PeticioCercaType.DadesCerca.Paginacio();
        paginacio.setNumeroPagina(BigInteger.valueOf(1));
        paginacio.setResultatsPerPagina(25);
        paginacio.setCampOrdenacio("CodiNotificacio");
        paginacio.setSentitOrdenacio("Descendent");
        dadesCerca.setPaginacio(paginacio);

        IntervalValors valors = new IntervalValors();
        valors.setValorAbsolut("prova3");
        PeticioCercaType.DadesCerca.CriterisNotificacio criteris = new PeticioCercaType.DadesCerca.CriterisNotificacio();
        criteris.setReferencia(valors);
        dadesCerca.setCriterisNotificacio(criteris);

        PeticioCercaType peticio = new PeticioCercaType();
        peticio.setDadesCerca(dadesCerca);
        peticio.setUsuari(buildUsuari());
        peticio.setEmissor(buildEmissor());
        return peticio;
    }

    private static PeticioProcessarTramesaType buildPeticioProcessarTramesa() {
        PeticioProcessarTramesaType.Tramesa tramesa = new PeticioProcessarTramesaType.Tramesa();
        PeticioProcessarTramesaType.Tramesa.DadesAvisos dadesAvisos = new PeticioProcessarTramesaType.Tramesa.DadesAvisos();
        dadesAvisos.setPlantilla("Universitat Autònoma de Barcelona");
        tramesa.setDadesAvisos(dadesAvisos);
        PeticioProcessarTramesaType.Tramesa.DadesOfici dadesOfici = new PeticioProcessarTramesaType.Tramesa.DadesOfici();
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
        DocumentPersonaFisicaType document = new DocumentPersonaFisicaType();
        document.setNIF("12345678Z");
        personaFisica.setDocumentIdentificatiu(document);
        personaFisica.setNom("Friedriche");
        personaFisica.setPrimerCognom("Wilhelm");
        personaFisica.setSegonCognom("Nietzsche");
        destinatari.setPersonaFisica(personaFisica);
        destinatari.setIdioma(Idioma.CA);
        destinataris.getDestinatari().add(destinatari);
        notificacio.setDestinataris(destinataris);
        tramesa.getNotificacio().add(notificacio);
        PeticioProcessarTramesaType peticio = new PeticioProcessarTramesaType();
        peticio.setTramesa(tramesa);
        peticio.setUsuari(buildUsuari());
        peticio.setEmissor(buildEmissor());
        return peticio;
    }

    private static EmissorType buildEmissor() {
        EmissorType emissor = new EmissorType();
        emissor.setCodiDepartament("9821920002");
        emissor.setCodiOrganisme("9821920002");
        return emissor;
    }

    private static UsuariType buildUsuari() {
        UsuariType usuari = new UsuariType();
        usuari.setRol("EMPLEAT");
        return usuari;
    }
}
