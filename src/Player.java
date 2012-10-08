import java.util.EnumMap;
import java.util.Map;

public class Player
{
	// Player Stats
	public static enum Stat
	{
		PILOT,
		TRADER,
		FIGHTER,
		ENGINEER;
	}

	public static final short INITCREDITS = 1000;
	private final Map<Stat, Short> myStats;
	

	private boolean amNPC; // false if it is a human, true if it is a computer. 
	private int credits;
	private String name;
	private SpaceShip myShip;
	
	//TODO Implement the Location class.
	//private Location myLocation;
	
	public Player(String name, short pilotStat, short traderStat, short fighterStat, short engineerStat, boolean amNPC)
	{
		myStats = new EnumMap<Stat,Short>(Stat.class);
		myStats.put(Stat.PILOT, pilotStat);
		myStats.put(Stat.TRADER, traderStat);
		myStats.put(Stat.FIGHTER, fighterStat);
		myStats.put(Stat.ENGINEER, engineerStat);
		this.name = name;
		this.amNPC = amNPC;
		this.credits = INITCREDITS;
	}
	public String getName()
	{
		return name;
	}
	public int getCredits()
	{
		return credits;
	}
	public SpaceShip getShip()
	{
		return myShip;
	}
	public int getStat(Stat attr)
	{
		return myStats.get(attr);
	}
	public void addCredits(int credits)
	{
		this.credits += credits;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setShip(SpaceShip newShip)
	{
		myShip = newShip;
	}

	public String toString() {
		String description = "Player {\n";
		description += "\tName: " + name + ",\n";
		description += "\tCredits: " + credits + ",\n";
		description += "\tShip: " + myShip + ",\n";
		for (Stat s : myStats.keySet()) {
			description += "\t" + s.toString() + ": " + myStats.get(s) + "\n";
		}
		description += "}";
		return description;
	}
}
