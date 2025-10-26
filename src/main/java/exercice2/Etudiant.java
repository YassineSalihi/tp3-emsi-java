package exercice2;

public class Etudiant {

    private int no_etudiant;
    private String nom_etudiant;
    private String prenom_etudiant;
    private int no_option;

    public Etudiant() {}

    public Etudiant(String nom_etudiant, String prenom_etudiant, int no_option) {
        this.nom_etudiant = nom_etudiant;
        this.prenom_etudiant = prenom_etudiant;
        this.no_option = no_option;
    }

    public Etudiant(int no_etudiant, String nom_etudiant, String prenom_etudiant, int no_option) {
        this.no_etudiant = no_etudiant;
        this.nom_etudiant = nom_etudiant;
        this.prenom_etudiant = prenom_etudiant;
        this.no_option = no_option;
    }

    public int getNo_etudiant() {
        return no_etudiant;
    }

    public void setNo_etudiant(int no_etudiant) {
        this.no_etudiant = no_etudiant;
    }

    public String getNom_etudiant() {
        return nom_etudiant;
    }

    public void setNom_etudiant(String nom_etudiant) {
        this.nom_etudiant = nom_etudiant;
    }

    public String getPrenom_etudiant() {
        return prenom_etudiant;
    }

    public void setPrenom_etudiant(String prenom_etudiant) {
        this.prenom_etudiant = prenom_etudiant;
    }

    public int getNo_option() {
        return no_option;
    }

    public void setNo_option(int no_option) {
        this.no_option = no_option;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "no_etudiant=" + no_etudiant +
                ", nom_etudiant='" + nom_etudiant + '\'' +
                ", prenom_etudiant='" + prenom_etudiant + '\'' +
                ", no_option=" + no_option +
                '}';
    }
}
