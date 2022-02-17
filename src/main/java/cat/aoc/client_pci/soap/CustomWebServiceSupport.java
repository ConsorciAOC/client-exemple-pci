package cat.aoc.client_pci.soap;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.wss4j.dom.WSConstants;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

import javax.xml.transform.Source;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
@Getter
public class CustomWebServiceSupport<P, R> extends WebServiceGatewaySupport {

    private final String OPENURI_PACKAGE = "org.openuri:" +
            "net.gencat.scsp.esquemes.peticion:" +
            "net.gencat.scsp.esquemes.respuesta";

    protected final JAXBContext jaxbContext;
    protected CustomWebServiceSupport(String... externalPackages) throws Exception {
        jaxbContext = JAXBContext.newInstance(OPENURI_PACKAGE + ":" + String.join(":", externalPackages));
        setInterceptors(new SignatureInterceptor[]{
                new SignatureInterceptor("src\\main\\resources\\keystore.properties"),
        });

    }

    protected R send(String endpoint, P procesa) {
        log.debug("Enviando petición: " + endpoint);
        return getWebServiceTemplate().sendAndReceive(
                endpoint,
                request -> {
                    try {
                        requestCallback(procesa, request);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                },
                response -> {
                    try {
                        return responseExtractor(response);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        );
    }

    protected void requestCallback(P procesa, WebServiceMessage request) throws JAXBException, IOException {
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // pretty print
        jaxbMarshaller.marshal(procesa, request.getPayloadResult());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        request.writeTo(bos);
        System.out.println("Peticion");
        System.out.println(bos);
    }

    protected R responseExtractor(WebServiceMessage response) throws IOException, JAXBException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        response.writeTo(bos);
        System.out.println("Respuesta");
        System.out.println(bos);
        Source payload = response.getPayloadSource();
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (R) jaxbUnmarshaller.unmarshal(payload);
    }

}
