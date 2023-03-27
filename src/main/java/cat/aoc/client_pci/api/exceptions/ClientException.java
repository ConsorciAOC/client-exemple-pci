package cat.aoc.client_pci.api.exceptions;

import lombok.Getter;
import org.springframework.ws.client.WebServiceClientException;

@Getter
public class ClientException extends WebServiceClientException {

    private final String message;
    private final Exception exception;
    public ClientException(String message, Exception exception) {
        super(message);
        this.message = message;
        this.exception = exception;
    }

    public ClientException(String message) {
        super(message);
        this.message = message;
        this.exception = null;
    }

}
