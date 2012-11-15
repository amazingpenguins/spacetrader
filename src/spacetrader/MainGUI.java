package spacetrader; /**
 * Main GUI
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
* Class to allow displaying of multiple screens. 
* All panels that are displayed in this MainGUI should take in a 
* CountDownLatch and decrement it when it's actions are complete. 
* @author AmazingPenguins
* @version 0.01
*/

public class MainGUI {
	/**
	 * Field frame.
	 */
	private final JFrame frame;

	/**
	 * Field contentPanel.
	 */
	private final JPanel contentPanel;

    /**
     * Field contentLayout.
     */
    private final CardLayout contentLayout;

	/**
	 * Field cardMap.
	 */
	private final Map<JPanel,String> cardMap;

	/**
	* Constructor for MainGUI
 * @param panels List<JPanel>
		*/
	public MainGUI(List<JPanel> panels) {
        frame = new JFrame();
        contentLayout = new CardLayout();
        contentPanel = new JPanel(contentLayout);
        cardMap = new HashMap<JPanel, String>();
        
        setupPanel(panels);
	}

	/**
	 * Setup the Panel
	 * @param panels The panels to add
	 */
	private void setupPanel(List<JPanel> panels) {
	        frame.setLayout(new BorderLayout());
	        frame.setMinimumSize(new Dimension(800, 500));
	        frame.setPreferredSize(new Dimension(800, 500));

	        /* Generate a UUID as the string identifier for each card
	        *  Add them to the card layout in the content panel
	        *  Keep a mapping to be able to access the UUID for each panel later
	        */
	        for (JPanel card : panels) {
	            String identifier = (UUID.randomUUID()).toString();
	            cardMap.put(card, identifier);
	            contentPanel.add(card, identifier);
	        }

	        // display title, buttons, etc. then pack the frame and display to user
	        //this.setupTitle();
	        //mainPanel.add(titlePanel);
	        frame.add(contentPanel, BorderLayout.CENTER);
	        
	        packageAndDisplay();
	}
	
	/**
	* Displays a panel in the MainGUI. 
	* If there was a panel being displayed, it will be removed,
	* and the new panel will be displayed in its place. 
	* @param panel The panel to display
	*/
	public void displayPanel(JPanel panel) {
		final String identifier;
		identifier = cardMap.get(panel);
		if (identifier == null) return;
		contentLayout.show(contentPanel, identifier);
	}

	/*
	* Method to call at the end of configuring the screen.
	* Packs, set's default close, and displays it. 
	*/
	/**
	 * Method packageAndDisplay.
	 */
	protected void packageAndDisplay() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

    /**
     * toString
     * @return String
     */
	@Override
	public String toString() {
	    return "Main GUI";
	}
}