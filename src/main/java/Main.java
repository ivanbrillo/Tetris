import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (Exception ignored) {}

        JFrame frame = new JFrame();
        frame.setTitle("Tetris");

        Container container = frame.getContentPane();

        BoardController p = new BoardController();
        container.add(p.view);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
    }
}
   