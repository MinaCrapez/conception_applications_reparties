<html>
    <head>
        <title>Authentification</title>
        <meta charset = "utf-8">
        <link rel="stylesheet" href="css/style.css">
    </head>
    
    <body>
    
        <h1> Bonjour, Bienvenue sur ce site de gestion pour les etudiants </h1>
        
        <p> Veuillez vous identifier :</p>
        
        <div class=error>
            <p>${error}</p>
        </div>
        
        <form action="connexionCompte">
        
            <div class="form_email">
                <label for="email"> Adresse email :</label>
                <input type="text" name="email" id="email" required>
            </div>
            
            <div class="form_mdp">
                <label for="mdp"> Mot de passe :</label>
                <input type="password" name="mdp" id="mdp" required>
            </div>
        
            <button type="submit"> Connection </button>
            
        </form>
        
        <form action="creationCompte">
            <div class="button_creation_compte">
                <button type="submit"> Creer un compte</button>
            </div>
        </form>
        
        
    </body>
</html>