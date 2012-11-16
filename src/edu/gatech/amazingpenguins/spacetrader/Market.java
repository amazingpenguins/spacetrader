/**
 * Market
 */
package edu.gatech.amazingpenguins.spacetrader; 
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class Market implements java.io.Serializable {

    // DO NOT CHANGE THIS! If you do, it will prevent saving and loading. 
    /**
     * Field serialVersionUID.
     * (value is -983351089925190472)
     */
    public static final long serialVersionUID = -983351089925190472L;

    /**
     * Field environment.
     */
    private final int environment;

    /**
     * Field government.
     */
    private final int government;

    /**
     * Field techLevel.
     */
    private final int techLevel;

    /**
     * Field myItems.
     */
    private final Map<TradeGood, MarketItem> myItems;
    
    /**
     * Field random.
     */
    private final Random random;

    /**
     * Field GOOD_INCREASE.
     */
    private static final int GOOD_INCREASE = 10;
    
    /**
     * Field GOOD_DECREASE.
     */
    private static final int GOOD_DECREASE = 5;
    
    /**
     * @author AmazingPenguins
     * @version 0.01
     */
    private static class MarketItem implements java.io.Serializable {
        /**
         * Field serialVersionUID.
         * (value is -5884235755886394603)
         */
        private static final long serialVersionUID = -5884235755886394603L;

        /**
         * Field value.
         */
        private final int value;

        /**
         * Field quantity.
         */
        private int quantity;

        /**
         * Constructor for MarketItem.
         * @param value int
         * @param quantity int
         */
        private MarketItem(int value, int quantity) {
            this.value = value;
            this.quantity = quantity;
        }
        
        /**
         * toString
         * @return String
         */
        @Override
        public String toString() {
            return "Market Item";
        }
    }

    /**
     * Constructor for Market.
     * @param government int
     * @param environment int
     * @param techLevel int
     */
    public Market(int government, int environment, int techLevel) {
        this.government = government;
        this.environment = environment;
        this.techLevel = techLevel;
        random = new Random();
        myItems = new HashMap<TradeGood,MarketItem>();
        for(int i = 0; i < TradeGood.ITEMCOUNT; i++) {
            TradeGood curGood = new TradeGood((short) i);
            myItems.put(curGood, 
                    new MarketItem(myPrice(curGood), random.nextInt(20)));
        }
    }

    /**
     * Calculate the price of a TradeGood.
     * @param t The TradeGood to price.
     * @return TradeGood's value. */
    public int getPrice(TradeGood t) {
        return myItems.containsKey(t) ?
                myItems.get(t).value : 0;
    }

    /**
     * Method getQuantity.
     * @param t TradeGood
     * @return int
     */
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
        if(!myItems.containsKey(t) || (p == null)) {
            return;
        }

        if(p.getShip().hasCargo(t, quantity)) {
                p.getShip().removeCargo(t, quantity);
                p.addCredits(myItems.get(t).value * quantity);
                myItems.get(t).quantity += quantity;
        }
    }

    /**
     * Sell a quantity of TradeGood(s) to a player.
     * @param p The player buying the TradeGood.
     * @param t The TradeGood the player wants.
     * @param quantity The amount of TradeGood(s) to sell.
     */
    public void marketSell(Player p, TradeGood t, int quantity) {
        if(!myItems.containsKey(t) || (p == null)) {
            return;
        }

        if(!p.getShip().isCargoFull() &&
                (p.getCredits() >= (myItems.get(t).value * quantity)) &&
                (myItems.get(t).quantity >= quantity)) {
                p.getShip().addCargo(t, quantity);
                p.addCredits(-(myItems.get(t).value * quantity));
                myItems.get(t).quantity -= quantity;
        }
    }

    /**
    * Getter for the TradeGoods map in the market.. 
    *
    * @return Map Map of tradegoods */
    public Set<TradeGood> getMarketGoods() {
        return this.myItems.keySet();
    }

    /**
     * Calculate how much we value a TradeGood.
     * @param tg The TradeGood to price.
     * @return Our current value of the TradeGood. */
    public int myPrice(TradeGood tg) {
        switch(tg.getType()) {
            case TradeGood.WATER:
                if(environment == Planet.DESERT){
                    return increasePrice(tg);
                } else if(environment == Planet.LOTSOFWATER) {
                    return decreasePrice(tg);
                }
                break;
            case TradeGood.FURS:
                if(environment == Planet.RICHFAUNA) {
                    return decreasePrice(tg);
                } else if(environment == Planet.DESERT || 
                        techLevel == SolarSystem.PREAGRICULTURE) {
                    return increasePrice(tg);
                }
                break;
            case TradeGood.FOOD:
                if(environment == Planet.RICHSOIL) {
                    return decreasePrice(tg);
                }
                break;
            case TradeGood.ORE:
                if(environment == Planet.WARLIKE) {
                    return increasePrice(tg);
                } else if(environment == Planet.MINERALRICH) {
                    return decreasePrice(tg);
                }
                break;
            case TradeGood.GAMES:
                if(environment == Planet.ARTISTIC) {
                    return decreasePrice(tg);
                } else if(techLevel == SolarSystem.HITECH) {
                    return increasePrice(tg);
                }
                    break;
            case TradeGood.FIREARMS:
                if(environment == Planet.WARLIKE) {
                    return decreasePrice(tg);
                } else if(government == SolarSystem.FEUDAL) {
                    return increasePrice(tg);
                }
                break;
            case TradeGood.MEDICINE:
                if(environment == Planet.LOTSOFHERBS) {
                    return decreasePrice(tg);
                } else if(environment == Planet.WARLIKE) {
                    return increasePrice(tg);
                }
                break;
            case TradeGood.MACHINES:
                if(environment == Planet.LIFELESS) {
                    return decreasePrice(tg);
                } else if(government == SolarSystem.CAPITALIST || 
                          techLevel == SolarSystem.INDUSTRIAL) {
                    return increasePrice(tg);
                }
                break;
            case TradeGood.NARCOTICS:
                if(environment == Planet.WEIRDMUSHROOMS) {
                   return decreasePrice(tg);
                } else if(government == SolarSystem.THEOCRACY || 
                          environment == Planet.ARTISTIC) {
                    return increasePrice(tg);
                }
                break;
            case TradeGood.ROBOTS:
                if(techLevel == SolarSystem.HITECH) {
                    return decreasePrice(tg);
                } else if(government == SolarSystem.INDUSTRIAL || 
                          techLevel == SolarSystem.AGRICULTURE) {
                    return increasePrice(tg);
                }
                break;
            default:
                return tg.getValue();
        }
        return tg.getValue();
    }
    
    /**
     * Increases the price of the goods.
     * @param tg The TradeGood to process.
     * @return The new value.
     */
    private int increasePrice(TradeGood tg) {
        return tg.getValue() * GOOD_INCREASE;
    }
    
    /**
     * Decrease the price of the goods.
     * @param tg The TradeGood to process.
     * @return The new value.
     */
    private int decreasePrice(TradeGood tg) {
        return tg.getValue() / GOOD_DECREASE;
    }
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return  "Market: {Government: " + government + 
                "Environment: " + environment +
                "Tech Level: " + techLevel + "}";
    }
}
