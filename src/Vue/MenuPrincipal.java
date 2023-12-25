package Vue;

import javax.swing.*;

import Global.Configuration;
import Modele.Dictionary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JComponent {
    InterfaceGraphique ig;
    JPanel languagePanel;
    JPanel carouselPanel;

    public MenuPrincipal(InterfaceGraphique ig) { 
        this.ig = ig;
        addLanguagePanel();
        addCarouselPanel();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        languagePanel.setBounds(getWidth() - 150, getHeight() - 50, 150, 50);
        carouselPanel.setBounds(0, 70, getWidth(), getHeight() - 120);
    }

    private void addLanguagePanel() {
        languagePanel = new JPanel();
        languagePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        add(languagePanel, BorderLayout.NORTH);

        ImageIcon englishFlag = new ImageIcon("res/Image/englishflag.png");
        ImageIcon frenchFlag = new ImageIcon("res/Image/frenchflag.png");
        Image image = englishFlag.getImage();
        Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        englishFlag = new ImageIcon(newimg);

        image = frenchFlag.getImage();
        newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        frenchFlag = new ImageIcon(newimg);

        JButton englishButton = new JButton(englishFlag);
        JButton frenchButton = new JButton(frenchFlag);

        languagePanel.add(englishButton);
        languagePanel.add(frenchButton);

        englishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Configuration.setLang("EN");
                ig.fenetre.dispose();
                ig.run();
                ig.refresh();

            }
        });

        frenchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Configuration.setLang("FR");
                ig.fenetre.dispose();
                ig.run();
                ig.refresh();
            }
        });

        // set focus to the first button
        englishButton.setFocusable(true);

    }

    private void addCarouselPanel() { // Ajout du carrousel
        carouselPanel = new JPanel();
        add(carouselPanel, BorderLayout.CENTER);

        carouselPanel.setLayout(new BorderLayout());

        carouselPanel.setLayout(new BoxLayout(carouselPanel, BoxLayout.Y_AXIS));
        Carousel carousel = new Carousel(ig);

        JLabel label = new JLabel(Dictionary.translate("Films recommandés"));
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        carouselPanel.add(label, BorderLayout.NORTH);
        carouselPanel.add(carousel, BorderLayout.CENTER);

        // add space between carousel and historique:
        carouselPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel label2 = new JLabel(Dictionary.translate("Historique"));
        label2.setFont(new Font("Arial", Font.BOLD, 20));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setForeground(Color.WHITE);

        Historique historique = new Historique(ig);
        carouselPanel.add(label2, BorderLayout.NORTH);
        carouselPanel.add(historique, BorderLayout.CENTER);

        carousel.setOpaque(false);
        historique.setOpaque(false);

        carouselPanel.setBackground(new Color(25, 42, 79));
        label.setForeground(Color.WHITE);
    }
}

class MovieAffiche {
    ImageIcon image;

    MovieAffiche(ImageIcon image) {
        this.image = image;
    }

    public ImageIcon getImageIcon() {
        return image;
    }
}