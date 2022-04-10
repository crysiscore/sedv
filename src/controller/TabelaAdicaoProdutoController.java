/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import BussinessLogic.Produto;
import DataAcessLayer.UsuarioDAO;
import DataAcessLayer.ProdutoDAO;
import BussinessLogic.Stock;
import BussinessLogic.Usuario;
import Model.StockModel;
import Service.StockServicos;
import Service.UsuarioServicos;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Neusia
 */
public class TabelaAdicaoProdutoController implements Initializable {
     
    @FXML
    private TableView <Stock> tableviewAdicionarStock;

    @FXML
    private TableColumn<Stock, Integer> columnCodigoProduto;

    @FXML
    private TableColumn<Stock, Integer> columnQuantidade;

    @FXML
    private TableColumn<Stock, Date> columnData;

    @FXML
    private TableColumn<Stock, String> columnNumeroLote;

    @FXML
    private TableColumn<Stock, String> columnFabricante;

    @FXML
    private Button buttonAdicionarStock;

    @FXML
    private Button buittonRegistarStock;
    @FXML
    private Label labelcopr;
    @FXML
    private Button buttonCancelar;
     @FXML
    private Label labelCodUsuario;
   UsuarioServicos usuarioServicos ;
    Stock stock;
    StockServicos stServico;
     Usuario usuario;
     private CadStockController cadStockController;
     private Produto produto;
     private StockModel stockModel = new StockModel();


 //final static String ficheiro_gravado = "C:\\app\\stock.txt";
       
  
   

    public void ReceberDadosProduto(Produto produto) {
        this.produto = produto;
        this.labelcopr.setText(produto.getCod_produto().toString());
        
    }
       public Usuario getUsuario() {
        return usuario;
    }
       
         public void  receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;
      
        this.labelCodUsuario.setText(usuario.getCod_Funcionario().toString());
        this.labelCodUsuario.setVisible(false);
       // return usuario;
         }
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
      this.columnCodigoProduto.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("produto_Cod_Produto"));
      
       this.columnQuantidade.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("quantidade_recebida"));
       
       this.columnData.setCellValueFactory(new PropertyValueFactory<Stock, Date>("data_entrada"));
       this.columnNumeroLote.setCellValueFactory(new PropertyValueFactory<Stock, String>("numero_lote"));
       this.columnFabricante.setCellValueFactory(new PropertyValueFactory<Stock, String>("fabricante"));
      //tableviewAdicionarStock.setItems(stockModel.getStockList());
      //ObservableList<Stock> lista = tableviewAdicionarStock.getItems();
      
       
    }    
    
    
     
   

    

    
         @FXML
    public void handleMenuItemStockAdicionar() throws IOException {
    
/*     */     try {
/* 159 */       
/* 165 */          //String codUsuario = this.labelCodUsuario.getText();
/* 166 */        
/*     */     FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/BuscaProdutos.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
         //BuscaProdutosController buscaProdutosController= loader.getController();
         
         BuscaProdutosController buscaProdutosController= loader.<BuscaProdutosController>getController();
         
         //buscaProdutosController.UserInfo(codUsua);
         usuarioServicos = new UsuarioServicos();
       
        buscaProdutosController.receberdadosUsuario(usuario);
       
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registo de Stock");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setMaximized(false);
        dialogStage.setResizable(false);
        // Mostra o Dialog e espera atÃ© que o usuÃ¡rio o feche
        dialogStage.show();
        
/*     */     }
/* 201 */     catch (Exception ex) {
/* 202 */       System.out.println("" + ex + ex.getLocalizedMessage());
                System.out.println("" + ex.toString());
/*     */     } 
/*     */   }
    
    
    
        public void CancelarButtonOnAction(ActionEvent event){
        Stage stage =(Stage) buttonCancelar.getScene().getWindow();
        stage.close();
    }
        
        
            @FXML

     void removerStock(ActionEvent event)  {
         
         
        // int selectedID=tableViewStock.getSelectionModel().getSelectedIndex();
         //tableViewStock.getItems().remove(selectedID);
     }
     
}
