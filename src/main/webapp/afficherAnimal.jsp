<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cartes avec animaux</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/afficherAnimal.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div id="basketCounterContainer">
    <a style="color: black;" href="AfficherPanierServlet">Panier</a>
    <i class="fas fa-shopping-cart"></i>
    <span id="basketCounter">0</span>
</div>

<br>
<br>

<div id="dogCardsContainer">


    <c:forEach var="animal" items="${animaux}">
        <div class="card">

            <img class="card-img-top" src="${animal.imageUrl}"/>
            <p class="descript-dest">
                <c:out value="${animal.nom}"/>
                (<c:out value="${animal.sexe}"/>) <br>
                Prix: <c:out value="${animal.prixAnimal}"/>
            </p>

            
                    <!--Lorsque que l'utilisateur appuie sur ajouter ca envoie l'information a la servlet ajouteranimal-->
                    <form action="AjouterAnimalServlet" method="post">
                        <input type="hidden" name="animalId" value="${animal.id}"/>
                        <b>Quantity: </b><input type="number" name="qty" SIZE="3" value=1 min="0">
                        <button class="btnAnimal" type="submit" name="ajouter">Ajouter au panier</button>
                    </form>

        </div>
    </c:forEach>


    <script src="${pageContext.request.contextPath}/javaScript/afficherAnimal.js"></script>
</div>
</body>
</html>
