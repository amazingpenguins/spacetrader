import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private JComboBox choosePlanet;
    protected GameController gc;

    public GamePanel(GameController gc) {
        this.gc = gc;
        JButton market = new JButton("Market");
        JLabel placeholder = new JLabel("Lots of other stuff to come...");
        choosePlanet = new JComboBox(gc.getPlanets());


        this.setLayout(new BorderLayout());
        this.add(market, BorderLayout.WEST);
        this.add(choosePlanet, BorderLayout.CENTER);
        this.add(placeholder, BorderLayout.EAST);
        market.addActionListener(new ButtonListener());
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           Planet planet = (Planet)choosePlanet.getSelectedItem();
           gc.updateMarketPanel(planet);
           gc.goToState(GameController.State.MARKETPANEL);
        }
    }
}
