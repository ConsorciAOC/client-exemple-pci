package cat.aoc.client_pci.samples.serveis.representa;

import generated.representa.*;

interface PeticionBuilderRepresentaConsultarRepresentacio {
    static ConsultarRepresentacio buildConsultarRepresentacio() {
        ConsultarRepresentacio peticio = new ConsultarRepresentacio();
        ConsultarRepresentacio.ConsultaRepresentacio consultaRepresentacio = new ConsultarRepresentacio.ConsultaRepresentacio();
        consultaRepresentacio.setIdentificadorLegal("201900000377");
        Solicitant solicitant = new Solicitant();
        Persona persona = new Persona();
        persona.setTipusDocumentIdentificatiu("NIF");
        persona.setValorDocumentIdentificatiu("46773080G");
        persona.setTipusPersona(TipusPersona.FISICA);
        solicitant.setPersona(persona);
        Administracio administracio = new Administracio();
        administracio.setCodi("12345");
        solicitant.setAdministracio(administracio);
        consultaRepresentacio.setSolicitant(solicitant);
        peticio.setConsultaRepresentacio(consultaRepresentacio);
        return peticio;
    }

}
