/*     */ package DataAcessLayer;
/*     */ 
import java.sql.Blob;
/*     */ import java.sql.CallableStatement;
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProdutoDAO
/*     */ {
/*     */   private ResultSet rs;
/*     */   private Connection connect;
/*  20 */   private CallableStatement cs = null;
/*     */ 
/*     */   
/*     */   public ProdutoDAO() {
/*     */     try {
/*  25 */       conexao conexao = new conexao();
/*  26 */       this.connect = conexao.getConnection();
/*     */     }
/*  28 */     catch (SQLException e) {
/*     */ 
/*     */     
/*  31 */     } catch (ClassNotFoundException e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultSet ProcurarProdutos(String parametro) throws SQLException {
/*  38 */     this.cs = this.connect.prepareCall("{call ProcurarProdutos(?)}");
/*  39 */     this.cs.setString(1, parametro);
/*  40 */     this.rs = this.cs.executeQuery();
/*  41 */     return this.rs;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResultSet SearchProdutoVenda(String parametro) throws SQLException {
/*  46 */     this.cs = this.connect.prepareCall("{call SearchProdutoVenda(?)}");
/*  47 */     this.cs.setString(1, parametro);
/*  48 */     this.rs = this.cs.executeQuery();
/*  49 */     return this.rs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void AdicionarStock(int codProduto, int quant) throws SQLException {
/*  55 */     this.cs = this.connect.prepareCall("{call AdicionarStock(?,?)}");
/*  56 */     this.cs.setInt(1, codProduto);
/*  57 */     this.cs.setInt(2, quant);
/*     */     
/*  59 */     this.cs.executeQuery();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultSet TodosProdutos() throws SQLException {
/*  66 */     this.cs = this.connect.prepareCall("{call TodosProdutos()}");
/*  67 */     this.rs = this.cs.executeQuery();
/*     */     
/*  69 */     return this.rs;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResultSet ListarProdutos() throws SQLException {
/*  74 */     this.cs = this.connect.prepareCall("{call  ListarProdutos()}");
/*  75 */     this.rs = this.cs.executeQuery();
/*     */     
/*  77 */     return this.rs;
/*     */   }
/*     */  
            // áo inves de mostrar o codigo da unidade e categoria mostra os respectivos nomes
             public ResultSet ListagemdeProduto() throws SQLException {
/*  74 */     this.cs = this.connect.prepareCall("{call  ListagemdeProduto()}");
/*  75 */     this.rs = this.cs.executeQuery();
/*     */     
/*  77 */     return this.rs;
/*     */   }
/*     */  
/*     */   
/*     */   public ResultSet getDetalhesProduto(int codProduto) throws SQLException {
/*  82 */     this.cs = this.connect.prepareCall("{call  DetalhesProdutos(?)}");
/*  83 */     this.cs.setInt(1, codProduto);
/*  84 */     this.rs = this.cs.executeQuery();
/*     */     
/*  86 */     return this.rs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultSet Pesquisartabela(String nome) throws SQLException {
/*  92 */     this.cs = this.connect.prepareCall("{call  PesquisarProdutos()}");
/*  93 */     this.cs.setString(1, nome);
/*  94 */     this.rs = this.cs.executeQuery();
/*     */ 
/*     */     
/*  97 */     return this.rs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void cadastrarprodutos(String nome, double preco_unitario, int unidade, int categoria, String descricao) throws SQLException {
/* 103 */     this.cs = this.connect.prepareCall("{call CadProduto(null,?,?,?,?,?,null)}");
/* 104 */     this.cs.setString(1, nome);
/* 105 */     this.cs.setDouble(2, preco_unitario);
/* 107 */     this.cs.setInt(3, unidade);
/* 108 */     this.cs.setInt(4, categoria);
/* 109 */     this.cs.setString(5, descricao);
             // this.cs.setBlob(6, foto);
              
              
/* 110 */     this.cs.executeQuery();
/* 111 */     this.cs.close();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ApagarProduto(int codProduto) throws SQLException {
/* 117 */     this.cs = this.connect.prepareCall("{call ApagarProduto(?)}");
/* 118 */     this.cs.setInt(1, codProduto);
/* 119 */     this.cs.executeQuery();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultSet popularunidade() throws SQLException {
/* 125 */     this.cs = this.connect.prepareCall("{call Populacombounidade()}");
/*     */     
/* 127 */     this.rs = this.cs.executeQuery();
/*     */     
/* 129 */     return this.rs;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResultSet populacombocategoria() throws SQLException {
/* 134 */     this.cs = this.connect.prepareCall("{call Populacombocategoria()}");
/* 135 */     this.rs = this.cs.executeQuery();
/*     */     
/* 137 */     return this.rs;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResultSet capturaId(String Descricao_Unidade) throws SQLException {
/* 142 */     this.cs = this.connect.prepareCall("{call capturaId(?)}");
/* 143 */     this.cs.setString(1, Descricao_Unidade);
/* 144 */     this.rs = this.cs.executeQuery();
/*     */     
/* 146 */     return this.rs;
/*     */   }
/*     */   
/*     */   public ResultSet capturaIdcategoria(String Descricao) throws SQLException {
/* 150 */     this.cs = this.connect.prepareCall("{call capturaIdcategoria(?)}");
/* 151 */     this.cs.setString(1, Descricao);
/* 152 */     this.rs = this.cs.executeQuery();
/* 153 */     return this.rs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void Editarproduto(int Codigo, String nome, double preco_unitario, int quantidadeStock, int unidade, int categoria, String descricao) throws SQLException {
/* 159 */     this.cs = this.connect.prepareCall("{call EditarProduto(?,?,?,?,?,?,?)}");
/* 160 */     this.cs.setInt(1, Codigo);
/* 161 */     this.cs.setString(2, nome);
/* 162 */     this.cs.setDouble(3, preco_unitario);
/* 163 */     this.cs.setInt(4, quantidadeStock);
/* 164 */     this.cs.setInt(5, unidade);
/* 165 */     this.cs.setInt(6, categoria);
/* 166 */     this.cs.setString(7, descricao);
/* 167 */     this.cs.executeQuery();
/*     */   }
/*     */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\DataAcessLayer\ProdutoDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */