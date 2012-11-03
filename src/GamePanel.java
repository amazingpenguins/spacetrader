import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private UniversePanel universePanel;
    private JTextArea playerArea;
    protected GameController gc;

    public GamePanel(GameController gc, SolarSystem[][] universe) {
        this.gc = gc;
        universePanel = new UniversePanel(universe);
        JButton market = new JButton("Market");

        playerArea = new JTextArea();
        playerArea.setEditable(false);
        playerArea.setMinimumSize(new Dimension(20, 20));


        this.setLayout(new BorderLayout());
        this.add(market, BorderLayout.WEST);
        this.add(universePanel, BorderLayout.CENTER);
        this.add(playerArea, BorderLayout.EAST);
        market.addActionListener(new ButtonListener());
    }

    public void updatePlayer(Player p) {
        playerArea.setText(String.format(p + "\n" + p.getShip()));
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
