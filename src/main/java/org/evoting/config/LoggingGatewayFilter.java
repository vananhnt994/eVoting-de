package org.evoting.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LoggingGatewayFilter extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            System.out.println("Request Path: " + exchange.getRequest().getURI().getPath());
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("Response Status Code: " + exchange.getResponse().getStatusCode());
            }));
        };
    }
}