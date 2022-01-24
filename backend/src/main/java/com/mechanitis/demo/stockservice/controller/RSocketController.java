package com.mechanitis.demo.stockservice.controller;

import com.mechanitis.demo.stockservice.dto.StockPrice;
import com.mechanitis.demo.stockservice.service.PriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;

@Controller

public class RSocketController {

    private final PriceService priceService;

    @Autowired
    public RSocketController(PriceService service){
        this.priceService = service;
    }

    @MessageMapping("stockPrices")
    public Flux<StockPrice> prices(String symbol) {
        return priceService.generatePrices(symbol);
    }

}
