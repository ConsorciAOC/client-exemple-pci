package cat.aoc.client_pci.clients.vo.estat.inss;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.inss_historic.PeticioConsultaPrestacionsHistoric;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

public class INSSPeticionBuilder extends AbstractPeticionBuilder<INSSOperacio> {
    public INSSPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(INSSOperacio operacio) {
        return switch (operacio) {
            case PRESTACIONS_HISTORIC -> new Object[]{
                    buildPeticioConsultaPrestacionsHistoric()
            };
            default -> new Object[]{};
        };
    }

    private PeticioConsultaPrestacionsHistoric buildPeticioConsultaPrestacionsHistoric() {
        PeticioConsultaPrestacionsHistoric peticio = new PeticioConsultaPrestacionsHistoric();
        try {
            peticio.setDataInici(DatatypeFactory.newInstance().newXMLGregorianCalendar("2021-01-01"));
            peticio.setDataFi(DatatypeFactory.newInstance().newXMLGregorianCalendar("2021-12-01"));
        } catch (DatatypeConfigurationException e) {
           e.printStackTrace();
        }
        return peticio;
    }

}
