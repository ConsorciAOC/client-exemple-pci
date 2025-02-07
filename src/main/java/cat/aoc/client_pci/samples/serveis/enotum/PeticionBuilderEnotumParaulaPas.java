package cat.aoc.client_pci.samples.serveis.enotum;

import generated.serveis.enotum.PeticioParaulaPas;
import generated.serveis.enotum.UsuariType;

interface PeticionBuilderEnotumParaulaPas {
    static PeticioParaulaPas buildPeticioParaulaPas(UsuariType usuari) {
        PeticioParaulaPas peticio = new PeticioParaulaPas();
        PeticioParaulaPas.DadesEnviament dades = new PeticioParaulaPas.DadesEnviament();
        dades.setBustiaCorreu("XXXX@XXXXXX.com");
        peticio.setDadesEnviament(dades);
        peticio.setUsuari(usuari);
        return peticio;
    }

}
