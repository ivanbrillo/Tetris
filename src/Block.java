import java.awt.*;
import java.util.Arrays;

public class Block {

    private Color color;
    public int posizionex = 150;
    public int posizioney = 50;
    public int PosX[];
    public int PosY[];


    private int lenght;

    public Block(Color color, int[] vettx, int[] vetty, int lenght) {

        this.color = color;
        PosX = vettx;
        PosY = vetty;
        this.lenght = lenght;

    }

    public Block(Block block){

        this.color = block.color;
        PosX = Arrays.copyOf(block.PosX, block.PosX.length);
        PosY = Arrays.copyOf(block.PosY, block.PosY.length);
        this.lenght = block.lenght;


    }


    public void rotate() {

        if(color.equals(Color.yellow))
            return;

        for (int i = 0; i < 4; i++) {
            int tmp = PosX[i];

            PosX[i] = PosY[i];
            PosY[i] = 1 - (tmp - (lenght - 2));

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


    public void drop() {
        posizioney += 50;
    }

    public Color getColor(){ return color;}

}