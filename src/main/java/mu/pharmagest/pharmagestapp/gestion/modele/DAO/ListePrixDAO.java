package mu.pharmagest.pharmagestapp.gestion.modele.DAO;

import mu.pharmagest.pharmagestapp.database.PostgresDB;
import mu.pharmagest.pharmagestapp.gestion.modele.enity.Fournisseur;
import mu.pharmagest.pharmagestapp.gestion.modele.enity.ListePrix;
import mu.pharmagest.pharmagestapp.gestion.modele.enity.Medicament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pour obtenir les donnees de la base de la liste des prix
 */
public class ListePrixDAO {
    private Connection connection;
    private FournisseurDAO fournisseurDAO;
    private MedicamentDAO medicamentDAO;
    public ListePrixDAO() {
        this.connection = PostgresDB.getConnexion();
        this.fournisseurDAO = new FournisseurDAO();
        this.medicamentDAO = new MedicamentDAO();
    }

    //Pour obtenir la liste des medicaments par un fournisseur
    public List<ListePrix> listemedicamentparidfournisseur(Integer id_fournisseur) throws SQLException {
        List<ListePrix> listePrixList = new ArrayList<>();


        String requete = "SELECT * FROM listeprix WHERE id_fournisseur = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)) {
            // Définir le paramètre dans la requête préparée avec l'id_medicmanent
            preparedStatement.setInt(1, id_fournisseur);

            // Exécuter la requête SQL
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Parcourir les résultats et ajouter les fournisseurs à la liste
                while (resultSet.next()) {
                    listePrixList.add(mapListePrix(resultSet));
                }
            }
        } catch (SQLException e) {
            // Gérer l'exception en cas d'erreur SQL
            throw new SQLException("Erreur lors de la récupération des fournisseurs par nom. Cause : " + e.getMessage(), e);
        }


        return listePrixList;
    }

    private ListePrix mapListePrix(ResultSet resultSet) throws SQLException {
        return new ListePrix(
                fournisseurDAO.getFournisseursById(resultSet.getInt("id_fournisseur")),
                medicamentDAO.getMedicamentById(resultSet.getInt("id_medicament")),
                resultSet.getDouble("prix_unitaire"),
                resultSet.getInt("qt"),
                resultSet.getDouble("prix_vente")

        );
    }

}
