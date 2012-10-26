import java.util.HashMap;
import java.util.Map;

/**
 * User: ryree0
 * Date: 10/22/12 | Time: 12:10 PM
 */
public class Market {
    private int government;
    private int environment;
    private int techLevel;
    private Map<TradeGood, Integer> myItems;

    public Market(int government, int environment, int techLevel) {
        this.government = government;
        this.environment = environment;
        this.techLevel = techLevel;
        myItems = new HashMap<TradeGood,Integer>();
        for(int i = 0; i < TradeGood.ITEMCOUNT; i++) {
            TradeGood curGood = new TradeGood((short)i);
            myItems.put(curGood, myPrice(curGood));
        }
    }

    /**
     * Calculate the price of a TradeGood.
     * @param t The TradeGood to price.
     * @return TradeGood's value.
     */
    public int calcPrice(TradeGood t) {
        return myItems.containsKey(t) ?
                myItems.get(t) : 0;
    }

    /**
     * Buy a quantity of TradeGood(s) from a player.
     * @param p The player to buy the TradeGood from.
     * @param t The TradeGood to buy.
     * @param quantity Number of TradeGood(s) to buy.
     */
    public void marketBuy(Player p, TradeGood t, int quantity) {
        if(!myItems.containsKey(t))
            return;

        if(p.getShip().containsCargo(t, quantity)) {
            if(p.getShip().removeCargo(t, quantity))
                p.addCredits(myItems.get(t) * quantity);
        }
    }

    /**
     * Sell a quantity of TradeGood(s) to a player.
     * @param p The player buying the TradeGood.
     * @param t The TradeGood the player wants.
     * @param quantity The amount of TradeGood(s) to sell.
     */
    public void marketSell(Player p, TradeGood t, int quantity) {
        if(!myItems.containsKey(t))
            return;

        if(!p.getShip().cargoFull() &&
                (p.getCredits() >= (myItems.get(t) * quantity))) {
            p.getShip().addCargo(t, quantity);
            p.addCredits(-(myItems.get(t) * quantity));
        }
    }

    /**
     * Calculate how much we value a TradeGood.
     * @param tg The TradeGood to price.
     * @return Our current value of the TradeGood.
     */
    private int myPrice(TradeGood tg) {
        switch(tg.getType()) {
            case TradeGood.WATER:
                if(environment == Planet.DESERT)
                    return tg.getValue() * 4;
                else if(environment == Planet.LOTSOFWATER)
                    return tg.getValue() / 3;
                break;
            case TradeGood.FURS:
                if(environment == Planet.RICHFAUNA)
                    return tg.getValue() / 10;
            case TradeGood.FOOD:
                if(environment == Planet.RICHSOIL)
                    return tg.getValue() / 5;
                break;
            case TradeGood.ORE:
                if(environment == Planet.WARLIKE)
                    return tg.getValue() * 30;
                else if(environment == Planet.MINERALRICH)
                    return tg.getValue() / 20;
                break;
            case TradeGood.GAMES:
                if(environment == Planet.ARTISTIC)
                    return tg.getValue() / 5;
                    break;
            case TradeGood.FIREARMS:
                if(environment == Planet.WARLIKE)
                    return tg.getValue() / 100;
                break;
            case TradeGood.MEDICINE:
                if(environment == Planet.LOTSOFHERBS)
                    return tg.getValue() / 10;
                break;
            case TradeGood.MACHINES:
                break;
            case TradeGood.NARCOTICS:
                if(environment == Planet.WEIRDMUSHROOMS)
                   return tg.getValue() / 150;
                break;
            case TradeGood.ROBOTS:
                break;
            default:
                return tg.getValue();
        }
        return tg.getValue();
    }
}
