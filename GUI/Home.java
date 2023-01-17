package GUI;

import MesClasses.Agence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Home extends JFrame implements ActionListener, WindowListener {
    private Agence agence;
    private ImageIcon icon;
    private JLayeredPane layeredpane;
    private JPanel homePanel,sidebarPanel;
    private ClientInterface clientPanel;
    private VoitureInterface voiturePanel;
    private LocationInterface locationPanel;
    private JButton client_btn,location_btn,voiture_btn;
    private CardLayout cardLayout;
    public Home(Agence agence) {
        super("Home");
        this.addWindowListener(this);
        this.agence = agence;
        this.setSize(1000,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.icon = new ImageIcon(getClass().getResource("carlogo.jpg"));
        this.setIconImage(this.icon.getImage());
        this.layeredpane = new JLayeredPane();
        this.layeredpane.setLayout(new BorderLayout());
        this.setLayeredPane(this.layeredpane);
        /** Home Panel **/
        this.homePanel = new JPanel();
        this.clientPanel = new ClientInterface(this.agence);
        this.voiturePanel = new VoitureInterface(this.agence);
        this.locationPanel = new LocationInterface(this.agence);
        this.cardLayout = new CardLayout();
        this.homePanel.setLayout(this.cardLayout);
        this.homePanel.add(this.clientPanel,"Clients");
        this.homePanel.add(this.voiturePanel,"Voitures");
        this.homePanel.add(this.locationPanel, "Locations");
        this.homePanel.setBackground(new Color(160,175,183));
        this.layeredpane.add(this.homePanel,BorderLayout.CENTER);
        /** Fin de Home Panel **/
        /** Side Bar **/
        this.sidebarPanel = new JPanel();
        this.sidebarPanel.setLayout(new GridLayout(13,1));
        this.client_btn = new JButton("Clients");
        this.client_btn.addActionListener(this);
        this.client_btn.setBackground(new Color(255,255,255));
        this.voiture_btn = new JButton("Voitures");
        this.voiture_btn.addActionListener(this);
        this.voiture_btn.setBackground(new Color(255,255,255));
        this.location_btn = new JButton("Locations");
        this.location_btn.addActionListener(this);
        this.location_btn.setBackground(new Color(255,255,255));
        this.sidebarPanel.add(this.location_btn);
        this.sidebarPanel.add(this.voiture_btn);
        this.sidebarPanel.add(this.client_btn);
        this.sidebarPanel.setBackground(new Color(255,255,255));
        this.layeredpane.add(this.sidebarPanel,BorderLayout.WEST);

        /** Fin de Side Bar **/
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.client_btn)
            this.cardLayout.show(this.homePanel,"Clients");
        if(e.getSource() == this.voiture_btn)
            this.cardLayout.show(this.homePanel,"Voitures");
        if(e.getSource() == this.location_btn)
            this.cardLayout.show(this.homePanel, "Locations");

    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
