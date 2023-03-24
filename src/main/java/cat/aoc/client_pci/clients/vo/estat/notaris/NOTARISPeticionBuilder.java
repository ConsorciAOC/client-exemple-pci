package cat.aoc.client_pci.clients.vo.estat.notaris;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.notaris.IdEscriptura;
import generated.notaris.PeticioConsultaSubsistenciaAdministradors;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;

public class NOTARISPeticionBuilder extends AbstractPeticionBuilder<NOTARISOperacio> {
    public NOTARISPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(NOTARISOperacio operacio) {
        return switch (operacio) {
            case SUBSISTENCIA_ADMINISTRADORS -> new Object[]{
                    buildPeticioConsultaSubsistenciaAdministradors()
            };
            default -> new Object[]{};
        };
    }

    private PeticioConsultaSubsistenciaAdministradors buildPeticioConsultaSubsistenciaAdministradors() {
        PeticioConsultaSubsistenciaAdministradors peticio = new PeticioConsultaSubsistenciaAdministradors();
        PeticioConsultaSubsistenciaAdministradors.DadesProtocol dades = new PeticioConsultaSubsistenciaAdministradors.DadesProtocol();
        IdEscriptura id = new IdEscriptura();
        id.setCodiNotari("0913999");
        id.setCodiNotaria("070012009");
        id.setNumProtocol(BigInteger.valueOf(22));
        try {
            id.setDataAutoritzacio(DatatypeFactory.newInstance().newXMLGregorianCalendar("2013-01-11"));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        dades.setIdEscriptura(id);
        peticio.setDadesProtocol(dades);
        return peticio;
    }

}
