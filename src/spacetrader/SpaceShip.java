package spacetrader; /**
 * Space Ship
 */
import java.util.HashMap;
import java.util.Map;

/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class SpaceShip implements java.io.Serializable {

    // DO NOT CHANGE THIS! If you do, it will prevent saving and loading.
    /**
     * Field serialVersionUID.
     * (value is 4490691815582148282)
     */
    public static final long serialVersionUID = 4490691815582148282L;

    /**
     * Field BUMBLEBEE.
     * (value is 4)
     */
    /**
     * Field MOSQUITO.
     * (value is 3)
     */
    /**
     * Field FIREFLY.
     * (value is 2)
     */
    /**
     * Field GNAT.
     * (value is 1)
     */
    /**
     * Field FLEA.
     * (value is 0)
     */
    public static final short FLEA      = 0,
                              GNAT      = 1,
                              FIREFLY   = 2,
                              MOSQUITO  = 3,
                              BUMBLEBEE = 4;

    /**
     * Field size.
     */
    /**
     * Field repaircost.
     */
    /**
     * Field hullstrength.
     */
    /**
     * Field occurence.
     */
    /**
     * Field bounty.
     */
    /**
     * Field fuelcost.
     */
    /**
     * Field techlevel.
     */
    /**
     * Field fuelsize.
     */
    /**
     * Field crewsize.
     */
    /**
     * Field gadgetslots.
     */
    /**
     * Field shieldslots.
     */
    /**
     * Field weaponslots.
     */
    /**
     * Field cargosize.
     */
    @SuppressWarnings("unused")
    private final short cargosize,
                        weaponslots,
                        shieldslots,
                        gadgetslots,
                        crewsize,
                        fuelsize,
                        techlevel,
                        fuelcost,
                        bounty,
                        occurence,
                        hullstrength,
                        repaircost,
                        size;

    /**
     * Field cargoBay.
     */
    private final Map<TradeGood, Integer> cargoBay;

    /**
     * Field curCargoCount.
     */
    private int curCargoCount;

    /**
     * Field price.
     */
    private final int price;

    /**
     * Field name.
     */
    private final String name;

    /**
     * Field currentFuel.
     */
    private int currentFuel;

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
                name = "Flea";
                cargosize    = 10;
                weaponslots  = 0;
                shieldslots  = 0;
                gadgetslots  = 0;
                crewsize     = 1;
                fuelsize     = 20;
                techlevel    = 4;
                fuelcost     = 1;
                bounty       = 5;
                occurence    = 2;
                hullstrength = 25;
                repaircost   = 1;
                size         = 0;
                price        = 2000;
                break;
            case GNAT:
                //gnat
                name = "Gnat";
                cargosize    = 15;
                weaponslots  = 1;
                shieldslots  = 0;
                gadgetslots  = 1;
                crewsize     = 1;
                fuelsize     = 14;
                techlevel    = 5;
                fuelcost     = 2;
                bounty       = 50;
                occurence    = 28;
                hullstrength = 100;
                repaircost   = 1;
                size         = 1;
                price        = 10000;
                break;
            case FIREFLY:
                //firefly
                name = "Firefly";
                cargosize    = 20;
                weaponslots  = 1;
                shieldslots  = 1;
                gadgetslots  = 1;
                crewsize     = 1;
                fuelsize     = 17;
                techlevel    = 5;
                fuelcost     = 3;
                bounty       = 75;
                occurence    = 20;
                hullstrength = 100;
                repaircost   = 1;
                size         = 1;
                price        = 25000;
                break;
            case MOSQUITO:
                //mosquito
                name = "Mosquito";
                cargosize    = 15;
                weaponslots  = 2;
                shieldslots  = 1;
                gadgetslots  = 1;
                crewsize     = 1;
                fuelsize     = 13;
                techlevel    = 5;
                fuelcost     = 5;
                bounty       = 100;
                occurence    = 20;
                hullstrength = 100;
                repaircost   = 1;
                size         = 1;
                price        = 30000;
                break;
            case BUMBLEBEE:
                //bumblebee
                name = "Bumblebee";
                cargosize    = 25;
                weaponslots  = 1;
                shieldslots  = 1;
                gadgetslots  = 2;
                crewsize     = 2;
                fuelsize     = 15;
                techlevel    = 5;
                fuelcost     = 7;
                bounty       = 125;
                occurence    = 15;
                hullstrength = 100;
                repaircost   = 1;
                size         = 2;
                price        = 60000;
                break;
            default:
                //gnat
                name = "Gnat";
                cargosize    = 15;
                weaponslots  = 1;
                shieldslots  = 0;
                gadgetslots  = 1;
                crewsize     = 1;
                fuelsize     = 14;
                techlevel    = 5;
                fuelcost     = 2;
                bounty       = 50;
                occurence    = 28;
                hullstrength = 100;
                repaircost   = 1;
                size         = 1;
                price        = 10000;
                break;
        }
        cargoBay = new HashMap<TradeGood, Integer>();
        curCargoCount = 0;
        currentFuel = fuelsize;
    }

    /**
     * Add TradeGood(s) to the cargoBay
     * @param tg TradeGood to be added to cargoBay
     * @param quantity Number of the type of TradeGood being added to cargoBay
     */
    public void addCargo(TradeGood tg, int quantity){
        if(tg == null) {
            return;
        }

        if((curCargoCount + quantity) <= cargosize) {
            int curAmt = 0;
            if(cargoBay.containsKey(tg)) {
                curAmt = cargoBay.get(tg);
            }

            curCargoCount += quantity;
            cargoBay.put(tg, curAmt + quantity);
        }
    }

    /**
     * Remove TradeGood(s) from the cargoBay
     * @param tg TradeGood to be removed from cargoBay
     * @param quantity Number of the type of TradeGood being removed from cargoBay
     */
    public void removeCargo(TradeGood tg, int quantity) {
        if(tg == null) {
            return;
        }

        if(cargoBay.containsKey(tg) &&
                (cargoBay.get(tg) >= quantity)) {
            final int curAmt = cargoBay.get(tg);
            curCargoCount -= quantity;
            cargoBay.put(tg, curAmt - quantity);
        }
    }

    /**
     * Removes all TradeGood(s) from the cargoBay
     */

    public void clearCargo(){
        cargoBay.clear();
    }

    /**
     * Checks the cargo for a certain quantity of a TradeGood.
     * @param tg The TradeGood to check for in the cargo.
     * @param quantity Number of TradeGoods we need.

     * @return Whether or not the cargo contains enough of that TradeGood. */
    public boolean hasCargo(TradeGood tg, int quantity) {
        return (cargoBay.containsKey(tg) &&
                (cargoBay.get(tg) >= quantity));
    }

    /**
     * Method getCargoCount.
     * @param tg TradeGood
     * @return int
     */
    public int getCargoCount(TradeGood tg) {
        if(!cargoBay.containsKey(tg)) {
            return 0;
        }
        return cargoBay.get(tg);
    }

    /**
     * Method getCargoSpace.
     * @return int
     */
    public int getCargoSpace() {
        return cargosize - curCargoCount;
    }

    /**
     * Check to see if the cargo is full.

     * @return Is the cargo full? */
    public boolean isCargoFull() {
        return curCargoCount >= cargosize;
    }

    /**
     * Method getMaxFuel.
     * @return int
     */
    public int getMaxFuel(){
        return fuelsize;
    }

    /**
     * Method setFuel.
     * @param fuel int
     */
    public void setFuel(int fuel){
        currentFuel = fuel;
    }

    /**
     * Method getFuel.
     * @return int
     */
    public int getFuel(){
        return currentFuel;
    }
    
    /**
     * Method getPrice.
     * @return int
     */
    public int getPrice() {
        return price;
    }

    /**
     * Method toString.
     * @return String
     */
    public String toString() {
        return name;
    }
}
