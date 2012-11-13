import java.awt.Point;
import java.util.concurrent.CountDownLatch;
import javax.swing.JPanel;
import java.util.*;

public class GameController implements InitViewDelegate, java.io.Serializable {

    // DO NOT CHANGE THIS! If you do, it will prevent saving and loading. 
    static final long serialVersionUID = -4592216675779618168L;

    // Game State
    public enum State {
        INIT,
        MAINMENU,
        NEWPLAYER,
        GAMEPANEL,
        MARKETPANEL,
        NEXTSTATE;
    }

    private Player plr;
    private State state = State.INIT;
    private String difficulty;
    private SolarSystem[][] universe;
    private ArrayList<Planet> planets;
    private MainGUI mainGUI;
    private ArrayList<JPanel> panels;

    private StartGamePanel startPanel;
    private GamePanel gamePanel;
    private MarketPanel marketPanel;
    private CountDownLatch mainScreenLatch;
    private InitView initView;

    public static final int UNIVERSE_SIZE = 5;

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
        panels.add((marketPanel = new MarketPanel(new Market(1,2,3), plr, this)));
        panels.add(initView = new InitView(this));
        mainScreenLatch = new CountDownLatch(1);
    }

    private int runGame() {
        switch(state) {
            case INIT:
                this.setupMainGUI();
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

    private void generateUniverse() {
        universe = new SolarSystem[UNIVERSE_SIZE][UNIVERSE_SIZE];
        for (int i = 0; i < UNIVERSE_SIZE; i++) {
            for (int j = 0; j < UNIVERSE_SIZE; j++) {
                universe[i][j] = new SolarSystem(new Point(i, j));
                planets.add(universe[i][j].getPlanet());
            }
        }
    }

    public void updateMarketPanel(Planet pl) {
        marketPanel.setPlanet(pl);
        marketPanel.setPlayer(plr);
    }

    protected void displayInitConfigScreen() {
        initView.setDelegate(this);
        mainGUI.displayPanel(initView);
        this.await();
    }

    protected void setupMainGUI() {
        mainGUI = new MainGUI(panels);  
    }

    private void await() {
        mainScreenLatch = new CountDownLatch(1);
        try {
            mainScreenLatch.await();
        } catch (InterruptedException ie) {
          ie.printStackTrace();
        }
    }

    public void goToState(State state) {
        mainScreenLatch.countDown();
        this.state = state;
    }

    public void doneConfiguring(InitView view) {
        Stats plrStats = new Stats();
        plrStats.setName(view.getName());
        plrStats.setPilot(view.getPilot());
        plrStats.setTrader(view.getTrader());
        plrStats.setFighter(view.getFighter());
        plrStats.setEngineer(view.getEngineer());
        plrStats.setAmNPC(false);

        plr = new Player(plrStats);
        plr.setShip(new SpaceShip(SpaceShip.GNAT));
        marketPanel.setPlayer(plr);
        gamePanel.updatePlayer(plr);
        difficulty = view.getDifficulty();
    }

    /**
     * Utilize the SerialSaver class in order to save all of the game objects and data.
     */
    public void saveGame() {
        HashMap<Class<?>, Object> storeMap = new HashMap<Class<?>, Object>();
        SerialSaver ss = new SerialSaver();
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
    public void loadGame() {
        SerialSaver ss = new SerialSaver();
        HashMap<Class<?>, Object> storeMap = ss.serializeFromDisk();
        plr = (Player)storeMap.get(Player.class);
        universe = (SolarSystem[][])storeMap.get(SolarSystem.class);
        planets = (ArrayList<Planet>)storeMap.get(Planet.class);
        difficulty = (String)storeMap.get(String.class);
        state = (State)storeMap.get(State.class);
        marketPanel.setPlayer(plr);
        gamePanel.updatePlayer(plr);
        gamePanel.setUniverse(universe);
        goToState(state);
    }

    public static void main(String[] args) {
        GameController gc = new GameController();
        while(gc.runGame() == 0);

        System.exit(0);
    }
}
