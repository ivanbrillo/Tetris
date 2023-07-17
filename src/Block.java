import java.awt.*;
import java.util.ArrayList;

public class Block {

    private final Color color;
    Point position = new Point(150, 50);
    private final ArrayList<Point> blocksPosition;
    private final int length;

    public Block(Color color, ArrayList<Point> blocksPosition, int length) {

        this.blocksPosition = blocksPosition;
        this.color = color;
        this.length = length;

    }

    public Block(Block block){

        this.color = block.color;
        position = new Point(position);

        blocksPosition = new ArrayList<> (block.blocksPosition.size());
        for (Point point : block.blocksPosition)
            blocksPosition.add(new Point(point));

        this.length = block.length;

    }


    public void rotate() {

        if(color.equals(Color.yellow))
            return;

        for (int i = 0; i < 4; i++) {
            int tmp = blocksPosition.get(i).x;

            blocksPosition.get(i).x = blocksPosition.get(i).y;
            blocksPosition.get(i).y = 1 - (tmp - (length - 2));

        }
    }

    public void minore(int posMax[], int PosMaxX[]) {
        for (int x = 0; x < 4; x++)
            for (int j = 0; j < 4; j++) {
                if (blocksPosition.get(j).x == x)
                    if (posMax[x] < blocksPosition.get(j).y) {
                        posMax[x] = blocksPosition.get(j).y;
                        PosMaxX[x] = blocksPosition.get(j).x;
                    }
            }
        for (int j = 0; j < 4; j++)
            if (posMax[j] != -1) {
                PosMaxX[j] = (PosMaxX[j] * 50 + position.x) / 50;
                posMax[j] = (posMax[j] * 50 + position.y) / 50;
            }
    }

    public void maggioreX(int posMax[], int PosMaxX[]) {
        for (int x = 0; x < 4; x++)
            for (int j = 0; j < 4; j++) {
                if (blocksPosition.get(j).y == x)
                    if (posMax[x] < blocksPosition.get(j).x) {
                        posMax[x] =blocksPosition.get(j).x;
                        PosMaxX[x] = blocksPosition.get(j).y;
                    }

            }
        for (int j = 0; j < 4; j++)
            if (posMax[j] != -1) {
                posMax[j] = (posMax[j] * 50 + position.x) / 50;
                PosMaxX[j] = (PosMaxX[j] * 50 + position.y) / 50;
            }
    }

    public void minoreX(int posMax[], int PosMaxX[]) {
        for (int x = 0; x < 4; x++)
            for (int j = 0; j < 4; j++) {
                if (blocksPosition.get(j).y == x)
                    if (posMax[x] > blocksPosition.get(j).x) {
                        posMax[x] = blocksPosition.get(j).x;
                        PosMaxX[x] = blocksPosition.get(j).y;
                    }
            }
        for (int j = 0; j < 4; j++)
            if (posMax[j] != 99) {
                posMax[j] = (posMax[j] * 50 + position.x) / 50;
                PosMaxX[j] = (PosMaxX[j] * 50 + position.y) / 50;
            }
    }


    public void drop() {
        position.y += 50;
    }

    public Point getPoint(int i){return new Point(blocksPosition.get(i));}

    public Color getColor(){ return color;}

}