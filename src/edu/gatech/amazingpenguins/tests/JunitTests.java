package edu.gatech.amazingpenguins.tests;
import edu.gatech.amazingpenguins.spacetrader.*;
import org.junit.Test;
import org.junit.Assert;

/**
 * Run JUnit tests on the code.
 * @author AmazingPenguins
 * @version 0.01
 */
public class JunitTests {
	
	public JunitTests() {}
	
    /**
     * Field GOOD_INCREASE.
     */
    private static final int GOOD_INCREASE = 10;
    
    /**
     * Field GOOD_DECREASE.
     */
    private static final int GOOD_DECREASE = 5;

    /**
     * Tests to make sure the Market properly behaves when
     * selling a TradeGood to a player.
     * author: Ryan R
     */
    @Test
    public void testMarketSell() {
        int prevQuantity;
        boolean wasFull;
        Stats s = new Stats();
        Player p = new Player(s);
        Market m = new Market(0, 1, 2);
        TradeGood[] tradeGoods = new TradeGood[TradeGood.ITEMCOUNT];
        for(int i = 0; i < tradeGoods.length; i++) {
            tradeGoods[i] = new TradeGood((short)i);
        }

        /* Using the GNAT for testing, 15 cargo slots. */
        p.setShip(new SpaceShip(SpaceShip.GNAT));
        p.addCredits(1000000);

        prevQuantity = m.getQuantity(tradeGoods[0]);
        m.marketSell(null, tradeGoods[0], 1);
        assert(prevQuantity == m.getQuantity(tradeGoods[0]));
        m.marketSell(p, tradeGoods[0], 100);
        assert(prevQuantity == m.getQuantity(tradeGoods[0]));

        for (TradeGood tradeGood : tradeGoods) {
            wasFull = p.getShip().isCargoFull();
            prevQuantity = m.getQuantity(tradeGood);
            if(wasFull) {
               p.getShip().clearCargo();
               assert(!p.getShip().isCargoFull());
            }
            if (prevQuantity > 0) {
                m.marketSell(p, tradeGood, m.getQuantity(tradeGood));
                if (wasFull) {
                    assert (prevQuantity == m.getQuantity(tradeGood));
                } else {
                    assert (prevQuantity < m.getQuantity(tradeGood));
                }
            }
        }
        System.out.println("Market selling test successfully passed!");
    }

private void verifyIncrease(TradeGood tg, Market market) {
     Assert.assertTrue("Failing on " + market + " and " + tg,
        GOOD_INCREASE*tg.getValue() == market.myPrice(tg));
}

private void verifyDecrease(TradeGood tg, Market market) {
    Assert.assertTrue("Failing on " + market + " and " + tg,
        tg.getValue()/GOOD_DECREASE == market.myPrice(tg));
}
/**
 * Test myMyprice method. 
 * Scott Daner
 * sdaner3
 */
@Test
public void testMyPrice() {
    for (int i = 0; i < TradeGood.ITEMCOUNT; i++) {
        Market market;
        TradeGood tg = new TradeGood((short) i);
        switch(tg.getType()) {
            case TradeGood.WATER:
                market = new Market(0, Planet.DESERT, 0);
                verifyIncrease(tg, market);
                market = new Market(0, Planet.LOTSOFWATER, 0);
                verifyDecrease(tg, market);
                break;
            case TradeGood.FURS:
                market = new Market(0, Planet.RICHFAUNA, 0);
                verifyDecrease(tg, market);
                market = new Market(0, 0, SolarSystem.PREAGRICULTURE);
                verifyIncrease(tg, market);
                break;
            case TradeGood.FOOD:
                market = new Market(0, Planet.RICHSOIL, 0);
                verifyDecrease(tg, market);
                break;
            case TradeGood.ORE:
                market = new Market(0, Planet.WARLIKE, 0);
                verifyIncrease(tg, market);
                market = new Market(0, Planet.MINERALRICH, 0);
                verifyDecrease(tg, market);
                break;
            case TradeGood.GAMES:
                market = new Market(0, Planet.ARTISTIC, 0);
                verifyDecrease(tg, market);
                market = new Market(0, Planet.MINERALRICH, SolarSystem.HITECH);
                verifyIncrease(tg, market);
                break;
            case TradeGood.FIREARMS:
                market = new Market(0,Planet.WARLIKE, 0);
                verifyDecrease(tg, market);
                market = new Market(SolarSystem.FEUDAL, 0, 0);
                verifyIncrease(tg, market);
                break;
            case TradeGood.MEDICINE:
                market = new Market(0,Planet.LOTSOFHERBS, 0);
                verifyDecrease(tg, market);
                market = new Market(0,Planet.WARLIKE, 0);
                verifyIncrease(tg, market);
                break;
            case TradeGood.MACHINES:
                market = new Market(0,Planet.LIFELESS, 0);
                verifyDecrease(tg, market);
                market = new Market(SolarSystem.CAPITALIST, 0, 0);
                verifyIncrease(tg, market);
                market = new Market(0, 0, SolarSystem.INDUSTRIAL);
                verifyIncrease(tg, market);
                break;
            case TradeGood.NARCOTICS:
                market = new Market(0,Planet.WEIRDMUSHROOMS,0);
                verifyDecrease(tg, market);
                market = new Market(SolarSystem.THEOCRACY, 0, 0);
                verifyIncrease(tg, market);
                market = new Market(0, Planet.ARTISTIC, 0);
                verifyIncrease(tg, market);
                market = new Market(SolarSystem.THEOCRACY, Planet.ARTISTIC, 0);
                verifyIncrease(tg, market);
                break;
            case TradeGood.ROBOTS:
                market = new Market(0,0,SolarSystem.HITECH);
                verifyDecrease(tg, market);
                market = new Market(SolarSystem.INDUSTRIAL,0,0);
                verifyIncrease(tg, market);
                market = new Market(0, 0, SolarSystem.AGRICULTURE);
                verifyIncrease(tg, market);
                break;
            default:
                System.out.printf("Something is really wrong...");
            }
        }
    }
}
