package cat.aoc.client_pci.operations;

import cat.aoc.client_pci.Cluster;
import cat.aoc.client_pci.Entorn;
import cat.aoc.client_pci.Frontal;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.apache.wss4j.dom.WSConstants;
import org.openuri.Procesa;
import org.openuri.ProcesaResponse;
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
public abstract class SOAPRequestSiri<P, R> extends WebServiceGatewaySupport implements SOAPRequestServei<P, R> {
    private final String OPENURI_PACKAGE = "org.openuri:" +
            "net.gencat.scsp.esquemes.peticion:" +
            "net.gencat.scsp.esquemes.respuesta";

    protected Entorn entorn;
    protected Cluster cluster;
    protected Frontal tipus;
    protected final JAXBContext jaxbContext;

    public SOAPRequestSiri(Entorn entorn, Cluster cluster, Frontal tipus, String... externalPackages) throws Exception {
        this.entorn = entorn;
        this.cluster = cluster;
        this.tipus = tipus;
        jaxbContext = JAXBContext.newInstance(OPENURI_PACKAGE + ":" + String.join(":", externalPackages));
        System.out.println(OPENURI_PACKAGE + ":" + String.join(":", externalPackages));
        setInterceptors(new ClientInterceptor[]{
                securityInterceptor(),
        });
    }

    @Override
    public String getEndpoint() {
        if (entorn.equals(Entorn.PRO))
            return "https://serveis3." + cluster.name().toLowerCase() +
                    ".aoc.cat:443/siri-proxy/services/" + tipus.getValue();
        return "https://serveis3-" + entorn.name().toLowerCase() + "." + cluster.name().toLowerCase() +
                ".aoc.cat:443/siri-proxy/services/" + tipus.getValue();
    }

    @Override
    public ProcesaResponse procesa(Procesa procesa) {
        String endpoint = getEndpoint();
        log.debug("Enviando petición: " + endpoint);
        return getWebServiceTemplate().sendAndReceive(
                endpoint,
                request -> {
                    try {
                        requestCallback(procesa, request);
                        System.out.println("procesa");
                        System.out.println(procesa);
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

    @Override
    public Respuesta peticion(Peticion peticion) {
        Procesa procesa = new Procesa();
        procesa.setPeticion(peticion);
        ProcesaResponse response = procesa(procesa);
        return response.getRespuesta();
    }

    protected void requestCallback(Procesa procesa, WebServiceMessage request) throws JAXBException, IOException {
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // pretty print
        jaxbMarshaller.marshal(procesa, request.getPayloadResult());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        request.writeTo(bos);
        System.out.println("Peticion");
        System.out.println(bos);
    }

    protected ProcesaResponse responseExtractor(WebServiceMessage response) throws IOException, JAXBException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        response.writeTo(bos);
        System.out.println("Respuesta");
        System.out.println(bos);
        Source payload = response.getPayloadSource();
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (ProcesaResponse) jaxbUnmarshaller.unmarshal(payload);
    }

    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
        securityInterceptor.setSecurementActions("Signature Timestamp");
        securityInterceptor.setSecurementUsername("segellconsorciaoc");
        securityInterceptor.setSecurementPassword("1234");
        securityInterceptor.setSecurementSignatureCrypto(cryptoFactoryBean().getObject());
        securityInterceptor.setSecurementSignatureKeyIdentifier("DirectReference");
        securityInterceptor.setSecurementSignatureAlgorithm(WSConstants.RSA_SHA1);
        securityInterceptor.setSecurementSignatureDigestAlgorithm(WSConstants.SHA1);
        securityInterceptor.setSecurementTimeToLive(60);
        securityInterceptor.setTimestampPrecisionInMilliseconds(false);
        securityInterceptor.setTimestampStrict(false);
        securityInterceptor.setValidateResponse(false);
        return securityInterceptor;
    }

    private CryptoFactoryBean cryptoFactoryBean() throws Exception {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        Properties properties = new Properties();
        properties.setProperty("org.apache.ws.security.crypto.provider", "org.apache.wss4j.common.crypto.Merlin");
        properties.setProperty("org.apache.wss4j.crypto.merlin.keystore.alias", "segellconsorciaoc");
        properties.setProperty("org.apache.ws.security.crypto.merlin.keystore.type", "PKCS12");
        properties.setProperty("org.apache.ws.security.crypto.merlin.keystore.password", "1234");
        properties.setProperty("org.apache.ws.security.crypto.merlin.keystore.file",
                "C:\\Users\\obernalp\\OneDrive - NTT DATA EMEAL\\code\\work\\aoc\\client-exemple-pci\\src\\main\\resources\\segellconsorciaoc.p12");
        cryptoFactoryBean.setConfiguration(properties);
        cryptoFactoryBean.afterPropertiesSet();
        return cryptoFactoryBean;
    }

}
