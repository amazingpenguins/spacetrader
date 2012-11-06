import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
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
	}

	public void paintComponent(Graphics g) {
		this.planet.draw(g, (this.size.width - Planet.PLANET_SIZE)/2, 
			(this.size.height - Planet.PLANET_SIZE)/2);
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