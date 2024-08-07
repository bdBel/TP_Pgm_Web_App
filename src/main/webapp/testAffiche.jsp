<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cartes avec animaux</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Basket Counter</title>
    <!-- Include Font Awesome for the icon -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
                background-color: #e9ecef; /* Lighter background color for the btnAnimal */
                padding: 12px 20px; /* Increased padding for a bigger btnAnimal area */
                cursor: pointer;
            }
    </style>


</head>
<body>
<div id="basketCounterContainer">
    <span>PANIER </span>
    <i class="fas fa-shopping-cart"></i>
    <span id="basketCounter">0</span>
</div>

<%--<btnAnimal id = "incrementbtnAnimal">Add to Basket</btnAnimal>--%>
<br>
<br>

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
<%--                <form action="control/panier" method="post">--%>
                    <!-- Input fields for animal details go here -->
               <button class="btnAnimal" type="button" name="BUY_ANIMALS">Buy Animals</button>
<%--                 <input type="submit" class="btnAnimal" value="Buy Animals" name="BUY_ANIMALS">--%>
<%--                </form>--%>
         </div>
        <%--<div class="card mb-3 p-2">
            <img class="chiens box"  src="../images/labradorR.jpeg" alt="presentation 2" style="height:300px; ">
            <h4>Labrador Golden</h4>
            <p class="descript-dest">Chien calme , parfait pour enfants</p>
        </div>--%>

    </c:forEach>
    <!-- End Dynamically Generated Cards -->
    <script type="text/javascript">

        document.addEventListener('DOMContentLoaded', function() {
            var btnAnimals = document.getElementsByClassName('btnAnimal'); // Single variable for clarity
            var counterSpan = document.getElementById('basketCounter');

            // Iterate over each button in the collection
            for(var i = 0; i < btnAnimals.length; i++) {
                var btn = btnAnimals[i];
                btn.addEventListener('click', function() { // Attach event listener to each button individually
                    var currentCount = parseInt(counterSpan.textContent);
                    counterSpan.textContent = currentCount + 1; // Increment the counter
                });
            }
        });
    </script>
</div>
</body>
</html>
