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

    public ArrayList<Point> getRotationPoints() {

        if (color.equals(Color.yellow)) {
            return blocksPosition;
        }

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

    public void setBlocksPosition(ArrayList<Point> blocksPosition) {
        this.blocksPosition = blocksPosition;
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