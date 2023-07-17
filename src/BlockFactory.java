import java.awt.*;
import java.util.Random;

public class BlockFactory {

    private static final Random random = new Random();

    final static int[][] blocksPositionX = {
            {0, 1, 2, 3},
            {0, 0, 1, 2},
            {0, 1, 2, 2},
            {1, 1, 2, 2},
            {0, 1, 1, 2},
            {0, 1, 1, 2},
            {0, 1, 1, 2},
    };
    final static int[][] blocksPositionY = {
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
                return new Block(Color.cyan, blocksPositionX[k], blocksPositionY[k], 4);
            }
            case 1 -> {
                return new Block(Color.blue, blocksPositionX[k], blocksPositionY[k], 3);
            }
            case 2 -> {
                return new Block(Color.orange, blocksPositionX[k], blocksPositionY[k], 3);
            }
            case 3 -> {
                return new Block(Color.yellow, blocksPositionX[k], blocksPositionY[k], 2);
            }
            case 4 -> {
                return new Block(Color.green, blocksPositionX[k], blocksPositionY[k], 3);
            }
            case 5 -> {
                return new Block(new Color(92, 46, 145), blocksPositionX[k], blocksPositionY[k], 3);
            }
            case 6 -> {
                return new Block(Color.red, blocksPositionX[k], blocksPositionY[k], 3);
            }
            default -> throw new RuntimeException();
        }
    }




}
