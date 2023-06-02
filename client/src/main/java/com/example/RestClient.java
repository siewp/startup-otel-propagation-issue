package com.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello")
public interface RestClient {

    @GET
    String requestHello();
}
