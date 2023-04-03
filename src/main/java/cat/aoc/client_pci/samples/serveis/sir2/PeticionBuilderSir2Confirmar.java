package cat.aoc.client_pci.samples.serveis.sir2;

import generated.sir2.PeticioConfirmacioAssentament;

interface PeticionBuilderSir2Confirmar {
    static PeticioConfirmacioAssentament buildPeticioConfirmacioAssentament() {
        PeticioConfirmacioAssentament peticio = new PeticioConfirmacioAssentament();
        peticio.setIdEnviamentSIR("O00015791_23_00060501");
        return peticio;
    }

}
