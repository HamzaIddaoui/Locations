package GUI;

import MesClasses.Agence;
import MesClasses.Voiture;
import Services.VoitureServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class VoitureInterface extends JPanel implements ActionListener {
    private VoitureServices voitureServices;
    private JButton ajouterVoiture_btn,ajouterVoitureDialog_btn,modifierVoiture_btn,supprimerVoiture_btn,confirmerModif_btn,chercherVoiture_btn;
    private JDialog ajouterDialog,modifierDialog,chercherDialog;
    private JTextField marque,nom,annee,prix;
    private Vector<Voiture> voitures;
    private JTable voitureTable;
    private DefaultTableModel tableModel;
    private int modifVoitureId;
    private JComboBox criterePrixVoitures;

    public VoitureInterface(Agence agence) {
        super();
        this.voitureServices = new VoitureServices(agence);
        this.setLayout(new GridLayout(6, 1));
        JScrollPane scrollPane = new JScrollPane(this);

        /** Voitures de test **/
        this.voitureServices.AjouterVoiture("Renaut","Clio",2009,100);
        this.voitureServices.AjouterVoiture("Toyota","NomToyota",2010,80);
        this.voitureServices.AjouterVoiture("Renaut","Clio4",2015,200);
        /** Fin de voitures de test **/

        /** Welcome Panel **/
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout());
        JLabel welcomeLabel = new JLabel("Bienvenue a l'interface des voitures");
        welcomePanel.add(welcomeLabel);
        add(welcomePanel);
        /** Fin de Welcome Panel **/

        /** Chercher par Critere Voitures **/
        JPanel chercherPanel = new JPanel();
        chercherPanel.setLayout(new FlowLayout());
        JLabel chercherLabel = new JLabel("Chercher Voiture par critere");
        this.chercherVoiture_btn = new JButton("Chercher");
        this.chercherVoiture_btn.addActionListener(this);
        chercherPanel.add(chercherLabel);
        chercherPanel.add(this.chercherVoiture_btn);
        add(chercherPanel);
        /** Fin de Chercher par Critere Voitures **/

        /**
        JPanel infosPanel = new JPanel();
        infosPanel.setLayout(new FlowLayout());
        JLabel totalVoitureLabel = new JLabel("Nombre total des voitures : " + this.voitureServices.nombreDeVoitures());
        JLabel totalVoitureLoueesLabel = new JLabel("Nombre total des voitures louees : " + this.voitureServices.nombreDeVoituresLouees());
        infosPanel.add(totalVoitureLoueesLabel);
        infosPanel.add(totalVoitureLabel);
        add(infosPanel);
         **/

        /** Ajouter Voiture boutton **/
        JPanel ajouterPanel = new JPanel();
        ajouterPanel.setLayout(new FlowLayout());
        this.ajouterVoiture_btn = new JButton("Ajouter");
        this.ajouterVoiture_btn.addActionListener(this);
        ajouterPanel.add(this.ajouterVoiture_btn);
        add(ajouterPanel);
        /** Fin de Ajouter Voiture boutton **/

        /** Table des voitures existentes **/
        JPanel tabletitlePanel = new JPanel();
        tabletitlePanel.setLayout(new FlowLayout());
        JLabel tableTitle = new JLabel("Table des voitures");
        tabletitlePanel.add(tableTitle);
        add(tabletitlePanel);
        String [] colonnes = {"Id","Marque","Nom","Annee","Prix"};
        this.tableModel = new DefaultTableModel(colonnes,0);
        this.voitures = this.voitureServices.getVoituresVector();
        for(Voiture v : this.voitures) {
            int id = v.getId();
            String marque = v.getMarque();
            String nom = v.getNom();
            int annee = v.getAnnee();
            int prix = v.getPrix_location();
            Object[] obj = {id,marque,nom,annee,prix};
            tableModel.addRow(obj);
        }
        this.voitureTable = new JTable(tableModel);
        this.voitureTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableHeaderPanel = new JScrollPane(this.voitureTable);
        add(tableHeaderPanel);
        /** Fin de Table des voitures existentes **/

        /** Supprimer / Modifier voiture bouttons **/
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        this.modifierVoiture_btn = new JButton("Modifier");
        this.modifierVoiture_btn.addActionListener(this);
        this.supprimerVoiture_btn = new JButton("Supprimer");
        this.supprimerVoiture_btn.addActionListener(this);
        btnPanel.add(this.supprimerVoiture_btn);
        btnPanel.add(this.modifierVoiture_btn);
        add(btnPanel);
        /** Fin de Supprimer / Modifier voiture bouttons **/

        /** Table des voitures louees **/
    }



    /** Modifier Voiture Modal **/
    public void ModifierVoitureModal(int id, String marque, String nom, int annee, int prix) {
        this.modifVoitureId = id;
        this.modifierDialog = new JDialog();
        this.modifierDialog.setSize(300,300);
        this.modifierDialog.setLocationRelativeTo(null);
        Container modifierDialogContainer = this.modifierDialog.getContentPane();
        this.modifierDialog.setTitle("Modifier Voiture Interface {{ Id : " + id + " }}");
        modifierDialogContainer.setLayout(new GridLayout(5,1));

        /** Marque **/
        JLabel marqueLabel = new JLabel("Marque : ");
        this.marque = new JTextField();
        this.marque.setText(marque);
        /** Fin de Marque **/
        /** Nom **/
        JLabel nomLabel = new JLabel("Nom : ");
        this.nom = new JTextField();
        this.nom.setText(nom);
        /** Fin de Nom **/
        /** Annee **/
        JLabel anneeLabel = new JLabel("Annee : ");
        this.annee = new JTextField();
        this.annee.setText(String.valueOf(annee));
        /** Fin de Annee **/
        /** Prix **/
        JLabel prixLabel = new JLabel("Prix");
        this.prix = new JTextField();
        this.prix.setText(String.valueOf(prix));
        /** Fin de Prix **/

        /** Ajouter boutton **/
        this.confirmerModif_btn = new JButton("Modifier");
        this.confirmerModif_btn.addActionListener(this);
        /** Fin de Ajouter boutton **/

        modifierDialogContainer.add(marqueLabel);
        modifierDialogContainer.add(this.marque);
        modifierDialogContainer.add(nomLabel);
        modifierDialogContainer.add(this.nom);
        modifierDialogContainer.add(anneeLabel);
        modifierDialogContainer.add(this.annee);
        modifierDialogContainer.add(prixLabel);
        modifierDialogContainer.add(this.prix);
        modifierDialogContainer.add(this.confirmerModif_btn);
        this.modifierDialog.setVisible(true);

    }
    /** Fin de Modifier Voiture Modal **/

    public void AjouterVoitureModal() {
        this.ajouterDialog = new JDialog();
        this.ajouterDialog.setSize(300,300);
        this.ajouterDialog.setLocationRelativeTo(null);
        Container ajouterDialogContainer = this.ajouterDialog.getContentPane();
        this.ajouterDialog.setTitle("Ajouter Voiture Interface");
        ajouterDialogContainer.setLayout(new GridLayout(5,1));
        /** Marque **/
        JLabel marqueLabel = new JLabel("Marque : ");
        this.marque = new JTextField();
        /** Fin de Marque **/
        /** Nom **/
        JLabel nomLabel = new JLabel("Nom : ");
        this.nom = new JTextField();
        /** Fin de Nom **/
        /** Annee **/
        JLabel anneeLabel = new JLabel("Annee : ");
        this.annee = new JTextField();
        /** Fin de Annee **/
        /** Prix **/
        JLabel prixLabel = new JLabel("Prix");
        this.prix = new JTextField();
        /** Fin de Prix **/

        /** Ajouter boutton **/
        this.ajouterVoitureDialog_btn = new JButton("Ajouter");
        this.ajouterVoitureDialog_btn.addActionListener(this);
        /** Fin de Ajouter boutton **/

        ajouterDialogContainer.add(marqueLabel);
        ajouterDialogContainer.add(this.marque);
        ajouterDialogContainer.add(nomLabel);
        ajouterDialogContainer.add(this.nom);
        ajouterDialogContainer.add(anneeLabel);
        ajouterDialogContainer.add(this.annee);
        ajouterDialogContainer.add(prixLabel);
        ajouterDialogContainer.add(this.prix);
        ajouterDialogContainer.add(this.ajouterVoitureDialog_btn);
        this.ajouterDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Ajouter Voiture Modal
        if(e.getSource() == this.ajouterVoiture_btn)
            this.AjouterVoitureModal();

        // Ajouter Voiture
        if(e.getSource() == this.ajouterVoitureDialog_btn) {
            // Ajouter la voiture
            Voiture v = this.voitureServices.AjouterVoiture(
                    this.marque.getText(),
                    this.nom.getText(),
                    Integer.valueOf(this.annee.getText()),
                    Integer.valueOf(this.prix.getText())
            );
            // Modifier la Table
            // Mise a jour de la table
            Object[] obj = {v.getId(),v.getMarque(),v.getNom(),v.getAnnee(),v.getPrix_location()};
            tableModel.addRow(obj);
            JOptionPane.showMessageDialog(null,"Voiture a ete ajoute avec succes !");
        }

        /** Supprimer Voiture **/
        if(e.getSource() == this.supprimerVoiture_btn){
            int row = this.voitureTable.getSelectedRow();
            if(row == -1)
                JOptionPane.showMessageDialog(null,"Veuillez selectionner une voiture.");
            else {
                int choix = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer cette Voiture ?");
                if (choix == 0) {
                    System.out.println(this.voitureTable.getValueAt(row,0));
                    Voiture v = new Voiture(
                            (int)this.voitureTable.getValueAt(row,0),
                            (String)this.voitureTable.getValueAt(row,1),
                            (String)this.voitureTable.getValueAt(row,2),
                            (int)this.voitureTable.getValueAt(row,3),
                            (int)this.voitureTable.getValueAt(row,4)
                    );
                    if(this.voitureServices.SupprimerVoiture(v)) {
                        JOptionPane.showMessageDialog(null,"Voiture a ete supprimee avec succes!");
                        this.tableModel.removeRow(row);
                    } else { JOptionPane.showMessageDialog(null,"Cette voiture ne peut pas etre supprimee, elle correspond a une location!");}
                }
                }
            }
        /** Fin de Supprimer Voiture **/

        /** Modifier boutton Modal **/
        if(e.getSource() == this.modifierVoiture_btn){
            int row = this.voitureTable.getSelectedRow();
            if(row == 0)
                JOptionPane.showMessageDialog(null,"Veuillez selectionner une Voiture a modifier.");
            else {
                this.ModifierVoitureModal(
                        (int)this.voitureTable.getValueAt(row,0),
                        (String)this.voitureTable.getValueAt(row,1),
                        (String)this.voitureTable.getValueAt(row,2),
                        (int)this.voitureTable.getValueAt(row,3),
                        (int)this.voitureTable.getValueAt(row,4)
                );
            }
        }
        /** Fin de Modifier boutton Modal **/

        /** Confimer Modification Voiture **/
        if(e.getSource() == this.confirmerModif_btn) {
            int choix = JOptionPane.showConfirmDialog(null,"Voulez vous modifier cette Voiture ?");
            if(choix == 0 ){
                boolean result = this.voitureServices.UpdateVoiture(
                        new Voiture(
                                this.modifVoitureId,
                                this.marque.getText(),
                                this.nom.getText(),
                                Integer.valueOf(this.annee.getText()),
                                Integer.valueOf(this.prix.getText())
                        )
                );
                if(result){
                    int row = this.voitureTable.getSelectedRow();
                    JOptionPane.showMessageDialog(null,"Voiture avec id = " + this.modifVoitureId + " a ete modifie avec succes");
                    this.tableModel.setValueAt(this.marque.getText(),row,1);
                    this.tableModel.setValueAt(this.nom.getText(),row,2);
                    this.tableModel.setValueAt(this.annee.getText(),row,3);
                    this.tableModel.setValueAt(this.prix.getText(),row,4);
                }
                else {JOptionPane.showMessageDialog(null,"Aucune voiture ne correspond a id = " + this.modifVoitureId);}
            }

        }
        /** Fin de Confimer Modification Voiture **/

    }

}
