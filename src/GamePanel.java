import javax.swing.*;
import java.awt.*;

/**
 * @author ryree0
 * @version $Revision: 1.0 $
 */
public class GamePanel extends JPanel {
    
    /**
     * Field serialVersionUID.
     * (value is 3442496102257946170)
     */
    private static final long serialVersionUID = 3442496102257946170L;
    
    /**
     * Field universePanel.
     */
    private UniversePanel universePanel;
    
    /**
     * Field playerPanel.
     */
    private final PlayerPanel playerPanel;
    
    /**
     * Field gc.
     */
    private GameController gc;
    
    /**
     * Field player.
     */
    private Player player;

    /**
     * Constructor for GamePanel.
     * @param gc GameController
     * @param universe SolarSystem[][]
     */
    public GamePanel(GameController gc, SolarSystem[][] universe) {
        this.gc = gc;
        universePanel = new UniversePanel(universe, this.gc, this);

        playerPanel = new PlayerPanel(GameController.State.GAMEPANEL, player, gc);

        this.setLayout(new BorderLayout());
        this.add(universePanel, BorderLayout.CENTER);
        this.add(playerPanel, BorderLayout.SOUTH);
    }

    /**
     * Method updatePlayer.
     * @param p Player
     */
    public void updatePlayer(Player p) {
        player = p;
        playerPanel.updatePlayer(p);
        universePanel.setPlayer(p);
    }

    /**
     * Method updatePlayerPanel.
     */
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
