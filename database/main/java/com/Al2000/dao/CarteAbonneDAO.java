package database.main.java.com.Al2000.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Global.Configuration;

import Modele.CarteAbonne;

public class CarteAbonneDAO extends AbstractDAO<CarteAbonne> {
    public CarteAbonneDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Boolean create(CarteAbonne obj) {
        // On insère une nouvelle carte dans la table CarteAbonne
        String sql = "INSERT INTO CarteAbonne (CarteId,ClientId) VALUES(?,?,NULL)";
        try (PreparedStatement state = this.connection.prepareStatement(sql)) {
            state.setString(1, obj.getNumero());

            // On récupère l'id du client abonné actuellement connecté à l'interface et on
            // l'associe à la carte
            state.setInt(3, Configuration.getDb().getClientAbonneService().getclientAbonneId(obj.client));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    public Boolean update(CarteAbonne obj) throws SQLException {

        return false;
    }

    public CarteAbonne read(CarteAbonne c) throws SQLException {
        return null;
    }

    @Override
    public Boolean delete(CarteAbonne obj) throws SQLException {

        return false;
    }

    public Integer getCarte(CarteAbonne c) throws SQLException {
        try {
            // On récupère les informations de la première carte abonné associée au client
            // abonné
            ResultSet resultat = this.connection.createStatement()
                    .executeQuery("SELECT * FROM CarteAbonne WHERE clientid ="
                            + Configuration.getDb().getClientAbonneService().getclientAbonneId(c.client));
            if (resultat.first()) {
                return resultat.getInt("CarteId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void recharger(CarteAbonne c, Double montant) {
        CarteAbonne carte = c;
        try {
            if (carte != null) {
                // On met à jour le solde du client abonné
                String sql = "UPDATE ClientAbonne SET Solde = ? WHERE ClientId ="
                        + Configuration.getDb().getClientAbonneService().getclientAbonneId(c.client);
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setDouble(1, c.client.getSolde() + montant);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSolde(CarteAbonne c) {
        CarteAbonne carte = c;
        try {
            if (carte != null) {
                // On met à jour le solde du client abonné
                System.out
                        .println("cid: " + Configuration.getDb().getClientAbonneService().getclientAbonneId(c.client));
                String sql = "UPDATE ClientAbonne SET Solde = ? WHERE ClientId = "
                        + Configuration.getDb().getClientAbonneService().getclientAbonneId(c.client);
                System.out.println(sql);
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setDouble(1, c.client.getSolde());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
