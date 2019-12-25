package a_star;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Grid {
    /* Number of cells in the x direction */
    private final int rows;
    /* Number of cells in the y direction; */
    private final int cols;
    /* Representation of presented problem */
    List<Node[]> grid = new ArrayList<Node[]>();

    public Grid(int x, int y) {
        rows = x;
        cols = y;

        /* Initialize Grid representation */
        for (int i = 0; i < x; i++) {
            Node[] list = new Node[y];
            for (int j = 0; j < y; j++) {
                Node newNode = new Node(true, i, j);
                list[j] = newNode;
            }
            grid.add(list);
        }
    }

    public Node getNode(int x, int y) {
        return grid.get(x)[y];
    }

    public List getGrid() {
        return grid;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
