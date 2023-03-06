<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
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

        <p> liste des fiches de présences :</p>
        <c:if test = "${feuillePres.length > 0}">
            <c:forEach items="${feuillesPres}" var="feuille">

                <p> Feuille de présence numéro  <c:out value="${feuillePres.id}" />

                <form action="affichageFichePresence" method="post"> 
                    <input type="hidden" name="mailEtudiant" value=${etudiant.email}>
                    <input type="hidden" name="mdpEtudiant" value=${etudiant.mdp}>
                    <input type="hidden" name="idFeuille" value=${feuillePres.id}>
                    <button type="submit"> afficher </button>
                </form>
<form action="suppressionFichePresence" method="post">
                    <input type="hidden" name="mailEtudiant" value=${etudiant.email}>
                    <input type="hidden" name="mdpEtudiant" value=${etudiant.mdp}>
                    <input type="hidden" name="idFeuille" value=${feuillePres.id}>
                    <button type="submit"> supprimer </button>
                </form>

            </c:forEach>
        </c:if>

        <form action="creationFeuillePresence" method="post">
            <div class="button_creation_feuille">
                <button type="submit"> Creer une fiche de présence </button>
                <input type="hidden" name="mailEtudiant" value=${etudiant.email}>
                <input type="hidden" name="mdpEtudiant" value=${etudiant.mdp}>
            </div>
        </form>


    </body>