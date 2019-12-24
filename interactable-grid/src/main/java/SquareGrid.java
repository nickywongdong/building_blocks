import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareGrid extends JFrame{

    private static final JSplitPane splitPane = new JSplitPane();
    private static final JPanel topPanel = new JPanel();
    private static final JPanel bottomPanel = new JPanel();

    private static int dimension;
    private static int cell;

    private boolean start = false;
    private boolean end = false;

    private Point startCoord = new Point();
    private Point endCoord = new Point();

    public SquareGrid(int dimension, int cell) {

        this.cell = cell;
        this.dimension = dimension;

        createInitialPanel();
        createResetPanel();

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(600);
        add(splitPane);
        setVisible(true);
        setSize(dimension, dimension);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void createResetPanel() {
        //final JPanel panel = new JPanel();
        bottomPanel.setLayout(new GridLayout());

        JButton button = new JButton("Reset");
        button.setName("ResetButton");
        button.setBackground(Color.white);
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                resetGrid();
            }
        });
        bottomPanel.add(button);
        splitPane.setBottomComponent(bottomPanel);
    }

    public void createInitialPanel() {
        topPanel.setLayout(new GridLayout(cell, cell));

        for(int i=0; i<cell; i++) {
            for(int j=0; j<cell; j++) {
                JButton button = new JButton(i + ", " + j);
                button.setName("empty");
                button.setBackground(Color.white);
                button.setOpaque(true);
                //button.setBorderPainted(false);
                int finalI = i;
                int finalJ = j;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!start) {
                            System.out.println("Starting Point Initialized");
                            button.setBackground(new Color(3, 115, 252));
                            button.setName("start");
                            button.setText("Start");
                            startCoord.setLocation(finalI, finalJ);
                            start = true;
                        } else if(!end) {
                            System.out.println("Setting End Point");
                            button.setBackground(new Color(245, 138, 66));
                            button.setName("end");
                            button.setText("End");
                            endCoord.setLocation(finalI, finalJ);
                            end = true;

                            // Display path upon selecting finish point
                            bruteForcePath();
                        }
                    }
                });
                topPanel.add(button);
            }
        }
        splitPane.setTopComponent(topPanel);
    }

    public void resetGrid() {
        // Reset start and end booleans
        this.start = false;
        this.end = false;

        // Reset coordinate values
        startCoord.setLocation(0, 0);

        // Reset top panel (grid portion)
        Component[] components = topPanel.getComponents();

        for(int i=0; i<cell; i++) {
            for(int j=0; j<cell; j++) {
                // Calculate corresponding button in components array
                int index = ( (10 * i) + j );
                // Reset color, name, and text of button
                if(components[index] instanceof JButton) {
                    JButton button = (JButton) components[index];
                    button.setBackground(Color.white);
                    button.setName("empty");
                    button.setText(i + ", " + j);
                } else {
                    throw new IllegalStateException("Component in grid at: " + i + ", " + j + " was not an instance of JButton");
                }
            }
        }
    }

    public void bruteForcePath() {
        // Flag used to signify first entry into loops
        boolean flag = false;

        Component[] components = topPanel.getComponents();

        for(int i=startCoord.x; i<cell; i++) {
            for(int j=0; j<cell; j++) {
                // We want to be able to start at the "start" button, but not remain there when traversing to end
                if(!flag) {
                    j = startCoord.y;
                    flag = true;
                }
                // Calculate corresponding button in components array
                int index = ( (10 * i) + j );

                // Ensure component is indeed button
                if(components[index] instanceof JButton) {
                    JButton button = (JButton) components[index];

                    if(button.getName().equals("start")) {
                        continue;
                    }
                    if(button.getName().equals("end")) {
                        return;
                    }

                    System.out.printf("Setting path component at %d, %d\n", i, j);    // Debug Statement
                    button.setBackground(new Color(123, 245, 66));
                    button.setText("--");
                } else {
                    throw new IllegalStateException("Component in grid at: " + i + ", " + j + " was not an instance of JButton");
                }
            }
        }
    }

    public static void main(String[] args) {
        //new SquareGrid();
        new SquareGrid(1000, 10);
    }
}
