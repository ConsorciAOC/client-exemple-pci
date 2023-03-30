package cat.aoc.client_pci.samples.serveis.eacat;

import generated.eacat.PeticioConsultaTipusEns;

interface PeticionBuilderEacatTipusEns {
    static PeticioConsultaTipusEns buildPeticioConsultaTipusEns() {
        return new PeticioConsultaTipusEns();
    }

}
