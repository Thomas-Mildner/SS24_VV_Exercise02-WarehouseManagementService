package de.th.ro.vv.tm.services;

import de.th.ro.vv.tm.models.Article;
import de.th.ro.vv.tm.persistence.ArticleStorage;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped()
public class ArticleService {

    @Inject
    ArticleStorage articleStorage;

    public Article getArticleById(int articleId) {
        return articleStorage.getArticleById(articleId);
    }
}
