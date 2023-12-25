package Modele;

import java.util.ArrayList;

/*
 * Cette classe hérite de DécoratorCritere, elle permet d'appliquer
 * le filtre Age sur une liste de film.
 * Pour un age donné, nous allons renvoyer tous 
 * les films dans lequel l'age minimal est inférieur à "AGE" 
 */

public class Age extends DecoratorCritere {
    int age;

    public Age(Critere c, int age) {
        super(c);
        this.age = age;
    }

    
    /** 
     * @param films
     * @return ArrayList<FilmPhysique>
     */
    @Override
    public ArrayList<FilmPhysique> filtrer(ArrayList<FilmPhysique> films) {
        ArrayList<FilmPhysique> mesFilms = new ArrayList<FilmPhysique>();
        for (FilmPhysique film : super.filtrer(films)) {
            if (film.getAgeMin() >= age) {
                mesFilms.add(film);
            }
        }
        return mesFilms;
    }
}

