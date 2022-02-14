package cat.aoc.client_pci.serveis;

import cat.aoc.client_pci.Entorn;
import cat.aoc.client_pci.SOAPClient;
import cat.aoc.client_pci.serveis.padro.clients.SOAPClientPADROImpl;
import cat.aoc.client_pci.serveis.tfn.clients.SOAPClientTFNImpl;

public enum ServiceClient {
    TFN, PADRO;

    public SOAPClient getClient(Entorn entorn) throws Exception {
        switch (this){
            case TFN:
                return new SOAPClientTFNImpl(entorn);
            case PADRO:
                return new SOAPClientPADROImpl(entorn);
            default:
                throw new Exception("Service not defined: " + this.name());
        }
    }
}
