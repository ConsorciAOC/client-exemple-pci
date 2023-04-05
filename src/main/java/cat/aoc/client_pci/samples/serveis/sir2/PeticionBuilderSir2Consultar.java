package cat.aoc.client_pci.samples.serveis.sir2;

import generated.serveis.sir2.PeticioConsultaAssentament;

interface PeticionBuilderSir2Consultar {
    static PeticioConsultaAssentament buildPeticioConsultaAssentament() {
        PeticioConsultaAssentament peticio = new PeticioConsultaAssentament();
        peticio.setIdEnviamentSIR("O00015791_23_00060501");
        return peticio;
    }

}
