package cat.aoc.client_pci.samples.serveis.enotum;

import generated.serveis.enotum.*;

import java.math.BigInteger;

interface PeticionBuilderEnotumProcessarTramesa {

    static PeticioProcessarTramesa buildPeticioProcessarTramesa(EmissorType emissor, UsuariType usuari) {
        PeticioProcessarTramesa peticio = new PeticioProcessarTramesa();
        peticio.setTramesa(getTramesa());
        peticio.setEmissor(emissor);
        peticio.setUsuari(usuari);
        return peticio;
    }

    private static PeticioProcessarTramesa.Tramesa getTramesa() {
        PeticioProcessarTramesa.Tramesa tramesa = new PeticioProcessarTramesa.Tramesa();
        tramesa.setDadesAvisos(getDadesAvisos());
        tramesa.setDadesOfici(getDadesOfici());
        tramesa.getNotificacio().add(getNotificacio());
        tramesa.setDocuments(getDocument());
        return tramesa;
    }

    private static PeticioProcessarTramesa.Tramesa.DadesAvisos getDadesAvisos() {
        PeticioProcessarTramesa.Tramesa.DadesAvisos dadesAvisos = new PeticioProcessarTramesa.Tramesa.DadesAvisos();
        dadesAvisos.setPlantilla("Universitat Aut�noma de Barcelona");
        return dadesAvisos;
    }

    private static PeticioProcessarTramesa.Tramesa.DadesOfici getDadesOfici() {
        PeticioProcessarTramesa.Tramesa.DadesOfici dadesOfici = new PeticioProcessarTramesa.Tramesa.DadesOfici();
        dadesOfici.setCosNotificacio("Cos");
        dadesOfici.setPeuRecurs("Peu");
        return dadesOfici;
    }

    private static NotificacioType getNotificacio() {
        NotificacioType notificacio = new NotificacioType();
        notificacio.setTitol("Titol");
        notificacio.setReferencia("Ref");
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
        return notificacio;
    }

    private static DocumentsType getDocument() {
        DocumentsType documentsType = new DocumentsType();
        DocumentsType.Document document = new DocumentsType.Document();
        document.setTipus(TipusDocumentType.RESOLUCIÓ);
        document.setNom("sample.pdf");
        document.setIdFicheroPCI("1234");
        documentsType.getDocument().add(document);
        return documentsType;
    }

}
