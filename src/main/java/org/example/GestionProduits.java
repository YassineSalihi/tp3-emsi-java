package org.example;

import java.sql.*;
import java.util.Scanner;

public class GestionProduits {

    // Configuration de la connexion
    private static final String URL = "jdbc:mysql://localhost:3306/example_java";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "rootroot";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║   GESTION DES PRODUITS - MENU      ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. Afficher tous les produits");
            System.out.println("2. Insérer un nouveau produit");
            System.out.println("3. Mettre à jour le prix d'un produit");
            System.out.println("4. Rechercher un utilisateur par nom");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            switch (choix) {
                case 1:
                    afficherProduits();
                    break;
                case 2:
                    insererProduit(scanner);
                    break;
                case 3:
                    mettreAJourPrix(scanner);
                    break;
                case 4:
                    rechercherUtilisateurParNom(scanner);
                    break;
                case 0:
                    System.out.println("👋 Au revoir !");
                    break;
                default:
                    System.out.println("❌ Choix invalide !");
            }
        } while (choix != 0);

        scanner.close();
    }

    // 1. Afficher tous les produits
    public static void afficherProduits() {
        String requete = "SELECT * FROM produits";

        try (Connection connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             Statement statement = connexion.createStatement();
             ResultSet rs = statement.executeQuery(requete)) {

            System.out.println("\n═══════════════════════════════════════");
            System.out.println("         LISTE DES PRODUITS");
            System.out.println("═══════════════════════════════════════");
            System.out.printf("%-5s %-20s %-10s%n", "ID", "Nom", "Prix (€)");
            System.out.println("───────────────────────────────────────");

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                double prix = rs.getDouble("prix");
                System.out.printf("%-5d %-20s %-10.2f%n", id, nom, prix);
            }

            if (!hasResults) {
                System.out.println("Aucun produit trouvé.");
            }
            System.out.println("═══════════════════════════════════════\n");

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'affichage : " + e.getMessage());
        }
    }

    // 2. Insérer un nouveau produit
    public static void insererProduit(Scanner scanner) {
        System.out.print("\n📦 Nom du produit : ");
        String nom = scanner.nextLine();
        System.out.print("💰 Prix du produit : ");
        double prix = scanner.nextDouble();
        scanner.nextLine(); // Consommer le retour à la ligne

        String requete = "INSERT INTO produits (nom, prix) VALUES (?, ?)";

        try (Connection connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement ps = connexion.prepareStatement(requete)) {

            ps.setString(1, nom);
            ps.setDouble(2, prix);
            int lignesAffectees = ps.executeUpdate();

            if (lignesAffectees > 0) {
                System.out.println("✅ Produit '" + nom + "' ajouté avec succès !");
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'insertion : " + e.getMessage());
        }
    }

    // 3. Mettre à jour le prix d'un produit
    public static void mettreAJourPrix(Scanner scanner) {
        System.out.print("\n🔢 ID du produit à modifier : ");
        int id = scanner.nextInt();
        System.out.print("💰 Nouveau prix : ");
        double nouveauPrix = scanner.nextDouble();
        scanner.nextLine(); // Consommer le retour à la ligne

        String requete = "UPDATE produits SET prix = ? WHERE id = ?";

        try (Connection connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement ps = connexion.prepareStatement(requete)) {

            ps.setDouble(1, nouveauPrix);
            ps.setInt(2, id);
            int lignesAffectees = ps.executeUpdate();

            if (lignesAffectees > 0) {
                System.out.println("✅ Prix mis à jour avec succès !");
            } else {
                System.out.println("⚠️ Aucun produit trouvé avec l'ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    // 4. Rechercher un utilisateur par nom
    public static void rechercherUtilisateurParNom(Scanner scanner) {
        System.out.print("\n🔍 Nom de l'utilisateur à rechercher : ");
        String nom = scanner.nextLine();

        String requete = "SELECT * FROM utilisateurs WHERE nom LIKE ?";

        try (Connection connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement ps = connexion.prepareStatement(requete)) {

            ps.setString(1, "%" + nom + "%"); // Recherche partielle (contient)
            ResultSet rs = ps.executeQuery();

            System.out.println("\n═══════════════════════════════════════");
            System.out.println("      RÉSULTATS DE LA RECHERCHE");
            System.out.println("═══════════════════════════════════════");

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                int id = rs.getInt("id");
                String nomUtilisateur = rs.getString("nom");
                String email = rs.getString("email");
                System.out.println("ID: " + id);
                System.out.println("Nom: " + nomUtilisateur);
                System.out.println("Email: " + email);
                System.out.println("───────────────────────────────────────");
            }

            if (!hasResults) {
                System.out.println("⚠️ Aucun utilisateur trouvé avec le nom '" + nom + "'");
            }
            System.out.println("═══════════════════════════════════════\n");

            rs.close();

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche : " + e.getMessage());
        }
    }
}
