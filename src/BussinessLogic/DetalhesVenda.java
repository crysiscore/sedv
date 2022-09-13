/*    */ package BussinessLogic;

import java.util.Set;
        import javax.persistence.Column;
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
         @Table(name="detalhesvenda")
/*    */ public class DetalhesVenda
/*    */ {
           @Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           @Column(name="id_detalhes")
           private Integer id_detalhes;
           
      
           @OneToMany (mappedBy ="detalhesVenda" )
/*    */   private Produto produto;
           
           @ManyToOne
           @JoinColumn(name = "id_venda")
           private Venda venda;
           
           @Column(name="preco")
/*    */   public Double Preco;
           
           @Column(name="quantidade")
/*    */   public Integer Quantidade;
/*    */   
/*    */   public Double getPreco() {
/* 19 */     return this.Preco;
/*    */   }
/*    */   
/*    */   public void setPreco(Double Preco) {
/* 23 */     this.Preco = Preco;
/*    */   }
/*    */   

/*    */   
/*    */   public Integer getQuantidade() {
/* 35 */     return this.Quantidade;
/*    */   }
/*    */   
/*    */   public void setQuantidade(Integer Quantidade) {
/* 39 */     this.Quantidade = Quantidade;
/*    */   }
/*    */   
/* 
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\BussinessLogic\DetalhesVenda.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */