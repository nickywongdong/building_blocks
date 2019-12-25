package a_star;

import java.awt.*;

public class Node {
    /* Determines if node is traversible or not */
    private boolean obstacle;
    // Distance from starting node
    private int g_cost;
    /* Distance from end node */
    private int h_cost;
    /* Point representation on grid */
    private final Point point;
    /* Parent of node */
    private Node parent;

    public Node(boolean traversible, int x, int y) {
        this.obstacle = !traversible;
        point = new Point(x, y);
        parent = null;
    }

    public int f_cost() {
        return g_cost + h_cost;
    }

    public Point getPoint() {
        return this.point;
    }

    public boolean isObstacle() {
        return obstacle;
    }

    public void setParent(Node n) {
        parent = n;
    }
}
