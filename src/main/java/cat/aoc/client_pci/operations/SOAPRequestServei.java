package cat.aoc.client_pci.operations;

public interface SOAPRequestServei<P, R> extends SOAPRequestPCI {

    R invoke(P data);

}
