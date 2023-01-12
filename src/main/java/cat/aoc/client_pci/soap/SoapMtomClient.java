package cat.aoc.client_pci.soap;

import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Slf4j
@Getter
public class SoapMtomClient<P, R> extends WebServiceGatewaySupport {

    private static String processPackages(String... packages) {
            return String.join(":", packages);
    }

    protected SoapMtomClient(String... packages) throws WebServiceSupportException {
        try {
            Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
            marshaller.setContextPaths(processPackages(packages));
            marshaller.setMtomEnabled(true);
            this.setMarshaller(marshaller);
            this.setUnmarshaller(marshaller);
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
        return (R) getWebServiceTemplate().marshalSendAndReceive(endpoint, procesa);
    }

}
