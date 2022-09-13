/*    */ package BussinessLogic;

import javax.persistence.*;

/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
         @Table(name="usuario")
/*    */ public class Usuario
/*    */ {
           @Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           @Column(name="id_usuario")
/*    */   private Integer Cod_Funcionario;
           
           @Column(name="nome")
/*    */   private String Nome;
           
           @Column(name="senha")
/*    */   private String Senha;
           
           @Column(name="categoria")
/*    */   private String Categoria;
           
           @Column(name="esta_autenticado")
           private String esta_autenticado;
            
           @OneToMany (mappedBy ="usuario_Cod_Funcionario" )
/*    */    private Factura factura;


/*    */   @OneToMany (mappedBy ="usuario_Cod_Funcionario" )
/*    */    private Stock stock;

           @OneToMany (mappedBy ="usuario_Cod_Funcionario" )
/*    */    private Venda venda;

/*    */   public String getCategoria() {
/* 19 */     return this.Categoria;
/*    */   }
/*    */   
/*    */   public void setCategoria(String Categoria) {
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
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\BussinessLogic\Usuario.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */