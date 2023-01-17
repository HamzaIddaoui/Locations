package GUI;

import MesClasses.Agence;
import MesClasses.Civilite;
import MesClasses.Client;
import Services.ClientServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ClientInterface extends JPanel implements ActionListener {
    private ClientServices clientServices;
    private JButton ajouterclient_btn,ajouterclientDialog_btn;
    private JTextField nom,prenom,cin;
    private JComboBox civilite;
    private JDialog ajouterDialog,modifierDialog;
    private JTable clientTable;
    private DefaultTableModel tablemodel;
    private Vector<Client> clients;
    private JButton supprimerclient_btn,modifierclient_btn,confirmermodifclient_btn;
    private int modifClientid;

    public ClientInterface(Agence agence) {
        super();
        this.setLayout(new GridLayout(6, 1));
        this.clientServices = new ClientServices(agence);
        JScrollPane scrollPane = new JScrollPane(this);

        /** Clients de test **/
        this.clientServices.AjouterClient("Nom1","Prenom1","123456",Civilite.Mr);
        this.clientServices.AjouterClient("Nom2","Prenom3","758964",Civilite.Mlle);
        this.clientServices.AjouterClient("Nom3","Prenom3","159753",Civilite.Mme);
        /** Fin de Client de test **/

        /** Welcome Panel **/
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout());
        JLabel welcomeLabel = new JLabel("Bienvenue a l'interface des clients");
        welcomePanel.add(welcomeLabel);
        add(welcomePanel);
        /** Fin de Welcome Panel **/

        /** Ajouter Client boutton **/
        JPanel ajouterPanel = new JPanel();
        ajouterPanel.setLayout(new FlowLayout());
        this.ajouterclient_btn = new JButton("Ajouter");
        this.ajouterclient_btn.addActionListener(this);
        ajouterPanel.add(this.ajouterclient_btn);
        add(ajouterPanel);
        /** Fin de Ajouter Client boutton **/

        /** Table des clients existents **/
        JPanel tabletitlePanel = new JPanel();
        tabletitlePanel.setLayout(new FlowLayout());
        JLabel tableTitle = new JLabel("Table des clients");
        tabletitlePanel.add(tableTitle);
        add(tabletitlePanel);
        String [] colonnes = {"Id","Nom","Prenom","Cin","Civilite"};
        this.tablemodel = new DefaultTableModel(colonnes,0);
        this.clients = this.clientServices.getClients();
        for(Client c : this.clients) {
            int id = c.getId();
            String nom = c.getNom();
            String prenom = c.getPrenom();
            String cin = c.getCin();
            Civilite civ = c.getCivilite();
            Object[] obj = {id,nom,prenom,cin,civ};
            tablemodel.addRow(obj);
        }
        this.clientTable = new JTable(tablemodel);
        this.clientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableHeaderPanel = new JScrollPane(this.clientTable);
        add(tableHeaderPanel);
        /** Fin de Table des clients existents **/

        /** Supprimer client boutton **/
        JPanel supprimerbtnPanel = new JPanel();
        supprimerbtnPanel.setLayout(new FlowLayout());
        this.supprimerclient_btn = new JButton("Supprimer");
        this.supprimerclient_btn.addActionListener(this);
        this.modifierclient_btn = new JButton("Modifier");
        this.modifierclient_btn.addActionListener(this);
        supprimerbtnPanel.add(this.supprimerclient_btn);
        supprimerbtnPanel.add(this.modifierclient_btn);
        add(supprimerbtnPanel);
        /** Fin de supprimer client boutton **/
    }


    /** Modifier Client Modal Function **/

    public void ModifierClientModal(int id,String nom,String prenom, String cin, int index) {
        this.modifClientid = id;
        this.modifierDialog = new JDialog();
        this.modifierDialog.setSize(300,300);
        this.modifierDialog.setLocationRelativeTo(null);
        Container modifierDialogContainer = this.modifierDialog.getContentPane();
        this.modifierDialog.setTitle("Modifier Client Interface { Id = "+id + " }");
        modifierDialogContainer.setLayout(new GridLayout(5,1));

        /** Nom **/
        JLabel nomlabel = new JLabel("Nom : ");
        this.nom = new JTextField();
        this.nom.setText(nom);
        /** Fin de Nom **/

        /** Prenom **/
        JLabel prenomlabel = new JLabel("Prenom : ");
        this.prenom = new JTextField();
        this.prenom.setText(prenom);
        /** Fin de prenom **/

        /** Cin **/
        JLabel cinlabel = new JLabel("Cin : ");
        this.cin = new JTextField();
        this.cin.setText(cin);
        /** Fin de Cin **/

        /** Civilite **/
        JLabel civilitelabel = new JLabel("Civilite : ");
        String options[] = new String[] { "Mr","Mmme","Mlle"};
        this.civilite = new JComboBox<>(options);
        this.civilite.setSelectedIndex(index);
        /** Fin de Civilite **/

        /** Confirmer boutton **/
        this.confirmermodifclient_btn = new JButton("Confirmer");
        this.confirmermodifclient_btn.addActionListener(this);
        /** Fin de Confirmer boutton **/

        modifierDialogContainer.add(nomlabel);
        modifierDialogContainer.add(this.nom);
        modifierDialogContainer.add(prenomlabel);
        modifierDialogContainer.add(this.prenom);
        modifierDialogContainer.add(cinlabel);
        modifierDialogContainer.add(this.cin);
        modifierDialogContainer.add(civilitelabel);
        modifierDialogContainer.add(this.civilite);
        modifierDialogContainer.add(this.confirmermodifclient_btn);
        this.modifierDialog.setVisible(true);
    }

    /** Fin de Modifier Client Modal Function **/


    public void AjouterClientModal() {
        this.ajouterDialog = new JDialog();
        this.ajouterDialog.setSize(300,300);
        this.ajouterDialog.setLocationRelativeTo(null);
        Container ajouterDialogContainer = this.ajouterDialog.getContentPane();
        this.ajouterDialog.setTitle("Ajouter Client Interface");
        ajouterDialogContainer.setLayout(new GridLayout(5,1));

        /** Nom **/
        JLabel nomlabel = new JLabel("Nom : ");
        this.nom = new JTextField();
        /** Fin de Nom **/

        /** Prenom **/
        JLabel prenomlabel = new JLabel("Prenom : ");
        this.prenom = new JTextField();
        /** Fin de prenom **/

        /** Cin **/
        JLabel cinlabel = new JLabel("Cin : ");
        this.cin = new JTextField();
        /** Fin de Cin **/

        /** Civilite **/
        JLabel civilitelabel = new JLabel("Civilite : ");
        String options[] = new String[] { "Mr","Mmme","Mlle"};
        this.civilite = new JComboBox<>(options);
        /** Fin de Civilite **/

        /** Ajouter boutton **/
        this.ajouterclientDialog_btn = new JButton("Ajouter");
        this.ajouterclientDialog_btn.addActionListener(this);
        /** Fin de Ajouter boutton **/

        ajouterDialogContainer.add(nomlabel);
        ajouterDialogContainer.add(this.nom);
        ajouterDialogContainer.add(prenomlabel);
        ajouterDialogContainer.add(this.prenom);
        ajouterDialogContainer.add(cinlabel);
        ajouterDialogContainer.add(this.cin);
        ajouterDialogContainer.add(civilitelabel);
        ajouterDialogContainer.add(this.civilite);
        ajouterDialogContainer.add(this.ajouterclientDialog_btn);
        this.ajouterDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Ajouter Client Modal
        if(e.getSource() == this.ajouterclient_btn)
            this.AjouterClientModal();

        // Ajouter Client
        if(e.getSource() == this.ajouterclientDialog_btn) {
            // Definir la civilite du combo box
            Civilite civ = Civilite.Mr;
            switch (this.civilite.getSelectedIndex()) {
                case 1 : civ = Civilite.Mme;
                   break;
                case 2 : civ = Civilite.Mlle;
                   break;
                default: civ = Civilite.Mr;
            }
            // Ajouter le nouveau client
            Client c = this.clientServices.AjouterClient(
                    this.nom.getText(),
                    this.prenom.getText(),
                    this.cin.getText(),
                    civ
            );
            // Mise a jour de la table
            try {
                Object[] obj = {c.getId(),c.getNom(),c.getPrenom(),c.getCin(),c.getCivilite()};
                tablemodel.addRow(obj);
            } catch (Exception exception){System.out.println(exception);}
            JOptionPane.showMessageDialog(null,"Client a ete ajoute avec succes !");
        }

        /** Supprimer Client **/
        if(e.getSource() == this.supprimerclient_btn) {
            int row = this.clientTable.getSelectedRow();
            Civilite civ = Civilite.Mr;
            // Si aucun client n'est selectionne
            if(this.clientTable.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null,"Veuillez selectionner un client.");
                return;
            }
            Client c = new Client(
                    (int)this.clientTable.getValueAt(row,0),
                    (String)this.clientTable.getValueAt(row,1),
                    (String)this.clientTable.getValueAt(row,2),
                    (String)this.clientTable.getValueAt(row,3),
                    (Civilite) this.clientTable.getValueAt(row,4)
            );
            int choix = JOptionPane.showConfirmDialog(null,"Voulez vous vraiment supprimer ce Client ?");
            if(choix == 0) {
                if(this.clientServices.SupprimerClient(c)) {
                    JOptionPane.showMessageDialog(null, "Client a ete supprimer avec succes !");
                    this.tablemodel.removeRow(row);
                }
                else JOptionPane.showMessageDialog(null, "Ce client ne peut pas etre supprimer ! Il a une location.");
            }

        }
        /** Fin de Supprimer Client **/

        /** Modifier Client **/
        if(e.getSource() == this.modifierclient_btn) {
            int row = this.clientTable.getSelectedRow();
            if(this.clientTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(null,"Veuillez selectionner un client a modifier");
            else {
                int index = 0;
                if((Civilite)this.clientTable.getValueAt(row,4) == Civilite.Mme) index = 1;
                if((Civilite)this.clientTable.getValueAt(row,4) == Civilite.Mlle) index = 2;
                this.ModifierClientModal(
                        (int) this.clientTable.getValueAt(row,0),
                        (String) this.clientTable.getValueAt(row,1),
                        (String) this.clientTable.getValueAt(row,2),
                        (String) this.clientTable.getValueAt(row,3),
                        index
                );
            }
        }
        /** Fin de Modifier Client **/

        /** Confirmer Modification Client **/
        if(e.getSource() == this.confirmermodifclient_btn) {
            int choix = JOptionPane.showConfirmDialog(null,"Voulez vous modifier ce client ?");
            if(choix == 0) {
                Civilite civ = Civilite.Mr;
                if(this.civilite.getSelectedIndex() == 1) civ = Civilite.Mme;
                if(this.civilite.getSelectedIndex() == 2) civ = Civilite.Mlle;
                boolean result = this.clientServices.UpdateClient(
                        this.modifClientid,
                        this.nom.getText(),
                        this.prenom.getText(),
                        this.cin.getText(),
                        civ
                );
                if(result) {
                    int row = this.clientTable.getSelectedRow();
                    JOptionPane.showMessageDialog(null,"Client a ete modifie avec succes !");
                    this.tablemodel.setValueAt(this.nom.getText(),row,1);
                    this.tablemodel.setValueAt(this.prenom.getText(),row,2);
                    this.tablemodel.setValueAt(this.cin.getText(),row,3);
                    this.tablemodel.setValueAt(civ,row,4);
                }
                else {JOptionPane.showMessageDialog(null,"Aucun client ne correspond a cet id!");}
            }
        }
        /** Fin de Confimer Modification Client **/

    }

}
