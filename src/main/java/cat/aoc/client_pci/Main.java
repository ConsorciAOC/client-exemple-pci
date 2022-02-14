package cat.aoc.client_pci;

import cat.aoc.client_pci.serveis.ServiceClient;
import cat.aoc.client_pci.serveis.tfn.clients.SOAPClientTFN;
import cat.aoc.tfn.PeticioDadesCompletes;
import cat.aoc.tfn.RespostaDadesCompletes;
import cat.aoc.tfn.TTipusDocumentacio;
import lombok.extern.slf4j.Slf4j;
import net.aocat.padro.PeticionDatosTitular;

@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {

        SOAPClientTFN client = (SOAPClientTFN) ServiceClient.TFN.getClient(Entorn.PRE);
        RespostaDadesCompletes respostaDadesCompletes = client.consultaDadesTitol(buildPeticioDadesCompletes());
        System.out.println(respostaDadesCompletes.getResultat());
        System.out.println(respostaDadesCompletes.getResultat().getDescripcio());
        System.out.println(respostaDadesCompletes.getResposta().getCodiPostal());

        /*
        SOAPClientPADRO client = (SOAPClientPADRO) ServiceClient.PADRO.getClient(Entorn.PRE);
        RespuestaDatosTitular respuestaDatosTitular = client.consultaTitular(buildPeticioDatosTitular());
        System.out.println(respuestaDatosTitular.getAny());
        System.out.println(respuestaDatosTitular.getCodigoMunicipio());
        */

    }

    private static PeticionDatosTitular buildPeticioDatosTitular() {
        PeticionDatosTitular peticionDatosTitular = new PeticionDatosTitular();
        peticionDatosTitular.setCodigoMunicipio("019");
        peticionDatosTitular.setCodigoProvincia("08");
        peticionDatosTitular.setDocumentacion("21777952B");
        peticionDatosTitular.setIdescat(0);
        peticionDatosTitular.setTipoDocumentacion(1);
        peticionDatosTitular.setNumExpediente("TEST");
        return peticionDatosTitular;
    }


    private static PeticioDadesCompletes buildPeticioDadesCompletes(){
        PeticioDadesCompletes peticioDadesCompletes = new PeticioDadesCompletes();
        peticioDadesCompletes.setIdentificadorTitular(buildIdentificadorTitular());
        return peticioDadesCompletes;
    }

    private static PeticioDadesCompletes.IdentificadorTitular buildIdentificadorTitular(){
        PeticioDadesCompletes.IdentificadorTitular identificadorTitular = new PeticioDadesCompletes.IdentificadorTitular();
        identificadorTitular.setDocumentacio("38991311D");
        identificadorTitular.setTipusDocumentacio(TTipusDocumentacio.NIF);
        return identificadorTitular;
    }

}
