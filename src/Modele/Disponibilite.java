package Modele;

import java.util.ArrayList;
/*
 * Cette classe hérite de DécoratorCritere, elle permet d'appliquer
 * le filtre de disponibilité sur une liste de film.
 * L'objectif est de renvoyer l'ensemble des films dont l'état
 * vaut celui qui a été choisi.
 */
public class Disponibilite extends DecoratorCritere {

    FilmPhysique.Etat etat;
    public Disponibilite(Critere c, FilmPhysique.Etat etat) {
        super(c);
        this.etat = etat;
    }
    
    public ArrayList<FilmPhysique> filtrer(ArrayList<FilmPhysique> films) {
        ArrayList<FilmPhysique> mesFilms = new ArrayList<FilmPhysique>();
        for (FilmPhysique film : super.filtrer(films)) {
            if ((film.getEtat())==this.etat) {
                mesFilms.add(film);
            }
        }
        return mesFilms;
    }
    
}
