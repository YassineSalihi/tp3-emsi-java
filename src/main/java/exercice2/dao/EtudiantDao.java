package exercice2.dao;

import exercice2.Etudiant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDao implements Dao<Etudiant> {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Tp3Exercice2_db";
        String user = "root";
        String password = "rootroot";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void insert(Etudiant etd) {
        String sql = "INSERT INTO etudiant (nom_etudiant, prenom_etudiant, no_option) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, etd.getNom_etudiant());
            ps.setString(2, etd.getPrenom_etudiant());
            ps.setInt(3, etd.getNo_option());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur insert Etudiant: " + e.getMessage());
        }
    }

    @Override
    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT no_etudiant, nom_etudiant, prenom_etudiant, no_option FROM etudiant";
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                etudiants.add(new Etudiant(
                        rs.getInt("no_etudiant"),
                        rs.getString("nom_etudiant"),
                        rs.getString("prenom_etudiant"),
                        rs.getInt("no_option")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur findAll Etudiant: " + e.getMessage());
        }
        return etudiants;
    }
}
