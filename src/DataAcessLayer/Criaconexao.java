/*    */ package DataAcessLayer;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
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
/*    */ public class Criaconexao
/*    */ {
/*    */   public static Connection getConexao() throws SQLException {
/*    */     try {
/* 20 */       Class.forName("com.mysql.jdbc.Driver");
/* 21 */       System.out.println("Conexao ao Banco");
/* 22 */       return DriverManager.getConnection("jdbc:mysql://host/db", "user", "password");
/*    */     }
/* 24 */     catch (ClassNotFoundException e) {
/*    */       
/* 26 */       throw new SQLException(e.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\DataAcessLayer\Criaconexao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */