        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!doctype html>
        <html>
        <head>
            <title>Cartes avec animaux</title>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--            <meta charset="UTF-8">--%>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Basket Counter</title>
            <!-- Include Font Awesome for the icon -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
                <style>
                body {

                    padding: 20px;
                    background-color: #f6f7f8;
                }

                    #dogCardsContainer {
                        display: flex;
                        flex-wrap: wrap;
                        justify-content: space-between;
                    }

                    .card {
                        border: 1px solid #ddd;
                        border-radius: 10px;
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
                        background-color: #e9ecef;
                        padding: 12px 20px;
                        cursor: pointer;
                    }
            </style>


        </head>
        <body>
        <h1> TEST AFFICHAGE </h1>
        <div id="basketCounterContainer">
            <span id="panier">PANIER </span>
            <i class="fas fa-shopping-cart"></i>
            <span id="basketCounter">0</span>
        </div>
        <form action="Panier" method="GET">
            <select name="animalId">
                <c:forEach var="animal" items="${animaux}">
<%--                   < option value="${animal.id}>--%>
                    <option value="${animal.Id}" ${animal.nom} ${animal.quantiteDispondible}(${animal.sexe}) - Prix: ${animal.prixAnimal}</option>

                </c:forEach>
            </select>
            <button type="submit" name="action" value="add">Ajouter au panier</button>
        </form>

        <%--<btnAnimal id = "incrementbtnAnimal">Add to Basket</btnAnimal>--%>
        <br>
        <br>

        <div id="dogCardsContainer">

           <%-- <div class="card">
                <img class="descript-dest" src="../images/labradorR.jpeg" alt="Présentation">
                <p id="chien box"></p>
                <h5></h5>
            </div>--%>



            <c:forEach var="animal" items="${animaux}">
                <div class="card">
                    <p id = "descr" style = "text-align: center">Description </p>
                    <%--<img class="card-img-top" src="../images/labradorR.jpeg" alt="Présentation">--%>
                    <img class="card-img-top id="${animal.imageUrl} src="${animal.imageUrl}"/>

                    <p class="descript-dest"><c:out value="${animal.nom}"/>
                        (<c:out value="${animal.sexe}"/>) <br>
                        </bf> Prix: <c:out value="${animal.prixAnimal}"/></p>
        <%--                <form action="control/panier" method="post">--%>
                            <!-- Input fields for animal details go here -->

                        <form id="addCart">

                            <input type="hidden" name="itemId" value="${animal.id}">
                            <input type="hidden" name="quantity" value="1">
                            <button type="submit" class="btnAnimal">Ajouter au panier</button>
                        </form>
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
                    var btnAnimals = document.getElementsByClassName('btnAnimal');
                    var counterSpan = document.getElementById('basketCounter');

                    for(var i = 0; i < btnAnimals.length; i++) {
                        var btn = btnAnimals[i];
                        btn.addEventListener('click', function() { /
                            var currentCount = parseInt(counterSpan.textContent);
                            var newCount = currentCount + 1;
                            counterSpan.textContent = newCount.toString();
                        });
                    }
                });
            </script>
        </div>
        <div id="panierDisplay">
        <%@ include file="panier.jsp" %>
        </div>
        </body>
        </html>
