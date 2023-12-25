package Modele;

import java.util.ArrayList;

/*
 * Cette classe permet d'initialiser notre décorateur 
 * lorsque qu'aucun critère n'est ajouté.
 */

class CritereBase extends Critere {
    @Override
    public ArrayList<FilmPhysique> filtrer(ArrayList<FilmPhysique> films) {
        return films;
    }
}
