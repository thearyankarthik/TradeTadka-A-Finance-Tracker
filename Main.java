public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        UserRepository userRepository = new UserRepository();
        PortfolioRepository portfolioRepository = new PortfolioRepository();
        StockRepository stockRepository = new StockRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        // Create a user and portfolio
        User user = new User(1, "user1", "pass123", "user1@example.com", 10000);
        userRepository.saveUser(user);
        Portfolio portfolio = new Portfolio(1, user.getUserID(), user.getCashBalance());
        portfolioRepository.savePortfolio(portfolio);

        // Create a stock and save it in the stock repository
        Stock appleStock = new Stock("AAPL", "Apple Inc.", 150.00, 0);
        stockRepository.saveStock(appleStock);

        // Simulate buying stock
        int quantity = 10;
        double totalPrice = appleStock.getCurrentPrice() * quantity;
        if (user.getCashBalance() >= totalPrice) {
            portfolio.updateHoldings(appleStock, quantity);
            user.updateCashBalance(-totalPrice);
            Transaction transaction = new Transaction(1, portfolio.getPortfolioID(), "Buy", appleStock.getStockSymbol(), quantity, appleStock.getCurrentPrice());
            transactionRepository.saveTransaction(transaction);
        }

        // Print user portfolio and transaction history
        user.viewPortfolio(portfolio);
        System.out.println("Transaction history:");
        for (Transaction transaction : transactionRepository.getTransactionsByPortfolioId(portfolio.getPortfolioID())) {
            System.out.println(transaction);
        }
    }
}
