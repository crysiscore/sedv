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
/*    */ public class DetalhesVenda
/*    */ {

 
    private Integer Produto_Cod_produto;
    public Integer Venda_Cod_venda;
    public Double Preco;
    public Double Quantidade;
    public Double Subtotal;
    public String Nome_Produto;
    public Venda venda;
    public Produto produto;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
        public DetalhesVenda(Integer Produto_Cod_produto, Double Preco, Double Quantidade, Double Subtotal, String Nome_Produto) {
        this.Produto_Cod_produto = Produto_Cod_produto;
        this.Preco = Preco;
        this.Quantidade = Quantidade;
        this.Subtotal = Subtotal;
        this.Nome_Produto = Nome_Produto;
    }
        
         public DetalhesVenda(Integer Produto_Cod_produto, Integer Venda_Cod_venda, Double Preco, Double Quantidade, Double Subtotal, String Nome_Produto) {
        this.Produto_Cod_produto = Produto_Cod_produto;
        this.Venda_Cod_venda = Venda_Cod_venda;
        this.Preco = Preco;
        this.Quantidade = Quantidade;
        this.Subtotal = Subtotal;
        this.Nome_Produto = Nome_Produto;
    }
        
 


    public DetalhesVenda() {
      
    }
       
    public Integer getProduto_Cod_produto() {
        return Produto_Cod_produto;
    }

    public void setProduto_Cod_produto(Integer Cod_Produto) {
        this.Produto_Cod_produto = Cod_Produto;
    }

    public Integer getVenda_Cod_venda() {
        return Venda_Cod_venda;
    }

    public void setVenda_Cod_venda(Integer Cod_Venda) {
        this.Venda_Cod_venda = Cod_Venda;
    }

    public Double getPreco() {
        return Preco;
    }

    public void setPreco(Double Preco) {
        this.Preco = Preco;
    }

    public Double getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(Double Quantidade) {
        this.Quantidade = Quantidade;
    }

    public Double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(Double Subtotal) {
        this.Subtotal = Subtotal;
    }

    public String getNome_Produto() {
        return Nome_Produto;
    }

    public void setNome_Produto(String Nome_Produto) {
        this.Nome_Produto = Nome_Produto;
    }

/*    */ 
           


/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\BussinessLogic\DetalhesVenda.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */