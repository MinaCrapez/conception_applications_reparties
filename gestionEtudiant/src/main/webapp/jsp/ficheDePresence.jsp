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

        <p> Etudiant : ${ficheDePresence.etudiant.prenom} ${ficheDePresence.etudiant.nom} </p>
        
        <table>
    		<thead>
        		<tr>
            		<th colspan="8">Fiche de prÃ©sence de la date : ${ficheDePresence.moisAnnee}</th>
        		</tr>
    		</thead>
    		<tbody>
        
            <%
            FeuilleDePresence ficheDePresence = (FeuilleDePresence) request.getAttribute("ficheDePresence");
            
            for(Ligne ligne : ficheDePresence.getLignes()) {
                out.print("<tr> <td>Heure Debut : "+ ligne.getHeureDebut()+"</td>");
                out.print("<td> Heure fin : " + ligne.getHeureFin()+"</td>");
                out.print("<td> Matiere : " + ligne.getMatiere()+"</td>");
                out.print("<td> <label for=\"signature etudiant\"> Signature de l'etudiant </label>");
                out.print("<td> <input type=\"checkbox\"value="+ligne.getSignatureEtudiant()+"id=\"signatureEtudiant\" > </td>");
                out.print("<td> <label for=\"signature professeur\"> Signature du professeur </label>");
                out.print("<td> <input type=\"checkbox\" value="+ligne.getSignatureProf()+ "id=\"signatureProfesseur\"> </td>");

                out.print("<td> <form action=\"suppressionLigne\" method=\"post\">");
                out.print("<input type=\"hidden\" name=\"idFeuille\" value=" +ficheDePresence.getId()+">");
                out.print("<input type=\"hidden\" name=\"idLigne\" value=" +ligne.getId()+">");
                out.print("<button type=\"submit\"> supprimer </button>");
                out.print("</form> </td>");


                out.print("</tr>");
            }    
            %>
            </tbody>
		</table>

        <form action="creationLigne" method="post">
            <div class="button_creation_ligne">
                <button type="submit"> Creer une ligne </button>
                <label for="matiere"> Matiere :</label>
                <input type="text" name="matiere" id="matiere" required>
                <label for="matiere"> Jour :</label>
                <input type="text" name="jour" id="jour" required>
				<label for="heureDebut"> heure de debut :</label>
                <input type="text" name="heureDebut" id="heureDebut" required>
                <label for="heureFin"> heure de fin :</label>
                <input type="text" name="heureFin" id="heureFin" required>
                
                
                <input type="hidden" name="idFiche" value=${ficheDePresence.id}>   
         
            </div>
        </form>
        
    </body>
</html>