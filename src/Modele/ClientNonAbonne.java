package Modele;
import Controleur.Controleur;

public class ClientNonAbonne extends Client{

    public ClientNonAbonne(String email, CarteBancaire cb, Controleur controleur) {
        super(email, cb, controleur);
    }
    
}
