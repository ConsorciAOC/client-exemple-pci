package cat.aoc.client_pci;

import cat.aoc.client_pci.jaxb.pci.Peticion;
import cat.aoc.client_pci.jaxb.tfn.PeticioDadesCompletes;
import cat.aoc.client_pci.tfn.DatosEspecificosTFN;
import cat.aoc.client_pci.tfn.TFNClient;
import jakarta.xml.bind.*;

import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) throws JAXBException {
        TFNClient tfnClient = new TFNClient();
        Peticion peticionTFN = tfnClient.buildPeticion();

        // MARSHALLING
        String xml = marshalPeticion(peticionTFN);
        System.out.println(xml);

        // UNMARSHALLING
        Peticion peticion = unmarshalPeticion(xml);
        System.out.println(peticion.getAtributos().getIdPeticion());

    }

    private static String marshalPeticion(Peticion peticion) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Peticion.class, DatosEspecificosTFN.class, PeticioDadesCompletes.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // pretty print
        StringWriter stringWriter = new StringWriter();
        jaxbMarshaller.marshal(peticion, stringWriter);
        return stringWriter.toString();
    }

    private static Peticion unmarshalPeticion(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Peticion.class, DatosEspecificosTFN.class, PeticioDadesCompletes.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Peticion) jaxbUnmarshaller.unmarshal(new StringReader(xml));
    }

}
