import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BoardController implements KeyListener, ActionListener {

    private final Timer tm = new Timer(150, this);
    Block mainBlock = BlockFactory.getRandomBlock();
    Block nextBlock = BlockFactory.getRandomBlock();
    boolean[][] isOccupiedBoard = new boolean[16][10];
    Color[][] colorsBoard = new Color[16][10];
    private int timer = 0;
    boolean descending = false;
    boolean isStarted = false;
    boolean isLost = false;

    final BoardView view;

    public BoardController() {
        view = new BoardView(this);
    }

    public void actionPerformed(ActionEvent e) {
        timer += 150;
        if (timer == 2550 || descending)
            descent();
    }

    public void descent() {

        ArrayList<Point> absPath = mainBlock.getAbsolutePosition();

        descending = true;
        for (Point point : absPath)
            if (point.y >= 15 || point.x >= 10 || point.x < 0 || isOccupiedBoard[point.y + 1][point.x]) {
                endDescent(absPath);
                view.repaint();
                return;
            }

        mainBlock.drop();
        view.repaint();
    }

    private void endDescent(ArrayList<Point> absPath) {

        timer = 0;
        descending = false;

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
            view.repaint();
        }

        if (!isStarted)
            return;

        switch (keyPressed) {

            case 'r', 'R' -> {
                ArrayList<Point> rotationPoints = mainBlock.getRotationPoints();
                ArrayList<Point> absoluteRotationPoints = mainBlock.getAbsolutePosition(rotationPoints);
                for (Point point : absoluteRotationPoints)
                    if (point.x >= 10 || point.x < 0 || isOccupiedBoard[point.y][point.x]) {
                        return;
                    }

                mainBlock.setBlocksPosition(rotationPoints);
                view.repaint();
            }

            case KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT -> {

                int shift = keyPressed == KeyEvent.VK_RIGHT ? +1 : -1;
                ArrayList<Point> positions = mainBlock.getAbsolutePosition();
                for (Point point : positions)
                    if ((shift == 1 && point.x >= 9) || (shift == -1 && point.x <= 0) || isOccupiedBoard[point.y][point.x + shift]) {
                        return;
                    }

                mainBlock.position.x += shift * 50;
                view.repaint();

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