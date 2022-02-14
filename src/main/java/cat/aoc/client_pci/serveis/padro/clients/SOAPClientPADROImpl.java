package cat.aoc.client_pci.serveis.padro.clients;

import cat.aoc.client_pci.Entorn;
import cat.aoc.client_pci.operations.SOAPRequestServei;
import cat.aoc.client_pci.serveis.padro.operations.PeticionDatosTitularRequest;
import net.aocat.padro.PeticionDatosTitular;
import net.aocat.padro.RespuestaDatosTitular;

public class SOAPClientPADROImpl implements SOAPClientPADRO {

    private final PeticionDatosTitularRequest peticionDatosTitularRequest ;

    public SOAPClientPADROImpl(Entorn entorn) throws Exception {
        this.peticionDatosTitularRequest= new PeticionDatosTitularRequest(entorn);
    }

    @Override
    public RespuestaDatosTitular consultaTitular(PeticionDatosTitular peticionDatosTitular) {
        return peticionDatosTitularRequest.invoke(peticionDatosTitular);
    }

    @Override
    public SOAPRequestServei getOperacio(String modalitat) throws Exception {
        switch (modalitat){
            case "TITULAR":
                return peticionDatosTitularRequest;
            default:
                throw new Exception("Modalitat no afegida: " + modalitat);
        }
    }
}
