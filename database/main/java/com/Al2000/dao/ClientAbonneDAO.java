package database.main.java.com.Al2000.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modele.CarteAbonne;
import Modele.CarteBancaire;
import Modele.ClientAbonne;

public class ClientAbonneDAO extends AbstractDAO<ClientAbonne> {
    public ClientAbonneDAO(Connection connection) {
        super(connection);
    }

    public Boolean create(ClientAbonne obj) {
        {
            // On insère un nouveau client dans la table ClientAbonne
            String sql = "INSERT INTO ClientAbonne ( Nom, Prenom, DateNaissance, Email, PassW, Adresse,Solde, CarteBleu) VALUES ( ?, ?, ?, ?, ?,?,?,?)";
            try (PreparedStatement statement2 = connection.prepareStatement(sql)) {
                statement2.setString(1, obj.getNom());
                statement2.setString(2, obj.getPrenom());
                statement2.setDate(3, new java.sql.Date(obj.getDateDeNaissance().getTime()));
                statement2.setString(4, obj.getEmail());
                statement2.setString(5, obj.getMdp());
                statement2.setString(6, obj.getAdresse());
                statement2.setDouble(7, obj.getSolde());
                statement2.setString(8, "0");
                statement2.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public ClientAbonne read(ClientAbonne c) throws SQLException {
        try {
            // On vérifie que le client existe bien dans la base de données
            ResultSet cmpt = this.connection.createStatement()
                    .executeQuery("SELECT COUNT(*) FROM ClientAbonne WHERE Email ='" + c.getEmail() + "' AND PassW ='"
                            + c.getMdp() + "'");
            if (cmpt.getInt(1) == 0) {
                return null;
            }

            // On récupère les informations du client
            ResultSet resultat = this.connection.createStatement().executeQuery(
                    "SELECT * FROM ClientAbonne WHERE Email ='" + c.getEmail() + "' AND PassW ='" + c.getMdp() + "'");

            c.setNom(resultat.getString("Nom"));
            c.setPrenom(resultat.getString("Prenom"));
            c.setDateDeNaissance(resultat.getDate("DateNaissance"));
            c.setAdresse(resultat.getString("Adresse"));
            c.setSolde(resultat.getDouble("Solde"));

            // On récupère les informations de la carte abonné associée au client
            ResultSet resultat2 = this.connection.createStatement()
                    .executeQuery("SELECT * FROM CarteAbonne WHERE ClientId =" + resultat.getInt("ClientId"));

            // On crée une nouvelle carte abonné et on l'associe au client
            CarteAbonne carte = new CarteAbonne(c);
            carte.setNumero(resultat2.getString("CarteId"));
            c.addcarteAbonne(carte);

            c.setCarteBancaire(new CarteBancaire(resultat.getString("CarteBleu")));
            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        c = null;
        return c;
    }

    public Boolean update(ClientAbonne obj) throws SQLException {
        return false;
    }

    public Boolean delete(ClientAbonne obj) throws SQLException {
        return false;
    }

    public Integer getId(ClientAbonne c) throws SQLException {
        try {
            // On récupère l'id du client abonné c
            ResultSet resultat = this.connection.createStatement()
                    .executeQuery("SELECT * FROM ClientAbonne WHERE Email ='" + c.getEmail() + "'");
            return resultat.getInt("ClientId");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
