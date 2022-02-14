package cat.aoc.client_pci.serveis.padro.clients;

import cat.aoc.client_pci.SOAPClient;
import net.aocat.padro.*;

public interface SOAPClientPADRO extends SOAPClient {

    RespuestaDatosTitular consultaTitular(PeticionDatosTitular peticionDatosTitular);

}
