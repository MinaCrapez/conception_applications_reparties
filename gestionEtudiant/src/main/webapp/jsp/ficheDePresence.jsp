<%@ page import= "com.tp2.gestionEtudiant.model.FeuilleDePresence"%>
<%@ page import= "com.tp2.gestionEtudiant.model.Etudiant"%>
<%@ page import= "com.tp2.gestionEtudiant.model.Ligne"%>
<%@ page import= "java.util.List"%>

<script type="text/javascript"> 

function changeSignature() {
	$('#nouvSignatureEtudiant').val($'#signatureEtudiant');
	$('#nouvSignatureProf').val($'#signatureProfesseur');
    document.getElementById(form_signature).submit();
}
</script>
<html>
    <head>
        <title>Authentification</title>
        <meta charset = "utf-8">
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>

        <h1> Fiche de prÃ©sence de la date : ${ficheDePresence.moisAnnee} </h1>

        <p> Etudiant : ${etudiant.prenom} ${etudiant.nom} </p>


        <ul>
            <%
            Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");
            List<Ligne> lignes = (List<Ligne>) request.getAttribute("lignes");
            FeuilleDePresence ficheDePresence = (FeuilleDePresence) request.getAttribute("ficheDePresence");
            
            for(Ligne ligne : lignes) {
                out.print("<li> Heure Debut : "+ ligne.getHeureDebut());
                out.print("Heure fin : " + ligne.getHeureFin());
                out.print("Matiere : " + ligne.getMatiere());
                out.print("<input type=\"checkbox\"value="+ligne.getSignatureEtudiant()+"id=\"signatureEtudiant\"  onClick=\"changeSignature();\" > ");
                out.print("<label for=\"signature etudiant\"> Signature de l'etudiant </label>");
                out.print("<input type=\"checkbox\" value="+ligne.getSignatureProf()+ "id=\"signatureProfesseur\" onClick=\"changeSignature();\"> ");
                out.print("<label for=\"signature professeur\"> Signature du professeur </label>");

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
                <label for="matiere"> Jour :</label>
                <input type="text" name="jour" id="jour" required>
				<label for="heureDebut"> heure de debut :</label>
                <input type="text" name="heureDebut" id="heureDebut" required>
                <label for="heureFin"> heure de fin :</label>
                <input type="text" name="heureFin" id="heureFin" required>
                
                
                <input type="hidden" name="idFiche" value=${ficheDePresence.id}>
                <input type="hidden" name="mailEtudiant" value=${etudiant.email}>
				<input type="hidden" name="mdpEtudiant" value=${etudiant.mdp}>
         
            </div>
        </form>
        
        
         <form action="changementSignature" id="form_signature" method="post">
         	<input type="hidden" name="idFiche" value=${ficheDePresence.id}>
            <input type="hidden" name="mailEtudiant" value=${etudiant.email}>
			<input type="hidden" name="mdpEtudiant" value=${etudiant.mdp}>
			<input type="hidden" name="nouvSignatureEtudiant" value=false>
			<input type="hidden" name="nouvSignatureProf" value=false>
         </form>
    </body>
</html>