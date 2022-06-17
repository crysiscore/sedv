/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Produto;
import BussinessLogic.Usuario;
import DataAcessLayer.ProdutoDAO;
import Service.ProdutosServicos;
import Service.UsuarioServicos;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Blob;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

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
    private Button buttonAnular;

    @FXML
    private Button buttonEditarProduto;

    @FXML
    private Button buttonImprimirProduto;
    
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
    
    @FXML
    private Label labelCodProduto;
    
     @FXML
    private ImageView imagevie1;
    
    Produto produto;
    ProdutosServicos servicoProdutos;
    UsuarioServicos usuarioServicos;
    ObservableList <Produto> produtoObservableList =FXCollections.observableArrayList();
    private Image image;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setCellValueFromTableToTextField();
        
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
                 String  foto= rs.getString("foto"); 
               
           
               
                 produtoObservableList.add(new Produto(QueryProductId,QueryNome,QueryCategoria,QueryUnidade,QueryDescricao,QueryPreco,foto));
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
    
    
    
    
      private void setCellValueFromTableToTextField(){
          
         
        tableViewListaProdutos.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                
                Produto pr =tableViewListaProdutos.getItems().get(tableViewListaProdutos.getSelectionModel().getSelectedIndex());
                 labelCodProduto.setText(String.valueOf(pr.getCod_produto()));
                //produto.setCod_produto(Integer.parseInt(labelCodProduto.getText()));
               labelCodProduto.setVisible(false);
              
            }
            });
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
        
        Stage stage =(Stage) imageViewAdicionarProduto.getScene().getWindow();
        stage.close();
        
        cadastroProdutoProdutoController.OcultarBotaoEditar();
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

    
    
      @FXML
       public void handleEditarProduto() throws IOException {
    
        try {
        String codProduto = this.labelCodProduto.getText();
            if (labelCodProduto.getText().isEmpty()){
                
                JOptionPane.showMessageDialog(null, " Produto Não Selecionado!");
                
                }else{
      
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/CadastroProduto.fxml"));
       
         Parent root = (Parent) loader.load();           
         CadastroProdutoController cadastroProdutoController= loader.<CadastroProdutoController> getController();
         
         servicoProdutos = new ProdutosServicos();
         usuarioServicos = new UsuarioServicos();
         Produto selectedProduto = new Produto();
         
         
         selectedProduto= servicoProdutos.getDetalhesProduto(Integer.parseInt(codProduto));
         //selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        cadastroProdutoController.ReceberDadosProduto(selectedProduto);
        cadastroProdutoController.OcultarBotaoSalvar();

        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registo de Stock");
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.setMaximized(false);
        dialogStage.setResizable(false);
        // Mostra o Dialog e espera atÃ© que o usuÃ¡rio o feche
        dialogStage.show();
        
         Stage stage =(Stage)buttonEditarProduto.getScene().getWindow();
        stage.close();
        
            
            }          
    }
    catch (Exception ex) {
      System.out.println("" + ex + ex.getLocalizedMessage());
                System.out.println("" + ex.toString());
    }
    
      
   }
    
         
    }    
    

