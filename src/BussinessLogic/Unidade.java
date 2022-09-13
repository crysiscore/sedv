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
         @Table(name="unidade")
/*    */ public class Unidade
/*    */ {
           @Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           @Column(name="id_unidade")
/*    */   private int Cod_unidade;
           
           @Column(name="descricao_unidade")
/*    */   public String descricao_unidade;


            
            @OneToMany (mappedBy ="unidade" )
/*    */    private Produto produto;
/*    */   
/*    */   public int getCod_unidade() {
/* 17 */     return this.Cod_unidade;
/*    */   }
/*    */   
/*    */   public void setCod_unidade(int Cod_unidade) {
/* 21 */     this.Cod_unidade = Cod_unidade;
/*    */   }
/*    */   
/*    */   public String getDescricao_Unidade() {
/* 25 */     return this.descricao_unidade;
/*    */   }
/*    */   
/*    */   public void setDescricao_Unidade(String descricao_unidade) {
/* 29 */     this.descricao_unidade = descricao_unidade;
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\BussinessLogic\Unidade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */