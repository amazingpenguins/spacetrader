package tests;
import spacetrader.*;

/**
 * Run JUnit tests on the code.
 * @author AmazingPenguins
 * @version 0.01
 */
public class JunitTests {
    /**
     * Tests to make sure the Market properly behaves when
     * selling a TradeGood to a player.
     * author: Ryan R
     */
    private static void testMarketSell() {
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

    public static void main(String[] args) {
        testMarketSell();
    }
}
