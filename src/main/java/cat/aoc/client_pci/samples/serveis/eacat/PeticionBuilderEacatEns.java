package cat.aoc.client_pci.samples.serveis.eacat;

import generated.eacat.PeticioConsultaEns;

interface PeticionBuilderEacatEns {
    static PeticioConsultaEns buildPeticioConsultaEns() {
        PeticioConsultaEns peticio = new PeticioConsultaEns();
        peticio.setCodiEns("9821920002");
        return peticio;
    }

}
