package cat.aoc.client_pci.clients.vo.estat.registre_civil;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.registre_civil.AltresDades;
import generated.registre_civil.PeticioConsultaRegistreCivil;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

public class REGISTRECIVILPeticionBuilder extends AbstractPeticionBuilder<REGISTRECIVILOperacio> {
    public REGISTRECIVILPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(REGISTRECIVILOperacio operacio) {
        return switch (operacio) {
            case NAIXEMENT -> new Object[]{
                    buildPeticioConsultaRegistreCivil()
            };
            default -> new Object[]{};
        };
    }

    private PeticioConsultaRegistreCivil buildPeticioConsultaRegistreCivil() {
        PeticioConsultaRegistreCivil peticio = new PeticioConsultaRegistreCivil();
        try {
            peticio.setDataFetRegistral(DatatypeFactory.newInstance().newXMLGregorianCalendar("1980-03-02"));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        peticio.setAbsenciaSegonCognom(false);
        AltresDades altres = new AltresDades();
        altres.setPoblacioFetRegistral("01009");
        try {
            altres.setDataNaixement(DatatypeFactory.newInstance().newXMLGregorianCalendar("1980-03-02"));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        peticio.setAltresDades(altres);
        return peticio;
    }

}
