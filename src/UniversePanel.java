import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class UniversePanel extends JPanel {
    private final GameController gc;
    private final GamePanel gamePanel;
    private final PlanetButton[][] planetButtons;
    private BufferedImage universeBackground;
    private Player player;

    public UniversePanel(SolarSystem[][] universe, GameController gc, GamePanel gamePanel) {
        try{
            universeBackground = ImageIO.read(new File("images/star_background.jpg"));
        } catch(IOException ioe) {
            System.err.println("Error getting images/UniverseBackground.png");
            universeBackground = null;
        }
        this.gc = gc;
        this.gamePanel = gamePanel;
        int TILE_SIZE = 60;
        setPreferredSize(new Dimension(5* TILE_SIZE, 5* TILE_SIZE));
        setLayout(new GridLayout(GameController.UNIVERSE_SIZE, GameController.UNIVERSE_SIZE));
        planetButtons = new PlanetButton[GameController.UNIVERSE_SIZE][GameController.UNIVERSE_SIZE];
        for (int i = 0; i < GameController.UNIVERSE_SIZE; i++) {
            for (int j = 0; j < GameController.UNIVERSE_SIZE; j++) {
                planetButtons[i][j] = new PlanetButton(universe[i][j].getPlanet(),
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

    public void randomEvent(){
        int numEvent = 5;
        int whichEvent = (int)(Math.random() * numEvent);

        int random = (int)(Math.random() * 10);
        if (random <= 2) {
            events(whichEvent);
        }
    }

    public void events(int i){
        switch (i){
            case 1:
                JOptionPane.showMessageDialog(this, "You discovered a fuel leak en route. The damage was successfully " +
                        "repaired, but your ship lost 5 units of fuel.");

                if (player.getShip().getFuel() - 5 >= 0){
                    player.getShip().setFuel(player.getShip().getFuel() - 5);
                }
                else {
                    player.getShip().setFuel(0);
                }
                break;
            case 2:
                JOptionPane.showMessageDialog(this, "You successfully maneuvered through a treacherous asteroid belt. " +
                        "You earned a pilot skill point.");

                player.s.setPilot((short) (player.s.getPilot() + 1));
                break;
            case 3:
                JOptionPane.showMessageDialog(this, "Your ship was ambushed by pirates who pillaged your goods. " +
                        "Check your cargo bay to assess your losses.");

                player.getShip().clearCargo();
                break;
            case 4:
                JOptionPane.showMessageDialog(this, "You responded to a distress signal and assisted a stranded Bumblebee ship and its crew. " +
                        "You were offered 3 units of narcotics for your trouble.");

                if (player.getShip().getCargoSpace() > 2){
                    TradeGood tg = new TradeGood(TradeGood.NARCOTICS);
                    player.getShip().addCargo(tg, 3);
                }
                break;

            case 5:
                JOptionPane.showMessageDialog(this, "Your ship was damaged while navigating through an asteroid belt. " +
                        "The damages were repaired at the nearest starport, for a price of 500 credits.");

                if (player.getCredits() >= 500){
                    player.addCredits(-500);
                }
                else {
                    player.addCredits(-(player.getCredits()));
                }
                break;
        }
    }

    public void goToPlanet(Planet p) {
        if (!playerNearPlanet(p)) {
            JOptionPane.showMessageDialog(this, "That planet is too far away.");
            return;
    	}

        if (!sufficientFuel(p)) {
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

        randomEvent();

    	planetButtons[(int) p.getLocation().getX()][(int) p.getLocation().getY()].setHere(true);
    	planetButtons[(int) p.getLocation().getX()][(int) p.getLocation().getY()].repaint();
        gc.updateMarketPanel(p);
        player.setPlanet(p);
        gamePanel.updatePlayerPanel();

        // so, when you need to change the current location
        // all you need to do is setHere on the button that corresponds to the location they are going to 
        // then call the repaint on the button 
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(universeBackground, 0, 0, this.getWidth(), this.getHeight(), null);
    }

  private boolean playerNearPlanet(Planet p) {
      return Math.abs(player.getLocation().getX() - p.getLocation().getX()) < 2 &&
              Math.abs(player.getLocation().getY() - p.getLocation().getY()) < 2;

  }

    private boolean sufficientFuel(Planet p) {
        SpaceShip ship = player.getShip();
        return ship.getFuel() >=
                Math.abs(Math.abs(player.getLocation().getX() - p.getLocation().getX()) -
                (player.getLocation().getY() - p.getLocation().getY()));

    }

}