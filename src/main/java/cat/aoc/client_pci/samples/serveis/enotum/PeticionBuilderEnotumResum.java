package cat.aoc.client_pci.samples.serveis.enotum;

import generated.enotum.EmissorType;
import generated.enotum.PeticioResum;
import generated.enotum.UsuariType;

interface PeticionBuilderEnotumResum {
    static PeticioResum buildPeticioResum(EmissorType emissor, UsuariType usuari) {
        PeticioResum peticio = new PeticioResum();
        peticio.setEmissor(emissor);
        peticio.setUsuari(usuari);
        return peticio;
    }

}
