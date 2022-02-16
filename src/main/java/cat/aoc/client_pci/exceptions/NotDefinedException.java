package cat.aoc.client_pci.exceptions;

public class NotDefinedException extends Exception {

    public NotDefinedException(String servei) {
        super("Servei no definit: " + servei);
    }

}
