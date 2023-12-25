package database.main.java.com.Al2000.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Modele.FilmDigital;

public class FilmsDAO extends AbstractDAO<FilmDigital> {
    public FilmsDAO(Connection connection) {
        super(connection);
    }

    public Boolean create(FilmDigital obj) {
        // On insère un nouveau film dans la table Films
        String sql = "INSERT INTO Films (Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getTitre());
            statement.setString(2, obj.getDescription());
            statement.setDate(3, new Date(obj.getSortie()));
            statement.setString(4, obj.getAffiche());
            statement.setInt(5, obj.getAgeMin());
            statement.setInt(6, obj.getDuree());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public FilmDigital read(int id) throws SQLException {
        return null;
    }

    public ArrayList<FilmDigital> getFilmsDigitals() {
        // On va récupérer les films qui sont dans la base de données
        ArrayList<FilmDigital> filmsDigitals = new ArrayList<FilmDigital>();
        try {
            ResultSet resultat = this.connection.createStatement().executeQuery("SELECT * FROM Films");
            while (resultat.next()) {
                ResultSet resultat2 = this.connection.createStatement()
                        .executeQuery("SELECT * FROM Categories WHERE FilmId = " + resultat.getInt("FilmId"));
                // On récupère les catégories du film
                ArrayList<String> categories = new ArrayList<String>();
                while (resultat2.next()) {
                    categories.add(resultat2.getString("Categories"));
                }
                ResultSet resultat3 = this.connection.createStatement()
                        .executeQuery("SELECT * FROM Acteurs WHERE FilmId = " + resultat.getInt("FilmId"));

                // On récupère les acteurs du film
                ArrayList<String> acteurs = new ArrayList<String>();
                while (resultat3.next()) {
                    acteurs.add(resultat3.getString("Nom") + " " + resultat3.getString("Prenom"));
                }
                ResultSet resultat4 = this.connection.createStatement()
                        .executeQuery("SELECT * FROM Producteurs WHERE FilmId = " + resultat.getInt("FilmId"));

                // On récupère les producteurs du film
                ArrayList<String> producteurs = new ArrayList<String>();
                while (resultat4.next()) {
                    producteurs.add(resultat4.getString("Nom") + " " + resultat4.getString("Prenom"));
                }
                FilmDigital filmDigital = new FilmDigital(resultat.getString("Titre"),
                        resultat.getString("Descr"),
                        resultat.getInt("Annee_sortie"),
                        categories,
                        acteurs,
                        producteurs,
                        resultat.getString("Img"),
                        resultat.getInt("AgeMin"),
                        resultat.getInt("duree"),
                        "dsfsfdsfds");
                filmsDigitals.add(filmDigital);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmsDigitals;
    }

    public Boolean update(FilmDigital obj) throws SQLException {
        return false;
    }

    public Boolean delete(FilmDigital obj) throws SQLException {
        return false;
    }

    @Override
    public FilmDigital read(FilmDigital id) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    public Integer getFilmId(FilmDigital film) throws SQLException {
        try {
            // On récupère l'id du film
            ResultSet resultat = this.connection.createStatement()
                    .executeQuery("SELECT FilmId FROM Films WHERE Titre ='" + film.getTitre() + "'");
            return resultat.getInt("FilmId");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}