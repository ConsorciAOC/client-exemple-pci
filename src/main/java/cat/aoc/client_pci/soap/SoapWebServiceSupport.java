package cat.aoc.client_pci.soap;

import cat.aoc.client_pci.exceptions.MarshallingException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.transform.Source;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Getter
public class SoapWebServiceSupport<P, R> extends WebServiceGatewaySupport {

    private static String processPackages(String... packages) {
            return String.join(":", packages);
    }

    protected final JAXBContext jaxbContext;

    protected SoapWebServiceSupport(String... packages) throws WebServiceSupportException {
        try {
            jaxbContext = JAXBContext.newInstance(processPackages(packages));
            setInterceptors(new SignatureInterceptor[]{
                    new SignatureInterceptor("src\\main\\resources\\keystore.properties"),
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebServiceSupportException("Error en configurar el client i el signador");
        }
    }

    protected R send(String endpoint, P procesa) {
        log.debug("Enviando petición: " + endpoint);
        return getWebServiceTemplate().sendAndReceive(
                endpoint,
                request -> {
                    try {
                        requestCallback(procesa, request);
                    } catch (MarshallingException e) {
                        e.printStackTrace();
                    }
                },
                response -> {
                    try {
                        return responseExtractor(response);
                    } catch (MarshallingException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        );
    }

    protected void requestCallback(P procesa, WebServiceMessage request) throws MarshallingException {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // pretty print
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.ISO_8859_1.name());
            jaxbMarshaller.marshal(procesa, request.getPayloadResult());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            request.writeTo(bos);
            log.info("Peticion");
            log.info(String.valueOf(bos));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new MarshallingException("S'ha produit un error al crear el marshaller");
        } catch (IOException e) {
            throw new MarshallingException("S'ha produit un error convertir la petició a XML");
        }
    }

    protected R responseExtractor(WebServiceMessage response) throws MarshallingException {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            response.writeTo(bos);
            log.info("Respuesta");
            log.info(String.valueOf(bos));
            Source payload = response.getPayloadSource();
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (R) jaxbUnmarshaller.unmarshal(payload);
        } catch (JAXBException e) {
            throw new MarshallingException("S'ha produit un error al crear el unmarshaller");
        } catch (IOException e) {
            throw new MarshallingException("S'ha produit un error convertir la resposta a partir de l'XML");
        }
    }

}
