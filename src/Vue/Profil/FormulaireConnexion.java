package Vue.Profil;

import javax.swing.*;

import Global.Configuration;
import Modele.ClientAbonne;
import Modele.Dictionary;
import Vue.InterfaceGraphique;

import java.awt.*;
import java.awt.event.*;

public class FormulaireConnexion extends JPanel {
    private JTextField email;
    private JPasswordField mot_de_passe;
    private JFrame fenetre;
    private InterfaceGraphique ig;
    JPanel filtre;

    public FormulaireConnexion(InterfaceGraphique ig, JFrame fenetre, JPanel filtre) { // Création du formulaire de connexion
        this.fenetre = fenetre;
        this.filtre = filtre;
        this.ig = ig;
        setLayout(new BorderLayout());

        JLabel titreLabel = new JLabel(Dictionary.translate("Se connecter"));
        titreLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titreLabel, BorderLayout.NORTH);

        // Ajout des champs pour l'email et le mot de passe
        JPanel champsPanel = new JPanel();
        champsPanel.setLayout(new GridLayout(2, 2, 10, 10));

        champsPanel.add(new JLabel(Dictionary.translate("Email:")));
        email = new JTextField();
        champsPanel.add(email);

        champsPanel.add(new JLabel(Dictionary.translate("Mot de passe:")));
        mot_de_passe = new JPasswordField();
        champsPanel.add(mot_de_passe);

        add(champsPanel, BorderLayout.CENTER);

        addButtons();
        
    }

    public void addButtons() {

        JButton retour = new JButton(Dictionary.translate("Retour"));
        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fenetre.remove(filtre);
                fenetre.revalidate();
                fenetre.repaint();
            }
        });

        JButton connexion = new JButton(Dictionary.translate("Se connecter"));
        connexion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String inputPass = getPassword();
                String inputEmail = getEmail();

                ClientAbonne client = new ClientAbonne(inputEmail, null);
                client.setMdp(inputPass);
                try {
                    client = Configuration.getDb().getClientAbonneService().login(client);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                if (client == null) {
                    JOptionPane.showMessageDialog(null, Dictionary.translate("Email ou mot de passe incorrect"));
                } else {
                    System.out.println("s: " + client.getMdp());
                    Configuration.setLogged(true);
                    Configuration.setClientConnecte(client);
                    ig.switchToTopToolbarConnected();

                    JOptionPane.showMessageDialog(null, Dictionary.translate("Connexion réussie"));
                    // Configuration.setCurrentUser(client);
                    fenetre.remove(filtre);
                    fenetre.revalidate();
                    fenetre.repaint();
                }

                fenetre.remove(filtre);
                fenetre.revalidate();
                fenetre.repaint();
            }
        });

        JPanel boutonsPanel = new JPanel(new BorderLayout(10, 10));
        JPanel boutonsRetSub = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        boutonsRetSub.add(retour);
        boutonsRetSub.add(connexion);

        boutonsPanel.add(boutonsRetSub, BorderLayout.CENTER);
        add(boutonsPanel, BorderLayout.SOUTH);
    }

    public String getEmail() {
        return email.getText();
    }

    public String getPassword() {
        return new String(mot_de_passe.getPassword());
    }
}
