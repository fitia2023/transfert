package mu.pharmagest.pharmagestapp.gestion.modele.DAO;

import mu.pharmagest.pharmagestapp.database.PostgresDB;
import mu.pharmagest.pharmagestapp.gestion.modele.enity.Fournisseur;
import mu.pharmagest.pharmagestapp.gestion.modele.enity.Medicament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Pour obtenir les donnees de la base info de la medicament
 */
public class MedicamentDAO {

    private final Connection connection;

    private final FournisseurDAO fournisseurDAO;

    public MedicamentDAO(){
        this.connection = PostgresDB.getConnexion();
        this.fournisseurDAO = new FournisseurDAO();
    }

    //obtenir medicament par id
    public Medicament getMedicamentById(int idMedicament) throws SQLException {
        // Requête SQL pour récupérer les medicament par id
        String requete = "SELECT * FROM medicament WHERE id_medicament = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)) {
            // Définir le paramètre dans la requête préparée avec le nom du fournisseur (utilisation de % pour une recherche partielle)
            preparedStatement.setInt(1,idMedicament);

            // Exécuter la requête SQL
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    return mapmedicament(resultSet);
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            // Gérer l'exception en cas d'erreur SQL
            throw new SQLException("Erreur lors de la récupération des medicaments par id. Cause : " + e.getMessage(), e);
        }

    }

    private Medicament mapmedicament(ResultSet resultSet) throws SQLException {

        return new Medicament(
                resultSet.getInt("id_medicament"),
                resultSet.getString("nom_medicament"),
                resultSet.getString("famille"),
                resultSet.getBoolean("ordonnance"),
                resultSet.getDouble("prix_vente"),
                resultSet.getInt("qt_stock"),
                resultSet.getInt("qt_min"),
                resultSet.getInt("qt_max"),
                resultSet.getInt("seuil_commande"),
                resultSet.getString("unite"),
                fournisseurDAO.getFournisseursById(resultSet.getInt("id_fournisseur"))
        );

    }


}
