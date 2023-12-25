package Modele;

import java.util.ArrayList;

/*
 * Classe décorateur dont
 * toutes nos classes de critère vont hérités.
 * permettant ainsi de lié les différentes
 * classes en fonction de nos besoins.
 */
public class DecoratorCritere extends Critere {
    private Critere wrappee;

    public DecoratorCritere(Critere c) {
        this.wrappee = c;
    }

    
    /** 
     * @param films
     * @return ArrayList<FilmPhysique>
     */
    @Override
    public ArrayList<FilmPhysique> filtrer(ArrayList<FilmPhysique> films) {
        return wrappee.filtrer(films);
    }
}

