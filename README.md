# conception_applications_reparties

## Mina Crapez


-------------

a faire : 

- le serveur doit pouvoir permettre de s'identifier et de rentrer son mdp
bug au while (true), le premier print se fait sans soucis mais on a toujours le retour "invalid command" en voulant entrer du texte apres. 


amelioration a faire :
- penser à faire les min et max de threads et continuer leur gestion (en commentaire pour le moment)
- faire un readme propre
- mettre les mess derreur d'authentification et mdp
- ameliorer la map de MDP pq la cest moche en brut comme ca 
- faire une FTPexception

1er terminal :
```
javac ServeurFTP.java

java ServeurFTP 1024 
OU
java ServeurFTP
```

2eme terminal :
``` 
ftp localhost 1024
```