import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardView extends JPanel {

    private final Image startImage = new ImageIcon("src/resources/start.png").getImage();
    private final Image gameOverImage = new ImageIcon("src/resources/gameOver.jpg").getImage();
    private final BoardController board;

    public BoardView(BoardController board) {
        this.board = board;
        setPreferredSize(new Dimension(850, 802));
        setBackground(Color.black);
        addKeyListener(board);
        setFocusable(true);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!board.isStarted) {
            g.drawImage(startImage, 240, 120, this);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Press space to start", 265, 600);
            return;
        }

        if (!board.isLost) {

            g.setColor(board.mainBlock.getColor());
            ArrayList<Point> absPosition = board.mainBlock.getAbsolutePosition();

            for (Point point : absPosition)
                g.fillRect(point.x * 50, point.y * 50, 50, 50);

            for (int i = 0; i < 16; i++)
                for (int j = 0; j < 10; j++)
                    if (board.isOccupiedBoard[i][j]) {
                        g.setColor(board.colorsBoard[i][j]);
                        g.fillRect(j * 50, i * 50, 50, 50);
                    }

            paintMenu(g);

            g.setColor(board.nextBlock.getColor());
            ArrayList<Point> nextBlockAbsolutePosition = board.nextBlock.getAbsolutePosition();

            for (Point point : nextBlockAbsolutePosition)
                g.fillRect(point.x * 50 + 450, point.y * 50 + 150, 50, 50);

        }

        if (board.isLost)
            g.drawImage(gameOverImage, 250, 250, this);

    }

    private void paintMenu(Graphics g){

        g.setColor(Color.white);
        for (int j = 0; j <= 16; j++)
            g.drawLine(0, j * 50, 500, j * 50);
        for (int j = 0; j <= 10; j++)
            g.drawLine(j * 50, 0, j * 50, 800);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Calibri", Font.BOLD, 30));
        g.drawString("Next Piece:", 620, 150);

        g.setFont(new Font("Calibri", Font.PLAIN, 20));
        g.drawString("Press the arrows to move the piece", 520, 500);
        g.drawString("Press 'r' to rotate the piece", 520, 540);
        g.drawString("Press 'f' to start the descending", 520, 580);


    }


}
