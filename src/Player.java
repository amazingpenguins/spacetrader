
public class Player {
    private final int INITCREDITS = 1000;

    private Stats s;
    private SpaceShip ship;

    public Player(Stats s) {
        this.s = s.clone();
        this.s.setCredits(INITCREDITS);
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

	public String toString() {
		return String.format("Player {\n" +
				             "\tName:     " + s.getName()     + "\n" +
				             "\tCredits:  " + s.getCredits()  + "\n" +
				             "\tShip:     " + getShip()       + "\n" +
				             "\tPilot:    " + s.getPilot()    + "\n" +
				             "\tTrader:   " + s.getTrader()   + "\n" +
				             "\tFighter:  " + s.getFighter()  + "\n" +
				             "\tEngineer: " + s.getEngineer() + "\n" +
				             "}");
	}
}
