import java.awt.*;
import java.util.ArrayList;

public class Block {

    private final Color color;
    Point position = new Point(150, 50);
    private ArrayList<Point> blocksPosition;
    private final int length;

    public Block(Color color, ArrayList<Point> blocksPosition, int length) {

        this.blocksPosition = blocksPosition;
        this.color = color;
        this.length = length;

    }

//    public Block(Block block) {
//
//        this.color = block.color;
//        position = new Point(position);
//
//        blocksPosition = new ArrayList<>(block.blocksPosition.size());
//        for (Point point : block.blocksPosition)
//            blocksPosition.add(new Point(point));
//
//        this.length = block.length;
//
//    }

    public ArrayList<Point> getRotationPoints() {

        if (color.equals(Color.yellow))
            return blocksPosition;

        ArrayList<Point> rotationPoints = new ArrayList<>();

        for (int i = 0; i < 4; i++)
            rotationPoints.add(new Point(blocksPosition.get(i).y, 1 - (blocksPosition.get(i).x - (length - 2))));

        return rotationPoints;


    }

    public ArrayList<Point> getAbsolutePosition(ArrayList<Point> points) {

        ArrayList<Point> absolutePosition = new ArrayList<>(4);

        for (int j = 0; j < 4; j++)
            absolutePosition.add(new Point((points.get(j).x * 50 + position.x) / 50, (points.get(j).y * 50 + position.y) / 50));

        return absolutePosition;
    }

    public ArrayList<Point> getAbsolutePosition() {
        return getAbsolutePosition(blocksPosition);
    }

    public void setBlocksPosition(ArrayList<Point> blocksPosition){
        this.blocksPosition = blocksPosition;
    }



    public void maggioreX(int posMax[], int PosMaxX[]) {
        for (int x = 0; x < 4; x++)
            for (int j = 0; j < 4; j++) {
                if (blocksPosition.get(j).y == x) {
                    if (posMax[x] < blocksPosition.get(j).x) {
                        posMax[x] = blocksPosition.get(j).x;
                        PosMaxX[x] = blocksPosition.get(j).y;
                    }
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
                if (blocksPosition.get(j).y == x) {
                    if (posMax[x] > blocksPosition.get(j).x) {
                        posMax[x] = blocksPosition.get(j).x;
                        PosMaxX[x] = blocksPosition.get(j).y;
                    }
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

    public Point getPoint(int i) {
        return new Point(blocksPosition.get(i));
    }

    public Color getColor() {
        return color;
    }

}