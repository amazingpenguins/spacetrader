import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private UniversePanel universePanel;
    private JPanel playerPanel;
    public JLabel fuel, name, ship, credits, cargo,
                  planetName, env, tech, loc, gov;
    private JButton market, shipYard, refuel, save;
    protected GameController gc;
    private Player player;
    private BufferedImage bg;
    private Planet p;
    private int data = 0;

    public GamePanel(GameController gc, SolarSystem[][] universe) {
        this.gc = gc;
        universePanel = new UniversePanel(universe, this.gc, this);


        playerPanel = new JPanel();


        this.setLayout(new BorderLayout());
        this.add(universePanel, BorderLayout.CENTER);
        this.add(playerPanel, BorderLayout.SOUTH);
    }

    public void updatePlayer(Player p) {
        universePanel.setPlayer(p);
        player = p;

       setupPlayerPanel();
    }

    public void setupPlayerPanel(){
        playerPanel.setLayout(new GridLayout(5,3));
        playerPanel.setPreferredSize(new Dimension(650, 150));

        //player info
        fuel = new JLabel("Fuel Level: " + player.getShip().getFuel());
        name = new JLabel("Name: " + player.getName());
        ship = new JLabel("Spaceship: " + player.getShip());
        credits = new JLabel("Credits: " + player.getCredits());
        cargo = new JLabel("Cargo space: " + player.getShip().getCargoSpace());


        //planet info
        planetName = new JLabel("Planet Name: ");
        gov = new JLabel("Government: ");
        tech = new JLabel("Tech Level: ");
        env = new JLabel("Environment: ");
        loc = new JLabel("Location: ");


        //navigation buttons
        JButton market = new JButton("Market");
        JButton shipYard = new JButton("Ship Yard");
        JButton refuel = new JButton("Refuel");
        JButton save = new JButton("Save Game");
        shipYard.setEnabled(false);
        shipYard.setToolTipText("Under construction");
        market.addActionListener(new MarketListener());
        refuel.addActionListener(new FuelListener());
        save.addActionListener(new SaveListener());

        playerPanel.add(name);
        playerPanel.add(planetName);
        playerPanel.add(market);
        playerPanel.add(ship);
        playerPanel.add(gov);
        playerPanel.add(shipYard);
        playerPanel.add(credits);
        playerPanel.add(tech);
        playerPanel.add(new JLabel(""));
        playerPanel.add(cargo);
        playerPanel.add(env);
        playerPanel.add(refuel);
        playerPanel.add(fuel);
        playerPanel.add(loc);
        playerPanel.add(save);


    }

    public void updatePlayerPanel(){
        fuel.setText("Fuel Level: " + player.getShip().getFuel());
        name.setText("Name: " + player.getName());
        ship.setText("Spaceship: " + player.getShip());
        credits.setText("Credits: " + player.getCredits());
        cargo.setText("Cargo space: " + player.getShip().getCargoSpace());

        p = player.getPlanet();
        planetName.setText("Planet Name: " + p.toString());
        gov.setText("Government: " + p.getSolarSystem().govString());
        tech.setText("Tech Level: " + p.getSolarSystem().techString());
        env.setText("Environment: " + p.envString());
        loc.setText("Location: " + (int)p.getLocation().getX()+", "+(int)p.getLocation().getY());
    }

    private void refuel() {
        SpaceShip ship = player.getShip();
        int max =  ship.getMaxFuel();

        int price = (max - ship.getFuel())*10;
        if (player.getCredits() >= price){
            player.addCredits(-price);
            ship.setFuel(max);
        }
        else {
            JOptionPane.showMessageDialog(this, "Insufficient funds.");
        }
    }

    /**
     * Thanks to CardLayout, we have to re-initialize when player is loading a game.
     * @param universe The universe to use for this panel.
     */
    public void setUniverse(SolarSystem[][] universe) {
        universePanel = new UniversePanel(universe, this.gc, this);
        universePanel.setPlayer(player);
        this.removeAll();
        this.revalidate();
        this.setLayout(new BorderLayout());
        this.add(universePanel, BorderLayout.CENTER);
        this.add(playerPanel, BorderLayout.SOUTH);
    }

    private class MarketListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            gc.goToState(GameController.State.MARKETPANEL);

        }
    }

    private class FuelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            refuel();
            updatePlayerPanel();
        }
    }
    
    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            gc.saveGame();
        }
    }

}
