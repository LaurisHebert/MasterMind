#MasterMind

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)

Mastermind crée pour le projet 3 du parcours de Développeur d'application JAVA
https://openclassrooms.com/fr/projects/140/assignment

##Pré-requis:
- Java 8 minimum
- Ajouter le dossier "lib" dans le ClassPath


##Démarrage 
- Configuer le jeu à partir du fichier resources/config.properties
 
  - "sizeOfLineToFind" : quantité de nombre de la combinaison du mastermind
 
  - "maximumValue" : nombre maximum d'un élément de la combinaison
  
  - "minimalValue" : nombre minimum d'un élément de la combinaison
  
  - "maximumOfRounds" : nombre maximum de tours
  
  - "devMod" : activer (=true) ou non (=false) le mode dévelloppeur
  
- Executer la methode Main :
>`java -classpath ./out/production/MasterMind:./lib/log4j-api-2.12.1.jar:./lib/log4j-core-2.12.1.jar com.pda.games.Main`


Version 1.00 (Stable)

Lauris Hebert alias @Pandanam