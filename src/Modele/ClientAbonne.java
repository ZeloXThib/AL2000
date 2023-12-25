package Modele;
import java.util.Date;
import Controleur.Controleur;
import Global.Configuration;

import java.util.ArrayList;

public class ClientAbonne extends Client{
    private ArrayList<CarteAbonne> carteAbonnes;
    private ArrayList<Film>  favoris;
    private ArrayList<ClientEnfant>  enfants;
    private String mdp;
    private String adresse;
    private Date dateDeNaissance;
    private int nbFilmLoue;
    private double solde;


    public ClientAbonne(String nom, String prenom, String email, java.util.Date dateDeNaissance,String mdp, String adresse, CarteBancaire carteB, Controleur controleur){
        super(nom, prenom, carteB, email, controleur);
        this.dateDeNaissance = dateDeNaissance;
        this.mdp=mdp;
        this.adresse=adresse;
        this.nbFilmLoue=0;
        this.carteAbonnes = new ArrayList<CarteAbonne>();
        // carteAbonnes.add(new CarteAbonne(this));
        this.enfants = new ArrayList<ClientEnfant>();
        this.favoris = new ArrayList<Film>();
        this.solde = 0;
    }

    
    public ClientAbonne(String email, Controleur controleur){
        super(email, controleur);
        this.carteAbonnes = new ArrayList<CarteAbonne>();
    }

    public void setNom(String nom){
        this.nom=nom;
    }

    public void setPrenom(String prenom){
        this.prenom=prenom;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getNom(){
        return nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public void ajouterEnfant(Date dateDeNaissance, String nom, String prenom){
        ClientEnfant enfant = new ClientEnfant(prenom, nom, dateDeNaissance, email, controleur);
        enfants.add(enfant);
    }

    public void supprimerEnfant(ClientEnfant enfant){
        enfants.remove(enfant);
    }

    public String getAdresse(){
        return adresse;
    }

    public void setAdresse(String adresse){
        this.adresse=adresse;
    }

    public String getMdp(){
        return mdp;
    }

    public void setMdp(String mdp){
        this.mdp=mdp;
    }

    public void setSolde(Double d){
        solde = d;
    }

    public void ajouterFavoris(Film film){
        favoris.add(film);
    }

    public void supprimerFavoris(Film film){
        favoris.remove(film);
    }

    public ArrayList<Film> getFavoris(){
        return favoris;
    }

    public int getNbFilmLoue(){
        return nbFilmLoue;
    }

    public void incrementerNbFilmLoue(){
        this.nbFilmLoue +=1;
    }

    public Date getDateDeNaissance(){
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance){
        this.dateDeNaissance=dateDeNaissance;
    }

    public ArrayList<CarteAbonne> getcarteAbonne(){
        return carteAbonnes;
    }
    
    public void addcarteAbonne(CarteAbonne carte){
         this.carteAbonnes.add(carte);
    }

    public CarteBancaire getCarteBancaire(){
        return carteBancaire;
    }

    public void setCarteBancaire(CarteBancaire carteBancaire){
        this.carteBancaire=carteBancaire;
    }

    public ArrayList<ClientEnfant> getEnfants(){
        return enfants;
    }

    public double getSolde(){
        return solde;
    }

    public void setSolde(double d){
        solde = d;
    }
    public void debiter(double prix){
        solde -= prix;
        Configuration.getDb().getCarteAbonneService().debiter(getcarteAbonne().get(0));
    }
} 