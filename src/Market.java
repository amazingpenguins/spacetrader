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
        return myItems.get(t);
    }

    /**
     * Buy a TradeGood from a player.
     * @param p The player to buy the TradeGood from.
     * @param t The TradeGood to buy.
     */
    public void marketBuy(Player p, TradeGood t) {
        p.addCredits(myItems.get(t));

        //TODO Add in a check for player's ship cargo.
        //if(p.getShip().getCargo().get(t))
        // p.getShip().removeItem(t);
    }

    /**
     * Sell a TradeGood to a player.
     * @param p The player buying the TradeGood.
     * @param t The TradeGood the player wants.
     */
    public void marketSell(Player p, TradeGood t) {
        //TODO Make sure the player can fit this in their cargo.
        //TODO Make sure the player can afford this item.
        p.addCredits(-myItems.get(t));
    }

    /**
     * Calculate how much we value a TradeGood.
     * @param t The TradeGood to price.
     * @return Our current value of the TradeGood.
     */
    private int myPrice(TradeGood t) {
        switch(t.getType()) {
            case TradeGood.WATER:
                if(environment == Planet.DESERT)
                    return t.getValue() * 4;
                else if(environment == Planet.LOTSOFWATER)
                    return t.getValue() / 3;
                break;
            case TradeGood.FURS:
                if(environment == Planet.RICHFAUNA)
                    return t.getValue() / 10;
            case TradeGood.FOOD:
                if(environment == Planet.RICHSOIL)
                    return t.getValue() / 5;
                break;
            case TradeGood.ORE:
                if(environment == Planet.WARLIKE)
                    return t.getValue() * 30;
                else if(environment == Planet.MINERALRICH)
                    return t.getValue() / 20;
                break;
            case TradeGood.GAMES:
                if(environment == Planet.ARTISTIC)
                    return t.getValue() / 5;
                    break;
            case TradeGood.FIREARMS:
                if(environment == Planet.WARLIKE)
                    return t.getValue() / 100;
                break;
            case TradeGood.MEDICINE:
                if(environment == Planet.LOTSOFHERBS)
                    return t.getValue() / 10;
                break;
            case TradeGood.MACHINES:
                break;
            case TradeGood.NARCOTICS:
                if(environment == Planet.WEIRDMUSHROOMS)
                   return t.getValue() / 150;
                break;
            case TradeGood.ROBOTS:
                break;
            default:
                return t.getValue();
        }
        return t.getValue();
    }
}
