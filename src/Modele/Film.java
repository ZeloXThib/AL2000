package Modele;
import java.util.Date;
import java.util.ArrayList;

public abstract class Film{
    private String titre;
    private String description;
    private Integer anneeSortie;
    private ArrayList<String>  categories;
    private ArrayList<String>  acteurs;
    private ArrayList<String>  producteurs;
    private String affiche;
    private Integer ageMin;
    private Integer duree;

    public Film(String titre,String description,Integer anneeSortie, ArrayList<String>  categories,ArrayList<String>  acteurs,ArrayList<String>  producteurs,String affiche, int ageMin, int duree){
        this.titre=titre;
        this.anneeSortie = anneeSortie;
        this.ageMin=ageMin;
        this.duree=duree;
        this.description=description;
        this.affiche=affiche;
        this.acteurs = acteurs;
        this.producteurs = producteurs;
        this.categories = categories;
    }
    
    //Constructeur pour les tests du décorateur
    public Film(String titre,Integer anneeSortie,ArrayList<String>  acteurs,ArrayList<String>  producteurs,int ageMin){
        this.titre = titre;
        this.anneeSortie = anneeSortie;
        this.acteurs = acteurs;
        this.producteurs = producteurs;
        this.ageMin = ageMin;
    }
    
    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }


    public Integer getSortie(){
        return anneeSortie;
    }

    public ArrayList<String>  getCategories(){
        return categories;
    }

    public int getNombreCategories(){
        return categories.size();
    }


    public ArrayList<String>  getActeurs(){
        return acteurs;

    }

    public ArrayList<String>  getProducteur(){
        return producteurs;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public int getDuree() {
        return duree;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSortie(Integer sortie) {
        this.anneeSortie = sortie;
    }

    public void setCategories(ArrayList<String>  categories) {
        this.categories = categories;
    }

    public void setActeurs(ArrayList<String>  acteurs) {
        this.acteurs = acteurs;
    }

    public void setProducteur(ArrayList<String>  producteur) {
        this.producteurs = producteur;
    }
}