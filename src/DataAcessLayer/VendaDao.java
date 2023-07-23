/*    */ package DataAcessLayer;
/*    */ 
import BussinessLogic.DetalhesVenda;
import BussinessLogic.Venda;
import com.sun.javafx.collections.MappingChange.Map;
/*    */ import java.sql.CallableStatement;
/*    */ import java.sql.Connection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.HashMap;
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
           
          ObservableList <DetalhesVenda> detalhesVenda =FXCollections.observableArrayList();
/*    */   
/*    */   public VendaDao() {
/*    */     try {
/* 25 */       conexao conexao = new conexao();
/* 26 */       this.connect = conexao.getConnection();
/*    */     }
/* 28 */     catch (SQLException e) {
/*    */ 
/*    */     
/* 31 */     } catch (ClassNotFoundException e) {}
/*    */   }
/*    */    
              public void RegistarDetalhesVenda( ObservableList <DetalhesVenda> detalhesVenda ) throws SQLException {
/* 103 */    
              this.cs = this.connect.prepareCall("{call RegistarDetalhesVenda(?,?,?,?)}");
              try{
/* 104 */     for(int i = 0; i < detalhesVenda.size(); i++) {
              this.cs.setInt(1, detalhesVenda.get(i).getProduto_Cod_produto());
/* 106 */     this.cs.setDouble(2, detalhesVenda.get(i).getPreco());
/* 107 */     this.cs.setDouble(3, detalhesVenda.get(i).getQuantidade());
              this.cs.setDouble(4, detalhesVenda.get(i).getSubtotal());
/* 108 */ 
/* 110 */     this.cs.executeQuery();
/* 111 */     
/*     */   }
                  } catch(SQLException ex){ 
                     Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
  }
/*    */ 
/*    */     public ResultSet RegistarVenda(Venda venda) throws SQLException {
/* 103 */     cs = this.connect.prepareCall("{call VENDA(null,?,?,?,?,?,?)}");
/* 104 */     cs.setDate(1, venda.getData_Venda());
/* 105 */     cs.setDouble(2, venda.getTotal_Venda());
              cs.setInt(3, venda.getUsuario_Cod_Funcionario());
              cs.setString(4, venda.getForma_Pagamento());  
              cs.setInt(5, venda.getNuit_cliente());
              cs.setString(6, venda.getNome_cliente());
              
/* 110 */     this.rs = cs.executeQuery();         
              return this.rs;
/*     */   }

/*     */   
            public ResultSet RegistarVendasemnuitesemnome(Venda venda) throws SQLException {
/* 103 */     cs = this.connect.prepareCall("{call VENDA(null,?,?,?,?,null,null)}");
/* 104 */     cs.setDate(1, venda.getData_Venda());
/* 105 */     cs.setDouble(2, venda.getTotal_Venda());
              cs.setInt(3, venda.getUsuario_Cod_Funcionario()); 
              cs.setString(4, venda.getForma_Pagamento());  
             // cs.setInt(5, venda.getNuit_cliente());
             // cs.setString(6, venda.getNome_cliente());
/* 110 */     this.rs = cs.executeQuery();         
              return this.rs;
/*     */   }
            

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
/*    */   public ResultSet Listagem_Venda() throws SQLException {
/*  74 */     this.cs = this.connect.prepareCall("{call  Lista_Venda()}");
/*  75 */     this.rs = this.cs.executeQuery();
/*     */     
/*  77 */     return this.rs;
/*     */   }
/*    */   
/*    */   public void DetalhesVenda(int codP, int codV, double preco, int quantid) throws SQLException {
/* 76 */     this.cs = this.connect.prepareCall("{call DETALHESVENDA(?,?,?,?)}");
/* 77 */     this.cs.setInt(1, codP);
/* 78 */     this.cs.setInt(2, codV);
/* 79 */     this.cs.setDouble(3, preco);
/* 80 */     this.cs.setInt(4, quantid);
/* 81 */     this.cs.executeQuery();
/*    */   }

              public ResultSet getDetalhesVenda(int Cod_Venda) throws SQLException {
/*  82 */     this.cs = this.connect.prepareCall("{call  Detalhes_Venda(?)}");
/*  83 */     this.cs.setInt(1, Cod_Venda);
/*  84 */     this.rs = this.cs.executeQuery();
/*     */     
/*  86 */     return this.rs;
/*     */   }
              


/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\DataAcessLayer\VendaDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */