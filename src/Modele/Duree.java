package Modele;

import java.util.ArrayList;

/*
 * Cette classe hérite de DécoratorCritere, elle permet d'appliquer
 * le filtre Duree sur une liste de film.
 * Pour un durée donné, nous allons renvoyer tous 
 * les films dans lequel la durée est inférieur à "duree" 
 */


public class Duree extends DecoratorCritere{
    int duree;
    public Duree(Critere c, int duree) {
        super(c);
        this.duree = duree;
    }
    public ArrayList<FilmPhysique> filtrer(ArrayList<FilmPhysique> films) {
        ArrayList<FilmPhysique> mesFilms = new ArrayList<FilmPhysique>();
        for (FilmPhysique film : super.filtrer(films)) {
            if (film.getDuree() < duree) {
                mesFilms.add(film);
            }
        }
        return mesFilms;
    }

}
