<%@ page import= "com.tp2.gestionEtudiant.model.FeuilleDePresence"%>
<%@ page import= "com.tp2.gestionEtudiant.model.Etudiant"%>
<%@ page import= "com.tp2.gestionEtudiant.model.Ligne"%>
<%@ page import= "java.util.List"%>
<html>
    <head>
        <title>Authentification</title>
        <meta charset = "utf-8">
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>

        <h1> Fiche de presence de la date : ${ficheDePresence.moisAnnee} </h1>

        <p> Etudiant : ${etudiant.prenom} ${etudiant.nom} </p>


        <ul>
            <%
            Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");
            List<Ligne> lignes = (List<Ligne>) request.getAttribute("lignes");
            FeuilleDePresence ficheDePresence = (FeuilleDePresence) request.getAttribute("ficheDePresence");
            
            for(Ligne ligne : lignes) {
                out.print("<li> Heure : "+ ligne.getHeure());
                out.print("Matiere : " + ligne.getMatiere());

                out.print("<form action=\"suppressionLigne\" method=\"post\">");
                out.print("<input type=\"hidden\" name=\"idFeuille\" value=" +ficheDePresence.getId()+">");
                out.print("<input type=\"hidden\" name=\"idLigne\" value=" +ligne.getId()+">");
                out.print("<input type=\"hidden\" name=\"mailEtudiant\" value=" +etudiant.getEmail()+">");
    			out.print("<input type=\"hidden\" name=\"mdpEtudiant\" value=" +etudiant.getMdp()+">");
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
                <input type="hidden" name="mailEtudiant" value=${etudiant.email}>
				<input type="hidden" name="mdpEtudiant" value=${etudiant.mdp}>
         
            </div>
        </form>
    </body>
</html>