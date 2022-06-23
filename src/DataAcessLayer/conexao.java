/*    */ package DataAcessLayer;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
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

/*    */


          public static String getBd() {
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
/*    */   ReadWriteTextFile rwTextFile = new ReadWriteTextFile();

    final static String ficheiro_gravado = "C:\\sedv\\credenciaisConn.txt";
    
    // definir uma lista de frutas
    List <String> credenciais;

    
    List <String> credenciaisConn;
    
    
    /**
     * Creates new form Frutinha
     */
              /*    */ 
    
      private List<String> LerDadosDeConexao(final String loction) {

         try{
            credenciaisConn = rwTextFile.readSmallTextFile(ficheiro_gravado);
            
            
         } catch (Exception e) {
            System.out.println("Ficheiro nao encontrado");
         }
          return credenciaisConn;
     }
        

    
/*    */   public conexao(String host, String port, String bd, String user, String password) {
/* 69 */     conexao.host = host;
/* 70 */     conexao.port = port;
/* 71 */     conexao.bd = bd;
/* 72 */     conexao.user = user;
/* 73 */     conexao.password = password;
  
/*    */   }



/*    */   public conexao() {
     // ler o ficheiro
     // extrair os valore lidos
             
/* 69 */          credenciaisConn = LerDadosDeConexao(ficheiro_gravado);
           // Lipara a textarea

               host = credenciaisConn.get(0).substring(5);
              port = credenciaisConn.get(1).substring(5);
                bd = credenciaisConn.get(2).substring(3);
               user = credenciaisConn.get(3).substring(9);
               password= credenciaisConn.get(4).substring(9);
               
                if (host.isEmpty()) {
/*     */       
/*  89 */       JOptionPane.showMessageDialog(null, "Nome do servidor em falta!");
/*     */     }
/*  92 */     else if (port.isEmpty()) {
/*     */      JOptionPane.showMessageDialog(null, "Numero da porta em falta");
/*     */     
/*     */     }
/* 102 */     else if (bd.isEmpty()) {
/*     */        JOptionPane.showMessageDialog(null, "Nome da base de dados em falta!");
/* 104 */       
/* 106 */     } else if (user.isEmpty()) {
/*     */       
/* 108 */       JOptionPane.showMessageDialog(null, "Nome do User em falta!");
/*     */     }
/* 112 */     else if (password.isEmpty()) {
/*     */       
/* 114 */       JOptionPane.showMessageDialog(null, "password em falta");
/* 115 */      
/*     */     } 
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
/* 85 */     return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + bd , user, password);
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\DataAcessLayer\conexao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */