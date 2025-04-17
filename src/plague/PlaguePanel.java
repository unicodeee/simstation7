package plague;

import simstation.WorldPanel;

import javax.swing.*;
import java.awt.*;

public class PlaguePanel extends WorldPanel {

    private final JToggleButton fatalToggle;

    private JSlider initSlider, virSlider, popSlider, recSlider;
    private JLabel initLabel, virLabel, popLabel, recLabel;

    public PlaguePanel(PlagueFactory factory) {
        super(factory);


        Box sliderBox = Box.createVerticalBox();
        sliderBox.add(Box.createVerticalStrut(10));

        initLabel = new JLabel("Initial % Infected: " + PlagueSimulation.INITIAL_INFECTED);
        initSlider = createSlider(0, 100, PlagueSimulation.INITIAL_INFECTED, 10);
        initSlider.addChangeListener(e -> {
            PlagueSimulation.INITIAL_INFECTED = initSlider.getValue();
            initLabel.setText("Initial % Infected: " + PlagueSimulation.INITIAL_INFECTED);
        });
        sliderBox.add(initLabel);
        sliderBox.add(initSlider);


        virLabel = new JLabel("Infection Probability: " + PlagueSimulation.VIRULENCE);
        virSlider = createSlider(0, 100, PlagueSimulation.VIRULENCE, 10);
        virSlider.addChangeListener(e -> {
            PlagueSimulation.VIRULENCE = virSlider.getValue();
            virLabel.setText("Infection Probability: " + PlagueSimulation.VIRULENCE);
        });
        sliderBox.add(Box.createVerticalStrut(10));
        sliderBox.add(virLabel);
        sliderBox.add(virSlider);


        popLabel = new JLabel("Initial Population Size: " + PlagueSimulation.POPULATION_SIZE);
        popSlider = createSlider(0, 200, PlagueSimulation.POPULATION_SIZE, 20);
        popSlider.addChangeListener(e -> {
            PlagueSimulation.POPULATION_SIZE = popSlider.getValue();
            popLabel.setText("Initial Population Size: " + PlagueSimulation.POPULATION_SIZE);
        });
        sliderBox.add(Box.createVerticalStrut(10));
        sliderBox.add(popLabel);
        sliderBox.add(popSlider);


        recLabel = new JLabel("Fatality/Recovery Time: " + PlagueSimulation.RECOVERY_TIME);
        recSlider = createSlider(0, 500, PlagueSimulation.RECOVERY_TIME, 100);
        recSlider.addChangeListener(e -> {
            PlagueSimulation.RECOVERY_TIME = recSlider.getValue();
            recLabel.setText("Fatality/Recovery Time: " + PlagueSimulation.RECOVERY_TIME);
        });
        sliderBox.add(Box.createVerticalStrut(10));
        sliderBox.add(recLabel);
        sliderBox.add(recSlider);


        fatalToggle = new JToggleButton("Not Fatal");
        fatalToggle.setSelected(false);
        fatalToggle.addActionListener(e -> {
            boolean isFatal = !fatalToggle.isSelected();  // selected = "Not Fatal"
            if (model instanceof PlagueSimulation sim) {
                sim.setFatal(isFatal);
            }
            fatalToggle.setText(isFatal ? "Fatal" : "Not Fatal");
        });

        JPanel togglePanel = new JPanel();
        togglePanel.add(fatalToggle);
        sliderBox.add(Box.createVerticalStrut(10));
        sliderBox.add(togglePanel);


        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(threadPanel, BorderLayout.NORTH);
        controlPanel.add(sliderBox, BorderLayout.CENTER);
    }

    private JSlider createSlider(int min, int max, int initial, int majorTick) {
        JSlider slider = new JSlider(min, max, initial);
        slider.setMajorTickSpacing(majorTick);
        slider.setMinorTickSpacing(majorTick / 2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    @Override
    public void setModel(mvc.Model m) {
        super.setModel(m);
        if (m instanceof PlagueSimulation sim) {
            sim.setFatal(!fatalToggle.isSelected());
        }
    }

    public static void main(String[] args) {
        PlagueSimulation.main(args);
    }

}
