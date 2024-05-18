package de.th.ro.vv.tm.services;

import de.th.ro.vv.tm.models.Stock;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.Date;


@RequestScoped()
public class StockService {

    @Inject ArticleStockStorage stockStorage;

    public boolean updateStock(Stock stock) {
        var existingStock = stockStorage.getStockById(stock.ArticleId());
        return stockStorage.updateStockForArticleId(stock.ArticleId(), stock.Stock());
    }

    public Stock getArticleStock(int articleId) {
        return stockStorage.getStockById(articleId);
    }
}
