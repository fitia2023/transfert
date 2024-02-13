package mu.pharmagest.pharmagestapp;

import mu.pharmagest.pharmagestapp.database.PostgresDB;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

//Test de la connexion
public class TestDatabase {

    //Test postgres
    @Test
    public void test_connexion(){
        Connection connection = PostgresDB.getConnexion();
        if (connection != null){
            System.out.print("Connexion réussi");
        }
    }
}
