package persistence;

public class SQL_BOX {
//Produit Animal
    public static final String RECHERCHER_PRODUIT_PAR_ID = "SELECT * FROM ProduitsAnimaux WHERE id = ?";

    public static final String AFFICHER_TOUT_PRODUIT_ANIMAL = "SELECT * FROM ProduitsAnimaux";

    public static final String RECHERCHER_PRODUIT_ANIMAL_BY_ID = "SELECT * FROM ProduitsAnimaux WHERE id = ?";

    public static final String INSERT_PRODUIT_ANIMAL = "INSERT INTO ProduitsAnimaux (nom, description, prix, quantiteDisponible) VALUES (?, ?, ?, ?)";

    public static final String MODIFIER_PRODUIT_ANIMAL = "UPDATE ProduitsAnimaux SET nom = ?, description = ?, prix = ?, quantiteDisponible = ? WHERE id = ?";

    public static final String SUPPRIMER_PRODUIT_ANIMAL = "DELETE FROM ProduitsAnimaux WHERE id = ?";

    //Type Animal
    public static final String AFFICHER_TOUT_TYPE_ANIMAL = "SELECT * FROM TypeAnimal";

    public static final String RECHERCHER_TYPE_ANIMAL_PAR_NOM = "SELECT * FROM TypeAnimal WHERE nom = ?";

    public static final String AJOUTER_TYPE_ANIMAL = "INSERT INTO TypeAnimal (nom) VALUES (?)";

    public static final String SUPPRIMER_TYPE_ANIMAL_PAR_NOM = "DELETE FROM TypeAnimal WHERE nom = ?";

    public static final String MODIFIER_PRIX_ANIMAL = "UPDATE TypeAnimal SET nom = ? WHERE nom = ?";

    public  static final String RECHERCHER_PRIX_TYPE_ANIMAL_PAR_ID = "SELECT prixAnimal FROM TypeAnimal WHERE id = ?";

    public static final String RECHERCHER_TYPE_ANIMAL_PAR_ID = "SELECT * FROM TypeAnimal WHERE id = ?";
}
