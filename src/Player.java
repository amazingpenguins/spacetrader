import java.awt.*;

public class Player {
    private final int INITCREDITS = 1000;

    private Stats s;
    private SpaceShip ship;
    private Point location;
    private Planet currentPlanet;

    public Player(Stats s) {
        this.s = s.clone();
        this.s.setCredits(INITCREDITS);
        location = new Point(0, 0);
    }

    public SpaceShip getShip() {
        return ship;
    }

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public Stats getStats() {
        return s.clone();
    }

    public void addCredits(int dCredits) {
        s.setCredits(s.getCredits() + dCredits);
    }

    public int getCredits() {
        return s.getCredits();
    }



    public String getName() {
        return (s.getName() == null) ? "null" : s.getName();
    }

	public String toString() {
		return String.format("Name:     " + s.getName()     + "\n" +
				             "Credits:  " + s.getCredits()  + "\n" +
				             "Pilot:    " + s.getPilot()    + "\n" +
				             "Trader:   " + s.getTrader()   + "\n" +
				             "Fighter:  " + s.getFighter()  + "\n" +
				             "Engineer: " + s.getEngineer() + "\n" +
                             "Location: (" + location.x + ", " + location.y + ")");
	}
	public Point getLocation(){
		return location;
	
	}
	
	public void setLocation(Point location){
		this.location = location;
	}

    public Planet getPlanet(){
        return currentPlanet;
    }
    public void setPlanet(Planet p){
       currentPlanet = p;
    }
}
