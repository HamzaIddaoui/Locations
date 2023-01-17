package MesClasses;

import java.util.Objects;

public class Client implements Comparable{
    private static int cpt = 1;
    private int id;
    private String nom;
    private String prenom;
    private String cin;
    private Civilite civilite;

    public Client(int id){
        this.id = id;
    }
    public Client(int id, String nom, String prenom, String cin, Civilite civilite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.civilite = civilite;
    }

    public Client(String nom, String prenom, String cin, Civilite civilite) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.civilite = civilite;
        this.id = cpt;
        cpt ++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Civilite getCivilite() {
        return civilite;
    }

    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Client client = (Client) o;
        return this.id == client.id;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.id,( (Client)o ).id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +
                ", civilite=" + civilite +
                '}';
    }
}

