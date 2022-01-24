package com.mechanitis.demo.stockclient.model;

import com.mechanitis.demo.stockclient.StockPrice;

import reactor.core.publisher.Flux;

public interface StockClient {
    Flux<StockPrice> pricesFor(String symbol);
}
