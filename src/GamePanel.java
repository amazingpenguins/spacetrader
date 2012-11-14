/**
 * GamePanel
 */
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * @author AmazingPenguins
 * @version 0.01
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
    private final GameController gc;
    
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
        setupPanel();
    }
    
    /**
     * Sets up the GamePanel
     */
    private void setupPanel() {
        setLayout(new BorderLayout());
        add(universePanel, BorderLayout.CENTER);
        add(playerPanel, BorderLayout.SOUTH);
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
        removeAll();
        revalidate();
        setLayout(new BorderLayout());
        add(universePanel, BorderLayout.CENTER);
        add(playerPanel, BorderLayout.SOUTH);
    }
}
