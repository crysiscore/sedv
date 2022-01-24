/*    */ package DataAcessLayer;
/*    */ 
/*    */ import java.sql.CallableStatement;
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
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
/*    */ 
/*    */ public class UnidadeDao
/*    */ {
/*    */   ResultSet rs;
/* 22 */   CallableStatement cs = null;
/*    */   
/*    */   PreparedStatement ps;
/*    */   Connection connect;
/*    */   
/*    */   public UnidadeDao() {
/*    */     try {
/* 29 */      conexao conexao = new conexao();
/* 30 */       this.connect = conexao.getConnection();
/*    */     }
/* 32 */     catch (SQLException e) {
/*    */ 
/*    */     
/* 35 */     } catch (ClassNotFoundException e) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cadastrarUnidade(String Descricao_Unidade) throws SQLException {
/* 42 */     this.cs = this.connect.prepareCall("{call proc_unidade(?)}");
/*    */     
/* 44 */     this.cs.setString(1, Descricao_Unidade);
/* 45 */     this.cs.executeQuery();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultSet ListaUnidade() throws SQLException {
/* 52 */     this.cs = this.connect.prepareCall("{call  ListarUnidade()}");
/* 53 */     this.rs = this.cs.executeQuery();
/*    */     
/* 55 */     return this.rs;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void ApagarUnidade(int idUnidade) throws SQLException {
/* 62 */     this.cs = this.connect.prepareCall("{call apagarUnidade(?)}");
/* 63 */     this.cs.setInt(1, idUnidade);
/* 64 */     this.cs.executeQuery();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void atualizaunidade(int idUnidade, String Descricao_Unidade) throws SQLException {
/* 71 */     this.cs = this.connect.prepareCall("{call atualizaunidade(?,?)}");
/* 72 */     this.cs.setInt(1, idUnidade);
/* 73 */     this.cs.setString(2, Descricao_Unidade);
/* 74 */     this.cs.executeQuery();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResultSet Pesquisartabela(String Descricao_Unidade) throws SQLException {
/* 79 */     this.cs = this.connect.prepareCall("{call  PesquisaUnidade()}");
/* 80 */     this.cs.setString(1, Descricao_Unidade);
/* 81 */     this.rs = this.cs.executeQuery();
/*    */ 
/*    */     
/* 84 */     return this.rs;
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\DataAcessLayer\UnidadeDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */