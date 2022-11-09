package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.*;
import generated.enotum.*;
import net.gencat.scsp.esquemes.peticion.Peticion;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Operacio;

import java.math.BigInteger;

public class ENOTUMClient extends ClientAOC {
    private static final String CODI_SERVEI = "ENOTUM";
    private static final String CODI_MODALITAT = "ENOTUM";
    private static final String[] PACKAGES = {
            "generated.enotum",
    };

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
        DocumentsType.Document document = new DocumentsType.Document();
        document.setTipus(TipusDocumentType.RESOLUCIÓ);
        document.setNom("sample.pdf");
        document.setDades(getDocumentBytes());
        DocumentsType documentsType = new DocumentsType();
        documentsType.getDocument().add(document);
        tramesa.setDocuments(documentsType);
        PeticioProcessarTramesa peticio = new PeticioProcessarTramesa();
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
        usuari.setRol(RolType.EMPLEAT);
        return usuari;
    }

    private static byte[] getDocumentBytes() {
        return ("%PDF-1.5\n" +
                "%\n" +
                "15 0 obj\n" +
                "<</Linearized 1/L 7201/O 17/E 1807/N 1/T 6898/H [ 453 145]>>\n" +
                "endobj\n" +
                "                     \n" +
                "21 0 obj\n" +
                "<</DecodeParms<</Columns 4/Predictor 12>>/Filter/FlateDecode/ID[<977164624182614496C6E7D3B6745764><DB9E9AB0A759584A8DFC95A9D5AEB099>]/Index[15 11]/Info 14 0 R/Length 51/Prev 6899/Root 16 0 R/Size 26/Type/XRef/W[1 2 1]>>stream\n" +
                "hbbd\u0010``b`r\u0003\u0012\f@\"8\u000B\"*v\u0006&F Y\u0006F\bq??\u0003?\u0007\n" +
                "endstream\n" +
                "endobj\n" +
                "startxref\n" +
                "0\n" +
                "%%EOF\n" +
                "        \n" +
                "25 0 obj\n" +
                "<</C 67/Filter/FlateDecode/I 89/Length 61/S 38>>stream\n" +
                "hb```a``rc?\u0002\u0016\u000B\u0006T\b\u0012dh\u0010@\u0012cb\u0006\f\u001C,\fC\n" +
                "yc!4c\u0007R\u0005\t&@\u0001??\u0004c\n" +
                "endstream\n" +
                "endobj\n" +
                "16 0 obj\n" +
                "<</MarkInfo<</Marked true>>/Metadata 2 0 R/PageLayout/OneColumn/Pages 13 0 R/StructTreeRoot 6 0 R/Type/Catalog>>\n" +
                "endobj\n" +
                "17 0 obj\n" +
                "<</Contents 18 0 R/CropBox[0.0 0.0 595.32 841.92]/MediaBox[0.0 0.0 595.32 841.92]/Parent 13 0 R/Resources<</ColorSpace<</CS0 22 0 R>>/Font<</TT0 24 0 R>>>>/Rotate 0/StructParents 0/Type/Page>>\n" +
                "endobj\n" +
                "18 0 obj\n" +
                "<</Filter/FlateDecode/Length 135>>stream\n" +
                "H,=\u000B0\u0014E+\fDL\u001DJ\u001D\u0014\n" +
                "o\u0013\u0007I\u0011\u0011?\u000E}\u0013v\u001EnNh[7?AC\u0002t\u0016\u0004X\uD9DD\uDF2AC$,\u0012\u0019~»XR\u000441/.}Y^HRmb:'S\u001E\u0002\f? \n" +
                "endstream\n" +
                "endobj\n" +
                "19 0 obj\n" +
                "<</Filter/FlateDecode/First 18/Length 281/N 3/Type/ObjStm>>stream\n" +
                "hPMk0\u0010+9eb\u001E\u0016Am\u000B*\u0005j?FI\"?,\u007F\u001E\u001EL?{3 =\")\u0002T1IbX\b6{u8@j&4r0q 3m#J`!iA:\n" +
                "\u001Dws\b\u001A\n" +
                "\u0016\u0017bJ\u0007_qI{gt`|_\u000BZ2]wr\u0010\u001Ep?u$?!\ffP\\&f[Ozf\u001Bfe???\u0016\\DB;R?8\u000B^@|\u001FS/1\n" +
                "93\u0016Lnt?l??\n" +
                "\u001F\u0001\u0006?4@\n" +
                "endstream\n" +
                "endobj\n" +
                "20 0 obj\n" +
                "<</Filter/FlateDecode/Length 216/N 1>>stream\n" +
                "Hb``$WR\u001E\u0018\u0019\u0011\u0019~\u0001\f\u0012\u000B\u001C\u0003\u0002|@T\u0006\f\u001A\u0003#\u000B2\u000BS\u001E/`M.(*\u0001\u0007(%8\u0019H\u007F\u0001\u00028c\u0002-\n" +
                "f?d\u00049\u0003\u001D@6_Ij\u0005H9(3=DR1%?)U!$5X3/9 ($5\u0005\u0016j\u0007\b\u0017%V*'&*\u0018\u0019r\"?(,!!0b\u0014;\u0010C?2(?\u0001 ?I8/\n" +
                "endstream\n" +
                "endobj\n" +
                "1 0 obj\n" +
                "<</Filter/FlateDecode/First 42/Length 298/N 7/Type/ObjStm>>stream\n" +
                "hlPj\u00021\u0010~lU\u000B`m\u0015wK\u001F?5T0fA-e&t .\u0003\u0014<jg2h?HRoBci`\u000BdCD29\u0002\n" +
                "@See(/\b\\oS?i\\I+r\u0005mY}<\u001A)\u00135\u0005\u001DXxmq\u001Cpd\tX\u001Fo\u001Fj\n" +
                "_Nf\tg1\u000Fz\u0007ckt\u0010\u0015m>?eo\u0013&J?q ?\u0012?\u001C\u001Fup7#]?uct<Z1Lli,@u\u001B?D_w6jv\u0013.7y%??|\n" +
                "endstream\n" +
                "endobj\n" +
                "2 0 obj\n" +
                "<</Length 4184/Subtype/XML/Type/Metadata>>stream\n" +
                "<?xpacket begin=\"\uFEFF\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?>\n" +
                "<x:xmpmeta xmlns:x=\"adobe:ns:meta/\" x:xmptk=\"Adobe XMP Core 4.2.1-c043 52.372728, 2009/01/18-15:08:04        \">\n" +
                "   <rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n" +
                "      <rdf:Description rdf:about=\"\"\n" +
                "            xmlns:xmp=\"http://ns.adobe.com/xap/1.0/\">\n" +
                "         <xmp:ModifyDate>2016-10-03T13:27:48+02:00</xmp:ModifyDate>\n" +
                "         <xmp:CreateDate>2016-10-03T13:27:44+02:00</xmp:CreateDate>\n" +
                "         <xmp:MetadataDate>2016-10-03T13:27:48+02:00</xmp:MetadataDate>\n" +
                "         <xmp:CreatorTool>Acrobat PDFMaker 9.1 para Word</xmp:CreatorTool>\n" +
                "      </rdf:Description>\n" +
                "      <rdf:Description rdf:about=\"\"\n" +
                "            xmlns:xmpMM=\"http://ns.adobe.com/xap/1.0/mm/\">\n" +
                "         <xmpMM:DocumentID>uuid:dc548626-85bb-4b4a-a16d-ed39a4bfcdbb</xmpMM:DocumentID>\n" +
                "         <xmpMM:InstanceID>uuid:56522eed-1c44-4200-bc55-ee7f4fa54834</xmpMM:InstanceID>\n" +
                "         <xmpMM:subject>\n" +
                "            <rdf:Seq>\n" +
                "               <rdf:li>1</rdf:li>\n" +
                "            </rdf:Seq>\n" +
                "         </xmpMM:subject>\n" +
                "      </rdf:Description>\n" +
                "      <rdf:Description rdf:about=\"\"\n" +
                "            xmlns:dc=\"http://purl.org/dc/elements/1.1/\">\n" +
                "         <dc:format>application/pdf</dc:format>\n" +
                "         <dc:title>\n" +
                "            <rdf:Alt>\n" +
                "               <rdf:li xml:lang=\"x-default\"/>\n" +
                "            </rdf:Alt>\n" +
                "         </dc:title>\n" +
                "         <dc:description>\n" +
                "            <rdf:Alt>\n" +
                "               <rdf:li xml:lang=\"x-default\"/>\n" +
                "            </rdf:Alt>\n" +
                "         </dc:description>\n" +
                "         <dc:creator>\n" +
                "            <rdf:Seq>\n" +
                "               <rdf:li/>\n" +
                "            </rdf:Seq>\n" +
                "         </dc:creator>\n" +
                "      </rdf:Description>\n" +
                "      <rdf:Description rdf:about=\"\"\n" +
                "            xmlns:pdf=\"http://ns.adobe.com/pdf/1.3/\">\n" +
                "         <pdf:Producer>Adobe PDF Library 9.0</pdf:Producer>\n" +
                "         <pdf:Keywords/>\n" +
                "      </rdf:Description>\n" +
                "      <rdf:Description rdf:about=\"\"\n" +
                "            xmlns:pdfx=\"http://ns.adobe.com/pdfx/1.3/\">\n" +
                "         <pdfx:SourceModified>D:20160725063944</pdfx:SourceModified>\n" +
                "         <pdfx:Company>Hewlett-Packard Company</pdfx:Company>\n" +
                "         <pdfx:Comments/>\n" +
                "      </rdf:Description>\n" +
                "   </rdf:RDF>\n" +
                "</x:xmpmeta>\n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                           \n" +
                "<?xpacket end=\"w\"?>\n" +
                "endstream\n" +
                "endobj\n" +
                "3 0 obj\n" +
                "<</Filter/FlateDecode/First 5/Length 50/N 1/Type/ObjStm>>stream\n" +
                "h24V0Pw/+Q0L)64\u0007\n" +
                "\u0006T\u0016\u0007$\u0016\u0001\u0004\u0018?k\u000B\n" +
                "endstream\n" +
                "endobj\n" +
                "4 0 obj\n" +
                "<</Filter/FlateDecode/First 5/Length 204/N 1/Type/ObjStm>>stream\n" +
                "hlAk@\u0010\u0005\u0FF27\u0013f6j-\"HC)\u0010dwJjF\u0013$?z{<\u001E?\u0380YM_,Y^L\u000BvCF\u0013N\u001BG`>\n" +
                "P#w5*es\tvn\u0001*[\u000B\u001E?LU7^E5M#Y?`\tyy?lC)[f\u0017[A\u0019\n" +
                "yq^<%&~F\n" +
                "\u0003\u0011?4o?(?\u0003?\tPZ\n" +
                "endstream\n" +
                "endobj\n" +
                "5 0 obj\n" +
                "<</DecodeParms<</Columns 4/Predictor 12>>/Filter/FlateDecode/ID[<977164624182614496C6E7D3B6745764><DB9E9AB0A759584A8DFC95A9D5AEB099>]/Info 14 0 R/Length 48/Root 16 0 R/Size 15/Type/XRef/W[1 2 1]>>stream\n" +
                "hbb?\u0002&Fv~\u0006&\u0006\u000E !\u0014H0\uDBC7\uDE1A@g| .\u0003#>\u0017`d?\b0?\u0004%\n" +
                "endstream\n" +
                "endobj\n" +
                "startxref\n" +
                "116\n" +
                "%%EOF\n").getBytes();
    }

}
