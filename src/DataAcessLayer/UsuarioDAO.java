/*     */ package DataAcessLayer;
/*     */ 
import BussinessLogic.Usuario;
/*     */ import java.sql.CallableStatement;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UsuarioDAO
/*     */ {
/*     */   ResultSet rs;
/*     */   CallableStatement cs;
/*     */   PreparedStatement ps;
/*     */   Connection connect;
            private Usuario usuario;
/*     */   
/*     */   public UsuarioDAO() {
/*     */     try {
/*  22 */       conexao conexao = new conexao();
/*  23 */       this.connect = conexao.getConnection();
/*     */     }
/*  25 */     catch (SQLException e) {
/*     */ 
/*     */      System.out.println("Erro ao conectar a DB:" + e.toString());
/*     */     }
/*  29 */     catch (ClassNotFoundException e) {
                System.out.println("Erro ao conectar a DB:" + e.toString());}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cadastrarusuario(String nome, String categoria, String senha) throws SQLException {
/*  38 */     this.cs = this.connect.prepareCall("{call proc_usuario(null,?,?,?)}");
/*     */     
/*  40 */     this.cs.setString(1, nome);
/*  41 */     this.cs.setString(2, categoria);
/*  42 */     this.cs.setString(3, senha);
/*  43 */     this.cs.executeQuery();
/*     */   }
/*     */ 
/*     */    public ResultSet RegistarUsuario(Usuario usuario) throws SQLException {
/* 103 */     cs = this.connect.prepareCall("{call Cadastro_Usuario(?,?,?,?)}");
              cs.setString(1,usuario.getNome());
/* 104 */     cs.setString(2, usuario.getSenha());
              cs.setString(3, usuario.getCategoria());   
              cs.setString(4, usuario.getStatus());
/* 110 */     this.rs = cs.executeQuery();         
              return this.rs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultSet Pesquisatabela(String nome) throws SQLException {
/*  52 */     this.cs = this.connect.prepareCall("{call  PesquisaUsuario(?)}");
/*  53 */     this.cs.setString(1, nome);
/*  54 */     this.rs = this.cs.executeQuery();
/*     */     
/*  56 */     return this.rs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void Apagarusuario(int codigFunc) throws SQLException {
/*  63 */     this.cs = this.connect.prepareCall("{call ApagarUsuario(?)}");
/*  64 */     this.cs.setInt(1, codigFunc);
/*  65 */     this.cs.executeQuery();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultSet ListarUsuarios() throws SQLException {
/*  72 */     this.cs = this.connect.prepareCall("{call  ListarUsuarios()}");
/*  73 */     this.rs = this.cs.executeQuery();
/*     */     
/*  75 */     return this.rs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int IsUser(String user, String pass) throws SQLException {
/*  82 */     String sql = "select count(nome) from `usuario` where nome =? and senha =?";
/*     */     
/*  84 */     PreparedStatement stmt = this.connect.prepareStatement(sql);
/*  85 */     stmt.setString(1, user);
/*  86 */     stmt.setString(2, pass);
/*  87 */     stmt.execute();
/*  88 */     ResultSet rs = stmt.executeQuery();
/*  89 */     rs.next();
/*  90 */     int isUser = Integer.parseInt(rs.getString("count(nome)"));
/*     */     
/*  92 */     return isUser;
/*     */   }
/*     */ 
/*     */   
/*     */   public void Login(String user, String pass) throws SQLException {
/*  97 */     this.cs = this.connect.prepareCall("{call Login(?,?)}");
/*  98 */     this.cs.setString(1, user);
/*  99 */     this.cs.setString(2, pass);
/* 100 */     this.cs.executeQuery();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void Logout() throws SQLException {
/* 108 */     this.cs = this.connect.prepareCall("{call Logout()}");
/* 109 */     this.cs.executeQuery();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idFuncionarioAutenticado(String user) throws SQLException {
/* 118 */     this.cs = this.connect.prepareCall("{call UsuarioAutenticado(?)}");
/* 119 */     this.cs.setString(1, user);
/* 120 */     this.rs = this.cs.executeQuery();
/* 121 */     this.rs.next();
/* 122 */     int idUsuario = this.rs.getInt("Cod_Funcionario");
/* 123 */     return idUsuario;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultSet editarusuario(Usuario usuario) throws SQLException {
/* 129 */     this.cs = this.connect.prepareCall("{call Editarusuario(?,?,?,?,?)}");
/*     */     
/* 131 */     this.cs.setInt(1, usuario.getCod_Funcionario());
/* 132 */     this.cs.setString(2, usuario.getNome());
/* 133 */     this.cs.setString(3, usuario.getSenha());
/* 134 */     this.cs.setString(4, usuario.getCategoria());
/* 134 */     this.cs.setString(5, usuario.getStatus());

/* 135 */     this.cs.executeQuery();

              return this.rs;
/*     */   }
/*     */ 

/*     */   
/*     */   public void PrevilegiosUsuario(String a, String b, String c, String d, String e, String f, String g, String h) throws SQLException {
/* 143 */     this.cs = this.connect.prepareCall("{call PrevilegiosFuncionario(?,?,?,?,?,?,?,?)}");
/* 144 */     this.cs.setString(1, a);
/* 145 */     this.cs.setString(2, b);
/* 146 */     this.cs.setString(3, c);
/* 147 */     this.cs.setString(4, d);
/* 148 */     this.cs.setString(5, e);
/* 149 */     this.cs.setString(6, f);
/* 150 */     this.cs.setString(7, g);
/* 151 */     this.cs.setString(8, h);
/* 152 */     this.cs.executeQuery();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultSet ListarPrevilegios() throws SQLException {
/* 159 */     this.cs = this.connect.prepareCall("{call  LISTARPREVILEGIOS()}");
/* 160 */     this.rs = this.cs.executeQuery();
/* 161 */     return this.rs;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResultSet getCategoriaUsuario(String user, String pass) throws SQLException {
/* 166 */     this.cs = this.connect.prepareCall("{call GetCategoriaUsuario(?,?)}");
/* 167 */     this.cs.setString(1, user);
/* 168 */     this.cs.setString(2, pass);
            
/* 169 */     this.rs = this.cs.executeQuery();
/* 170 */     return this.rs;
/*     */   }

            public ResultSet getDetalhesUsuario(int codUsuario) throws SQLException {
/*  82 */     this.cs = this.connect.prepareCall("{call  DetalhesUsuario(?)}");
/*  83 */     this.cs.setInt(1, codUsuario);
/*  84 */     this.rs = this.cs.executeQuery();
/*     */     
/*  86 */     return this.rs;
/*     */   }
/*     */ }

/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\DataAcessLayer\UsuarioDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */