package database.main.java.com.Al2000.service;
import java.sql.SQLException;

import Modele.ClientAbonne;
import database.main.java.com.Al2000.dao.ClientAbonneDAO;

public class ClientAbonneService {
    public ClientAbonneDAO clientAbonneDao;

    public ClientAbonneService(ClientAbonneDAO clientAbonneDao) {
        this.clientAbonneDao = clientAbonneDao;
    }
    
    public void createClientAbonne(ClientAbonne utilisateur) {
        clientAbonneDao.create(utilisateur);
    }
    public int getclientAbonneId(ClientAbonne utilisateur) throws SQLException{
        return clientAbonneDao.getId(utilisateur);
    }

    public ClientAbonne login(ClientAbonne utilisateur) throws SQLException{
        return clientAbonneDao.read(utilisateur);
    }
}
