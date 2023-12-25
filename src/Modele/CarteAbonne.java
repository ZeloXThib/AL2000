package Modele;

import Global.Configuration;

public class CarteAbonne extends Carte{

    public ClientAbonne client;

    public CarteAbonne(ClientAbonne client){
        this.client = client;
    }

    public void setNumeroDeCarte(String numeroDeCarte){
        super.numeroDeCarte = numeroDeCarte;
    }

    public void recharger(double montant){
        Configuration.getDb().getCarteAbonneService().recharger(this, montant);
        client.setSolde(client.getSolde() + montant);
    }

    public void debiter(double montant){
        Configuration.getDb().getCarteAbonneService().debiter(this);
        client.setSolde(client.getSolde() - montant);
    }
}