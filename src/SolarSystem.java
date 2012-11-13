import java.awt.*;

public class SolarSystem implements java.io.Serializable {

    // DO NOT CHANGE THIS! If you do, it will prevent saving and loading. 
    public static final long serialVersionUID = -7550559303342254510L;

    private int government;
    private int techLevel;
    private final Point myLocation;
    private Planet myPlanet;

    private static final short ANARCHY      = 0,
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
    public SolarSystem(short government, short techLevel, Planet p, Point mp) {
        this.government = government;
        this.techLevel = techLevel;
        myPlanet = p;
        myLocation = mp;
    }

    /**
     * Generates a random SolarSystem.
     */
    public SolarSystem(Point location) {
        short GOVCOUNT = 16;
        short TECHCOUNT = 8;
    	myLocation = location;
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
        myPlanet = new Planet(government, techLevel, myLocation, this);
    }
    public Planet getPlanet(){
        return myPlanet;
    }

    public String govString() {
        switch(government) {
            case ANARCHY:
                return "Anarchy";
            case CAPITALIST:
                return "Capitalist";
            case COMMUNIST:
                return "Communist";
            case CONFEDERACY:
                return "Confederacy";
            case CORPORATE:
                return "Corporate";
            case CYBERNETIC:
                return "Cybernetic";
            case DICTATORSHIP:
                return "Dictatorship";
            case FASCIST:
                return "Fascist";
            case FEUDAL:
                return "Feudal";
            case MILITARY:
                return "Military";
            case MONARCHY:
                return "Monarchy";
            case PACIFIST:
                return "Pacifist";
            case SOCIALIST:
                return "Socialist";
            case SATORI:
                return "Satori";
            case TECHNOCRACY:
                return "Technocracy";
            case THEOCRACY:
                return "Theocracy";
            default:
                return "";
        }
    }

    public String techString() {
        switch(techLevel) {
            case PREAGRICULTURE:
                return "Pre-agriculture";
            case AGRICULTURE:
                return "Agriculture";
            case MEDIEVAL:
                return "Medieval";
            case RENAISSANCE:
                return "Renaissance";
            case EARLYINDUSTRIAL:
                return "Early Industrial";
            case INDUSTRIAL:
                return "Industrial";
            case POSTINDUSTRIAL:
                return "Post-industrial";
            case HITECH:
                return "Hi-Tech";
            default:
                return "";
        }
    }

    public String toString() {
        return String.format("SolarSystem {\n" +
                             "\tLocation:   " + "(" + myLocation.getX() + ","  +
                                      myLocation.getY() + ")"           + "\n" +
                             "\tGovernment: " + govString()             + "\n" +
                             "\tTechLevel:  " + techString()            + "\n" +
                             "\tPlanet:     " + myPlanet                + "\n" +
                             "\tPlanet Env: " + myPlanet.envString()    + "\n" +
                             "}");
    }
}
