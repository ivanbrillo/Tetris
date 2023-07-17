import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Board extends JPanel implements KeyListener, ActionListener {

    Timer tm = new Timer(150, this);
    Block mainBlock = BlockFactory.getRandomBlock();
    Block nextBlock = BlockFactory.getRandomBlock();
    boolean[][] isOccupiedBoard = new boolean[16][10];
    Color[][] colorsBoard = new Color[16][10];
    int timer = 0;
    boolean descending = false;
    Image startImage = new ImageIcon("resources/start.png").getImage();
    Image gameOverImage = new ImageIcon("resources/gameOver.jpg").getImage();
    boolean isStarted = false;
    boolean isLost = false;

    public Board() {
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isStarted) {
            g.drawImage(startImage, 240, 120, this);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Press space to start", 265, 600);
            return;
        }


        if (!isLost) {
            g.setColor(Color.black);
            for (int i = 0; i < 4; i++) {
                g.setColor(mainBlock.getColor());
                g.fillRect(mainBlock.getPoint(i).x * 50 + mainBlock.position.x, mainBlock.getPoint(i).y * 50 + mainBlock.position.y, 50, 50);
            }
            for (int i = 0; i < 16; i++)
                for (int j = 0; j < 10; j++)
                    if (isOccupiedBoard[i][j]) {
                        g.setColor(colorsBoard[i][j]);
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
                g.setColor(nextBlock.getColor());
                g.fillRect(nextBlock.getPoint(i).x * 50 + 550, nextBlock.getPoint(i).y * 50 + 200, 50, 50);
            }

        } else {
            g.drawImage(gameOverImage, 250, 250, this);
        }

    }

    public void actionPerformed(ActionEvent e) {
        timer += 150;
        if (timer == 2550 || descending) {
            descent();
        }
    }

    public void descent() {

        ArrayList<Point> absPath = mainBlock.getAbsolutePosition();

        descending = true;
        for (Point point : absPath)
            if (point.y >= 15 || point.x >= 10 || point.x < 0 || isOccupiedBoard[point.y + 1][point.x]) {
                endDescent(absPath);
                repaint();
                return;
            }

        mainBlock.drop();
        repaint();
    }

    private void endDescent(ArrayList<Point> absPath) {

        timer = 0;
        for (Point point : absPath) {
            isOccupiedBoard[point.y][point.x] = true;
            colorsBoard[point.y][point.x] = mainBlock.getColor();
        }

        checkFullRow();
        checkGameOver();

        mainBlock = nextBlock;
        nextBlock = BlockFactory.getRandomBlock();

    }


    public void keyPressed(KeyEvent e) {

        int keyPressed = e.getKeyCode();

        if (keyPressed == ' ') {
            isStarted = true;
            tm.start();
            repaint();
        }

        if (!isStarted) {
            return;
        }

        switch (keyPressed) {

            case 'r', 'R' -> {
                ArrayList<Point> rotationPoints = mainBlock.getRotationPoints();
                ArrayList<Point> absoluteRotationPoints = mainBlock.getAbsolutePosition(rotationPoints);
                for (Point point : absoluteRotationPoints)
                    if (point.x >= 10 || point.x < 0 || isOccupiedBoard[point.y][point.x]) {
                        return;
                    }

                mainBlock.setBlocksPosition(rotationPoints);
                repaint();
            }

            case KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT -> {

                int shift = keyPressed == KeyEvent.VK_RIGHT ? +1 : -1;
                ArrayList<Point> positions = mainBlock.getAbsolutePosition();
                for (Point point : positions)
                    if ((shift == 1 && point.x >= 9) || (shift == -1 && point.x <= 0) || isOccupiedBoard[point.y][point.x + shift]) {
                        return;
                    }

                mainBlock.position.x += shift * 50;
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
                if (!isOccupiedBoard[i][j]) {
                    fullRow = false;
                    break;
                }

            if (!fullRow) {
                continue;
            }

            for (int x = i; x > 0; x--) {
                isOccupiedBoard[x] = isOccupiedBoard[x - 1];
                colorsBoard[x] = colorsBoard[x - 1];
            }

            isOccupiedBoard[0] = new boolean[10];
            colorsBoard[0] = new Color[10];

        }
    }

    public void checkGameOver() {
        for (int i = 0; i < 10; i++)
            if (isOccupiedBoard[2][i]) {
                isLost = true;
                tm.stop();
                return;
            }
    }


}