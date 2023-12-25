package database.main.java.com.Al2000.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Modele.FilmPhysique;

public class FilmPhysiqueDAO extends AbstractDAO<FilmPhysique>  {
    
    public FilmPhysiqueDAO(Connection connection) {
        super(connection);
    }
    
    public Boolean create(FilmPhysique obj) {
        // On insère un nouveau film dans la table Films
        String sql = "SELECT FilmId FROM Films WHERE Titre = "+obj.getTitre();
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultat = statement.executeQuery();
            String sql2= "INSERT INTO FilmsPhysique (FilmId, Etat) VALUES (?,?)";
            try(PreparedStatement statement2 = connection.prepareStatement(sql2)){
                statement2.setInt(1,resultat.getInt("FilmId"));
                statement2.setObject(2,obj.getEtat());
            }
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public FilmPhysique read(FilmPhysique id) throws SQLException {
        // On récupère les informations du film
        FilmPhysique filmb;
        try{
            ResultSet resultat = this.connection.createStatement().executeQuery("SELECT * FROM Films WHERE FilmId = "+id);
            ResultSet resultat2 = this.connection.createStatement().executeQuery("SELECT * FROM Categories WHERE FilmId = "+id);
            ArrayList<String> categories=new ArrayList<String>();
            while(resultat2.next()){
                categories.add(resultat2.getString("Categories"));
            }
            ResultSet resultat3 = this.connection.createStatement().executeQuery("SELECT * FROM Acteurs WHERE FilmId = "+id);
            ArrayList<String> acteurs=new ArrayList<String>();
            while(resultat3.next()){
                acteurs.add(resultat3.getString("Acteurs"));
            }
            ResultSet resultat4 = this.connection.createStatement().executeQuery("SELECT * FROM Producteurs WHERE FilmId = "+id);
            ArrayList<String> producteurs=new ArrayList<String>();
            while(resultat4.next()){
                producteurs.add(resultat4.getString("Producteurs"));
            }
            FilmPhysique.Etat etat = FilmPhysique.Etat.INDISPONIBLE;
            ResultSet resultat5 = this.connection.createStatement().executeQuery("SELECT Etat FROM FilmsPhysique WHERE FilmId = "+id);
            String result = resultat5.getString("Etat");
            if (result.equals("DISPONIBLE")) {
                etat = FilmPhysique.Etat.DISPONIBLE;
            } else if (result.equals("CASSE")) {
                etat = FilmPhysique.Etat.CASSE;
            }
            
            if(resultat.first()){
               filmb= new FilmPhysique(resultat.getString("Titre"), 
                    resultat.getString("Descr"), 
                    resultat.getInt("Année de sortie"), 
                    categories, 
                    acteurs, 
                    producteurs,
			        resultat.getString("affiche"), 
                    resultat.getInt("age minimum"), 
                    resultat.getInt("duree"),
                    etat);
                return filmb;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public Boolean update(FilmPhysique obj) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Boolean delete(FilmPhysique obj) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
