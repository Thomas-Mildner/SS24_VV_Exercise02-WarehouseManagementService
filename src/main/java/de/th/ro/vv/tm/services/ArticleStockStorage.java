package de.th.ro.vv.tm.services;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import de.th.ro.vv.tm.models.Article;
import de.th.ro.vv.tm.models.Stock;
import jakarta.inject.Singleton;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
public class ArticleStockStorage {

    private final Cache<Integer, Article> articleCache;
    private final Dictionary<Integer, Article> articleStorage = new Hashtable<>();

    public ArticleStockStorage(){
        articleCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(5, TimeUnit.MINUTES)
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
            var article = new Article(i, drinks.get(i), new Stock(i, 0, null));
            this.articleStorage.put(i, article);
            articleCache.put(i, article);
        }
    }

    public Article getArticleById(int articleId) {
        var article =  articleCache.getIfPresent(articleId);
        if(article != null)
            return article;

        //no current stock value in 'database'
        return this.articleStorage.get(articleId);
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
