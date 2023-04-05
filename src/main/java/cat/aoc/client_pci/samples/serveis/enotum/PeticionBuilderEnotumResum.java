package cat.aoc.client_pci.samples.serveis.enotum;

import generated.serveis.enotum.EmissorType;
import generated.serveis.enotum.PeticioResum;
import generated.serveis.enotum.UsuariType;

interface PeticionBuilderEnotumResum {
    static PeticioResum buildPeticioResum(EmissorType emissor, UsuariType usuari) {
        PeticioResum peticio = new PeticioResum();
        peticio.setEmissor(emissor);
        peticio.setUsuari(usuari);
        return peticio;
    }

}
