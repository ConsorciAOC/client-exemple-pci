package cat.aoc.client_pci.model;

import lombok.Getter;

@Getter
public enum Frontal {
    SINCRON("Sincron"),
    ASINCRON("Asincron"),
    ASINCRON_RESPOSTA("AsincronResposta");

    private final String name;

    Frontal(String name) {
        this.name = name;
    }

}
