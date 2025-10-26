package exercice2;

import exercice2.dao.EtudiantDao;
import exercice2.dao.OptionDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Test {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Tp3Exercice2_db";
        String user = "root";
        String password = "rootroot";
        return DriverManager.getConnection(url, user, password);
    }

    public static void enregistrerEtudiant(Etudiant etd){
        String sql = "INSERT INTO etudiant (nom_etudiant, prenom_etudiant, no_option) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, etd.getNom_etudiant());
            ps.setString(2, etd.getPrenom_etudiant());
            ps.setInt(3, etd.getNo_option());
            ps.executeUpdate();
            System.out.println("Étudiant enregistré : " + etd.getNom_etudiant() + " " + etd.getPrenom_etudiant());
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public static void enregistrerOption(Option opt) {
        String sql = "INSERT INTO optionTable (nom_option) VALUES (?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, opt.getNom());
            ps.executeUpdate();
            System.out.println("Option enregistrée : " + opt.getNom());
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }

    static void main() {
        // Test d’insertion d’options
//        Option opt1 = new Option(1, "Info");
//        Option opt2 = new Option(2, "Télécommunications");
//
//        enregistrerOption(opt1);
//        enregistrerOption(opt2);
//
//        // Test d’insertion d’étudiants (avec l’ID d’option correspondant)
//        Etudiant etd1 = new Etudiant("Salihi", "Yassine", 1);
//        Etudiant etd2 = new Etudiant("Ait Hmad", "Soufiane", 2);
//
//        enregistrerEtudiant(etd1);
//        enregistrerEtudiant(etd2);
        // ========================================== DAO ==========================================
        OptionDao optionDao = new OptionDao();
        optionDao.insert(new Option(1, "Info"));

        EtudiantDao etudiantDao = new EtudiantDao();
        etudiantDao.insert(new Etudiant("Nom", "Prenom", 1));

        List<Option> options = optionDao.findAll();
        List<Etudiant> etudiants = etudiantDao.findAll();

    }
}
