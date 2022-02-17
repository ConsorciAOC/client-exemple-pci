package cat.aoc.client_pci.soap.impl;

import cat.aoc.client_pci.Cluster;
import cat.aoc.client_pci.Entorn;
import cat.aoc.client_pci.Frontal;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.soap.SOAPClient;
import cat.aoc.client_pci.soap.SOAPOperation;
import cat.aoc.client_pci.exceptions.NotDefinedException;

public class TfnSOAPClient extends SOAPClient {
    private static final String[] PACKAGES = {
            "cat.aoc.tfn"
    };

    public TfnSOAPClient(Entorn entorn) throws WebServiceSupportException {
        super(entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public SOAPOperation getOperation(String modalitat) throws NotDefinedException {
        switch (modalitat){
            case "TFN_VIGENCIA":
            case "TFN_DADESCOMPLETES":
            case "TFN_DADESCOMPLETES_DIS":
                return new SOAPOperation(Frontal.SINCRON);
            default:
                throw new NotDefinedException("Modalitat no definida: " + modalitat);
        }
    }

}
