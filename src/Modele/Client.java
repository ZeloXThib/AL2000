package Modele;

import java.util.ArrayList;

import Controleur.Controleur;

public abstract class Client {
    protected CarteBancaire carteBancaire;
    protected String nom;
    protected String prenom;
    protected String email;
    protected Controleur controleur;
    ArrayList<Film>  historique;


    public Client(String nom, String prenom, CarteBancaire carteB, String email, Controleur controleur) {
        this.nom=nom;
        this.prenom=prenom;
        this.carteBancaire = carteB;
        this.email = email;
        this.controleur = controleur;
        historique = new ArrayList<Film>();
    }

    public Client(String email, Controleur controleur){
        this.email = email;
        this.controleur = controleur;
        historique = new ArrayList<Film>();
    }

    public Client(String email, CarteBancaire cb, Controleur controleur) {
        this.email = email;
        this.carteBancaire = cb;
        this.controleur = controleur;
        historique = new ArrayList<Film>();
    }
    
    public void ajouterHistorique(Film film){
        historique.add(film);
    } 

    public ArrayList<Film> getHistorique(){
        return historique;
    }

    public void supprimerHistorique(Film film){
        historique.remove(film);
    }

    public String getEmail(){
        return email;
    }

    public CarteBancaire getCarteBancaire() {
        return carteBancaire;
    }

    public void setCarteBancaire(CarteBancaire carteBancaire) {
        this.carteBancaire = carteBancaire;
    }

    public void setControleur(Controleur controleur){
        this.controleur = controleur;
    }

}