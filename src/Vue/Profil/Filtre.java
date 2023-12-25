package Vue.Profil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;;

public class Filtre extends JPanel{ // Filtre pour empêcher l'utilisateur d'interagir avec la fenêtre et assomblir le fond.
    public Filtre(JFrame fenetre){
        setBackground(new java.awt.Color(0, 0, 0, 150));
        setBounds(0, 0, fenetre.getWidth(), fenetre.getHeight());
        setVisible(true);
        setLayout(new GridBagLayout());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.consume();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.consume();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                e.consume();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                e.consume();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.consume();
            }
        });
    }
}
