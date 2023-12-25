package Global;

import java.io.InputStream;

import database.main.java.com.Al2000.AL2000Database;

import javax.imageio.ImageIO;

import Modele.ClientAbonne;

import java.awt.Image;
import java.awt.image.BufferedImage;

// Singleton, pour accéder à la configuration de l'application
// depuis n'importe quelle classe
public class Configuration {
	private static Configuration instance = null;
	public final static String typeInterface = "Graphique";

	private int silence;
	private String imagePath;
	private String lang;
	private String code;
	private Boolean isLogged;
	private AL2000Database db;
	private ClientAbonne clientConnecte;

	private Configuration() {
		silence = 0;
		imagePath = "res/Image";
		lang = "FR";
		code = "1230";
		isLogged = false;
		db = new AL2000Database();
	}

	public static Configuration getInstance() {
		if (instance == null)
			instance = new Configuration();
		return instance;
	}

	public void affiche(int niveau, String message) {
		if (niveau > silence)
			System.err.println(message);
	}

	public static void info(String s) {
		getInstance().affiche(1, "INFO : " + s);
	}

	public static void alerte(String s) {
		getInstance().affiche(2, "ALERTE : " + s);
	}

	public static void erreur(String s) {
		getInstance().affiche(3, "ERREUR : " + s);
		System.exit(1);
	}

	public static AL2000Database getDb() {
		return getInstance().db;
	}

	public static InputStream ouvre(String s) {
		InputStream in = null;
		try {
			in = Configuration.class.getResourceAsStream("/" + s);
		} catch (Exception e) {
			Configuration.erreur("Impossible d'ouvrir le fichier " + s);
		}
		return in;
	}

	public Image lisImage(String nom) {
		InputStream in = ouvre(getInstance().imagePath + "/" + nom + ".png");
		try {
			BufferedImage image = ImageIO.read(in);
			return image;
		} catch (Exception e) {
			alerte("Impossible de charger l'image " + nom);
		}
		return null;
	}

	public static String getLang() {
		return getInstance().lang;
	}

	public static void setLang(String l) {
		getInstance().lang = l;
	}

	public static String getCode() {
		return getInstance().code;
	}

	public static Boolean getLogged() {
		return getInstance().isLogged;
	}

	public static void setLogged(Boolean logged) {
		getInstance().isLogged = logged;
	}

	public static String getImagePath() {
		return getInstance().imagePath;
	}

	public static void setClientConnecte(ClientAbonne clientConnecte) {
		getInstance().clientConnecte = clientConnecte;
	}

	public static ClientAbonne getClientConnecte() {
		return getInstance().clientConnecte;
	}
}