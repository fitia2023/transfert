package mu.pharmagest.pharmagestapp;

import mu.pharmagest.pharmagestapp.authentification.modele.UtilisateurDAO;
import org.junit.jupiter.api.Test;

//Test acces utilisateur
public class TestUtilisateur {

    //Pour s'authentifier
    @Test
    public void sAuthentifier(){

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

        if (utilisateurDAO.sAuthentifier("john.doe","snow")){
            System.out.print("Utilisateur trouve");
        }else {
            System.out.print("Utilisateur pas trouve");
        }

    }

}
