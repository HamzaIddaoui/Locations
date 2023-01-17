package GUI;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private JLayeredPane layeredpane;
    private ImageIcon icon;
    private BackgroundJPanel backgroundPanel;
    private JPanel loginPanel,nomutilisateurPanel,mdpPanel;
    private JTextField nomutilisField;
    private JTextField mdpField;
    public Login(){
        super("Interface d'authentification");
        this.setSize(1000,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.layeredpane = new JLayeredPane();
        /** Background Panel **/
        this.backgroundPanel = new BackgroundJPanel(new ImageIcon(getClass().getResource("carbg.jpg")),
                                                1000,600
                );
        this.backgroundPanel.setOpaque(true);
        this.backgroundPanel.setBounds(0,0,1000,600);
        this.layeredpane.add(this.backgroundPanel,1);
        /** Fin de Background Panel **/

        /** Nom Utilisateur Panel **/
        this.nomutilisateurPanel = new JPanel();
        this.nomutilisateurPanel.setLayout(new FlowLayout());

        /** Login Panel **/
        this.loginPanel = new JPanel();
        this.loginPanel.setBackground(new Color(255,255,255));
        this.loginPanel.setBounds(600,100,350,450);
        this.loginPanel.setLayout(new GridLayout(5,1));
        this.loginPanel.setOpaque(true);

        /** Nom d'utilisateur Label **/
        JLabel nomUtilisateur = new JLabel("Nom d'utilisateur");
        nomUtilisateur.setFont(new Font(Font.SANS_SERIF,Font.BOLD,25));
        /** Fin de Nom d'utilisateur Label **/

        /** Nom d'utilisateur input **/
        this.nomutilisField = new JTextField(20);
        /** Fin de Nom d'utilisateur input **/

        this.nomutilisateurPanel.add(nomUtilisateur);
        this.nomutilisateurPanel.add(this.nomutilisField);
        this.nomutilisateurPanel.setBackground(new Color(255,255,255));
        this.loginPanel.add(this.nomutilisateurPanel);
        /** Fin de Nom utilisateur Panel **/

        /** Mot de passe Panel **/
        this.mdpPanel = new JPanel();
        this.mdpPanel.setLayout(new FlowLayout());
        this.mdpPanel.setBackground(new Color(255,255,255));

        /** Mot de passe Label **/
        JLabel mdp = new JLabel("Mot de passe");
        mdp.setFont(new Font("Courier New",Font.BOLD,25));
        /** Fin de Mot de passe Label **/

        /** Mot de passe input **/
        this.mdpField = new JPasswordField(20);
        /** Fin de Mot de passe input **/
        this.mdpPanel.add(mdp);
        this.mdpPanel.add(this.mdpField);
        this.loginPanel.add(this.mdpPanel);

        /** Fin de Mot de passe Panel **/

        this.layeredpane.add(this.loginPanel,0);
        /** Fin de Login Panel **/
        this.setLayeredPane(this.layeredpane);
        this.icon = new ImageIcon(getClass().getResource("carlogo.jpg"));
        this.setIconImage(this.icon.getImage());
        this.setLayout(null);
        this.setVisible(true);
    }
}
