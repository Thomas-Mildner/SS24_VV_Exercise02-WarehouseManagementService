package de.th.ro.vv.tm.api;

import de.th.ro.vv.tm.models.Article;
import de.th.ro.vv.tm.models.Stock;
import de.th.ro.vv.tm.services.ArticleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;


@Path("/article")
public class ArticleResource {

    @Inject
    ArticleService articleService;


    @GET
    @Path("/{articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Get Article by given ArticleId successful"),
            @APIResponse(responseCode = "404", description = "Article with given Article Id not found")
    })
    @Operation(
            summary = "Get an article by its ID",
            description = "Retrieves the details of an article specified by its ID. Returns a 404 status if the article is not found."
    )
    @Parameter(
            description = "ID of the article to retrieve",
            required = true,
            example = "1"
    )
    public Response getArticle(@PathParam("articleId") int articleId){
        var article = articleService.getArticleById(articleId);
        if(article == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(article).build();
    }
}
