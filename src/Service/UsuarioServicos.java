/*     */ package Service;
/*     */ 
/*     */ import BussinessLogic.Previlegio;
/*     */ import BussinessLogic.Usuario;
/*     */ import DataAcessLayer.UsuarioDAO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UsuarioServicos
/*     */ {
/*  25 */   private UsuarioDAO UsuarioDAO = new UsuarioDAO();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cadastrarUsuario(Usuario u) throws SQLException {
/*  32 */     UsuarioDAO us = new UsuarioDAO();
/*  33 */     us.cadastrarusuario(u.getNome(), u.getSenha(), u.getCategoria());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void atualizaUsuario(Usuario u) throws SQLException {
/*  39 */     UsuarioDAO da = new UsuarioDAO();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean AutenticarUsuario(String user, String pass) {
/*  45 */     boolean isUser = false;
/*  46 */     UsuarioDAO bi = new UsuarioDAO();
/*     */     
/*     */     try {
/*  49 */       int a = bi.IsUser(user, pass);
/*  50 */       if (a == 1)
/*  51 */       { isUser = true; }
/*  52 */       else { isUser = false; }
/*     */     
/*  54 */     } catch (SQLException ex) {}
/*  55 */     return isUser;
/*     */   }
/*     */   
/*     */   public void Login(String user, String pass) throws SQLException {
/*  59 */     this.UsuarioDAO.Login(user, pass);
/*     */   }
/*     */ 
/*     */   
/*     */   public int IDBibliotecarioAutenticado(String user) throws SQLException {
/*  64 */     UsuarioDAO b = new UsuarioDAO();
/*  65 */     return b.idFuncionarioAutenticado(user);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void Logout() throws SQLException {
/*  71 */     this.UsuarioDAO.Logout();
/*     */   }
/*     */ 
/*     */   
/*     */   public Previlegio VerificarPrevilegios() throws SQLException {
/*  76 */     Previlegio p = new Previlegio();
/*  77 */     ResultSet rs = this.UsuarioDAO.ListarPrevilegios();
/*  78 */     rs.next();
/*  79 */     String a = rs.getString("prodPodeRegistar");
/*  80 */     String b = rs.getString("prodPodeAlterarDados");
/*  81 */     String c = rs.getString("ProdPodeAdicionarStock");
/*  82 */     String d = rs.getString("ProdPodeRegistarCateg");
/*  83 */     String e = rs.getString("ProdPodeRegistarUnida");
/*  84 */     String f = rs.getString("relVendasRecentes");
/*  85 */     String g = rs.getString("relVendasPorDaata");
/*  86 */     String h = rs.getString("relProdutosMaisVendidos");
/*     */     
/*  88 */     if (a.contentEquals("sim"))
/*     */     {
/*  90 */       p.setProdutosPodeRegistar(true);
/*     */     }
/*     */     
/*  93 */     if (b.contentEquals("sim"))
/*     */     {
/*  95 */       p.setProdutosPodeAlterarDados(true);
/*     */     }
/*     */     
/*  98 */     if (c.contentEquals("sim"))
/*     */     {
/* 100 */       p.setProdutosPodeAdicionarStock(true);
/*     */     }
/*     */     
/* 103 */     if (d.contentEquals("sim"))
/*     */     {
/* 105 */       p.setProdutosPodeRegistarCat(true);
/*     */     }
/*     */     
/* 108 */     if (e.contentEquals("sim"))
/*     */     {
/* 110 */       p.setProdutosPodeRegistarUnid(true);
/*     */     }
/*     */     
/* 113 */     if (f.contentEquals("sim"))
/*     */     {
/* 115 */       p.setRelatoriosVendasRecentes(true);
/*     */     }
/*     */     
/* 118 */     if (g.contentEquals("sim"))
/*     */     {
/* 120 */       p.setRelatoriosVendasPorDaata(true);
/*     */     }
/*     */     
/* 123 */     if (h.contentEquals("sim"))
/*     */     {
/* 125 */       p.setRelatoriosProdutosMaisVendidos(true);
/*     */     }
/*     */ 
/*     */     
/* 129 */     return p;
/*     */   }
/*     */   
/*     */   public void EditarPrevilegios(Previlegio p) throws SQLException {
/* 133 */     String a = "nao";
/* 134 */     String b = "nao";
/* 135 */     String c = "nao";
/* 136 */     String d = "nao";
/* 137 */     String e = "nao";
/* 138 */     String f = "nao";
/* 139 */     String g = "nao";
/* 140 */     String h = "nao";
/*     */     
/* 142 */     if (p.isProdutosPodeRegistar() == true)
/*     */     {
/* 144 */       a = "sim";
/*     */     }
/*     */     
/* 147 */     if (p.isProdutosPodeAlterarDados() == true)
/*     */     {
/* 149 */       b = "sim";
/*     */     }
/*     */     
/* 152 */     if (p.isProdutosPodeAdicionarStock() == true)
/*     */     {
/* 154 */       c = "sim";
/*     */     }
/*     */     
/* 157 */     if (p.isProdutosPodeRegistarCat() == true)
/*     */     {
/* 159 */       d = "sim";
/*     */     }
/*     */     
/* 162 */     if (p.isProdutosPodeRegistarUnid() == true)
/*     */     {
/* 164 */       e = "sim";
/*     */     }
/*     */     
/* 167 */     if (p.isRelatoriosVendasRecentes() == true)
/*     */     {
/* 169 */       f = "sim";
/*     */     }
/* 171 */     if (p.isRelatoriosVendasPorDaata() == true)
/*     */     {
/* 173 */       g = "sim";
/*     */     }
/* 175 */     if (p.isRelatoriosProdutosMaisVendidos() == true)
/*     */     {
/* 177 */       h = "sim";
/*     */     }
/*     */     
/* 180 */     this.UsuarioDAO.PrevilegiosUsuario(a, b, c, d, e, f, g, h);
/*     */   }
/*     */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\Service\UsuarioServicos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */