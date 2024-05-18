package de.th.ro.vv.tm.persistence;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import de.th.ro.vv.tm.models.Stock;
import jakarta.inject.Singleton;

import java.util.concurrent.TimeUnit;

@Singleton
public class StockStorage {

    private final Cache<Integer, Stock> stockCache;


    public StockStorage() {
        stockCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build();

    }

    public Stock getStockById(int articleId) {
        return stockCache.getIfPresent(articleId);
    }

    public boolean updateStockForArticleId(int articleId, int updatedStock){
        var existingStock = getStockById(articleId);
        if(existingStock == null)
            return false;

        existingStock.setStock(updatedStock);
        stockCache.put(articleId, existingStock);
        return true;

    }

}
