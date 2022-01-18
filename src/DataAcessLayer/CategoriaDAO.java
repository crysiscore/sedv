/*    */ package DataAcessLayer;
/*    */ 
/*    */ import java.sql.CallableStatement;
/*    */ import java.sql.Connection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CategoriaDAO
/*    */ {
/*    */   private ResultSet rs;
/*    */   private Connection connect;
/* 21 */   private CallableStatement cs = null;
/*    */ 
/*    */   
/*    */   public CategoriaDAO() {
/*    */     try {
/* 26 */       conexao conexao = new conexao("localhost", "3306", "mydb", "root", "123456789");
/* 27 */       this.connect = conexao.getConnection();
/*    */     }
/* 29 */     catch (SQLException e) {
/*    */ 
/*    */     
/* 32 */     } catch (ClassNotFoundException e) {}
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void cadastrarcategoria(String Nome) throws SQLException {
/* 38 */     this.cs = this.connect.prepareCall("{call proc_categoria(?)}");
/* 39 */     this.cs.setString(1, Nome);
/* 40 */     this.cs.executeQuery();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atualizacategoria(int idCategoria, String Nome) throws SQLException {
/* 46 */     this.cs = this.connect.prepareCall("{call AtualizaCategoria(?,?)}");
/* 47 */     this.cs.setInt(1, idCategoria);
/* 48 */     this.cs.setString(2, Nome);
/* 49 */     this.cs.executeQuery();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void ApagarCategoria(int idCategoria) throws SQLException {
/* 55 */     this.cs = this.connect.prepareCall("{call apagarCategoria(?)}");
/* 56 */     this.cs.setInt(1, idCategoria);
/* 57 */     this.cs.executeQuery();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultSet ListaCategoria() throws SQLException {
/* 64 */     this.cs = this.connect.prepareCall("{call  ListarCategoria()}");
/* 65 */     this.rs = this.cs.executeQuery();
/*    */     
/* 67 */     return this.rs;
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\DataAcessLayer\CategoriaDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */