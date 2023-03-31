<html>
    <head>
        <title>Authentification</title>
        <meta charset = "utf-8">
        <link rel="stylesheet" href="css/style.css">
    </head>
	
	<body>
		<h3> Compter un mot dans un fichier </h3>
		
		<h4>Etape 1 - creation du systeme : </h4>
		<form action="creationDySysteme" method="post">
			 <button type="submit"> créer </button>
		</form>
		
		<h4>Etape 2 - Analyse du fichier : </h4>
		<form action="analyseFichier" method="post">
			<label for="fichier">Choix du fichier a analyser :</label>
			<input type="file" id="fichier" name="fichier" accept=".doc, .txt" required>
			<button type="submit"> analyser le fichier </button>
		</form>
		
		<h4>Etape 3 - Compter un mot : </h4>
		<form action="comptageDuMot" method="post">
			<label for="mot">Choix du mot à compter :</label>
       		<input type="text" id="mot" name="mot" required>
       		
       		 <button type="submit"> Compter ! </button>
		</form>
       
       <p> Résultat : Il y a ${occurence} occurence(s) du mot ${mot}.
	</body> 
       
</html>
