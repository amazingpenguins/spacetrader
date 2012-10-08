public class GameController implements InitViewDelegate
{
	// Game State
	private enum State
	{
		INIT(0),
		NEXTSTATE(1);
		
		private int index;
		private State(int index)
		{
			this.index = index;
		}
	}
	
	//
	Player plr;
	private State state = State.INIT;
	short[] attributes;
	private InitView init;
	short pilotStat, traderStat, fighterStat, engineerStat;
	
	private String difficulty;

	public GameController()
	{
		difficulty = new String("Beginner"); // default to this, but the player can change it in the InitView
		//TODO Implement saved games.

		//TODO Actually get the player info from the UI.
<<<<<<< HEAD
		this.displayInitConfigScreen();
	}

	private int runGame()
=======
		pilotStat = 0;
		traderStat = 0;
		fighterStat = 0;
		engineerStat = 0;
	}
	
	public void setPlayer(Player p){
		plr = p;
		System.out.println(plr);
	}
	private int runGame(GameController gc)
>>>>>>> when start is clicked new player is created based on gui input
	{
		//TODO Finish the game states.
		switch(state)
		{
			case INIT:
<<<<<<< HEAD
=======
				System.out.println("Init");
				init = new InitView(gc);
				//player should not be created until start button is clicked
>>>>>>> when start is clicked new player is created based on gui input
				state = State.NEXTSTATE;
				
				break;
			default:
				return 1;
		}
		return 0;
	}

	public void displayInitConfigScreen()
	{
		InitView initView = new InitView();
		initView.setDelegate(this);
	}

	public void doneConfiguring(InitView view) {
		plr = new Player(view.getName(), view.getPilot() , view.getTrader(), view.getFighter(), view.getEngineer(), false);
		difficulty = view.getDifficulty();
		plr.setShip(new SpaceShip(1));
		view.exit();
		System.out.println("Starting Game With Difficulty: " + difficulty);
		System.out.println("Created a new player:");
		System.out.println(plr);
	}

	public static void main(String[] args)
	{
		GameController gc = new GameController();
		while(gc.runGame(gc) == 0);
	}
}
