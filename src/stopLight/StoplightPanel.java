package stopLight;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import mvc.*;


public class StoplightPanel extends AppPanel {
    private JButton change;

    public StoplightPanel(AppFactory factory) {
        super(factory);
        change = new JButton("Change");
        change.addActionListener(this);
        controlPanel.add(change);
    }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    //     String cmmd = e.getActionCommand();
    // }

    public static void main(String[] args) {
        AppFactory factory = new StoplightFactory();
        AppPanel panel = new StoplightPanel(factory);
        panel.display();
    }

}


