package cat.aoc.client_pci.samples.serveis.boe;

import generated.boe.PeticioEnviamentAnunci;

interface PeticionBuilderBoeConsultar {
    static PeticioEnviamentAnunci buildPeticioEnviamentAnunci() {
        PeticioEnviamentAnunci peticio = new PeticioEnviamentAnunci();
        peticio.setId("IDENTIFICADORPROVA");
        return peticio;
    }

}
