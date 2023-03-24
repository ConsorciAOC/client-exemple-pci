package cat.aoc.client_pci.clients.vo.estat.cadastre;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.cadastre.certificacio.Ambito;
import generated.cadastre.certificacio.Cn;
import generated.cadastre.certificacio.DatosEntrada;
import generated.cadastre.certificacio.PeticioCertificacioTitular;

public class CADASTREPeticionBuilder extends AbstractPeticionBuilder<CADASTREOperacio> {
    public CADASTREPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(CADASTREOperacio operacio) {
        return switch (operacio) {
            case CERTIFICACIO_TITULARITAT -> new Object[]{
                    buildPeticioCertificacioTitular()
            };
            default -> new Object[]{
            };
        };
    }

    private PeticioCertificacioTitular buildPeticioCertificacioTitular() {
        PeticioCertificacioTitular peticio = new PeticioCertificacioTitular();
        DatosEntrada datos = new DatosEntrada();
        Ambito ambito = new Ambito();
        ambito.setCcaa("09");
        ambito.setCp("08");
        ambito.setCm("155");
        datos.setAmbito(ambito);
        datos.setCn(Cn.UR);
        peticio.setDatosEntrada(datos);
        return peticio;
    }

}
