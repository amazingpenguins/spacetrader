import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MarketPanel extends JPanel {
	private JPanel mainPanel, cargoPanel;
	private Market market;
    private Player plr;
	private ArrayList<JPanel> itemPanels;
	public ArrayList<TradeGood> dummyData;
	private boolean dummy;
    private JLabel credits;
    private JButton backButton;
    GameController gc;

	public MarketPanel(Market market, Player p, GameController gc) {
		this.setLayout(new BorderLayout());
        this.gc = gc;

		dummy = false;
		this.market = market;
        this.plr = p;

        this.setupDisplay();
	}

    public void setPlayer(Player plr) {
        this.plr = plr;
    }

	private void setupDummyData() {
		Random rand = new Random();
		dummyData = new ArrayList<TradeGood>();

		dummy = true;
		for (int i = 0; i < 200; i++) {
			short type = (short)rand.nextInt(TradeGood.ITEMCOUNT);
			dummyData.add(new TradeGood(type));
		}
	}

	private void setupDisplay() {
		itemPanels = new ArrayList<JPanel>();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2));
        this.add(mainPanel, BorderLayout.WEST);

        cargoPanel = new JPanel();
        this.add(cargoPanel, BorderLayout.EAST);
        cargoPanel.setBorder(BorderFactory.createTitledBorder("Cargo Hold"));
        JLabel cargo = new JLabel("Cargo goes here");
        cargoPanel.add(cargo);
        if (plr != null) {
            credits = new JLabel("Credits: $"+plr.getCredits());
            cargoPanel.add(credits);
        }

        JPanel topPanel = new JPanel();
        this.add(topPanel, BorderLayout.NORTH);
        JButton backButton = new JButton("Back to Universe");

        BackListener back = new BackListener();
        back.gc = gc;
        backButton.addActionListener(new BackListener());
        backButton.setEnabled(false);
        backButton.setToolTipText("Not working; null pointer error");

        topPanel.add(backButton);


        for (TradeGood good : market.getMarketGoods()) {
			JButton sellButton = new JButton("Sell");
			JButton buyButton = new JButton("Buy");


			JLabel typeLabel = new JLabel(good.toString());
			JLabel priceLabel = new JLabel("price: $" + market.getPrice(good));
			JLabel quantity = new JLabel("amount: " + market.getQuantity(good));

            JPanel itemPanel = new JPanel();

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
			itemPanels.add(itemPanel);
			//this.add(itemPanel);
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
                //credits.setText(new String("Credits: $"+plr.getCredits()));

            }
            else {
                market.marketBuy(plr, tg, 1);
                quantityLabel.setText("amount: " + market.getQuantity(tg));
                System.out.println(market.getQuantity(tg));
                //credits.setText(new String("Credits: $"+plr.getCredits()));
            }
        }
    }

    private class BackListener implements ActionListener {
        protected GameController gc;

        public void actionPerformed(ActionEvent event) {
            gc.goToState(GameController.State.MAINMENU);
        }
    }
}