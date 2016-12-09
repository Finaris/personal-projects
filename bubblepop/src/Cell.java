import java.awt.*;

/**
 * Created by finaris on 12/3/16.
 */

class Cell {

    private Color color;
    private int x, y, i, j;

    Cell(Color color, int x, int y, int i, int j) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
    }

    Color getColor() {
        return color;
    }

    int getX() {
        return x;
    }

    int getY() { return y; }

    int getI() { return i; }

    int getJ() { return j; }

}
