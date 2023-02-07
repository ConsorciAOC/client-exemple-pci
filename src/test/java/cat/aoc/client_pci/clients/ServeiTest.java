package cat.aoc.client_pci.clients;

import cat.aoc.client_pci.Servei;
import cat.aoc.client_pci.clients.dgp.DGPClient;
import cat.aoc.client_pci.clients.dgp.DGPOperacio;
import cat.aoc.client_pci.clients.enotum.ENOTUMClient;
import cat.aoc.client_pci.clients.enotum.ENOTUMOperacio;
import cat.aoc.client_pci.clients.etauler.ETAULERClient;
import cat.aoc.client_pci.clients.etauler.ETAULEROperacio;
import cat.aoc.client_pci.clients.over.OVERClient;
import cat.aoc.client_pci.clients.over.OVEROperacio;
import cat.aoc.client_pci.clients.padro.PADROOperacio;
import cat.aoc.client_pci.clients.padro.PADROProxyClient;
import cat.aoc.client_pci.clients.tfn.TFNClient;
import cat.aoc.client_pci.clients.tfn.TFNOperacio;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServeiTest {

    @Test
    void getClient() {
        assertDoesNotThrow(
                () -> {
                    assertTrue(Servei.ENOTUM.getClient(Entorn.PRE) instanceof ENOTUMClient);
                    assertTrue(Servei.ETAULER.getClient(Entorn.PRE) instanceof ETAULERClient);
                    assertTrue(Servei.OVER.getClient(Entorn.PRE) instanceof OVERClient);
                    assertTrue(Servei.TFN.getClient(Entorn.PRE) instanceof TFNClient);
                    assertTrue(Servei.DGP.getClient(Entorn.PRE) instanceof DGPClient);
                    assertTrue(Servei.PADRO.getClient(Entorn.PRE) instanceof PADROProxyClient);
                }
        );
    }

    @Test
    void getPeticion() {
        assertDoesNotThrow(
                () -> {
                    assertNotNull(Servei.ENOTUM.getPeticion(ENOTUMOperacio.PARAULA_PAS, Finalitat.PROVES));
                    assertNotNull(Servei.ETAULER.getPeticion(ETAULEROperacio.AMPLIAR_TERMINI, Finalitat.PROVES));
                    assertNotNull(Servei.OVER.getPeticion(OVEROperacio.OVER_DOCUMENTACIO, Finalitat.PROVES));
                    assertNotNull(Servei.TFN.getPeticion(TFNOperacio.TFN_DADESCOMPLETES, Finalitat.PROVES));
                    assertNotNull(Servei.DGP.getPeticion(DGPOperacio.IDENTITAT_DADES, Finalitat.PROVES));
                    assertNotNull(Servei.PADRO.getPeticion(PADROOperacio.CONVIVENTS, Finalitat.PROVES));
                }
        );
    }

    @Test
    void getCluster() {
        assertEquals(Cluster.NT, Servei.ENOTUM.getCluster());
        assertEquals(Cluster.APP, Servei.ETAULER.getCluster());
        assertEquals(Cluster.APP, Servei.OVER.getCluster());
        assertEquals(Cluster.IOP, Servei.TFN.getCluster());
        assertEquals(Cluster.IOP, Servei.DGP.getCluster());
        assertEquals(Cluster.IOP, Servei.PADRO.getCluster());
    }

    @Test
    void getCodi() {
        assertEquals("ENOTUM", Servei.ENOTUM.getCodi());
        assertEquals("ETAULER", Servei.ETAULER.getCodi());
        assertEquals("OVER", Servei.OVER.getCodi());
        assertEquals("TFN", Servei.TFN.getCodi());
        assertEquals("DGP_IDENTITAT", Servei.DGP.getCodi());
        assertEquals("PADRO", Servei.PADRO.getCodi());
    }

}
