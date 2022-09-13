/*    */ package BussinessLogic;
/*    */ 
/*    */ import java.util.Date;
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
         @Table(name="venda")

/*    */ public class Venda {
           
          @OneToOne(mappedBy="venda")

           @Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           @Column(name="id_venda")
           private Integer cod_venda;
          
           @Column(name="data_venda")
/*    */   private Date data_venda;
           
           @Column(name="iva")
           private Double iva;
           
           @Column(name="desconto")
           private Double desconto;
           
           @Column(name="forma_pagamento")
           private Double forma_pagamento;
           
           @Column(name="sub_total_venda")
           private Double sub_total_venda;
           
           @Column(name="total_venda")
/*    */   private Double total_venda;
           
           @OneToMany
           @JoinColumn(name="id_produto")
           private Produto prod;
  
           @OneToOne
           @JoinColumn(name="id_usuario")
/*    */   public Usuario usuario_Cod_Funcionario;

           @OneToOne (mappedBy ="venda" )
/*    */   private DetalhesVenda detalhes_venda;
           

}