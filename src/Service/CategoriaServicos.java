/*    */ package Service;
/*    */ 
/*    */ import BussinessLogic.Categoria;
/*    */ import DataAcessLayer.CategoriaDAO;
import java.sql.ResultSet;
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
    
     ResultSet rs;

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


    public boolean registrarCategoria(Categoria categoria) throws SQLException {
        boolean status = false;
        ResultSet rs = null;

        try {
            rs = categoriaDAO.registrarCategoria(categoria);
            if (rs.next() && "Sucesso".equals(rs.getString(1))) {
                status = true;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao registrar a categoria: " + e.getMessage(), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return status;
    }
}


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\Service\CategoriaServicos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */