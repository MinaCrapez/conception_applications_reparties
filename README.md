# conception_applications_reparties

## Mina Crapez


---------------
a faire :
- multithread et protection


### <u>Pour lancer le projet :</u>

- dans la *racine du projet*, dans un premier terminal :
```
make all
```
Make all va permettre de compiler le projet.

```
cd src
javac ServerFTP
```
permetttra de lancer le serveur.

- dans un second terminal :
``` 
ftp localhost 1024

```

### <u>Pour créer la documentation du projet :</u>

- dans la *racine du projet*, dans un premier terminal :
```
make doc
```

### <u>Pour clean le projet :</u>

- dans la *racine du projet*, dans un premier terminal :
```
make clean
```

clean permettra de supprimer les .class et la documentation.


--------------------


### <u>Réponse aux questions :</u>

**Présentation du projet**

Un serveur FTP est un serveur permettant le transfert de fichier. Dans ce projet différente commande ont dû être implémenté comme :
- l'authentification (où les mots de passe et username sont dans le fichier *src/tool/identification.java*)
- le get et put pour récupérer des fichiers en local ou distant et les envoyer en local ou distant sur des dépots individuels des utilisateurs.
- cd et dir pour afficher le répertoire ou le modifier afin de se déplacer dans le dépot distant.

Toutes les commandes demandées ont été implementées.

*Note : il reste encore à gérer le multithread pour permettre à plusieurs utilisateurs de se connecter simultanément de manière sécurisé.*

-------------

**Comment ajouter une nouvelle commande ?**

Pour ajouter une fonction x il suffit d'ajouter du code dans **ServerFTP.java** :

- Dans la méthode start :

    ajout d'un 
    ```java
    else if(commandAsk.startsWith(x)) {
    commandX(String message);
    }
    ```

- créer une méthode *commandX(String message)* qui va agir sur le serveur en fonction de ce que fait la commande. Cette fonction devra renvoyer à notre élément dos un code retour et un message correspondant au code retour de serveurFTP.

Pour savoir quel code utilisé vous pouvez vous referer au [lien suivant](https://fr.wikipedia.org/wiki/Liste_des_commandes_ftp).

<u>Exemple :</u> si nous voulions ajouter la commande TYPE on ferait :

Dans **ServerFTP** :
```java
public void start() {
...
else if(commandAsk.startsWith("TYPE")) {
    commandType(String message);
}
...

public void commandType(String message) {
    // affiche le mode courant de transfert
    dos.writeBytes("200 Command okay.\r\n");
}
```

