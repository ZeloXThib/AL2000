package Modele;

//Simule la connexion à une banque
public class SimuBanque {
    private String nomBanque;

    public SimuBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }

    void payer(double montant) {
        System.out.println("Paiement de " + montant + "€");
    }
    
}
