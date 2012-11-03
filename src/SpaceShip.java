import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpaceShip {
    public static final short FLEA      = 0,
                              GNAT      = 1,
                              FIREFLY   = 2,
                              MOSQUITO  = 3,
                              BUMBLEBEE = 4;

    @SuppressWarnings("unused")
    private final short CARGOSIZE,
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
                        SIZE;
    
    private Map<TradeGood, Integer> cargoBay;
    private int curCargoCount;
    @SuppressWarnings("unused")
    private final int PRICE;
    private final String NAME;
    private ArrayList<Player> crew;

    /**
     * This constructor generates a SpaceShip with all of the correct
     * stats, based on the type specified.
     * @param type The type of ship to generate:
     * SpaceShip.<TYPE>
     * 0 == flea
     * 1 == gnat
     * 2 == firefly
     * 3 == mosquito
     * 4 == bumblebee
     * default == gnat
     */
    public SpaceShip(int type) {
        switch(type) {
            case FLEA:
                //flea
                NAME = "Flea";
                CARGOSIZE    = 10;
                WEAPONSLOTS  = 0;
                SHIELDSLOTS  = 0;
                GADGETSLOTS  = 0;
                CREWSIZE     = 1;
                FUELSIZE     = 20;
                TECHLEVEL    = 4;
                FUELCOST     = 1;
                BOUNTY       = 5;
                OCCURENCE    = 2;
                HULLSTRENGTH = 25;
                REPAIRCOST   = 1;
                SIZE         = 0;
                PRICE        = 2000;
                break;
            case GNAT:
                //gnat
                NAME = "Gnat";
                CARGOSIZE    = 15;
                WEAPONSLOTS  = 1;
                SHIELDSLOTS  = 0;
                GADGETSLOTS  = 1;
                CREWSIZE     = 1;
                FUELSIZE     = 14;
                TECHLEVEL    = 5;
                FUELCOST     = 2;
                BOUNTY       = 50;
                OCCURENCE    = 28;
                HULLSTRENGTH = 100;
                REPAIRCOST   = 1;
                SIZE         = 1;
                PRICE        = 10000;
                break;
            case FIREFLY:
                //firefly
                NAME = "Firefly";
                CARGOSIZE    = 20;
                WEAPONSLOTS  = 1;
                SHIELDSLOTS  = 1;
                GADGETSLOTS  = 1;
                CREWSIZE     = 1;
                FUELSIZE     = 17;
                TECHLEVEL    = 5;
                FUELCOST     = 3;
                BOUNTY       = 75;
                OCCURENCE    = 20;
                HULLSTRENGTH = 100;
                REPAIRCOST   = 1;
                SIZE         = 1;
                PRICE        = 25000;
                break;
            case MOSQUITO:
                //mosquito
                NAME = "Mosquito";
                CARGOSIZE    = 15;
                WEAPONSLOTS  = 2;
                SHIELDSLOTS  = 1;
                GADGETSLOTS  = 1;
                CREWSIZE     = 1;
                FUELSIZE     = 13;
                TECHLEVEL    = 5;
                FUELCOST     = 5;
                BOUNTY       = 100;
                OCCURENCE    = 20;
                HULLSTRENGTH = 100;
                REPAIRCOST   = 1;
                SIZE         = 1;
                PRICE        = 30000;
                break;
            case BUMBLEBEE:
                //bumblebee
                NAME = "Bumblebee";
                CARGOSIZE    = 25;
                WEAPONSLOTS  = 1;
                SHIELDSLOTS  = 1;
                GADGETSLOTS  = 2;
                CREWSIZE     = 2;
                FUELSIZE     = 15;
                TECHLEVEL    = 5;
                FUELCOST     = 7;
                BOUNTY       = 125;
                OCCURENCE    = 15;
                HULLSTRENGTH = 100;
                REPAIRCOST   = 1;
                SIZE         = 2;
                PRICE        = 60000;
                break;
            default:
                //gnat
                NAME = "Gnat";
                CARGOSIZE    = 15;
                WEAPONSLOTS  = 1;
                SHIELDSLOTS  = 0;
                GADGETSLOTS  = 1;
                CREWSIZE     = 1;
                FUELSIZE     = 14;
                TECHLEVEL    = 5;
                FUELCOST     = 2;
                BOUNTY       = 50;
                OCCURENCE    = 28;
                HULLSTRENGTH = 100;
                REPAIRCOST   = 1;
                SIZE         = 1;
                PRICE        = 10000;
                break;
        }
        cargoBay = new HashMap<TradeGood, Integer>();
        curCargoCount = 0;
    }

    /**
     * Add a crew member to the spaceship.
     * @param crewMem Crew member to add.
     * @return boolean: Was the addition successful?
     */
    public boolean addCrew(Player crewMem) {
        return crew.add(crewMem);
    }

    /**
     * Remove a crew member from the spaceship.
     * @param crewMem Crew member to remove.
     * @return boolean: Was the removal successful?
     */
    public boolean removeCrew(Player crewMem) {
        return crew.remove(crewMem);
    }
    
    /**
     * Add TradeGood(s) to the cargoBay
     * @param tg TradeGood to be added to cargoBay
     * @param quantity Number of the type of TradeGood being added to cargoBay
     */
    public boolean addCargo(TradeGood tg, int quantity){
        if(tg == null)
            return false;

        if((curCargoCount + quantity) <= CARGOSIZE) {
            int curAmt = 0;
            if(cargoBay.containsKey(tg))
                curAmt = cargoBay.get(tg);

            curCargoCount += quantity;
            cargoBay.put(tg, curAmt + quantity);
            return true;
        }
        return false;
    }
    
    /**
     * Remove TradeGood(s) from the cargoBay
     * @param tg TradeGood to be removed from cargoBay
     * @param quantity Number of the type of TradeGood being removed from cargoBay
     * @return TradeGood removed from cargoBay
     */
    public boolean removeCargo(TradeGood tg, int quantity) {
        if(tg == null)
            return false;

        if(cargoBay.containsKey(tg) &&
                (cargoBay.get(tg) >= quantity)) {
            int curAmt = cargoBay.get(tg);
            curCargoCount -= quantity;
            cargoBay.put(tg, curAmt - quantity);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks the cargo for a certain quantity of a TradeGood.
     * @param tg The TradeGood to check for in the cargo.
     * @param quantity Number of TradeGoods we need.
     * @return Whether or not the cargo contains enough of that TradeGood.
     */
    public boolean containsCargo(TradeGood tg, int quantity) {
        return (cargoBay.containsKey(tg) &&
                (cargoBay.get(tg) >= quantity));
    }

    public int getCargoCount(TradeGood tg) {
        if(!cargoBay.containsKey(tg))
            return 0;
        return cargoBay.get(tg);
    }

    public int getCargoSpace() {
        return CARGOSIZE - curCargoCount;
    }

    /**
     * Check to see if the cargo is full.
     * @return Is the cargo full?
     */
    public boolean cargoFull() {
        return curCargoCount >= CARGOSIZE;
    }

    public String toString() {
        return NAME;
    }
}
