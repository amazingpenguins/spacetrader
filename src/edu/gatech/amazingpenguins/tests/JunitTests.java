/**
 * JUnit Testing File.
 */
package edu.gatech.amazingpenguins.tests;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import edu.gatech.amazingpenguins.spacetrader.Market;
import edu.gatech.amazingpenguins.spacetrader.Planet;
import edu.gatech.amazingpenguins.spacetrader.Player;
import edu.gatech.amazingpenguins.spacetrader.SolarSystem;
import edu.gatech.amazingpenguins.spacetrader.SpaceShip;
import edu.gatech.amazingpenguins.spacetrader.Stats;
import edu.gatech.amazingpenguins.spacetrader.TradeGood;
import edu.gatech.amazingpenguins.spacetrader.GameController;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Run JUnit tests on the code.
 * @author AmazingPenguins
 * @version 0.01
 */
public class JunitTests {

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
        final Player player = new Player(new Stats());
        final Market market = new Market(0, 1, 2);
        final TradeGood[] tradeGoods = new TradeGood[TradeGood.ITEMCOUNT];
        for(int i = 0; i < tradeGoods.length; i++) {
            tradeGoods[i] = new TradeGood((short) i);
        }

        /* Using the GNAT for testing, 15 cargo slots. */
        player.setShip(new SpaceShip(SpaceShip.GNAT));
        player.addCredits(1000000);

        prevQuantity = market.getQuantity(tradeGoods[0]);
        market.marketSell(null, tradeGoods[0], 1);
        assertTrue("Market null check failed.", 
                prevQuantity == market.getQuantity(tradeGoods[0]));
        market.marketSell(player, tradeGoods[0], 100);
        assertTrue("Market over sell failed.", 
                prevQuantity == market.getQuantity(tradeGoods[0]));

        for (TradeGood tradeGood : tradeGoods) {
            wasFull = player.getShip().isCargoFull();
            prevQuantity = market.getQuantity(tradeGood);
            if(wasFull) {
               player.getShip().clearCargo();
               assertTrue("Cargo clear failed.", 
                       !player.getShip().isCargoFull());
            }
            if (prevQuantity > 0) {
                market.marketSell(player, tradeGood, market.getQuantity(tradeGood));
                if (wasFull) {
                    assertTrue ("Cargo full check failed.", 
                            prevQuantity == market.getQuantity(tradeGood));
                } else {
                    assertTrue ("Market quanity check failed.",
                            prevQuantity > market.getQuantity(tradeGood));
                }
            }
        }
        System.out.println("Market selling test successfully passed!");
    }

/**
 * Verify the increase
 * @param tg The TradeGood
 * @param market The Market
 */
private void verifyIncrease(TradeGood tg, Market market) {
     assertTrue("Failing on " + market + " and " + tg,
        GOOD_INCREASE * tg.getValue() == market.myPrice(tg));
}

/**
 * Verify the decrease
 * @param tg The TradeGood
 * @param market The Market
 */
private void verifyDecrease(TradeGood tg, Market market) {
    assertTrue("Failing on " + market + " and " + tg,
        tg.getValue() / GOOD_DECREASE == market.myPrice(tg));
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
                market = new Market(0, Planet.WARLIKE, 0);
                verifyDecrease(tg, market);
                market = new Market(SolarSystem.FEUDAL, 0, 0);
                verifyIncrease(tg, market);
                break;
            case TradeGood.MEDICINE:
                market = new Market(0, Planet.LOTSOFHERBS, 0);
                verifyDecrease(tg, market);
                market = new Market(0, Planet.WARLIKE, 0);
                verifyIncrease(tg, market);
                break;
            case TradeGood.MACHINES:
                market = new Market(0, Planet.LIFELESS, 0);
                verifyDecrease(tg, market);
                market = new Market(SolarSystem.CAPITALIST, 0, 0);
                verifyIncrease(tg, market);
                market = new Market(0, 0, SolarSystem.INDUSTRIAL);
                verifyIncrease(tg, market);
                break;
            case TradeGood.NARCOTICS:
                market = new Market(0, Planet.WEIRDMUSHROOMS, 0);
                verifyDecrease(tg, market);
                market = new Market(SolarSystem.THEOCRACY, 0, 0);
                verifyIncrease(tg, market);
                market = new Market(0, Planet.ARTISTIC, 0);
                verifyIncrease(tg, market);
                market = new Market(SolarSystem.THEOCRACY, Planet.ARTISTIC, 0);
                verifyIncrease(tg, market);
                break;
            case TradeGood.ROBOTS:
                market = new Market(0, 0, SolarSystem.HITECH);
                verifyDecrease(tg, market);
                market = new Market(SolarSystem.INDUSTRIAL, 0, 0);
                verifyIncrease(tg, market);
                market = new Market(0, 0, SolarSystem.AGRICULTURE);
                verifyIncrease(tg, market);
                break;
            default:
                System.out.println("Something is really wrong...");
            }
        }
    }
    
    /**
     * Tests if the generateUniverse method works properly
     * Chris Shaw did this.
     */
    @Test
    public void testGenerateUniverse(){
        GameController gc1 = new GameController();
        GameController gc2 = new GameController();
        GameController gc3 = new GameController();
        List<Planet> list1 = gc1.getPlanets();
        List<Planet> list2 = gc2.getPlanets();
        List<Planet> list3 = gc3.getPlanets();
        
        assertNotNull(gc1);
        assertNotNull(gc2);
        assertNotNull(gc3);
        assertNotNull(list1);
        assertNotNull(list2);
        assertNotNull(list3);
        
        assertNotSame(gc1, gc2);
        assertNotSame(gc2, gc3);
        assertNotSame(gc3, gc1);
        assertNotSame(list1, list2);
        assertNotSame(list2, list3);
        assertNotSame(list3, list1);
        
        for(Object planet : list1){
            for(int i=0; i<25; i++){
                assertNotSame(planet, list1.get(i));
            }
        }
        for(Object planet : list2){
            for(int i=0; i<25; i++){
                assertNotSame(planet, list2.get(i));
            }
        }
        for(Object planet : list3){
            for(int i=0; i<25; i++){
                assertNotSame(planet, list3.get(i));
            }
        }
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "Junit Tests";
    }
    
    /**
     * Tests to make sure the Market properly behaves when
     * buying a TradeGood to a player.
     * author: Adam Szaruga
     */
    @Test
    public void testMarketBuy() {
    	
        Market market = new Market(0, 1, 2);
        Stats stats = new Stats();
        Player player = new Player(stats);
        int prevQuantity = 0;
        int prevCredits = 0;
        
        TradeGood[] tradeGoods = new TradeGood[TradeGood.ITEMCOUNT];
        for(int i = 0; i < tradeGoods.length; i++) {
            tradeGoods[i] = new TradeGood((short)i);
            
        }

        
        /* Using the GNAT for testing, 15 cargo slots. */
        player.setShip(new SpaceShip(SpaceShip.GNAT));
        player.addCredits(100);
        for (TradeGood t : tradeGoods){
        	player.getShip().addCargo(t, 5);
        }
        
        /* test that nothing happens when player is null */
        
        for (TradeGood t : tradeGoods){
        	prevQuantity = market.getQuantity(t);
        	market.marketBuy(null, t, 1);
        	assert(prevQuantity == market.getQuantity(t));
        	assert(player.getCredits() == 100);
        }
        
        /* test that nothing happens when the ship doesn't have the trade good */
        player.getShip().clearCargo();
        for (TradeGood t : tradeGoods){
        	prevQuantity = market.getQuantity(t);
        	market.marketBuy(player, t, 1);
        	assert(prevQuantity == market.getQuantity(t));
        	assert(player.getCredits() == 100);
        }
        
        /* test that the player get the right amount of credits added */
        for (TradeGood t : tradeGoods){
        	player.getShip().addCargo(t, 1);
        	prevCredits = player.getCredits();
        	market.marketBuy(player, t, 1);
        	assert(player.getCredits() == prevCredits + market.getPrice(t));
        }
        
        /* test that the right amount of trade goods get added to the market */
        for (TradeGood t : tradeGoods){
        	player.getShip().addCargo(t, 1);
        	prevQuantity = market.getQuantity(t);
        	market.marketBuy(player, t, 1);
        	assert(prevQuantity +1  == market.getQuantity(t));
        }
        
    }
    
}
