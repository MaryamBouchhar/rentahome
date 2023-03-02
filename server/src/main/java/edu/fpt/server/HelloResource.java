package edu.fpt.server;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/test")
public class HelloResource {
    // fix CORS policy

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}