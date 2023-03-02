package edu.fpt.server;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/test")
public class HelloResource {
    @GET
    @Produces("application/json")
    public Response hello() {
        JSONPObject jsonpObject = new JSONPObject("callback", "{\"message\": \"Hello World!\", \"status\": 200}");
        return Response
                .ok(jsonpObject)
                .build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response helloPost(String body) {
        return Response
                .ok(body)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }
}