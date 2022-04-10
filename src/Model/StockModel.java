/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BussinessLogic.Stock;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleObjectProperty;
/**
 *
 * @author Neusia
 */
public class StockModel {
    
     private final ObservableList<Stock> stockList = FXCollections.observableArrayList();
    
    private final ObjectProperty<Stock> currentStock = new SimpleObjectProperty<>(null);

    public ObjectProperty<Stock> currentStockProperty() {
        return currentStock ;
    }

    public final Stock getCurrentStock() {
        return currentStock.get();
    }

    public final void setCurrentStock(Stock stock) {
        currentStock.set(stock);
    }

    public ObservableList<Stock> getStockList() {
        return stockList ;
    }
    
      public void addStock(Stock s) {
        stockList.add(s);
    }

    
}
