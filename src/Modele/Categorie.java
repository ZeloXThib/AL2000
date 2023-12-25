package Modele;

import java.util.ArrayList;

/*
 * Cette classe hérite de DécoratorCritere, elle permet d'appliquer
 * une catégorie sur une recherche de film.
 * Pour un catégorie donnée, nous allons renvoyer tous 
 * les films dans lequel cette catégorie  apparait" 
 */

public class Categorie extends DecoratorCritere {
    String uneCategorie;
    public Categorie(Critere c, String uneCategorie) {
        super(c);
    }

    
    /** 
     * @param films
     * @return ArrayList<FilmPhysique>
     */
    public ArrayList<FilmPhysique> filtrer(ArrayList<FilmPhysique> films) {
        ArrayList<FilmPhysique> mesFilms = new ArrayList<FilmPhysique>();
        for (FilmPhysique film : super.filtrer(films)) {
            if (film.getCategories().contains(uneCategorie) == true) {
                mesFilms.add(film);
            }
        }
        return mesFilms;
    }
    
}
