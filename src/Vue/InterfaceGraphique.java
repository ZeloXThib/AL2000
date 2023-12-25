package Vue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controleur.Controleur;
import Controleur.ControleurConcret;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Image;
import java.awt.event.*;
import java.util.HashMap;

import Global.Configuration;
import Modele.Film;
import Vue.PageFilm.PageFilm;
import Vue.Profil.AjouterSolde;
import Vue.Profil.Filtre;
import Vue.Profil.FormulaireInscription;
import Vue.TopToolbar.TopToolbar;

public class InterfaceGraphique implements Runnable, InterfaceUtilisateur {
    Controleur controleur;
    JFrame fenetre;
    JPanel languagePanel;
    TopToolbar topToolbar;
    MenuPrincipal menuPrincipal;
    MenuMaintenance menuMaintenance;
    HashMap<String, Image> imagesCache = new HashMap<String, Image>();
    Filtre filtre;

    public void creationFenetre() {
        // Création de la fenêtre principale
        Configuration.info("Creation de la fenetre principale");
        fenetre = new JFrame();
        fenetre.setTitle("AL2000");
        fenetre.setSize(1400, 900);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
        fenetre.setResizable(false);
        Configuration.info("Fenetre principale créée");

        Menu expressionMenu = new Menu("Shop Owner");
        expressionMenu.add("Se connecter");
        MenuBar menuBar = new MenuBar();
        menuBar.add(expressionMenu);
        fenetre.setMenuBar(menuBar);

        fenetre.getContentPane().setBackground(new Color(25, 42, 79));


        expressionMenu.getItem(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = JOptionPane.showInputDialog(fenetre, "Entrez votre code", "Connexion",
                        JOptionPane.QUESTION_MESSAGE);
                if (code != null) {
                    if (code.equals(Configuration.getCode())) {
                        // TODO
                        fenetre.remove(menuPrincipal);
                        fenetre.add(menuMaintenance);
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(fenetre, "Code incorrect", "Connexion",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

    public void run() {
        creationFenetre();
        creerMenuPrincipal();
        creerTopToolbar();
        creerMenuMaintenance();
    }

    public void refresh() {
        fenetre.revalidate();
        fenetre.repaint();
    }

    public static void demarrer() {
        InterfaceGraphique vue = new InterfaceGraphique();
        vue.controleur = new ControleurConcret(vue);
        SwingUtilities.invokeLater(vue);
    }

    private void creerMenuPrincipal() {
        menuPrincipal = new MenuPrincipal(this);
        fenetre.add(menuPrincipal);

        refresh();
    }

    private void creerTopToolbar() {
        topToolbar = new TopToolbar(this);
        topToolbar.setSize(new Dimension(fenetre.getWidth(), 70));
        fenetre.add(topToolbar, BorderLayout.PAGE_START);

        topToolbar.setBounds(0, 0, fenetre.getWidth(), 50);
        refresh();
    }

    public void addTopToolbarToWindow() {
        fenetre.add(topToolbar, BorderLayout.PAGE_START);
        refresh();
    }

    public void creerMenuMaintenance() {
        menuMaintenance = new MenuMaintenance(this);
    }

    public void switchToMenuMaintenance() {
        fenetre.getContentPane().removeAll();
        fenetre.add(menuMaintenance);
        refresh();
    }

    public void switchToTopToolbarConnected() {
        topToolbar.refreshToolBar();
        refresh();
    }

    public void switchToMenuPrincipal() {
        fenetre.getContentPane().removeAll();
        addTopToolbarToWindow();
        fenetre.add(menuPrincipal);
        refresh();
    }

    public void switchToAjouterSolde() {
        filtre = new Filtre(fenetre);
        AjouterSolde ajoutSolde = new AjouterSolde(this, fenetre, filtre);

        filtre.add(ajoutSolde, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        fenetre.add(filtre, 0);
        refresh();
    }

    public void switchToModifierProfil() {
        filtre = new Filtre(fenetre);
        FormulaireInscription registrationForm = new FormulaireInscription(this, fenetre, filtre);

        filtre.add(registrationForm, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        fenetre.add(filtre, 0);
        refresh();
    }

    public void switchToPageFilm(Film film) {
        fenetre.getContentPane().removeAll();
        addTopToolbarToWindow();

        System.out.println(film.getTitre());
        PageFilm pageFilm = new PageFilm(this, film, true);
        fenetre.add(pageFilm);
        refresh();
    }

    public void addLanguagePanel() { 
        languagePanel = new JPanel();
        languagePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        fenetre.add(languagePanel, BorderLayout.NORTH);

        ImageIcon englishFlag = new ImageIcon("res/Image/englishflag.png");
        ImageIcon frenchFlag = new ImageIcon("res/Image/frenchflag.png");
        Image image = englishFlag.getImage();
        Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        englishFlag = new ImageIcon(newimg);

        image = frenchFlag.getImage();
        newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        frenchFlag = new ImageIcon(newimg);

        JButton englishButton = new JButton(englishFlag);
        JButton frenchButton = new JButton(frenchFlag);

        languagePanel.add(englishButton);
        languagePanel.add(frenchButton);
    }

    public Controleur getControleur() {
        return controleur;
    }

    public TopToolbar getTopToolbar() {
        return topToolbar;
    }
}