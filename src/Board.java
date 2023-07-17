import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements KeyListener, ActionListener {

    Timer tm = new Timer(150, this);
    Block prova = BlockFactory.getRandomBlock();
    Block prova2 = BlockFactory.getRandomBlock();
    boolean scacchiera[][] = new boolean[16][10];
    Color scacchiera2[][] = new Color[16][10];
    int timer = 0;
    boolean stascendendo = false;
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

            g.drawString("Premere spazio per iniziare", 210, 600);

        } else {
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
    }

    public void actionPerformed(ActionEvent e) {
        timer += 150;
        if (timer == 2550 || stascendendo)
            scendi();
    }

    public void scendi() {
        int[] PosMax = new int[4];
        int[] PosMaxX = new int[4];
        for (int i = 0; i < 4; i++) {
            PosMax[i]--;
            PosMaxX[i]--;
        }
        prova.minore(PosMax, PosMaxX);

        if ((PosMax[0] == -1 || (PosMax[0] < 15 && !scacchiera[PosMax[0] + 1][PosMaxX[0]])) && (PosMax[1] == -1 || (PosMax[1] < 15 && scacchiera[PosMax[1] + 1][PosMaxX[1]] == false)) && (PosMax[2] == -1 || (PosMax[2] < 15 && scacchiera[PosMax[2] + 1][PosMaxX[2]] == false)) && (PosMax[3] == -1 || (PosMax[3] < 15 && scacchiera[PosMax[3] + 1][PosMaxX[3]] == false))) {
            prova.drop();
            stascendendo = true;
            repaint();
        } else {
            timer = 0;
            for (int i = 0; i < 4; i++) {
                scacchiera[(prova.getPoint(i).y * 50 + prova.position.y) / 50][(prova.getPoint(i).x * 50 + prova.position.x) / 50] = true;
                scacchiera2[(prova.getPoint(i).y * 50 + prova.position.y) / 50][(prova.getPoint(i).x * 50 + prova.position.x) / 50] = prova.getColor();
            }
            stascendendo = false;
            checkRiga();
            checkFregature();

            prova = prova2;
            prova2 = BlockFactory.getRandomBlock();

            repaint();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if ((e.getKeyChar() == 'r' || e.getKeyChar() == 'R') && inizio) {
            int posX[] = new int[4];
            int posY[] = new int[4];
            for (int i = 0; i < 4; i++) {
                posX[i] = prova.getPoint(i).x;
                posY[i] = prova.getPoint(i).y;
            }
            Block prova2 = new Block(prova);

            prova2.rotate();
//            System.out.println(prova2.blocksPosition);
            int k = 0;
            for (int i = 0; i < 4; i++) {
                if (prova2.getPoint(i).x * 50 + prova2.position.x < 500 && prova2.getPoint(i).x * 50 + prova2.position.x > 0 && (!scacchiera[(prova2.getPoint(i).y * 50 + prova2.position.y) / 50][(prova2.getPoint(i).x * 50 + prova2.position.x) / 50]))
                    k++;
            }
            if (k == 4) {
//                System.out.println(prova.blocksPosition);
                prova.rotate();

                repaint();
            }
        }

        if (e.getKeyCode() == 39 && inizio) {
            int[] PosMax = new int[4];
            int[] PosMaxY = new int[4];
            for (int i = 0; i < 4; i++) {
                PosMaxY[i]--;
                PosMax[i]--;
            }
            prova.maggioreX(PosMax, PosMaxY);
            int k = 0;
            for (int i = 0; i < 4; i++) {
                if (prova.getPoint(i).x * 50 + prova.position.x < 450)
                    k++;
                if (PosMax[i] == -1 || (PosMax[i] + 1) > 9 || !scacchiera[PosMaxY[i]][PosMax[i] + 1])
                    k++;
            }
            if (k == 8) {
                prova.position.x += 50;
                repaint();
            }
        }

        if (e.getKeyCode() == 37 && inizio) {
            int PosMax[] = new int[4];
            int PosMaxY[] = new int[4];
            for (int i = 0; i < 4; i++) {
                PosMaxY[i] = 99;
                PosMax[i] = 99;
            }
            prova.minoreX(PosMax, PosMaxY);
            int k = 0;
            for (int i = 0; i < 4; i++) {
                if (prova.getPoint(i).x * 50 + prova.position.x > 0)
                    k++;
                if (PosMax[i] == 99 || (PosMax[i] - 1) < 0 || scacchiera[PosMaxY[i]][PosMax[i] - 1] == false)
                    k++;
            }
            if (k == 8) {
                prova.position.x -= 50;
                repaint();
            }
        }

        if ((e.getKeyChar() == 'f' || e.getKeyChar() == 'F') && inizio) {
            stascendendo = true;
        }

        if (e.getKeyChar() == ' ') {
            if (!inizio) {
                inizio = true;
                tm.start();
                repaint();
            }
        }


    }

    public void keyReleased(KeyEvent e) {
    }

    public void checkRiga() {
        int k = 0;
        boolean eliminariga = false;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 10; j++) {
                if (scacchiera[i][j] == true) {
                    k++;
                }
            }
            if (k >= 10) {
                //scacchiera[i]=new boolean[10];
                // scacchiera2[i] = new Color[10];
                //boolean tmp[] = new boolean[10];
                //Color tmp2[] = new Color[10];
                for (int x = i; x > 0; x--) {
                    scacchiera[x] = scacchiera[x - 1];
                    scacchiera2[x] = scacchiera2[x - 1];
                }
                scacchiera[0] = new boolean[10];
                scacchiera2[0] = new Color[10];
                //checkRiga();
                eliminariga = true;
            }
            k = 0;
            repaint();
        }
    }

    public void checkFregature() {
        for (int i = 0; i < 10; i++)
            if (scacchiera[2][i] == true) {
                perdita = true;

                tm.stop();
                repaint();
                break;
            }
    }


}