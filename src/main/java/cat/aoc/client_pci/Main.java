package cat.aoc.client_pci;

import cat.aoc.client_pci.clients.ClientAOC;
import cat.aoc.client_pci.clients.Servei;
import cat.aoc.client_pci.clients.enotum.ENOTUMOperacio;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import lombok.extern.slf4j.Slf4j;
import net.gencat.scsp.esquemes.peticion.Peticion;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Hello world!");
        try {
            ClientAOC client = Servei.ENOTUM.getClient(Entorn.PRE);
            Peticion peticion = Servei.ENOTUM.getPeticion(ENOTUMOperacio.CERCA, Finalitat.PROVES);
            client.send(ENOTUMOperacio.CERCA, peticion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
