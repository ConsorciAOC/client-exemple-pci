package cat.aoc.client_pci.samples.serveis.etauler;

import generated.serveis.etauler.PeticioConsultarEstatEdicte;

interface PeticionBuilderEtaulerConsultar {

    static PeticioConsultarEstatEdicte buildPeticioConsultarEstatEdicte() {
        PeticioConsultarEstatEdicte peticio = new PeticioConsultarEstatEdicte();
        peticio.setIdEdicte("ET3-1600672644963");
        return peticio;
    }

}
