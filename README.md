# conception_applications_reparties

## Mina Crapez


-------------
a faire :

- verifier si c'est bien ce qu'il faut faire au get
- corriger le bug du quit
- continuer les autres commandes

amelioration a faire :
- penser à faire les min et max de threads et continuer leur gestion 
- faire un readme propre
- faire une FTPexception
- mettre les bons messages d'erreurs avec les exception (try catch)
- sécuriser avec plusieurs utilisateur (synchronised, voir cours)

1er terminal :
```
make ServeurFTP;
cd src;
java ServeurFTP
```



2eme terminal :
``` 
ftp localhost 1024

pour des tests sur un client tcp :
nc localhost 1024
```