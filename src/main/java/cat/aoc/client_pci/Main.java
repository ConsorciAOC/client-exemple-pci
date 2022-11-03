package cat.aoc.client_pci;

import cat.aoc.client_pci.clients.Clients;
import cat.aoc.client_pci.clients.tfn.TFNOperacio;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            ClientAOC client = Clients.TFN.getClient(Entorn.PRE);
            client.send(TFNOperacio.TFN_DADESCOMPLETES, Finalitat.PROVES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
