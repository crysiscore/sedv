/*    */ package BussinessLogic;
/*    */ import javax.persistence.*;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
         @Table(name="categoria")
/*    */ public class Categoria
/*    */ {
            
           @Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           @Column(name="id_categoria")
            private Integer idCategoria;
           
           @OneToOne(mappedBy = "Categoria")
           @PrimaryKeyJoinColumn
           private Produto produto;
           
           @Column(name="nome")
/*    */   public String Nome;
          
/*    */   
/*    */   public String getNome() {
/* 17 */     return this.Nome;
/*    */   }
/*    */   
/*    */   public void setNome(String Nome) {
/* 21 */     this.Nome = Nome;
/*    */   }
/*    */   
/*    */   public Integer getIdCategoria() {
/* 25 */     return this.idCategoria;
/*    */   }
/*    */   
/*    */   public void setIdCategoria(Integer idCategoria) {
/* 29 */     this.idCategoria = idCategoria;
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\BussinessLogic\Categoria.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */