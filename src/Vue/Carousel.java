package Vue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Global.Configuration;
import Modele.FilmDigital;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;

public class Carousel extends JPanel {
    private MenuBouton[] buttons;

    JButton[] tBoutons = new JButton[2];
    int cptTBoutons = 0;
    int indexCarrousel = 0;

    int AfficheWidth = 250;
    int AfficheHeight = 300;



    final int NB_FILMS_CARROUSEL = 20;

    public Carousel(InterfaceGraphique ig) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        ArrayList<FilmDigital> films = Configuration.getDb().getFilmService().getFilmsDigitals();
        

        // systeme de carousssel qui fait "tourner" les films 
        FilmDigital[] filmsCaroussel = new FilmDigital[4];
        for (int i = 0; i < 4; i++) {
            filmsCaroussel[i] = films.get(i);
        }

        buttons = new MenuBouton[NB_FILMS_CARROUSEL];

        Runnable rightMovie = new Runnable() { // permet de faire tourner le carroussel vers la droite
            public void run() {

                indexCarrousel = (indexCarrousel + 1) % NB_FILMS_CARROUSEL;

                for (int i = 0; i < 4; i++) {
                    Image image = new ImageIcon(
                            "res/movies/" + films.get((indexCarrousel + i) % NB_FILMS_CARROUSEL).getAffiche()).getImage();
                    Image newimg = image.getScaledInstance(AfficheWidth, AfficheHeight, java.awt.Image.SCALE_SMOOTH);
                    buttons[i].setIcon(new ImageIcon(newimg));

                    filmsCaroussel[i] = films.get((indexCarrousel + i) % NB_FILMS_CARROUSEL);
                }
            }
        };

        Runnable leftMovie = new Runnable() { // permet de faire tourner le carroussel vers la gauche
            public void run() {

                indexCarrousel = (indexCarrousel - 1) % NB_FILMS_CARROUSEL;
                if (indexCarrousel < 0) {
                    indexCarrousel = NB_FILMS_CARROUSEL - 1;
                }

                for (int i = 0; i < 4; i++) {
                    Image image = new ImageIcon(
                            "res/movies/" + films.get((indexCarrousel + i) %NB_FILMS_CARROUSEL).getAffiche()).getImage();
                    Image newimg = image.getScaledInstance(AfficheWidth, AfficheHeight, java.awt.Image.SCALE_SMOOTH);
                    buttons[i].setIcon(new ImageIcon(newimg));

                    filmsCaroussel[i] = films.get((indexCarrousel + i) % NB_FILMS_CARROUSEL);

                }
            }
        };

        addButton(leftMovie, "fleche_gauche", null, 40, 40, false);
        for (int i = 0; i < 4; i++) { // on affiche les 4 premiers films du carroussel
            final int indexMovie = i;
            buttons[i] = new MenuBouton(new Runnable() {
                public void run() {
                    ArrayList<String> categories = new ArrayList<String>();
                    categories.add("Action");
                    categories.add("Comédie");

                    ArrayList<String> acteurs = new ArrayList<String>();
                    acteurs.add("Acteur 1");

                    ArrayList<String> producteurs = new ArrayList<String>();
                    producteurs.add("Producteur 1");

                    ig.switchToPageFilm(filmsCaroussel[indexMovie]);
                }
            }, null, 500, 400, false);

            Image image = new ImageIcon("res/movies/" + films.get(i).getAffiche()).getImage();
            Image newimg = image.getScaledInstance(AfficheWidth, AfficheHeight, java.awt.Image.SCALE_SMOOTH);
            buttons[i].setIcon(new ImageIcon(newimg));
            add(buttons[i]);
        }

        addButton(rightMovie, "fleche_droite", null, 40, 40, false);
    }

    private void addButton(Runnable action, String imageName, String texte, int widthImage, int heightImage,
            boolean estBoutonSolde) {
        tBoutons[cptTBoutons] = new MenuBouton(action, imageName, texte, widthImage, heightImage, estBoutonSolde,
                false);
        tBoutons[cptTBoutons].setPreferredSize(new Dimension(widthImage, heightImage));
        add(tBoutons[cptTBoutons]);
        cptTBoutons++;
    }
}