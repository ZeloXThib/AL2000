package Modele;
import java.util.Date;
import java.util.ArrayList;



public class FilmPhysique extends Film{
    private Etat etat;

    public enum Etat {
        DISPONIBLE,
        CASSE,
        INDISPONIBLE
    };
    
    public FilmPhysique(String titre,String description,Integer anneeSortie, ArrayList<String>  categories,ArrayList<String>  acteurs,ArrayList<String>  producteurs,String affiche, int ageMin, int duree,Etat etat) {
		super(titre,description,anneeSortie,categories,acteurs,producteurs,affiche,ageMin,duree);
        this.etat=etat;
	}
    public FilmPhysique(String titre, Integer dateSortie, ArrayList<String> acteurs, ArrayList<String> producteurs, int ageMin) {
        super(titre, dateSortie, acteurs, producteurs, ageMin);
    }


    public Etat setEtat(Etat e){
        return this.etat = e; 
    }

    public Etat getEtat(){
        return etat;
    }
}