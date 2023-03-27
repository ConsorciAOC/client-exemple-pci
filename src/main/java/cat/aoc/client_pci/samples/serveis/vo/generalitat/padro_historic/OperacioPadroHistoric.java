package cat.aoc.client_pci.samples.serveis.vo.generalitat.padro_historic;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioPadroHistoric implements Operacio {
    TITULAR_HISTORIC,
    CONVIVENTS_HISTORIC;

    @Override
    public String getCodiProducte() {
        return "PADRO_HISTORIC";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
