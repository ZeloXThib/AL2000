# Projet AL2000 

## Auteurs :
- Adrien Chapurlat
- Lilou Bouvier
- Alexis Ethève
- Louis Lemay
- Enzo Nulli


## Organisation du code source



Le code source de ce projet est organisé de la manière suivante :

- **src** : Ce répertoire contient tous les fichiers sources de l'application.

      - Controleur : Ce répertoire contient les fichiers du controleur.

      - Modele : Ce répertoire contient les fichiers qui définissent les classes qui implémente les différentes fonctions de l'application.

      - Vue : Ce répertoire contient les fichiers de l'interface grapgique de l'application.
  
- **res** : Ce répertoire contient les ressources nécessaires à l'application, telles que les fichiers de configuration, les images, etc.

- **database** : Ce répertoire contient les ressources liées à la base de données. (DAO)

- **AL2000** : Ce fichier contient le fichier main de l'application.


## Compilation et exécution 
 **Initialisation de la base de donnée** : Executer `python3 initDb.py` dans le dossier `database`.
 **Execution** : Executer `java -jar PROJET_INTEGRATEUR.jar`