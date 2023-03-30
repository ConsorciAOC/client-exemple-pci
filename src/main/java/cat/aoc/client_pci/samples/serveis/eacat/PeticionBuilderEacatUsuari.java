package cat.aoc.client_pci.samples.serveis.eacat;

import generated.eacat.PeticioConsultaUsuari;

interface PeticionBuilderEacatUsuari {
    static PeticioConsultaUsuari buildPeticioConsultaUsuari() {
        PeticioConsultaUsuari peticio = new PeticioConsultaUsuari();
        peticio.setNif("38461395J");
        return peticio;
    }

}
