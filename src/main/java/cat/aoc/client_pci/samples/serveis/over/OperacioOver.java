package cat.aoc.client_pci.samples.serveis.over;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioOver implements Operacio {
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
