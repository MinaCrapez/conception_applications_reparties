<%@ page import= "com.tp2.gestionEtudiant.model.FeuilleDePresence"%>
<%@ page import= "com.tp2.gestionEtudiant.model.Etudiant"%>
<%@ page import= "java.util.List"%>
<html>
	<head>
		<title>Accueil</title>
		<meta charset = "utf-8">
		<link rel="stylesheet" href="css/style.css">
	</head>
	
	<body>
	
		<h1> Gestion des feuilles de présences</h1>
		
		
		<h3>Bonjour ${etudiant.nom} ${etudiant.prenom} ! </h3>
		
		<p>Bienvenue sur votre espace de gestion. <p>
		
			<table>
				<thead>
					<tr>
						<th colspan="3">Liste des feuilles de présence </th>
					</tr>
				</thead>
				<tbody>
			
				<%
				Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");
		
				for(FeuilleDePresence fiche : etudiant.getFeuilles()) {
					out.print("<tr><td> feuille de presence du "+ fiche.getMoisAnnee()+"</td>");
		
					out.print("<td> <form action="affichageFichePresence" method="post">");
					out.print("<input type="hidden" name="idFeuille" value=" +fiche.getId()+">");
					out.print("<button type="submit"> afficher </button>");
					out.print("</form> </td>");
		
					out.print(" <td> <form action="suppressionFichePresence" method="post">");
					out.print("<input type="hidden" name="mailEtudiant" value=" +etudiant.getEmail()+">");
					out.print("<input type="hidden" name="mdpEtudiant" value=" +etudiant.getMdp()+">");
					out.print("<input type="hidden" name="idFeuille" value=" +fiche.getId()+">");
					out.print("<button type="submit"> supprimer </button>");
					out.print("</form> </td>");
					out.print("</tr>");
		
		
				}
					%>
					</tbody>
				</table>
		
				<form action="creationFeuillePresence" method="post">
					<div class="button_creation_feuille">
						<button type="submit"> Creer une fiche de présence </button>
						<input type="hidden" name="mailEtudiant" value=${etudiant.email}>
						<input type="hidden" name="mdpEtudiant" value=${etudiant.mdp}>
					</div>
				</form>
			
		
		
	</body>
	
</html>