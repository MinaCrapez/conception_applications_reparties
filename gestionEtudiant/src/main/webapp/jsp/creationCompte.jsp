<html>

    <head>
        <title>Creation de compte</title>
        <meta charset = "utf-8">
        <link rel="stylesheet" href="css/style.css">
    </head>
    
    <body>
    
        <h1> Creation de compte </h1>
        
        <div class="error">
            <p>${error}</p>
        </div>
        
        <form action="enregistrerCompte" method="post">
        
            <div class="element_form">
                <label for="nom"> Nom :</label>
                <input type="text" name="nom" id="nom" required>
            </div>
            
            <div class="element_form">
                <label for="prenom"> Prenom :</label>
                <input type="text" name="prenom" id="prenom" required>
            </div>
            
            <div class="element_form">
                <label for="email"> Adresse email :</label>
                <input type="text" name="email" id="email" required>
            </div>
            
            <div class="element_form">
                <label for="mdp"> Mot de passe :</label>
                <input type="password" name="mdp" id="mdp" required>
            </div>
            
            <div class="button">
                <button type ="submit"> Creer le compte</button>
            </div>
           
        </form>
    </body>
    
</html>