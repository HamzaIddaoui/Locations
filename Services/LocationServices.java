package Services;

import MesClasses.*;

import java.util.Iterator;
import java.util.Vector;

public class LocationServices {
    private Agence agence;
    public LocationServices(Agence agence){
        this.agence = agence;
    }

    public Iterator getLocations() {return this.agence.getLocations();}

    public Vector<Client> getClients() {return this.agence.getClients();}
    public Vector<Voiture> getVoitures() {
        Vector<Voiture> v = new Vector<>();
        Iterator it = this.agence.getVoitures();
        while(it.hasNext())
            v.add((Voiture) it.next());
        return v;
    }

    public Vector<Client> getClientsLibres() {return this.agence.getClientsLibres();}
    public Vector<Voiture> getVoituresLibres() {return this.agence.getVoituresLibres();}

    public Client getClientParCni(String cni){return this.agence.ChercherClientParCni(cni);}

    public boolean AjouterLocation(Client c, Voiture v) {
       try {
           this.agence.loueVoiture(c,v);
           return true;
       }catch (Exception e) {return false;}
    }

    public Vector<String> getMarques(){return this.agence.getMarques();}

    public int getMaxPrix() {return this.agence.getMaxPrix();}
    public int getMaxAnnee() {return this.agence.getMaxAnnee();}

    public Vector<Voiture> getVoituresSelection(Critere c) {return this.agence.AfficherSelection(c);}

    public void rendreLocation(Client c) throws ClientNonLoueurException {
        this.agence.rendVoiture(c);
    }
}
