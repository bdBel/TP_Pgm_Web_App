<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/afficherPanier.css">
    <title>Votre Panier</title>
</head>
<body>
<h1>Votre Panier</h1>

<c:choose>
    <c:when test="${empty animaux}">
        <p>Votre panier est vide.</p>
    </c:when>
    <c:otherwise>
        <div class="table-wrapper">
            <table class="fl-table">
                <thead>
                <tr>
                    <th>Nom</th>
                    <th>Sexe</th>
                    <th>Prix</th>
                    <th>Quantit�</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="total" value="0"/>
                <c:forEach var="animal" items="${animaux}">
                    <c:set var="quantite" value="${animal.quantite}"/>
                    <c:set var="animalTotal" value="${animal.prixAnimal * quantite}"/>
                    <c:set var="total" value="${total + animalTotal}"/>

                    <tr>
                        <td>${animal.nom}</td>
                        <td>${animal.sexe}</td>
                        <td><fmt:formatNumber value="${animal.prixAnimal}" type="number" maxFractionDigits="2"/></td>
                        <td>${quantite}</td>
                        <td><fmt:formatNumber value="${animalTotal}" type="number" maxFractionDigits="2"/></td>
                    </tr>
                </c:forEach>

                <c:set var="tps" value="${total * 0.05}"/>
                <c:set var="tvq" value="${total * 0.09975}"/>
                <c:set var="totauxAvTaxe" value="${total}"/>
                <c:set var="totauxApTaxe" value="${total + tps + tvq}"/>

                <tr>
                    <td style="background-color: rgba(71, 147, 227, 1)" colspan="3"></td>
                    <td>Total</td>
                    <td><fmt:formatNumber value="${totauxAvTaxe}" type="number" maxFractionDigits="2" /></td>
                </tr>

                <tr>
                    <td style="background-color: rgba(71, 147, 227, 1)" colspan="3"></td>
                    <td>TPS 5%</td>
                    <td><fmt:formatNumber value="${tps}" type="number" maxFractionDigits="2" /></td>
                </tr>
                <tr>
                    <td style="background-color: rgba(71, 147, 227, 1)" colspan="3"></td>
                    <td>TVQ 9,9975%</td>
                    <td><fmt:formatNumber value="${tvq}" type="number" maxFractionDigits="2" /></td>
                </tr>
                <tr>
                    <td style="background-color: rgba(71, 147, 227, 1)" colspan="3"></td>
                    <td>Total Apres Taxe</td>
                    <td><fmt:formatNumber value="${totauxApTaxe}" type="number" maxFractionDigits="2" /></td>
                </tr>

                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>


<a href="checkout.jsp?total=${totauxApTaxe}">Payer votre facture ICI</a>



</body>
</html>
