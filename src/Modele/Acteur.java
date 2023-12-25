package Modele;

import java.util.ArrayList;

/*
 * Cette classe hérite de DécoratorCritere, elle permet d'appliquer
 * le filtre Acteur sur une liste de film.
 * Pour un acteur donné, nous allons renvoyer tous 
 * les films dans lequel l'acteur apparait. 
 */


public class Acteur extends DecoratorCritere {
    String acteur;
    public Acteur(Critere c, String acteur) {
        super(c);
        this.acteur = acteur;
    }

    
    /** 
     * @param films
     * @return ArrayList<FilmPhysique>
     */
    public ArrayList<FilmPhysique> filtrer(ArrayList<FilmPhysique> films) {
        ArrayList<FilmPhysique> mesFilms = new ArrayList<FilmPhysique>();
        for (FilmPhysique film : super.filtrer(films)) {
            if (film.getActeurs().contains(acteur) == true) {
                mesFilms.add(film);
            }
        }
        return mesFilms;
    }
    
}
