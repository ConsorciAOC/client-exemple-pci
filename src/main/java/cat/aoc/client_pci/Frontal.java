package cat.aoc.client_pci;

public enum Frontal {
    SINCRON("Sincron"),
    ASINCRON("Asincron"),
    ASINCRON_RESPOSTA("AsincronResposta");

    private final String value;

    Frontal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
