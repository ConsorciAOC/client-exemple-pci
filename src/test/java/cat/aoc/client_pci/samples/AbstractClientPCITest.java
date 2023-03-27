package cat.aoc.client_pci.samples;

import cat.aoc.client_pci.api.ClientPCI;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.utils.PropertiesReader;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class AbstractClientPCITest<O extends Operacio> {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    protected static final Properties PROPERTIES;

    static {
        try {
            PROPERTIES = PropertiesReader.load(PROPERTIES_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final ClientPCI client;
    private final PeticionBuilderFromProperties<O> builder;
    public AbstractClientPCITest(ClientPCI client, PeticionBuilderFromProperties<O> builder) {
        this.client = client;
        this.builder = builder;
    }

    @Test
    public <T> T send(O operacio, Finalitat finalitat) {
        Respuesta respuesta = client.send(builder.build(operacio, finalitat));
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        return (T) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
    }

}
