
import Global.Configuration;
import Vue.InterfaceGraphique;

public class AL2000 {
    final static String typeInterface = Configuration.typeInterface;

    public static void main(String[] args) {
        Configuration.info("Bienvenue dans AL2000 !");

        switch (typeInterface) {
            case "Graphique":
                Configuration.info("Interface graphique");
                InterfaceGraphique.demarrer();
                break;
            default:
                Configuration.erreur("Type d'interface inconnu");
                break;
        }

    }

}