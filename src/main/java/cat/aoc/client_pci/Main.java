package cat.aoc.client_pci;

import cat.aoc.client_pci.serveis.tfn.TFNClient;
import lombok.extern.slf4j.Slf4j;
import net.gencat.scsp.esquemes.peticion.Peticion;
import org.openuri.Procesa;
import org.openuri.ProcesaResponse;

@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {
        TFNClient tfnClient = new TFNClient();
        Peticion peticionTFN = tfnClient.buildPeticion();
        System.out.println(peticionTFN.getAtributos().getIdPeticion());

        PCI3ClientI client = new MTISincronClient();
        Procesa procesa = new Procesa();
        procesa.setPeticion(peticionTFN);
        ProcesaResponse response = client.procesa(Cluster.IOP, procesa);
        System.out.println(response.getRespuesta().getAtributos().getIdPeticion());

    }


}
