package database.main.java.com.Al2000.service;

import Modele.ClientNonAbonne;
import database.main.java.com.Al2000.dao.ClientNonAboDAO;

public class ClientNonAbonneService {
    public ClientNonAboDAO clientNonAbonneDao;

    public ClientNonAbonneService(ClientNonAboDAO clientNonAboDAO) {
        this.clientNonAbonneDao = clientNonAboDAO;
    }

    public void createClientNonAbonne(ClientNonAbonne utilisateur) {
        clientNonAbonneDao.create(utilisateur);
    }

}
