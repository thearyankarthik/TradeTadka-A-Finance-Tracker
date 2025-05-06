import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private int portfolioID;
    private int userID;
    private List<Stock> stockHoldings;
    private double cashBalance;

    public Portfolio(int portfolioID, int userID, double cashBalance) {
        this.portfolioID = portfolioID;
        this.userID = userID;
        this.stockHoldings = new ArrayList<>();
        this.cashBalance = cashBalance;
    }

    public void viewHoldings() {
        for (Stock stock : stockHoldings) {
            System.out.println(stock);
        }
    }

    public void updateHoldings(Stock stock, int quantity) {
        stock.setSharesOwned(stock.getSharesOwned() + quantity);
        stockHoldings.add(stock);
    }

    public double calculateTotalValue() {
        double totalValue = cashBalance;
        for (Stock stock : stockHoldings) {
            totalValue += stock.getCurrentPrice() * stock.getSharesOwned();
        }
        return totalValue;
    }

    public int getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(int portfolioID) {
        this.portfolioID = portfolioID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Stock> getStockHoldings() {
        return stockHoldings;
    }

    public void setStockHoldings(List<Stock> stockHoldings) {
        this.stockHoldings = stockHoldings;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }
}

