import java.awt.Font;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;
import java.util.concurrent.CountDownLatch;

public class MainGUI {
	private JFrame frame;
	private JPanel mainPanel, titlePanel, displayPanel, startPanel;
	private CountDownLatch latch;

	/**
	* Constructor for MainGUI
	* @param latch Countdown latch to make sure the main thread waits properly. 
	* @return MainGUI Instance
	*/
	public MainGUI(CountDownLatch latch) {
		frame = new JFrame();
        frame.setLayout(new BorderLayout());
		mainPanel = new JPanel();
        startPanel = new StartGamePanel();
		// display title, buttons, etc. then pack the frame and display to user
		
		this.latch = latch;
		this.setupTitle();
		mainPanel.add(titlePanel);
		frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(startPanel, BorderLayout.CENTER);
		this.packageAndDisplay();
		System.out.println("Created the mainGUI and should have displayed it..."); 
        
	}

	/**
	* Displays a panel in the MainGUI. 
	* If there was a panel being displayed, it will be removed, and the new panel will be displayed in its place. 
	* @param panel The panel to display
	*/
	public void displayPanel(JPanel panel) {
		// remove old panel, replace with new panel, hold on to a pointer to the new panel 
		if (this.displayPanel != null)
			mainPanel.remove(this.displayPanel);
		this.displayPanel = panel;
		mainPanel.add(displayPanel);
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
	private void packageAndDisplay() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}

}