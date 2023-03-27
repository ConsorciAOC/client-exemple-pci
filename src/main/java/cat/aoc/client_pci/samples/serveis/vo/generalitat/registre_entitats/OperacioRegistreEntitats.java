package cat.aoc.client_pci.samples.serveis.vo.generalitat.registre_entitats;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioRegistreEntitats implements Operacio {
    ENTITAT_INSCRIPCIO,
    ENTITAT_DADES,
    ENTITAT_ESTATUTS,
    ENTITAT_ESCRIPTURES,
    ENTITAT_COMPTES;

    @Override
    public String getCodiProducte() {
        return "REGISTRE_ENTITATS";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
