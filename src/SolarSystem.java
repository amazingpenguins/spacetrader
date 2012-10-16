public class SolarSystem {
    private int government;
    private int techLevel;
    private Planet myPlanet;

    //NOTE this is EC
    /* Government Types */
    private final short GOVCOUNT = 16;
    public static final short ANARCHY      = 0,
                              CAPITALIST   = 1,
                              COMMUNIST    = 2,
                              CONFEDERACY  = 3,
                              CORPORATE    = 4,
                              CYBERNETIC   = 5,
                              DICTATORSHIP = 6,
                              FASCIST      = 7,
                              FEUDAL       = 8,
                              MILITARY     = 9,
                              MONARCHY     = 10,
                              PACIFIST     = 11,
                              SOCIALIST    = 12,
                              SATORI       = 13,
                              TECHNOCRACY  = 14,
                              THEOCRACY    = 15;

    /* Tech Levels */
    private final short TECHCOUNT = 8;
    public static final short PREAGRICULTURE  = 0,
                              AGRICULTURE     = 1,
                              MEDIEVAL        = 2,
                              RENAISSANCE     = 3,
                              EARLYINDUSTRIAL = 4,
                              INDUSTRIAL      = 5,
                              POSTINDUSTRIAL  = 6,
                              HITECH          = 7;

    /**
     * Construct a specific SolarSystem.
     * @param government SolarSystem government.
     * @param techLevel SolarSystem techLevel.
     * @param p Planet contained within this SolarSystem.
     */
    public SolarSystem(short government, short techLevel, Planet p) {
        this.government = government;
        this.techLevel = techLevel;
        myPlanet = p;
    }

    /**
     * Generates a random SolarSystem.
     */
    public SolarSystem() {
        generatePlanets();
        government = (int)(Math.random() * GOVCOUNT);
        techLevel  = (int)(Math.random() * TECHCOUNT);
    }

    public int getGovernment() {
        return government;
    }

    public int getTechLevel() {
        return techLevel;
    }

    public void generatePlanets() {
        myPlanet = new Planet(government, techLevel);
    }

    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }

    public void setGovernment(int government) {
        this.government = government;
    }

    public String toString() {
        return String.format("SolarSystem {\n" +
                             "\tGovernment: " + getGovernment() + "\n" +
                             "\tTechLevel:  " + getTechLevel()  + "\n" +
                             "\tPlanet:     " + myPlanet        + "\n" +
                             "\tPlanet Env: " + myPlanet.getEnvironment() + "\n" +
                             "}");
    }
}
