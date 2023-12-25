package Modele;

import java.util.ArrayList;

/*
 * Cette classe hérite de DécoratorCritere, elle permet d'appliquer
 * le filtre Titre sur une liste de film.
 * Pour un titre de film donné, nous allons renvoyer le film
 * qui est associé a ce titre.  
 */

public class Titre extends DecoratorCritere {
    String titre;
    public Titre(Critere c, String titre) {
        super(c);
        this.titre=titre;
    }



    
    /** 
     * @param films
     * @return ArrayList<FilmPhysique>
     */
    public ArrayList<FilmPhysique> filtrer(ArrayList<FilmPhysique> films) {
        ArrayList<FilmPhysique> mesFilms = new ArrayList<FilmPhysique>();
        for (FilmPhysique film : super.filtrer(films)) {
            if (film.getTitre().equals(titre)==true) {
                mesFilms.add(film);
            }
        }
        return mesFilms;
    }
}
