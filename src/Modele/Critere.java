package Modele;

import java.util.ArrayList;

/*
 * Classe abstraite dont notre décorateur va hériter
 * Ainsi il sera possible de réaliser une recherche
 * de film en appliquant plusieurs critères
 */

public abstract class Critere {
    
    public abstract ArrayList<FilmPhysique> filtrer(ArrayList<FilmPhysique>films);
}
