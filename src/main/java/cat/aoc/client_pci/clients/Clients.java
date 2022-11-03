package cat.aoc.client_pci.clients;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.clients.enotum.ENOTUMClient;
import cat.aoc.client_pci.clients.etauler.ETAULERClient;
import cat.aoc.client_pci.clients.padro.PADROClient;
import cat.aoc.client_pci.clients.tfn.TFNClient;
import cat.aoc.client_pci.utils.PeticionBuilderFromProperties;

public enum Clients {
    TFN,
    PADRO,
    ENOTUM,
    ETAULER;

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    public ClientAOC getClient(Entorn entorn) throws WebServiceSupportException, NotDefinedException, NotFoundException {
        switch (this){
            case TFN:
                return new TFNClient(entorn, new PeticionBuilderFromProperties(PROPERTIES_PATH));
            case PADRO:
                return new PADROClient(entorn, new PeticionBuilderFromProperties(PROPERTIES_PATH));
            case ENOTUM:
                return new ENOTUMClient(entorn, new PeticionBuilderFromProperties(PROPERTIES_PATH));
            case ETAULER:
                return new ETAULERClient(entorn, new PeticionBuilderFromProperties(PROPERTIES_PATH));
            default:
                throw new NotDefinedException("Servei no definit: " + this.name());
        }
    }
}
