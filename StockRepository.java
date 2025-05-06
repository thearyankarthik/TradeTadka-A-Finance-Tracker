import java.util.HashMap;
import java.util.Map;

public class StockRepository {
    private Map<String, Stock> stocks = new HashMap<>();

    public StockRepository() {
        // Add example stocks to the repository
        stocks.put("AMZN", new Stock("AMZN", "Amazon", 3400.00, 0));
        stocks.put("AAPL", new Stock("AAPL", "Apple", 150.00, 0));
        stocks.put("GOOGL", new Stock("GOOGL", "Google", 2800.00, 0));
        stocks.put("MSFT", new Stock("MSFT", "Microsoft", 300.00, 0));
    }

    public void saveStock(Stock stock) {
        stocks.put(stock.getStockSymbol(), stock);
    }

    public Stock getStockBySymbol(String stockSymbol) {
        return stocks.get(stockSymbol);
    }

    public void updateStockPrice(String stockSymbol, double newPrice) {
        Stock stock = stocks.get(stockSymbol);
        if (stock != null) {
            stock.setCurrentPrice(newPrice);
        }
    }

    public void deleteStock(String stockSymbol) {
        stocks.remove(stockSymbol);
    }
}
