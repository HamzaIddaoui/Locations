package GUI;

import MesClasses.*;
import Services.LocationServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class LocationInterface extends JPanel implements ActionListener {
    private LocationServices locationServices;
    private JTable locationTable;
    private DefaultTableModel locationTableModel;
    private JButton ajouterLocation_btn,ajouterlocationDialog_btn,chercherCritereBtn,rendreLocation_Btn;
    private JDialog ajouterLocationDialog;
    private Vector<Client> clients;
    private Vector<Voiture> voitures;
    private JComboBox clients_cni;
    private JTable voituresLibresTable;
    private DefaultTableModel voitureLibresModel;
    private JComboBox criteresPrix,criteresAnnee,criteresMarque;

    public LocationInterface(Agence agence) {
        super();
        this.setLayout(new GridLayout(6, 1));
        this.locationServices= new LocationServices(agence);
        JScrollPane scrollPane = new JScrollPane(this);

        /** Welcome Panel **/
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout());
        JLabel welcomeLabel = new JLabel("Bienvenue a l'interface des Locations");
        welcomePanel.add(welcomeLabel);
        add(welcomePanel);
        /** Fin de Welcome Panel **/

        /** Ajouter Location boutton **/
        JPanel ajouterPanel = new JPanel();
        ajouterPanel.setLayout(new FlowLayout());
        this.ajouterLocation_btn = new JButton("Ajouter");
        this.ajouterLocation_btn.addActionListener(this);
        ajouterPanel.add(this.ajouterLocation_btn);
        add(ajouterPanel);
        /** Fin de Ajouter Location boutton **/

        /** Table des Locations existents **/
        JPanel tabletitlePanel = new JPanel();
        tabletitlePanel.setLayout(new FlowLayout());
        JLabel tableTitle = new JLabel("Table des Locations");
        tabletitlePanel.add(tableTitle);
        add(tabletitlePanel);
        String [] colonnes = {"Id_client","Id_voiture","Nom complet","Marque","Prix"};
        this.locationTableModel = new DefaultTableModel(colonnes,0);
        Iterator it = this.locationServices.getLocations();
        while(it.hasNext()) {
            Map.Entry location = (Map.Entry) it.next();
            Client c = (Client)location.getKey();
            Voiture v = (Voiture)location.getValue();
            String nomComplet = c.getNom() + " " + c.getPrenom();
            Object[] obj ={c.getId(),v.getId(),nomComplet,v.getMarque(),v.getPrix_location()};
            this.locationTableModel.addRow(obj);
        }
        this.locationTable = new JTable(locationTableModel);
        this.locationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableHeaderPanel = new JScrollPane(this.locationTable);
        add(tableHeaderPanel);
        /** Fin de Table des Locations existents **/
        JPanel rendrePanel = new JPanel();
        rendrePanel.setLayout(new FlowLayout());
        this.rendreLocation_Btn = new JButton("Rendre");
        this.rendreLocation_Btn.addActionListener(this);
        rendrePanel.add(this.rendreLocation_Btn);
        add(rendrePanel);
    }


    /** Ajouter Location Modal **/
    public void AjouterLocationModal() {
        this.ajouterLocationDialog = new JDialog();
        this.ajouterLocationDialog.setSize(800,800);
        this.ajouterLocationDialog.setLocationRelativeTo(null);
        Container ajouterLocationDialogContainer = this.ajouterLocationDialog.getContentPane();
        this.ajouterLocationDialog.setTitle("Ajouter Client Interface");
        ajouterLocationDialogContainer.setLayout(new GridLayout(9,1));
        this.clients_cni = new JComboBox<>();
        this.clients = this.locationServices.getClientsLibres();
        for(Client c : this.clients)
            this.clients_cni.addItem(c.getCin());
        JLabel client_label = new JLabel("Choisir CNI du Client :");
        ajouterLocationDialogContainer.add(client_label);
        ajouterLocationDialogContainer.add(this.clients_cni);

        /** Criteres **/
        JPanel critereLabelPanel = new JPanel();
        JPanel criterePanel = new JPanel();
        criterePanel.setLayout(new FlowLayout());
        critereLabelPanel.setLayout(new FlowLayout());
        String[] optionPrix = new String[] {"All","200","150","100"};
        this.criteresPrix = new JComboBox(optionPrix);
        String[] optionAnnee = new String[] {"All","2010","2011","2012","2013","2014","2015"};
        this.criteresAnnee = new JComboBox(optionAnnee);
        Vector<String> optionMarque = this.locationServices.getMarques();
        optionMarque.add(0,"All");
        this.criteresMarque = new JComboBox(optionMarque);
        this.chercherCritereBtn = new JButton("Chercher");
        this.chercherCritereBtn.addActionListener(this);
        criterePanel.add(this.criteresMarque);
        criterePanel.add(this.criteresPrix);
        criterePanel.add(this.criteresAnnee);
        criterePanel.add(this.chercherCritereBtn);
        JLabel critereLabel = new JLabel("Choisir un critere : ");
        critereLabelPanel.add(critereLabel);
        JLabel tableTitle = new JLabel("Table des Voitures libres (Veuillez selectionner une voiture)");
        ajouterLocationDialogContainer.add(critereLabelPanel);
        ajouterLocationDialogContainer.add(criterePanel);
        /** Fin de Criteres **/

        /** Table des Locations existents **/
        JPanel tabletitlePanel = new JPanel();
        tabletitlePanel.setLayout(new FlowLayout());
        tabletitlePanel.add(tableTitle);
        ajouterLocationDialogContainer.add(tabletitlePanel);
        String [] colonnes = {"Id","Marque","Non","Annee","Prix"};
        this.voitureLibresModel = new DefaultTableModel(colonnes,0);
        this.voitures = this.locationServices.getVoituresLibres();
        for(Voiture v : this.voitures) {
            int id = v.getId();
            String marque = v.getMarque();
            String nom = v.getNom();
            int annee = v.getAnnee();
            int prix = v.getPrix_location();
            Object[] obj = {id,marque,nom,annee,prix};
            voitureLibresModel.addRow(obj);
        }
        this.voituresLibresTable = new JTable(voitureLibresModel);
        this.voituresLibresTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableHeaderPanel = new JScrollPane(this.voituresLibresTable);
        ajouterLocationDialogContainer.add(tableHeaderPanel);
        /** Fin de Table des Locations existents **/

        /** Ajouter Location boutton **/
        this.ajouterlocationDialog_btn = new JButton("Ajouter");
        this.ajouterlocationDialog_btn.addActionListener(this);
        ajouterLocationDialogContainer.add(this.ajouterlocationDialog_btn);
        /** Fin de Ajouter Location Boutton **/

        this.ajouterLocationDialog.setVisible(true);
    }
    /** Fin de Ajouter Location Modal **/

    @Override
    public void actionPerformed(ActionEvent e) {
        /** Ajouter Location Modal **/
        if(e.getSource() == this.ajouterLocation_btn)
            this.AjouterLocationModal();

        /** Ajouter Location **/
        if(e.getSource() == this.ajouterlocationDialog_btn) {
            int row = this.voituresLibresTable.getSelectedRow();
            if(row == -1)
                JOptionPane.showMessageDialog(null,"Veuillez selectionner une voiture.");
            else {
                int choix = JOptionPane.showConfirmDialog(null,"Etes vous sur d'enregistrer cette location ? ");
                if(choix == 0) {
                    Client client = this.locationServices.getClientParCni((String)this.clients_cni.getSelectedItem());
                    if(client == null){
                        JOptionPane.showMessageDialog(null,"Aucun client ne correspond a ce cin ..");
                        return;
                    }
                    boolean result = this.locationServices.AjouterLocation(client,
                            new Voiture( (int)this.voituresLibresTable.getValueAt(row,0),
                                    (String) this.voituresLibresTable.getValueAt(row,1),
                                    (String) this.voituresLibresTable.getValueAt(row,2),
                                    (int)this.voituresLibresTable.getValueAt(row,3),
                                    (int)this.voituresLibresTable.getValueAt(row,4))
                            );
                    if(result)
                    {
                        this.voitureLibresModel.removeRow(row);
                        Iterator it = this.locationServices.getLocations();
                        while(it.hasNext()) {
                            Map.Entry location = (Map.Entry) it.next();
                            Client c = (Client)location.getKey();
                            Voiture v = (Voiture)location.getValue();
                            String nomComplet = c.getNom() + " " + c.getPrenom();
                            Object[] obj ={c.getId(),v.getId(),nomComplet,v.getMarque(),v.getPrix_location()};
                            this.locationTableModel.addRow(obj);
                        }
                        JOptionPane.showMessageDialog(null,"Location ajoute avec succes.");
                    }
                    else {JOptionPane.showMessageDialog(null,"Location n'a pas ete enregistrer .. Une erreur est produite.");}
                }
            }
        }

        /** Chercher Par Critere **/
        if(e.getSource() == this.chercherCritereBtn){
            // Tous les voitures libres
            if ( ( this.criteresPrix.getSelectedIndex() == 0) &&
                    (this.criteresAnnee.getSelectedIndex() == 0) &&
                    (this.criteresMarque.getSelectedIndex() == 0)
               ) {
                this.voitures = this.locationServices.getVoituresLibres();
                voitureLibresModel.setRowCount(0);
                for(Voiture v : this.voitures) {
                    int id = v.getId();
                    String marque = v.getMarque();
                    String nom = v.getNom();
                    int annee = v.getAnnee();
                    int prix = v.getPrix_location();
                    Object[] obj = {id,marque,nom,annee,prix};
                    voitureLibresModel.addRow(obj);
                }
            }
            // Sinon
            CritereMarque cm = new CritereMarque((String)this.criteresMarque.getSelectedItem());
            CriterePrix cp;
            CritereAnnee ca;
            if(this.criteresPrix.getSelectedIndex() == 0)
                cp = new CriterePrix(this.locationServices.getMaxPrix());
            else {cp = new CriterePrix(Integer.valueOf((String)this.criteresPrix.getSelectedItem()));}
            if(this.criteresAnnee.getSelectedIndex() == 0)
                ca = new CritereAnnee(0);
            else {ca = new CritereAnnee(Integer.valueOf((String)this.criteresAnnee.getSelectedItem()));}
            InterCritere interc = new InterCritere(cm,ca,cp);
            this.voitures = this.locationServices.getVoituresSelection(interc);
            System.out.println(this.voitures);
            voitureLibresModel.setRowCount(0);
            for(Voiture v : this.voitures) {
                int id = v.getId();
                String marque = v.getMarque();
                String nom = v.getNom();
                int annee = v.getAnnee();
                int prix = v.getPrix_location();
                Object[] obj = {id,marque,nom,annee,prix};
                voitureLibresModel.addRow(obj);
            }
        }
        /** Fin de Chercher Par Critere **/

        /** Rendre Location **/
        if(e.getSource() == this.rendreLocation_Btn){
            int row = this.locationTable.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(null,"Veuillez selectionner une location");
                return;
            }
            Client c = new Client((int)this.locationTable.getValueAt(row,0));
            try{
                this.locationServices.rendreLocation(c);
                this.locationTableModel.removeRow(row);
            }catch (Exception exception){
                System.out.println("Exceeption .. ");
            }
        }
        /** Fin de Rendre Location **/


    }
}
