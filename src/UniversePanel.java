import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;

public class UniversePanel extends JPanel {
	public final int TILE_SIZE = 60;
	public final int PLANET_OFFSET = 20;

	private SolarSystem[][] universe;
    private Player player;
    private GameController gc;
    private PlanetButton[][] planetButtons;
    private BufferedImage universeBackground;

	public UniversePanel(SolarSystem[][] universe, GameController gc) {
        try{
            universeBackground = ImageIO.read(new File("images/UniverseBackground.png"));
        } catch(IOException ioe) {
            System.err.println("Error getting images/UniverseBackground.png");
            universeBackground = null;
        }
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


    public void setPlayer(Player p) {
        this.player = p;
        repaint();
    }

    public void goToPlanet(Planet p) {
    	if (!playerNearPlanet(p)){
    		return;
    	}
        planetButtons[(int) player.getLocation().getX()][(int) player.getLocation().getY()].setHere(false);
        planetButtons[(int) player.getLocation().getX()][(int) player.getLocation().getY()].repaint();
    	player.setLocation(p.getLocation());
    	planetButtons[(int) p.getLocation().getX()][(int) p.getLocation().getY()].setHere(true);
    	planetButtons[(int) p.getLocation().getX()][(int) p.getLocation().getY()].repaint();
        gc.updateMarketPanel(p);
        gc.goToState(GameController.State.MARKETPANEL);
        // so, when you need to change the current location
        // all you need to do is setHere on the button that corresponds to the location they are going to 
        // then call the repaint on the button 
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(universeBackground, 0, 0, this.getWidth(), this.getHeight(), null);
    }

  private boolean playerNearPlanet(Planet p){
	  
	  if (Math.abs(player.getLocation().getX() - p.getLocation().getX()) < 2  && Math.abs(player.getLocation().getY() - p.getLocation().getY()) < 2){
		  return true;
	  }
	return false;
	  
  }

}