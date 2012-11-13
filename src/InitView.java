import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/*
 * Instantiating this class will create a JFrame that lets the user choose a name, set attributes, and choose a
 * difficulty. The errorLabel can be set to some string if the user doesn't input values correctly.
 */
/**
 * @author ryree0
 * @version $Revision: 1.0 $
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
    
    /**
     * Constructor for InitView.
     * @param gc GameController
     */
    public InitView(GameController gc) {
        this.gc = gc;

        // assign pointsLeft
        pointsLeft = MAXSTATVAL;
        
        // header
        final JPanel headingPanel = new JPanel();
        headingPanel.setPreferredSize(new Dimension(500, 50));
        final JLabel heading = new JLabel(
                "Choose a name, distribute 16 attribute points, and choose a difficulty");
        headingPanel.add(heading);

        // name
        final JPanel namePanel = new JPanel();
        namePanel.setPreferredSize(new Dimension(500, 40));
        final JLabel nameLabel = new JLabel("Character Name:  ");
        name = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(name);

        // pilot
        final JPanel pilotPanel = new JPanel();
        pilotPanel.setPreferredSize(new Dimension(500, 30));
        final JLabel pilotLabel = new JLabel("Pilot:           ");
        pilotSkill = new SpinnerNumberModel(0, 0, MAXSTATVAL, 1);
        pilotS = new JSpinner(pilotSkill);
        ((JSpinner.DefaultEditor) pilotS.getEditor()).getTextField().setColumns(2);
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
        
        // trader
        final JPanel traderPanel = new JPanel();
        traderPanel.setPreferredSize(new Dimension(500, 30));
        final JLabel traderLabel = new JLabel("Trader:       ");
        traderSkill = new SpinnerNumberModel(0, 0, MAXSTATVAL, 1);
        traderS = new JSpinner(traderSkill);
        ((JSpinner.DefaultEditor) traderS.getEditor()).getTextField().setColumns(2);
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
        
        // fighter
        final JPanel fighterPanel = new JPanel();
        fighterPanel.setPreferredSize(new Dimension(500, 30));
        final JLabel fighterLabel = new JLabel("Fighter:       ");
        fighterSkill = new SpinnerNumberModel(0, 0, MAXSTATVAL, 1);
        fighterS = new JSpinner(fighterSkill);
        ((JSpinner.DefaultEditor) fighterS.getEditor()).getTextField().setColumns(2);
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
        
        // engineer
        final JPanel engineerPanel = new JPanel();
        engineerPanel.setPreferredSize(new Dimension(500, 40));
        final JLabel engineerLabel = new JLabel("Engineer:   ");
        engineerSkill = new SpinnerNumberModel(0, 0, MAXSTATVAL, 1);
        engineerS = new JSpinner(engineerSkill);
        ((JSpinner.DefaultEditor) engineerS.getEditor()).getTextField().setColumns(2);
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
        
        // difficulty
        final JPanel difficultyPanel = new JPanel();
        difficultyPanel.setPreferredSize(new Dimension(500, 40));
        difficulties = new ButtonGroup();

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

        // start
        final JPanel startPanel = new JPanel();
        startPanel.setPreferredSize(new Dimension(500, 40));
        start = new JButton("Start");
        start.addActionListener(new StartButtonListener(this));
        startPanel.add(start);
        
        // error
        final JPanel errorPanel = new JPanel();
        errorPanel.setPreferredSize(new Dimension(500, 30));
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorPanel.add(errorLabel);
        
        // create the frame, add the components, display the frame
        this.setLayout(new BoxLayout(this,
                BoxLayout.Y_AXIS));
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

    /*
     * Getters for the attributes.
     * @return the integer associated with each player attribute
     */
    /**
     * Method getPilot.
    
     * @return short */
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

    /*
     * Getter for the character name
     * @return the String associated with the player name
     */
    /**
     * Method getName.
    
     * @return String */
    public String getName() {
        return name.getText();
    }
    
    /*
     * Getter for the difficulty
     * @return the String associated with the difficulties (Beginner, Easy, Normal, Hard, Impossible)
     */
    /**
     * Method getDifficulty.
    
     * @return String */
    public String getDifficulty() {
        return difficulties.getSelection().getActionCommand();
    }
    
    /**
    *
   
     * @return boolean */
    private boolean verifyInput() {
        return ((this.name.getText().length() != 0) && this.pointsLeft == 0);
    }

    /**
     * @author ryree0
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
        public void actionPerformed(ActionEvent e) {
            if (view.verifyInput()) {
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
    }
}
