package cat.aoc.client_pci.samples.serveis.enotum;

import generated.serveis.enotum.PeticioResum;
import generated.serveis.enotum.UsuariType;

interface PeticionBuilderEnotumResum {
    static PeticioResum buildPeticioResum(UsuariType usuari) {
        PeticioResum peticio = new PeticioResum();
        peticio.setUsuari(usuari);
        return peticio;
    }

}
