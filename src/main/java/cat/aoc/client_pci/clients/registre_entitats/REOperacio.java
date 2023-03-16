package cat.aoc.client_pci.clients.registre_entitats;

import cat.aoc.client_pci.model.Operacio;

public enum REOperacio implements Operacio {
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
