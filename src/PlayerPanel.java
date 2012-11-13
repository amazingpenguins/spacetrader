import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: ryree0
 * Date: 11/7/12
 * Time: 12:23 PM
 * @author ryree0
 * @version $Revision: 1.0 $
 */
public class PlayerPanel extends JPanel {
    /**
     * Field serialVersionUID.
     * (value is 4040927144118839467)
     */
    private static final long serialVersionUID = 4040927144118839467L;

    /**
     * Field gc.
     */
    private final GameController gc;

    /**
     * Field gov.
     */
    /**
     * Field loc.
     */
    /**
     * Field tech.
     */
    /**
     * Field env.
     */
    /**
     * Field planetName.
     */
    /**
     * Field cargo.
     */
    /**
     * Field credits.
     */
    /**
     * Field ship.
     */
    /**
     * Field name.
     */
    /**
     * Field fuel.
     */
    private JLabel fuel, name, ship, credits, cargo,
            planetName, env, tech, loc, gov;

    /**
     * Field player.
     */
    private Player player;

    /**
     * Constructor for PlayerPanel.
     * @param s GameController.State
     * @param p Player
     * @param gc GameController
     */
    public PlayerPanel(GameController.State s, Player p, GameController gc) {
        this.player = p;
        this.gc = gc;
        switch(s) {
            case MARKETPANEL:
                setupPlayerPanel(true);
                break;
            case GAMEPANEL:
                setupPlayerPanel(false);
                break;
            default:
                setupPlayerPanel(false);
        }
    }

    /**
     * Method updatePlayer.
     * @param p Player
     */
    public void updatePlayer(Player p) {
        player = p;
        updatePlayerPanel();
    }

    /**
     * Method setupPlayerPanel.
     * @param onMarket boolean
     */
    private void setupPlayerPanel(boolean onMarket) {
        this.setLayout(new GridLayout(5, 3));
        this.setPreferredSize(new Dimension(650, 150));
        //this.setBackground(new Color(255, 255, 255, 90));
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        //player info
        fuel = new JLabel("  Fuel Level: ");
        name = new JLabel("  Name: ");
        ship = new JLabel("  Spaceship: ");
        credits = new JLabel("  Credits: ");
        cargo = new JLabel("  Cargo space: ");

        //planet info
        planetName = new JLabel("Planet Name: ");
        gov = new JLabel("Government: ");
        tech = new JLabel("Tech Level: ");
        env = new JLabel("Environment: ");
        loc = new JLabel("Location: ");

        /* Navigation Button */
        JButton navigationButton;
        if(onMarket) {
            navigationButton = new JButton("Universe");
        } else {
            navigationButton = new JButton("Market");
        }

        navigationButton.setBackground(new Color(255, 255, 255, 80));
        navigationButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        navigationButton.setRolloverEnabled(false);
        navigationButton.setFocusPainted(false);
        navigationButton.addActionListener(new ButtonListener(onMarket));

        /* Ship Yard Button */
        final JButton shipYard = new JButton("Ship Yard");
        shipYard.setBackground(new Color(255, 255, 255, 80));
        shipYard.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        shipYard.setRolloverEnabled(false);
        shipYard.setFocusPainted(false);
        shipYard.setEnabled(false);
        shipYard.setToolTipText("Under construction");

        /* Refuel Button */
        final JButton refuel = new JButton("Refuel");
        refuel.setBackground(new Color(255, 255, 255, 80));
        refuel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        refuel.setRolloverEnabled(false);
        refuel.setFocusPainted(false);
        refuel.addActionListener(new FuelListener());

        /* Save Game Button */
        final JButton save = new JButton("Save Game");
        save.setBackground(new Color(255, 255, 255, 80));
        save.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        save.setRolloverEnabled(false);
        save.setFocusPainted(false);
        save.addActionListener(new SaveListener());

        this.add(name);
        this.add(planetName);
        this.add(navigationButton);
        this.add(ship);
        this.add(gov);
        this.add(shipYard);
        this.add(credits);
        this.add(tech);
        this.add(refuel);
        this.add(cargo);
        this.add(env);
        this.add(new JLabel(""));
        this.add(fuel);
        this.add(loc);
        this.add(save);
    }

    /**
     * Method updatePlayerPanel.
     */
    public void updatePlayerPanel() {
        fuel.setText("  Fuel Level: " + player.getShip().getFuel());
        name.setText("  Name: " + player.getName());
        ship.setText("  Spaceship: " + player.getShip());
        credits.setText("  Credits: " + player.getCredits());
        cargo.setText("  Cargo space: " + player.getShip().getCargoSpace());

        final Planet curPlanet = player.getPlanet();
        if (curPlanet != null) {
            planetName.setText("Planet Name: " + curPlanet.toString());
            gov.setText("Government: " + curPlanet.getSolarSystem().govString());
            tech.setText("Tech Level: " + curPlanet.getSolarSystem().techString());
            env.setText("Environment: " + curPlanet.envString());
            loc.setText("Location: " + (int) curPlanet.getLocation().getX() + ", " + (int) curPlanet.getLocation().getY());
        } else {
            planetName.setText("Planet Name: ");
            gov.setText("Government: ");
            tech.setText("Tech Level: ");
            env.setText("Environment: ");
            loc.setText("Location: ");
        }
    }

    /**
     * Method refuel.
     */
    private void refuel() {
        final SpaceShip ship = player.getShip();
        final int max =  ship.getMaxFuel();

        int price = (max - ship.getFuel()) * 10;
        if (player.getCredits() >= price){
            player.addCredits(-price);
            ship.setFuel(max);
        } else {
            JOptionPane.showMessageDialog(this, "Insufficient funds.");
        }
    }

    /**
     */
    private class ButtonListener implements ActionListener {
        /**
         * Field onMarket.
         */
        private final boolean onMarket;

        /**
         * Constructor for ButtonListener.
         * @param onMarket boolean
         */
        private ButtonListener(boolean onMarket) {
            this.onMarket = onMarket;
        }

        /**
         * Method actionPerformed.
         * @param e ActionEvent
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(onMarket) {
                gc.goToState(GameController.State.GAMEPANEL);
            } else {
                gc.updateMarketPanel(player.getPlanet());
                gc.goToState(GameController.State.MARKETPANEL);
            }
        }
    }

    /**
     */
    private class FuelListener implements ActionListener {
        /**
         * Method actionPerformed.
         * @param e ActionEvent
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            refuel();
            updatePlayerPanel();
        }
    }

    /**
     */
    private class SaveListener implements ActionListener {
        /**
         * Method actionPerformed.
         * @param e ActionEvent
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            gc.saveGame();
        }
    }
}
