/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BussinessLogic.Inventario;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Neusia Hilario
 */
public class InventarioModel {
    
    private String TipoDeInventario;

    // Outros atributos e métodos do modelo...

    public String getTipoDeInventario() {
        return TipoDeInventario;
    }

    public void setTipoDeInventario(String TipoDeInventario) {
        this.TipoDeInventario = TipoDeInventario;
    }
    

    
     private final ObservableList<Inventario> inventarioList = FXCollections.observableArrayList();
    
    private final ObjectProperty<Inventario> currentInventario = new SimpleObjectProperty<>(null);

    public ObjectProperty<Inventario> currentInventarioProperty() {
        return currentInventario ;
    }

    public final Inventario getCurrentInventario() {
        return currentInventario.get();
    }

    public final void setCurrentInventaario(Inventario inventario) {
        currentInventario.set(inventario);
    }

    public ObservableList<Inventario> getInventarioList() {
        return inventarioList ;
    }
    
      public void addInventario(Inventario i) {
        inventarioList.add(i);
    }

    
}

    

