/*    */ package Service;
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
/*    */ public class TesteService
/*    */ {
/*    */   public static void main(String[] args) {
/*    */     try {
/* 18 */       UsuarioServicos us = new UsuarioServicos();
/*    */ 
/*    */ 
/*    */       
/* 22 */       System.out.println(" Username " + us.IDBibliotecarioAutenticado(""));
/*    */     }
/*    */     catch (Exception e) {
/*    */       
/* 26 */       System.out.println("" + e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\Service\TesteService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */