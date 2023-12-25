package Vue;

import Vue.TopToolbar.TopToolbar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Global.Configuration;
import Modele.FilmDigital;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputFilter.Config;


public class MenuMaintenance extends JComponent {
    InterfaceGraphique ig;
    JPanel languagePanel;
    JPanel carouselPanel;
    JPanel tablePanel;
    JScrollPane scrollPane;

    public MenuMaintenance(InterfaceGraphique ig) {
        // System.out.println("MenuMaintenance");
        // this.ig = ig;
        // setLayout(new BorderLayout());

        // ArrayList<FilmDigital> filmsDigitals = ig.controleur.getFilmsDigitals();
        // addTablePanel(filmsDigitals);
        // System.out.println("filmsDigitals : " + filmsDigitals);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tablePanel.setBounds(0, 70, getWidth() , getHeight());
    }

    private void addTablePanel(ArrayList<FilmDigital> filmsDigitals) {
        tablePanel = new JPanel();
        add(tablePanel, BorderLayout.CENTER);
        // Créer un tableau de données pour le modèle de la table
        Object[][] data = new Object[filmsDigitals.size()][3]; // Supposons que chaque film a 3 champs (par exemple : titre, réalisateur, année)
        System.out.println("data : " + data);

        // Remplir le tableau de données avec les informations des films
        for (int i = 0; i < filmsDigitals.size(); i++) {
            FilmDigital film = filmsDigitals.get(i);
            data[i][0] = film.getTitre();
            data[i][1] = film.getProducteur();
            data[i][2] = film.getDescription();
        };

        // Créer un tableau de noms de colonnes
        String[] columnNames = {"Titre", "Producteur", "Description"};

        // Créer le modèle de table avec les données et les noms de colonnes
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Créer la table avec le modèle
        JTable filmTable = new JTable(model);

        filmTable.setPreferredScrollableViewportSize(new Dimension(1000, 700));

        // Ajouter la table à un JScrollPane pour permettre le défilement si nécessaire
        JScrollPane scrollPane = new JScrollPane(filmTable);

        // Ajouter le JScrollPane au panel
        tablePanel.add(scrollPane);

        // Ajouter la table au tablePanel avec les contraintes
        tablePanel.add(new JScrollPane(filmTable) , BorderLayout.CENTER);
        

        // Créer les boutons Ajouter et Supprimer
        JButton addButton = new JButton("Ajouter");
        JButton deleteButton = new JButton("Supprimer");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) filmTable.getModel();
        
                // Créer une boîte de dialogue personnalisée
                JPanel panel = new JPanel(new GridLayout(3, 2)); // Utilise une grille de 3 lignes et 2 colonnes
                JTextField titreField = new JTextField();
                JTextField realisateurField = new JTextField();
                JTextField descriptionField = new JTextField();
        
                // Ajouter les champs de texte à la boîte de dialogue
                panel.add(new JLabel("Titre du film:"));
                panel.add(titreField);
                panel.add(new JLabel("Réalisateur du film:"));
                panel.add(realisateurField);
                panel.add(new JLabel("Description du film:"));
                panel.add(descriptionField);
        
                // Afficher la boîte de dialogue personnalisée
                int result = JOptionPane.showConfirmDialog(null, panel, "Ajouter un film", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // Récupérer les valeurs saisies dans les champs de texte
                    String titre = titreField.getText();
                    String realisateur = realisateurField.getText();
                    String description = descriptionField.getText();

    //                  FilmDigital film = new FilmDigital(1, "titre", "description", new Date(), new ArrayList<String>(), new ArrayList<String>(), "producteur", "affiche", 12, 120, "url", null);
    // filmService.createFilm(film);

                    FilmDigital film = new FilmDigital(1, titre, description, new Date(), "affiche", 12, 120, null);
                    Configuration.getDb().getFilmService().createFilm(film);
        
                    // Ajouter les informations du nouveau film au modèle de la table
                    model.addRow(new Object[]{titre, realisateur, description});
                }
            }
        });

    deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = filmTable.getSelectedRow(); // Obtenir l'indice de la ligne sélectionnée
            if (selectedRow != -1) { // Vérifier si une ligne est sélectionnée
                DefaultTableModel model = (DefaultTableModel) filmTable.getModel();
                model.removeRow(selectedRow); // Supprimer la ligne du modèle de la table
            }
        }
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); // Utilise un BoxLayout horizontal
    JButton retourButton = new JButton("Retour");
    retourButton.addActionListener(e -> ig.switchToMenuPrincipal());
    buttonPanel.add(addButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(retourButton);

    // Ajouter le JPanel des boutons en bas du tablePanel
    tablePanel.add(buttonPanel, BorderLayout.SOUTH);
    }
}
