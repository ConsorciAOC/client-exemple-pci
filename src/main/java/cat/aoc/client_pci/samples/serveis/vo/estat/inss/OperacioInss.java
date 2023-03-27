package cat.aoc.client_pci.samples.serveis.vo.estat.inss;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioInss implements Operacio {
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
