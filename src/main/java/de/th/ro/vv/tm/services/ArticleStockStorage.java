package de.th.ro.vv.tm.services;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import de.th.ro.vv.tm.models.Article;
import de.th.ro.vv.tm.models.Stock;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
public class ArticleStockStorage {

    private Cache<Integer, Article> articleCache;

    public ArticleStockStorage(){
        articleCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build();

        initializeCacheWithDummyArticles();
    }

    private void initializeCacheWithDummyArticles() {
        List<String> drinks = List.of(
                "Cola", "Wasser", "Bier", "Orangensaft", "Spezi",
                "Schnapserl", "Radler", "Hirschkuss", "Limonade",
                "Flötzinger Radler"
        );
        for (int i = 0; i < drinks.toArray().length; i++) {
            articleCache.put(i, new Article(i, drinks.get(i), new Stock(i, 0, null)));
        }
    }

    public Article getArticleById(int articleId) {
        Article cachedArticle = articleCache.getIfPresent(articleId);
        if (cachedArticle != null) {
            return cachedArticle;
        }

        // if not found in cache - no stock available
        Article article = new Article(articleId, "Article" + articleId, new Stock(articleId, 0, null));
        articleCache.put(articleId, article);

        return article;
    }


    public Stock getStockById(int articleId) {
        Article cachedArticle = articleCache.getIfPresent(articleId);
        if (cachedArticle != null) {
            return cachedArticle.stock();
        }
        return null;
    }

    public boolean updateStockForArticleId(int articleId, int updatedStock){
        var existingStock = getArticleById(articleId);
        if(existingStock == null)
            return false;

        existingStock.setStock(new Stock(articleId, updatedStock, null));
        articleCache.put(articleId, existingStock);
        return true;

    }


}
