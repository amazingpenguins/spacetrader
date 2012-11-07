import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/*
 * Instantiating this class will create a JFrame that lets the user choose a name, set attributes, and choose a
 * difficulty. The errorLabel can be set to some string if the user doesn't input values correctly.
 */
public class InitView extends JPanel{
    private JTextField name;
    private JSpinner pilotS, traderS, fighterS, engineerS;
    private SpinnerNumberModel pilotSkill, traderSkill, fighterSkill, engineerSkill;
    private ButtonGroup difficulties;
    private JButton start;
    private JLabel errorLabel;
    private InitViewDelegate delegate;
    private GameController gc;

    //TODO move this.
    private final short MAXSTATVAL = 16;
    private int pointsLeft;
    public InitView(GameController gc) {
        this.gc = gc;

        // assign pointsLeft
        pointsLeft = MAXSTATVAL;
        
        // header
        JPanel headingPanel = new JPanel();
        headingPanel.setPreferredSize(new Dimension(500, 50));
        JLabel heading = new JLabel(
                "Choose a name, distribute 16 attribute points, and choose a difficulty");
        headingPanel.add(heading);

        // name
        JPanel namePanel = new JPanel();
        namePanel.setPreferredSize(new Dimension(500, 40));
        JLabel nameLabel = new JLabel("Character Name:  ");
        name = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(name);

        // pilot
        JPanel pilotPanel = new JPanel();
        pilotPanel.setPreferredSize(new Dimension(500, 30));
        JLabel pilotLabel = new JLabel("Pilot:           ");
        pilotSkill = new SpinnerNumberModel(0,0,MAXSTATVAL,1);
        pilotS = new JSpinner(pilotSkill);
        ((JSpinner.DefaultEditor)pilotS.getEditor()).getTextField().setColumns(2);
        pilotS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                pointsLeft = MAXSTATVAL - getSpentPoints();
                if (pointsLeft < 0)
                    pilotS.setValue(getPilot() - 1);
            }
        });
        pilotPanel.add(pilotLabel);
        pilotPanel.add(pilotS);
        
        // trader
        JPanel traderPanel = new JPanel();
        traderPanel.setPreferredSize(new Dimension(500, 30));
        JLabel traderLabel = new JLabel("Trader:       ");
        traderSkill = new SpinnerNumberModel(0,0,MAXSTATVAL,1);
        traderS = new JSpinner(traderSkill);
        ((JSpinner.DefaultEditor)traderS.getEditor()).getTextField().setColumns(2);
        traderS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                pointsLeft = MAXSTATVAL - getSpentPoints();
                if (pointsLeft < 0)
                    traderS.setValue(getTrader() - 1);
            }
        });
        traderPanel.add(traderLabel);
        traderPanel.add(traderS);
        
        // fighter
        JPanel fighterPanel = new JPanel();
        fighterPanel.setPreferredSize(new Dimension(500, 30));
        JLabel fighterLabel = new JLabel("Fighter:       ");
        fighterSkill = new SpinnerNumberModel(0,0,MAXSTATVAL,1);
        fighterS = new JSpinner(fighterSkill);
        ((JSpinner.DefaultEditor)fighterS.getEditor()).getTextField().setColumns(2);
        fighterS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                pointsLeft = MAXSTATVAL - getSpentPoints();
                if (pointsLeft < 0)
                    fighterS.setValue(getFighter() - 1);
            }
        });
        fighterPanel.add(fighterLabel);
        fighterPanel.add(fighterS);
        
        // engineer
        JPanel engineerPanel = new JPanel();
        engineerPanel.setPreferredSize(new Dimension(500, 40));
        JLabel engineerLabel = new JLabel("Engineer:   ");
        engineerSkill = new SpinnerNumberModel(0,0,MAXSTATVAL,1);
        engineerS = new JSpinner(engineerSkill);
        ((JSpinner.DefaultEditor)engineerS.getEditor()).getTextField().setColumns(2);
        engineerS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                pointsLeft = MAXSTATVAL - getSpentPoints();
                if (pointsLeft < 0)
                    engineerS.setValue(getEngineer() - 1);
            }
        });
        engineerPanel.add(engineerLabel);
        engineerPanel.add(engineerS);
        
        // difficulty
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setPreferredSize(new Dimension(500, 40));
        difficulties = new ButtonGroup();

        JRadioButton beginner = new JRadioButton("Beginner");
        beginner.setActionCommand("Beginner");
        JRadioButton easy = new JRadioButton("Easy");
        easy.setActionCommand("Easy");
        JRadioButton normal = new JRadioButton("Normal");
        normal.setActionCommand("Normal");
        JRadioButton hard = new JRadioButton("Hard");
        hard.setActionCommand("Hard");
        JRadioButton impossible = new JRadioButton("Impossible");
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
        JPanel startPanel = new JPanel();
        startPanel.setPreferredSize(new Dimension(500, 40));
        start = new JButton("Start");
        start.addActionListener(new StartButtonListener(this));
        startPanel.add(start);
        
        // error
        JPanel errorPanel = new JPanel();
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

    public Object getDelegate() {
        return delegate;
    }

    public void setDelegate(InitViewDelegate delegate) {
        this.delegate = delegate;
    }
    private short getSpentPoints() {
        return (short) (getPilot() + getTrader() + getFighter() + getEngineer());
    }

    /*
     * Getters for the attributes.
     * @return the integer associated with each player attribute
     */
    public short getPilot() {
        return pilotSkill.getNumber().shortValue();
    }
    
    public short getTrader() {
        return traderSkill.getNumber().shortValue();
    }
    
    public short getFighter() {
        return fighterSkill.getNumber().shortValue();
    }
    
    public short getEngineer() {
        return engineerSkill.getNumber().shortValue();
    }

    /*
     * Getter for the character name
     * @return the String associated with the player name
     */
    public String getName() {
        return name.getText();
    }
    
    /*
     * Getter for the difficulty
     * @return the String associated with the difficulties (Beginner, Easy, Normal, Hard, Impossible)
     */
    public String getDifficulty() {
        return difficulties.getSelection().getActionCommand();
    }
    
    /*
     * Getter for the start button
     * @return the JButton used to start the game
     */
    public JButton getStart() {
        return start;
    }
    
    /*
     * Setter for the error text
     * @param text The text to display
     */
    public void setError(String text) {
        errorLabel.setText(text);
    }
    
    /**
    *
    */
    private boolean verifyInput() {
        return ((this.name.getText().length() != 0) && this.pointsLeft == 0);
    }



    private class StartButtonListener implements ActionListener {
        private InitView view;

        public StartButtonListener(InitView view) {
            this.view = view;
        }

        public void actionPerformed(ActionEvent e) {
            if (view.verifyInput()) {
                delegate.doneConfiguring(view);
                gc.goToState(GameController.State.GAMEPANEL);
            } else {

                if (view.name.getText().length() == 0){
                    JOptionPane.showMessageDialog(view, "Please enter a player name.");

                }
                else if (view.pointsLeft > 0){
                    JOptionPane.showMessageDialog(view, "Please allocate 16 skill points.");
                }



            }
        }
    }


}
