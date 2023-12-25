package Vue.Profil;

import javax.swing.*;

import Global.Configuration;

import java.awt.*;
import java.awt.event.*;

import Modele.Dictionary;
import Vue.InterfaceGraphique;

public class AjouterSolde extends JPanel{
    private JFrame fenetre;
    private InterfaceGraphique ig;
    private JTextField montant;
    JPanel filtre;

    public AjouterSolde(InterfaceGraphique ig, JFrame fenetre, Filtre filtre) { 
        this.fenetre = fenetre;
        this.filtre = filtre;
        this.ig = ig;
        setLayout(new BorderLayout());

        JLabel titreLabel = new JLabel(Dictionary.translate("Combien souhaitez-vous ajouter à votre compte ?"));
        titreLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titreLabel, BorderLayout.NORTH);

        JPanel champsPanel = new JPanel();
        champsPanel.setLayout(new GridLayout(2, 2, 10, 10));

        champsPanel.add(new JLabel(Dictionary.translate("Montant:")));
        montant = new JTextField();
        champsPanel.add(montant);

        add(champsPanel, BorderLayout.CENTER);

        addButtons();
        
    }

    public void addButtons() { // Ajout des boutons pour retourner au menu principal ou pour ajouter le solde
        JButton retour = new JButton(Dictionary.translate("Retour"));
        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fenetre.remove(filtre);
                fenetre.revalidate();
                fenetre.repaint();
            }
        });

        JButton crediter = new JButton(Dictionary.translate("Créditer"));
        crediter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Configuration.getLogged() == true){ // Si l'utilisateur est connecté, on ajoute le solde
                    ig.getControleur().notify(Double.parseDouble(montant.getText()), "recharger");
                    ig.switchToTopToolbarConnected();
                    JOptionPane.showMessageDialog(null, Dictionary.translate("Crédit ajouté"));
                }
                else
                { // Sinon on affiche un message d'erreur
                    JOptionPane.showMessageDialog(null, Dictionary.translate("Vous devez être connecté pour créditer votre compte"));
                }

                fenetre.remove(filtre);
                fenetre.revalidate();
                fenetre.repaint();
            }
        });

        JPanel boutonsPanel = new JPanel(new BorderLayout(10, 10));
        JPanel boutonsRetSub = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        boutonsRetSub.add(retour);
        boutonsRetSub.add(crediter);

        boutonsPanel.add(boutonsRetSub, BorderLayout.CENTER);
        add(boutonsPanel, BorderLayout.SOUTH);
    }
    
}
