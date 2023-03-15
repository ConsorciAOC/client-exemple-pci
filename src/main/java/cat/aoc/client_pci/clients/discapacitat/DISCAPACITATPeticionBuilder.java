package cat.aoc.client_pci.clients.discapacitat;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.discapacitat.DadesPersona;
import generated.discapacitat.PeticioConsultaDiscapacitatSimple;

public class DISCAPACITATPeticionBuilder extends AbstractPeticionBuilder<DISCAPACITATOperacio> {
    public DISCAPACITATPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(DISCAPACITATOperacio operacio) {
        return switch (operacio) {
            case GRAU_DISCAPACITAT_SIMPLE -> new Object[]{
                    buildPeticioConsultaDiscapacitatSimple()
            };
            default -> new Object[]{};
        };
    }

    private PeticioConsultaDiscapacitatSimple buildPeticioConsultaDiscapacitatSimple() {
        PeticioConsultaDiscapacitatSimple peticio = new PeticioConsultaDiscapacitatSimple();
        DadesPersona dadesPersona = new DadesPersona();
        dadesPersona.setNom("NOM12");
        dadesPersona.setCognoms("COGNOM121 COGNOM122");
        dadesPersona.setDataNaixement("15111946");
        peticio.setDadesPersona(dadesPersona);
        return peticio;
    }

}
