package cat.aoc.client_pci;

import cat.aoc.client_pci.jaxb.pci.Peticion;
import cat.aoc.client_pci.jaxb.tfn.PeticioDadesCompletes;
import cat.aoc.client_pci.tfn.DatosEspecificosTFN;
import cat.aoc.client_pci.tfn.TFNClient;
import jakarta.xml.bind.*;

import javax.xml.namespace.QName;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) throws JAXBException {
        //tryWithDatosEspecificosNull();
        //tryWithCustomDatosEspecificos();
        //tryWithMarshalledString();
        tryWithJaxbElements();
    }

    /** Si no tenim datos especificos funciona OK **/
    public static void tryWithDatosEspecificosNull() throws JAXBException {
        TFNClient tfnClient = new TFNClient();
        Peticion peticionTFN = tfnClient.buildPeticion();
        tfnClient.setDatosEspecificos(peticionTFN, null);

        // MARSHALLING
        String xml = marshalPeticion(peticionTFN);
        System.out.println(xml);

        // UNMARSHALLING
        Peticion peticion = unmarshalPeticion(xml);
        System.out.println(peticion.getAtributos().getIdPeticion());
    }

    /** Si tenim un objecte de datos especificos no funiona KO **/
    public static void tryWithCustomDatosEspecificos() throws JAXBException {
        TFNClient tfnClient = new TFNClient();
        Peticion peticionTFN = tfnClient.buildPeticion();
        //tfnClient.setDatosEspecificos(peticionTFN, null);

        // MARSHALLING
        String xml = marshalPeticion(peticionTFN);
        System.out.println(xml);

        // UNMARSHALLING
        Peticion peticion = unmarshalPeticion(xml);
        System.out.println(peticion.getAtributos().getIdPeticion());
    }

    /** Si passem els datos especificos com un String XML, no falla però l'XML no està bé **/
    public static void tryWithMarshalledString() throws JAXBException {
        TFNClient tfnClient = new TFNClient();
        Peticion peticionTFN = tfnClient.buildPeticion();

        // Convert datos especificos to String
        JAXBContext jaxbContext = JAXBContext.newInstance(DatosEspecificosTFN.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // pretty print
        StringWriter stringWriter = new StringWriter();
        jaxbMarshaller.marshal(peticionTFN.getSolicitudes().getSolicitudTransmision().get(0).getDatosEspecificos(), stringWriter);

        tfnClient.setDatosEspecificos(peticionTFN, stringWriter.toString());

        // MARSHALLING
        String xml = marshalPeticion(peticionTFN);
        System.out.println(xml);

        // UNMARSHALLING
        Peticion peticion = unmarshalPeticion(xml);
        System.out.println(peticion.getAtributos().getIdPeticion());
    }

    /** Jugant amb JAXBElement tampoc m'en surto :( **/
    public static void tryWithJaxbElements() throws JAXBException {
        TFNClient tfnClient = new TFNClient();
        Peticion peticionTFN = tfnClient.buildPeticion();

        QName fooQName = new QName("http://www.aoc.cat/tfn", "datosEspecificos");
        JAXBElement<DatosEspecificosTFN> datosEspecificos = new JAXBElement<>(
                fooQName, DatosEspecificosTFN.class,
                (DatosEspecificosTFN) peticionTFN.getSolicitudes().getSolicitudTransmision().get(0).getDatosEspecificos()
        );
        tfnClient.setDatosEspecificos(peticionTFN, datosEspecificos);

        // MARSHALLING
        String xml = marshalPeticion(peticionTFN);
        System.out.println(xml);

        // UNMARSHALLING
        Peticion peticion = unmarshalPeticion(xml);
        System.out.println(peticion.getAtributos().getIdPeticion());
    }

    private static String marshalPeticion(Peticion peticion) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Peticion.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // pretty print
        StringWriter stringWriter = new StringWriter();
        jaxbMarshaller.marshal(peticion, stringWriter);
        return stringWriter.toString();
    }

    private static Peticion unmarshalPeticion(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Peticion.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Peticion) jaxbUnmarshaller.unmarshal(new StringReader(xml));
    }

}
