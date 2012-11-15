/**
 * Trade Good
 */
package edu.gatech.amazingpenguins.spacetrader;
import java.io.Serializable;

/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class TradeGood implements Serializable {
    /**
     * Field serialVersionUID.
     * (value is -3138429268487822800)
     */
    private static final long serialVersionUID = -3138429268487822800L;

    /**
     * Field ITEMCOUNT.
     * (value is 10)
     */
    public static final short ITEMCOUNT = 10;

    /**
     * Field ROBOTS.
     * (value is 9)
     */
    /**
     * Field NARCOTICS.
     * (value is 8)
     */
    /**
     * Field MACHINES.
     * (value is 7)
     */
    /**
     * Field MEDICINE.
     * (value is 6)
     */
    /**
     * Field FIREARMS.
     * (value is 5)
     */
    /**
     * Field GAMES.
     * (value is 4)
     */
    /**
     * Field ORE.
     * (value is 3)
     */
    /**
     * Field FOOD.
     * (value is 2)
     */
    /**
     * Field FURS.
     * (value is 1)
     */
    /**
     * Field WATER.
     * (value is 0)
     */
    public static final short WATER     = 0,
                              FURS      = 1,
                              FOOD      = 2,
                              ORE       = 3,
                              GAMES     = 4,
                              FIREARMS  = 5,
                              MEDICINE  = 6,
                              MACHINES  = 7,
                              NARCOTICS = 8,
                              ROBOTS    = 9;

    /**
     * Field value.
     */
    private final int value;

    /**
     * Field type.
     */
    private final short type;

    /**
     * Instantiate a TradeGood of a given type.
     * @param type TradeGood.<TYPE> for type of TradeGood.
     */
    public TradeGood(short type) {
        this.type = type;
        switch(type) {
            case WATER:
                value = 30;
                break;
            case FURS:
                value = 250;
                break;
            case FOOD:
                value = 100;
                break;
            case ORE:
                value = 350;
                break;
            case GAMES:
                value = 250;
                break;
            case FIREARMS:
                value = 1250;
                break;
            case MEDICINE:
                value = 650;
                break;
            case MACHINES:
                value = 900;
                break;
            case NARCOTICS:
                value = 3500;
                break;
            case ROBOTS:
                value = 5000;
                break;
            default:
                value = 0;
        }
    }

    /**
     * Method getValue.
     * @return int
     */
    public int getValue() {
        return value;
    }

    /**
     * Method getType.
     * @return short
     */
    public short getType() {
        return type;
    }

    /**
     * Method hashCode.
     * @return int
     */
    @Override
    public int hashCode() {
        return type;
    }

    /**
     * Method equals.
     * @param obj Object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TradeGood) {
            return type == ((TradeGood) obj).getType();
        } else {
            return false;
        }
    }

    /**
     * Method toString.
     * @return String
     */
    @Override
    public String toString() {
        switch(type) {
            case WATER:
                return "Water";
            case FURS:
                return "Furs";
            case FOOD:
                return "Food";
            case ORE:
                return "Ore";
            case GAMES:
                return "Games";
            case FIREARMS:
                return "Firearms";
            case MEDICINE:
                return "Medicine";
            case MACHINES:
                return "Machines";
            case NARCOTICS:
                return "Narcotics";
            case ROBOTS:
                return "Robots";
            default:
                return "Unkown";
        }
    }
}
