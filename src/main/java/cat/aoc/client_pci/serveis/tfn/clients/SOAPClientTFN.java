package cat.aoc.client_pci.serveis.tfn.clients;

import cat.aoc.client_pci.SOAPClient;
import cat.aoc.tfn.*;

public interface SOAPClientTFN extends SOAPClient {

    RespostaConsultaVigencia consultaVigencia(PeticioConsultaVigencia peticioConsultaVigencia);

    RespostaDadesCompletes consultaDadesTitol(PeticioDadesCompletes peticioDadesCompletes);

    RespostaDadesCompletesDiscapacitats consultaDadesTitolDiscapacitats(PeticioDadesCompletesDiscapacitats peticioDadesCompletesDiscapacitats);

    RespostaDadesCompletes consultaDadesTitolMassiu(PeticioDadesCompletes peticioDadesCompletes);

    RespostaDadesCompletesDiscapacitats consultaDadesTitolDiscapacitatsMassiu(PeticioDadesCompletesDiscapacitats peticioDadesCompletesDiscapacitats);

}
