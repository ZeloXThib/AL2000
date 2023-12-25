package Modele;

import java.util.ArrayList;

/*
 * Cette classe hérite de DécoratorCritere, elle permet d'appliquer
 * le filtre producteur sur une liste de film.
 * Pour un producteur donné, nous allons renvoyer tous 
 * les films dans lequel le producteur apparait. 
 */

public class Producteur extends DecoratorCritere {
    String producteur;
    public Producteur(Critere c, String producteur) {
        super(c);
        this.producteur=producteur;
    }


    
    /** 
     * @param producteurs
     * @param monProducteur
     * @return boolean
     */
    public boolean verifierProducteur(ArrayList<String> producteurs, String monProducteur){
        for (String producteur : producteurs) {
            if (producteur.equals(monProducteur)) {
                return true;
            }
        }
        return false;
    }



    public ArrayList<FilmPhysique> filtrer(ArrayList<FilmPhysique> films) {
        ArrayList<FilmPhysique> mesFilms = new ArrayList<FilmPhysique>();
        for (FilmPhysique film : films) {
            if (film.getProducteur().contains(producteur) == true) {
                mesFilms.add(film);
            }
        }
        return mesFilms;
    }
}
