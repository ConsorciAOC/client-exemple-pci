package cat.aoc.client_pci.operations;

import org.openuri.Procesa;
import org.openuri.ProcesaResponse;

public interface SOAPRequest {

    String getEndpoint();

    ProcesaResponse procesa(Procesa procesa);

}
