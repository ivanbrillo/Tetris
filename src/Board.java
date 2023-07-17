import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Board extends JPanel implements KeyListener, ActionListener {

    Timer tm = new Timer(150, this);
    Block prova = BlockFactory.getRandomBlock();
    Block prova2 = BlockFactory.getRandomBlock();
    boolean scacchiera[][] = new boolean[16][10];
    Color scacchiera2[][] = new Color[16][10];
    int timer = 0;
    boolean descending = false;
    Image miaimmagine;
    Image miaimmagine2;
    boolean inizio = false;
    boolean perdita = false;

    public Board() {
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
        miaimmagine = new ImageIcon("resources/ciao.png").getImage();
        miaimmagine2 = new ImageIcon("resources/Immagine1.jpg").getImage();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!inizio) {
            g.drawImage(miaimmagine, 240, 120, this);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Press space to start", 210, 600);
            return;
        }


        if (!perdita) {
            g.setColor(Color.black);
            for (int i = 0; i < 4; i++) {
                g.setColor(prova.getColor());
                g.fillRect(prova.getPoint(i).x * 50 + prova.position.x, prova.getPoint(i).y * 50 + prova.position.y, 50, 50);
            }
            for (int i = 0; i < 16; i++)
                for (int j = 0; j < 10; j++)
                    if (scacchiera[i][j]) {
                        g.setColor(scacchiera2[i][j]);
                        g.fillRect(j * 50, i * 50, 50, 50);
                    }

            g.setColor(Color.white);
            for (int j = 0; j < 16; j++)
                g.drawLine(0, j * 50, 500, j * 50);
            for (int j = 0; j <= 10; j++)
                g.drawLine(j * 50, 0, j * 50, 800);

            g.setColor(Color.WHITE);

            g.setFont(new Font("Arial", Font.PLAIN, 30));

            g.drawString("Prossimo pezzo:", 540, 150);

            g.setFont(new Font("Arial", Font.PLAIN, 15));

            g.drawString("Premi f per farlo cadere piu' velocemente", 520, 600);
            g.drawString("Premi r per far ruotare il pezzo", 520, 550);
            g.drawString("Premi le freccette per muovere il pezzo", 520, 500);
            for (int i = 0; i < 4; i++) {
                g.setColor(prova2.getColor());
                g.fillRect(prova2.getPoint(i).x * 50 + 550, prova2.getPoint(i).y * 50 + 200, 50, 50);
            }

        } else {
            g.drawImage(miaimmagine2, 250, 250, this);
        }

    }

    public void actionPerformed(ActionEvent e) {
        timer += 150;
        if (timer == 2550 || descending) {
            descent();
        }
    }

    public void descent() {

        ArrayList<Point> absPath = prova.getAbsolutePosition();

        descending = true;
        for (Point point : absPath)
            if (point.y >= 15 || point.x >= 10 || point.x < 0 || scacchiera[point.y + 1][point.x]) {
                endDescent(absPath);
                repaint();
                return;
            }

        prova.drop();
        repaint();
    }

    private void endDescent(ArrayList<Point> absPath) {

        timer = 0;
        for (Point point : absPath) {
            scacchiera[point.y][point.x] = true;
            scacchiera2[point.y][point.x] = prova.getColor();
        }

        checkFullRow();
        checkGameOver();

        prova = prova2;
        prova2 = BlockFactory.getRandomBlock();

    }


    public void keyPressed(KeyEvent e) {

        int keyPressed = e.getKeyCode();

        if (keyPressed == ' ') {
            inizio = true;
            tm.start();
            repaint();
        }

        if (!inizio) {
            return;
        }

        switch (keyPressed) {

            case 'r', 'R' -> {
                ArrayList<Point> rotationPoints = prova.getRotationPoints();
                ArrayList<Point> absoluteRotationPoints = prova.getAbsolutePosition(rotationPoints);
                for (Point point : absoluteRotationPoints)
                    if (point.x >= 10 || point.x < 0 || scacchiera[point.y][point.x]) {
                        return;
                    }

                prova.setBlocksPosition(rotationPoints);
                repaint();
            }

            case KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT -> {

                int shift = keyPressed == KeyEvent.VK_RIGHT ? +1 : -1;
                ArrayList<Point> positions = prova.getAbsolutePosition();
                for (Point point : positions)
                    if ((shift == 1 && point.x >= 9) || (shift == -1 && point.x <= 0) || scacchiera[point.y][point.x + shift]) {
                        return;
                    }

                prova.position.x += shift * 50;
                repaint();

            }

            case 'f', 'F' -> descending = true;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void checkFullRow() {

        for (int i = 3; i < 16; i++) {

            boolean fullRow = true;
            for (int j = 0; j < 10; j++)
                if (!scacchiera[i][j]) {
                    fullRow = false;
                    break;
                }

            if (!fullRow) {
                continue;
            }

            for (int x = i; x > 0; x--) {
                scacchiera[x] = scacchiera[x - 1];
                scacchiera2[x] = scacchiera2[x - 1];
            }

            scacchiera[0] = new boolean[10];
            scacchiera2[0] = new Color[10];

        }
    }

    public void checkGameOver() {
        for (int i = 0; i < 10; i++)
            if (scacchiera[2][i]) {
                perdita = true;
                tm.stop();
                return;
            }
    }


}