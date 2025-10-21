package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    static void main() {

        String url = "jdbc:mysql://localhost:3306/example_java";
        String utilisateur = "root"; // Votre nom d'utilisateur MySQL
        String motDePasse = "rootroot"; // Votre mot de passe MySQL
        try {
            Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion établie avec succès !");
            connexion.close();
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }
}
