package de.th.ro.vv.tm.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;
import java.util.Objects;

@Schema(name = "StockModel", description = "Stock Data Model")
public final class Stock {
    @JsonProperty
    private final int ArticleId;
    @JsonProperty
    private final int Stock;

    public Stock(int ArticleId, int Stock, Date NextDeliveryDate) {
        this.ArticleId = ArticleId;
        this.Stock = Stock;
    }

    public int ArticleId() {
        return ArticleId;
    }

    public int Stock() {
        return Stock;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Stock) obj;
        return this.ArticleId == that.ArticleId &&
                this.Stock == that.Stock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ArticleId, Stock);
    }

    @Override
    public String toString() {
        return "Stock[" +
                "ArticleId=" + ArticleId + ", " +
                "Stock=" + Stock + ", " + ']';
    }


}
