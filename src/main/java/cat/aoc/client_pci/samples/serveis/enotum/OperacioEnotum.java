package cat.aoc.client_pci.samples.serveis.enotum;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioEnotum implements Operacio {

    ANULLACIO,
    CERCA,
    CONSULTA,
    EVIDENCIA,
    PARAULA_PAS,
    PRACTICAR,
    PROCESSAR_TRAMESA,
    RECUPERAR_REPORT,
    RESUM;

    @Override
    public String getCodiProducte() {
        return "ENOTUM";
    }

    @Override
    public String getCodiModalitat() {
        return "ENOTUM";
    }

}
