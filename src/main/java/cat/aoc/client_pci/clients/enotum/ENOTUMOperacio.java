package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.model.Operacio;

public enum ENOTUMOperacio implements Operacio {
    PROCESSAR_TRAMESA,
    RESUM,
    EVIDENCIA,
    PRACTICAR,
    RECUPERAR_REPORT,
    CONSULTA,
    PARAULA_PAS,
    CERCA;

    @Override
    public String getCodiModalitat() {
        return "ENOTUM";
    }

}
