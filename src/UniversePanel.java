import javax.swing.*;
import java.util.*;
import java.awt.*;

public class UniversePanel extends JPanel {
	public final int TILE_SIZE = 60;
	public final int PLANET_OFFSET = 20;

	private SolarSystem[][] universe;
    private Player player;
    private GameController gc;
    private PlanetButton[][] planetButtons;

	public UniversePanel(SolarSystem[][] universe, GameController gc) {
        this.gc = gc;
		setPreferredSize(new Dimension(5*TILE_SIZE, 5*TILE_SIZE));
        setLayout(new GridLayout(GameController.UNIVERSE_SIZE, GameController.UNIVERSE_SIZE));
        this.universe = universe;
        planetButtons = new PlanetButton[GameController.UNIVERSE_SIZE][GameController.UNIVERSE_SIZE];
        for (int i = 0; i < GameController.UNIVERSE_SIZE; i++) {
            for (int j = 0; j < GameController.UNIVERSE_SIZE; j++) {
                planetButtons[i][j] = new PlanetButton(this.universe[i][j].getPlanet(), 
                    new Dimension(40, 40), this);
                this.add(planetButtons[i][j]);
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
        // so, when you need to change the current location
        // all you need to do is setHere on the button that corresponds to the location they are going to 
        // then call the repaint on the button 
    }


}