package cat.aoc.client_pci.samples.serveis.enotum;

import generated.serveis.enotum.PeticioConsulta;
import generated.serveis.enotum.UsuariType;

interface PeticionBuilderEnotumConsulta {
    static PeticioConsulta buildPeticioConsulta(UsuariType usuari) {
        PeticioConsulta peticio = new PeticioConsulta();
        peticio.setIdNotificacio(353336L);
        peticio.setUsuari(usuari);
        return peticio;
    }

}
