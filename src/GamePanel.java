import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class GamePanel extends JPanel{
    private JButton market;
    private JLabel placeholder;
    private JComboBox choosePlanet;
    private ArrayList<Planet> planets;
    Planet[] planetArray;
    GameController gc;
    CountDownLatch latch;

    public GamePanel(GameController gc, CountDownLatch latch) {
        this.latch = latch;
        this.gc = gc;
        market = new JButton("Market");
        placeholder = new JLabel("Lots of other stuff to come...");
        planets = gc.getPlanets();
        planetArray = new Planet[planets.size()];
        for (int x = 0; x < planets.size(); x++){
            planetArray[x] = planets.get(x);
        }
        choosePlanet = new JComboBox<Planet>(planetArray);


        this.setLayout(new BorderLayout());
        this.add(market, BorderLayout.WEST);
        this.add(choosePlanet, BorderLayout.CENTER);
        this.add(placeholder, BorderLayout.EAST);
        market.addActionListener(new ButtonListener());
    }

    private class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e){

            if (e.getSource() == market) {

                Object planet = (Planet)choosePlanet.getSelectedItem();

                //gc.switchToMarketPanel(planet.getGovernment(), planet.getEnvironment(), planet.getTechLevel());
                //gc.switchToMarketPanel(1, 1, 1);
                gc.goToState(GameController.State.MARKETPANEL);
            }
        }

    }
}
