package cat.aoc.client_pci.samples.serveis.sir2;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioSir2 implements Operacio {
    ENVIAR,
    CONFIRMAR,
    REBUTJAR,
    REENVIAR,
    CONSULTAR,
    SINCRONITZAR;

    @Override
    public String getCodiProducte() {
        return "SIR";
    }

    @Override
    public String getCodiModalitat() {
        return "SIR";
    }

}
