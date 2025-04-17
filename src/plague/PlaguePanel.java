package plague;

import mvc.AppPanel;
import simstation.*;
import mvc.Model;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

public class PlaguePanel extends WorldPanel {
    // Sliders for various simulation parameters
    private JSlider initialInfectedSlider;
    private JSlider infectionProbabilitySlider;
    private JSlider populationSizeSlider;
    private JSlider recoveryTimeSlider;
    private JButton fatalityButton;

    // Value labels displayed above sliders
    private JLabel initialInfectedValue;
    private JLabel infectionProbabilityValue;
    private JLabel populationSizeValue;
    private JLabel recoveryTimeValue;

    // Reference to the simulation
    private PlagueSimulation plagueSimulation;

    public PlaguePanel(PlagueFactory factory) {
        super(factory);

        // Add the slider panels below the existing controls
        addSliderPanels();

    }

    private void addSliderPanels() {
        // Create a main panel to hold all the sliders
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
        sliderPanel.setBackground(Color.PINK);
        sliderPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create slider panels
        JPanel infectedPanel = createLabeledSlider("Initial % Infected:", 0, 100, 10, 10);
        JPanel probabilityPanel = createLabeledSlider("Infection Probability:", 0, 100, 50, 10);
        JPanel populationPanel = createLabeledSlider("Initial Population Size:", 0, 200, 50, 20);
        JPanel recoveryPanel = createLabeledSlider("Fatality/Recovery Time:", 0, 500, 200, 50);

        // Fatality button panel
        JPanel fatalityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fatalityPanel.setOpaque(false);
        fatalityButton = new JButton("Not Fatal");
        fatalityButton.setPreferredSize(new Dimension(100, 30));
        fatalityButton.addActionListener(e -> toggleFatality());
        fatalityPanel.add(fatalityButton);

        // Add slider panels to the main panel
        sliderPanel.add(infectedPanel);
        sliderPanel.add(Box.createVerticalStrut(10));
        sliderPanel.add(probabilityPanel);
        sliderPanel.add(Box.createVerticalStrut(10));
        sliderPanel.add(populationPanel);
        sliderPanel.add(Box.createVerticalStrut(10));
        sliderPanel.add(recoveryPanel);
        sliderPanel.add(Box.createVerticalStrut(10));
        sliderPanel.add(fatalityPanel);

        // Add the slider panel to the south position of the control panel
        controlPanel.add(sliderPanel, BorderLayout.SOUTH);

        // Update the control panel
        controlPanel.revalidate();
        controlPanel.repaint();

        PlagueSimulation.INITIAL_INFECTED = initialInfectedSlider.getValue();
        PlagueSimulation.POPULATION_SIZE = populationSizeSlider.getValue();
        PlagueSimulation.RECOVERY_TIME = recoveryTimeSlider.getValue();

    }

    private JPanel createLabeledSlider(String labelText, int min, int max, int initial, int majorTick) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        // Title label - centered
        JLabel titleLabel = new JLabel(labelText);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        // Value label - centered below title
        JLabel valueLabel = new JLabel(String.valueOf(initial));
        valueLabel.setHorizontalAlignment(JLabel.CENTER);
        valueLabel.setAlignmentX(CENTER_ALIGNMENT);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        // Add title and value to panel
        panel.add(titleLabel);
        panel.add(valueLabel);
        panel.add(Box.createVerticalStrut(5));

        // Create slider with custom labels
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, initial);

        // Only paint a few primary labels to avoid overlap
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();

        // For population size and recovery time, use fewer labels
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

        // Add extra height for the slider to accommodate labels
        slider.setPreferredSize(new Dimension(slider.getPreferredSize().width, 50));

        // Store slider and value label references for later use
        if (labelText.contains("Initial % Infected")) {
            initialInfectedSlider = slider;
            initialInfectedValue = valueLabel;
            slider.addChangeListener(e -> updateInitialInfected());
        } else if (labelText.contains("Infection Probability")) {
            infectionProbabilitySlider = slider;
            infectionProbabilityValue = valueLabel;
            slider.addChangeListener(e -> updateInfectionProbability());
        } else if (labelText.contains("Population Size")) {
            populationSizeSlider = slider;
            populationSizeValue = valueLabel;
            slider.addChangeListener(e -> updatePopulationSize());
        } else if (labelText.contains("Fatality/Recovery")) {
            recoveryTimeSlider = slider;
            recoveryTimeValue = valueLabel;
            slider.addChangeListener(e -> updateRecoveryTime());
        }

        // Add slider to panel
        panel.add(slider);

        return panel;
    }

    @Override
    public void setModel(Model m) {
        super.setModel(m);
        if (m instanceof PlagueSimulation) {
            plagueSimulation = (PlagueSimulation)m;
        }
    }

    // Update methods for sliders
    private void updateInitialInfected() {
        int value = initialInfectedSlider.getValue();
        initialInfectedValue.setText(String.valueOf(value));
        if (plagueSimulation != null) {
            plagueSimulation.setInitialInfectedPercent(value);
        }
    }

    private void updateInfectionProbability() {
        int value = infectionProbabilitySlider.getValue();
        infectionProbabilityValue.setText(String.valueOf(value));
        PlagueSimulation.VIRULENCE = value;
    }

    private void updatePopulationSize() {
        int value = populationSizeSlider.getValue();
        populationSizeValue.setText(String.valueOf(value));
        if (plagueSimulation != null) {
            plagueSimulation.setPopulationSize(value);
        }
    }

    private void updateRecoveryTime() {
        int value = recoveryTimeSlider.getValue();
        recoveryTimeValue.setText(String.valueOf(value));
        if (plagueSimulation != null) {
            plagueSimulation.setRecoveryTime(value);
        }
    }
    private void toggleFatality() {
        if (plagueSimulation != null) {
            plagueSimulation.changeFatal();
            boolean isFatal = plagueSimulation.isFatal();
            fatalityButton.setText(isFatal ? "Fatal" : "Not Fatal");
        }
    }
    // Main method to run the application
    public static void main(String[] args) {
        PlagueFactory factory = new PlagueFactory();
        PlaguePanel panel = new PlaguePanel(factory);
        panel.display();
    }
}


/*
package plague;

import simstation.WorldPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaguePanel extends WorldPanel {

    private final JToggleButton fatalToggle;

    public PlaguePanel(PlagueFactory factory) {
        super(factory);

        Box sliderBox = Box.createVerticalBox();

        // Initial % Infected
        JLabel initLabel = new JLabel("Initial % Infected:");
        JSlider initSlider = new JSlider(0, 100, PlagueSimulation.INITIAL_INFECTED);
        initSlider.setMajorTickSpacing(10);
        initSlider.setMinorTickSpacing(5);
        initSlider.setPaintTicks(true);
        initSlider.setPaintLabels(true);
        initSlider.addChangeListener(e -> {
            PlagueSimulation.INITIAL_INFECTED = initSlider.getValue();
            initLabel.setText("Initial % Infected: " + PlagueSimulation.INITIAL_INFECTED);
        });

        // Infection Probability (Virulence)
        JLabel virLabel = new JLabel("Infection Probability:");
        JSlider virSlider = new JSlider(0, 100, PlagueSimulation.VIRULENCE);
        virSlider.setMajorTickSpacing(10);
        virSlider.setMinorTickSpacing(5);
        virSlider.setPaintTicks(true);
        virSlider.setPaintLabels(true);
        virSlider.addChangeListener(e -> {
            PlagueSimulation.VIRULENCE = virSlider.getValue();
            virLabel.setText("Infection Probability: " + PlagueSimulation.VIRULENCE);
        });

        // Initial Population Size
        JLabel popLabel = new JLabel("Initial Population Size:");
        JSlider popSlider = new JSlider(0, 200, PlagueSimulation.POPULATION_SIZE);
        popSlider.setMajorTickSpacing(20);
        popSlider.setMinorTickSpacing(10);
        popSlider.setPaintTicks(true);
        popSlider.setPaintLabels(true);
        popSlider.addChangeListener(e -> {
            PlagueSimulation.POPULATION_SIZE = popSlider.getValue();
            popLabel.setText("Initial Population Size: " + PlagueSimulation.POPULATION_SIZE);
        });

        // Fatality/Recovery Time
        JLabel recLabel = new JLabel("Fatality/Recovery Time:");
        JSlider recSlider = new JSlider(0, 500, PlagueSimulation.RECOVERY_TIME);
        recSlider.setMajorTickSpacing(100);
        recSlider.setMinorTickSpacing(50);
        recSlider.setPaintTicks(true);
        recSlider.setPaintLabels(true);
        recSlider.addChangeListener(e -> {
            PlagueSimulation.RECOVERY_TIME = recSlider.getValue();
            recLabel.setText("Fatality/Recovery Time: " + PlagueSimulation.RECOVERY_TIME);
        });


        fatalToggle = new JToggleButton("Not Fatal");
        fatalToggle.setSelected(false); // Default is fatal
        fatalToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isFatal = !fatalToggle.isSelected(); // Selected means "Not Fatal"

                if (model instanceof PlagueSimulation) {
                    ((PlagueSimulation) model).setFatal(isFatal);
                }

                // Update button label based on the current state
                if (isFatal) {
                    fatalToggle.setText("Fatal");
                } else {
                    fatalToggle.setText("Not Fatal");
                }
            }
        });



        sliderBox.add(Box.createVerticalStrut(10));
        sliderBox.add(initLabel);
        sliderBox.add(initSlider);
        sliderBox.add(Box.createVerticalStrut(10));
        sliderBox.add(virLabel);
        sliderBox.add(virSlider);
        sliderBox.add(Box.createVerticalStrut(10));
        sliderBox.add(popLabel);
        sliderBox.add(popSlider);
        sliderBox.add(Box.createVerticalStrut(10));
        sliderBox.add(recLabel);
        sliderBox.add(recSlider);
        sliderBox.add(Box.createVerticalStrut(10));


        JPanel togglePanel = new JPanel();
        togglePanel.add(fatalToggle);
        sliderBox.add(togglePanel);
        sliderBox.add(Box.createVerticalStrut(10));


        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(threadPanel, BorderLayout.NORTH);
        controlPanel.add(sliderBox, BorderLayout.CENTER);
    }
}

 */