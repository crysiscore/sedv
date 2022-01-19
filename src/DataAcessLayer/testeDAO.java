/*    */ package DataAcessLayer;
/*    */ 
/*    */ import java.sql.ResultSet;
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
/*    */ public class testeDAO
/*    */ {
/*    */   public static void main(String[] args) {
/*    */     try {
/* 18 */       UsuarioDAO u = new UsuarioDAO();
/* 19 */       ResultSet rs = u.getCategoriaUsuario("admin", "1234");
/* 20 */       rs.next();
/* 21 */       String categoriaUsuario = rs.getString("Categoria");
/* 22 */       System.out.println("" + categoriaUsuario);
/*    */     }
/* 24 */     catch (Exception e) {
/* 25 */       System.out.println("" + e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\DataAcessLayer\testeDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */