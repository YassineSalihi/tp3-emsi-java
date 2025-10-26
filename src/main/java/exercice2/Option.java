package exercice2;

public class Option {
    private int no;
    private String nom;

    // Constructeur vide
    public Option() {
    }

    // Constructeur avec paramètres
    public Option(int no, String nom) {
        this.no = no;
        this.nom = nom;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Option{" +
                "no=" + no +
                ", nom='" + nom + '\'' +
                '}';
    }
}
