/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLogic;

import java.sql.Date;
import java.time.LocalDate;


/**
 *
 * @author Neusia
 */
public class Stock {
           
/*    */   public Integer quantidade_recebida;
/*    */   public Date data_entrada;
/*    */   public Integer produto_Cod_Produto;
           public Usuario user;
           private Produto prod;
/*    */   public Integer usuario_Cod_Funcionario;
/*    */   public String numero_lote;
/*    */   public String fabricante;
/*    */   public StockLevel stock;
/*    */

    public StockLevel getStock() {
        return stock;
    }

    public void setStock(StockLevel stock) {
        this.stock = stock;
    }
 public Stock() {
          }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }


          public Stock(Integer produto_Cod_Produto, Integer quantidade_recebida, Date data_entrada,
            Integer usuario_Cod_Funcionario, String numero_lote, String fabricante ){
           
             //this.prod=prod;
             this.produto_Cod_Produto= produto_Cod_Produto;
           this.quantidade_recebida=quantidade_recebida;
           this.data_entrada=data_entrada;
          this.usuario_Cod_Funcionario=usuario_Cod_Funcionario;
          // this.prod = prod;
           this.numero_lote=numero_lote;
           this.fabricante=fabricante;
          }
      
  
       
    public Integer getQuantidade_recebida() {
        return quantidade_recebida;
    }

    public void setQuantidade_recebida(Integer quantidade_recebida) {
        this.quantidade_recebida = quantidade_recebida;
    }

    public Date getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Integer getProduto_Cod_Produto() {
      return produto_Cod_Produto;
   }

    public void setProduto_Cod_Produto(Integer produto_Cod_Produto) {
        this.produto_Cod_Produto = produto_Cod_Produto;
   }

    public Integer getUsuario_Cod_Funcionario() {
       return usuario_Cod_Funcionario;
    }

    public void setUsuario_Cod_Funcionario(Integer usuario_Cod_Funcionario) {
        this.usuario_Cod_Funcionario = usuario_Cod_Funcionario;
    }

    public String getNumero_lote() {
        return numero_lote;
    }

    public void setNumero_lote(String numero_lote) {
        this.numero_lote = numero_lote;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

   
}
