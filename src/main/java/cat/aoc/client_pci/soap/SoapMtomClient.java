package cat.aoc.client_pci.soap;

import cat.aoc.client_pci.model.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Slf4j
public abstract class SoapMtomClient<P, R> extends WebServiceGatewaySupport {

    private static String processPackages(String... packages) {
        return String.join(":", packages);
    }

    protected SoapMtomClient(String keystorePath, String... packages) {
        this(packages);
        try {
            setInterceptors(new SignatureInterceptor[]{
                    new SignatureInterceptor(keystorePath),
            });
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    protected SoapMtomClient(String... packages) {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths(processPackages(packages));
        marshaller.setMtomEnabled(true);
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    protected R send(String endpoint, P procesa) {
        log.debug("Enviando petición: " + endpoint);
        return (R) getWebServiceTemplate().marshalSendAndReceive(endpoint, procesa);
    }

}
