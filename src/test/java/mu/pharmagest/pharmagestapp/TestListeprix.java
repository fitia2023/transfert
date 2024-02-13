package mu.pharmagest.pharmagestapp;

import mu.pharmagest.pharmagestapp.gestion.modele.DAO.ListePrixDAO;
import mu.pharmagest.pharmagestapp.gestion.modele.DAO.MedicamentDAO;
import mu.pharmagest.pharmagestapp.gestion.modele.enity.ListePrix;
import mu.pharmagest.pharmagestapp.gestion.modele.enity.Medicament;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class TestListeprix {

    @Test
    public void listeprix() throws SQLException {
        ListePrixDAO listePrixDAO = new ListePrixDAO();

        List<ListePrix> listePrixes = listePrixDAO.listemedicamentparidfournisseur(1);
        for (ListePrix listes:listePrixes) {

            System.out.println(listes.getMedicament().getNom_medicament());
        }

    }

}
