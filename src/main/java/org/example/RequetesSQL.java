package org.example;

import java.sql.*;

public class RequetesSQL {

    static void main() {
        String url = "jdbc:mysql://localhost:3306/example_java";
        String utilisateur = "root";
        String motDePasse = "rootroot";

        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)) {
            Statement statement = connexion.createStatement();
            // -------- Affichage --------
            ResultSet resultSet = statement.executeQuery("SELECT * FROM utilisateurs");
            System.out.println("=== Liste des utilisateurs ===");
            while(resultSet.next()){
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nom: " + resultSet.getString("nom"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("---");
            }

            // -------- Insertion avant la requête préparée --------
            String insertionSQL = "INSERT INTO utilisateurs (nom, email) VALUES (?, ?)";
            PreparedStatement insertStmt = connexion.prepareStatement(insertionSQL);
            insertStmt.setString(1, "Prepared User");
            insertStmt.setString(2, "preparedstat@example.com");
            int lignesAffectees = insertStmt.executeUpdate();
            System.out.println("✅ Lignes insérées : " + lignesAffectees);

            // -------- Sélection avec PreparedStatement --------
            String requete = "SELECT * FROM utilisateurs WHERE email = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setString(1, "preparedstat@example.com");
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("=== Résultat du PreparedStatement ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("---");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
