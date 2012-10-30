import java.util.concurrent.CountDownLatch;
import javax.swing.JPanel;
import java.util.*;

public class GameController implements InitViewDelegate {
    // Game State
    public enum State {
        INIT(0),
        MAINMENU(1),
        NEWPLAYER(2),
        GAMEPANEL(3),
        MARKETPANEL(4),
        NEXTSTATE(5);

        @SuppressWarnings("unused")
        private int index;
        private State(int index) {
            this.index = index;
        }
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


    public GameController() {
        planets = new ArrayList<Planet>();
        generateUniverse();
        difficulty = "Beginner"; // default to this, but the player can change it in the InitView
        //TODO Implement saved games.

        panels = new ArrayList<JPanel>();
        // add panels here to the array in the order they should show up in the game
        // if using the below syntax to both assign to the instance variable and add to the panels array, be sure to use proper parenthesis
        panels.add((startPanel = new StartGamePanel(this, mainScreenLatch)));
        panels.add((gamePanel = new GamePanel(this, mainScreenLatch)));
        panels.add((marketPanel = new MarketPanel(new Market(1,2,3), plr)));
        panels.add(initView = new InitView(this));
        mainScreenLatch = new CountDownLatch(1);
    }

    private int runGame() {
        switch(state) {
            case INIT:

                this.setupMainGUI();
                state = State.MAINMENU;
                break;
            case MAINMENU:
                if (plr != null) {
                    startPanel.marketButton.setEnabled(true);
                }
                mainGUI.displayPanel(startPanel);
                this.await();
                break;
            case NEWPLAYER:
                this.displayInitConfigScreen();
                state = State.MAINMENU;
                break;
            case GAMEPANEL:
                mainGUI.displayPanel(gamePanel);
                this.await();
                state = State.MAINMENU;
                break;
            case MARKETPANEL:
                mainGUI.displayPanel(marketPanel);
                this.await();
                state = State.MAINMENU;
                break;
            default:
                System.out.println("skipped states...");
                return 1;
        }
        return 0;
    }

    private void generateUniverse() {
        universe = new SolarSystem[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                universe[i][j] = new SolarSystem();
                planets.add(universe[i][j].getPlanet());
            }
        }
    }
    public ArrayList<Planet> getPlanets() {
        return planets;
    }



    public void switchToMarketPanel(int gov, int env, int tech){
        MarketPanel market = new MarketPanel(new Market(gov,env,tech), plr);
        panels.add(market);
        mainGUI.displayPanel(market);
    }

    public void displayInitConfigScreen() {
        initView.setDelegate(this);
        mainGUI.displayPanel(initView);
        this.await();
    }

    public void setupMainGUI() {
        mainGUI = new MainGUI(panels);  
    }

    public Player getPlayer(){
        return plr;
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
        difficulty = view.getDifficulty();
    }

    public static void main(String[] args) {
        GameController gc = new GameController();
        while(gc.runGame() == 0);

        System.exit(0);
    }
}
