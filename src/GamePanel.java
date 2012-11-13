import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private UniversePanel universePanel;
    private PlayerPanel playerPanel;
    private GameController gc;
    private Player player;

    public GamePanel(GameController gc, SolarSystem[][] universe) {
        this.gc = gc;
        universePanel = new UniversePanel(universe, this.gc, this);

        playerPanel = new PlayerPanel(GameController.State.GAMEPANEL, player, gc);

        this.setLayout(new BorderLayout());
        this.add(universePanel, BorderLayout.CENTER);
        this.add(playerPanel, BorderLayout.SOUTH);
    }

    public void updatePlayer(Player p) {
        player = p;
        playerPanel.updatePlayer(p);
        universePanel.setPlayer(p);
    }

    public void updatePlayerPanel() {
        playerPanel.updatePlayerPanel();
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
}
