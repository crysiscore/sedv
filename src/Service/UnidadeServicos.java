/*    */ package Service;
/*    */ 
/*    */ import BussinessLogic.Unidade;
/*    */ import DataAcessLayer.UnidadeDao;
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
/*    */ public class UnidadeServicos
/*    */ {
/* 23 */   private UnidadeDao unidadeDao = new UnidadeDao();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cadastrarUnidade(Unidade r) throws SQLException {
/* 29 */     this.unidadeDao.cadastrarUnidade(r.getDescricao_Unidade());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atualizaUnidade(Unidade u) throws SQLException {
/* 35 */     this.unidadeDao.atualizaunidade(u.getCod_unidade(), u.getDescricao_Unidade());
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\Service\UnidadeServicos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */