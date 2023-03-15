# client-exemple-pci
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

> Client d'exemple pels integradors de la PCI.

El següent projecte presenta un client SOAP d'exemple pels integradors que permet consumir els diferents serveis de l'AOC.
Per a més informació sobre la integració podeu consultar la documentació genèrica de la PCI (Plataforma de Col·laboració Interadministrativa):
https://github.com/ConsorciAOC/PCI

Addicionalment, per integrar-se amb els diferents serveis del Consorci, podreu consultar la documentació específica de cada un accedint al projecte concret que trobareu a:
https://consorciaoc.github.io/


## Requisits
### Java
Per poder compilar i utilitzar el codi serà necessari utilitzar la JDK 17 (o superior) de java.
- jdk17: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
### Gradle
El projecte incorpora un wrapper de gradle (`gradle/wrapper`) i uns fitxers executables
(`./gradlew`) per poder fer servir el gestor sense necessitat de tenir-lo instal·lat.

Si es desitja instal·lar gradle en local: https://gradle.org/install/

### Dades CAOC
Per consumir els serveis del CAOC necessitareu haver estat donats d'alta als nostres entorns prèviament.
Podeu sol·licitar l'alta a través del nostre portal: https://www.aoc.cat/serveis/

Amb l'alta se us assignaran permisos per consumir els serveis demanats i necessitareu:
- Codi del servei
- Codi de la modalitat
- Codi de la finalitat
- El certificat digital amb el qual signareu les peticions XML (XMLDSIG)
- Executar el codi des d'un ordinador que la seva IP hagi estat habilitada.

## Primeres passes
### Configurar el certificat
Per tal que el client funcioni, necessitareu configurar el vostre certificat de signatura al fitxer `src/main/resources/keystore.properties`
- `org.apache.ws.security.crypto.provider`: Proveïdor usat per crear instàncies criptogràfiques.
- `org.apache.wss4j.crypto.merlin.keystore.alias`: Àlies del certificat digital.
- `org.apache.ws.security.crypto.merlin.keystore.type`: Format del certificat.
- `org.apache.ws.security.crypto.merlin.keystore.password`: Contrasenya del certificat.
- `org.apache.ws.security.crypto.merlin.keystore.file`: Ruta del fitxer.

### Generació de codi JAXB
El projecte incorpora una sèrie d'XSD's tant de la PCI (part genèrica) com dels diferents serveis (part específica) sota el directori:
`src/main/resources/xsd`

Per tal de convertir els esquemes a classes java es fan servir llibreries de JAXB i s'ha configurat una tasca gradle anomenada `jaxb` que realitza el procés automàticament.
Per executar la tasca:
```bash
gradlew jaxb
```

La compilació del projecte java depèn d'aquesta tasca, per tant, cada cop que es necessiti compilar quelcom s'executarà abans la taca jaxb mencionada.
Si desitgeu eliminar aquesta dependència per agilitzar les build, podeu comentar la següent línia al fitxer build.gradle:
```groovy
compileJava.dependsOn jaxb
```


### Exemple d'ús
```java
// Obtenir el client del servei desitjat
ClientAOC client = Servei.ENOTUM.getClient(Entorn.PRE);

// Construir una petició d'exemple
Peticion peticion = Servei.ENOTUM.getPeticion(ENOTUMOperacio.CERCA, Finalitat.PROVES);
        
// Enviar una petició d'exemple
client.send(ENOTUMOperacio.CERCA, peticion);
```

### Més exemples
Podreu trobar més exemples dels diferents serveis sota l'apartat de test del projecte.
