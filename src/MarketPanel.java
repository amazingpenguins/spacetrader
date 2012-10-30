import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MarketPanel extends JPanel {
    private Market market;
    private Player plr;
    private JLabel credits;
    protected GameController gc;

	private HashMap<TradeGood, MarketItem> itemMap;

    private class MarketItem {
        private JButton buyButton;
        private JButton sellButton;
        private JLabel quantity;

        private MarketItem(JButton buyButton, JButton sellButton, JLabel quantity) {
            this.buyButton = buyButton;
            this.sellButton = sellButton;
            this.quantity = quantity;
        }
    }

	public MarketPanel(Market market, Player p, GameController gc) {
		this.setLayout(new BorderLayout());
        this.gc = gc;
		this.market = market;
        this.plr = p;

        this.setupDisplay();
	}

    public void setPlayer(Player plr) {
        this.plr = plr;
        credits.setText("Credits: $" + plr.getCredits());
    }

    public void setPlanet(Planet p) {
        this.market = new Market(p.getGovernment(), p.getEnvironment(), p.getTechLevel());
        for(TradeGood good : market.getMarketGoods())
            itemMap.get(good).quantity.setText("amount: " + market.getQuantity(good));
    }

    private void updatePanel() {
        for(TradeGood good : market.getMarketGoods())
            itemMap.get(good).quantity.setText("amount: " + market.getQuantity(good));
    }

	private void setupDisplay() {
        itemMap = new HashMap<TradeGood, MarketItem>();

        /* Main Panel */
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2));
        this.add(mainPanel, BorderLayout.WEST);

        /* Cargo Panel */
        JPanel cargoPanel = new JPanel();
        cargoPanel.setBorder(BorderFactory.createTitledBorder("Cargo Hold"));
        credits = new JLabel("Credits");
        cargoPanel.add(credits);
        this.add(cargoPanel, BorderLayout.EAST);

        /* Top Panel */
        JPanel topPanel = new JPanel();
        this.add(topPanel, BorderLayout.NORTH);

        /* Back Button */
        JButton backButton = new JButton("Back to Universe");
        backButton.addActionListener(new BackListener());
        backButton.setToolTipText("Not working; null pointer error");
        topPanel.add(backButton);


        for (TradeGood good : market.getMarketGoods()) {
			JButton sellButton = new JButton("Sell");
			JButton buyButton  = new JButton("Buy");

            JLabel typeLabel  = new JLabel(good.toString());
            JLabel priceLabel = new JLabel("price: $" + market.getPrice(good));
            JLabel quantity   = new JLabel("amount: " + market.getQuantity(good));

            JPanel itemPanel = new JPanel();

            itemMap.put(good, new MarketItem(buyButton, sellButton, quantity));

            sellButton.addActionListener(new GoodListener(false, good, quantity));
            buyButton.addActionListener(new GoodListener(true, good, quantity));

			itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
			itemPanel.add(typeLabel);
			itemPanel.add(new JSeparator(SwingConstants.VERTICAL));
            itemPanel.add(quantity);
            itemPanel.add(new JSeparator(SwingConstants.VERTICAL));
			itemPanel.add(priceLabel);
			itemPanel.add(new JSeparator(SwingConstants.VERTICAL));
			itemPanel.add(buyButton);
			itemPanel.add(sellButton);
            mainPanel.add(itemPanel, BorderLayout.WEST);
		}
	}

    private class GoodListener implements ActionListener {

        private boolean buyButton;
        private TradeGood tg;
        private JLabel quantityLabel;

        public GoodListener(boolean buyButton, TradeGood tg, JLabel quantityLabel) {
            this.buyButton = buyButton;
            this.tg = tg;
            this.quantityLabel = quantityLabel;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(buyButton) {
                market.marketSell(plr, tg, 1);
                quantityLabel.setText("amount: " + market.getQuantity(tg));
                System.out.println(market.getQuantity(tg));
                credits.setText("Credits: $" + plr.getCredits());

            } else {
                market.marketBuy(plr, tg, 1);
                quantityLabel.setText("amount: " + market.getQuantity(tg));
                System.out.println(market.getQuantity(tg));
                credits.setText("Credits: $" + plr.getCredits());
            }
        }
    }

    private class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            gc.goToState(GameController.State.GAMEPANEL);
        }
    }
}