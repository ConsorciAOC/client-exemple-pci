package cat.aoc.client_pci.samples.serveis.enotum;

import generated.serveis.enotum.*;

import java.math.BigInteger;

interface PeticionBuilderEnotumConsulta {
    static PeticioConsulta buildPeticioConsulta(EmissorType emissor, UsuariType usuari) {
        PeticioConsulta peticio = new PeticioConsulta();
        peticio.setIdNotificacio(BigInteger.valueOf(353336));
        peticio.setEmissor(emissor);
        peticio.setUsuari(usuari);
        return peticio;
    }

}
