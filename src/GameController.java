import java.util.concurrent.CountDownLatch;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.*;

public class GameController implements InitViewDelegate {
    // Game State
    private enum State {
        INIT(0),
        MAINMENU(1),
        NEXTSTATE(2);

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
    private MainGUI mainGUI;
    private ArrayList<JPanel> panels;

    private StartGamePanel startPanel;
    private MarketPanel marketPanel;

    public GameController() {
        difficulty = "Beginner"; // default to this, but the player can change it in the InitView
        //TODO Implement saved games.

        panels = new ArrayList<JPanel>();
        // add panels here to the array in the order they should show up in the game
        // if using the below syntax to both assign to the instance variable and add to the panels array, be sure to use proper parenthesis
        panels.add((startPanel = new StartGamePanel()));
        panels.add((marketPanel = new MarketPanel(new Market(1,2,3))));
    }

    private int runGame() {
        //TODO Finish the game states.
        switch(state) {
            case INIT:
                this.displayInitConfigScreen();
                System.out.println("Starting Game With Difficulty: " + difficulty);
                System.out.println("Created a new player:");
                System.out.println(plr);
                generateUniverse();
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        System.out.println(universe[i][j]);
                    }
                }
                System.out.println("Exiting...");
                state = State.MAINMENU;
                break;
            case MAINMENU:
                this.setupMainGUI();
                mainGUI.displayPanel(startPanel);
                state = State.NEXTSTATE;
                break;
            default:
                mainGUI.displayPanel(marketPanel);
                return 1;
        }
        return 0;
    }

    private void generateUniverse() {
        universe = new SolarSystem[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                universe[i][j] = new SolarSystem();
            }
        }
    }

    public void displayInitConfigScreen() {
        CountDownLatch initLatch = new CountDownLatch(1);
        InitView initView = new InitView(initLatch);
        initView.setDelegate(this);
        try {
            initLatch.await();
        } catch (InterruptedException ie) {
          ie.printStackTrace();
        }
    }

    public void setupMainGUI() {
        CountDownLatch mainScreenLatch = new CountDownLatch(1);
        mainGUI = new MainGUI(mainScreenLatch, panels);
        try {
            mainScreenLatch.await();
        } catch (InterruptedException ie) {
          ie.printStackTrace();
        }
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

        difficulty = view.getDifficulty();
        view.exit();
    }

    public static void main(String[] args) {
        GameController gc = new GameController();
        while(gc.runGame() == 0);
        
        System.exit(0);
    }
}
