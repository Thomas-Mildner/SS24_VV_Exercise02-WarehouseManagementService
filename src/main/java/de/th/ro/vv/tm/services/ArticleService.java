package de.th.ro.vv.tm.services;

import de.th.ro.vv.tm.models.Article;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped()
public class ArticleService {

    @Inject ArticleStockStorage articleStockStorage;

    public Article getArticleById(int articleId) {
        return articleStockStorage.getArticleById(articleId);
    }
}
