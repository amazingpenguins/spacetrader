import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private UniversePanel universePanel;
    private JPanel playerPanel;
    public JLabel fuel;
    protected GameController gc;
    private Player player;

    public GamePanel(GameController gc, SolarSystem[][] universe) {
        this.gc = gc;
        universePanel = new UniversePanel(universe, this.gc, this);
        this.player = player;

        playerPanel = new JPanel();


        this.setLayout(new BorderLayout());
        this.add(universePanel, BorderLayout.CENTER);
        this.add(playerPanel, BorderLayout.WEST);
    }

    public void updatePlayer(Player p) {
        universePanel.setPlayer(p);
        player = p;

       setupPlayerPanel();
    }

    private void setupPlayerPanel(){
        fuel = new JLabel();
        fuel.setText("Fuel Level: " + player.getShip().getFuel());
        playerPanel.add(fuel);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           Planet planet = gc.getPlanets()[0];
           gc.updateMarketPanel(planet);
           gc.goToState(GameController.State.MARKETPANEL);
        }
    }
}
