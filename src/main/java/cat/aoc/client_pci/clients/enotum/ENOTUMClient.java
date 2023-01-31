package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.model.*;
import net.gencat.scsp.esquemes.peticion.Fichero;
import net.gencat.scsp.esquemes.peticion.Ficheros;
import net.gencat.scsp.esquemes.peticion.Peticion;
public class ENOTUMClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.enotum",
    };

    private final PeticionBuilder peticionBuilder;
    public ENOTUMClient(String keystorePath, Entorn entorn, PeticionBuilder peticionBuilder) {
        super(keystorePath, entorn, Cluster.NT, peticionBuilder, PACKAGES);
        this.peticionBuilder = peticionBuilder;
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        try {
            return switch ((ENOTUMOperacio) operacio) {
                case PROCESSAR_TRAMESA, RESUM, EVIDENCIA, PRACTICAR, RECUPERAR_REPORT, CONSULTA, PARAULA_PAS, CERCA -> Frontal.SINCRON;
            };
        } catch (Exception e) {
            throw new NotDefinedException("Operacio no definida: " + operacio);
        }
    }

    @Override
    public String getCodiServei() {
        return "ENOTUM";
    }

    @Override
    public String getCodiModalitat(Operacio operacio) {
        return "ENOTUM";
    }

    @Override
    protected Peticion buildPeticion(Operacio operacio, Finalitat finalitat) throws NotDefinedException, NotFoundException {
        Peticion peticion = peticionBuilder.build(
                getCodiServei(),
                operacio,
                getCodiModalitat(operacio),
                finalitat
        );
        Fichero fichero = new Fichero();
        fichero.setNombreFichero("sample.pdf");
        fichero.setId("1234");
        fichero.setVia("Salida");
        jakarta.activation.DataSource ds = new jakarta.activation.FileDataSource("C:\\Users\\obernalp\\Documents\\PROJECTS\\demo\\src\\main\\resources\\examples\\example.pdf");
        fichero.setContenido(new jakarta.activation.DataHandler(ds));
        Ficheros ficheros = new Ficheros();
        ficheros.getFichero().add(fichero);
        peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos()
                .setFicheros(ficheros);
        return peticion;
    }

}
