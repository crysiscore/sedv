/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BussinessLogic.DetalhesVenda;
import BussinessLogic.Venda;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Neusia Hilario
 */
public class DetalhesVendaModel {
         private final ObservableList<DetalhesVenda> DetalhesVendaList = FXCollections.observableArrayList();
    
    private final ObjectProperty<DetalhesVenda> currentStock = new SimpleObjectProperty<>(null);

    public ObjectProperty<DetalhesVenda> currentStockProperty() {
        return currentStock ;
    }

    public final DetalhesVenda getCurrentStock() {
        return currentStock.get();
    }

    public final void setCurrentStock(DetalhesVenda detalhesVenda) {
        currentStock.set(detalhesVenda);
    }

    public ObservableList<DetalhesVenda> getDetalhesVendaList() {
        return DetalhesVendaList ;
    }
    
      public void addStock(DetalhesVenda DV) {
        DetalhesVendaList.add(DV);
    }

}
