import java.util.HashMap;
import java.util.Map;

public class PortfolioRepository {
    private Map<Integer, Portfolio> portfolios = new HashMap<>();

    public void savePortfolio(Portfolio portfolio) {
        portfolios.put(portfolio.getUserID(), portfolio);
    }

    public Portfolio getPortfolioByUserId(int userID) {
        return portfolios.get(userID);
    }

    // Other CRUD operations
}
