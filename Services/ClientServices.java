package Services;

import MesClasses.Agence;
import MesClasses.Civilite;
import MesClasses.Client;

import java.util.Vector;

public class ClientServices {
    private Agence agence;
    public ClientServices(Agence agence){
        this.agence = agence;
    }

    public Client AjouterClient(String nom, String prenom, String cin, Civilite civ){
        Client c = new Client(nom,prenom,cin,civ);
        this.agence.AjouterClient(c);
        System.out.println("Client Services : client ajoutee avec succes");
        return c;
    }

    public boolean UpdateClient(int id, String n, String p , String c , Civilite civ) {
        return this.agence.UpdateClient(id,n,p,c,civ);
    }

    public boolean SupprimerClient(Client c){
        return this.agence.SupprimerClient(c);
    }

    public Vector<Client> getClients() { return this.agence.getClients();}

}
