package mu.pharmagest.pharmagestapp;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mu.pharmagest.pharmagestapp.gestion.modele.DAO.FournisseurDAO;
import mu.pharmagest.pharmagestapp.gestion.modele.enity.Fournisseur;
import mu.pharmagest.pharmagestapp.utilis.chargerfxml;
import mu.pharmagest.pharmagestapp.utilis.chargerimages;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//Test pour fournisseur DAO
public class TestFournisseur {

    @Test
    public void getfournisseurbyid() throws SQLException {
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        System.out.println(fournisseurDAO.getFournisseursById(1).getNom_fournisseur());
    }

    @Test
    public void getallfournisseurs() throws SQLException {

        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        List<Fournisseur> fournisseurDAOList = fournisseurDAO.getallfournisseurs();
        for (Fournisseur fournisseur : fournisseurDAOList) {
            System.out.println(fournisseur.getNom_fournisseur() + " " + fournisseur.getAdresse_fournisseur());
        }
    }

    @Test
    public void addfournisseurs() throws SQLException {
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        if (fournisseurDAO.addFournisseur(new Fournisseur(null,"Fina", "Tana 6", 5798123))) {
            System.out.println("Insertion bien reussi");
        } else {
            System.out.println("Insertion pas fait");
        }
    }

    @Test
    public void delfournisseur() throws SQLException {
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        if (fournisseurDAO.deleteFournisseur(5)) {
            System.out.println("Suppression bien reussi");
        } else {
            System.out.println("Suppresion pas fait");
        }
    }

    @Test
    public void upfournisseur() throws SQLException {

        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        if (fournisseurDAO.updateFournisseur(new Fournisseur(3, "Fitia", "36 Avenu Trianon", 123456))) {
            System.out.println("Mis a jour  bien reussi");
        } else {
            System.out.println("Mis a jour pas fait");
        }
    }

    @Test
    public void getallfournisseurbyname() throws SQLException {

        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        List<Fournisseur> fournisseurDAOList = fournisseurDAO.getFournisseursByName("Fi");
        for (Fournisseur fournisseur : fournisseurDAOList) {
            System.out.println(fournisseur.getNom_fournisseur() + " " + fournisseur.getAdresse_fournisseur());
        }
    }

    @Test
    public void getallfournisseurbyid() throws SQLException {

        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        List<Fournisseur> fournisseurDAOList = fournisseurDAO.getFournisseursByName("Fita");
        for (Fournisseur fournisseur : fournisseurDAOList) {
            System.out.println(fournisseur.getNom_fournisseur() + " " + fournisseur.getAdresse_fournisseur());
        }
    }

}
