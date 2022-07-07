/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import BussinessLogic.Produto;
import BussinessLogic.Stock;
import BussinessLogic.Usuario;
import DataAcessLayer.StockDAO;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Neusia
 */
public class StockServicos {
    
    
     ResultSet rs;
/*     */   private Stock stock;
/*  30 */   private StockDAO stockDAO = new StockDAO();
            private   Produto produto ;
            private Usuario user;
           
/*  31 */  // private VendaDao vendaDao = new VendaDao();
/*     */ 
/*     */ 
            ProdutosServicos prodserv ;

    public StockServicos() {
        this.prodserv = new  ProdutosServicos();
    }
    

    
     public Stock getDetalhesStock(int codProduto) throws SQLException {
/*  97 */     this.rs = this.stockDAO.getDetalhesStock(codProduto);
/*  98 */     this.rs.next();
/*  99 */   //  this.stock = new Stock();
             // this.stock.setIdstock(Integer.valueOf(this.rs.getInt("idstock")));
              this.stock.setQuantidade_recebida(Integer.valueOf(this.rs.getInt("quantidade_recebida")));
/* 101 */    this.stock.setData_entrada(this.rs.getDate("data_entrada"));
              this.stock.setProd(produto);
              this.stock.setUser(user);
              //this.stock.setProduto_Cod_Produto(Integer.valueOf(this.rs.getInt("Codigo")));
/* 103 */    // this.stock.setUsuario_Cod_Funcionario(Integer.valueOf(this.rs.getInt("usuario_Cod_Funcionario")));
/* 104 */     this.stock.setNumero_lote(this.rs.getString("numero_lote"));
/* 105 */     this.stock.setFabricante(this.rs.getString("fabricante"));
/* 106 */     return this.stock;
/*     */   }
           
     
     public Stock getDetalhesStockTotal(int codProduto) throws SQLException {
        /*  97 */ this.rs = this.stockDAO.getDetalhesStockLevel(codProduto);
        /*  98 */ this.rs.next();
        /*  99 */ this.stock = new Stock();
        /* 100 */ this.stock.setProduto_Cod_Produto(Integer.valueOf(this.rs.getInt("Codigo")));
        /* 101 */ this.stock.setQuantidade_recebida(Integer.valueOf(this.rs.getInt("Codigo")));
        /* 10
        /* 106 */ return this.stock;
        /*     */    }
     

     

     
     
      //public Stock  criarStock(Produto prod, Usuario user , int quantidade, String fabricante , String nrLote, Date  data){
            
           //stock.setProd(prod);
            //stock.setUser(user);
        
          // return stock;
        //}
        
      
        
        public void RegistarStock(Stock  st) throws SQLException{
         //stockDAO.RegistarStock(st);
        
        
        }
    
}
