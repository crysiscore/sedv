/*    */ package Service;
/*    */ 
/*    */ import BussinessLogic.Categoria;
/*    */ import DataAcessLayer.CategoriaDAO;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CategoriaServicos
/*    */ {
/* 24 */   private CategoriaDAO categoriaDAO = new CategoriaDAO();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cadastrarcategoria(Categoria r) throws SQLException {
/* 30 */     this.categoriaDAO.cadastrarcategoria(r.getNome());
/*    */   }
/*    */ 
/*    */   
/*    */   public void atualizacategoria(Categoria u) throws SQLException {
/* 35 */     this.categoriaDAO.atualizacategoria(u.getIdCategoria().intValue(), u.getNome());
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\Service\CategoriaServicos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */