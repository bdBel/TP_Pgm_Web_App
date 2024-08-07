<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cartes avec animaux</title>

        <style>
        body {
            padding: 20px; /* Adds padding around the entire page content */
            background-color: #f6f7f8; /* Light grey background for a soft contrast */
        }

            #dogCardsContainer {
                display: flex; /* Enables Flexbox for the container */
                flex-wrap: wrap; /* Allows items to wrap onto multiple lines */
                justify-content: space-between; /* Ensures equal spacing between cards */
            }

            .card {
                border: 1px solid #ddd; /* Softer border color */
                border-radius: 10px; /* Rounded corners for a softer look */
                box-shadow: 0 4px 8px rgba(0,0,0,0.1); /* Enhanced shadow for depth */
                margin-bottom: 30px; /* Increased bottom margin for spacing */
                width: calc(33% - 40px); /* Adjusted width to allow for margins and padding */
                background-color: #fff; /* White background for the cards */
                padding: 20px; /* Added padding inside the cards for content */
                transition: transform 0.3s ease-in-out; /* Smooth transition for hover effect */
            }

            .card:hover {
                transform: translateY(-5px); /* Slightly lift the card on hover */
            }

            .card-img-top {
                width: 100%;
                height: auto;
            }

               .descript-dest{
                font-size: 18px; /* Increased font size for better readability */
                color: #666; /* Darker text color for contrast */
                margin: 15px 0; /* Increased margin for spacing */
            }

            h5 {
                text-align: center;
                background-color: #e9ecef; /* Lighter background color for the button */
                padding: 12px 20px; /* Increased padding for a bigger button area */
                cursor: pointer;
            }
    </style>


</head>
<body>
<div id="dogCardsContainer">
    <!-- Static Card Template -->
   <%-- <div class="card">
        <img class="descript-dest" src="../images/labradorR.jpeg" alt="Présentation">
        <p id="chien box"></p>
        <h5></h5>
    </div>--%>
    <!-- End Static Card Template -->

    <!-- Dynamically Generated Cards -->
    <c:forEach var="animal" items="${animaux}">
        <div class="card">
            <%--<img class="card-img-top" src="../images/labradorR.jpeg" alt="Présentation">--%>
            <img class="card-img-top" src="${animal.imageUrl}"/>
            <p class="descript-dest"><c:out value="${animal.nom}"/>
                (<c:out value="${animal.sexe}"/>) <br>
                </bf> Prix: <c:out value="${animal.prixAnimal}"/></p>
            <h5>Ajouter au panier</h5>
        </div>
        <%--<div class="card mb-3 p-2">
            <img class="chiens box"  src="../images/labradorR.jpeg" alt="presentation 2" style="height:300px; ">
            <h4>Labrador Golden</h4>
            <p class="descript-dest">Chien calme , parfait pour enfants</p>
        </div>--%>

    </c:forEach>
    <!-- End Dynamically Generated Cards -->
</div>
</body>
</html>
