package cat.aoc.client_pci.clients;

import cat.aoc.client_pci.clients.enotum.ENOTUMClient;
import cat.aoc.client_pci.clients.etauler.ETAULERClient;
import cat.aoc.client_pci.clients.padro.PADROClient;
import cat.aoc.client_pci.clients.tfn.TFNClient;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.Entorn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientsTest {

    @Test
    void getClient() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        assertTrue(Clients.ENOTUM.getClient(Entorn.PRE) instanceof ENOTUMClient);
        assertTrue(Clients.ETAULER.getClient(Entorn.PRE) instanceof ETAULERClient);
        assertTrue(Clients.PADRO.getClient(Entorn.PRE) instanceof PADROClient);
        assertTrue(Clients.TFN.getClient(Entorn.PRE) instanceof TFNClient);
    }
}