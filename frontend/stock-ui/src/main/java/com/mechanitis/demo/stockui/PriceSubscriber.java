package com.mechanitis.demo.stockui;

import static java.lang.String.valueOf;

import java.util.function.Consumer;

import com.mechanitis.demo.stockclient.StockPrice;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class PriceSubscriber implements Consumer<StockPrice> {

    private final ObservableList<Data<String, Double>> seriesData = FXCollections.observableArrayList();
    private final Series<String,Double> series; 


    public PriceSubscriber(String symbol) {
        series = new Series<>(symbol,seriesData);
    }

    @Override
    public void accept(StockPrice stockPrice) {
        Platform.runLater(() -> seriesData.add(new Data<>(valueOf(stockPrice.getTime().getSecond()), stockPrice.getPrice())));
    }


    public Series<String,Double> getSeries() {
        return this.series;
    }


}
