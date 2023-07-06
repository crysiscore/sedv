/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BussinessLogic.Venda;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Neusia Hilario
 */
public class VendaModel {
    
    
    private final ObservableList<Venda> stockList = FXCollections.observableArrayList();
    
    private final ObjectProperty<Venda> currentStock = new SimpleObjectProperty<>(null);

    public ObjectProperty<Venda> currentStockProperty() {
        return currentStock ;
    }

    public final Venda getCurrentStock() {
        return currentStock.get();
    }

    public final void setCurrentStock(Venda venda) {
        currentStock.set(venda);
    }

    public ObservableList<Venda> getStockList() {
        return stockList ;
    }
    
      public void addStock(Venda v) {
        stockList.add(v);
    }

    
}
