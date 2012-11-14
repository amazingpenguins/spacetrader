/**
 * Planet Button
 */
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class PlanetButton extends JButton {
    
    /**
     * Field serialVersionUID.
     * (value is -1473724251555760223)
     */
    private static final long serialVersionUID = -1473724251555760223L;

    /**
     * Field planet.
     */
    private final Planet planet;

	/**
	 * Field size.
	 */
	private final Dimension size;

	/**
	 * Field up.
	 */
	private final UniversePanel up;

	/**
	 * Field isHere.
	 */
	private boolean isHere;

    /**
     * Field playerImage.
     */
    private BufferedImage playerImage;

    /**
     * Constructor for PlanetButton.
     * @param p Planet
     * @param size Dimension
     * @param up UniversePanel
     */
    public PlanetButton(Planet p, Dimension size, UniversePanel up) {
		this.up = up;
		this.size = size;
		this.planet = p;
		setupPanel();
	}

    /**
     * Setup this Panel
     */
    private void setupPanel() {
        addActionListener(new PlanetButtonListener());
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
        setFocusable(false);
        setOpaque(false);
        setPreferredSize(size);

        try {
            playerImage = ImageIO.read(new File("images/SpaceShip.png"));
        } catch(IOException ioe) {
            System.err.println("Error locating images/SpaceShip.png");
            playerImage = null;
            ioe.printStackTrace();
        }
        
        BufferedImage myImage;
        try {
            myImage = ImageIO.read(new File("images/planets/" + 
                                                planet.toString() + ".png"));
        } catch(IOException ioe) {
            ioe.printStackTrace();
            System.err.println("Error locating image: images/planets/" + 
                    planet.toString() + ".png");
            myImage = null;
        }
        setIcon(new ImageIcon(myImage));
    }
    
    /**
     * Method paintComponent.
     * @param g Graphics
     */
    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		planet.draw(g, (size.width - 
		                    (planet.toString().length() << 2)) >> 1,
		                    (size.height));
		if (isHere) {
			g.drawImage(playerImage, size.width + 25, size.height >> 1, null);
		}
	}

	/**
	 * Method setHere.
	 * @param here boolean
	 */
	public void setHere(boolean here) {
		this.isHere = here;
	}

	/**
	 * @author AmazingPenguins
	 * @version 0.01
	 */
	private class PlanetButtonListener implements ActionListener {
        /**
         * Method actionPerformed.
         * @param event ActionEvent
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
		public void actionPerformed(ActionEvent event) {
			up.goToPlanet(planet);
		}
        
        /**
         * toString
         * @return String
         */
        @Override
        public String toString() {
            return "Planet Button Listener";
        }
	}
	
    /**
     * toString
     * @return String
     */
	@Override
	public String toString() {
	    return "Planet Button";
	}
}