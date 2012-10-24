/**
 * User: ryree0
 * Date: 10/22/12 | Time: 12:10 PM
 */
public class TradeGood implements Cloneable {
    public static final short ITEMCOUNT = 10;
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
    private int value;
    private short type;

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
    public int getValue() {
        return value;
    }
    public short getType() {
        return type;
    }
    public TradeGood clone() {
        try {
            return (TradeGood)super.clone();
        } catch(CloneNotSupportedException CNSE) {
            CNSE.printStackTrace();
        }
        return new TradeGood((short)-1);
    }
}
