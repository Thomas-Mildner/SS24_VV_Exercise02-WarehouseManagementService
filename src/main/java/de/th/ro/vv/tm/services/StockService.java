package de.th.ro.vv.tm.services;

import de.th.ro.vv.tm.models.Article;
import de.th.ro.vv.tm.models.Stock;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;


@RequestScoped()
public class StockService {

    @Inject ArticleStockStorage stockStorage;

    public boolean updateStock(Stock stock) {
        var existingStock = stockStorage.getStockById(stock.ArticleId());
       if(existingStock == null)
           return false;


       return true;

    }

    public Stock getArticleStock(int articleId) {
        return stockStorage.getStockById(articleId);
    }
}
