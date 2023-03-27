package cat.aoc.client_pci;

import cat.aoc.client_pci.api.ClientPCI;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.Serveis;
import cat.aoc.client_pci.samples.serveis.enotum.OperacioEnotum;
import cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotum;
import cat.aoc.client_pci.utils.PropertiesReader;
import lombok.extern.slf4j.Slf4j;
import net.gencat.scsp.esquemes.peticion.Peticion;

import java.util.Properties;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Hello world!");
        try {
            ClientPCI client = Serveis.ENOTUM.getClient(Entorn.PRE, Frontal.SINCRON);
            Properties clientProperties = PropertiesReader.load("src/main/resources/client.properties");
            Peticion peticion = new PeticionBuilderEnotum(clientProperties).build(OperacioEnotum.CERCA, Finalitat.PROVES);
            client.send(peticion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
