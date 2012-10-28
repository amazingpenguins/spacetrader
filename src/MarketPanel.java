import javax.swing.*;
import java.awt.GridLayout;
import java.util.*;

public class MarketPanel extends JPanel {
	private JPanel mainPanel;
	private Market market;
	private ArrayList<JPanel> itemPanels;
	public ArrayList<TradeGood> dummyData;
	private boolean dummy;

	public MarketPanel(Market market) {
		this.setLayout(new GridLayout(0, 2));
		dummy = false;
		this.market = market;
		this.setupDisplay();
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
		for (TradeGood good : market.getTradegoods().keySet()) {
			JButton sellButton = new JButton("Sell");
			JButton buyButton = new JButton("Buy");
			JLabel typeLabel = new JLabel(good.toString());
			JLabel priceLabel = new JLabel("$" + market.calcPrice(good));
			JLabel quantity = new JLabel("" + good.getQuantity());
			
			JPanel itemPanel = new JPanel();
			
			itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
			itemPanel.add(typeLabel);
			itemPanel.add(new JSeparator(SwingConstants.VERTICAL));
			itemPanel.add(priceLabel);
			itemPanel.add(new JSeparator(SwingConstants.VERTICAL));
			itemPanel.add(buyButton);
			itemPanel.add(sellButton);
			itemPanels.add(itemPanel);
			this.add(itemPanel);
		}

	}
}