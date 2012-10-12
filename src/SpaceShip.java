import java.util.ArrayList;
import java.util.Map;
import java.util.EnumMap;

public class SpaceShip {
    protected enum Stat {
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
        REPAIRCOST,
        SIZE,
        PRICE;
    }
    protected ArrayList<Player> crew;
    protected Map<Stat, Integer> shipStats;
    private final String name;

    /**
     * This constructor generates a SpaceShip with all of the correct
     * stats, based on the type specified.
     * @param type The type of ship to generate:
     * 0 == flea
     * 1 == gnat
     * 2 == firefly
     * 3 == mosquito
     * 4 == bumblebee
     * default == gnat
     */
    public SpaceShip(int type) {
        shipStats = new EnumMap<Stat, Integer>(Stat.class);
        switch(type) {
            case 0:
                //flea
                this.name = "flea";
                shipStats.put(Stat.CARGOSIZE, 10);
                shipStats.put(Stat.WEAPONSLOTS, 0);
                shipStats.put(Stat.SHIELDSLOTS, 0);
                shipStats.put(Stat.GADGETSLOTS, 0);
                shipStats.put(Stat.CREWSIZE, 1);
                shipStats.put(Stat.FUELSIZE, 20);
                shipStats.put(Stat.TECHLEVEL, 4);
                shipStats.put(Stat.FUELCOST, 1);
                shipStats.put(Stat.BOUNTY, 5);
                shipStats.put(Stat.OCCURENCE, 2);
                shipStats.put(Stat.HULLSTRENGTH, 25);
                shipStats.put(Stat.REPAIRCOST, 1);
                shipStats.put(Stat.SIZE, 0);
                shipStats.put(Stat.PRICE, 2000);
                break;
            case 1:
                //gnat
                this.name = "gnat";
                shipStats.put(Stat.CARGOSIZE, 15);
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
                shipStats.put(Stat.REPAIRCOST, 1);
                shipStats.put(Stat.SIZE, 1);
                shipStats.put(Stat.PRICE, 10000);
                break;
            case 2:
                //firefly
                this.name = "firefly";
                shipStats.put(Stat.CARGOSIZE, 20);
                shipStats.put(Stat.WEAPONSLOTS, 1);
                shipStats.put(Stat.SHIELDSLOTS, 1);
                shipStats.put(Stat.GADGETSLOTS, 1);
                shipStats.put(Stat.CREWSIZE, 1);
                shipStats.put(Stat.FUELSIZE, 17);
                shipStats.put(Stat.TECHLEVEL, 5);
                shipStats.put(Stat.FUELCOST, 3);
                shipStats.put(Stat.BOUNTY, 75);
                shipStats.put(Stat.OCCURENCE, 20);
                shipStats.put(Stat.HULLSTRENGTH, 100);
                shipStats.put(Stat.REPAIRCOST, 1);
                shipStats.put(Stat.SIZE, 1);
                shipStats.put(Stat.PRICE, 25000);
                break;
            case 3:
                //mosquito
                this.name = "mosquito";
                shipStats.put(Stat.CARGOSIZE, 15);
                shipStats.put(Stat.WEAPONSLOTS, 2);
                shipStats.put(Stat.SHIELDSLOTS, 1);
                shipStats.put(Stat.GADGETSLOTS, 1);
                shipStats.put(Stat.CREWSIZE, 1);
                shipStats.put(Stat.FUELSIZE, 13);
                shipStats.put(Stat.TECHLEVEL, 5);
                shipStats.put(Stat.FUELCOST, 5);
                shipStats.put(Stat.BOUNTY, 100);
                shipStats.put(Stat.OCCURENCE, 20);
                shipStats.put(Stat.HULLSTRENGTH, 100);
                shipStats.put(Stat.REPAIRCOST, 1);
                shipStats.put(Stat.SIZE, 1);
                shipStats.put(Stat.PRICE, 30000);
                break;
            case 4:
                //bumblebee
                this.name = "bumblebee";
                shipStats.put(Stat.CARGOSIZE, 25);
                shipStats.put(Stat.WEAPONSLOTS, 1);
                shipStats.put(Stat.SHIELDSLOTS, 1);
                shipStats.put(Stat.GADGETSLOTS, 2);
                shipStats.put(Stat.CREWSIZE, 2);
                shipStats.put(Stat.FUELSIZE, 15);
                shipStats.put(Stat.TECHLEVEL, 5);
                shipStats.put(Stat.FUELCOST, 7);
                shipStats.put(Stat.BOUNTY, 125);
                shipStats.put(Stat.OCCURENCE, 15);
                shipStats.put(Stat.HULLSTRENGTH, 100);
                shipStats.put(Stat.REPAIRCOST, 1);
                shipStats.put(Stat.SIZE, 2);
                shipStats.put(Stat.PRICE, 60000);
                break;
            default:
                //gnat
                this.name = "gnat";
                shipStats.put(Stat.CARGOSIZE, 15);
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
                shipStats.put(Stat.REPAIRCOST, 1);
                shipStats.put(Stat.SIZE, 1);
                shipStats.put(Stat.PRICE, 10000);
                break;
        }
    }
    public int getFuelSize() {
        return shipStats.get(Stat.FUELSIZE);
    }
    public boolean addCrew(Player crewMem) {
        return crew.add(crewMem);
    }
    public boolean removeCrew(Player crewMem) {
        return crew.remove(crewMem);
    }
    public String toString() {
        return name;
    }
}
