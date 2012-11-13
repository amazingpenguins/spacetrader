import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: ryree0
 * Date: 10/22/12 | Time: 12:10 PM
 */
public class Market implements java.io.Serializable {

    // DO NOT CHANGE THIS! If you do, it will prevent saving and loading. 
    public static final long serialVersionUID = -983351089925190472L;


    private final int environment;
    private final Map<TradeGood, MarketItem> myItems;

    private class MarketItem implements Serializable {
        private int value;
        private int quantity;

        private MarketItem(int value, int quantity) {
            this.value = value;
            this.quantity = quantity;
        }
    }

    public Market(int government, int environment, int techLevel) {
        this.environment = environment;
        myItems = new HashMap<TradeGood,MarketItem>();
        for(int i = 0; i < TradeGood.ITEMCOUNT; i++) {
            TradeGood curGood = new TradeGood((short)i);
            myItems.put(curGood, new MarketItem(myPrice(curGood), (int)(Math.random() * 20)));
        }
    }

    /**
     * Calculate the price of a TradeGood.
     * @param t The TradeGood to price.
     * @return TradeGood's value.
     */
    public int getPrice(TradeGood t) {
        return myItems.containsKey(t) ?
                myItems.get(t).value : 0;
    }

    public int getQuantity(TradeGood t) {
        return myItems.containsKey(t) ?
                myItems.get(t).quantity : 0;
    }

    /**
     * Buy a quantity of TradeGood(s) from a player.
     * @param p The player to buy the TradeGood from.
     * @param t The TradeGood to buy.
     * @param quantity Number of TradeGood(s) to buy.
     */
    public void marketBuy(Player p, TradeGood t, int quantity) {
        if(!myItems.containsKey(t) || (p == null))
            return;

        if(p.getShip().containsCargo(t, quantity)) {
            if(p.getShip().removeCargo(t, quantity)) {
                p.addCredits(myItems.get(t).value * quantity);
                myItems.get(t).quantity += quantity;
            }
        }
    }

    /**
     * Sell a quantity of TradeGood(s) to a player.
     * @param p The player buying the TradeGood.
     * @param t The TradeGood the player wants.
     * @param quantity The amount of TradeGood(s) to sell.
     */
    public void marketSell(Player p, TradeGood t, int quantity) {
        if(!myItems.containsKey(t) || (p == null))
            return;

        if(!p.getShip().cargoFull() &&
                (p.getCredits() >= (myItems.get(t).value * quantity)) &&
                (myItems.get(t).quantity >= quantity)) {
            p.getShip().addCargo(t, quantity);
            p.addCredits(-(myItems.get(t).value * quantity));
            myItems.get(t).quantity -= quantity;
        }
    }

    /**
    * Getter for the TradeGoods map in the market.. 
    * @return Map Map of tradegoods
    *
    */
    public Set<TradeGood> getMarketGoods() {
        return this.myItems.keySet();
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
