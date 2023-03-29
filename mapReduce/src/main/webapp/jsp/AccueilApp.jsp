<html>
    <head>
        <title>Authentification</title>
        <meta charset = "utf-8">
        <link rel="stylesheet" href="css/style.css">
    </head>
	
	<body>
		<h3> Compter un mot dans un fichier </h3>
		
		<form action="comptageDuMot" method="post">
		
			<label for="fichier">Choix du fichier a analyser :</label>
			<input type="file" id="fichier" name="fichier" accept=".doc, .txt" required>
       		
       		<label for="mot">Choix du mot à compter :</label>
       		<input type="text" id="mot" name="mot" required>
       		
       		 <button type="submit"> Compter ! </button>
       </form>
       
       <p> Résultat : Il y a ${occurence} occurence(s) du mot ${mot}.
	</body> 
       
</html>