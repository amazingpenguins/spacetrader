public class Stats implements Cloneable, java.io.Serializable {

    // DO NOT CHANGE THIS! If you do, it will prevent saving and loading. 
    public static final long serialVersionUID = 8678497835531038348L;

    private boolean amNPC; // PC or Player?
    private short pilot,
                  trader,
                  fighter,
                  engineer;
    private int credits;
    private String name;

    public Stats() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public short getEngineer() {
        return engineer;
    }

    public void setEngineer(short engineer) {
        this.engineer = engineer;
    }

    public short getFighter() {
        return fighter;
    }

    public void setFighter(short fighter) {
        this.fighter = fighter;
    }

    public short getTrader() {
        return trader;
    }

    public void setTrader(short trader) {
        this.trader = trader;
    }

    public short getPilot() {
        return pilot;
    }

    public void setPilot(short pilot) {
        this.pilot = pilot;
    }

    public boolean isAmNPC() {
        return amNPC;
    }

    public void setAmNPC(boolean amNPC) {
        this.amNPC = amNPC;
    }

    public Stats clone() {
        try {
            return (Stats)super.clone();
        } catch (CloneNotSupportedException CNSE) {
            CNSE.printStackTrace();
        }
        return new Stats();
    }
}
