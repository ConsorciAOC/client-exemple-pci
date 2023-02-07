package cat.aoc.client_pci.clients.over;

import cat.aoc.client_pci.model.Operacio;

public enum OVEROperacio implements Operacio {
    OVER_DOCUMENTACIO,
    OVER_FORMULARI,
    OVER_CONTEXT,
    OVER_TRAMITACIO,
    OVER_ACTUALITZACIO,
    OVER_CONSULTA,
    OVER_LLISTA_EXPEDIENTS,
    OVER_CONSULTA_EXPEDIENT,
    OVER_LLISTA_SERVEIS,
    OVER_LLISTA_TRAMITS,
    OVER_INTEGRACIO;

    @Override
    public String getCodiProducte() {
        return "OVER";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
