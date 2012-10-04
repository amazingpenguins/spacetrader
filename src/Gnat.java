import java.util.EnumMap;

public class Gnat extends SpaceShip
{
	public Gnat()
	{
		super("Gnat");
		shipStats = new EnumMap<Stat, Integer>(Stat.class);
		shipStats.put(Stat.CARGOSIZE, 10);
		shipStats.put(Stat.WEAPONSLOTS, 1);
		shipStats.put(Stat.SHIELDSLOTS, 0);
		shipStats.put(Stat.GADGETSLOTS, 1);
		shipStats.put(Stat.CREWSIZE, 1);
		shipStats.put(Stat.FUELSIZE, 14);
		shipStats.put(Stat.TECHLEVEL, 5);
		shipStats.put(Stat.FUELCOST, 2);
		shipStats.put(Stat.BOUNTY, 50);
		shipStats.put(Stat.OCCURENCE, 28);
		shipStats.put(Stat.HULLSTRENGTH, 100);
		shipStats.put(Stat.PRICE, 10000);
	}
}
