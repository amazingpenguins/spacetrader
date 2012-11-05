import javax.swing.*;
import java.util.*;
import java.awt.*;

public class UniversePanel extends JPanel {
	public final int TILE_SIZE = 60;
	public final int PLANET_OFFSET = 20;

	private SolarSystem[][] universe;
    private Player player;
    private GameController gc;

	public UniversePanel(SolarSystem[][] universe, GameController gc) {
        this.gc = gc;
		setPreferredSize(new Dimension(5*TILE_SIZE, 5*TILE_SIZE));
        setLayout(new GridLayout(GameController.UNIVERSE_SIZE, GameController.UNIVERSE_SIZE));
        this.universe = universe;
        for (int i = 0; i < GameController.UNIVERSE_SIZE; i++) {
            for (int j = 0; j < GameController.UNIVERSE_SIZE; j++) {
                this.add(new PlanetButton(this.universe[i][j].getPlanet(), 
                    new Dimension(40, 40), this));
            }
        }
    }


    private void setPlayer(Player p) {
        this.player = p;
        repaint();
    }

    public void goToPlanet(Planet p) {
        gc.updateMarketPanel(p);
        gc.goToState(GameController.State.MARKETPANEL);
    }


}