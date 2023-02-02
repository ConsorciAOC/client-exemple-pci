package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.model.Operacio;

public enum PADROOperacio implements Operacio {
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
    public String getCodiModalitat() {
        return this.name();
    }
}
