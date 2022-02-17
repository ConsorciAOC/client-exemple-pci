package cat.aoc.client_pci;

import cat.aoc.client_pci.soap.Clients;
import cat.aoc.tfn.*;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TFNTest {
    private static final String SERVEI = "TFN";
    private static final String FINALITAT = "PROVES";

    @DisplayName("TFN_VIGENCIA")
    @Test
    void vigendcia() throws Exception {
        PeticionBuilder builder = new PeticionBuilderFromProperties("src\\main\\resources\\client.properties");
        Peticion peticion = builder.build(
                SERVEI,
                "TFN_VIGENCIA",
                FINALITAT,
                buildPeticioConsultaVigencia()
        );
        Respuesta response = Clients.TFN.getClient(Entorn.PRE).send(peticion);
        assertNotNull(response);
        RespostaConsultaVigencia specificResponse = (RespostaConsultaVigencia) response.getTransmisiones()
                .getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(specificResponse);
        assertNotNull(specificResponse.getResposta());
        assertEquals("Si", specificResponse.getResposta().getIndicadorVigencia());
    }

    @DisplayName("TFN_DADESCOMPLETES")
    @Test
    void dadescompletes() throws Exception {
        PeticionBuilder builder = new PeticionBuilderFromProperties("src\\main\\resources\\client.properties");
        Peticion peticion = builder.build(
                SERVEI,
                "TFN_DADESCOMPLETES",
                FINALITAT,
                buildPeticioDadesCompletes()
        );
        Respuesta response = Clients.TFN.getClient(Entorn.PRE).send(peticion);
        assertNotNull(response);
        RespostaDadesCompletes specificResponse = (RespostaDadesCompletes) response.getTransmisiones()
                .getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(specificResponse);
        assertNotNull(specificResponse.getResposta());
        assertEquals("080119372003", specificResponse.getResposta().getNumeroTitol());
        assertEquals("BBBB", specificResponse.getResposta().getNom());
        assertEquals("ZZZZ", specificResponse.getResposta().getPrimerCognom());
        assertEquals("HHHH", specificResponse.getResposta().getSegonCognom());
        assertEquals("08921", specificResponse.getResposta().getCodiPostal());
    }

    @DisplayName("TFN_DADESCOMPLETES_DIS")
    @Test
    void dadescompletesDis() throws Exception {
        PeticionBuilder builder = new PeticionBuilderFromProperties("src\\main\\resources\\client.properties");
        Peticion peticion = builder.build(
                SERVEI,
                "TFN_DADESCOMPLETES_DIS",
                FINALITAT,
                buildPeticioDadesCompletesDiscapacitats()
        );
        Respuesta response = Clients.TFN.getClient(Entorn.PRE).send(peticion);
        assertNotNull(response);
        RespostaDadesCompletesDiscapacitats specificResponse = (RespostaDadesCompletesDiscapacitats) response
                .getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(specificResponse);
        assertNotNull(specificResponse.getResposta());
        assertEquals("080119372003", specificResponse.getResposta().getNumeroTitol());
        assertEquals("BBBB", specificResponse.getResposta().getNom());
        assertEquals("ZZZZ", specificResponse.getResposta().getPrimerCognom());
        assertEquals("HHHH", specificResponse.getResposta().getSegonCognom());
        assertEquals("08921", specificResponse.getResposta().getCodiPostal());
    }

    private static PeticioConsultaVigencia buildPeticioConsultaVigencia(){
        PeticioConsultaVigencia dades = new PeticioConsultaVigencia();
        dades.setIdentificadorTitular(buildIdentificadorTitularPeticioConsultaVigencia());
        dades.setNumeroTitol("080119372003");
        dades.setData("01012022");
        return dades;
    }

    private static PeticioConsultaVigencia.IdentificadorTitular buildIdentificadorTitularPeticioConsultaVigencia(){
        PeticioConsultaVigencia.IdentificadorTitular identificadorTitular = new PeticioConsultaVigencia.IdentificadorTitular();
        identificadorTitular.setDocumentacio("38991311D");
        identificadorTitular.setTipusDocumentacio(TTipusDocumentacio.NIF);
        return identificadorTitular;
    }

    private static PeticioDadesCompletes buildPeticioDadesCompletes(){
        PeticioDadesCompletes dades = new PeticioDadesCompletes();
        dades.setIdentificadorTitular(buildIdentificadorTitular());
        return dades;
    }

    private static PeticioDadesCompletes.IdentificadorTitular buildIdentificadorTitular(){
        PeticioDadesCompletes.IdentificadorTitular identificadorTitular = new PeticioDadesCompletes.IdentificadorTitular();
        identificadorTitular.setDocumentacio("38991311D");
        identificadorTitular.setTipusDocumentacio(TTipusDocumentacio.NIF);
        return identificadorTitular;
    }

    private static PeticioDadesCompletesDiscapacitats buildPeticioDadesCompletesDiscapacitats(){
        PeticioDadesCompletesDiscapacitats dades = new PeticioDadesCompletesDiscapacitats();
        dades.setIdentificadorTitular(buildIdentificadorTitularDadesCompletesDiscapacitats());
        return dades;
    }

    private static PeticioDadesCompletesDiscapacitats.IdentificadorTitular buildIdentificadorTitularDadesCompletesDiscapacitats(){
        PeticioDadesCompletesDiscapacitats.IdentificadorTitular identificadorTitular = new PeticioDadesCompletesDiscapacitats.IdentificadorTitular();
        identificadorTitular.setDocumentacio("38991311D");
        identificadorTitular.setTipusDocumentacio(TTipusDocumentacio.NIF);
        return identificadorTitular;
    }


}
