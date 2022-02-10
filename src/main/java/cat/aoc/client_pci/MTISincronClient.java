package cat.aoc.client_pci;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.openuri.Procesa;
import org.openuri.ProcesaResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.ws.FaultAwareWebServiceMessage;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.SoapFaultException;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

import javax.xml.transform.Source;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class MTISincronClient extends WebServiceGatewaySupport implements PCI3ClientI {

    private final JAXBContext jaxbContext;

    public MTISincronClient() throws Exception {
        jaxbContext = JAXBContext.newInstance("org.openuri:net.gencat.scsp.esquemes.peticion:net.gencat.scsp.esquemes.respuesta:cat.aoc.tfn");
        ClientInterceptor[] interceptors = new ClientInterceptor[]{
                securityInterceptor(),
        };
        setInterceptors(interceptors);
    }

    @Override
    public String getEndpoint(Cluster cluster, TipusPeticioMti tipus) {
        return "https://serveis3-pre." + cluster.name().toLowerCase() + ".aoc.cat:443/siri-proxy/services/" + tipus.getValue();
    }

    @Override
    public ProcesaResponse procesa(Cluster cluster, Procesa procesa) {
        String endpoint = getEndpoint(cluster, TipusPeticioMti.SINCRON);
        log.debug("Enviando petición: " + endpoint);
        return getWebServiceTemplate().sendAndReceive(
                endpoint,
                request -> {
                    log.debug("Convirtiendo la petición a XML para enviarla...");
                    try {
                        marshalProcesa(procesa, request);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                    log.debug("\t hecho.");
                },
                response -> {
                    if (response instanceof FaultAwareWebServiceMessage) {
                        FaultAwareWebServiceMessage faultMessage = (FaultAwareWebServiceMessage) response;
                        if (faultMessage.hasFault()) {
                            throw new SoapFaultException(faultMessage.getFaultReason());
                        }
                    }
                    log.debug("Convirtiendo la respuesta XML recibida a objeto...");
                    ProcesaResponse unmarshal = null;
                    try {
                        unmarshal = unmarshalProcesa(response);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                    log.debug("\t hecho.");
                    return unmarshal;
                }

        );
    }

    private void marshalProcesa(Procesa procesa, WebServiceMessage message) throws JAXBException, IOException {
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // pretty print
        jaxbMarshaller.marshal(procesa, message.getPayloadResult());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        message.writeTo(bos);
        System.out.println("Peticion");
        System.out.println(bos);
    }

    private ProcesaResponse unmarshalProcesa(WebServiceMessage message) throws JAXBException, IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        message.writeTo(bos);
        System.out.println("Respuesta");
        System.out.println(bos);
        Source payload = message.getPayloadSource();
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (ProcesaResponse) jaxbUnmarshaller.unmarshal(payload);
    }

    public Wss4jSecurityInterceptor wsResponseSignatureInterceptor() throws Exception {
        System.out.println("INTERCEPTING!");
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStorePassword("caoc");
        cryptoFactoryBean.setKeyStoreLocation(new FileSystemResource("C:\\Users\\obernalp\\OneDrive - NTT DATA EMEAL\\code\\work\\aoc\\client-exemple-pci\\src\\main\\resources\\truststore-SIRI-PRE.jks"));
        cryptoFactoryBean.afterPropertiesSet();
        Crypto signatureCrypto = cryptoFactoryBean.getObject();

        Wss4jSecurityInterceptor wss4jSecurityInterceptor = new Wss4jSecurityInterceptor();
        wss4jSecurityInterceptor.setValidateRequest(false);
        wss4jSecurityInterceptor.setValidateResponse(true);
        wss4jSecurityInterceptor.setValidationActions(WSHandlerConstants.TIMESTAMP + " " + WSHandlerConstants.SIGNATURE);
        wss4jSecurityInterceptor.setValidationSignatureCrypto(signatureCrypto);
        return wss4jSecurityInterceptor;
    }

    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();

        // set security actions on request
        securityInterceptor.setSecurementActions("Signature Timestamp");
        // sign the request
        securityInterceptor.setSecurementUsername("segellconsorciaoc");
        securityInterceptor.setSecurementPassword("1234");
        Crypto object = cryptoFactoryBean().getObject();
        securityInterceptor.setSecurementSignatureCrypto(object);
        securityInterceptor.setSecurementSignatureKeyIdentifier("DirectReference");
        //securityInterceptor.setValidationActions("NoSecurity");
        securityInterceptor.setSecurementSignatureAlgorithm(WSConstants.RSA_SHA1);
        securityInterceptor.setSecurementSignatureDigestAlgorithm(WSConstants.SHA1);
        securityInterceptor.setSecurementTimeToLive(60);
        securityInterceptor.setTimestampPrecisionInMilliseconds(false);
        securityInterceptor.setTimestampStrict(false);

        // no validation for the response
        securityInterceptor.setValidateResponse(false);
        return securityInterceptor;
    }

    public CryptoFactoryBean getCryptoFactoryBean() throws IOException {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStorePassword("1234");
        //.setKeyStoreLocation(new FileSystemResource("C:/Users/obernalp/OneDrive - NTT DATA EMEAL/code/work/aoc/client-exemple-pci/src/main/resources/segellconsorciaoc.jks"));
        cryptoFactoryBean.setKeyStoreLocation(new FileSystemResource("C:\\Users\\obernalp\\OneDrive - NTT DATA EMEAL\\code\\work\\aoc\\client-exemple-pci\\src\\main\\resources\\segellconsorciaoc.p12"));
        return cryptoFactoryBean;
    }
    public CryptoFactoryBean cryptoFactoryBean() throws IOException {

        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        Properties properties = new Properties();
        properties.setProperty("org.apache.ws.security.crypto.provider", "org.apache.wss4j.common.crypto.Merlin");
        properties.setProperty("org.apache.wss4j.crypto.merlin.keystore.alias", "segellconsorciaoc");
        properties.setProperty("org.apache.ws.security.crypto.merlin.keystore.type", "PKCS12");
        properties.setProperty("org.apache.ws.security.crypto.merlin.keystore.password", "1234");
        properties.setProperty("org.apache.ws.security.crypto.merlin.keystore.file",
                "C:\\Users\\obernalp\\OneDrive - NTT DATA EMEAL\\code\\work\\aoc\\client-exemple-pci\\src\\main\\resources\\segellconsorciaoc.p12");
        cryptoFactoryBean.setConfiguration(properties);

        try {
            cryptoFactoryBean.afterPropertiesSet();
        } catch (Exception e) {
            System.out.println("ERRRRRRROROROROROROR");
            e.printStackTrace();
        }
        return cryptoFactoryBean;
    }

}