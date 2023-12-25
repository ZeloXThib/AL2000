package Modele;
import java.util.Date;
import Controleur.Controleur;

public class ClientEnfant extends Client{
  
    private Date dateDeNaissance;

    public ClientEnfant(String prenom, String nom, Date dateDeNaissance, String email, Controleur controleur) {
        super(email, controleur);
    }


    public void setCB(CarteBancaire carteBancaire){
        super.carteBancaire = carteBancaire;
    }

    public void setNom(String nom){
        super.nom = nom;
    }

    public void setPrenom(String prenom){
        super.prenom = prenom;
    }

    public void setDateNaissance(Date dateDeNaissance){
        this.dateDeNaissance = dateDeNaissance;
    }

    public Date getDateDeNaissance(){
        return dateDeNaissance;
    }
} 