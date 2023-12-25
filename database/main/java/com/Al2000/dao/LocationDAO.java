package database.main.java.com.Al2000.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Global.Configuration;
import Modele.ClientAbonne;
import Modele.Film;
import Modele.FilmDigital;
import Modele.Location;

public class LocationDAO extends AbstractDAO<Location> {
    public LocationDAO(Connection connection) {
        super(connection);
    }

    public Boolean create(Location obj) {
        // On insère une nouvelle location dans la table Location
        String sql = "INSERT INTO Location(Carte, FilmId, Date_loc, Prix,TypeFilm) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);

            // On récupère le type de film
            if (obj.getFilm() instanceof FilmDigital) {
                statement.setString(5, "digital");
            } else {
                statement.setString(5, "physique");
            }
            statement.setString(1, obj.getClient().getCarteBancaire().getNumero());

            Integer filmId = 0;
            statement.setInt(2, filmId);
            statement.setDate(3, new java.sql.Date(obj.getDateLocation().getTime()));
            statement.setInt(4, obj.getPrix());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Location read(Location c) throws SQLException {
        return null;
    }

    public Boolean update(Location obj) throws SQLException {
        // TODO
        return false;
    }

    public Boolean delete(Location obj) throws SQLException {
        // TODO
        return false;
    }
}
