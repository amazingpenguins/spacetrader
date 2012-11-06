import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlanetButton extends JButton {
	private Planet planet;
	private Dimension size;
	protected UniversePanel up;
	private boolean isHere; 

	public PlanetButton(Planet p, Dimension size, UniversePanel up) {
		setPreferredSize(size);
		this.up = up;
		this.size = size;
		this.planet = p;
		this.addActionListener(new PlanetButtonListener());
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setOpaque(false);

        BufferedImage myImage;
        try {
            myImage = ImageIO.read(new File("images/planets/" + planet.toString() + ".png"));
        } catch(IOException ioe) {
            System.err.println("Error locating image: images/planets/" + planet.toString() + ".png");
            myImage = null;
        }
        this.setIcon(new ImageIcon(myImage));
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		this.planet.draw(g, (this.size.width - (planet.toString().length() * 4))/2, (this.size.height));
		if (isHere) {
			try {
            	g.drawImage(ImageIO.read(new File("images/Star.jpg")),
			 0, 0, 15, 15, null);
        	} catch(IOException IOE) {
            	IOE.printStackTrace();
			}
		}
	}

	public void setHere(boolean here) {
		this.isHere = here;
	}

	public boolean isHere() {
		return this.isHere;
	}

	private class PlanetButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			up.goToPlanet(planet);
		}
	}
}