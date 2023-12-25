package Modele;
import java.util.ArrayList;
import java.util.Date;


public class FilmDigital extends Film{

    public String url;


	public FilmDigital(String titre,String description,Integer anneeSortie, ArrayList<String>  categories,ArrayList<String>  acteurs,ArrayList<String>  producteurs,String affiche, int ageMin, int duree,String url) {
		super(titre,description,anneeSortie,categories,acteurs,producteurs,affiche,ageMin,duree);
        this.url = url;
	}
	public String getUrl(){
		return url;
	}    
}