package cat.aoc.client_pci.soap;

import org.springframework.oxm.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.openuri.Procesa;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class LoggerInterceptor implements ClientInterceptor {

    private final Unmarshaller unmarshaller;
    public LoggerInterceptor(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        this.log("SENT INFO", () -> {
            try {
                Procesa procesa = (Procesa) unmarshaller.unmarshal(messageContext.getRequest().getPayloadSource());
                log.info("ID: " + procesa.getPeticion().getAtributos().getIdPeticion());
                log.info("Timestamp: " + procesa.getPeticion().getAtributos().getTimeStamp());
                log.info("Producte: " + procesa.getPeticion().getAtributos().getCodigoProducto());
                log.info("Modalitat: " + procesa.getPeticion().getAtributos().getCodigoCertificado());
                log.info("Finalitat: " + procesa.getPeticion().getAtributos().getDatosAutorizacion().getFinalidad());
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                messageContext.getRequest().writeTo(buffer);
                String payload = buffer.toString(StandardCharsets.UTF_8);
                log.info("Request:");
                log.info(payload);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        this.log("RECEIVED INFO", () -> {
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                messageContext.getResponse().writeTo(buffer);
                String payload = buffer.toString(StandardCharsets.UTF_8);
                log.info("Response:");
                log.info(payload);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        this.log("RECEIVED FAULT", () -> {
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                messageContext.getResponse().writeTo(buffer);
                String payload = buffer.toString(StandardCharsets.UTF_8);
                log.info("Fault:");
                log.info(payload);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
        log.debug("Communication completed!");
    }

    public void log(String message, Runnable runnable) throws WebServiceClientException {
        log.info("#################################### " + message + " ####################################");
        runnable.run();
        log.info("###################################################################################");
    }

}
