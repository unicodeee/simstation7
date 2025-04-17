package greed;

import mvc.Model;
import simstation.WorldPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class MeadowPanel extends WorldPanel {
    private JSlider greedinessSlider;
    private JLabel greedinessLabel;


    private JSlider growBackRateSlider;
    private JLabel growBackRateLabel;


    private JSlider moveEnergySlider;
    private JLabel moveEnergyLabel;



    private Meadow meadow;
    private String[] labels = {"Greed", "Grow back rate", "Move Energy"};

    public MeadowPanel(GreedFactory factory) {
        super(factory);
        meadow = (Meadow) model;
        addSliderPanels();
    }

    private void addSliderPanels() {
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
        sliderPanel.setBackground(Color.PINK);
        sliderPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));



        JPanel panel;

        panel = createLabeledSlider(labels[0] + ":", 0, 100, 25, 10);
        sliderPanel.add(panel);
        sliderPanel.add(Box.createVerticalStrut(10));

        panel = createLabeledSlider(labels[1] + ":", 0, 10, 1, 10);
        sliderPanel.add(panel);
        sliderPanel.add(Box.createVerticalStrut(10));

        panel = createLabeledSlider(labels[2] + ":", 0, 50, 10, 10);
        sliderPanel.add(panel);
        sliderPanel.add(Box.createVerticalStrut(10));





        controlPanel.add(sliderPanel, BorderLayout.SOUTH);
        controlPanel.revalidate();
        controlPanel.repaint();
    }

    private JPanel createLabeledSlider(String labelText, int min, int max, int initial, int majorTick) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel titleLabel = new JLabel(labelText);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel valueLabel = new JLabel(String.valueOf(initial));
        valueLabel.setHorizontalAlignment(JLabel.CENTER);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        panel.add(titleLabel);
        panel.add(valueLabel);
        panel.add(Box.createVerticalStrut(5));

        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, initial);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();

        // AX: fewer labels
        if (max > 100) {
            int step = (max == 200) ? 20 : 50;
            for (int i = min; i <= max; i += step) {
                labelTable.put(i, new JLabel(String.valueOf(i)));
            }
        } else {
            // For percentage sliders
            for (int i = min; i <= max; i += 10) {
                labelTable.put(i, new JLabel(String.valueOf(i)));
            }
        }

        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(majorTick);
        slider.setMinorTickSpacing(majorTick/2);

        // extra height for slider:
        slider.setPreferredSize(new Dimension(slider.getPreferredSize().width, 50));

        if (labelText.contains(labels[0])) { // Greed variable
            greedinessSlider = slider;
            greedinessLabel = valueLabel;
            slider.addChangeListener(e -> updateGreediness());
        }
        else if (labelText.contains(labels[1])) {
            growBackRateSlider = slider;
            growBackRateLabel = valueLabel;
            slider.addChangeListener(e -> updateGrowBackRate());
        }
        else if (labelText.contains(labels[2])) {
            moveEnergySlider = slider;
            moveEnergyLabel = valueLabel;
            slider.addChangeListener(e -> updateMoveEnergy());
        }

        // Add slider to panel
        panel.add(slider);
        return panel;
    }

    @Override
    public void setModel(Model m) {
        super.setModel(m);
        if (m instanceof Meadow) {
            meadow = (Meadow)m;
        }
    }

    // adjust  upadte sliders
    private void updateGreediness() {
        int value = greedinessSlider.getValue();
        greedinessLabel.setText(String.valueOf(value));
        if (meadow != null) {
            meadow.setGreediness(value);
        }
    }

    private void updateGrowBackRate() {
        int value = growBackRateSlider.getValue();
        growBackRateLabel.setText(String.valueOf(value));
        if (meadow != null) {
            meadow.setGrowBackRate(value);
        }
    }

    private void updateMoveEnergy() {
        int value = moveEnergySlider.getValue();
        moveEnergyLabel.setText(String.valueOf(value));
        if (meadow != null) {
            meadow.setMoveEnergy(value);
        }
    }

    // main
    public static void main(String[] args) {
        MeadowPanel panel = new MeadowPanel(new GreedFactory());
        panel.display();
    }
}