package de.th.ro.vv.tm.api;

import de.th.ro.vv.tm.models.Article;
import de.th.ro.vv.tm.models.Stock;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/order")
public class OrderResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllOpenOrders() {
        return "No Open Orders - Fix me";
    }


    @POST
    public void addNewOrder(Article a){

    }
}
