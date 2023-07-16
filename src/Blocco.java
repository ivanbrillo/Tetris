import java.awt.*;
import java.util.Random;

public class Blocco {
    final int vettPosX[][] = {
            {0, 1, 2, 3},
            {0, 0, 1, 2},
            {0, 1, 2, 2},
            {1, 1, 2, 2},
            {0, 1, 1, 2},
            {0, 1, 1, 2},
            {0, 1, 1, 2},
    };
    final int vettPosY[][] = {
            {1, 1, 1, 1},
            {0, 1, 1, 1},
            {1, 1, 1, 0},
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {1, 0, 1, 1},
            {0, 0, 1, 1},
    };
    public int PosX[];
    public int PosY[];
    public Color colore;
    public int posizionex = 150;
    public int posizioney = 50;
    public int k = 0;
    double rotx = 0;
    double roty = 0;
    Random generatore = new Random();

    public Blocco() {
        pezzo();

        switch (k) {
            case 0:
                rotx = 2;
                roty = 2;
                colore = Color.cyan;
                break;
            case 1:
                rotx = 1.5;
                roty = 1.5;
                colore = Color.blue;
                break;
            case 2:
                rotx = 1.5;
                roty = 1.5;
                colore = Color.orange;
                break;
            case 3:
                rotx = 2;
                roty = 1;
                colore = Color.yellow;
                break;
            case 4:
                rotx = 1.5;
                roty = 1.5;
                colore = Color.green;
                break;
            case 5:
                rotx = 1.5;
                roty = 1.5;
                colore = new Color(92, 46, 145);
                break;
            case 6:
                rotx = 1.5;
                roty = 1.5;
                colore = Color.red;
                break;
        }
    }

    public Blocco(int k, int vettx[], int vetty[]) {
        PosX = vettx;
        PosY = vetty;
        this.k = k;

        switch (k) {
            case 0:
                rotx = 2;
                roty = 2;
                colore = Color.cyan;
                break;
            case 1:
                rotx = 1.5;
                roty = 1.5;
                colore = Color.blue;
                break;
            case 2:
                rotx = 1.5;
                roty = 1.5;
                colore = Color.orange;
                break;
            case 3:
                rotx = 2;
                roty = 1;
                colore = Color.yellow;
                break;
            case 4:
                rotx = 1.5;
                roty = 1.5;
                colore = Color.green;
                break;
            case 5:
                rotx = 1.5;
                roty = 1.5;
                colore = new Color(92, 46, 145);
                break;
            case 6:
                rotx = 1.5;
                roty = 1.5;
                colore = Color.red;
                break;
        }
    }

    public void pezzo() {
        k = generatore.nextInt(7);
        PosX = vettPosX[k];
        PosY = vettPosY[k];
    }

    public void gira() {
        for (int i = 0; i < 4; i++) {
            int tmp = PosX[i];
            PosX[i] = (int) (-PosY[i] + rotx + roty) - 1;
            PosY[i] = (int) (tmp - rotx + roty);

        }
    }

    public void minore(int posMax[], int PosMaxX[]) {
        for (int x = 0; x < 4; x++)
            for (int j = 0; j < 4; j++) {
                if (PosX[j] == x)
                    if (posMax[x] < PosY[j]) {
                        posMax[x] = PosY[j];
                        PosMaxX[x] = PosX[j];
                    }
            }
        for (int j = 0; j < 4; j++)
            if (posMax[j] != -1) {
                PosMaxX[j] = (PosMaxX[j] * 50 + posizionex) / 50;
                posMax[j] = (posMax[j] * 50 + posizioney) / 50;
            }
    }

    public void maggioreX(int posMax[], int PosMaxX[]) {
        for (int x = 0; x < 4; x++)
            for (int j = 0; j < 4; j++) {
                if (PosY[j] == x)
                    if (posMax[x] < PosX[j]) {
                        posMax[x] = PosX[j];
                        PosMaxX[x] = PosY[j];
                    }

            }
        for (int j = 0; j < 4; j++)
            if (posMax[j] != -1) {
                posMax[j] = (posMax[j] * 50 + posizionex) / 50;
                PosMaxX[j] = (PosMaxX[j] * 50 + posizioney) / 50;
            }
    }

    public void minoreX(int posMax[], int PosMaxX[]) {
        for (int x = 0; x < 4; x++)
            for (int j = 0; j < 4; j++) {
                if (PosY[j] == x)
                    if (posMax[x] > PosX[j]) {
                        posMax[x] = PosX[j];
                        PosMaxX[x] = PosY[j];
                    }
            }
        for (int j = 0; j < 4; j++)
            if (posMax[j] != 99) {
                posMax[j] = (posMax[j] * 50 + posizionex) / 50;
                PosMaxX[j] = (PosMaxX[j] * 50 + posizioney) / 50;
            }
    }


    public void scendi() {
        posizioney += 50;
    }

}