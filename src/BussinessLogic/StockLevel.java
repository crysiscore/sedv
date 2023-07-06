/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLogic;

/**
 *
 * @author Neusia
 */
public class StockLevel {


    public Produto produto_Cod_Produto;
    public Double unidades_stock;

    public Produto getProduto_Cod_Produto() {
        return produto_Cod_Produto;
    }

    public void setProduto_Cod_Produto(Produto produto_Cod_Produto) {
        this.produto_Cod_Produto = produto_Cod_Produto;
    }
    
    
    

    public Double getUnidades_stock() {
        return unidades_stock;
    }

    public void setUnidades_stock(Double unidades_stock) {
        this.unidades_stock = unidades_stock;
    }
}
