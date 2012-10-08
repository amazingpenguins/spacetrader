import java.awt.Color;
import java.awt.Dimension;
<<<<<<< HEAD
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
=======
import java.awt.event.*;
>>>>>>> InitView uses spinners now, and Start has ActionListener
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
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
	private JSpinner pilotS, traderS, fighterS, engineerS;
	private ButtonGroup difficulties;
	private JRadioButton beginner, easy, normal, hard, impossible;
	private JButton start;
	private JLabel heading, nameLabel, pilotLabel, traderLabel, fighterLabel,
			engineerLabel, errorLabel;
<<<<<<< HEAD
<<<<<<< HEAD
	private InitViewDelegate delegate;
=======
	private boolean engaged;
=======
>>>>>>> i made a variable and then deleted it.
	private Player plr;
	private GameController gc;
>>>>>>> InitView uses spinners now, and Start has ActionListener

	public InitView(GameController gc) {
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
		name = new JTextField("Name", 20);
		namePanel.add(nameLabel);
		namePanel.add(name);

		// pilot
		pilotPanel = new JPanel();
		pilotPanel.setPreferredSize(new Dimension(500, 30));
		pilotLabel = new JLabel("Pilot:           ");
		pilotS = new JSpinner();
		pilotPanel.add(pilotLabel);
		pilotPanel.add(pilotS);
		
		// trader
		traderPanel = new JPanel();
		traderPanel.setPreferredSize(new Dimension(500, 30));
		traderLabel = new JLabel("Trader:       ");
		traderS = new JSpinner();
		traderPanel.add(traderLabel);
		traderPanel.add(traderS);
		
		// fighter
		fighterPanel = new JPanel();
		fighterPanel.setPreferredSize(new Dimension(500, 30));
		fighterLabel = new JLabel("Fighter:       ");
		fighterS = new JSpinner();
		fighterPanel.add(fighterLabel);
		fighterPanel.add(fighterS);
		
		// engineer
		engineerPanel = new JPanel();
		engineerPanel.setPreferredSize(new Dimension(500, 40));
		engineerLabel = new JLabel("Engineer:   ");
		engineerS = new JSpinner();
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
<<<<<<< HEAD
		start.addActionListener(new StartButtonListener(this));
=======
		start.addActionListener(new StartListener());
>>>>>>> InitView uses spinners now, and Start has ActionListener
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
		
		this.gc = gc;
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
		return Short.parseShort((String)pilotS.getValue().toString());
	}
	
	public short getTrader() {
		return Short.parseShort((String)traderS.getValue().toString());
	}
	
	public short getFighter() {
		return Short.parseShort((String)fighterS.getValue().toString());
	}
	
	public short getEngineer() {
		return Short.parseShort((String)engineerS.getValue().toString());
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
	
	public void setPlayer(Player p){
		gc.setPlayer(p);
	}
	
	private class StartListener implements ActionListener{
		public void actionPerformed(ActionEvent e){			
			plr = new Player(getName(), getPilot(), getTrader(), getFighter(), getEngineer(), false);
			setPlayer(plr);
		}
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
