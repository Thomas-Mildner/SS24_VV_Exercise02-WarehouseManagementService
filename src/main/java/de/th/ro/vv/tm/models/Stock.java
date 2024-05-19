package de.th.ro.vv.tm.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Objects;

@Schema(name = "StockModel", description = "Stock Data Model")
public final class Stock {

    @JsonProperty
    private final int articleId;

    @JsonProperty
    @Min(value = 1, message = "Stock must be at least 1")
    @Max(value = 10, message = "Stock must be at most 10")
    @Schema(description = "Stock value", defaultValue = "1", minimum = "1", maximum = "10")
    private int stock;

    public Stock(int articleId, int stock) {
        this.articleId = articleId;
        this.stock = stock;
    }

    public int getArticleId() {
        return articleId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 1 || stock > 10) {
            throw new IllegalArgumentException("Stock must be between 1 and 10");
        }
        this.stock = stock;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Stock) obj;
        return this.articleId == that.articleId &&
                this.stock == that.stock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, stock);
    }

    @Override
    public String toString() {
        return "Stock[" +
                "articleId=" + articleId + ", " +
                "stock=" + stock + ']';
    }
}
