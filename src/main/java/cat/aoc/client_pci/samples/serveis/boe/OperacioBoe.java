package cat.aoc.client_pci.samples.serveis.boe;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioBoe implements Operacio {
    PUBLICAR,
    CONSULTAR,
    ANULAR;

    @Override
    public String getCodiProducte() {
        return "BOE";
    }

    @Override
    public String getCodiModalitat() {
        return "BOE";
    }

}
