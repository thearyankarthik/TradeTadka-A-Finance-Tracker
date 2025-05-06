public class Stock {
    private String stockSymbol;
    private String companyName;
    private double currentPrice;
    private int sharesOwned;

    public Stock(String stockSymbol, String companyName, double currentPrice, int sharesOwned) {
        this.stockSymbol = stockSymbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
        this.sharesOwned = sharesOwned;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void updateStockPrice(double newPrice) {
        this.currentPrice = newPrice;
    }

    public int getSharesOwned() {
        return sharesOwned;
    }

    public void setSharesOwned(int sharesOwned) {
        this.sharesOwned = sharesOwned;
    }
    public String getStockSymbol() {
        return stockSymbol;
    }
    public void setCurrentPrice(double newPrice) {
        this.currentPrice = newPrice;
    }

    @Override
    public String toString() {
        return stockSymbol + " (" + companyName + "): " + sharesOwned + " shares at $" + currentPrice;
    }
}
