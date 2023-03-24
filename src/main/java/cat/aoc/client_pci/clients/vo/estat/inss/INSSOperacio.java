package cat.aoc.client_pci.clients.vo.estat.inss;

import cat.aoc.client_pci.model.Operacio;

public enum INSSOperacio implements Operacio {
    PRESTACIONS,
    PRESTACIONS_HISTORIC;

    @Override
    public String getCodiProducte() {
        return "PRESTACIONS_SOCIALS";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
