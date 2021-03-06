/**
 * Player Panel
 */
package edu.gatech.amazingpenguins.spacetrader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author AmazingPenguins
 * @version 0.01
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
     * Field _255.
     */
    private static int _255 = 255;
    
    /**
     * Field _80.
     */
    private static int _80 = 80;
    
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
        //this.setBackground(new Color(_255, _255, _255, 90));
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

        navigationButton.setBackground(new Color(_255, _255, _255, _80));
        navigationButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        navigationButton.setRolloverEnabled(false);
        navigationButton.setFocusPainted(false);
        navigationButton.addActionListener(new ButtonListener(onMarket));

        /* Ship Yard Button */
        final JButton shipYard = new JButton("Ship Yard");
        shipYard.setBackground(new Color(_255, _255, _255, _80));
        shipYard.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        shipYard.setRolloverEnabled(false);
        shipYard.setFocusPainted(false);
        shipYard.setEnabled(false);
        shipYard.setToolTipText("Under construction");

        /* Refuel Button */
        final JButton refuel = new JButton("Refuel");
        refuel.setBackground(new Color(_255, _255, _255, _80));
        refuel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        refuel.setRolloverEnabled(false);
        refuel.setFocusPainted(false);
        refuel.addActionListener(new FuelListener());

        /* Save Game Button */
        final JButton save = new JButton("Save Game");
        save.setBackground(new Color(_255, _255, _255, _80));
        save.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        save.setRolloverEnabled(false);
        save.setFocusPainted(false);
        save.addActionListener(new SaveListener());

        add(name);
        add(planetName);
        add(navigationButton);
        add(ship);
        add(gov);
        add(shipYard);
        add(credits);
        add(tech);
        add(refuel);
        add(cargo);
        add(env);
        add(new JLabel(""));
        add(fuel);
        add(loc);
        add(save);
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
            loc.setText("Location: " + curPlanet.getLocation().x + ", "
                                     + curPlanet.getLocation().y);
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
     * @author AmazingPenguins
     * @version 0.01
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
        
        /**
         * toString
         * @return String
         */
        @Override
        public String toString() {
            return "Button Listener";
        }
    }

    /**
     * @author AmazingPenguins
     * @version 0.01
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
        
        /**
         * toString
         * @return String
         */
        @Override
        public String toString() {
            return "Fuel Listener";
        }
    }

    /**
     * @author AmazingPenguins
     * @version 0.01
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
        
        /**
         * toString
         * @return String
         */
        @Override
        public String toString() {
            return "Save Listener";
        }
    }
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "Player Panel";
    }
}
