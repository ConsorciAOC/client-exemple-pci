package cat.aoc.client_pci.soap;

import cat.aoc.client_pci.Entorn;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.soap.impl.PadroSOAPClient;
import cat.aoc.client_pci.soap.impl.TfnSOAPClient;

public enum Clients {
    TFN,
    PADRO;

    public SOAPClient getClient(Entorn entorn) throws Exception {
        switch (this){
            case TFN:
                return new TfnSOAPClient(entorn);
            case PADRO:
                return new PadroSOAPClient(entorn);
            default:
                throw new NotDefinedException("Servei no definit: " + this.name());
        }
    }
}
