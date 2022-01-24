package com.mechanitis.demo.stockclient.implementation;

import java.io.IOException;
import java.time.Duration;

import com.mechanitis.demo.stockclient.StockPrice;
import com.mechanitis.demo.stockclient.model.StockClient;

import org.springframework.messaging.rsocket.RSocketRequester;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

@Log4j2
public class RSocketStockClient implements StockClient {

    private final RSocketRequester rSocketRequester;


    public RSocketStockClient(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }


    public Flux<StockPrice> pricesFor(String symbol) {
        log.info("RSocket stock client");
        return rSocketRequester.route("stockPrices")
                                .data(symbol)
                                .retrieveFlux(StockPrice.class)
                                .retryWhen(Retry.backoff(5, Duration.ofSeconds(1)))
                                .doOnError(IOException.class, e -> log.error(e.getMessage()));
    }

}
