package cat.aoc.client_pci.samples.serveis.etauler;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioEtauler implements Operacio {
    PUBLICAR,
    DADES,
    AMPLIAR_TERMINI,
    CANCELAR,
    DESPENJAR,
    SINCRONITZAR,
    DESCARREGAR_DOCUMENT,
    CONSULTAR;

    @Override
    public String getCodiProducte() {
        return "ETAULER";
    }

    @Override
    public String getCodiModalitat() {
        return "ETAULER";
    }
}
