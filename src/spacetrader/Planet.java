package spacetrader; /**
 * Planet
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class Planet implements java.io.Serializable {
    /**
     * Field serialVersionUID.
     * (value is 5751844421646783820)
     */
    private static final long serialVersionUID = 5751844421646783820L;

    /**
     * Field name.
     */
    private final String name;

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
     * Field location.
     */
    private final Point location;

    /**
     * Field solarSystem.
     */
    private final SolarSystem solarSystem;

    /**
     * Field market.
     */
    private final Market market;

    /**
     * Field ENVCOUNT.
     */
    private static final short ENVCOUNT = 12;
    
    /**
     * Field WARLIKE.
     * (value is 11)
     */
    /**
     * Field ARTISTIC.
     * (value is 10)
     */
    /**
     * Field LOTSOFHERBS.
     * (value is 9)
     */
    /**
     * Field WEIRDMUSHROOMS.
     * (value is 8)
     */
    /**
     * Field LIFELESS.
     * (value is 7)
     */
    /**
     * Field RICHFAUNA.
     * (value is 6)
     */
    /**
     * Field RICHSOIL.
     * (value is 5)
     */
    /**
     * Field LOTSOFWATER.
     * (value is 4)
     */
    /**
     * Field DESERT.
     * (value is 3)
     */
    /**
     * Field MINERALPOOR.
     * (value is 2)
     */
    /**
     * Field MINERALRICH.
     * (value is 1)
     */
    /**
     * Field NORESOURCES.
     * (value is 0)
     */
    public static final short NORESOURCES    = 0,
                              MINERALRICH    = 1,
                              MINERALPOOR    = 2,
                              DESERT         = 3,
                              LOTSOFWATER    = 4,
                              RICHSOIL       = 5,
                              RICHFAUNA      = 6,
                              LIFELESS       = 7,
                              WEIRDMUSHROOMS = 8,
                              LOTSOFHERBS    = 9,
                              ARTISTIC       = 10,
                              WARLIKE        = 11;

    /* Planet Names */
    /**
     * Field planetName.
     */
    private final String[] planetName = new String[] {
            "Acamar",
            "Adahn",
            "Aldea",
            "Andevian",
            "Antedi",
            "Balosnee",
            "Baratas",
            "Brax",
            "Bretel",
            "Calondia",
            "Campor",
            "Capelle",
            "Carzon",
            "Castor",
            "Cestus",
            "Cheron",
            "Courteney",
            "Daled",
            "Damast",
            "Davlos",
            "Deneb",
            "Deneva",
            "Devidia",
            "Draylon",
            "Drema",
            "Endor",
            "Esmee",
            "Exo",
            "Ferris",
            "Festen",
            "Fourmi",
            "Frolix",
            "Gemulon",
            "Guinifer",
            "Hades",
            "Hamlet",
            "Helena",
            "Hulst",
            "Iodine",
            "Iralius",
            "Janus",
            "Japori",
            "Jarada",
            "Jason",
            "Kaylon",
            "Khefka",
            "Kira",
            "Klaatu",
            "Klaestron",
            "Korma",
            "Kravat",
            "Krios",
            "Laertes",
            "Largo",
            "Lave",
            "Ligon",
            "Lowry",
            "Magrat",
            "Malcoria",
            "Melina",
            "Mentar",
            "Merik",
            "Mintaka",
            "Montor",
            "Mordan",
            "Myrthe",
            "Nelvana",
            "Nix",
            "Nyle",
            "Odet",
            "Og",
            "Omega",
            "Omphalos",
            "Orias",
            "Othello",
            "Parade",
            "Penthara",
            "Picard",
            "Pollux",
            "Quator",
            "Rakhar",
            "Ran",
            "Regulas",
            "Relva",
            "Rhymus",
            "Rochani",
            "Rubicum",
            "Rutia",
            "Sarpeidon",
            "Sefalla",
            "Seltrice",
            "Sigma",
            "Sol",
            "Somari",
            "Stakoron",
            "Styris",
            "Talani",
            "Tamus",
            "Tantalos",
            "Tanuga",
            "Tarchannen",
            "Terosa",
            "Thera",
            "Titan",
            "Torin",
            "Triacus",
            "Turkana",
            "Tyrus",
            "Umberlee",
            "Utopia",
            "Vadera",
            "Vagra",
            "Vandor",
            "Ventax",
            "Xenon",
            "Xerxes",
            "Yew",
            "Yojimbo",
            "Zalkon",
            "Zuul"
    };

    /**
     * Generate a random planet.
     * @param government SolarSystem parent's government.
     * @param techLevel SolarSystem parent's tech level.
     * @param location Point
     * @param solarSystem SolarSystem
     */
    public Planet(int government, int techLevel, 
                    Point location, SolarSystem solarSystem) {
        this.government = government;
        this.techLevel = techLevel;
        environment = (int) (Math.random() * ENVCOUNT);
        name = planetName[(int) (Math.random() * planetName.length)];
        market = new Market(this.government, this.environment, this.techLevel);
        this.location = location;
        this.solarSystem = solarSystem;
    }

    /**
     * Method getEnvironment.
     * @return int
     */
    public int getEnvironment() {
        return environment;
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
     * Method getSolarSystem.
     * @return SolarSystem
     */
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    /**
     * Method envString.
     * @return String
     */
    public String envString() {
        switch(environment) {
            case NORESOURCES:
                return "No Resources";
            case MINERALPOOR:
                return "Mineral Poor";
            case MINERALRICH:
                return "Mineral Rich";
            case DESERT:
                return "Desert";
            case LOTSOFWATER:
                return "Lots of Water";
            case RICHSOIL:
                return "Rich Soil";
            case RICHFAUNA:
                return "Rich Fauna";
            case LIFELESS:
                return "Lifeless";
            case WEIRDMUSHROOMS:
                return "Weird Mushrooms";
            case LOTSOFHERBS:
                return "Lots of Herbs";
            case ARTISTIC:
                return "Artistic";
            case WARLIKE:
                return "War-like";
            default:
                return "";
        }
    }

    /**
     * Method toString.
     * @return String
     */
    public String toString() {
        return name;
    }

    /**
    * Method to draw a planet at a location. Creates a random colored Planet. 
    * Planet radius will be 10 pixels.
    * @param g Graphics context to draw in 
    * @param x the x coordinate of the upper left corner of the planet to be displayed.
    * @param y the y coordinate of the upper left corner of the planet to be displayed.
    */
    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.drawString(name, x, y);
    }

    /**
     * Method getMarket.
     * @return Market
     */
    public Market getMarket() {
        return market;
    }

    /**
     * Method getLocation.
     * @return Point
     */
    public Point getLocation() {
    	return location;
    }
}

