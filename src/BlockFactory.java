import java.awt.*;
import java.util.ArrayList;
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
        ArrayList<Point> position = new ArrayList<>();

        for(int i=0; i<4; i++)
            position.add(new Point(blocksPositionX[k][i], blocksPositionY[k][i]));


        switch (k) {
            case 0 -> {
                return new Block(Color.cyan, position, 4);
            }
            case 1 -> {
                return new Block(Color.blue, position, 3);
            }
            case 2 -> {
                return new Block(Color.orange, position, 3);
            }
            case 3 -> {
                return new Block(Color.yellow, position, 2);
            }
            case 4 -> {
                return new Block(Color.green, position, 3);
            }
            case 5 -> {
                return new Block(new Color(92, 46, 145), position, 3);
            }
            case 6 -> {
                return new Block(Color.red, position, 3);
            }
            default -> throw new RuntimeException();
        }
    }




}
