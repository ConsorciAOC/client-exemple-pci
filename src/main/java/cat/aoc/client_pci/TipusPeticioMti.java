package cat.aoc.client_pci;

public enum TipusPeticioMti {
    SINCRON("Sincron"),
    ASINCRON("Asincron"),
    ASINCRON_RESPOSTA("AsincronResposta");

    private final String value;

    TipusPeticioMti(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
