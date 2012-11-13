import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * User: ryree0
 * Date: 11/2/12
 * Time: 4:10 PM
 * @author ryree0
 * @version $Revision: 1.0 $
 */
public class MarketPanel extends JPanel {
    /**
     * Field serialVersionUID.
     * (value is 5382543667056746920)
     */
    private static final long serialVersionUID = 5382543667056746920L;

    /**
     * Field gc.
     */
    private final GameController gc;

    /**
     * Field background.
     */
    private BufferedImage background;

    /**
     * Field itemMap.
     */
    private HashMap<TradeGood, MarketItem> itemMap;

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
     */
    private static class MarketItem {
        /**
         * Field bimg.
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
    }

    /**
     * Constructor for MarketPanel.
     * @param market Market
     * @param p Player
     * @param gc GameController
     */
    public MarketPanel(Market market, Player p, GameController gc) {
        this.setLayout(new BorderLayout());
        this.gc = gc;
        this.market = market;
        this.player = p;
        this.setupDisplay();
    }

    /**
     * Method setPlayer.
     * @param plr Player
     */
    public void setPlayer(Player plr) {
        this.player = plr;
        playerPanel.updatePlayer(plr);
    }

    /**
     * Method setPlanet.
     * @param p Planet
     */
    public void setPlanet(Planet p) {
        this.market = p.getMarket();
    }

    /**
     * Method setupDisplay.
     */
    private void setupDisplay() {
        itemMap = new HashMap<TradeGood, MarketItem>();
        background = null;
        try {
            this.background = ImageIO.read(new File("images/Background.png"));
        } catch(IOException IOE) {
            IOE.printStackTrace();
        }
        this.addMouseListener(new BuyListener());
        this.setOpaque(false);

        /* Main Panel */
        final JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(400, 400));
        this.add(mainPanel, BorderLayout.CENTER);

        /* Player Panel */
        playerPanel = new PlayerPanel(GameController.State.MARKETPANEL, player, gc);
        this.add(playerPanel, BorderLayout.SOUTH);

        for (TradeGood good : market.getMarketGoods()) {
            BufferedImage im = null;
            Point location = new Point();
            try {
                im = ImageIO.read(new File("images/" + good + ".png"));
            } catch(Exception e) {
                e.printStackTrace();
            }
            switch(good.getType()) {
                case TradeGood.WATER:
                    location.x = 50;
                    location.y = 80;
                    break;
                case TradeGood.FOOD:
                    location.x = 200;
                    location.y = 80;
                    break;
                case TradeGood.FURS:
                    location.x = 350;
                    location.y = 80;
                    break;
                case TradeGood.GAMES:
                    location.x = 500;
                    location.y = 80;
                    break;
                case TradeGood.ORE:
                    location.x = 650;
                    location.y = 80;
                    break;
                case TradeGood.FIREARMS:
                    location.x = 50;
                    location.y = 200;
                    break;
                case TradeGood.MEDICINE:
                    location.x = 200;
                    location.y = 200;
                    break;
                case TradeGood.MACHINES:
                    location.x = 350;
                    location.y = 200;
                    break;
                case TradeGood.NARCOTICS:
                    location.x = 500;
                    location.y = 200;
                    break;
                case TradeGood.ROBOTS:
                    location.x = 650;
                    location.y = 200;
                    break;
                default :
                    location.x = 800;
                    location.y = 200;
                    break;
            }
            itemMap.put(good, new MarketItem(location, im));
        }
    }

    /**
     * Method paint.
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
        super.paint(g);

        /* We need Graphics2D for smooth drawings. */
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        g2d.setColor(Color.WHITE);

        /* Draw all of the items and their quantities */
        for(TradeGood tg : itemMap.keySet()) {
            MarketItem mi = itemMap.get(tg);
            g2d.drawImage(mi.bimg, mi.loc.x, mi.loc.y, null);
            g2d.drawString(tg.toString() +  " $" + market.getPrice(tg), mi.loc.x, mi.loc.y + 60);
            g2d.drawString("Market: " + market.getQuantity(tg), mi.loc.x, mi.loc.y - 25);
            g2d.drawString("Player: " + player.getShip().getCargoCount(tg), mi.loc.x, mi.loc.y - 10);
        }
    }

    /**
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
                if((x >= mi.loc.x && y >= mi.loc.y) && (x <= mi.dloc.x && y <= mi.dloc.y)) {
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
    }
}
