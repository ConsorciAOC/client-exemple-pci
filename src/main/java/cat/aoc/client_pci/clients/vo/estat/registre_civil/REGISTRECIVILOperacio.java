package cat.aoc.client_pci.clients.vo.estat.registre_civil;

import cat.aoc.client_pci.model.Operacio;

public enum REGISTRECIVILOperacio implements Operacio {
    NAIXEMENT,
    MATRIMONI,
    DEFUNCIO;

    @Override
    public String getCodiProducte() {
        return "REGISTRE_CIVIL";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
