package de.th.ro.vv.tm.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(name = "ArticleModel", description = "Article Data Model")
public record Article(
        @JsonProperty @Schema(description = "ArticleId value", minimum = "1", maximum = "10") @Min(value = 1, message = "ArticleId must be at least 1") @Max(value = 10, message = "ArticleId must be at most 10") int ArticleId,
        @JsonProperty String Description) {
    public Article(int ArticleId, String Description) {
        this.ArticleId = ArticleId;
        this.Description = Description;
    }

    @Override
    public int ArticleId() {
        return ArticleId;
    }

    @Override
    public String Description() {
        return Description;
    }


    @Override
    public String toString() {
        return "Article[" +
                "ArticleId=" + ArticleId + ", " +
                "Description=" + Description + ", " + ']';
    }

}
