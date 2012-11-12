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

    private GamePanel gamePanel;
	private SolarSystem[][] universe;
    private Player player;
    private GameController gc;
    private PlanetButton[][] planetButtons;
    private BufferedImage universeBackground;

	public UniversePanel(SolarSystem[][] universe, GameController gc, GamePanel gamePanel) {
        try{
            universeBackground = ImageIO.read(new File("images/star_background.jpg"));
        } catch(IOException ioe) {
            System.err.println("Error getting images/UniverseBackground.png");
            universeBackground = null;
        }
        this.gc = gc;
        this.gamePanel = gamePanel;
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
        planetButtons[(int) player.getLocation().getX()][(int) player.getLocation().getY()].setHere(true);
        planetButtons[(int) player.getLocation().getX()][(int) player.getLocation().getY()].repaint();
        repaint();
    }

    public void goToPlanet(Planet p) {

        if (!playerNearPlanet(p)){
            JOptionPane.showMessageDialog(this, "Planet too far away.");
            return;
    	}


        if (!sufficientFuel(p)){
            JOptionPane.showMessageDialog(this, "Not enough fuel.");
            return;
        }
        //fuel cost
        SpaceShip ship = player.getShip();
        double newFuel = ship.getFuel()-(Math.abs(player.getLocation().getX() - p.getLocation().getX()))-(Math.abs(player.getLocation().getY() - p.getLocation().getY()));
        ship.setFuel((int)newFuel);
        planetButtons[(int) player.getLocation().getX()][(int) player.getLocation().getY()].setHere(false);
        planetButtons[(int) player.getLocation().getX()][(int) player.getLocation().getY()].repaint();
    	player.setLocation(p.getLocation());
    	planetButtons[(int) p.getLocation().getX()][(int) p.getLocation().getY()].setHere(true);
    	planetButtons[(int) p.getLocation().getX()][(int) p.getLocation().getY()].repaint();
        gc.updateMarketPanel(p);
        gamePanel.fuel.setText("Fuel Level: "+(int)newFuel);
        player.setPlanet(p);
        gamePanel.updatePlayerPanel();
        //gc.goToState(GameController.State.MARKETPANEL);

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

    private boolean sufficientFuel(Planet p){

        SpaceShip ship = player.getShip();

        if (ship.getFuel() >= (Math.abs(player.getLocation().getX() - p.getLocation().getX()))-(player.getLocation().getY() - p.getLocation().getY()) && ship.getFuel() >= 0){
            return true;
        }

        return false;

    }

}