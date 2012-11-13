import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlanetButton extends JButton {
	private final Planet planet;
	private final Dimension size;
	private final UniversePanel up;
	private boolean isHere;
    private BufferedImage playerImage;

    public PlanetButton(Planet p, Dimension size, UniversePanel up) {
		setPreferredSize(size);
		this.up = up;
		this.size = size;
		this.planet = p;
		this.addActionListener(new PlanetButtonListener());
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setFocusable(false);
        this.setOpaque(false);

        try {
            playerImage = ImageIO.read(new File("images/SpaceShip.png"));
        } catch(IOException ioe) {
            System.err.println("Error locating images/SpaceShip.png");
            playerImage = null;
        }
        BufferedImage myImage;
        try {
            myImage = ImageIO.read(new File("images/planets/" + planet.toString() + ".png"));
        } catch(IOException ioe) {
            System.err.println("Error locating image: images/planets/" + planet.toString() + ".png");
            myImage = null;
        }
        this.setIcon(new ImageIcon(myImage));
	}

    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		this.planet.draw(g, (this.size.width - (planet.toString().length() * 4))/2, (this.size.height));
		if (isHere)
			g.drawImage(playerImage, this.size.width + 25, this.size.height / 2, null);
	}

	public void setHere(boolean here) {
		this.isHere = here;
	}

	private class PlanetButtonListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent event) {
			up.goToPlanet(planet);
		}
	}
}