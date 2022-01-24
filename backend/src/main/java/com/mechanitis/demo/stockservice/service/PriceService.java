package com.mechanitis.demo.stockservice.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import com.mechanitis.demo.stockservice.dto.StockPrice;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class PriceService {

    public Flux<StockPrice> generatePrices(String symbol) {
        return Flux.interval(Duration.ofSeconds(1)).map(e->  new StockPrice(symbol, randomStockPrice(), LocalDateTime.now()));
    }

    private Double randomStockPrice() {
        return ThreadLocalRandom.current().nextDouble(100.0);
    }

}
