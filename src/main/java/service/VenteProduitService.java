package service;

import model.ProduitAnimal;
import persistence.ProduitAnimalDAO_JDBC;

import java.util.ArrayList;
import java.util.List;

public class VenteProduitService {

        public ProduitAnimalDAO_JDBC produitAnimalDAO = new ProduitAnimalDAO_JDBC();

        public VenteProduitService (ProduitAnimalDAO_JDBC produitAnimalDAO) {

            this.produitAnimalDAO = produitAnimalDAO;
        }

        //methode pour caluler le PrixTotal des animaux selectionnés

    public double getPrixTotal(List<Integer> productIds) { // Assuming productIds is a list of product IDs
        double prixTotal = 0;

        // Iterate over product IDs, fetch each product using DAO, and sum their prices
        for (Integer id : productIds) {
            ProduitAnimal produit = produitAnimalDAO.rechercherProduitAnimal(id); // Fetch product using DAO
            if (produit != null) {
                prixTotal += produit.getPrix(); // Add product price to total
            }
        }

        return prixTotal;
    }
}
        //règle pour utilisateur :

        //ds UI declarer une instance de Vente produit
}
