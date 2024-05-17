package de.th.ro.vv.tm.api;

import de.th.ro.vv.tm.models.Article;
import de.th.ro.vv.tm.models.Stock;
import de.th.ro.vv.tm.services.StockService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/stock")
public class StockResource {

    @Inject StockService stockService;


    @GET
    @Path("/{articleId}")
    @Produces(APPLICATION_JSON)
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Get All Articles successful"),
            @APIResponse(responseCode = "404", description = "Stock with given ArticleId not found")
    })
    @Operation(
            summary = "Get an Stock by its matching ArticleId",
            description = "Retrieves the current stock by ArticleID. Returns a 404 status if the article is not found."
    )
    @Parameter(
            description = "ArticleId of the article to retrieve stock infos",
            required = true,
            example = "1"
    )
    public Response getArticleStock(@PathParam("articleId") int articleId) {
        Stock articleStock = stockService.getArticleStock(articleId);
        if (articleStock != null) {
            return Response.ok(articleStock).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Consumes(APPLICATION_JSON)
    @APIResponses({
            @APIResponse(responseCode = "202", description = "Update Stock successful"),
            @APIResponse(responseCode = "400", description = "Bad Request - Got validation Error"),
            @APIResponse(
                    responseCode = "422",
                    description = "Stock Updated failed",
                    content = @Content(mediaType = APPLICATION_JSON)
            )
    })
    @Operation(
            summary = "Update a Stock in Local Warehouse",
            description = "Update the Local Warehouse Stock for given ArticleId"
    )
    public Response updateStock(Stock stock){

        if(stock.Stock() < 1){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if(stock.ArticleId() < 0 || stock.ArticleId() > 10){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        var stockUpdateSuccessful = stockService.updateStock(stock);
        if(!stockUpdateSuccessful)
            return Response.status(Response.Status.fromStatusCode(422)).build();

        return Response.accepted().build();
    }


}