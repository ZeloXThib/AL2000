package Modele;
import java.util.ArrayList;

import Global.Configuration;

public class Catalogue {

    /*
     * Récupération des films grâce à la dao
     * Nous avons les films physiques disponible et indisponible
     * Ainsi que les digitaux qui sont toujours disponibles eux.
     * Egalement la liste des promo
     */


    ArrayList<FilmPhysique> listePhysique = new ArrayList<FilmPhysique>();
    ArrayList<FilmDigital> listeDigital = new ArrayList<FilmDigital>();
    ArrayList<Film> listePromo = new ArrayList<Film>();

    public Catalogue(){
        // TODO
        listePhysique = Configuration.getDb().getFilmService().getFilmsPhysiques();
        listeDigital = Configuration.getDb().getFilmService().getFilmsDigitals(); 
        listePromo = Configuration.getDb().getFilmService().getFilmsPromo();
    }
    /*
     * Getter pour nos listes de films
     */

    public ArrayList<FilmPhysique> getListePhysique(){
        return listePhysique;
    }

    public ArrayList<FilmDigital> listeDigital(){
        return listeDigital;
    }


    /*
     * Modifier l'état d'un film physique (DISPO/CASSE/INDISPO)
     */

    public static void changerEtat(FilmPhysique f, FilmPhysique.Etat e){
        f.setEtat(e);
    }

    public ArrayList<FilmPhysique> rechercher(Critere critere){
        ArrayList<FilmPhysique> resultatsFilm = critere.filtrer(this.listePhysique);
        return resultatsFilm;  
    }

    public Film getTopLocationSemaine(){
        return Configuration.getDb().getFilmService().getTopLocationSemaine();
    }

    public Film getTopLocationMois(){
        return Configuration.getDb().getFilmService().getTopLocationMois();
    }
}


