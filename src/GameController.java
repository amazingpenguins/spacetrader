import java.awt.Point;
import java.util.concurrent.CountDownLatch;
import javax.swing.JPanel;
import java.util.*;
import java.io.*;

public class GameController implements InitViewDelegate, java.io.Serializable {

    // DO NOT CHANGE THIS! If you do, it will prevent saving and loading. 
    static final long serialVersionUID = -4592216675779618168L;

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
    public Planet[] getPlanets() {
        Planet[] p = new Planet[planets.size()];
        for (int i = 0; i < planets.size(); i++){
            p[i] = planets.get(i);
        }
        return p;
    }

    public void updateMarketPanel(Planet pl) {
        marketPanel.setPlanet(pl);
        marketPanel.setPlayer(plr);
    }

    public void displayInitConfigScreen() {
        initView.setDelegate(this);
        mainGUI.displayPanel(initView);
        this.await();
    }

    public void setupMainGUI() {
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

    public static void main(String[] args) {
        GameController gc = new GameController();
        while(gc.runGame() == 0);

        System.exit(0);
    }
    
    public void saveGame(){
        String gameInfo = "";
        gameInfo+=plr.getName()+"\n";
        gameInfo+=plr.getStats().getEngineer()+"\n";
        gameInfo+=plr.getStats().getFighter()+"\n";
        gameInfo+=plr.getStats().getTrader()+"\n";
        gameInfo+=plr.getStats().getPilot()+"\n";
        gameInfo+=plr.getStats().getCredits()+"\n";
        gameInfo+=difficulty+"\n";
        gameInfo+=plr.getShip().toString()+"\n";
        gameInfo+=plr.getShip().getFuel()+"\n";
        //for cargo bay, go through hash map
            //signal end with ;
        //for planets and info about them, go through array of planets
            //signal end with ;

        File f = new File("gameSave.txt");
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            writer.println(gameInfo);
            writer.flush();
            writer.close();
        }
        catch(IOException e) {
            
        }
    }
}
