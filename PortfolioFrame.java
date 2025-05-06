import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PortfolioFrame extends JFrame {
    private User user;
    private Portfolio portfolio;
    private UserRepository userRepository;
    private PortfolioRepository portfolioRepository;
    private StockRepository stockRepository;

    private JTextArea portfolioArea;
    private JTextField stockSymbolField, quantityField;

    public PortfolioFrame(User user) {
        this.user = user;
        this.userRepository = userRepository;
        this.portfolioRepository = new PortfolioRepository();
        this.stockRepository = new StockRepository();

        // Initialize portfolio or create a new one if not exists
        this.portfolio = portfolioRepository.getPortfolioByUserId(user.getUserID());
        if (portfolio == null) {
            portfolio = new Portfolio(1, user.getUserID(), user.getCashBalance());
            portfolioRepository.savePortfolio(portfolio);
        }

        initializeComponents();
        updatePortfolioDisplay();
    }

    // Initialize UI components
    private void initializeComponents() {
        setTitle("Portfolio - " + user.getUsername());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        portfolioArea = new JTextArea(10, 40);
        portfolioArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.add(new JScrollPane(portfolioArea));

        stockSymbolField = new JTextField(10);
        quantityField = new JTextField(5);

        JButton buyButton = new JButton("Buy");
        JButton sellButton = new JButton("Sell");

        buyButton.addActionListener(new BuyButtonListener());
        sellButton.addActionListener(new SellButtonListener());

        panel.add(new JLabel("Stock Symbol:"));
        panel.add(stockSymbolField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(buyButton);
        panel.add(sellButton);

        add(panel);
        setVisible(true);
    }

    // Update portfolio display with current holdings and cash balance
    private void updatePortfolioDisplay() {
        portfolioArea.setText("");
        portfolioArea.append("Portfolio for " + user.getUsername() + "\n");
        portfolioArea.append("Cash Balance: $" + user.getCashBalance() + "\n");
        portfolioArea.append("Holdings:\n");

        for (Stock stock : portfolio.getStockHoldings()) {
            portfolioArea.append(stock.toString() + "\n");
        }
    }

    // Listener for Buy button
    private class BuyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String symbol = stockSymbolField.getText();
            int quantity;

            try {
                quantity = Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PortfolioFrame.this, "Invalid quantity.");
                return;
            }

            Stock stock = stockRepository.getStockBySymbol(symbol);

            if (stock == null) {
                JOptionPane.showMessageDialog(PortfolioFrame.this, "Stock not found.");
                return;
            }

            double totalPrice = stock.getCurrentPrice() * quantity;
            if (user.getCashBalance() >= totalPrice) {
                user.updateCashBalance(-totalPrice);
                portfolio.updateHoldings(stock, quantity);
                updatePortfolioDisplay();
                JOptionPane.showMessageDialog(PortfolioFrame.this, "Purchase successful!");
            } else {
                JOptionPane.showMessageDialog(PortfolioFrame.this, "Insufficient funds.");
            }
        }
    }

    // Listener for Sell button
    private class SellButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String symbol = stockSymbolField.getText();
            int quantity;

            try {
                quantity = Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PortfolioFrame.this, "Invalid quantity.");
                return;
            }

            Stock stock = stockRepository.getStockBySymbol(symbol);

            if (stock == null || portfolio.getStockHoldings().stream().noneMatch(s -> s.getStockSymbol().equals(symbol) && s.getSharesOwned() >= quantity)) {
                JOptionPane.showMessageDialog(PortfolioFrame.this, "Insufficient shares or stock not found in holdings.");
                return;
            }

            double totalSale = stock.getCurrentPrice() * quantity;
            user.updateCashBalance(totalSale);
            portfolio.updateHoldings(stock, -quantity);
            updatePortfolioDisplay();
            JOptionPane.showMessageDialog(PortfolioFrame.this, "Sale successful!");
        }
    }
}
