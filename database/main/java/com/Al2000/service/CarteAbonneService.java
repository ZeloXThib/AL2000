package database.main.java.com.Al2000.service;
import java.sql.SQLException;

import Modele.CarteAbonne;
import database.main.java.com.Al2000.dao.CarteAbonneDAO;


public class CarteAbonneService {
    public CarteAbonneDAO carteAbonneDAO;
    public CarteAbonneService(CarteAbonneDAO carteAbonneDAO) {
        this.carteAbonneDAO = carteAbonneDAO;
    }

    public void createCarteAbonne(CarteAbonne carte) {
        carteAbonneDAO.create(carte);
    }
    public int getCarte(CarteAbonne c ) throws SQLException{
        return carteAbonneDAO.getCarte(c);
    }
    public void recharger(CarteAbonne c, Double montant){
        carteAbonneDAO.recharger(c,montant);
    }
    public void debiter(CarteAbonne c){
        carteAbonneDAO.updateSolde(c);
    }
    
}

