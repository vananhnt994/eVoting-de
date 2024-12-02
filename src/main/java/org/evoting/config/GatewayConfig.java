package org.evoting.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customeRouteLocator(RouteLocatorBuilder builder) {
        RouteLocator routes = builder.routes()
                // GET request for /citizens/
                .route("get_citizens", r -> r.path("/api/citizens/").filters(f -> f.setResponseHeader("Access-Control-Allow-Origin","http://localhost:3000"))
                        .uri("http://localhost:8081/api/citizens/"))

                // POST request for /citizens/signup
                .route("post_signup", r -> r.path("/api/citizens/signup")
                        .and().method("POST").filters(f -> f.setResponseHeader("Access-Control-Allow-Origin","http://localhost:3000"))
                        .uri("http://localhost:8081/api/citizens/signup"))


                // POST request for /citizens/login
                .route("post_login", r -> r.path("/api/citizens/login")
                        .and().method("POST").filters(f -> f.setResponseHeader("Access-Control-Allow-Origin","http://localhost:3000"))
                        .uri("http://localhost:8081/api/citizens/login")) // Ziel-URI anpassen

                .route("topics", p -> p
                        .path("/api/topics/**")
                        .uri("http://localhost:8082"))
                .build();
        routes.getRoutes().log();
        return routes;


    }

}