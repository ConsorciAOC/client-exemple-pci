package cat.aoc.client_pci.samples.serveis.vo.local;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioPadro implements Operacio {
    RESIDENT,
    MUNICIPI_RESIDENCIA,
    RESIDENT_MUNICIPI,
    NUMERO_CONVIVENTS,
    COMPROVACIO_CONVIVENTS,
    TITULAR,
    TITULAR_PROPI,
    CONVIVENTS,
    CONVIVENTS_PROPI,
    TITULAR_PDF,
    CONVIVENTS_PDF,
    TITULAR_IDESCAT,
    CERCA_TITULAR;

    @Override
    public String getCodiProducte() {
        return "PADRO";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
