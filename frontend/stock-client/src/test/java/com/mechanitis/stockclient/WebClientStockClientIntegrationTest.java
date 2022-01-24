package com.mechanitis.stockclient;


import com.mechanitis.demo.stockclient.StockPrice;
import com.mechanitis.demo.stockclient.implementation.WebClientStockClient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

public class WebClientStockClientIntegrationTest {
    
    private  WebClient webClient = WebClient.builder().build();

    @Test
    void shouldRetrieveStcoPricesFromTheService(){
        WebClientStockClient webClientStockClient = new  WebClientStockClient(webClient);
        Flux<StockPrice> prices =  webClientStockClient.pricesFor("SYMBOL");

        Assertions.assertNotNull(prices);

        Flux<StockPrice> fivePrices = prices.take(5);
        

        Assertions.assertEquals(5,fivePrices.count().block());
        Assertions.assertEquals("SYMBOL",fivePrices.blockFirst().getSymbol());

    }

    
}
