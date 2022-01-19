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
/*    */ 
/*    */ 
/*    */ public class conexao
/*    */ {
/*    */   private static String host;
/*    */   private static String port;
/*    */   private static String bd;
/*    */   private static String user;
/*    */   private static String password;
/*    */   
/*    */   public static String getBd() {
/* 27 */     return bd;
/*    */   }
/*    */   
/*    */   public static void setBd(String bd) {
/* 31 */     conexao.bd = bd;
/*    */   }
/*    */   
/*    */   public static String getHost() {
/* 35 */     return host;
/*    */   }
/*    */   
/*    */   public static void setHost(String host) {
/* 39 */     conexao.host = host;
/*    */   }
/*    */   
/*    */   public static String getPassword() {
/* 43 */     return password;
/*    */   }
/*    */   
/*    */   public static void setPassword(String password) {
/* 47 */     conexao.password = password;
/*    */   }
/*    */   
/*    */   public static String getPort() {
/* 51 */     return port;
/*    */   }
/*    */   
/*    */   public static void setPort(String port) {
/* 55 */     conexao.port = port;
/*    */   }
/*    */   
/*    */   public static String getUser() {
/* 59 */     return user;
/*    */   }
/*    */   
/*    */   public static void setUser(String user) {
/* 63 */     conexao.user = user;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public conexao(String host, String port, String bd, String user, String password) {
/* 69 */     conexao.host = host;
/* 70 */     conexao.port = port;
/* 71 */     conexao.bd = bd;
/* 72 */     conexao.user = user;
/* 73 */     conexao.password = password;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Connection getConnection() throws SQLException, ClassNotFoundException {
/* 84 */     Class.forName("com.mysql.jdbc.Driver");
/* 85 */     return DriverManager.getConnection("jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "mydb", "root", "123456789");
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\DataAcessLayer\conexao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */