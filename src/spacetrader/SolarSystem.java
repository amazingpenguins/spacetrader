package spacetrader; /**
 * Solar System
 */
import java.awt.Point;

/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class SolarSystem implements java.io.Serializable {

    // DO NOT CHANGE THIS! If you do, it will prevent saving and loading. 
    /**
     * Field serialVersionUID.
     * (value is -7550559303342254510)
     */
    public static final long serialVersionUID = -7550559303342254510L;

    /**
     * Field government.
     */
    private int government;

    /**
     * Field techLevel.
     */
    private int techLevel;

    /**
     * Field myLocation.
     */
    private final Point myLocation;

    /**
     * Field myPlanet.
     */
    private Planet myPlanet;

    /**
     * Field GOVCOUNT
     * (value is 16)
     */
    private static final short GOVCOUNT = 16;
    
    /**
     * Field THEOCRACY.
     * (value is 15)
     */
    /**
     * Field TECHNOCRACY.
     * (value is 14)
     */
    /**
     * Field SATORI.
     * (value is 13)
     */
    /**
     * Field SOCIALIST.
     * (value is 12)
     */
    /**
     * Field PACIFIST.
     * (value is 11)
     */
    /**
     * Field MONARCHY.
     * (value is 10)
     */
    /**
     * Field MILITARY.
     * (value is 9)
     */
    /**
     * Field FEUDAL.
     * (value is 8)
     */
    /**
     * Field FASCIST.
     * (value is 7)
     */
    /**
     * Field DICTATORSHIP.
     * (value is 6)
     */
    /**
     * Field CYBERNETIC.
     * (value is 5)
     */
    /**
     * Field CORPORATE.
     * (value is 4)
     */
    /**
     * Field CONFEDERACY.
     * (value is 3)
     */
    /**
     * Field COMMUNIST.
     * (value is 2)
     */
    /**
     * Field CAPITALIST.
     * (value is 1)
     */
    /**
     * Field ANARCHY.
     * (value is 0)
     */
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

    /**
     * Field TECHCOUNT
     * (value is 8)
     */
    private static final short TECHCOUNT = 8;
    
    /**
     * Field HITECH.
     * (value is 7)
     */
    /**
     * Field POSTINDUSTRIAL.
     * (value is 6)
     */
    /**
     * Field INDUSTRIAL.
     * (value is 5)
     */
    /**
     * Field EARLYINDUSTRIAL.
     * (value is 4)
     */
    /**
     * Field RENAISSANCE.
     * (value is 3)
     */
    /**
     * Field MEDIEVAL.
     * (value is 2)
     */
    /**
     * Field AGRICULTURE.
     * (value is 1)
     */
    /**
     * Field PREAGRICULTURE.
     * (value is 0)
     */
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
     * @param mp Point
     */
    public SolarSystem(short government, short techLevel, Planet p, Point mp) {
        this.government = government;
        this.techLevel = techLevel;
        myPlanet = p;
        myLocation = mp;
        setupSolarSystem();
    }

    /**
     * Generates a random SolarSystem.
     * @param location Point
     */
    public SolarSystem(Point location) {
    	myLocation = location;
    	setupSolarSystem();
    }
    
    /**
     * 
     */
    private void setupSolarSystem() {
        generatePlanets();
        government = (int) (Math.random() * GOVCOUNT);
        techLevel  = (int) (Math.random() * TECHCOUNT);
    }

    /**
     * Method getGovernment.
     * @return int
     */
    public int getGovernment() {
        return government;
    }

    /**
     * Method getTechLevel.
     * @return int
     */
    public int getTechLevel() {
        return techLevel;
    }

    /**
     * Method generatePlanets.
     */
    public void generatePlanets() {
        myPlanet = new Planet(government, techLevel, myLocation, this);
    }

    /**
     * Method getPlanet.
     * @return Planet
     */
    public Planet getPlanet(){
        return myPlanet;
    }

    /**
     * Method govString.
     * @return String
     */
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

    /**
     * Method techString.
     * @return String
     */
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

    /**
     * Method toString.
     * @return String
     */
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
