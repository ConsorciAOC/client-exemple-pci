package cat.aoc.client_pci.clients;

import cat.aoc.client_pci.Servei;
import cat.aoc.client_pci.clients.enotum.ENOTUMClient;
import cat.aoc.client_pci.clients.etauler.ETAULERClient;
import cat.aoc.client_pci.clients.padro.PADROProxyClient;
import cat.aoc.client_pci.clients.tfn.TFNClient;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServeiTest {

    @Test
    void getClient() {
        assertDoesNotThrow(
                () -> {
                    assertTrue(Servei.ENOTUM.getClient(Entorn.PRE) instanceof ENOTUMClient);
                    assertTrue(Servei.ETAULER.getClient(Entorn.PRE) instanceof ETAULERClient);
                    assertTrue(Servei.PADRO.getClient(Entorn.PRE) instanceof PADROProxyClient);
                    assertTrue(Servei.TFN.getClient(Entorn.PRE) instanceof TFNClient);
                }
        );
    }

    @Test
    void getPeticion() {
    }

    @Test
    void getCluster() {
        assertEquals(Cluster.NT, Servei.ENOTUM.getCluster());
        assertEquals(Cluster.APP, Servei.ETAULER.getCluster());
        assertEquals(Cluster.IOP, Servei.PADRO.getCluster());
        assertEquals(Cluster.IOP, Servei.TFN.getCluster());
    }

    @Test
    void getCodi() {
        assertEquals("ENOTUM", Servei.ENOTUM.getCodi());
        assertEquals("ETAULER", Servei.ETAULER.getCodi());
        assertEquals("PADRO", Servei.PADRO.getCodi());
        assertEquals("TFN", Servei.TFN.getCodi());
    }

}
