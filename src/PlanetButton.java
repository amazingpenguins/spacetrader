import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlanetButton extends JButton {
	private Planet planet;
	private Dimension size;
	protected UniversePanel up;

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
	}

	private class PlanetButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			up.goToPlanet(planet);
		}
	}
}