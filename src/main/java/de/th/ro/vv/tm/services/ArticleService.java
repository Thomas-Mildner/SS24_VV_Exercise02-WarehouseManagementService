package de.th.ro.vv.tm.services;

import de.th.ro.vv.tm.models.Article;
import de.th.ro.vv.tm.models.Stock;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Scope;

import java.util.ArrayList;
import java.util.List;


@RequestScoped()
public class ArticleService {

    @Inject ArticleStockStorage articleStockStorage;

    public Article getArticleById(int articleId) {
        return articleStockStorage.getArticleById(articleId);
    }

    public List<Article> getAllArticles() {
        ArrayList<Article> articles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            articles.add(new Article(i, "Article"+ i, new Stock(i, i*2, null)));
        }
        return articles;
    }
}
