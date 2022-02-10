package cat.aoc.client_pci;


import org.openuri.Procesa;
import org.openuri.ProcesaResponse;

public interface PCI3ClientI  {

    String getEndpoint(Cluster cluster, TipusPeticioMti tipus);

    ProcesaResponse procesa(Cluster cluster, Procesa procesa);

}