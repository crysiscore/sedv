/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLogic;

import javax.persistence.*;

/**
 *
 * @author Neusia
 */
public class StockLevel {
           @Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           @Column(name="id_stockLevel")
           public Integer id_stock_level;
           
           @Column(name="unidades_stock")
           public Double unidades_stock;
           
           @OneToOne
           @MapsId
           @JoinColumn(name = "produto_Cod_Produto")
           private Produto prod;

    public Double getUnidades_stock() {
        return unidades_stock;
    }

    public void setUnidades_stock(Double unidades_stock) {
        this.unidades_stock = unidades_stock;
    }
}
