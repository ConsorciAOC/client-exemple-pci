package cat.aoc.client_pci.api.soap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Slf4j
public class SoapMtomClient<P, R> extends WebServiceGatewaySupport {

    private static String processPackages(String... packages) {
        return String.join(":", packages);
    }

    public SoapMtomClient(boolean useMtom, String... packages) {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths(processPackages(packages));
        marshaller.setMtomEnabled(useMtom);
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    @SuppressWarnings("unchecked")
    public R send(String endpoint, P procesa) {
        log.debug("Enviando petición: " + endpoint);
        WebServiceTemplate template = getWebServiceTemplate();
        return (R) template.marshalSendAndReceive(endpoint, procesa);
    }

}
