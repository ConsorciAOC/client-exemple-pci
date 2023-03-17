package cat.aoc.client_pci.clients.vo.generalitat.padro_historic;

import cat.aoc.client_pci.model.Operacio;

public enum PADRO_HISTORICOperacio implements Operacio {
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
