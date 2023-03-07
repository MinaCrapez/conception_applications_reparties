<html>
    <head>
        <title>Authentification</title>
        <meta charset = "utf-8">
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>

        <h1> Fiche de présence de la date : {ficheDePresence.date} </h1>

        <p> Etudiant : {etudiant.prenom} {etudiant.nom} </p>


        <ul>
            <%
            List<Ligne> lignes = (List<Ligne>) request.getAttribute("ligne");
            FeuilleDePresence ficheDePresence = (FeuilleDePresence) request.getAttribute("ficheDePresence");
            
            for(Lignes ligne : ficheDePresence.getLignes()) {
                out.print("<li> Heure : "+ ligne.getHeure());
                out.print("Matiere : " + ligne+getMatiere());

                out.print("<form action=\"suppressionLigne\" method=\"post\">");
                out.print("<input type=\"hidden\" name=\"idFeuille\" value=" +ficheDePresence.getId()+">");
                out.print("<input type=\"hidden\" name=\"idLigne\" value=" +ligne.getId()+">");
                out.print("<button type=\"submit\"> supprimer </button>");
                out.print("</form>");


                out.print("</li>");
            }	
            %>
        </ul>

        <form action="creationLigne" method="post">
			<div class="button_creation_ligne">
				<button type="submit"> Creer une ligne </button>
                <label for="matiere"> Matiere :</label>
                <input type="text" name="matiere" id="matiere" required>
                <input type="hidden" name="idFiche" value=${ficheDePresence.id}>
			</div>
		</form>
    </body>
</html>