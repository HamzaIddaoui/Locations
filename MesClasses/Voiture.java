package MesClasses;

import java.util.Objects;

public class Voiture implements Comparable{
    private static int cpt = 1;
    private int id;
    private String marque;
    private String nom;
    private int annee;
    private int prix_location;

    public Voiture(int id, String marque, String nom, int annee, int prix_location) {
        this.id = id;
        this.marque = marque;
        this.nom = nom;
        this.annee = annee;
        this.prix_location = prix_location;
    }

    public Voiture(String marque, String nom, int annee, int prix) {
        this.prix_location = prix;
        this.marque = marque;
        this.annee = annee;
        this.nom = nom;
        this.id = cpt;
        cpt++;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getPrix_location() {
        return prix_location;
    }

    public void setPrix_location(int prix_location) {
        this.prix_location = prix_location;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        return this.id == ((Voiture)o).id;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", nom='" + nom + '\'' +
                ", annee=" + annee +
                ", prix_location=" + prix_location +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.id,((Voiture)o).id);
    }
}

