package Vue.TopToolbar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.ButtonUI;

import Global.Configuration;
import Modele.Dictionary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;

import java.awt.Font;
import Vue.InterfaceGraphique;
import Vue.MenuBouton;

public class TopToolbar extends JPanel {
    InterfaceGraphique ig;
    JButton[] tBoutons = new JButton[4];
    int cptTBoutons = 0;
    SearchBar tSearchBar = new SearchBar(80);

    public TopToolbar(InterfaceGraphique ig) {
        this.ig = ig;
        Runnable commanderFilm = new Runnable() {
            public void run() {
                // ig.switchToCommanderFilm();
                // pas encore implémenté
            }
        };

        setBackground(new Color(53, 88, 166));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        addAllComponents();
        addBoutonConnection();
    }

    public void refreshToolBar() {
        cptTBoutons = 0;
        removeAll();
        addAllComponents();
        addBoutonConnection();
        revalidate();
        repaint();
    }

    private void addBoutonConnection() {
        Runnable deconnexion = new Runnable() {
            public void run() {
                Configuration.setLogged(false);
                refreshToolBar();
            }
        };

        Runnable modifierProfil = new Runnable() {
            public void run() {
                ig.switchToModifierProfil();
            }
        };

        Runnable ajouterSolde = new Runnable() {
            public void run() {
                ig.switchToAjouterSolde();
            }
        };

        if (Configuration.getLogged()) { // Si l'utilisateur est connecté
            addButton(deconnexion, "profil", null, 40, 40, false);
            addButton(ajouterSolde, "solde", Configuration.getClientConnecte().getSolde() + " €", 80, 40, true);

        } else { // Si l'utilisateur n'est pas connecté
            addButton(modifierProfil, "profil", null, 40, 40, false);
        }
    }

    private void addAllComponents() {
        addButton(null, "background_bouton", Dictionary.translate("Commander film"), 150, 40, false);
        addSearchBar();
        addButton(null, "favoris", null, 40, 40, false);
    }

    private void addButton(Runnable action, String imageName, String texte, int widthImage, int heightImage,
            boolean estBoutonSolde) {
        tBoutons[cptTBoutons] = new MenuBouton(action, imageName, texte, widthImage, heightImage, estBoutonSolde);
        tBoutons[cptTBoutons].setPreferredSize(new Dimension(widthImage, heightImage));
        add(tBoutons[cptTBoutons]);
        cptTBoutons++;
    }

    private void addSearchBar() { // Barre de recherche
        JPanel searchBarContainer = new JPanel();
        searchBarContainer.setPreferredSize(new Dimension(1000, 40));
        searchBarContainer.setBackground(new Color(53, 88, 166));
        tSearchBar.setPreferredSize(new Dimension(tSearchBar.getPreferredSize().width, 30));
        searchBarContainer.add(tSearchBar);
        add(searchBarContainer);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}