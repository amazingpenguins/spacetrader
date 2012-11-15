/**
 * Universe Panel
 */
package edu.gatech.amazingpenguins.spacetrader; 
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class UniversePanel extends JPanel {
    /**
     * Field serialVersionUID.
     * (value is -1595808849286334446)
     */
    private static final long serialVersionUID = -1595808849286334446L;

    /**
     * Field random.
     */
    private final Random random;
    
    /**
     * Field gc.
     */
    private final GameController gc;

    /**
     * Field gamePanel.
     */
    private final GamePanel gamePanel;

    /**
     * Field planetButtons.
     */
    private PlanetButton[][] planetButtons;

    /**
     * Field universeBackground.
     */
    private BufferedImage universeBackground;

    /**
     * Field player.
     */
    private Player player;

    /**
     * Constructor for UniversePanel.
     * @param universe SolarSystem[][]
     * @param gc GameController
     * @param gamePanel GamePanel
     */
    public UniversePanel(SolarSystem[][] universe,
                         GameController gc, GamePanel gamePanel) {
        this.gc = gc;
        this.gamePanel = gamePanel;
        random = new Random();
        setupPanel(universe);
    }

    /**
     * Setup Panel
     * @param universe The Universe
     */
    private void setupPanel(SolarSystem[][] universe) {
        try{
            universeBackground = ImageIO.read(new File("images/star_background.jpg"));
        } catch(IOException ioe) {
            System.err.println("Error getting images/UniverseBackground.png");
            universeBackground = null;
            ioe.printStackTrace();
        }
        
        final int tile_size = 300;
        setPreferredSize(new Dimension(tile_size, tile_size));
        setLayout(new GridLayout(GameController.UNIVERSE_SIZE,
                                 GameController.UNIVERSE_SIZE));
        planetButtons = new PlanetButton[GameController.UNIVERSE_SIZE]
                                        [GameController.UNIVERSE_SIZE];
        
        for (int i = 0; i < GameController.UNIVERSE_SIZE; i++) {
            for (int j = 0; j < GameController.UNIVERSE_SIZE; j++) {
                planetButtons[i][j] = new PlanetButton(universe[i][j].getPlanet(),
                    new Dimension(40, 40), this);
                this.add(planetButtons[i][j]);
            }
        }
    }
    
    /**
     * Method setPlayer.
     * @param p Player
     */
    public void setPlayer(Player p) {
        this.player = p;
        player.setPlanet(player.getPlanet());
        planetButtons[player.getLocation().x]
                     [player.getLocation().y].setHere(true);
        planetButtons[player.getLocation().x]
                     [player.getLocation().y].repaint();
        repaint();
    }

    /**
     * Method randomEvent.
     */
    public void randomEvent(){
        final int numEvent = 5;
        final int whichEvent = random.nextInt(numEvent);

        if (random.nextInt(10) <= 2) {
            events(whichEvent);
        }
    }

    /**
     * Method events.
     * @param i int
     */
    public void events(int i) {
        switch (i) {
            case 1:
                JOptionPane.showMessageDialog(this, 
                        "You discovered a fuel leak en route." +
                        "The damage was successfully " +
                        "repaired, but your ship lost 5 units of fuel.");

                if (player.getShip().getFuel() - 5 >= 0) {
                    player.getShip().setFuel(player.getShip().getFuel() - 5);
                } else {
                    player.getShip().setFuel(0);
                }
                break;
            case 2:
                JOptionPane.showMessageDialog(this, 
                        "You successfully maneuvered" +
                        "through a treacherous asteroid belt. " +
                        "You earned a pilot skill point.");

                player.s.setPilot((short) (player.s.getPilot() + 1));
                break;
            case 3:
                JOptionPane.showMessageDialog(this, "Your ship was ambushed by pirates" +
                        " who pillaged your goods. " +
                        "Check your cargo bay to assess your losses.");

                player.getShip().clearCargo();
                break;
            case 4:
                JOptionPane.showMessageDialog(this, 
                        "You responded to a distress signal and" + 
                        " assisted a stranded Bumblebee ship and its crew. " +
                        "You were offered 3 units of narcotics for your trouble.");

                if (player.getShip().getCargoSpace() > 2) {
                    final TradeGood tg = new TradeGood(TradeGood.NARCOTICS);
                    player.getShip().addCargo(tg, 3);
                }
                break;

            case 5:
                JOptionPane.showMessageDialog(this, 
                        "Your ship was damaged while navigating" +
                        " through an asteroid belt. The damages were repaired " +
                        "at the nearest starport, for a price of 500 credits.");

                if (player.getCredits() >= 500) {
                    player.addCredits(-500);
                } else {
                    player.addCredits(-(player.getCredits()));
                }
                break;
            default:
                /* This should not happen... */
                break;
        }
    }

    /**
     * Method goToPlanet.
     * @param p Planet
     */
    public void goToPlanet(Planet p) {
        if (!isPlayerNearPlanet(p)) {
            JOptionPane.showMessageDialog(this, "That planet is too far away.");
            return;
    	}

        if (!hasFuel(p)) {
            JOptionPane.showMessageDialog(this, "Not enough fuel.");
            return;
        }

        //fuel cost
        final SpaceShip ship = player.getShip();
        final double newFuel = ship.getFuel() - 
                (Math.abs(player.getLocation().getX() - p.getLocation().getX())) - 
                (Math.abs(player.getLocation().getY() - p.getLocation().getY()));
        ship.setFuel((int) newFuel);
        planetButtons[player.getLocation().x]
                     [player.getLocation().y].setHere(false);
        planetButtons[player.getLocation().x]
                     [player.getLocation().y].repaint();
    	player.setLocation(p.getLocation());

        randomEvent();

    	planetButtons[p.getLocation().x]
    	             [p.getLocation().y].setHere(true);
    	planetButtons[p.getLocation().x]
    	             [p.getLocation().y].repaint();
        gc.updateMarketPanel(p);
        player.setPlanet(p);
        gamePanel.updatePlayerPanel();

        // so, when you need to change the current location
        // all you need to do is setHere on the button that corresponds to the location they are going to 
        // then call the repaint on the button 
    }

    /**
     * Method paintComponent.
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(universeBackground, 0, 0, this.getWidth(), this.getHeight(), null);
    }

  /**
   * Method playerNearPlanet.
   * @param p Planet
   * @return boolean
   */
  private boolean isPlayerNearPlanet(Planet p) {
      return Math.abs(player.getLocation().x - p.getLocation().x) < 2 &&
              Math.abs(player.getLocation().y - p.getLocation().y) < 2;

  }

    /**
     * Method hasFuel.
     * @param p Planet
     * @return boolean
     */
    private boolean hasFuel(Planet p) {
        final SpaceShip ship = player.getShip();
        return ship.getFuel() >=
                Math.abs(Math.abs(player.getLocation().x - p.getLocation().x) -
                         Math.abs(player.getLocation().y - p.getLocation().y));

    }
}