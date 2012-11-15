/**
 * Game Controller
 * Controls the entire game, and contains the game loop.
 */
package edu.gatech.amazingpenguins.spacetrader; 
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import javax.swing.JPanel;

/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class GameController implements InitViewDelegate, java.io.Serializable {

    // DO NOT CHANGE THIS! If you do, it will prevent saving and loading. 
    /**
     * Field serialVersionUID.
     * (value is -4592216675779618168)
     */
    public static final long serialVersionUID = -4592216675779618168L;

    /**
     * Game State
     * @author AmazingPenguins
     */
    public enum State {
        /**
         * Field INIT.
         */
        INIT,
        /**
         * Field MAINMENU.
         */
        MAINMENU,
        /**
         * Field NEWPLAYER.
         */
        NEWPLAYER,
        /**
         * Field GAMEPANEL.
         */
        GAMEPANEL,
        /**
         * Field MARKETPANEL.
         */
        MARKETPANEL,
        /**
         * Field NEXTSTATE.
         */
        NEXTSTATE;
    }

    /**
     * Field plr.
     */
    private Player plr;
    
    /**
     * Field state.
     */
    private State state = State.INIT;
    
    /**
     * Field difficulty.
     */
    private String difficulty;
    
    /**
     * Field universe.
     */
    private SolarSystem[][] universe;
    
    /**
     * Field planets.
     */
    private List<Planet> planets;
    
    /**
     * Field mainGUI.
     */
    private MainGUI mainGUI;
    
    /**
     * Field panels.
     */
    private final List<JPanel> panels;

    /**
     * Field startPanel.
     */
    private final StartGamePanel startPanel;
    
    /**
     * Field gamePanel.
     */
    private final GamePanel gamePanel;
    
    /**
     * Field marketPanel.
     */
    private final MarketPanel marketPanel;
    
    /**
     * Field mainScreenLatch.
     */
    private CountDownLatch mainScreenLatch;
    
    /**
     * Field initView.
     */
    private final InitView initView;

    /**
     * Field UNIVERSE_SIZE.
     * (value is 5)
     */
    public static final int UNIVERSE_SIZE = 5;

    /**
     * Constructor for GameController.
     */
    public GameController() {
        planets = new ArrayList<Planet>();
        generateUniverse();
        difficulty = "Beginner";
        //TODO Implement saved games.

        panels = new ArrayList<JPanel>();
        // add panels here to the array in the order they should show up in the game
        // if using the below syntax to both assign to the instance variable and add to the panels array
        // be sure to use proper parenthesis
        panels.add((startPanel = new StartGamePanel(this)));
        panels.add((gamePanel = new GamePanel(this, universe)));
        panels.add((marketPanel = new MarketPanel(new Market(1, 2, 3), plr, this)));
        panels.add(initView = new InitView(this));
        mainScreenLatch = new CountDownLatch(1);
    }

    /**
     * Method runGame.
    
     * @return int */
    private int runGame() {
        switch(state) {
            case INIT:
                this.setupMainGUI();
                goToState(State.MAINMENU);
                break;
            case MAINMENU:
                mainGUI.displayPanel(startPanel);
                this.await();
                break;
            case NEWPLAYER:
                this.displayInitConfigScreen();
                break;
            case GAMEPANEL:
                if(plr.getPlanet() != null){
                    gamePanel.updatePlayerPanel();
                }
                mainGUI.displayPanel(gamePanel);
                this.await();
                break;
            case MARKETPANEL:
                mainGUI.displayPanel(marketPanel);
                this.await();
                break;
            default:
                System.out.println("skipped states...");
                return 1;
        }
        return 0;
    }

    /**
     * Method generateUniverse.
     */
    private void generateUniverse() {
        universe = new SolarSystem[UNIVERSE_SIZE][UNIVERSE_SIZE];
        for (int i = 0; i < UNIVERSE_SIZE; i++) {
            for (int j = 0; j < UNIVERSE_SIZE; j++) {
                universe[i][j] = new SolarSystem(new Point(i, j));
                planets.add(universe[i][j].getPlanet());
            }
        }
    }

    /**
     * Method updateMarketPanel.
     * @param pl Planet
     */
    public void updateMarketPanel(Planet pl) {
        marketPanel.setPlanet(pl);
        marketPanel.setPlayer(plr);
    }

    /**
     * Method displayInitConfigScreen.
     */
    protected void displayInitConfigScreen() {
        initView.setDelegate(this);
        mainGUI.displayPanel(initView);
        this.await();
    }

    /**
     * Method setupMainGUI.
     */
    protected void setupMainGUI() {
        mainGUI = new MainGUI(panels);  
    }

    /**
     * Method await.
     */
    private void await() {
        mainScreenLatch = new CountDownLatch(1);
        try {
            mainScreenLatch.await();
        } catch (InterruptedException ie) {
          ie.printStackTrace();
        }
    }

    /**
     * Method goToState.
     * @param state State
     */
    public void goToState(State state) {
        mainScreenLatch.countDown();
        this.state = state;
    }

    /**
     * Method doneConfiguring.
     * @param view InitView
    
     * @see InitViewDelegate#doneConfiguring(InitView) */
    @Override
    public void doneConfiguring(InitView view) {
        final Stats plrStats = new Stats();
        plrStats.setName(view.getName());
        plrStats.setPilot(view.getPilot());
        plrStats.setTrader(view.getTrader());
        plrStats.setFighter(view.getFighter());
        plrStats.setEngineer(view.getEngineer());

        plr = new Player(plrStats);
        plr.setShip(new SpaceShip(SpaceShip.GNAT));
        plr.setPlanet(planets.get(0));
        marketPanel.setPlayer(plr);
        gamePanel.updatePlayer(plr);
        difficulty = view.getDifficulty();
    }

    /**
     * Utilize the SerialSaver class in order to save all of the game objects and data.
     */
    public void saveGame() {
        final Map<Class<?>,Object> storeMap = new HashMap<Class<?>, Object>();
        final SerialSaver ss = new SerialSaver();
        storeMap.put(Player.class, plr);
        storeMap.put(SolarSystem.class, universe);
        storeMap.put(Planet.class, planets);
        storeMap.put(String.class, difficulty);
        storeMap.put(State.class, state);
        ss.serializeToDisk(storeMap);
    }

    /**
     * Utilize the SerialSave class to load all of the saved game objects and data.
     */
    @SuppressWarnings("unchecked")
    public void loadGame() {
        final SerialSaver ss = new SerialSaver();
        final Map<Class<?>,Object> storeMap = ss.serializeFromDisk();
        plr = (Player) storeMap.get(Player.class);
        universe = (SolarSystem[][]) storeMap.get(SolarSystem.class);
        planets = (ArrayList<Planet>) storeMap.get(Planet.class);
        difficulty = (String) storeMap.get(String.class);
        state = (State) storeMap.get(State.class);
        marketPanel.setPlayer(plr);
        gamePanel.updatePlayer(plr);
        gamePanel.setUniverse(universe);
        goToState(state);
    }
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "Game Controller";
    }

    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        final GameController gc = new GameController();
        while(gc.runGame() == 0);

        System.exit(0);
    }
}
