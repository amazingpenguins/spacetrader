/**
 * Market Panel
 */
package edu.gatech.amazingpenguins.spacetrader;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AmazingPenguins
 * @version 0.01
 */
public class MarketPanel extends JPanel {
    /**
     * Field serialVersionUID.
     * (value is 5382543667056746920)
     */
    private static final long serialVersionUID = 5382543667056746920L;

    /**
     * Field WATERPOINT.
     */
    private static final Point WATERPOINT = new Point(50, 80);
    
    /**
     * Field FOODPOINT.
     */
    private static final Point FOODPOINT = new Point(200, 80);
    
    /**
     * Field FURPOINT.
     */
    private static final Point FURSPOINT = new Point(350, 80);
    
    /**
     * Field GAMEPOINT.
     */
    private static final Point GAMESPOINT = new Point(500, 80);
    
    /**
     * Field OREPOINT.
     */
    private static final Point OREPOINT = new Point(650, 80);
   
    /**
     * Field FIREARMPOINT.
     */
    private static final Point FIREARMSPOINT = new Point(50, 200);
    
    /**
     * Field MEDICINEPOINT.
     */
    private static final Point MEDICINEPOINT = new Point(200, 200);
    
    /**
     * Field MACHINESPOINT.
     */
    private static final Point MACHINESPOINT = new Point(350, 200);
    
    /**
     * Field NARCOTICSPOINT.
     */
    private static final Point NARCOTICSPOINT = new Point(500, 200);
    
    /**
     * Field ROBOTSPOINT.
     */
    private static final Point ROBOTSPOINT = new Point(650, 200);
    
    /**
     * Field UNKNOWNPOINT.
     */
    private static final Point UNKNOWNPOINT = new Point(800, 400);
    
    /**
     * Field gc.
     */
    private final GameController gc;

    /**
     * Field background.
     */
    private BufferedImage myBackground;

    /**
     * Field itemMap.
     */
    private Map<TradeGood,MarketItem> itemMap;

    /**
     * Field market.
     */
    private Market market;

    /**
     * Field player.
     */
    private Player player;

    /**
     * Field playerPanel.
     */
    private PlayerPanel playerPanel;

    /**
     * @author AmazingPenguins
     * @version 0.01
     */
    private static class MarketItem {
        /**
         */
        private final BufferedImage bimg;

        /**
         * Field loc.
         */
        private final Point loc;

        /**
         * Field dloc.
         */
        private final Point dloc;

        /**
         * Constructor for MarketItem.
         * @param loc Point
         * @param bimg BufferedImage
         */
        private MarketItem(Point loc, BufferedImage bimg) {
            this.loc = loc;
            this.bimg = bimg;
            dloc = new Point(loc.x + 45, loc.y + 45);
        }
        
        /**
         * toString
         * @return String
         */
        @Override
        public String toString() {
            return "Market Item";
        }
    }

    /**
     * Constructor for MarketPanel.
     * @param market Market
     * @param p Player
     * @param gc GameController
     */
    public MarketPanel(Market market, Player p, GameController gc) {
        this.gc = gc;
        this.market = market;
        this.player = p;
        setupDisplay();
    }

    /**
     * Method setPlayer.
     * @param plr Player
     */
    public void setPlayer(Player plr) {
        player = plr;
        playerPanel.updatePlayer(plr);
    }

    /**
     * Method setPlanet.
     * @param p Planet
     */
    public void setPlanet(Planet p) {
        market = p.getMarket();
    }

    /**
     * Method setupDisplay.
     */
    private final void setupDisplay() {
        setLayout(new BorderLayout());
        itemMap = new HashMap<TradeGood, MarketItem>();
        myBackground = null;
        try {
            this.myBackground = ImageIO.read(new File("images/Background.png"));
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        addMouseListener(new BuyListener());
        setOpaque(false);

        /* Main Panel */
        final JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(400, 400));
        add(mainPanel, BorderLayout.CENTER);

        /* Player Panel */
        playerPanel = new PlayerPanel(GameController.State.MARKETPANEL, player, gc);
        add(playerPanel, BorderLayout.SOUTH);

        for (TradeGood good : market.getMarketGoods()) {
            BufferedImage im = null;
            Point location = locateGood(good);
            try {
                im = ImageIO.read(new File("images/" + good + ".png"));
            } catch(Exception e) {
                e.printStackTrace();
            }
            itemMap.put(good, new MarketItem(location, im));
        }
    }

    /**
     * Moves the item to the correct location.
     * @param tg the TradeGood
     * @return the new location
     */
    private Point locateGood(TradeGood tg) {
        switch(tg.getType()) {
            case TradeGood.WATER:
                return WATERPOINT;
            case TradeGood.FOOD:
                return FOODPOINT;
            case TradeGood.FURS:
                return FURSPOINT;
            case TradeGood.GAMES:
                return GAMESPOINT;
            case TradeGood.ORE:
                return OREPOINT;
            case TradeGood.FIREARMS:
                return FIREARMSPOINT;
            case TradeGood.MEDICINE:
                return MEDICINEPOINT;
            case TradeGood.MACHINES:
                return MACHINESPOINT;
            case TradeGood.NARCOTICS:
                return NARCOTICSPOINT;
            case TradeGood.ROBOTS:
                return ROBOTSPOINT;
            default :
                return UNKNOWNPOINT;
        }
    }
    
    /**
     * Method paint.
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(myBackground, 0, 0, this.getWidth(), this.getHeight(), null);
        super.paint(g);

        /* We need Graphics2D for smooth drawings. */
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        g2d.setColor(Color.WHITE);

        /* Draw all of the items and their quantities */
        for(TradeGood tg : itemMap.keySet()) {
            MarketItem mi = itemMap.get(tg);
            g2d.drawImage(mi.bimg, mi.loc.x, mi.loc.y, null);
            g2d.drawString(tg.toString() +  " $" + 
                            market.getPrice(tg), mi.loc.x, mi.loc.y + 60);
            g2d.drawString("Market: " + market.getQuantity(tg), mi.loc.x, mi.loc.y - 25);
            g2d.drawString("Player: " + player.getShip().getCargoCount(tg),
                            mi.loc.x, mi.loc.y - 10);
        }
    }

    /**
     * @author AmazingPenguins
     * @version 0.01
     */
    private class BuyListener extends MouseAdapter {
        /**
         * Method mousePressed.
         * @param e MouseEvent
         * @see java.awt.event.MouseListener#mousePressed(MouseEvent)
         */
        @Override
        public void mousePressed(MouseEvent e) {
            final int x = e.getX();
            final int y = e.getY();

            for(TradeGood tg : itemMap.keySet()) {
                MarketItem mi = itemMap.get(tg);
                if((x >= mi.loc.x && y >= mi.loc.y) && 
                        (x <= mi.dloc.x && y <= mi.dloc.y)) {
                    if(e.getButton() == MouseEvent.BUTTON3) {
                        market.marketBuy(player, tg, 1);
                        playerPanel.updatePlayerPanel();
                    } else {
                        market.marketSell(player, tg, 1);
                        playerPanel.updatePlayerPanel();
                    }
                    repaint();
                }
            }
        }
        
        /**
         * toString
         * @return String
         */
        @Override
        public String toString() {
            return "Buy Listener";
        }
    }
}
