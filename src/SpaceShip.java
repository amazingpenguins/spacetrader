import java.util.ArrayList;
import java.util.Map;

public class SpaceShip
{
	protected enum Stat
	{
		CARGOSIZE,
		WEAPONSLOTS,
		SHIELDSLOTS,
		GADGETSLOTS,
		CREWSIZE,
		FUELSIZE,
		TECHLEVEL,
		FUELCOST,
		BOUNTY,
		OCCURENCE,
		HULLSTRENGTH,
		PRICE;
	}
	protected ArrayList<Player> crew;
	protected Map<Stat, Integer> shipStats;
	private final String name;
	
	public SpaceShip(String name)
	{
		this.name = name;
	}
	public int getFuelSize()
	{
		return shipStats.get(Stat.FUELSIZE);
	}
	public boolean addCrew(Player crewMem)
	{
		return crew.add(crewMem);
	}
	public boolean removeCrew(Player crewMem)
	{
		return crew.remove(crewMem);
	}
}
