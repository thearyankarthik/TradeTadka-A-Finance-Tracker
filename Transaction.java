import java.util.Date;

public class Transaction {
    private int transactionID;
    private int portfolioID;
    private String transactionType;
    private String stockSymbol;
    private int quantity;
    private double priceAtTransaction;
    private Date date;

    public Transaction(int transactionID, int portfolioID, String transactionType, String stockSymbol, int quantity, double priceAtTransaction) {
        this.transactionID = transactionID;
        this.portfolioID = portfolioID;
        this.transactionType = transactionType;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.priceAtTransaction = priceAtTransaction;
        this.date = new Date();
    }
    public int getPortfolioID() {
        return portfolioID;
    }
    public int getTransactionID() {
        return transactionID;
    }


    @Override
    public String toString() {
        return transactionType + ": " + quantity + " shares of " + stockSymbol + " at $" + priceAtTransaction + " on " + date;
    }
}
