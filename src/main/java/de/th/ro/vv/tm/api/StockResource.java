package de.th.ro.vv.tm.api;

import de.th.ro.vv.tm.models.Article;
import de.th.ro.vv.tm.models.Stock;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/stock")
public class StockResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getAllArticleStocks() {
        ArrayList<Article> articles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            articles.add(new Article(i, "Article"+ i, new Stock(i, i*2)));
        }
        return articles;
    }


}