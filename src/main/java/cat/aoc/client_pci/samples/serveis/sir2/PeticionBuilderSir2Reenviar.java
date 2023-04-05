package cat.aoc.client_pci.samples.serveis.sir2;

import generated.serveis.sir2.PeticioReenviamentAssentament;

interface PeticionBuilderSir2Reenviar {
    static PeticioReenviamentAssentament buildPeticioReenviamentAssentament() {
        PeticioReenviamentAssentament peticio = new PeticioReenviamentAssentament();
        peticio.setIdEnviamentSIR("O00015791_23_00060501");
        peticio.setOficinaRegistreDesti("O00002721");
        peticio.setObservacions("Observacions");
        return peticio;
    }

}
