package cat.aoc.client_pci.clients.etauler;

import cat.aoc.client_pci.model.Operacio;

public enum ETAULEROperacio implements Operacio {
    PUBLICAR,
    DADES,
    AMPLIAR_TERMINI,
    CANCELAR,
    DESPENJAR,
    SINCRONITZAR,
    DESCARREGAR_DOCUMENT,
    CONSULTAR;
}
