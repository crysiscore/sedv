/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcessLayer;

import BussinessLogic.Produto;
import BussinessLogic.Stock;
import static DataAcessLayer.conexao.getConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Date.from;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Neusia
 */
public class StockDAO {
   private ResultSet rs;
/*     */   private Connection connect;
/*  20 */   private CallableStatement cs = null;
/*     */ 

   public StockDAO(){
       try {
/*  25 */       conexao conexao = new conexao();
/*  26 */       this.connect = conexao.getConnection();
/*     */     }
/*  28 */     catch (SQLException e) {
/*     */ 
/*     */     
/*  31 */     } catch (ClassNotFoundException e) {}
              }
   
    

     
        public boolean remover(Stock stock) {
        String sql = "DELETE FROM vendas WHERE idstock=?";
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, stock.getIdstock());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        

        public Stock buscarUltimoStock() {
        String sql = "SELECT max(idstock) FROM vendas";
        Stock retorno = new Stock();
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                retorno.setIdstock(resultado.getInt("max"));
                return retorno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
  
      
  
    public void AdicionarStock(Produto prod, int quant) throws SQLException {
/*  55 */     this.cs = this.connect.prepareCall("{call AdicionarStock(?,?)}");
/*  56 */     this.cs.setInt(1, prod.getCod_produto());
/*  57 */     this.cs.setInt(2, quant);
/*     */     
/*  59 */     this.cs.executeQuery();
/*     */   }
    
    public void ApagarStock(int Codigo) throws SQLException {
/* 117 */     this.cs = this.connect.prepareCall("{call ApagarStock(?)}");
/* 118 */     this.cs.setInt(1, Codigo);
/* 119 */     this.cs.executeQuery();
/*     */   }
    
     public ResultSet ListarStock() throws SQLException {
/*  74 */     this.cs = this.connect.prepareCall("{call  ListarStock()}");
/*  75 */     this.rs = this.cs.executeQuery();
/*     */     
/*  77 */     return this.rs;
/*     */   }
           
/*     */   public ResultSet TodoStock() throws SQLException {
/*  66 */     this.cs = this.connect.prepareCall("{call TodoStock()}");
/*  67 */     this.rs = this.cs.executeQuery();
/*     */     
/*  69 */     return this.rs;
/*     */   }
     
     
      public ResultSet getDetalhesStock(int codProduto) throws SQLException {
/*  82 */     this.cs = this.connect.prepareCall("{call  DetalhesStock(?)}");
/*  83 */     this.cs.setInt(1, codProduto);
/*  84 */     this.rs = this.cs.executeQuery();
/*     */     
/*  86 */     return this.rs;
/*     */   }
    
    
  public void RegistarStock(Stock stock) throws SQLException {
/* 103 */    
              this.cs = this.connect.prepareCall("{call RegistarStock(?,?,?,?,?,?)}");
/* 104 */    
              this.cs.setInt(1, stock.getQuantidade_recebida());
/* 105 */     this.cs.setDate(2, stock.getData_entrada());
/* 106 */     this.cs.setInt(3, stock.getProd().getCod_produto());
/* 107 */     this.cs.setInt(4, stock.getUser().getCod_Funcionario());
/* 108 */     this.cs.setString(5, stock.getNumero_lote());
/* 109 */     this.cs.setString(6, stock.getFabricante());
/* 110 */     this.cs.executeQuery();
/* 111 */     this.cs.close();
/*     */   }
         
    
}
 
