# client-exemple-pci
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

Client d'exemple pels integradors de la PCI.

El seg�ent projecte presenta un client SOAP d'exemple pels integradors que permet consumir els diferents serveis de l'AOC.
Per m�s informaci� sobre la integraci� podeu consultar la documentaci� gen�rica de la PCI (Plataforma de Col�laboraci� Interadministrativa):
https://github.com/ConsorciAOC/PCI

Addicionalment, per integrar-se amb els diferents serveis del Consorci, podreu consultar la documentaci� espec�fica de cada un accedint al projecte concret que trobareu a:
https://consorciaoc.github.io/


## Requisits
### Java
Per poder compilar i utilitzar el codi ser� necessari utilitzar la JDK 17 (o superior) de java.
- jdk17: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
### Gradle
El projecte incorpora un wrapper de gradle (gradle/wrapper) i uns fitxers executables
(./gradlew) per poder fer servir el gestor sense necessitat de tenir-lo instal�lat.

Si es desitja instal�lar gradle en local: https://gradle.org/install/

### Dades CAOC
Per consumir els serveis del CAOC necessitareu haver estat donats d'alta als nostres entorns pr�viament.
Podeu sol�licitar l'alta a trav�s del nostre portal: https://www.aoc.cat/serveis/
Amb l'alta se us atorgaran permisos per consumir els serveis demanats i necessitareu:
- Codi del servei
- Codi de la modalitat
- Codi de la finalitat
- El certificat digital amb el qual signareu les peticions XML (XMLDSIG)
- Executar el codi des d'un ordinador que la seva IP hagi estat habilitada.

## Primeres passes
### Configurar el certificat
Per tal que el client funcioni, necessitareu configurar el vostre certificat de signatura al fitxer src/main/resources/keystore.properties
- org.apache.ws.security.crypto.provider: Prove�dor usat per crear inst�ncies criptogr�fiques.
- org.apache.wss4j.crypto.merlin.keystore.alias: �lies del certificat digital.
- org.apache.ws.security.crypto.merlin.keystore.type: Format del certificat.
- org.apache.ws.security.crypto.merlin.keystore.password: Contrasenya del certificat.
- org.apache.ws.security.crypto.merlin.keystore.file: Ruta del fitxer.

### Generaci� de codi JAXB
El projecte incorpora una s�rie d'XSD's tant de la PCI (part gen�rica) com dels diferents serveis (part espec�fica) sota el directori:
src/main/resources/xsd

Per tal de convertir els esquemes a classes java es fan servir llibreries de JAXB i s'ha configurat una tasca gradle anomenada jaxb que realitza el proc�s autom�ticament.
Per executar la tasca:
```bash
gradlew jaxb
```

La compilaci� del projecte java dep�n d'aquesta tasca, per tant, cada cop que es necessiti compilar quelcom s'executar� abans la taca jaxb mencionada.
Si desitgeu eliminar aquesta depend�ncia per agilitzar les build, podeu comentar la seg�ent l�nia al fitxer build.gradle:
```groovy
compileJava.dependsOn jaxb
```


### Exemple d'�s
```java
// Obtenir el client del servei desitjat
ClientAOC client = Servei.ENOTUM.getClient(Entorn.PRE);

// Construir una petici� d'exemple
Peticion peticion = Servei.ENOTUM.getPeticion(ENOTUMOperacio.CERCA, Finalitat.PROVES);
        
// Enviar una petici� d'exemple
client.send(ENOTUMOperacio.CERCA, peticion);
```

### M�s exemples
Podreu trobar m�s exemples dels diferents serveis sota l'apartat de test del projecte.
