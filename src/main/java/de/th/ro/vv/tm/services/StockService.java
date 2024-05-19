package de.th.ro.vv.tm.services;

import de.th.ro.vv.tm.models.Stock;
import de.th.ro.vv.tm.persistence.ArticleStorage;
import de.th.ro.vv.tm.persistence.StockStorage;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped()
public class StockService {

    @Inject
    StockStorage stockStorage;

    @Inject
    ArticleStorage articleStorage;

    public boolean updateStock(Stock stock) {
        return stockStorage.updateStockForArticleId(stock.getArticleId(), stock.getStock());
    }

    public Stock getArticleStock(int articleId) {
        var existingStock =  stockStorage.getStockById(articleId);
        if(existingStock != null)
            return existingStock;

        var existingArticle = articleStorage.getArticleById(articleId);
        if(existingArticle == null)
            return null;

        //no stock yet
        return new Stock(articleId, 0);
    }
}
