package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.PanierItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AjouterAnimalServlet")
public class AjouterAnimalServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupère le paramètre animalId

        String produitId = request.getParameter("animalId");
        // Récupère le paramètre qty
        String quantiteAnimaux = request.getParameter("qty");
        int quantite = 0;
        try {
            quantite = Integer.parseInt(quantiteAnimaux);
            System.out.println("Parsed quantite = " + quantite);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
            // Optionally, set a default value or handle the error differently
            quantite = 1; // Setting a default value in case of NumberFormatException
        }

// Increment the quantite


        //System.out.println("Produit ID: " + produitId);
        //System.out.println("Quantité: " + quantite);

        // Vérifier si les paramètres sont vides
        if (produitId == null || produitId.isEmpty()) {
            throw new ServletException("Le paramètre 'animalId' est requis.");
        }


        int id;

        //Convertir les paramètres en nombres entiers
        try {
            id = Integer.parseInt(produitId);

        } catch (NumberFormatException e) {
            throw new ServletException("Les paramètres 'animalId' et 'qty' doivent être des nombres entiers.", e);
        }

        //Récupere la session
        HttpSession session = request.getSession();
        // Vérifier si le panier existe de déjà si non le creer
        List<PanierItem> panier = (List<PanierItem>) session.getAttribute("panier");
        if (panier == null) {
            panier = new ArrayList<>();
            session.setAttribute("panier", panier);
        }

        // Vérifier si l'article est déjà dans le panier
        boolean found = false;
        for (PanierItem item : panier) {
            if (item.getId() == id) {
                // Si l'article est dans le panie, ajuste la quantité
                item.setQuantite(item.getQuantite() + quantite); // Adjusted to add the requested quantity
                found = true;
                break;
            }
        }

// Si l'article est pas dans le panier, l'ajoute au panier
        if (!found) {
            panier.add(new PanierItem(id, quantite)); // Ensure the quantity matches the requested quantity
        }

// Increment the overall quantity only if the product was found and updated
        if (found) {
            quantite++;
        }


        // Rediriger vers la même page
        response.sendRedirect(request.getHeader("referer"));
    }
}
