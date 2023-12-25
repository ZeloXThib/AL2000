package Modele;

public class CarteBancaire extends Carte{
    private static SimuBanque banque = new SimuBanque("Banque de France");


    public CarteBancaire(String numeroDeCarte){
        this.numeroDeCarte = numeroDeCarte;
    }

    @Override
    public void debiter(double montant) {
        banque.payer(montant);
    }
    
}