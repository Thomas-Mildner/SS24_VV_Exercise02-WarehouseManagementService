package de.th.ro.vv.tm.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Objects;

@Schema(name = "ArticleModel", description = "Article Data Model")
public final class Article {
    @JsonProperty
    private final int ArticleId;

    @JsonProperty
    private final String Description;

    @JsonProperty
    private Stock stock;

    public Article(int ArticleId, String Description, Stock stock) {
        this.ArticleId = ArticleId;
        this.Description = Description;
        this.stock = stock;
    }

    public int ArticleId() {
        return ArticleId;
    }

    public String Description() {
        return Description;
    }

    public Stock stock() {
        return stock;
    }

    public void setStock(Stock updatedStock){
        this.stock = updatedStock;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Article) obj;
        return this.ArticleId == that.ArticleId &&
                Objects.equals(this.Description, that.Description) &&
                Objects.equals(this.stock, that.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ArticleId, Description, stock);
    }

    @Override
    public String toString() {
        return "Article[" +
                "ArticleId=" + ArticleId + ", " +
                "Description=" + Description + ", " +
                "stock=" + stock + ']';
    }

}
