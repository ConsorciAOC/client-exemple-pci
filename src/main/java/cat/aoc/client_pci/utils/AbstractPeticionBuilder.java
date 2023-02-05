package cat.aoc.client_pci.utils;

import cat.aoc.client_pci.model.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Operacio;
import net.gencat.scsp.esquemes.peticion.Peticion;

import java.util.Properties;

public abstract class AbstractPeticionBuilder<O extends Operacio> {
    protected final String codiServei;
    protected final PeticionPropertiesBuilder builder;
    protected AbstractPeticionBuilder(String codiServei, String propertiesPath) throws NotFoundException {
        this.codiServei = codiServei;
        Properties properties = PropertiesReader.load(propertiesPath);
        builder = new PeticionPropertiesBuilder(properties);
    }

    public Peticion build(O operacio, Finalitat finalitat) {
        return builder.build(codiServei, operacio.getCodiModalitat(), finalitat.name(), getDatosEspecificos(operacio));
    }

    protected abstract Object[] getDatosEspecificos(O operacio);

}
