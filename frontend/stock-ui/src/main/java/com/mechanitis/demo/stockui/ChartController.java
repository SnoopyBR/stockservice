package com.mechanitis.demo.stockui;

import static javafx.collections.FXCollections.observableArrayList;

import com.mechanitis.demo.stockclient.implementation.WebClientStockClient;
import com.mechanitis.demo.stockclient.model.StockClient;

import org.springframework.stereotype.Component;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
@Component
public class ChartController {

    @FXML
    public LineChart<String, Double> chart;
    private StockClient stockClient;

    public ChartController(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    @FXML
    public void initialize() {
        String symbol1 = "SYMBOL1";
        final PriceSubscriber priceSubscriber1 = new PriceSubscriber(symbol1);
        stockClient.pricesFor(symbol1).subscribe(priceSubscriber1);

        String symbol2 = "SYMBOL2";
        final PriceSubscriber priceSubscriber2 = new PriceSubscriber(symbol2);
        stockClient.pricesFor(symbol2).subscribe(priceSubscriber2);

        String symbol3 = "SYMBOL3";
        final PriceSubscriber priceSubscriber3 = new PriceSubscriber(symbol3);
        stockClient.pricesFor(symbol3).subscribe(priceSubscriber3);

        ObservableList<Series<String, Double>> data = observableArrayList();
        data.add(priceSubscriber1.getSeries());
        data.add(priceSubscriber2.getSeries());
        data.add(priceSubscriber3.getSeries());
        chart.setData(data);



    }

}
