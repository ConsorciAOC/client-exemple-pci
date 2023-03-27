package cat.aoc.client_pci.samples.serveis.vo.estat.cadastre;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioCadastre implements Operacio {
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
