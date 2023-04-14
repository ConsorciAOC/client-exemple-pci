package cat.aoc.client_pci.samples.serveis.etauler;

import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.pci.peticion.Fichero;
import generated.pci.peticion.Ficheros;
import generated.pci.peticion.Peticion;

import java.util.Properties;

import static cat.aoc.client_pci.samples.serveis.etauler.PeticionBuilderEtaulerConsultar.buildPeticioConsultarEstatEdicte;
import static cat.aoc.client_pci.samples.serveis.etauler.PeticionBuilderEtaulerPublicar.buildPeticioPublicarEdicte;

public class PeticionBuilderEtauler extends PeticionBuilderFromProperties<OperacioEtauler> {
    public PeticionBuilderEtauler(Properties properties) {
        super(properties);
    }

    @Override
    public Peticion build(OperacioEtauler operacio, Finalitat finalitat) {
        Peticion peticion = super.build(operacio, finalitat);
        if (operacio == OperacioEtauler.PUBLICAR) {
            peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos()
                    .setFicheros(getFicheros());
        }
        return peticion;
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioEtauler operacio) {
        return new Object[]{getDatoEspecifico(operacio)};
    }

    private Object getDatoEspecifico(OperacioEtauler operacio) {
        return switch (operacio) {
            case CONSULTAR -> buildPeticioConsultarEstatEdicte();
            case PUBLICAR -> buildPeticioPublicarEdicte();
            case DADES, AMPLIAR_TERMINI, CANCELAR, DESPENJAR, SINCRONITZAR, DESCARREGAR_DOCUMENT -> null;
        };
    }

    private static Ficheros getFicheros() {
        Fichero fichero = new Fichero();
        fichero.setNombreFichero("sample.pdf");
        fichero.setId("1234");
        fichero.setVia("Salida");
        jakarta.activation.DataSource ds = new jakarta.activation.FileDataSource("src\\main\\resources\\examples\\example.pdf");
        fichero.setContenido(new jakarta.activation.DataHandler(ds));
        Ficheros ficheros = new Ficheros();
        ficheros.getFichero().add(fichero);
        return ficheros;
    }

}
