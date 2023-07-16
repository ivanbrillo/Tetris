import java.awt.*;
import java.util.Random;

public class BlockFactory {

    private static final Random random = new Random();

    final static int[][] vettPosX = {
            {0, 1, 2, 3},
            {0, 0, 1, 2},
            {0, 1, 2, 2},
            {1, 1, 2, 2},
            {0, 1, 1, 2},
            {0, 1, 1, 2},
            {0, 1, 1, 2},
    };
    final static int[][] vettPosY = {
            {1, 1, 1, 1},
            {0, 1, 1, 1},
            {1, 1, 1, 0},
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {1, 0, 1, 1},
            {0, 0, 1, 1},
    };


    public static Block getRandomBlock() {
        int k = random.nextInt(7);

        switch (k) {
            case 0 -> {
                return new Block(Color.cyan, 2, 2, vettPosX[k], vettPosY[k]);
            }
            case 1 -> {
                return new Block(Color.blue, 1.5, 1.5, vettPosX[k], vettPosY[k]);
            }
            case 2 -> {
                return new Block(Color.orange, 1.5, 1.5, vettPosX[k], vettPosY[k]);
            }
            case 3 -> {
                return new Block(Color.yellow, 1.5, 1.5, vettPosX[k], vettPosY[k]);
            }
            case 4 -> {
                return new Block(Color.green, 1.5, 1.5, vettPosX[k], vettPosY[k]);
            }
            case 5 -> {
                return new Block(new Color(92, 46, 145), 1.5, 1.5, vettPosX[k], vettPosY[k]);
            }
            case 6 -> {
                return new Block(Color.red, 1.5, 1.5, vettPosX[k], vettPosY[k]);
            }
        }
        return null;
    }




}
