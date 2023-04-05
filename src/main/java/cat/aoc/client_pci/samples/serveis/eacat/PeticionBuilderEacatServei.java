package cat.aoc.client_pci.samples.serveis.eacat;

import generated.serveis.eacat.PeticioConsultaServei;

interface PeticionBuilderEacatServei {
    static PeticioConsultaServei buildPeticioConsultaServei() {
        PeticioConsultaServei peticio = new PeticioConsultaServei();
        peticio.setServei("SIFECAT1420");
        peticio.setNif("38873981W");
        return peticio;
    }

}
