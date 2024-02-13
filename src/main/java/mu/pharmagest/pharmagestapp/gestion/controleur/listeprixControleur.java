package mu.pharmagest.pharmagestapp.gestion.controleur;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mu.pharmagest.pharmagestapp.gestion.modele.DAO.FournisseurDAO;
import mu.pharmagest.pharmagestapp.gestion.modele.DAO.ListePrixDAO;
import mu.pharmagest.pharmagestapp.gestion.modele.enity.Fournisseur;
import mu.pharmagest.pharmagestapp.gestion.modele.enity.ListePrix;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class listeprixControleur implements Initializable {
    public TableView<ListePrix> t_liste;
    public TableColumn<ListePrix, String> t_medicament;
    public TableColumn<ListePrix, Double> t_prixU;
    public TableColumn<ListePrix, Integer> t_qt;
    public TableColumn<ListePrix, Double> t_prix;
    public Label fourni;

    //Pour detail du fournisseur
    private Integer id_fournisseur;

    //pour chargement
    private ListePrixDAO listePrixDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setId_fournisseur(1);

        listePrixDAO = new ListePrixDAO();
        try {
            change_nom_fournisseur();

            charge_data(FXCollections.observableArrayList(listePrixDAO.listemedicamentparidfournisseur(getId_fournisseur())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //-----Getters et Setters du fournisseur
    public Integer getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(Integer id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    //Changer le nom du fournisseur
    private void change_nom_fournisseur() throws SQLException {
        fourni.setText(new FournisseurDAO().getFournisseursById(getId_fournisseur()).getNom_fournisseur());
    }

    //Pour charger le contenu de la liste
    private void charge_data(ObservableList<ListePrix> listePrixes) throws SQLException {
        t_liste.setItems(listePrixes);
        t_medicament.setCellValueFactory(call_m -> new SimpleStringProperty(call_m.getValue().getMedicament().getNom_medicament()));
        t_prixU.setCellValueFactory(call_p -> new SimpleDoubleProperty(call_p.getValue().getPrix_unitaire_achat()).asObject());
        t_qt.setCellValueFactory(call_q -> new SimpleIntegerProperty(call_q.getValue().getQt_min_commande()).asObject());
        t_prix.setCellValueFactory(call_pr->new SimpleDoubleProperty(call_pr.getValue().getPrix_vente()).asObject());
    }


}
