package database.main.java.com.Al2000.dao;

import java.sql.Connection;
import java.sql.SQLException;

import Modele.Film;

// favories de tous les abonnées
public class FavorisDAO extends AbstractDAO<Film> {
    public FavorisDAO(Connection connection) {
        super(connection);
    }

    public Boolean create(Film c) {
        // String sql = "INSERT INTO Favoris (CarteId, FilmId) VALUES (?, ?)";
        // try (PreparedStatement statement = connection.prepareStatement(sql)) {
        // statement.setInt(1,
        // Configuration.getDb().getCarteAbonneService().getCarte(c.car));
        // statement.setInt(2, obj.getId());
        // statement.executeUpdate();
        // return true;
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        return false;
    }

    public Film read(Film movie) throws SQLException {
        // TODO
        return null;
    }

    public Boolean update(Film obj) throws SQLException {
        // TODO
        return false;
    }

    public Boolean delete(Film obj) throws SQLException {
        // TODO
        return false;
    }
}
