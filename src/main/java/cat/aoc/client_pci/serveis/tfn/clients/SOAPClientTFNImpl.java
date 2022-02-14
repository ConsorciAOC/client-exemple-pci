package cat.aoc.client_pci.serveis.tfn.clients;

import cat.aoc.client_pci.Entorn;
import cat.aoc.client_pci.operations.SOAPRequestServei;
import cat.aoc.client_pci.serveis.tfn.operations.ConsultaDadesTitolRequest;
import cat.aoc.client_pci.serveis.tfn.operations.ConsultaVigenciaRequest;
import cat.aoc.tfn.*;

public class SOAPClientTFNImpl implements SOAPClientTFN {

    private final ConsultaVigenciaRequest consultaVigenciaRequest ;
    private final ConsultaDadesTitolRequest consultaDadesTitolRequest ;

    public SOAPClientTFNImpl(Entorn entorn) throws Exception {
        this.consultaVigenciaRequest= new ConsultaVigenciaRequest(entorn);
        this.consultaDadesTitolRequest= new ConsultaDadesTitolRequest(entorn);
    }

    @Override
    public RespostaConsultaVigencia consultaVigencia(PeticioConsultaVigencia peticioConsultaVigencia) {
        return consultaVigenciaRequest.invoke(peticioConsultaVigencia);
    }

    @Override
    public RespostaDadesCompletes consultaDadesTitol(PeticioDadesCompletes peticioDadesCompletes) {
        return consultaDadesTitolRequest.invoke(peticioDadesCompletes);
    }

    @Override
    public RespostaDadesCompletesDiscapacitats consultaDadesTitolDiscapacitats(PeticioDadesCompletesDiscapacitats peticioDadesCompletesDiscapacitats) {
        return null;
    }

    @Override
    public RespostaDadesCompletes consultaDadesTitolMassiu(PeticioDadesCompletes peticioDadesCompletes) {
        return null;
    }

    @Override
    public RespostaDadesCompletesDiscapacitats consultaDadesTitolDiscapacitatsMassiu(PeticioDadesCompletesDiscapacitats peticioDadesCompletesDiscapacitats) {
        return null;
    }

    @Override
    public SOAPRequestServei<?, ?> getOperacio(String modalitat) throws Exception {
        switch (modalitat){
            case "TFN_VIGENCIA":
                return consultaVigenciaRequest;
            case "TFN_DADESCOMPLETES":
                return consultaDadesTitolRequest;
            default:
                throw new Exception("Modalitat no afegida: " + modalitat);
        }
    }

}
