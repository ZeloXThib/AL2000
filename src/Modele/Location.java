package Modele;
import java.util.Date;

import java.util.ResourceBundle.Control;


import Global.Configuration;
import Controleur.Controleur;

public class Location{
    private Client client;
    private Carte carte;
    private Film film;
    private Date dateLocation;
    private int prix;
    private Controleur controleur;
    private final static int PRIX_LOC_ABONNE = 4;
    private final static int PRIX_LOC_NON_ABONNE = 5;

    public Location(Controleur controleur){
        this.dateLocation = new Date();
        this.controleur = controleur;
    }
    
    public Location(Client client, Film film, Date date, int prix, Controleur controleur, Carte carte){
        this.client=client;
        this.film=film;
        this.dateLocation=date;
        this.prix=prix;
        this.controleur = controleur;
        this.carte = carte;
    } 

    public Date getDateLocation(){
        return dateLocation;
    }

    public void setPrix(int prix){
        this.prix = prix;
    }

    public Integer getPrix(){
        return prix;
    }

    public void setClient(Client client){
        this.client = client;
        if(client instanceof ClientAbonne){
            this.prix = PRIX_LOC_ABONNE;
        }
        else{
            this.prix = PRIX_LOC_NON_ABONNE;
        }
    }

    public Client getClient(){
        return client;
    }

    public void setFilm(Film film){
        this.film = film;
    }
    
    public Film getFilm(){
        return film;
    }
  
    public void setCarte(Carte carte){
        this.carte = carte;
    }

    public void setDateLocation(Date dateLocation){
        this.dateLocation = dateLocation;
    }

    public Carte getCarte(){
        return carte;
    }

    public boolean louerFilm(){
        // set le solde du client quand le nombre de location vaut 20, tu rajoute 10 euros au solde précedent
        if (client instanceof ClientAbonne) {
            ClientAbonne clientAbo = (ClientAbonne) client;
            if(clientAbo.getNbFilmLoue()%20 == 0 && clientAbo.getNbFilmLoue() != 0){
                clientAbo.setSolde(clientAbo.getSolde()+10);
            }
        }


        Configuration.getDb().getFilmService().louerFilm(this);
        controleur.notify(this, "louerFilm");

        return true;
    }

    public Boolean rendrefilm(Client client, Film film){
        Configuration.getDb().getFilmService().rendreFilm(this);
        controleur.notify(this, "rendreFilm");
    }
}