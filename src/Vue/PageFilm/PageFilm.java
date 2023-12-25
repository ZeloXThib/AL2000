package Vue.PageFilm;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.FontMetrics;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controleur.Controleur;
import Global.Configuration;
import Modele.CarteBancaire;
import Modele.ClientNonAbonne;
import Modele.Dictionary;
import Modele.Film;
import Modele.Location;
import Vue.InterfaceGraphique;
import Vue.MenuBouton;

public class PageFilm extends JComponent {

    private static final int AFFICHE_HEIGHT = 550;
    private static final int BUTTON_WIDTH = 400;
    private static final int BUTTON_HEIGHT = 40;
    private static final int BUTTON_MARGIN_TOP = 20;
    private static final int BUTTON_RETURN_WIDTH = 250;
    private static final int BUTTON_RETURN_HEIGHT = 50;
    private static final int BUTTON_RETURN_MARGIN_RIGHT = 280;
    private static final int OVERLAY_HEIGHT = 400;
    private static final int TITLE_FONT_SIZE = 22;
    private static final int SUBTITLE_FONT_SIZE = 14;
    private static final int DESCRIPTION_FONT_SIZE = 12;
    private static final int DESCRIPTION_MARGIN_TOP = 70;

    private InterfaceGraphique ig;
    private Film film;
    private MenuBouton bouton_location_digitale;
    private MenuBouton bouton_location_physique;
    private MenuBouton boutonRetour;
    private Boolean estPhysiquementDisponible;

    public PageFilm(InterfaceGraphique ig, Film film, Boolean estPhysiquementDisponible) {
        this.film = film;
        this.ig = ig;
        this.estPhysiquementDisponible = estPhysiquementDisponible;

        this.gestionBoutonsLocation();

        ArrayList<String> cats = new ArrayList<String>();
        for (String cat : film.getCategories()) {
            cats.add(cat);
        }
        this.film.setCategories(cats);

        ArrayList<String> acteurs = new ArrayList<String>();
        for (String acteur : film.getActeurs()) {
            acteurs.add(acteur);
        }
        this.film.setActeurs(acteurs);

        ArrayList<String> prod = new ArrayList<String>();

        for (String producteur : film.getProducteur()) {
            prod.add(producteur);
        }
        this.film.setProducteur(prod);

    }

    private void gestionBoutonsLocation() {
        bouton_location_digitale = new MenuBouton(new Runnable() {
            public void run() {
                Location location = new Location(ig.getControleur());
                // location.setClient(Configuration.getClientConnecte());
                if (Configuration.getClientConnecte() != null) {
                    location.setClient(Configuration.getClientConnecte());
                } else {
                    JTextField email = new JTextField();
                    JPasswordField mdp = new JPasswordField();
                    JTextField numeroDeCarte = new JTextField();
                    Object[] message = { Dictionary.translate("Email:"), email, Dictionary.translate("Mot de passe:"), mdp ,  Dictionary.translate("Numéro de carte bancaire:"), numeroDeCarte};

                    int option = JOptionPane.showConfirmDialog(null, message, Dictionary.translate("Nouveau client"),
                            JOptionPane.OK_CANCEL_OPTION);
                            
                    if (option == JOptionPane.OK_OPTION) {
                        ClientNonAbonne clientNonAbo = new ClientNonAbonne(email.getText(),
                                new CarteBancaire(numeroDeCarte.getText()), ig.getControleur());
                        Configuration.getDb().getClientNonAbonneService().createClientNonAbonne(clientNonAbo);
                        location.setClient(clientNonAbo);
                    }

                }
                location.setFilm(film);
                location.setDateLocation(new Date());

                location.louerFilm();
            }
        }, "background_bouton", Dictionary.translate("Louer au format digital"), BUTTON_WIDTH, AFFICHE_HEIGHT + BUTTON_MARGIN_TOP);

        if (estPhysiquementDisponible) {
            bouton_location_physique = new MenuBouton(null, "background_bouton", Dictionary.translate("Louer au format blueray"),
                    BUTTON_WIDTH, AFFICHE_HEIGHT + BUTTON_MARGIN_TOP);
            bouton_location_physique.setBounds(50, AFFICHE_HEIGHT + 2 * BUTTON_MARGIN_TOP + 30, BUTTON_WIDTH,
                    BUTTON_HEIGHT);
            add(bouton_location_physique);
        } else {
            JLabel labelPhysiquementIndisponible = new JLabel(Dictionary.translate("Louer au format blueray (indisponible)"));
            configurerLabel(labelPhysiquementIndisponible);
            labelPhysiquementIndisponible.setBounds(50, AFFICHE_HEIGHT + 2 * BUTTON_MARGIN_TOP + 30, BUTTON_WIDTH,
                    BUTTON_HEIGHT);
            add(labelPhysiquementIndisponible);
        }

        bouton_location_digitale.setBounds(50, AFFICHE_HEIGHT + BUTTON_MARGIN_TOP, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(bouton_location_digitale);
    }

    private void configurerLabel(JLabel label) {
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE);
        label.setBackground(Color.GRAY);
        label.setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Charger l'affiche du film
        Image afficheImage = new ImageIcon("res/movies/" + film.getAffiche()).getImage();

        String overlayPath = "res/movies/" + film.getAffiche();
        overlayPath = overlayPath.substring(0, overlayPath.length() - 10) + "overlay.jpg";
        Image overlayImage = new ImageIcon(overlayPath).getImage();

        // Assombrir l'image overlay
        Graphics2D g2d = (Graphics2D) g.create();
        float alpha = 0.7f;
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alphaComposite);
        g2d.setColor(new Color(0, 0, 0)); // Couleur noire
        g2d.drawImage(overlayImage, 0, 0, getWidth(), OVERLAY_HEIGHT, this);
        g2d.fillRect(0, 0, getWidth(), OVERLAY_HEIGHT);
        g2d.dispose();

        g.setColor(new Color(25, 42, 79)); // Couleur #192a4f
        g.fillRect(0, OVERLAY_HEIGHT, getWidth(), getHeight() - OVERLAY_HEIGHT);

        boutonRetour = new MenuBouton(() -> ig.switchToMenuPrincipal(), "background_bouton",
                Dictionary.translate("Retour vers Menu Principal"), BUTTON_RETURN_WIDTH, BUTTON_RETURN_HEIGHT);
        boutonRetour.setBounds(getWidth() - BUTTON_RETURN_MARGIN_RIGHT, 15, BUTTON_RETURN_WIDTH, BUTTON_RETURN_HEIGHT);
        add(boutonRetour);

        // Afficher l'affiche en haut à gauche
        g.drawImage(afficheImage, 50, 15, BUTTON_WIDTH, AFFICHE_HEIGHT, this);

        int padding_left = 460;

        // Afficher le titre
        g.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));
        g.setColor(Color.WHITE);
        g.drawString(film.getTitre(), padding_left, 430);

        // Afficher l'année de sortie
        g.setFont(new Font("Arial", Font.PLAIN, SUBTITLE_FONT_SIZE));
        g.drawString(Dictionary.translate("Date de sortie: ") + film.getSortie(), padding_left, 450);
        // Afficher la liste de catégories
        g.drawString("Catégories:", padding_left, 470);
        int y = 490;
        for (String categorie : film.getCategories()) {
            g.drawString("- " + categorie, padding_left, y);
            y += 20;
        }

        // Définir les coordonnées pour acteurs et producteurs à droite
        int xDroite = getWidth() - 200;

        // Afficher la liste d'acteurs à droite
        g.drawString(Dictionary.translate("Acteurs:"), xDroite, 430);
        int yActeurs = 450;
        for (String acteur : film.getActeurs()) {
            g.drawString("- " + acteur, xDroite, yActeurs);
            yActeurs += 20;
        }

        // Afficher le producteur à droite
        g.drawString(Dictionary.translate("Producteur:"), xDroite, yActeurs);
        int yProducteur = yActeurs + 20;
        for (String producteur : film.getProducteur()) {
            g.drawString("- " + producteur, xDroite, yProducteur);
            yProducteur += 20;
        }

        // Afficher l'âge minimum
        g.drawString(Dictionary.translate("Age minimum: ") + film.getAgeMin() + Dictionary.translate(" ans"), padding_left, yProducteur + 20);

        // Afficher la description
        String description = film.getDescription();
        g.drawString("Description:", padding_left, yProducteur + 50);
        g.setFont(new Font("Arial", Font.PLAIN, DESCRIPTION_FONT_SIZE));

        int maxWidth = getWidth() - padding_left - 20; // Limite de largeur pour la description
        List<String> lines = getWrappedTextLines(description, g.getFontMetrics(), maxWidth);

        int yDescription = yProducteur + DESCRIPTION_MARGIN_TOP;
        for (String line : lines) {
            g.drawString(line, padding_left, yDescription);
            yDescription += g.getFontMetrics().getHeight();
        }
    }

    // Méthode pour diviser le texte en lignes en fonction de la largeur maximale
    private List<String> getWrappedTextLines(String text, FontMetrics fontMetrics, int maxWidth) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder(words[0]);

        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            int currentWidth = fontMetrics.stringWidth(currentLine.toString() + " " + word);

            if (currentWidth <= maxWidth) {
                currentLine.append(" ").append(word);
            } else {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            }
        }

        lines.add(currentLine.toString()); // Ajouter la dernière ligne

        return lines;
    }
}