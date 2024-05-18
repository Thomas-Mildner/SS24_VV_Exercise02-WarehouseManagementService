package de.th.ro.vv.tm.services;

import de.th.ro.vv.tm.models.Stock;
import de.th.ro.vv.tm.persistence.StockStorage;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped()
public class StockService {

    @Inject
    StockStorage stockStorage;

    public boolean updateStock(Stock stock) {
        var existingStock = stockStorage.getStockById(stock.ArticleId());
        return stockStorage.updateStockForArticleId(stock.ArticleId(), stock.Stock());
    }

    public Stock getArticleStock(int articleId) {
        return stockStorage.getStockById(articleId);
    }
}
