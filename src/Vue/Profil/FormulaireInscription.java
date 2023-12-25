package Vue.Profil;

import javax.swing.*;

import Modele.Dictionary;
import Vue.InterfaceGraphique;
import Global.Configuration;
import Modele.ClientAbonne;
import Modele.ClientNonAbonne;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

public class FormulaireInscription extends JPanel {
    private JTextField prenom;
    private JTextField nom;
    private JTextField date_naissance;
    private JTextField email;
    private JTextField adresse;
    private JPasswordField mot_de_passe;
    private FormulaireConnexion formulaireConnexion;


    private JFrame fenetre;
    private JPanel filtre;
    private InterfaceGraphique ig;

    public FormulaireInscription(InterfaceGraphique ig, JFrame fenetre, JPanel filtre) { // Creation du formulaire d'inscription 
        this.fenetre = fenetre;
        this.filtre = filtre;
        this.ig = ig;
        setLayout(new BorderLayout());

        JLabel titreLabel = new JLabel(Dictionary.translate("S'inscrire"));
        titreLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titreLabel, BorderLayout.NORTH);

        JPanel champsPanel = new JPanel();
        champsPanel.setLayout(new GridLayout(6, 2, 10, 10));

        champsPanel.add(new JLabel(Dictionary.translate("Prénom:")));
        prenom = new JTextField();
        champsPanel.add(prenom);

        champsPanel.add(new JLabel(Dictionary.translate("Nom:")));
        nom = new JTextField();
        champsPanel.add(nom);

        champsPanel.add(new JLabel(Dictionary.translate("Date de naissance:")));
        date_naissance = new JTextField();
        champsPanel.add(date_naissance);

        champsPanel.add(new JLabel(Dictionary.translate("Email:")));
        email = new JTextField();
        champsPanel.add(email);

        champsPanel.add(new JLabel(Dictionary.translate("Mot de passe:")));
        mot_de_passe = new JPasswordField();
        champsPanel.add(mot_de_passe);

        champsPanel.add(new JLabel(Dictionary.translate("Adresse:")));
        adresse = new JTextField();
        champsPanel.add(adresse);

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

        JButton submit = new JButton(Dictionary.translate("Créer"));
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientAbonne client = new ClientAbonne(getNom(), getPrenom(), getEmail(), getDateNaissance(), getPassword(), getAdresse(), null, null);
                client.setMdp(getPassword());
                client.setSolde(100); // TODO
                Configuration.getDb().getClientAbonneService().createClientAbonne(client);

                fenetre.remove(filtre);
                fenetre.revalidate();
                fenetre.repaint();
            }
        });

        JButton connexion = new JButton(Dictionary.translate("Déjà inscrit ? Se connecter"));
        connexion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fenetre.remove(filtre);
                fenetre.revalidate();
                fenetre.repaint();

                filtre = new Filtre(fenetre);
                formulaireConnexion = new FormulaireConnexion(ig, fenetre, filtre);
                filtre.add(formulaireConnexion, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                fenetre.add(filtre, 0);
                fenetre.revalidate();
                fenetre.repaint();
            }
        });

        JPanel boutonsPanel = new JPanel(new BorderLayout(10, 10));
        JPanel boutonsRetSub = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        boutonsRetSub.add(retour);
        boutonsRetSub.add(submit);

        boutonsPanel.add(boutonsRetSub, BorderLayout.CENTER);
        boutonsPanel.add(connexion, BorderLayout.SOUTH);

        add(boutonsPanel, BorderLayout.SOUTH);
    }

    public String getPrenom() {
        return prenom.getText();
    }

    public String getNom() {
        return nom.getText();
    }

    public Date getDateNaissance() {
        return new Date(0);
    }

    public String getEmail() {
        return email.getText();
    }

    public String getPassword() {
        return new String(mot_de_passe.getPassword());
    }

    public String getAdresse() {
        return new String(mot_de_passe.getPassword());
    }
}
