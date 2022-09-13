/*    */ package BussinessLogic;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import javafx.scene.image.Image;
import javax.persistence.*;

/*    */ 
          
/*    */ 
/*    */ @Entity
         @Table(name="produto")
/*    */   public class Produto
/*    */   {
    
/*    */
           @Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           @Column(name="cod_produto")
           private Integer cod_produto;
           
          @OneToOne
          @MapsId
          @JoinColumn(name = "id_categoria")
          private Categoria Categoria;
          
          @Column(name="nome")
/*    */   private String Nome;
          
          @Column(name="preco_unitario")
/*    */   private Double Preco_unitario;
           
          @OneToOne
          @MapsId
          @JoinColumn(name = "unidade")
          private Unidade unidade;

   
/*    */   
          @Column(name="descricao")
/*    */   private String Descricao;
          
          @Column(name="nome")
           private String foto;
           
          @OneToOne (mappedBy ="prod" )
/*    */    private Stock stock1;
          
          @OneToMany (mappedBy ="prod" )
/*    */    private Venda venda;
          
          @OneToMany (mappedBy ="prod" )
/*    */    private StockLevel stockLevel;
          
           @ManyToOne
           @JoinColumn(name="id_detalhes")
           private DetalhesVenda detalhesVenda;
           
           public StockLevel stock;
  

   
            public Produto() {
        
                   }

            public Produto(Integer Cod_produto, String Nome) {
            this.cod_produto=Cod_produto;
            this.Nome= Nome;
       
            }
            
            public Produto(Integer cod_produto, String Nome,Categoria Categoria,Unidade Unidade,
            String Descricao,Double Preco_unitario,String foto, StockLevel stock) {
            this.cod_produto=cod_produto;
            this.Nome= Nome;
            this.Preco_unitario=Preco_unitario;
            this.unidade= Unidade;
            this.Categoria= Categoria;
            this.Descricao= Descricao;
            this.foto= foto;
            //this.stock=new StockLevel();
            this.stock=stock;
            
            
            }
/*    */   


          public StockLevel getStock() {
        return stock;
    }

    public void setStock(StockLevel stock) {
        this.stock = stock;
    }

      public Categoria getCategoria() {
        return Categoria;
      }

      public void setCategoria(Categoria Categoria) {
      this.Categoria = Categoria;
      }
               
          public Integer getCod_produto() {
/* 23 */     return this.cod_produto;
/*    */   }
/*    */   
/*    */   public void setCod_produto(Integer Cod_produto) {
/* 27 */     this.cod_produto = Cod_produto;
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

/*    */   
/*    */   public Produto(Integer Cod_produto) {
/* 71 */     this.cod_produto = Cod_produto;
/*    */   }
           
            public Unidade getUnidade() {
        return unidade;
    }

         public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

/*    */   
/*    */  // public String getUnidade() {
/* 89 */    // return this.Unidade;
/*    */  // }
/*    */   
/*    */  // public void setUnidade(String Unidade) {
/* 93 */    // this.Unidade = Unidade;
/*    */   //}

            public String getFoto() {
            return foto;
           }

            public void setFoto(String foto) {
            this.foto = foto;
            }

          }
