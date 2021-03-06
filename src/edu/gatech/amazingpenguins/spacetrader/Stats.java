/**
 * Stats
 */
package edu.gatech.amazingpenguins.spacetrader;

/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class Stats implements java.io.Serializable {

    // DO NOT CHANGE THIS! If you do, it will prevent saving and loading. 
    /**
     * Field serialVersionUID.
     * (value is 8678497835531038348)
     */
    public static final long serialVersionUID = 8678497835531038348L;

    /**
     * Field engineer.
     */
    /**
     * Field fighter.
     */
    /**
     * Field trader.
     */
    /**
     * Field pilot.
     */
    private short pilot,
                  trader,
                  fighter,
                  engineer;

    /**
     * Field credits.
     */
    private int credits;

    /**
     * Field name.
     */
    private String name;

    /**
     * Method getName.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method setName.
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method getCredits.
     * @return int
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Method setCredits.
     * @param credits int
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Method getEngineer.
     * @return short
     */
    public short getEngineer() {
        return engineer;
    }

    /**
     * Method setEngineer.
     * @param engineer short
     */
    public void setEngineer(short engineer) {
        this.engineer = engineer;
    }

    /**
     * Method getFighter.
     * @return short
     */
    public short getFighter() {
        return fighter;
    }

    /**
     * Method setFighter.
     * @param fighter short
     */
    public void setFighter(short fighter) {
        this.fighter = fighter;
    }

    /**
     * Method getTrader.
     * @return short
     */
    public short getTrader() {
        return trader;
    }

    /**
     * Method setTrader.
     * @param trader short
     */
    public void setTrader(short trader) {
        this.trader = trader;
    }

    /**
     * Method getPilot.
     * @return short
     */
    public short getPilot() {
        return pilot;
    }

    /**
     * Method setPilot.
     * @param pilot short
     */
    public void setPilot(short pilot) {
        this.pilot = pilot;
    }
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "Stats";
    }
}
