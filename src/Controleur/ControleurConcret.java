package Controleur;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import Global.Configuration;
import Modele.Client;
import Modele.ClientAbonne;
import Modele.FilmDigital;
import Modele.FilmPhysique;
import Modele.Location;
import Vue.InterfaceGraphique;
import Modele.Catalogue;

public class ControleurConcret implements Controleur {

    Location location;
    Client client;
    InterfaceGraphique ig;

    public ControleurConcret(InterfaceGraphique ig) {
        this.ig = ig;

    }

    public ArrayList<FilmDigital> getFilmsDigitals() {
        return Configuration.getDb().getFilmService().getFilmsDigitals();
    }

    //Permet de faire correspondre les classes entre elles
    //Afin de louer un film, rendre un film et recharger la carte.
    public void notify(Object object, String message) {
        if (object instanceof Location) {
            location = (Location) object;
            client = location.getClient();
            switch (message) {
                case "louerFilm":
                    JOptionPane.showMessageDialog(null, "Film loué", "Information", JOptionPane.INFORMATION_MESSAGE);
                    client.ajouterHistorique(location.getFilm());
                    ClientAbonne clientAbonne = (ClientAbonne) client;
                    clientAbonne.incrementerNbFilmLoue();
                    clientAbonne.debiter(location.getPrix());
                    ig.getTopToolbar().refreshToolBar();

                    if (location.getFilm() instanceof FilmPhysique) {
                        FilmPhysique filmPhysique = (FilmPhysique) location.getFilm();
                        Catalogue.changerEtat(filmPhysique, FilmPhysique.Etat.INDISPONIBLE);
                    }
                    break;
                case "rendreFilm":
                    if (location.getFilm() instanceof FilmPhysique) {
                        FilmPhysique filmPhysique = (FilmPhysique) location.getFilm();
                        Catalogue.changerEtat(filmPhysique, FilmPhysique.Etat.DISPONIBLE);
                    }
                    break;
                default:
                    break;
            }
        }
        if (object instanceof Double) {
            switch (message) {
                case "recharger": {
                    Configuration.getDb().getCarteAbonneService()
                            .recharger(Configuration.getClientConnecte().getcarteAbonne().get(0), (Double) object);
                    Configuration.getClientConnecte()
                            .setSolde(Configuration.getClientConnecte().getSolde() + (Double) object);
                }
                    break;
                default:
                    break;
            }
        }
    }
}
