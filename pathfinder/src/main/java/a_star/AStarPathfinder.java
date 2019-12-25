package a_star;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class AStarPathfinder {
    /* Starting point of path */
    private final Point start;
    /* Ending point of path */
    private final Point end;
    /* Grid representation */
    private Grid grid;

    /* List of nodes that have been visited */
    private ArrayList<Node> visitedSet = new ArrayList<>();
    /* List of nodes that have yet to be visited */
    private ArrayList<Node> unvisitedSet = new ArrayList<>();

    public AStarPathfinder(Point start, Point end, int x, int y) {
        this.start = start;
        this.end = end;

        grid = new Grid(x, y);
    }

    public void findPath() {
        /* Add starting position to unvisited set */
        Node startNode = grid.getNode((int)start.getX(), (int)start.getY());
        unvisitedSet.add(startNode);

        while(true) {
            Node currentNode = findLowestCostNode();
            if(!unvisitedSet.contains(currentNode))  {
                throw new IllegalStateException("Current node not found within unvisited set");
            }

            unvisitedSet.remove(currentNode);
            visitedSet.add(currentNode);

            /* Path to end has been found */
            if(currentNode.getPoint() == end) {
                return;
            }

            detectNeighbors(currentNode);
            for (Node n : unvisitedSet) {
                if(!n.isObstacle() || !visitedSet.contains(n)) {
                    if(/*newPath < path || */!unvisitedSet.contains(n)) {
                        n.setParent(currentNode);
                        if(unvisitedSet.contains(n)) {
                            unvisitedSet.add(n);
                        }
                    }
                }
            }
        }

    }

    /* Method that determines neighbors available (within bounds) of provided node n */
    private void detectNeighbors(@NotNull Node n) {
        Point p = n.getPoint();

        /* TODO: Does this work for point (0, 0)? */
        int rowBoundary = grid.getRows() - 1;
        int colBoundary = grid.getCols() - 1;

        for(int i = (int) max(p.getX(), grid.getRows()-1); i<=min(rowBoundary, grid.getRows()+1); i++) {
            for(int j = (int) max(p.getY(), grid.getCols()-1); j<=min(colBoundary, grid.getCols()+1); j++) {
                if(i != grid.getRows() || j != grid.getCols()) {
                    unvisitedSet.add(grid.getNode(i, j));
                }
            }
        }
    }

    /* Determines node with the least f_cost within unvisited set */
    private Node findLowestCostNode() {
        if(unvisitedSet.size() == 0) {
            throw new IllegalStateException("Unvisited set has size 0, there are no nodes to check");
        }
        if(unvisitedSet.size() == 1) {
            return unvisitedSet.get(0);
        }
        Node closestNode = unvisitedSet.get(0);

        for(Node n : unvisitedSet) {
            if(closestNode.f_cost() < n.f_cost()) {
                closestNode = n;
            }
        }
        return closestNode;
    }
}
