package cat.aoc.client_pci.samples.serveis.vo.estat.registre_civil;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioRegistreCivil implements Operacio {
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
