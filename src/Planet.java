public class Planet {
    private String name;
    private int environment;
    private int government;
    private int techLevel;

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

    public String toString() {
        return name;
    }
}
