/*    */ package BussinessLogic;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import javafx.scene.image.Image;

/*    */ 
          
/*    */ 
/*    */ 
/*    */   public class Produto
/*    */   {

    public Produto(StockLevel stock) {
        this.stock = stock;
    }


/*    */   private Integer Cod_produto;
/*    */   private String Nome;
/*    */   private Double Preco_unitario;
           private Double Preco_De_Compra;
/*    */   private String Unidade;
/*    */   private String Categoria;
/*    */   private String Descricao;
           private String foto;
           
           public Stock sk;
 
           public StockLevel stock;

           public DetalhesVenda detalhesVenda;


            //this.stock=new StockLevel();
          

    public DetalhesVenda getDetalhesVenda() {
        return detalhesVenda;
    }

    public void setDetalhesVenda(DetalhesVenda detalhesVenda) {
        this.detalhesVenda = detalhesVenda;
    }
    
           

    public StockLevel getStock() {
        return stock;
    }

    public void setStock(StockLevel stock) {
        this.stock = stock;
    }

    public Stock getSk() {
        return sk;
    }

    public void setSk(Stock sk) {
        this.sk = sk;
    }
   
            public Produto() {
        
                   }
            
           public Produto(Integer Cod_produto,String Nome, Double Preco_unitario, StockLevel stock) {
           this.Cod_produto = Cod_produto;
           this.Nome= Nome;
           this.Preco_unitario = Preco_unitario;
           this.stock=stock;
    }

            public Produto(Integer Cod_produto, String Nome) {
            this.Cod_produto=Cod_produto;
            this.Nome= Nome;
       
            }
            
            public Produto(Integer Cod_produto, String Nome,String Categoria,String Unidade,
            String Descricao,Double Preco_unitario,Double Preco_De_Compra,String foto, StockLevel stock) {
            this.Cod_produto=Cod_produto;
            this.Nome= Nome;
            this.Preco_unitario=Preco_unitario;
            this.Preco_De_Compra=Preco_De_Compra;
            this.Unidade= Unidade;
            this.Categoria= Categoria;
            this.Descricao= Descricao;
            this.foto= foto;
            //this.stock=new StockLevel();
            this.stock=stock;
            
            
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

    public Double getPreco_De_Compra() {
        return Preco_De_Compra;
    }

    public void setPreco_De_Compra(Double Preco_De_Compra) {
        this.Preco_De_Compra = Preco_De_Compra;
    }

/*    */   
/*    */   public Produto(Integer Cod_produto) {
/* 71 */     this.Cod_produto = Cod_produto;
/*    */   }
 
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

            public String getFoto() {
            return foto;
           }

            public void setFoto(String foto) {
            this.foto = foto;
            }

          }
