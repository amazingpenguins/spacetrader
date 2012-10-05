import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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
	private JRadioButton easy, normal, hard;
	private JButton start;
	private JLabel heading, nameLabel, pilotLabel, traderLabel, fighterLabel,
			engineerLabel, errorLabel;

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
		easy = new JRadioButton("Easy");
		normal = new JRadioButton("Normal");
		hard = new JRadioButton("Hard");
		difficulties.add(easy);
		difficulties.add(normal);
		difficulties.add(hard);
		difficultyPanel.add(easy);
		difficultyPanel.add(normal);
		difficultyPanel.add(hard);
		
		// start
		startPanel = new JPanel();
		startPanel.setPreferredSize(new Dimension(500, 40));
		start = new JButton("Start");
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

	
	/*
	 * Getters for the attributes.
	 * @return the JTextField associated with each player attribute
	 */
	public JTextField getPilot() {
		return pilot;
	}
	
	public JTextField getTrader() {
		return trader;
	}
	
	public JTextField getFighter() {
		return fighter;
	}
	
	public JTextField getEngineer() {
		return engineer;
	}
	
	/*
	 * Getter for the character name
	 * @return the JTextField associated with the player name
	 */
	public JTextField getName() {
		return name;
	}
	
	/*
	 * Getter for the difficulty
	 * @return the ButtonGroup associated with the difficulties
	 */
	public ButtonGroup getDifficulty() {
		return difficulties;
	}
	
	/*
	 * Getter for the start button
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

	public static void main(String args[]) {
		InitView blah = new InitView();
	}

}
