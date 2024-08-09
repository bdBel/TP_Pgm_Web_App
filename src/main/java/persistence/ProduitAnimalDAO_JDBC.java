package persistence;

import model.ProduitAnimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
o	Affichage de données de la BDD
o	Recherche dans la BDD selon plusieurs critères (au moins 4)
o	Mise à jour de données dans la BDD (modification ou suppression)
 */

public class ProduitAnimalDAO_JDBC implements IProduitAnimalDAO {

    Connection connection;

    public ProduitAnimalDAO_JDBC() {
        //Connexion à la basse de donnée
        this.connection = DB_ConnectorOld.getInstance().getConnection();
    }
    
    @Override
    public ProduitAnimal getProduitById(int id) {
        ProduitAnimal produit = null;
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_BOX.RECHERCHER_PRODUIT_PAR_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                produit = new ProduitAnimal();
                produit.setId(resultSet.getInt("id"));
                produit.setNom(resultSet.getString("nom"));
                produit.setDescription(resultSet.getString("description"));
                produit.setPrix(resultSet.getDouble("prix"));
                produit.setQuantite(resultSet.getInt("quantite"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return produit;
    }

    @Override
    public List<ProduitAnimal> afficherProduitAnimal() {
        List<ProduitAnimal> listeProduitAnimal = new ArrayList<>();
        try {
            PreparedStatement pst = this.connection.prepareStatement(SQL_BOX.AFFICHER_TOUT_PRODUIT_ANIMAL);
            //"SELECT * FROM PRODUIT_ANIMAL";

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ProduitAnimal produitAnimal = new ProduitAnimal();
                produitAnimal.setId(rs.getInt("id"));
                produitAnimal.setNom(rs.getString("nom"));
                produitAnimal.setPrix(rs.getFloat("prix"));
                produitAnimal.setQuantite(rs.getInt("quantite"));
                listeProduitAnimal.add(produitAnimal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listeProduitAnimal;
    }

    @Override
    public ProduitAnimal rechercherProduitAnimal(int id) {
        ProduitAnimal produitAnimal = null;
        try {
            PreparedStatement pst = this.connection.prepareStatement(SQL_BOX.RECHERCHER_PRODUIT_ANIMAL_BY_ID);
            //"SELECT * FROM PRODUIT_ANIMAL WHERE id = ?";
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                produitAnimal = new ProduitAnimal();
                produitAnimal.setId(rs.getInt("id"));
                produitAnimal.setNom(rs.getString("nom"));
                produitAnimal.setPrix(rs.getFloat("prix"));
                produitAnimal.setQuantite(rs.getInt("quantite"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produitAnimal;
    }


    @Override
    public void ajouterProduitAnimal(ProduitAnimal produitAnimal) {
        try {
            PreparedStatement pst = this.connection.prepareStatement(SQL_BOX.INSERT_PRODUIT_ANIMAL);
            //"INSERT INTO PRODUIT_ANIMAL (nom, description, prix, quantite) VALUES (?, ?, ?, ?)";
            pst.setString(1, produitAnimal.getNom());
            pst.setString(2, produitAnimal.getDescription());
            pst.setDouble(3, produitAnimal.getPrix());
            pst.setInt(4, produitAnimal.getQuantite());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void supprimerProduitAnimal(int id) {
        try {
            PreparedStatement pst = this.connection.prepareStatement(SQL_BOX.SUPPRIMER_PRODUIT_ANIMAL);
            //"DELETE FROM PRODUIT_ANIMAL WHERE id = ?";
            pst.setInt(1, id);

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void modifierProduitAnimal(ProduitAnimal produitAnimal) {
        try {
            PreparedStatement pst = this.connection.prepareStatement(SQL_BOX.MODIFIER_PRODUIT_ANIMAL);
            //"UPDATE PRODUIT_ANIMAL SET nom = ?, description = ?, prix = ?, quantite = ? WHERE id = ?";
            pst.setString(1, produitAnimal.getNom());
            pst.setString(2, produitAnimal.getDescription());
            pst.setDouble(3, produitAnimal.getPrix());
            pst.setInt(4, produitAnimal.getQuantite());
            pst.setInt(5, produitAnimal.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
