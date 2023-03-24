package cat.aoc.client_pci.clients.vo.estat.cadastre;

import cat.aoc.client_pci.model.Operacio;

public enum CADASTREOperacio implements Operacio {
    DADES_CADASTRALS,
    CERTIFICACIO_TITULARITAT,
    DESCRIPTIVA_GRAFICA,
    DOCUMENT_CSV;

    @Override
    public String getCodiProducte() {
        return "CADASTRE";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
