package cat.aoc.client_pci.soap.impl;

import cat.aoc.client_pci.Cluster;
import cat.aoc.client_pci.Entorn;
import cat.aoc.client_pci.Frontal;
import cat.aoc.client_pci.soap.SOAPClient;
import cat.aoc.client_pci.soap.SOAPOperation;
import cat.aoc.client_pci.exceptions.NotDefinedException;


public class PadroSOAPClient extends SOAPClient {
    private static final String[] PACKAGES = {
            "net.aocat.padro"
    };

    public PadroSOAPClient(Entorn entorn) throws Exception {
        super(entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public SOAPOperation getOperation(String modalitat) throws NotDefinedException {
        switch (modalitat){
            case "RESIDENT":
            case "MUNICIPI_RESIDENCIA":
            case "RESIDENT_MUNICIPI":
            case "NUMERO_CONVIVENTS":
            case "COMPROVACIO_CONVIVENTS":
            case "TITULAR":
            case "TITULAR_PROPI":
            case "CONVIVENTS":
            case "CONVIVENTS_PROPI":
            case "VALIDACIO_CONVIVENTS":
            case "TITULAR_PDF":
            case "CONVIVENTS_PDF":
            case "TITULAR_IDESCAT":
            case "CERCA_TITULAR":
                return new SOAPOperation(Frontal.SINCRON);
            default:
                throw new NotDefinedException("Modalitat no definida: " + modalitat);
        }
    }

}
