package Modele;

import java.util.ArrayList;
import java.util.Date;


public class TestDecorator {
    public static void main(String[] args) {
    // Créez une liste de films physiques
        ArrayList<FilmPhysique> filmsPhysiques = new ArrayList<>();
        // Ajoutez quelques films physiques à la liste
        
        ArrayList<String> acteurs = new ArrayList<>();
        acteurs.add("Acteur1");
        acteurs.add("Acteur2");

        ArrayList<String> producteurs = new ArrayList<>();
        producteurs.add("Producteur1");
        producteurs.add("Producteur2");

        FilmPhysique film = new FilmPhysique(
                "Titre du Film",
                new Date(),
                acteurs,
                producteurs,
                16
        );

        ArrayList<String> acteurs2 = new ArrayList<>();
        acteurs2.add("Brad Pitt");
        acteurs2.add("Angelina Jolie");
        acteurs2.add("Leonardo DiCaprio");

        // Création de la liste de producteurs
        ArrayList<String> producteurs2 = new ArrayList<>();
        producteurs2.add("Steven Spielberg");
        producteurs2.add("George Clooney");

        // Création d'un film avec de vrais acteurs
        FilmPhysique film2 = new FilmPhysique(
                "Intrigue Complex",
                new Date(),
                acteurs2,
                producteurs2,
                18
        );



        filmsPhysiques.add(film2);
        filmsPhysiques.add(film);



        // Créer des critères
        Critere critereBase = new CritereBase();
        Critere critereProd = new Producteur(critereBase, "Steven Spielberg");
        Critere critereAuteur = new Acteur(critereProd, "Brad Pitt");
        Critere critereAgeMin = new Age(critereAuteur, 12);

        


        // Filtrer la liste de films physiques
        ArrayList<FilmPhysique> resultatsFilm = critereAgeMin.filtrer(filmsPhysiques);

        // Afficher les résultats
        System.out.println("Résultats de la recherche : ");
        for (FilmPhysique filmZebi : resultatsFilm) {
            System.out.println("Titre: " + filmZebi.getTitre() +
                    ", Acteurs : " + filmZebi.getActeurs().toString() +
                    ", Producteurs: " + filmZebi.getProducteur().toString() +
                    ", Age: " + filmZebi.getAgeMin());
        }
    }
}
