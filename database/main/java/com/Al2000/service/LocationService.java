package database.main.java.com.Al2000.service;

import Modele.Location;
import database.main.java.com.Al2000.dao.LocationDAO;

public class LocationService {
    public LocationDAO locationDao;

    public LocationService(LocationDAO filmDao) {
        this.locationDao = filmDao;
    }

    public void createLocation(Location location) {
        locationDao.create(location);
    }
}
