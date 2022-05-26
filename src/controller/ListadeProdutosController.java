/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Produto;
import BussinessLogic.Usuario;
import DataAcessLayer.ProdutoDAO;
import Service.UsuarioServicos;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Neusia
 */
public class ListadeProdutosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button buttonCancelar;

    @FXML
    private ImageView imageViewAdicionarProduto;
    
    @FXML
    private TableView<Produto> tableViewListaProdutos;

    @FXML
    private TableColumn<Produto, String> tableColumnCategoria;

    @FXML
    private TableColumn<Produto, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Produto, String> tableColumnDescricao;

    @FXML
    private TableColumn<Produto, String> tableColumnNome;

    @FXML
    private TableColumn<Produto, Double> tableColumnPreco;

    @FXML
    private TableColumn<Produto, String> tableColumnUnidade;

    @FXML
    private TextField textFieldPesquisaProdutos;
    
    
    Produto produto;
    ObservableList <Produto> produtoObservableList =FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           ProdutoDAO produtodao = new ProdutoDAO();
             try {
                
                 ResultSet rs=produtodao.ListagemdeProduto();
                 
                 while(rs.next()){
                 Integer QueryProductId= rs.getInt("Cod_produto");
                 String QueryNome = rs.getString("Nome");
                 String QueryCategoria = rs.getString("Categoria");
                 String QueryUnidade = rs.getString("Unidade");
                 String QueryDescricao = rs.getString("Descricao");
                 Double QueryPreco = rs.getDouble("Preco_unitario");
                 
                 produtoObservableList.add(new Produto(QueryProductId,QueryNome,QueryCategoria,QueryUnidade,
                 QueryDescricao,QueryPreco));
                 }
                 
                 tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("Cod_produto"));
                 tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
                 tableColumnCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
                 tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
                 tableColumnUnidade.setCellValueFactory(new PropertyValueFactory<>("Unidade"));
                 tableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("Preco_unitario"));
                 
                 tableViewListaProdutos.setItems(produtoObservableList);
                 
                 
                 FilteredList<Produto> filteredData =new FilteredList<> (produtoObservableList, b ->true);
                 
                 textFieldPesquisaProdutos.textProperty().addListener((observable, oldValue, newValue) ->{
                 filteredData.setPredicate(produto -> {
                     if(newValue.isEmpty()|| newValue == null){
                         
                         return true;
                     }
                     
                     
                     String searchKeyword=newValue.toLowerCase();
                     
                     if(produto.getCod_produto().toString().indexOf(searchKeyword)>-1){
                         return true;
                     }else if(produto.getNome().toLowerCase().indexOf(searchKeyword)>-1){
                         return true;
                         
                         
                                
                     }else
                         
                     return false;
                     
                 });
                 
             });
                 
                 SortedList <Produto> sortdData = new SortedList <>(filteredData);
                 
                 sortdData.comparatorProperty().bind(tableViewListaProdutos.comparatorProperty());
                 
                 tableViewListaProdutos.setItems(sortdData);
             } catch (SQLException ex) {
                 Logger.getLogger(BuscaProdutosController.class.getName()).log(Level.SEVERE, null, ex);
             }
             
    
    }  
    
         public void CancelButtonOnAction(ActionEvent event){
        Stage stage =(Stage) buttonCancelar.getScene().getWindow();
        stage.close();
    }
        
        
         
             
    public void handleMenuItemProdutoRegistrarProduto (){
         
          
        try {
      
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/CadastroProduto.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        
         CadastroProdutoController cadastroProdutoProdutoController= loader.<CadastroProdutoController>getController();
        
         
        //usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
       // selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        //CadastroProdutoController.receberdadosUsuario(selectedUsuario);
        
        
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registo de Produtos");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setMaximized(false);
        dialogStage.setResizable(false);
        // Mostra o Dialog e espera atÃ© que o usuÃ¡rio o feche
        dialogStage.show();
           }
             catch (Exception ex) {
          System.out.println("" + ex + ex.getLocalizedMessage());
                System.out.println("" + ex.toString());
          } 
            }

         
         
    }    
    

