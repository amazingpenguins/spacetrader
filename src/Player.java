/**
 * Player
 */
import java.awt.Point;

/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class Player implements java.io.Serializable {

    // DO NOT CHANGE THIS! If you do, it will prevent saving and loading. 
    /**
     * Field serialVersionUID.
     * (value is 3344125658693803362)
     */
    public static final long serialVersionUID = 3344125658693803362L;

    /**
     * Field s.
     */
    public final Stats s;

    /**
     * Field ship.
     */
    private SpaceShip ship;

    /**
     * Field location.
     */
    private Point location;

    /**
     * Field currentPlanet.
     */
    private Planet currentPlanet;

    /**
     * Constructor for Player.
     * @param s Stats
     */
    public Player(Stats s) {
        this.s = s;
        final int initcredits = 1000;
        this.s.setCredits(initcredits);
        location = new Point(0, 0);
    }

    /**
     * Method getShip.
     * @return SpaceShip
     */
    public SpaceShip getShip() {
        return ship;
    }

    /**
     * Method setShip.
     * @param ship SpaceShip
     */
    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    /**
     * Method addCredits.
     * @param dCredits int
     */
    public void addCredits(int dCredits) {
        s.setCredits(s.getCredits() + dCredits);
    }

    /**
     * Method getCredits.
     * @return int
     */
    public int getCredits() {
        return s.getCredits();
    }

    /**
     * Method getName.
     * @return String
     */
    public String getName() {
        return (s.getName() == null) ? "null" : s.getName();
    }

	/**
	 * Method toString.
	 * @return String
	 */
	public String toString() {
		return String.format("Name:     " + s.getName()     + "\n" +
				             "Credits:  " + s.getCredits()  + "\n" +
				             "Pilot:    " + s.getPilot()    + "\n" +
				             "Trader:   " + s.getTrader()   + "\n" +
				             "Fighter:  " + s.getFighter()  + "\n" +
				             "Engineer: " + s.getEngineer() + "\n" +
                             "Location: (" + location.x + ", " + location.y + ")");
	}

	/**
	 * Method getLocation.
	 * @return Point
	 */
	public Point getLocation(){
		return location;
	
	}
	
	/**
	 * Method setLocation.
	 * @param location Point
	 */
	public void setLocation(Point location){
		this.location = location;
	}

    /**
     * Method getPlanet.
     * @return Planet
     */
    public Planet getPlanet(){
        return currentPlanet;
    }

    /**
     * Method setPlanet.
     * @param p Planet
     */
    public void setPlanet(Planet p){
       currentPlanet = p;
    }
}
