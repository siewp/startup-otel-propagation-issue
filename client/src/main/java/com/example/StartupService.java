package com.example;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Startup
public class StartupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupService.class);

    private final RestClient client;

    public StartupService() {
        client = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create("http://localhost:8080/"))
                .build(RestClient.class);
    }

    @Startup
    @WithSpan("Startup")
    public void onStart() {
        final String serverResponse = client.requestHello();
        LOGGER.info("Received server response: {}", serverResponse);
    }
}
