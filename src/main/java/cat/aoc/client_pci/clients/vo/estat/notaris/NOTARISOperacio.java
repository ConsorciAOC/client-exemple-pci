package cat.aoc.client_pci.clients.vo.estat.notaris;

import cat.aoc.client_pci.model.Operacio;

public enum NOTARISOperacio implements Operacio {
    SUBSISTENCIA_ADMINISTRADORS,
    SUBSISTENCIA_PODERS,
    COPIA_SIMPLE,
    CONSULTA_NOTARIS;

    @Override
    public String getCodiProducte() {
        return "PODERS_NOTARIALS";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
