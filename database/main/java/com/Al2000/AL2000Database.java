package database.main.java.com.Al2000;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Modele.CarteBancaire;
import Modele.ClientAbonne;
import Modele.FilmDigital;
import database.main.java.com.Al2000.dao.*;
import database.main.java.com.Al2000.service.*;

public class AL2000Database {
  static Connection connection = null;
  static ClientAbonneService clientAbonneService = null;
  static ClientNonAbonneService clientNonAbonneService = null;
  static CarteAbonneService carteAbonneService = null;
  static FilmService filmService = null;
  static LocationService locationService = null;

  public AL2000Database() {
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:al2000.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      // On initialise les différents services
      clientAbonneService = new ClientAbonneService(new ClientAbonneDAO(connection));
      clientNonAbonneService = new ClientNonAbonneService(new ClientNonAboDAO(connection));
      carteAbonneService = new CarteAbonneService(new CarteAbonneDAO(connection));
      filmService = new FilmService(new FilmsDAO(connection));
      locationService = new LocationService(new LocationDAO(connection));

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    new AL2000Database();

    ClientAbonne client = new ClientAbonne(generateRandomName(), "Adrien", generateRandomName() + "@gmail.com",
        new Date(), generateRandomName(), generateRandomName(), new CarteBancaire(generateRandomName()), null);
    client.setSolde(15);
    clientAbonneService.createClientAbonne(client);

    // CarteBancaire carteBancaire = new CarteBancaire("0000000000000000", new
    // SimuBanque());
    // client.setCarteBancaire(carteBancaire);

    // public FilmDigital(String titre,String description,Date anneeSortie,
    // ArrayList<String> categories,ArrayList<String> acteurs,ArrayList<String>
    // producteurs,String affiche, int ageMin, int duree,String url, Location
    // location) {

    FilmDigital film = new FilmDigital("titre", "description", 2020, new ArrayList<String>(), new ArrayList<String>(),
        new ArrayList<String>(), "affiche", 12, 120, "url");
    filmService.createFilm(film);

    // Location location = new Location();
    // location.setClient(client);
    // location.setFilm(film);
    // location.setDateLocation(new Date());

    // locationService.createLocation(location);
    // public ClientNonAbonne(String nom, String prenom, String email, CarteBancaire
    // cb, Controleur controleur)
    // ClientNonAbonne clientNonAbonne = new ClientNonAbonne(generateRandomName(),
    // generateRandomName(), generateRandomName() + "@gmail.com", new
    // CarteBancaire(generateRandomName()), null);

    // clientNonAbonneService.createClientAbonne(clientNonAbonne);

    // Location location = new Location();
    // location.setClient(clientNonAbonne);
    // location.setFilm(film);
    // location.setDateLocation(new Date());

    // locationService.createLocation(location);

  }

  // Génère une chaine de caractère aléatoire de 10 caractères
  static String generateRandomName() {
    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "0123456789"
        + "abcdefghijklmnopqrstuvxyz";
    StringBuilder sb = new StringBuilder(10);

    for (int i = 0; i < 10; i++) {
      int index = (int) (AlphaNumericString.length()
          * Math.random());
      sb.append(AlphaNumericString
          .charAt(index));
    }

    return sb.toString();
  }

  public ClientAbonneService getClientAbonneService() {
    return clientAbonneService;
  }

  public FilmService getFilmService() {
    return filmService;
  }

  public CarteAbonneService getCarteAbonneService() {
    return carteAbonneService;
  }

  public ClientNonAbonneService getClientNonAbonneService() {
    return clientNonAbonneService;
  }

  public LocationService getLocationService() {
    return locationService;
  }
}