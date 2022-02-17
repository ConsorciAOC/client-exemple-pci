package cat.aoc.client_pci.soap;

import cat.aoc.client_pci.exceptions.NotFoundException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.wss4j.common.WSS4JConstants;
import org.apache.wss4j.common.crypto.Crypto;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Slf4j
@Getter
public class SignatureInterceptor extends Wss4jSecurityInterceptor {

    private Properties properties;

    public SignatureInterceptor(String propertiesPath) throws NotFoundException {
        load(propertiesPath);
        createSecurityInterceptor(properties);
    }

    private void load(String propertiesPath) throws NotFoundException {
        try (InputStream input = new FileInputStream(propertiesPath)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            throw new NotFoundException("Fitxer .properties no trobat: " + propertiesPath);
        }
    }

    public void createSecurityInterceptor(Properties properties) throws NotFoundException {
        setSecurementActions("Signature Timestamp");
        setSecurementUsername("segellconsorciaoc");
        setSecurementPassword("1234");
        setSecurementSignatureCrypto(createCrypto(properties));
        setSecurementSignatureKeyIdentifier("DirectReference");
        setSecurementSignatureAlgorithm(WSS4JConstants.RSA_SHA1);
        setSecurementSignatureDigestAlgorithm(WSS4JConstants.SHA1);
        setSecurementTimeToLive(60);
        setTimestampPrecisionInMilliseconds(false);
        setTimestampStrict(false);
        setValidateResponse(false);
    }

    private Crypto createCrypto(Properties properties) throws NotFoundException {
        try {
            CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
            cryptoFactoryBean.setConfiguration(properties);
            cryptoFactoryBean.afterPropertiesSet();
            return cryptoFactoryBean.getObject();
        } catch (Exception e) {
            throw new NotFoundException("No s'han pogut carregar el magatzem");
        }
    }

}
