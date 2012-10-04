public class GameController
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
	
	public GameController()
	{
		//TODO Implement saved games.
		//TODO Actually get the player info from the UI.
		short pilotStat = 0, traderStat = 0, fighterStat = 0, engineerStat = 0;
		plr = new Player("test", pilotStat, traderStat, fighterStat, engineerStat, false);
	}
	private int runGame()
	{
		//TODO Finish the game states.
		switch(state)
		{
			case INIT:
				System.out.println("Init");
				state = State.NEXTSTATE;
				break;
			default:
				return 1;
		}
		return 0;
	}
	public static void main(String[] args)
	{
		GameController gc = new GameController();
		while(gc.runGame() == 0);
	}
}
