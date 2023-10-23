/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcessLayer;

import BussinessLogic.Inventario;
import BussinessLogic.InventarioPrincipal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Neusia Hilario
 */
public class InventarioDAO {
     private ResultSet rs;
/*    */   private Connection connect;
/* 21 */   private CallableStatement cs = null;
           
          ObservableList <Inventario> inventario =FXCollections.observableArrayList();
/*    */   /*    */   
/*    */   public InventarioDAO() {
/*    */     try {
/* 25 */       conexao conexao = new conexao();
/* 26 */       this.connect = conexao.getConnection();
/*    */     }
/* 28 */     catch (SQLException e) {
/*    */ 
/*    */     
/* 31 */     } catch (ClassNotFoundException e) {}
/*    */   }
/*    */    
              public void RegistarInventarioItem( ObservableList <Inventario> inventario ) throws SQLException {
/* 103 */    
              this.cs = this.connect.prepareCall("{call Registar_Inventario_Item(?,?,?,?)}");
              try{
/* 104 */     for(int i = 0; i < inventario.size(); i++) {
              this.cs.setInt(1, inventario.get(i).getCod_Produto());
/* 106 */     this.cs.setDouble(2, inventario.get(i).getStock());
/* 107 */     this.cs.setDouble(3, inventario.get(i).getQuantidade_Contada());
              this.cs.setDouble(4, inventario.get(i).getDiferenca_Existente_Contada());
/* 108 */ 
/* 110 */     this.cs.executeQuery();
/* 111 */     
/*     */   }
                  } catch(SQLException ex){ 
                     Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
  }
/*    */ 
/*    */     public ResultSet RegistarInventario(InventarioPrincipal IP) throws SQLException {
/* 103 */     cs = this.connect.prepareCall("{call Registar_Inventario(?,?,?)}");
/* 104 */     cs.setDate(1, IP.getData());
              cs.setInt(2, IP.getUser().getCod_Funcionario());  
              cs.setString(3, IP.getTipo_De_Inventario());
              
/* 110 */     this.rs = cs.executeQuery();         
              return this.rs;
/*     */   }
}
