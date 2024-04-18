/*    */ package BussinessLogic;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Usuario
/*    */ {
/*    */   private Integer Cod_Funcionario;
/*    */   public String Nome;
/*    */   public String Senha;
/*    */   public String Categoria;
           public String Status;

/*    */ public String getStatus() {
        return Status;
    }


    public void setStatus(String Status) {
        this.Status = Status;
    }

/*    */
    public String getCategoria() {
        /* 19 */     return this.Categoria;
        /*    */
    }
 public void setCategoria(String Categoria) {
/* 23 */     this.Categoria = Categoria;
/*    */   }
/*    */   
/*    */   public Integer getCod_Funcionario() {
/* 27 */     return this.Cod_Funcionario;
/*    */   }
/*    */   
/*    */   public void setCod_Funcionario(Integer Cod_Funcionario) {
/* 31 */     this.Cod_Funcionario = Cod_Funcionario;
/*    */   }
/*    */   
/*    */   public String getNome() {
/* 35 */     return this.Nome;
/*    */   }
/*    */   
/*    */   public void setNome(String Nome) {
/* 39 */     this.Nome = Nome;
/*    */   }
/*    */   
/*    */   public String getSenha() {
/* 43 */     return this.Senha;
/*    */   }
/*    */   
/*    */   public void setSenha(String Senha) {
/* 47 */     this.Senha = Senha;
/*    */   }


/*    */

    public Usuario() {
    }

    public Usuario(String Nome, String Senha, String Categoria, String Status) {
        this.Nome = Nome;
        this.Senha = Senha;
        this.Categoria = Categoria;
        this.Status = Status;
    }
    
    
        public Usuario(Integer Cod_Funcionario,String Nome, String Senha, String Categoria, String Status) {
        this.Cod_Funcionario = Cod_Funcionario;
        this.Nome = Nome;
        this.Senha = Senha;
        this.Categoria = Categoria;
        this.Status = Status;
    }

 }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\BussinessLogic\Usuario.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */