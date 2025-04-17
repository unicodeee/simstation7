package gameOfChicken;


import mvc.AppPanel;
import mvc.Command;
import mvc.Model;
import mvc.Utilities;
import simstation.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

class Rebel extends MobileAgent {
    int score = 0;
    boolean choose() {
        return Utilities.rng.nextInt(100) < Tournament.swerveTendency;
    }
    void playOnce(Rebel opponent) {
        Boolean iSwerved = choose();
        Boolean opponentSwerved = opponent.choose();
        if (iSwerved) {
            if (opponentSwerved) {
                // we both get 0
            } else {
                this.score += -1;
                opponent.score += 1;
            }
        } else {
            if (opponentSwerved) {
                opponent.score += -1;
                this.score += 1;
            } else {
                this.score += -1000;
                opponent.score += -1000;
            }

        }
    }

    public void update() {
        Agent opponent = world.getNeighbor(this, 100);
        if (opponent != null) {
            playOnce((Rebel)opponent);
        }
        move(5);
    }
}

class TournamentFactory extends WorldFactory {
    public World makeModel() {
        return new Tournament();
    }
    public String getTitle() { return "Chicken Tournamnt"; }
    public String[] getEditCommands() {
        return new String[] { "Start", "Pause", "Resume", "Stop", "Stats", "Population", "SwerveTendency"};
    }
    public Command makeEditCommand(Model model, String type, Object source) {
        Command cmmd = super.makeEditCommand(model, type, source);
        if (cmmd == null) {
            if (type.equals("Population")) {
                cmmd = new SetPopulation(model);
                if (source instanceof JSlider) {
                    ((SetPopulation)cmmd).value = ((JSlider)source).getValue();
                }
            } else if (type.equals("SwerveTendency")) {
                cmmd = new SetSwerveTendency(model);
                if (source instanceof JSlider) {
                    // ((SetPopulation)cmmd).value = ((JSlider)source).getValue();
                }
            }
        }
        return cmmd;
    }
}

class TournamentPanel extends WorldPanel implements ChangeListener {

    JPanel sliderPanel = new JPanel();
    JSlider slider1, slider2;

    public TournamentPanel(TournamentFactory factory) {
        super(factory);

        sliderPanel.setLayout(new GridLayout(6, 1));
        sliderPanel.setOpaque(false);

        slider1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
        //slider1.setMinorTickSpacing(1);
        slider1.setMajorTickSpacing(3);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        slider1.setLabelTable(slider1.createStandardLabels(10));


        slider2 = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider1.setMinorTickSpacing(1);
        slider2.setMajorTickSpacing(3);
        slider2.setPaintTicks(true);
        slider2.setPaintLabels(true);
        slider2.setLabelTable(slider2.createStandardLabels(10));

        slider1.addChangeListener(this);
        slider2.addChangeListener(this);
       /*
        slider1.addChangeListener(e -> {
            Tournament.numRebels = slider1.getValue();
        });

        slider2.addChangeListener(e -> {
            Tournament.swerveTendency = slider2.getValue();
        });
*/

        JPanel pp = new JPanel();
        pp.setLayout(new BorderLayout());
        pp.setOpaque(false);

        JPanel ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(new JLabel("Population:"));
        pp.add(ppp, BorderLayout.NORTH);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(slider1);
        pp.add(ppp, BorderLayout.CENTER);

        sliderPanel.add(pp);

        pp = new JPanel();
        pp.setLayout(new BorderLayout());
        pp.setOpaque(false);
        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(new JLabel("Swerve Tendency"));
        pp.add(ppp, BorderLayout.NORTH);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(slider2);
        pp.add(ppp, BorderLayout.CENTER);
        sliderPanel.add(pp);
        controlPanel.add(sliderPanel, BorderLayout.CENTER);
    }

    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == slider1) {
            Tournament.numRebels = slider1.getValue();
        }
        if (e.getSource() == slider2) {
            ((Tournament)model).setSwerveTendency(slider2.getValue());
        }
        model.changed();
    }

    public void update() {
        slider1.setValue(((Tournament)model).numRebels);
        slider2.setValue(((Tournament)model).swerveTendency);
        repaint();
    }
}

class SetPopulation extends Command {
    Integer value = null;

    public SetPopulation(Model m) {
        super(m);
    }

    public void execute() {
        if (value == null) {
            String response = Utilities.ask("population size = ?");
            value = Integer.valueOf(response);
        }
        Tournament.numRebels = value;
    }
}

class SetSwerveTendency extends Command {
    Integer value = null;

    public SetSwerveTendency(Model m) {
        super(m);
    }

    public void execute(){
        if (value == null) {
            String response = Utilities.ask("swerve tendency = ?");
            value = Integer.valueOf(response);
        }
        ((Tournament)model).setSwerveTendency(value);
    }
}

public class Tournament extends World {
    static int numRebels = 50;
    static int swerveTendency = 50;

    public void populate() {
        for(int i = 0; i < numRebels; i++) {
            addAgent(new Rebel());
        }
    }

    public void setSwerveTendency(Integer value) {
        swerveTendency = value;
        changed();
    }

    private double avgScore() {
        double totalScore = 0;
        for(Agent a: getAgents()) {
            totalScore += ((Rebel)a).score;
        }
        return totalScore / numRebels;
    }

    public String[] getStats() {
        String[] stats = new String[3];
        stats[0] = "#agents = " + this.getAgents().size();
//        stats[1] = "#clock = " + clock;
        stats[2] = "avg score = " + avgScore() ;
        return stats;
    }

    public static void main(String[] args) {
        AppPanel app = new TournamentPanel(new TournamentFactory());
        app.display();
    }
}

