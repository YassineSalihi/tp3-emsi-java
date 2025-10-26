package exercice2.dao;

import exercice2.Option;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OptionDao implements Dao<Option> {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Tp3Exercice2_db";
        String user = "root";
        String password = "rootroot";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void insert(Option opt) {
        String sql = "INSERT INTO optionTable (nom_option) VALUES (?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, opt.getNom());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur insert Option: " + e.getMessage());
        }
    }

    @Override
    public List<Option> findAll() {
        List<Option> options = new ArrayList<>();
        String sql = "SELECT no_option, nom_option FROM optionTable";
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                options.add(new Option(rs.getInt("no_option"), rs.getString("nom_option")));
            }
        } catch (SQLException e) {
            System.err.println("Erreur findAll Option: " + e.getMessage());
        }
        return options;
    }
}
