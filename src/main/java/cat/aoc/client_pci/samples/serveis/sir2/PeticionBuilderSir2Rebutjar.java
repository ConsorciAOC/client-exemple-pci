package cat.aoc.client_pci.samples.serveis.sir2;

import generated.serveis.sir2.PeticioRebuigAssentament;

interface PeticionBuilderSir2Rebutjar {
    static PeticioRebuigAssentament buildPeticioRebuigAssentament() {
        PeticioRebuigAssentament peticio = new PeticioRebuigAssentament();
        peticio.setIdEnviamentSIR("O00015791_23_00060501");
        peticio.setMotiu("Motiu");
        return peticio;
    }

}
