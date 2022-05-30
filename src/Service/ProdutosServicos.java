/*     */ package Service;

/*     */
 /*     */ import BussinessLogic.DetalhesVenda;
/*     */ import BussinessLogic.Produto;
/*     */ import BussinessLogic.Venda;
/*     */ import DataAcessLayer.ProdutoDAO;
/*     */ import DataAcessLayer.VendaDao;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
import javax.swing.JOptionPane;

/*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public class ProdutosServicos /*     */ {

    /*     */ ResultSet rs;
    /*     */    private Produto produto;
    /*  30 */    private ProdutoDAO prodDAO = new ProdutoDAO();
    /*  31 */    private VendaDao vendaDao = new VendaDao();

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public String[] GetUnidades() throws SQLException {
        /*  43 */ String[] unidades = new String[0];
        /*  44 */ ProdutoDAO ds = new ProdutoDAO();
        /*     */
 /*  46 */ ResultSet rs = ds.popularunidade();
        /*  47 */ while (rs.next()) {
            /*     */
 /*  49 */ int i = 1;
            /*  50 */ unidades[i] = rs.getString("Descricao_Unidade");
            /*  51 */ i++;
            /*     */        }
        /*  53 */ return unidades;
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public String[] GetCategorias() throws SQLException {
        /*  60 */ String[] categorias = new String[0];
        /*  61 */ ProdutoDAO cpd = new ProdutoDAO();
        /*  62 */ ResultSet rs = cpd.populacombocategoria();
        /*     */
 /*  64 */ while (rs.next()) {
            /*     */
 /*  66 */ int i = 1;
            /*  67 */ categorias[i] = rs.getString("");
            /*  68 */ i++;
            /*     */        }
        /*  70 */ return categorias;
        /*     */    }

    /*     */
 /*     */
 /*     */ public int CapturaIdUnidade(String Nom) throws SQLException {
        /*  75 */ ProdutoDAO ps = new ProdutoDAO();
        /*  76 */ ResultSet rs = ps.capturaId(Nom);
        /*     */
 /*  78 */ rs.next();
        /*  79 */ int CodUnidade = rs.getInt("idUnidade");
        /*     */
 /*  81 */ return CodUnidade;
        /*     */    }

    /*     */
 /*     */ public int CapturaIdCategoria(String Nome) throws SQLException {
        /*  85 */ ProdutoDAO pp = new ProdutoDAO();
        /*  86 */ ResultSet rs = pp.capturaIdcategoria(Nome);
        /*     */
 /*  88 */ rs.next();
        /*  89 */ int CodCategoria = rs.getInt("idCategoria");
        /*     */
 /*  91 */ return CodCategoria;
        /*     */    }

    /*     */
 /*     */
 /*     *//*     */
 /*     */
 /*     */
 /*     */ public Produto getDetalhesProduto(int codProduto) throws SQLException {
        /*  97 */ this.rs = this.prodDAO.getDetalhesProduto(codProduto);
        /*  98 */ this.rs.next();
        /*  99 */ this.produto = new Produto();
        /* 100 */ this.produto.setCod_produto(Integer.valueOf(this.rs.getInt("Codigo")));
        /* 101 */ this.produto.setNome(this.rs.getString("nome"));
        /* 102 */ this.produto.setPreco_unitario(Double.valueOf(this.rs.getDouble("Preco")));
        /* 104 */ this.produto.setUnidade(this.rs.getString("Unidade"));
        /* 105 */ this.produto.setCategoria(this.rs.getString("Categoria"));
        /* 106 */ return this.produto;
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */ public void VenderProduto(Venda v) throws SQLException {
        /* 112 */ this.vendaDao.VenderProduto(v.getTotal_Venda().doubleValue(), v.getNome_cliente(), v.getUsuario_Cod_Funcionario().intValue());
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void DetalhesVenda(DetalhesVenda dv) throws SQLException {
        /* 119 */ this.vendaDao.DetalhesVenda(dv.getProduto_Cod_produto().intValue(), dv.getVenda_Cod_produto().intValue(), dv.getPreco().doubleValue(), dv.getQuantidade().intValue());
        /*     */    }

    /*     */
 /*     */
 /*     *//*     */
 /*     */
 /*     */
 /*     */ public boolean RegistarProduto(Produto prod) throws SQLException{
            boolean status = false;
            rs = prodDAO.RegistarProduto(prod);
                      rs.next();
                          if( rs.getString(1).contentEquals("Sucesso")){
                                    status = true;
                          }                  
        /*     */
        return status;
    }

    /*     *//*     */
 /*     */ public void AdicionarStock(Produto produto, int quant) {
        /*     */ try {
            /* 128 */ int codigoProduto = produto.getCod_produto().intValue();
            /* 129 */ this.prodDAO.AdicionarStock(codigoProduto, quant);
            /*     */
 /*     */        } /* 132 */ catch (Exception e) {
            /* 133 */ System.out.println("" + e);
            /*     */        }
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public int CodVendacorrente() throws SQLException {
        /* 142 */ this.rs = this.vendaDao.CodVendaCorrente();
        /* 143 */ this.rs.next();
        /* 144 */ int codVendacorrente = this.rs.getInt("Cod_venda");
        /* 145 */ return codVendacorrente;
        /*     */    }
    /*     */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\Service\ProdutosServicos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
