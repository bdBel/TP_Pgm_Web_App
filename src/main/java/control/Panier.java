package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TypeAnimal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/control/panier")
public class Panier extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mariadb://mysql-bil.alwaysdata.net:3306/bil_animaux";
    private static final String JDBC_USERNAME = "bil_bel";
    private static final String JDBC_PASSWORD = "cricket123456**";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession();

        switch (action) {
            case "add":
                addToCart(request, session, response);
                break;
            case "updateQuantity":
                updateQuantity(request, session);
                break;
            case "remove":
                removeFromCart(request, session);
                break;
            default:
                // Handle default case or show error message
                response.sendRedirect("erreur.jsp");
                break;
        }
    }

    private void addToCart(HttpServletRequest request, HttpSession session, HttpServletResponse response)
            throws IOException, ServletException {

        String animalIdStr = request.getParameter("animalId");

        if (animalIdStr != null && !animalIdStr.isEmpty()) {
            int animalId = Integer.parseInt(animalIdStr);
            TypeAnimal animal = getAnimalById(animalId);

            if (animal == null) {
                response.sendRedirect("erreur.jsp");
                return;
            }

            List<TypeAnimal> panier = (List<TypeAnimal>) session.getAttribute("panier");
            if (panier == null) {
                panier = new ArrayList<>();
                session.setAttribute("panier", panier);
            }
            panier.add(animal);

            // Optionnel : mettre à jour les détails de l'animal dans le panier
            String nom = request.getParameter("nom");
            String sexe = request.getParameter("sexe");
            double prixAnimal = Double.parseDouble(request.getParameter("prixAnimal"));
          //  int quantiteDisponible = Integer.parseInt(request.getParameter("quantiteDisponible"));

            animal.setNom(nom);
            animal.setSexe(sexe);
            animal.setPrixAnimal(prixAnimal);
            // animal.setQuantiteDisponible(quantiteDisponible); // Si nécessaire

            RequestDispatcher dispatcher = request.getRequestDispatcher("/testAffiche.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("erreur.jsp");
        }
    }

    private void updateQuantity(HttpServletRequest request, HttpSession session) {
        // Implémentez la logique pour mettre à jour la quantité d'un animal dans le panier
        // Par exemple, vous pouvez récupérer l'ID de l'animal et la nouvelle quantité depuis les paramètres de la requête
        // et mettre à jour l'objet TypeAnimal dans le panier.
    }

    private void removeFromCart(HttpServletRequest request, HttpSession session) {
        // Implémentez la logique pour retirer un animal du panier
        // Par exemple, vous pouvez récupérer l'ID de l'animal depuis les paramètres de la requête
        // et supprimer l'objet TypeAnimal correspondant de la liste du panier.
    }

    private TypeAnimal getAnimalById(int animalId) {
        TypeAnimal animal = null;
        String sql = "SELECT id, nom, sexe, prixAnimal, FROM TypeAnimal WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, animalId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                animal = new TypeAnimal();
                animal.setId(resultSet.getInt("id"));
                animal.setNom(resultSet.getString("nom"));
                animal.setSexe(resultSet.getString("sexe"));
                animal.setPrixAnimal(resultSet.getDouble("prixAnimal"));
              //  animal.setImageUrl(resultSet.getString("imageUrl"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Gérer les exceptions de manière appropriée
        }

        return animal;
    }
}
