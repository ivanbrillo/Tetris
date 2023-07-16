import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame cornice = new JFrame();
        cornice.setTitle("Tetris");

        Container c;
        c = cornice.getContentPane();

        Pannello p = new Pannello();
        c.add(p);

        cornice.setVisible(true);
        cornice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cornice.pack();
    }
}
   