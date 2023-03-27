package cat.aoc.client_pci.samples.serveis.enotum;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioEnotum implements Operacio {
    CERCA,
    CONSULTA,
    EVIDENCIA,
    PARAULA_PAS,
    PRACTICAR,
    PROCESSAR_TRAMESA,
    RESUM,
    RECUPERAR_REPORT;

    @Override
    public String getCodiProducte() {
        return "ENOTUM";
    }

    @Override
    public String getCodiModalitat() {
        return "ENOTUM";
    }

}
