package Modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import Global.Configuration;

public class Dictionary {
    static Dictionary instance = null;
    static HashMap<String, String> dictionary;
    Dictionary() {
        dictionary = new HashMap<String, String>();
        BufferedReader dictio;
        try {
            dictio = new BufferedReader(new FileReader(new File("res/lang.dict")));
            String line;
            while ((line = dictio.readLine()) != null) {
                String[] parts = line.split("=");
                dictionary.put(parts[0], parts[1]);
            }
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la lecture du dictionnaire");
        }
           
    }

    public static Dictionary instance() {
        if (instance == null)
            instance = new Dictionary();
        return instance;
    }
    
    public static String translate(String s) {
        if (instance == null)
            instance = new Dictionary();
            
        if (Configuration.getLang().equals("FR"))
            return s;
        if (dictionary.containsKey(s))
            return dictionary.get(s);
        else
            return s;
    }
}
