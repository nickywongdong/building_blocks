import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SquareGrid {

    private static final JFrame frame = new JFrame("Square Grid");
    private static int dimension;
    private static int cell;

    public SquareGrid() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        panel.add(new JButton("button1"));
        panel.add(new JButton("button2"));
        panel.add(new JButton("button3"));
        panel.add(new JButton("button4"));
        panel.add(new JButton("button5"));
        panel.add(new JButton("button6"));
        panel.add(new JButton("button7"));
        panel.add(new JButton("button8"));
        panel.add(new JButton("button9"));

        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public SquareGrid(int dimension, int cell) {

        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(cell, cell));

        List<JButton> buttonList = new ArrayList<JButton>();
        for(int i=0; i<cell; i++) {
            for(int j=0; j<cell; j++) {
                JButton button = new JButton(i + ", " + j);
                panel.add(button);
                buttonList.add(button);
            }
        }

        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(dimension, dimension);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        //new SquareGrid();
        new SquareGrid(1000, 10);
    }
}
