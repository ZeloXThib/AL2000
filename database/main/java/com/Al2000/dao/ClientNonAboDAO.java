package database.main.java.com.Al2000.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modele.ClientNonAbonne;

public class ClientNonAboDAO extends AbstractDAO<ClientNonAbonne> {
    public ClientNonAboDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Boolean create(ClientNonAbonne obj) {
        // On insère un nouveau client dans la table ClientNonAbo
        String sql ="INSERT INTO ClientNonAbo (CarteBleu,Email) VALUES(?,?)";
        try(PreparedStatement state =connection.prepareStatement(sql)){
            state.setString(1, obj.getCarteBancaire().getNumero());
            state.setString(2, obj.getEmail());
            state.executeUpdate();
            System.out.println("Client non abonné créé");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return true;
    
    }

    @Override
    public ClientNonAbonne read(ClientNonAbonne c) throws SQLException {
        try {
            // On vérifie que le client existe bien dans la base de données
            ResultSet resultat = this.connection.createStatement()
                   .executeQuery("SELECT * FROM ClientAbonne WHERE Email ="+c.getEmail() );
           if (resultat.first()) {
            // On récupère les informations du client non abonné
                c.getCarteBancaire().setNumero(resultat.getString("CarteBleu"));
            } 
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return null;
    }

    @Override
    public Boolean update(ClientNonAbonne obj) throws SQLException {
        return false;
    }

    @Override
    public Boolean delete(ClientNonAbonne obj) throws SQLException {
        return false;
    }

}
