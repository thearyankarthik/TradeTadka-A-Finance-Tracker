import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();

    public void saveTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactionsByPortfolioId(int portfolioID) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getPortfolioID() == portfolioID) {
                result.add(transaction);
            }
        }
        return result;
    }

    public void deleteTransaction(int transactionID) {
        transactions.removeIf(transaction -> transaction.getTransactionID() == transactionID);
    }
}
