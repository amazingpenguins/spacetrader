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
import javax.swing.JTextField;
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
	private JTextField name, pilot, trader, fighter, engineer;
	private ButtonGroup difficulties;
	private JRadioButton beginner, easy, normal, hard, impossible;
	private JButton start;
	private JLabel heading, nameLabel, pilotLabel, traderLabel, fighterLabel,
			engineerLabel, errorLabel;
	private InitViewDelegate delegate;

	public InitView() {
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
		pilot = new JTextField(2);
		pilot.setDocument(new JTextFieldLimit(2));
		pilotPanel.add(pilotLabel);
		pilotPanel.add(pilot);
		
		// trader
		traderPanel = new JPanel();
		traderPanel.setPreferredSize(new Dimension(500, 30));
		traderLabel = new JLabel("Trader:       ");
		trader = new JTextField(2);
		trader.setDocument(new JTextFieldLimit(2));
		traderPanel.add(traderLabel);
		traderPanel.add(trader);
		
		// fighter
		fighterPanel = new JPanel();
		fighterPanel.setPreferredSize(new Dimension(500, 30));
		fighterLabel = new JLabel("Fighter:       ");
		fighter = new JTextField(2);
		fighter.setDocument(new JTextFieldLimit(2));
		fighterPanel.add(fighterLabel);
		fighterPanel.add(fighter);
		
		// engineer
		engineerPanel = new JPanel();
		engineerPanel.setPreferredSize(new Dimension(500, 40));
		engineerLabel = new JLabel("Engineer:   ");
		engineer = new JTextField(2);
		engineer.setDocument(new JTextFieldLimit(2));
		engineerPanel.add(engineerLabel);
		engineerPanel.add(engineer);
		
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


	/*
	 * Getters for the attributes.
	 * @return the integer associated with each player attribute
	 */
	public short getPilot() {
		return Short.parseShort(pilot.getText());
	}
	
	public short getTrader() {
		return Short.parseShort(trader.getText());
	}
	
	public short getFighter() {
		return Short.parseShort(fighter.getText());
	}
	
	public short getEngineer() {
		return Short.parseShort(engineer.getText());
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
	
	//Private subclass that limits text input for the attributes
	//pulled from http://stackoverflow.com/questions/10136794/limiting-the-number-of-characters-in-a-jtextfield
	private class JTextFieldLimit extends PlainDocument {
		private int limit;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		JTextFieldLimit(int limit, boolean upper) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr)
				throws BadLocationException {
			if (str == null)
				return;

			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
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
