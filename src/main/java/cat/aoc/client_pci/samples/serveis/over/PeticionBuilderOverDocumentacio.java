package cat.aoc.client_pci.samples.serveis.over;

import generated.over.PeticioDocumentacioTramit;

interface PeticionBuilderOverDocumentacio {

    static PeticioDocumentacioTramit buildPeticioDocumentacioTramit() {
        PeticioDocumentacioTramit peticio = new PeticioDocumentacioTramit();
        peticio.setCodiInstanciaTramit(400547L);
        return peticio;
    }

}
