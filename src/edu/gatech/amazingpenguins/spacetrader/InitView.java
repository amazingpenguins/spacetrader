/**
 * Init View
 */
package edu.gatech.amazingpenguins.spacetrader; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/*
 * Instantiating this class will create a JFrame that 
 * lets the user choose a name, set attributes, and choose a
 * difficulty. The errorLabel can be set to some string if the
 * user doesn't input values correctly.
 */
/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class InitView extends JPanel {
    
    /**
     * Field serialVersionUID.
     * (value is 6606437076848844624)
     */
    private static final long serialVersionUID = 6606437076848844624L;
    
    /**
     * Field name.
     */
    private final JTextField name;
    
    /**
     * Field engineerS.
     */
    /**
     * Field fighterS.
     */
    /**
     * Field traderS.
     */
    /**
     * Field pilotS.
     */
    private final JSpinner pilotS, traderS, fighterS, engineerS;
    
    /**
     * Field engineerSkill.
     */
    /**
     * Field fighterSkill.
     */
    /**
     * Field traderSkill.
     */
    /**
     * Field pilotSkill.
     */
    private final SpinnerNumberModel pilotSkill, traderSkill, fighterSkill, engineerSkill;
    
    /**
     * Field difficulties.
     */
    private final ButtonGroup difficulties;
    
    /**
     * Field start.
     */
    private final JButton start;
    
    /**
     * Field errorLabel.
     */
    private final JLabel errorLabel;
    
    /**
     * Field gc.
     */
    private final GameController gc;
    
    /**
     * Field delegate.
     */
    private InitViewDelegate delegate;

    //TODO move this.
    /**
     * Field MAXSTATVAL.
     */
    private static final short MAXSTATVAL = 16;
    
    /**
     * Field pointsLeft.
     */
    private int pointsLeft;
    
    private static int TEXT_FIELD_WIDTH = 20;
    private static int PANEL_WIDTH = 500;
    private static int HEADING_PANEL_HEIGHT = 50;
    private static int NAME_PANEL_HEIGHT = 40;
    private static int FIGHTER_PANEL_HEIGHT = 30;
    private static int COLS = 2;
    
    
    /**
     * Constructor for InitView.
     * @param gc GameController
     */
    public InitView(GameController gc) {
        this.gc = gc;
        
        /* JSpinner Models */
        pilotSkill    = new SpinnerNumberModel(0, 0, MAXSTATVAL, 1);
        traderSkill   = new SpinnerNumberModel(0, 0, MAXSTATVAL, 1);
        fighterSkill  = new SpinnerNumberModel(0, 0, MAXSTATVAL, 1);
        engineerSkill = new SpinnerNumberModel(0, 0, MAXSTATVAL, 1);
        
        /* JSpinners */
        pilotS    = new JSpinner(pilotSkill);
        traderS   = new JSpinner(traderSkill);
        fighterS  = new JSpinner(fighterSkill);
        engineerS = new JSpinner(engineerSkill);
        
        /* Text Fields */
        name = new JTextField(TEXT_FIELD_WIDTH);
        
        /* Buttons */
        start = new JButton("Start");
        difficulties = new ButtonGroup();
        
        /* Error Label */
        errorLabel = new JLabel("");
        
        /* Make this panel */
        setupPanel();
    }
    
    /**
     * Setup the InitView Panel
     */
    private void setupPanel() {

        /* assign pointsLeft */
        pointsLeft = MAXSTATVAL;
        
        /* Header */
        final JPanel headingPanel = new JPanel();
        headingPanel.setPreferredSize(new Dimension(PANEL_WIDTH, HEADING_PANEL_HEIGHT));
        final JLabel heading = new JLabel(
                "Choose a name, distribute 16 attribute points, and choose a difficulty");
        headingPanel.add(heading);

        /* Name */
        final JPanel namePanel = new JPanel();
        namePanel.setPreferredSize(new Dimension(PANEL_WIDTH, NAME_PANEL_HEIGHT));
        final JLabel nameLabel = new JLabel("Character Name:  ");
        namePanel.add(nameLabel);
        namePanel.add(name);

        /* Pilot Skill */
        final JPanel pilotPanel = new JPanel();
        pilotPanel.setPreferredSize(new Dimension(PANEL_WIDTH, NAME_PANEL_HEIGHT));
        final JLabel pilotLabel = new JLabel("Pilot:           ");
        ((JSpinner.DefaultEditor) pilotS.getEditor()).getTextField().setColumns(COLS);
        pilotS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                pointsLeft = MAXSTATVAL - getSpentPoints();
                if (pointsLeft < 0) {
                    pilotS.setValue(getPilot() - 1);
                }
            }
        });
        pilotPanel.add(pilotLabel);
        pilotPanel.add(pilotS);
        
        /* Trader Skill */
        final JPanel traderPanel = new JPanel();
        traderPanel.setPreferredSize(new Dimension(500, 30));
        final JLabel traderLabel = new JLabel("Trader:       ");
        ((JSpinner.DefaultEditor) traderS.getEditor()).getTextField().setColumns(COLS);
        traderS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                pointsLeft = MAXSTATVAL - getSpentPoints();
                if (pointsLeft < 0) {
                    traderS.setValue(getTrader() - 1);
                }
            }
        });
        traderPanel.add(traderLabel);
        traderPanel.add(traderS);
        
        /* Fighter Skill */
        final JPanel fighterPanel = new JPanel();
        fighterPanel.setPreferredSize(new Dimension(PANEL_WIDTH, FIGHTER_PANEL_HEIGHT));
        final JLabel fighterLabel = new JLabel("Fighter:       ");
        ((JSpinner.DefaultEditor) fighterS.getEditor()).getTextField().setColumns(COLS);
        fighterS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                pointsLeft = MAXSTATVAL - getSpentPoints();
                if (pointsLeft < 0) {
                    fighterS.setValue(getFighter() - 1);
                }
            }
        });
        fighterPanel.add(fighterLabel);
        fighterPanel.add(fighterS);
        
        /* Engineer Skill */
        final JPanel engineerPanel = new JPanel();
        engineerPanel.setPreferredSize(new Dimension(PANEL_WIDTH, NAME_PANEL_HEIGHT));
        final JLabel engineerLabel = new JLabel("Engineer:   ");
        ((JSpinner.DefaultEditor) engineerS.getEditor()).getTextField().setColumns(COLS);
        engineerS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                pointsLeft = MAXSTATVAL - getSpentPoints();
                if (pointsLeft < 0) {
                    engineerS.setValue(getEngineer() - 1);
                }
            }
        });
        engineerPanel.add(engineerLabel);
        engineerPanel.add(engineerS);
        
        /* Game Difficulty */
        final JPanel difficultyPanel = new JPanel();
        // same dimensions has name panel, so didn't want to be redundant
        difficultyPanel.setPreferredSize(new Dimension(PANEL_WIDTH, NAME_PANEL_HEIGHT));

        final JRadioButton beginner = new JRadioButton("Beginner");
        beginner.setActionCommand("Beginner");
        final JRadioButton easy = new JRadioButton("Easy");
        easy.setActionCommand("Easy");
        final JRadioButton normal = new JRadioButton("Normal");
        normal.setActionCommand("Normal");
        final JRadioButton hard = new JRadioButton("Hard");
        hard.setActionCommand("Hard");
        final JRadioButton impossible = new JRadioButton("Impossible");
        impossible.setActionCommand("Impossible");

        difficulties.add(beginner);
        difficulties.add(easy);
        difficulties.add(normal);
        difficulties.add(hard);
        difficulties.add(impossible);

        difficulties.setSelected(normal.getModel(), true);
        
        difficultyPanel.add(beginner);
        difficultyPanel.add(easy);
        difficultyPanel.add(normal);
        difficultyPanel.add(hard);
        difficultyPanel.add(impossible);

        /* Start Button */
        final JPanel startPanel = new JPanel();
        // same dimensions has name panel, so didn't want to be redundant
        startPanel.setPreferredSize(new Dimension(PANEL_WIDTH, NAME_PANEL_HEIGHT));
        start.addActionListener(new StartButtonListener(this));
        startPanel.add(start);
        
        /* Error */
        final JPanel errorPanel = new JPanel();
        errorPanel.setPreferredSize(new Dimension(500, 30));
        errorLabel.setForeground(Color.red);
        errorPanel.add(errorLabel);
        
        /* Create the frame 
         * Add the components 
         * Display the frame */
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(headingPanel);
        this.add(namePanel);
        this.add(pilotPanel);
        this.add(traderPanel);
        this.add(fighterPanel);
        this.add(engineerPanel);
        this.add(difficultyPanel);
        this.add(startPanel);
        this.add(errorPanel);
    }

    /**
     * Method setDelegate.
     * @param delegate InitViewDelegate
     */
    public void setDelegate(InitViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    /**
     * Method getSpentPoints.
     * @return short */
    private short getSpentPoints() {
        return (short) (getPilot() + getTrader() + getFighter() + getEngineer());
    }

    /**
     * Getters for the attributes.
     * @return the integer associated with each player attribute
     */
    public short getPilot() {
        return pilotSkill.getNumber().shortValue();
    }
    
    /**
     * Method getTrader.
    
     * @return short */
    public short getTrader() {
        return traderSkill.getNumber().shortValue();
    }
    
    /**
     * Method getFighter.
    
     * @return short */
    public short getFighter() {
        return fighterSkill.getNumber().shortValue();
    }
    
    /**
     * Method getEngineer.
    
     * @return short */
    public short getEngineer() {
        return engineerSkill.getNumber().shortValue();
    }

    /**
     * Getter for the character name
     * @return the String associated with the player name
     */
    @Override
    public String getName() {
        return name.getText();
    }
    
    /**
     * Getter for the difficulty
     * @return the String associated with the difficulties
     *      (Beginner, Easy, Normal, Hard, Impossible)
     */
    public String getDifficulty() {
        return difficulties.getSelection().getActionCommand();
    }
    
    /**
    *
   
     * @return boolean */
    private boolean canVerifyInput() {
        return ((this.name.getText().length() != 0) && this.pointsLeft == 0);
    }

    /**
     * @author AmazingPenguins
     * @version 0.01
     */
    private class StartButtonListener implements ActionListener {
        /**
         * Field view.
         */
        private final InitView view;

        /**
         * Constructor for StartButtonListener.
         * @param view InitView
         */
        private StartButtonListener(InitView view) {
            this.view = view;
        }

        /**
         * Method actionPerformed.
         * @param e ActionEvent
        
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent) */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.canVerifyInput()) {
                delegate.doneConfiguring(view);
                gc.goToState(GameController.State.GAMEPANEL);
            } else {
                if (view.name.getText().length() == 0) {
                    JOptionPane.showMessageDialog(view,
                            "Please enter a player name.");

                } else if (view.pointsLeft > 0) {
                    JOptionPane.showMessageDialog(view,
                            "Please allocate 16 skill points.");
                }
            }
        }
        
        /**
         * toString
         * @return String
         */
        @Override
        public String toString() {
            return "Save Button Listener";
        }
    }
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "Init View";
    }
}
