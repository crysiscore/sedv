/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLogic;

import java.sql.Date;

/**
 *
 * @author Neusia Hilario
 */
public class Inventario {
    
           public Integer Cod_Inventario;
           public Integer Cod_Produto;
           public String Cod_ProdutoManual;
/*    */   public String Nome;
           public Double Stock;
           public Date Data;
          // public StockLevel stock;
           public Double Quantidade_Contada;
           public Double Diferenca_Existente_Contada;
           public String Tipo_inventario;
           public Usuario User;
           public Inventario inventario;

    public Inventario(Integer Cod_Inventario, Date Data, String Tipo_inventario, Usuario User) {
        this.Cod_Inventario = Cod_Inventario;
        this.Data = Data;
        this.Tipo_inventario = Tipo_inventario;
        this.User = User;
    }

         

    public Inventario(Integer Cod_Produto, String Nome, Double Stock, Double Quantidade_Contada, Double Diferenca_Existente_Contada) {
        this.Cod_Produto = Cod_Produto;
        this.Nome = Nome;
        this.Stock = Stock;
        this.Quantidade_Contada = Quantidade_Contada;
        this.Diferenca_Existente_Contada = Diferenca_Existente_Contada;
    }
    
    
    public Inventario(String Cod_ProdutoManual, String Nome, Double Stock, Double Quantidade_Contada, Double Diferenca_Existente_Contada) {
        this.Cod_ProdutoManual = Cod_ProdutoManual;
        this.Nome = Nome;
        this.Stock = Stock;
        this.Quantidade_Contada = Quantidade_Contada;
        this.Diferenca_Existente_Contada = Diferenca_Existente_Contada;
    }
    
        public Inventario(Integer Cod_Produto, String Cod_ProdutoManual, String Nome, Double Stock, Double Quantidade_Contada, Double Diferenca_Existente_Contada) {
        this.Cod_Produto = Cod_Produto;
        this.Cod_ProdutoManual = Cod_ProdutoManual;
        this.Nome = Nome;
        this.Stock = Stock;
        this.Quantidade_Contada = Quantidade_Contada;
        this.Diferenca_Existente_Contada = Diferenca_Existente_Contada;
    }

    public Inventario() {
    }

    public Inventario(int codProduto, String nome, double stock, double d) {
        
    }

    public Inventario getDetalhesInventario() {
        return inventario;
    }

    public void setDetalhesInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Integer getCod_Inventario() {
        return Cod_Inventario;
    }

    public void setCod_Inventario(Integer Cod_Inventario) {
        this.Cod_Inventario = Cod_Inventario;
    }

    public Integer getCod_Produto() {
        return Cod_Produto;
    }

    public void setCod_Produto(Integer Cod_Produto) {
        this.Cod_Produto = Cod_Produto;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Double getStock() {
        return Stock;
    }

    public void setStock(Double Stock) {
        this.Stock = Stock;
    }

    public Double getQuantidade_Contada() {
        return Quantidade_Contada;
    }

    public void setQuantidade_Contada(Double Quantidade_Contada) {
        this.Quantidade_Contada = Quantidade_Contada;
    }

    public Double getDiferenca_Existente_Contada() {
        return Diferenca_Existente_Contada;
    }

    public void setDiferenca_Existente_Contada(Double Diferenca_Existente_Contada) {
        this.Diferenca_Existente_Contada = Diferenca_Existente_Contada;
    }

    public String getCod_ProdutoManual() {
        return Cod_ProdutoManual;
    }

    public void setCod_ProdutoManual(String Cod_ProdutoManual) {
        this.Cod_ProdutoManual = Cod_ProdutoManual;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public String getTipo_inventario() {
        return Tipo_inventario;
    }

    public void setTipo_inventario(String Tipo_inventario) {
        this.Tipo_inventario = Tipo_inventario;
    }

    public Usuario getUser() {
        return User;
    }

    public void setUser(Usuario User) {
        this.User = User;
    }



    
}
