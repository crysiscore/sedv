/*    */ package BussinessLogic;
        import java.sql.Blob;

/*    */ 
          
/*    */ 
/*    */ 
/*    */   public class Produto
/*    */   {
/*    */   public Integer Cod_produto;
/*    */   public String Nome;
/*    */   public Double Preco_unitario;
/*    */   //public Integer QuantidadeStock;
/*    */   public String Unidade;
/*    */   public String Categoria;
/*    */  // public Integer QuantidadeUnidade;
/*    */   public String Descricao;
           public Blob foto;
           
           
            public Produto() {
        
                   }

            public Produto(Integer Cod_produto, String Nome) {
            this.Cod_produto=Cod_produto;
            this.Nome= Nome;
       
            }
/*    */   
               
 public Integer getCod_produto() {
/* 23 */     return this.Cod_produto;
/*    */   }
/*    */   
/*    */   public void setCod_produto(Integer Cod_produto) {
/* 27 */     this.Cod_produto = Cod_produto;
/*    */   }
/*    */   
/*    */   public String getDescricao() {
/* 31 */     return this.Descricao;
/*    */   }
/*    */   
/*    */   public void setDescricao(String Descricao) {
/* 35 */     this.Descricao = Descricao;
/*    */   }
/*    */   
/*    */   public String getNome() {
/* 39 */     return this.Nome;
/*    */   }
/*    */   
/*    */   public void setNome(String Nome) {
/* 43 */     this.Nome = Nome;
/*    */   }
/*    */   
/*    */   public Double getPreco_unitario() {
/* 47 */     return this.Preco_unitario;
/*    */   }
/*    */   
/*    */   public void setPreco_unitario(Double Preco_unitario) {
/* 51 */     this.Preco_unitario = Preco_unitario;
/*    */   }
/*    */   
/*    */  // public Integer getQuantidadeStock() {
/* 55 */     //return this.QuantidadeStock;
/*    */   //}
/*    */   
/*    */   //public void setQuantidadeStock(Integer QuantidadeStock) {
/* 59 */    // this.QuantidadeStock = QuantidadeStock;
/*    */  // }
/*    */   
/*    */   //public Integer getQuantidadeUnidade() {
/* 63 */     //return this.QuantidadeUnidade;
/*    */  // }
/*    */   
/*    */   //public void setQuantidadeUnidade(Integer QuantidadeUnidade) {
/* 67 */    // this.QuantidadeUnidade = QuantidadeUnidade;
/*    */   //}
/*    */   
/*    */   public Produto(Integer Cod_produto) {
/* 71 */     this.Cod_produto = Cod_produto;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */  
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCategoria() {
/* 81 */     return this.Categoria;
/*    */   }
/*    */   
/*    */   public void setCategoria(String Categoria) {
/* 85 */     this.Categoria = Categoria;
/*    */   }
/*    */   
/*    */   public String getUnidade() {
/* 89 */     return this.Unidade;
/*    */   }
/*    */   
/*    */   public void setUnidade(String Unidade) {
/* 93 */     this.Unidade = Unidade;
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\BussinessLogic\Produto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */