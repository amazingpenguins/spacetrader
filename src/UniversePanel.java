import javax.swing.*;
import java.util.*;
import java.awt.*;

public class UniversePanel extends JPanel {
	public final int TILE_SIZE = 60;
	public final int PLANET_OFFSET = 20;

	private SolarSystem[][] universe;

	public UniversePanel(SolarSystem[][] universe) {
		setPreferredSize(new Dimension(5*TILE_SIZE, 5*TILE_SIZE));
        this.universe = universe;
    }

    public void paint(Graphics g) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                universe[i][j].getPlanet().draw(g,j * TILE_SIZE + PLANET_OFFSET,
                								  i * TILE_SIZE + PLANET_OFFSET);
            }
        }
    }
}