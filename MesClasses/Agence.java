package MesClasses;

import java.io.*;
import java.util.*;

public class Agence implements Serializable {
    private ArrayList<Voiture> voitures;

    private ArrayList<Client> clients;
    private TreeMap<Client, Voiture> Locations;

    public Agence() {
        this.Locations = new TreeMap<>();
        this.voitures = new ArrayList<>();
        this.clients = new ArrayList<>();
    };
    public Agence(ArrayList<Voiture> voitures) {
        this.Locations = new TreeMap<>();
        this.voitures = voitures;
        this.clients = new ArrayList<>();
    }
    public Agence(Voiture... args)
    {
        this.Locations = new TreeMap<>();
        voitures = new ArrayList<>();
        for ( Voiture v : args) voitures.add(v);
        this.clients = new ArrayList<>();
    }

    public Iterator getVoitures() {
        Iterator it = this.voitures.iterator();
        return it;
    }

    public Vector<Voiture> getVoituresLibres() {
        Vector<Voiture> v = new Vector<>();
        for(Voiture voit : this.voitures)
            if(!this.Locations.containsValue(voit))
                v.add(voit);
        return v;
    }

    public void AjouterVoiture(Voiture voiture) {
        this.voitures.add(voiture);
    }

    public boolean SupprimerVoiture(Voiture v){
        if(estLouee(v)) return false;
        this.voitures.remove(v);
        return true;
    }

    public boolean UpdateVoiture(Voiture v){
        for (int i = 0; i < this.voitures.size(); i++) {
            if(this.voitures.get(i).getId() == v.getId()) {
                this.voitures.get(i).setNom(v.getNom());
                this.voitures.get(i).setMarque(v.getMarque());
                this.voitures.get(i).setPrix_location(v.getPrix_location());
                this.voitures.get(i).setAnnee(v.getAnnee());
                return true;
            }
        }
        return false;
    }

    public Iterator Selectionne(Critere c, ArrayList<Voiture> v)
    {
        // Parcourir l'ensemble des voitures, si le critere est satistfait, ajouter a la collection
        for ( Voiture voiture : this.voitures)
            // Interface critere peut etre instanciee par n'importe qu'elle classe qu'elle l'implemente
            if(c.estSatisfaitPar(voiture))  v.add(voiture);
        Iterator iterator = v.iterator();
        return iterator;
    }

    public Vector<Voiture> AfficherSelection(Critere c) {
        ArrayList<Voiture> list = new ArrayList<>();
        Iterator iterator = Selectionne(c,list);
        Vector<Voiture> v = new Vector<>();
        for(Voiture voit : list)
            v.add(voit);
        return v;
    }

    public boolean estLoeur(Client client) {
        if(this.Locations.containsKey(client)) return true;
        return false;
    }

    public boolean estLouee(Voiture voiture) {
        return this.Locations.containsValue(voiture);
    }

    public boolean loueVoiture(Client client, Voiture voiture) throws ClientEstLoueurException, VoitureEstLoueeException {
        if(estLoeur(client)) throw new ClientEstLoueurException();
        if(estLouee(voiture)) throw new VoitureEstLoueeException();
        this.Locations.put(client,voiture);
        System.out.println("Location ajoute avec succes");
        return true;
    }

    public void rendVoiture(Client client) throws ClientNonLoueurException {
        if(this.Locations.remove(client) == null) throw new ClientNonLoueurException();
        System.out.println("Location a ete rendue avec succes");
    }

    public Iterator lesVoituresLouees() {
        Collection<Voiture> voitureslouees =  this.Locations.values();
        Iterator it = voitureslouees.iterator();
        return it;
    }

    public void AfficherVoituresLouees() {
        Iterator it = this.lesVoituresLouees();
        // Si aucune voiture n'est louee
        if (it == null) {
            System.out.println("Aucune voiture n'est louee.");
            return;
        }
        while(it.hasNext())
            System.out.println(it.next());
    }

    public InterCritere SaisirCriteresVoitures(Scanner sc) {
        String marque;
        int prix,annee;
        InterCritere critere = null;
        System.out.println("Saisir la marque : ");
        marque = sc.next();
        try {
            System.out.println("Saisir l'annee : ");
            annee = sc.nextInt();
            System.out.println("Saisir le prix ");
            prix = sc.nextInt();
            if (prix <= 0) throw new PrixNegatif();
            critere = new InterCritere(
                    new CriterePrix(prix),
                    new CritereMarque(marque),
                    new CritereAnnee(annee)
            );
        } catch(NumberFormatException e ) {
            System.out.println("Veuillez saisir un nombre pour annee/prix");
        } catch (InputMismatchException e ) {
            System.out.println("Format de saisit non respecte");
        }catch(Exception e ) {
            System.out.println(e);
        }
        return critere;
    }

    public Client SaisirClient(Scanner sc) {
        String nom,prenom,cin;
        Civilite civ = Civilite.Mr;
        boolean saisit = true;
        System.out.println("Saisir le nom : ");
        nom = sc.next();
        System.out.println("Saisir le prenom : ");
        prenom = sc.next();
        System.out.println("Saisir CIN : ");
        cin = sc.next();
        while(saisit){
            System.out.println("Saisir la civilite : (Tapez mr ou mme ou mlle) ");
            String civilite = sc.next();
            switch(civilite){
                case "mr" : civ = Civilite.Mr; saisit = false;break;
                case "mme" : civ = Civilite.Mme; saisit = false; break;
                case "mlle" : civ = Civilite.Mlle; saisit = false; break;
                default : System.out.println("Incorrect saisit !");
            }
        }
        Client client = new Client(nom,prenom,cin,civ);
        return client;
    }

    public void AjouterClient(Client c ) {
        for(Client client : this.clients)
            if(client.getCin() == c.getCin()) return;
        this.clients.add(c);
    }

    public boolean estLoueurId(int id) {
        for (Client c : this.Locations.keySet())
            if(c.getId() == id) return true;
        return false;
    }

    public boolean SupprimerClient(Client c) {
        if(estLoeur(c)) return false;
        this.clients.remove(c);
        return true;
    }

    public boolean UpdateClient(int id, String nom, String prenom, String cin, Civilite civ){
        for(int i = 0; i < this.clients.size(); i++){
            if(this.clients.get(i).getId() == id) {
                System.out.println("Valeur client precedente : ");
                System.out.println(this.clients.get(i));
                this.clients.get(i).setNom(nom);
                this.clients.get(i).setPrenom(prenom);
                this.clients.get(i).setCin(cin);
                this.clients.get(i).setCivilite(civ);
                System.out.println("Nouvelle Valeure du client : ");
                System.out.println(this.clients.get(i));
                return true;
            }
        }
        return false;
    }

    public Vector<Client> getClients() {
        Vector<Client> c = new Vector<>();
        c.addAll(this.clients);
        return c;
    }

    public Vector<Client> getClientsLibres() {
        Vector<Client> c = new Vector<>();
        for(Client client : this.clients)
            if(!estLoeur(client)) c.add(client);
        return c;
    }


    public void AfficherLocation(){
        int indice = 1;
        Iterator it = this.Locations.entrySet().iterator();
        if(!it.hasNext()){
            System.out.println("Aucune location n'est fait.");
            return;
        }
        while(it.hasNext()){
            Map.Entry location = (Map.Entry) it.next();
            System.out.println("Location numero : " + indice);
            System.out.println(location.getKey());
            System.out.println(location.getValue());
            indice++;
        }
    }

    public boolean AjouterLocation(String cin,Voiture v){
        Client c = null;
        for (int i = 0; i < this.clients.size(); i++)
            if(this.clients.get(i).getCin() == cin)
                c = this.clients.get(i);
        if(c == null) return false;
        this.Locations.put(c,v);
        return true;
    }

    public Iterator getLocations() {
        Iterator it = this.Locations.entrySet().iterator();
        return it;
    }

    public void Serialization() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Agence.txt"));
            try {
                oos.writeObject(this);
                oos.flush();
            } finally {
                oos.close();
            }
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("Erreur de serialization !");
        }
    }

    public static Agence Deserialization(Agence agence) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Agence.txt"));
            try {
                agence = (Agence) ois.readObject();
            } catch (Exception e) {

            } finally {
                ois.close();
            }
        } catch (IOException e ) {
            System.out.println("Aucun objet est trouve, creation d'une nouvelle Agence");
            agence = new Agence();
        }
        return agence;
    }

    public Client ChercherClientParCni(String cni) {
        for (int i = 0; i < this.clients.size(); i++)
            if(this.clients.get(i).getCin() == cni)
                return this.clients.get(i);
        return null;
    }

    public Vector<String> getMarques(){
        Vector<String> m = new Vector<>();
        for(Voiture v : this.voitures)
            m.add(v.getMarque());
        return m;
    }

    public int getMaxPrix() {
        Voiture v = this.voitures.stream().max(Comparator.comparing(Voiture::getPrix_location)).get();
        return v.getPrix_location()+1;
    }

    public int getMaxAnnee() {
        Voiture v = this.voitures.stream().max(Comparator.comparing(Voiture::getAnnee)).get();
        return v.getAnnee()+1;
    }

    @Override
    public String toString() {
        return "Agence{" +
                "voitures=" + voitures +
                '}';
    }
}

