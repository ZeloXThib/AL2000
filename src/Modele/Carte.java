package Modele;

public abstract class  Carte{
    protected String numeroDeCarte;

    public abstract void debiter(double montant);

    public String getNumero(){
        return numeroDeCarte;
    }

    public void setNumero(String numero){
        this.numeroDeCarte = numero;
    }
}