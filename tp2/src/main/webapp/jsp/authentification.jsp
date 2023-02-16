<html>
<body>

<h1> Hello, please identify yourself </h1>

<p> ceci est un test de formulaire: 
</p>

<form action="connecte.jsp">
<div class="form-nom">
	<label for="nom"> Nom :</label>
		<input type="text" name="nom" id="nom" required>
</div>
<div class="form-prenom">
	<label for="prenom"> prenom :</label>
		<input type="text" name="prenom" id="prenom" required>
</div>
	<input type="submit" value ="se connecter">
</form>

<form action="creationCompte.jsp">
	<div class="creationCompte">
		<input type="submit" value ="creer un compte">
	</div>
</form>

</body>
</html>