package database.main.java.com.Al2000.service;

import java.sql.SQLException;
import java.util.ArrayList;

import Global.Configuration;
import Modele.FilmDigital;
import Modele.Location;
import database.main.java.com.Al2000.dao.FilmsDAO;
import database.main.java.com.Al2000.dao.LocationDAO;

public class FilmService {
    public FilmsDAO filmDao;
    public LocationDAO locationDAO;

    public FilmService(FilmsDAO filmDao) {
        this.filmDao = filmDao;
    }

    public void createFilm(FilmDigital utilisateur) {
        filmDao.create(utilisateur);
    }

    public  ArrayList<FilmDigital> getFilmsDigitals(){
        return filmDao.getFilmsDigitals();
    }
    public void louerFilm(Location l){
        Configuration.getInstance().getDb().getLocationService().createLocation(l);
        
    }

    public Integer getFilmId(FilmDigital film) throws SQLException{
        return filmDao.getFilmId(film);
    }
}
