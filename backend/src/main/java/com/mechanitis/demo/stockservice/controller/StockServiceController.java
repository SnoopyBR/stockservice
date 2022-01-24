package com.mechanitis.demo.stockservice.controller;


import com.mechanitis.demo.stockservice.dto.StockPrice;
import com.mechanitis.demo.stockservice.service.PriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class StockServiceController {

    private final PriceService priceService;
    
    @Autowired
    public StockServiceController(PriceService service){
        this.priceService = service;
    }
    @GetMapping(value = "/stocks/{symbol}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<StockPrice> prices(@PathVariable String symbol){
        
        return priceService.generatePrices(symbol);

    }

}
