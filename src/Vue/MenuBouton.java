package Vue;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.*;
import java.awt.*;

public class MenuBouton extends JButton {
    Runnable action;
    String imageName, texte, texteTmp;
    Boolean estBoutonSolde = false;
    Boolean survolable = true;

    Boolean masque = false;

    public MenuBouton(Runnable action, String imageName, int widthImage, int heightImage,  boolean survolable) {
        this.action = action;
        this.imageName = imageName;
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        ImageIcon icon = new ImageIcon("./res/Image/Bouton/" + imageName + ".png");
        Image image = icon.getImage(); // transform it 

        Image newimg = image.getScaledInstance(widthImage, heightImage,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back


        getBouton().setIcon(icon);
        

        addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    repaint();
                    estClique();
                }
    
                public void mouseEntered(MouseEvent e) {
                    if (survolable){
                        ImageIcon icon = new ImageIcon("./res/Image/BoutonSurvol/" + imageName + ".png");
                        Image image = icon.getImage(); // transform it 
                        Image newimg = image.getScaledInstance(widthImage, heightImage,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                        icon = new ImageIcon(newimg);  // transform it back
                        getBouton().setIcon(icon);
                        if (estBoutonSolde){
                            texteTmp = texte;
                            texte = null;
                        }
                        repaint();
                    }
                }
    
                public void mouseExited(MouseEvent e) {
                    if (survolable){
                        ImageIcon icon = new ImageIcon("./res/Image/Bouton/" + imageName + ".png");
                        Image image = icon.getImage(); // transform it 
                        Image newimg = image.getScaledInstance(widthImage, heightImage,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                        icon = new ImageIcon(newimg);  // transform it back
                        getBouton().setIcon(icon);
                        if (estBoutonSolde){
                            texte = texteTmp;
                            texteTmp = null;
                        }
                        repaint();
                    }

                }
            });
    }

    public MenuBouton(Runnable action, String imageName, String texte, int widthImage, int heightImage ){
        this(action, imageName, widthImage, heightImage, true);
        this.texte = texte;
    }

    public MenuBouton(Runnable action, String imageName, String texte, int widthImage, int heightImage, boolean estBoutonSolde){
        this(action, imageName, widthImage, heightImage , true);
        this.texte = texte;
        this.estBoutonSolde = estBoutonSolde;
    }

    public MenuBouton(Runnable action, String imageName, String texte, int widthImage, int heightImage, boolean estBoutonSolde, boolean survolable){
        this(action, imageName, widthImage, heightImage , survolable);
        this.texte = texte;
        this.estBoutonSolde = estBoutonSolde;
    }

    public void estClique() {
        action.run();
    }

    public JButton getBouton(){
        return this;
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        // Dessiner le texte avec une police personnalisée
        if (texte != null) {
            // Appliquer la police au contexte graphique
            Font f1  = new Font(Font.DIALOG, Font.PLAIN,  16);
            g.setFont(f1);

            // Dessiner le texte
            g.setColor(Color.WHITE); // Couleur du texte
            g.drawString(texte, getWidth() / 2 - g.getFontMetrics().stringWidth(texte) / 2,
                    getHeight() / 2 + g.getFontMetrics().getAscent() / 2);
        }
    }
    
}
