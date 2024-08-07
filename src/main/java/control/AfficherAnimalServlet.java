package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TypeAnimal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AfficherAnimalServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Database connection setup

         //declarer une instance de la classe VenteAnimalService
        //appeler les méthodes dans cette classe (afficher détail, ajouter au panier etc..)
        //
        String jdbcURL = "jdbc:mariadb://mysql-bil.alwaysdata.net:3306/bil_animaux";

        String jdbcUsername = "bil_bel";

        String jdbcPassword = "cricket123456**";

        List<TypeAnimal> listeAnimaux = new ArrayList<>();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT id, nom, sexe, prixAnimal,imageUrl FROM TypeAnimal";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                TypeAnimal animal = new TypeAnimal();
                animal.setId(resultSet.getInt("id"));
                animal.setNom(resultSet.getString("nom"));
                animal.setSexe(resultSet.getString("sexe"));
                animal.setPrixAnimal(resultSet.getDouble("prixAnimal"));
                animal.setImageUrl(resultSet.getString("imageUrl"));
                listeAnimaux.add(animal);
                System.out.println("Retrieved animal: " + animal);            }
           resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("animaux", listeAnimaux);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/testAffiche.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}