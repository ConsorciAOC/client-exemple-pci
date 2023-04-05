package cat.aoc.client_pci.samples.serveis.eacat;

import generated.serveis.eacat.PeticioConsultaTipusEns;

interface PeticionBuilderEacatTipusEns {
    static PeticioConsultaTipusEns buildPeticioConsultaTipusEns() {
        return new PeticioConsultaTipusEns();
    }

}
