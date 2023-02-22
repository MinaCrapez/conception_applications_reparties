<html>
CREATION COMPTE

<form action="enregistrerCompte" method="post">
        <label for="nom"> Nom :</label>
        <input type="text" name="nom" id="nom" required>
        <label for="prenom"> prenom :</label>
        <input type="text" name="prenom" id="prenom" required>
        <label for="email"> email :</label>
        <input type="text" name="email" id="email" required>
        <label for="mdp"> mot de passe :</label>
        <input type="text" name="mdp" id="mdp" required>

        <div class="button">
            <button type ="submit"> creer le compte</button>
        </div>
</form>
</html>