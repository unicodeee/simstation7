package mineField;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;

public class MineFieldPanel extends AppPanel {

    private JButton northwest;
    private JButton north;
    private JButton northeast;
    private JButton west;
    private JButton east;
    private JButton southwest;
    private JButton south;
    private JButton southeast;

    public MineFieldPanel(AppFactory factory) {
        super(factory);
        northwest = new JButton("NW");
        north = new JButton("N");
        northeast = new JButton("NE");
        west = new JButton("W");
        east = new JButton("E");
        southwest = new JButton("SW");
        south = new JButton("S");
        southeast = new JButton("SE");
        northwest.addActionListener(this);
        north.addActionListener(this);
        northeast.addActionListener(this);
        west.addActionListener(this);
        east.addActionListener(this);
        southwest.addActionListener(this);
        south.addActionListener(this);
        southeast.addActionListener(this);

        // Add buttons (optionally add action listeners)
        controlPanel.add(northwest);
        controlPanel.add(north);
        controlPanel.add(northeast);
        controlPanel.add(west);
        controlPanel.add(east);
        controlPanel.add(southwest);
        controlPanel.add(south);
        controlPanel.add(southeast);

        // Set layout for the control panel
        controlPanel.setLayout(new GridLayout(4, 2));
    }

    public static void main(String[] args) {
        MineFieldFactory factory = new MineFieldFactory();
        MineFieldPanel panel = new MineFieldPanel(factory);
        panel.display();
    }
}
