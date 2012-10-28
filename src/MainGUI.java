import java.awt.Font;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;
import java.util.concurrent.CountDownLatch;
import java.util.*;

public class MainGUI {
	private JFrame frame;
	private JPanel mainPanel, titlePanel, displayPanel, contentPanel;
	private CountDownLatch latch;
	private HashMap<JPanel, String> cardMap;

	/**
	* Constructor for MainGUI
	* @param latch Countdown latch to make sure the main thread waits properly. 
	*/
	public MainGUI(CountDownLatch latch, ArrayList<JPanel> cards) {
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		mainPanel = new JPanel();
		contentPanel = new JPanel();
		contentPanel.setLayout(new CardLayout());
		cardMap = new HashMap<JPanel, String>();

		// generate a UUID as the string identifer for each card, and add them to the card layout in the content panel
		// also keep a mapping to be able to access the uuid for each panel later
		for (int i = 0; i < cards.size(); i++) {
			JPanel card = cards.get(i);
			String identifier = (UUID.randomUUID()).toString();
			cardMap.put(card, identifier);
			contentPanel.add(card, identifier);
		}

		// display title, buttons, etc. then pack the frame and display to user
		this.latch = latch;
		this.setupTitle();
		mainPanel.add(titlePanel);
		frame.add(mainPanel, BorderLayout.NORTH);
		frame.add(contentPanel, BorderLayout.CENTER);
		this.packageAndDisplay();
	}

	/**
	* Displays a panel in the MainGUI. 
	* If there was a panel being displayed, it will be removed, and the new panel will be displayed in its place. 
	* @param panel The panel to display
	*/
	public void displayPanel(JPanel panel) {
		String identifier;
		if ((identifier = cardMap.get(panel)) == null) return;
		((CardLayout)contentPanel.getLayout()).show(contentPanel, identifier);
	}

	/* 
	* Sets up title panel. Convenience method.
	*/
	private void setupTitle() {
		titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(500, 50));
		JLabel titleLabel = new JLabel("Spacetrader");
		Font font = new Font("Helvetica", Font.PLAIN, 32);
		titleLabel.setFont(font);
		titlePanel.add(titleLabel);
	}

	/*
	* Method to call at the end of configuring the screen. Packs, set's default close, and displays it. 
	*/
	public void packageAndDisplay() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}