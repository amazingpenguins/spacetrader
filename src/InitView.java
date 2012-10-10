import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


/*
 * Instantiating this class will create a JFrame that lets the user choose a name, set attributes, and choose a
 * difficulty. The errorLabel can be set to some string if the user doesn't input values correctly.
 */
public class InitView {

	private JFrame initFrame;
	private JPanel headingPanel, namePanel, pilotPanel, traderPanel,
			fighterPanel, engineerPanel, difficultyPanel, startPanel, errorPanel;
	private JTextField name;
	private JSpinner pilotS, traderS, fighterS, engineerS;
	private SpinnerNumberModel pilotSkill, traderSkill, fighterSkill, engineerSkill;
	private ButtonGroup difficulties;
	private JRadioButton beginner, easy, normal, hard, impossible;
	private JButton start;
	private JLabel heading, nameLabel, pilotLabel, traderLabel, fighterLabel,
			engineerLabel, errorLabel;
	private InitViewDelegate delegate;

	//TODO move this.
	private final short MAXSTATVAL = 16;
	private int pointsLeft;
	public InitView() {
		// assign pointsLeft
		pointsLeft = MAXSTATVAL;
		
		// header
		headingPanel = new JPanel();
		headingPanel.setPreferredSize(new Dimension(500, 50));
		heading = new JLabel(
				"Choose a name, distribute 16 attribute points, and choose a difficulty");
		headingPanel.add(heading);

		// name
		namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(500, 40));
		nameLabel = new JLabel("Character Name:  ");
		name = new JTextField(20);
		namePanel.add(nameLabel);
		namePanel.add(name);

		// pilot
		pilotPanel = new JPanel();
		pilotPanel.setPreferredSize(new Dimension(500, 30));
		pilotLabel = new JLabel("Pilot:           ");
		pilotSkill = new SpinnerNumberModel(0,0,MAXSTATVAL,1);
		pilotS = new JSpinner(pilotSkill);
		((JSpinner.DefaultEditor)pilotS.getEditor()).getTextField().setColumns(2);
	    pilotS.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	pointsLeft = MAXSTATVAL - getSpentPoints();
	            if (pointsLeft < 0) {
	            	pilotS.setValue(getPilot() - 1);
	            	pointsLeft = MAXSTATVAL - getSpentPoints();
	            }
	        }
	    });
		pilotPanel.add(pilotLabel);
		pilotPanel.add(pilotS);
		
		// trader
		traderPanel = new JPanel();
		traderPanel.setPreferredSize(new Dimension(500, 30));
		traderLabel = new JLabel("Trader:       ");
		traderSkill = new SpinnerNumberModel(0,0,MAXSTATVAL,1);
		traderS = new JSpinner(traderSkill);
		((JSpinner.DefaultEditor)traderS.getEditor()).getTextField().setColumns(2);
	    traderS.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	pointsLeft = MAXSTATVAL - getSpentPoints();
	            if (pointsLeft < 0) {
	            	traderS.setValue(getTrader() - 1);
	            	pointsLeft = MAXSTATVAL - getSpentPoints();
	            }
	        }
	    });
		traderPanel.add(traderLabel);
		traderPanel.add(traderS);
		
		// fighter
		fighterPanel = new JPanel();
		fighterPanel.setPreferredSize(new Dimension(500, 30));
		fighterLabel = new JLabel("Fighter:       ");
		fighterSkill = new SpinnerNumberModel(0,0,MAXSTATVAL,1);
		fighterS = new JSpinner(fighterSkill);
		((JSpinner.DefaultEditor)fighterS.getEditor()).getTextField().setColumns(2);
	    fighterS.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	pointsLeft = MAXSTATVAL - getSpentPoints();
	            if (pointsLeft < 0) {
	            	fighterS.setValue(getFighter() - 1);
	            	pointsLeft = MAXSTATVAL - getSpentPoints();
	            }
	        }
	    });
		fighterPanel.add(fighterLabel);
		fighterPanel.add(fighterS);
		
		// engineer
		engineerPanel = new JPanel();
		engineerPanel.setPreferredSize(new Dimension(500, 40));
		engineerLabel = new JLabel("Engineer:   ");
		engineerSkill = new SpinnerNumberModel(0,0,MAXSTATVAL,1);
		engineerS = new JSpinner(engineerSkill);
		((JSpinner.DefaultEditor)engineerS.getEditor()).getTextField().setColumns(2);
	    engineerS.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	pointsLeft = MAXSTATVAL - getSpentPoints();
	            if (pointsLeft < 0) {
	            	engineerS.setValue(getEngineer() - 1);
	            	pointsLeft = MAXSTATVAL - getSpentPoints();
	            }
	        }
	    });
		engineerPanel.add(engineerLabel);
		engineerPanel.add(engineerS);
		
		// difficulty
		difficultyPanel = new JPanel();
		difficultyPanel.setPreferredSize(new Dimension(500, 40));
		difficulties = new ButtonGroup();

		beginner = new JRadioButton("Beginner");
		beginner.setActionCommand("Beginner");
		easy = new JRadioButton("Easy");
		easy.setActionCommand("Easy");
		normal = new JRadioButton("Normal");
		normal.setActionCommand("Normal");
		hard = new JRadioButton("Hard");
		hard.setActionCommand("Hard");
		impossible = new JRadioButton("Impossible");
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
		startPanel = new JPanel();
		startPanel.setPreferredSize(new Dimension(500, 40));
		start = new JButton("Start");
		start.addActionListener(new StartButtonListener(this));
		startPanel.add(start);
		
		// error
		errorPanel = new JPanel();
		errorPanel.setPreferredSize(new Dimension(500, 30));
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.red);
		errorPanel.add(errorLabel);
		
		// create the frame, add the components, display the frame
		initFrame = new JFrame("Set Game and Player Properties");
		initFrame.setLayout(new BoxLayout(initFrame.getContentPane(),
				BoxLayout.Y_AXIS));
		initFrame.add(headingPanel);
		initFrame.add(namePanel);
		initFrame.add(pilotPanel);
		initFrame.add(traderPanel);
		initFrame.add(fighterPanel);
		initFrame.add(engineerPanel);
		initFrame.add(difficultyPanel);
		initFrame.add(startPanel);
		initFrame.add(errorPanel);
		initFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initFrame.pack();
		initFrame.setVisible(true);
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
	
	public void exit() {
		initFrame.setVisible(false);
		initFrame.dispose();
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
	public void setError(String text){
		errorLabel.setText(text);
	}
	
	private class StartButtonListener implements ActionListener {
		private InitView view;

		public StartButtonListener(InitView view) {
			this.view = view;
		}

		public void actionPerformed(ActionEvent e) {
			delegate.doneConfiguring(view);
		}
	}


}
