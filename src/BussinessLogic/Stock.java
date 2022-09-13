/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLogic;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.*;



/**
 *
 * @author Neusia
 */

         @Entity
         @Table(name="stock")
          public class Stock {
           @Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           @Column(name="id_stock")
           private Integer id_stock;
           
           @Column(name="quantidade_recebida")
/*    */   private Integer quantidade_recebida;
           
           @Column(name="data_entrada")
/*    */   private Date data_entrada;
           
         
           @ManyToOne
           @MapsId
           @JoinColumn(name = "produto_Cod_Produto")
           private Produto prod;
           
           @OneToOne
           @MapsId
           @JoinColumn(name = "usuario_Cod_Funcionario")  
           private Usuario usuario_Cod_Funcionario;
           
           @Column(name="numero_lote")
/*    */   private String numero_lote;
           
           @Column(name="fabricante")
/*    */   private String fabricante;
/*    */   
/*    */     public Stock() {
          }

    public Usuario getUser() {
        return usuario_Cod_Funcionario;
    }

    public void setUser(Usuario usuario_Cod_Funcionario) {
        this.usuario_Cod_Funcionario = usuario_Cod_Funcionario;
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
          //   this.produto_Cod_Produto= produto_Cod_Produto;
           this.quantidade_recebida=quantidade_recebida;
           this.data_entrada=data_entrada;
          //this.usuario_Cod_Funcionario=usuario_Cod_Funcionario;
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

    public Produto getProduto_Cod_Produto() {
      return prod;
   }

    public void setProduto_Cod_Produto(Produto prod) {
        this.prod = prod;
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
