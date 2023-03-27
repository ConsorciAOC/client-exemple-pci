package cat.aoc.client_pci.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntornTest {

    @Test
    void getEndpointsDev() {
        assertEquals("https://serveis3-dev.iop.aoc.cat:443", Entorn.DEV.getEndpoint(Cluster.IOP));
        assertEquals("https://serveis3-dev.app.aoc.cat:443", Entorn.DEV.getEndpoint(Cluster.APP));
        assertEquals("https://serveis3-dev.nt.aoc.cat:443", Entorn.DEV.getEndpoint(Cluster.NT));
    }

    @Test
    void getEndpointsPre() {
        assertEquals("https://serveis3-pre.iop.aoc.cat:443", Entorn.PRE.getEndpoint(Cluster.IOP));
        assertEquals("https://serveis3-pre.app.aoc.cat:443", Entorn.PRE.getEndpoint(Cluster.APP));
        assertEquals("https://serveis3-pre.nt.aoc.cat:443", Entorn.PRE.getEndpoint(Cluster.NT));
    }

    @Test
    void getEndpointsPro() {
        assertEquals("https://serveis3.iop.aoc.cat:443", Entorn.PRO.getEndpoint(Cluster.IOP));
        assertEquals("https://serveis3.app.aoc.cat:443", Entorn.PRO.getEndpoint(Cluster.APP));
        assertEquals("https://serveis3.nt.aoc.cat:443", Entorn.PRO.getEndpoint(Cluster.NT));
    }

}