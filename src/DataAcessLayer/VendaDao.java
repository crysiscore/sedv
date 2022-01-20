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
/*    */ public class VendaDao
/*    */ {
/*    */   private ResultSet rs;
/*    */   private Connection connect;
/* 21 */   private CallableStatement cs = null;
/*    */   
/*    */   public VendaDao() {
/*    */     try {
/* 25 */       conexao conexao = new conexao("host", "port", "db", "user", "password");
/* 26 */       this.connect = conexao.getConnection();
/*    */     }
/* 28 */     catch (SQLException e) {
/*    */ 
/*    */     
/* 31 */     } catch (ClassNotFoundException e) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void EfectuarVenda(int codProduto, int codVenda, double preco, double quantidade) throws SQLException {
/* 39 */     this.cs = this.connect.prepareCall("{call EfectuarVenda(?)}");
/* 40 */     this.cs.setInt(1, codProduto);
/* 41 */     this.cs.executeQuery();
/*    */   }
/*    */ 
/*    */   
/*    */   public void CriarVenda(String nomeCliente, int totalVenda, int codFuncionario) throws SQLException {
/* 46 */     this.cs = this.connect.prepareCall("{call CriarVenda(?)}");
/* 47 */     this.cs.setString(1, nomeCliente);
/* 48 */     this.cs.setInt(2, totalVenda);
/* 49 */     this.cs.setInt(3, codFuncionario);
/* 50 */     this.cs.executeQuery();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultSet CodVendaCorrente() throws SQLException {
/* 56 */     this.cs = this.connect.prepareCall("{call CodVendaCorrente()}");
/* 57 */     this.rs = this.cs.executeQuery();
/*    */     
/* 59 */     return this.rs;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void VenderProduto(double totalVenda, String nomeCliente, int idFuncionario) throws SQLException {
/* 65 */     this.cs = this.connect.prepareCall("{call VENDA(?,?,?)}");
/* 66 */     this.cs.setDouble(1, totalVenda);
/* 67 */     this.cs.setString(2, nomeCliente);
/* 68 */     this.cs.setInt(3, idFuncionario);
/* 69 */     this.cs.executeQuery();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void DetalhesVenda(int codP, int codV, double preco, int quantid) throws SQLException {
/* 76 */     this.cs = this.connect.prepareCall("{call DETALHESVENDA(?,?,?,?)}");
/* 77 */     this.cs.setInt(1, codP);
/* 78 */     this.cs.setInt(2, codV);
/* 79 */     this.cs.setDouble(3, preco);
/* 80 */     this.cs.setInt(4, quantid);
/* 81 */     this.cs.executeQuery();
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\DataAcessLayer\VendaDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */