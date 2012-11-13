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
 */
public class MarketPanel extends JPanel {
    private final GameController gc;
    private BufferedImage background;
    private HashMap<TradeGood, MarketItem> itemMap;
    private Market market;
    private Player player;
    private PlayerPanel playerPanel;

    private class MarketItem {
        private BufferedImage bimg;
        private Point loc;
        private Point dloc;

        private MarketItem(Point loc, BufferedImage bimg) {
            this.loc = loc;
            this.bimg = bimg;
            dloc = new Point(loc.x + 45, loc.y + 45);
        }
    }

    public MarketPanel(Market market, Player p, GameController gc) {
        this.setLayout(new BorderLayout());
        this.gc = gc;
        this.market = market;
        this.player = p;
        this.setupDisplay();
    }

    public void setPlayer(Player plr) {
        this.player = plr;
        playerPanel.updatePlayer(plr);
    }

    public void setPlanet(Planet p) {
        this.market = p.getMarket();
    }

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
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(400, 400));
        this.add(mainPanel, BorderLayout.CENTER);

        /* Player Panel */
        playerPanel = new PlayerPanel(GameController.State.MARKETPANEL, player, gc);
        this.add(playerPanel, BorderLayout.SOUTH);


        for (TradeGood good : market.getMarketGoods()) {
            BufferedImage im = null;
            Point p = new Point();
            try {
                im = ImageIO.read(new File("images/" + good + ".png"));
            } catch(Exception e) {
                e.printStackTrace();
            }
            switch(good.getType()) {
                case TradeGood.WATER:
                    p.x = 50;
                    p.y = 80;
                    break;
                case TradeGood.FOOD:
                    p.x = 200;
                    p.y = 80;
                    break;
                case TradeGood.FURS:
                    p.x = 350;
                    p.y = 80;
                    break;
                case TradeGood.GAMES:
                    p.x = 500;
                    p.y = 80;
                    break;
                case TradeGood.ORE:
                    p.x = 650;
                    p.y = 80;
                    break;
                case TradeGood.FIREARMS:
                    p.x = 50;
                    p.y = 200;
                    break;
                case TradeGood.MEDICINE:
                    p.x = 200;
                    p.y = 200;
                    break;
                case TradeGood.MACHINES:
                    p.x = 350;
                    p.y = 200;
                    break;
                case TradeGood.NARCOTICS:
                    p.x = 500;
                    p.y = 200;
                    break;
                case TradeGood.ROBOTS:
                    p.x = 650;
                    p.y = 200;
                    break;
                default :
                    p.x = 800;
                    p.y = 200;
                    break;
            }
            itemMap.put(good, new MarketItem(p, im));
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
        super.paint(g);

        /* We need Graphics2D for smooth drawings. */
        Graphics2D g2d = (Graphics2D) g;
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

    private class BuyListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

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
