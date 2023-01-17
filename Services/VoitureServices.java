package Services;

import MesClasses.Agence;
import MesClasses.Voiture;

import java.util.Iterator;
import java.util.Vector;

public class VoitureServices {
    private Agence agence;

    public VoitureServices(Agence agence) {
        this.agence = agence;
    }

    public Voiture AjouterVoiture(String marque, String nom, int annee, int prix) {
        Voiture v = new Voiture(marque, nom, annee, prix);
        this.agence.AjouterVoiture(v);
        System.out.println("Voitures Services : Voiture ajoutee avec succes !");
        return v;
    }

    public boolean SupprimerVoiture(Voiture v){return this.agence.SupprimerVoiture(v);}

    public boolean UpdateVoiture(Voiture v) {return this.agence.UpdateVoiture(v);}

    public Iterator getVoitures() { return this.agence.getVoitures();}

    public Iterator getVoituresLouees() {
        return this.agence.lesVoituresLouees();
    }

    public Vector<Voiture> getVoituresVector() {
        Vector<Voiture> v = new Vector<>();
        Iterator it = this.agence.getVoitures();
        while(it.hasNext())
            v.add((Voiture) it.next());
        return v;
    }

    ;

    public int nombreDeVoitures() {
        if(this.agence == null) return 0;
        int cpt = 0;
        Iterator it = this.getVoitures();
        while (it.hasNext()) {
            cpt++;
            it.next();
        }
        return cpt;
    }

    public int nombreDeVoituresLouees() {
        if(this.agence == null) return 0;
        int cpt = 0;
        Iterator it = this.getVoituresLouees();
        while (it.hasNext()) {
            cpt++;
            it.next();
        }
        return cpt;
    }
}
