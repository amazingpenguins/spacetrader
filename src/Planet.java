import java.awt.Graphics;
import java.awt.Color;
import java.util.*;

public class Planet {
    private Market market;
    private String name;
    private int environment;
    private int government;
    private int techLevel;
    private Color color;

    public final static int PLANET_SIZE = 20;

    /* Planet Environment */
    private final short ENVCOUNT = 12;
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
    String[] planetName = new String[] {
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
     * Create a specific planet.
     * @param name Planet name.
     * @param environment Planet environment.
     * @param government Planet government (should mirror SolarSystem parent).
     * @param techLevel Planet tech level (should mirror SolarSystem parent).
     */
    public Planet(String name, int environment, int government, int techLevel) {
        this.name = name;
        this.environment = environment;
        this.government  = government;
        this.techLevel   = techLevel;
    }

    /**
     * Generate a random planet.
     * @param government SolarSystem parent's government.
     * @param techLevel SolarSystem parent's tech level.
     */
    public Planet(int government, int techLevel) {
        this.government = government;
        this.techLevel = techLevel;
        environment = (int)(Math.random() * ENVCOUNT);
        name = planetName[(int)(Math.random() * planetName.length)];
        market = new Market(this.government, this.environment, this.techLevel);
    }

    public int getEnvironment() {
        return environment;
    }

    public int getGovernment() {
        return government;
    }

    public int getTechLevel() {
        return techLevel;
    }

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

    public String toString() {
        return name;
    }

    /*
    * Methods for drawing the Planet in a GUI
    */

    /**
    * Method to draw a planet at a location. Creates a random colored Planet. 
    * Planet radius will be 10 pixels.
    * @param g Graphics context to draw in 
    * @param x the x coordinate of the upper left corner of the planet to be displayed.
    * @param y the y coordinate of the upper left corner of the planet to be displayed.
    */
    public void draw(Graphics g, int x, int y) {
        // generate random values for rgb
        if (this.color == null) {
            Random random = new Random();
            float red = random.nextFloat();
            float green = random.nextFloat();
            float blue = random.nextFloat();

            // create color 
            this.color = new Color(red,green,blue);
        }
        g.setColor(this.color);
        // fill the circle for the planet
        g.fillOval(x, y, PLANET_SIZE, PLANET_SIZE);

        g.setColor(Color.BLACK);
        g.drawString(name, x, y);
    }

    public Market getMarket() {
        return market;
    }

}

