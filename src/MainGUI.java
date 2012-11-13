import javax.swing.*;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
* Class to allow displaying of multiple screens. 
* All panels that are displayed in this MainGUI should take in a CountDownLatch and decremement it when it's actions are complete. 
* * @author ryree0
 * @version $Revision: 1.0 $

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
		frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(800, 500));
        frame.setPreferredSize(new Dimension(800, 500));
        contentLayout = new CardLayout();
        contentPanel = new JPanel(contentLayout);
        cardMap = new HashMap<JPanel, String>();

		// generate a UUID as the string identifer for each card, and add them to the card layout in the content panel
		// also keep a mapping to be able to access the uuid for each panel later
        for (JPanel card : panels) {
            String identifier = (UUID.randomUUID()).toString();
            cardMap.put(card, identifier);
            contentPanel.add(card, identifier);
        }

		// display title, buttons, etc. then pack the frame and display to user
		//this.setupTitle();
		//mainPanel.add(titlePanel);
		frame.add(contentPanel, BorderLayout.CENTER);
		this.packageAndDisplay();
	}

	/**
	* Displays a panel in the MainGUI. 
	* If there was a panel being displayed, it will be removed, and the new panel will be displayed in its place. 
	* @param panel The panel to display
	*/
	public void displayPanel(JPanel panel) {
		final String identifier;
		identifier = cardMap.get(panel);
		if (identifier == null) return;
		contentLayout.show(contentPanel, identifier);
	}

	/*
	* Method to call at the end of configuring the screen. Packs, set's default close, and displays it. 
	*/
	/**
	 * Method packageAndDisplay.
	 */
	protected void packageAndDisplay() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}