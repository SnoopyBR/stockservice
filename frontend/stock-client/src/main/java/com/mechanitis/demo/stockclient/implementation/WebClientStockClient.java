package com.mechanitis.demo.stockclient.implementation;

import java.io.IOException;
import java.time.Duration;

import com.mechanitis.demo.stockclient.StockPrice;
import com.mechanitis.demo.stockclient.model.StockClient;

import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

@Log4j2
public class WebClientStockClient implements StockClient {

    private WebClient webClient;

    public WebClientStockClient(WebClient webClient){
        this.webClient = webClient;
    }

    @Override
    public Flux<StockPrice> pricesFor(String symbol) {
        log.info("WebClient stock client");

        return webClient.get()
                        .uri("http://localhost:8080/stocks/{symbol}",symbol)
                        .retrieve()
                        .bodyToFlux(StockPrice.class)
                        .retryWhen(Retry.backoff(5, Duration.ofSeconds(1)))
                        .doOnError(IOException.class, e -> log.error(e.getMessage()));

    }
}
