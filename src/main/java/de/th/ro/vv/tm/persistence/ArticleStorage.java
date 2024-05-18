package de.th.ro.vv.tm.persistence;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import de.th.ro.vv.tm.models.Article;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class ArticleStorage {

    private final Cache<Integer, Article> articleCache;

    public ArticleStorage(){
        articleCache = Caffeine.newBuilder()
                .maximumSize(100)
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
            var article = new Article(i + 1, drinks.get(i));
            articleCache.put(i+1, article);
        }
    }

    public Article getArticleById(int articleId) {
        return articleCache.getIfPresent(articleId);
    }
}
