package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.model.Operacio;

public enum ENOTUMOperacio implements Operacio {
    PeticioProcessarTramesa,
    PeticioResum,
    PeticioEvidencia,
    PeticioPracticar,
    PeticioRecuperarReport,
    PeticioConsulta,
    PeticioParaulaPas,
    PeticioCerca;
}
