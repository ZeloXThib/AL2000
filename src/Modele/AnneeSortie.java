package Modele;

import java.util.ArrayList;

/*
 * Classe permettant d'obtenir
 * les films sorti après "anneeSortie"
 * Si anneeSortie = 2009
 * Alors on obtient tous les films après 2009
 */




public class AnneeSortie extends DecoratorCritere {

    Integer anneeSortie;
    public AnneeSortie(Critere c, Integer anneeSortie) {
        super(c);
        this.anneeSortie = anneeSortie;
    }
    
    /** 
     * @param films
     * @return ArrayList<FilmPhysique>
     */
    public ArrayList<FilmPhysique> filtrer(ArrayList<FilmPhysique> films) {
        ArrayList<FilmPhysique> mesFilms = new ArrayList<FilmPhysique>();
        for (FilmPhysique film : super.filtrer(films)) {
            if (film.getSortie() >= anneeSortie) {
                mesFilms.add(film);
            }
        }
        return mesFilms;
    }
    
}
