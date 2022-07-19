/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
 
import BussinessLogic.Produto;
import BussinessLogic.Stock;
import BussinessLogic.Usuario;
import DataAcessLayer.ProdutoDAO;
import DataAcessLayer.UsuarioDAO;
import Model.StockModel;
import Service.ProdutosServicos;
import Service.UsuarioServicos;
import com.mysql.cj.protocol.Resultset;
import java.io.IOException;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Neusia
 */
public class BuscaProdutosController implements Initializable {
    
      /**
     * Initializes the controller class.
     */
    
        @FXML
    private Pane PanePane;

    @FXML
    private TableView<Produto> tableviewProdutos;

    @FXML
    private TableColumn<Produto, Integer> columnCodigoProduto;

    @FXML
    private TableColumn<Produto, String> columnNomeProduto;
    
    @FXML
    private Button buttonAdicionarProduto;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Label labelCodProduto;

    @FXML
    private Label labelUsuario;

    @FXML
    private TextField textfieldProcurar;
    
    
    ResultSet rs;
    Usuario usuario; 
    Produto produto;
    UsuarioServicos usuarioServicos ;
    private StockModel stockModel;
     
  
    ProdutosServicos servicoProdutos;
    ObservableList <Produto> produtoObservableList =FXCollections.observableArrayList();
    
             
    public void initModel(StockModel model) {
        if (this.stockModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.stockModel = model ;
    }

    
     public Usuario getUsuario() {
        return usuario;
    }
       
         public void receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;
      
        // this.labelUsuario.setText(usuario.getCod_Funcionario().toString());
        
        //return usuario;
         }
         

     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
       
        
        setCellValueFromTableToTextField();
        
        ProdutoDAO produtodao = new ProdutoDAO();
             try {
                 //produtodao.ListarProdutos();
                 ResultSet rs=produtodao.ListarProdutos();
                 
                 while(rs.next()){
                 Integer QueryProductId= rs.getInt("Cod_produto");
                 String QueryNome = rs.getString("Nome");
                 
                 produtoObservableList.add(new Produto(QueryProductId,QueryNome));
                 }
                 columnCodigoProduto.setCellValueFactory(new PropertyValueFactory<>("Cod_produto"));
                 columnNomeProduto.setCellValueFactory(new PropertyValueFactory<>("Nome"));
                 tableviewProdutos.setItems(produtoObservableList);
                 
                 
                 FilteredList<Produto> filteredData =new FilteredList<> (produtoObservableList, b ->true);
                 
                 textfieldProcurar.textProperty().addListener((observable, oldValue, newValue) ->{
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
                 
                 sortdData.comparatorProperty().bind(tableviewProdutos.comparatorProperty());
                 
                 tableviewProdutos.setItems(sortdData);
             } catch (SQLException ex) {
                 Logger.getLogger(BuscaProdutosController.class.getName()).log(Level.SEVERE, null, ex);
             }
             
    
    }    
    
      private void setCellValueFromTableToTextField(){
          
         
        tableviewProdutos.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                
                Produto pr =tableviewProdutos.getItems().get(tableviewProdutos.getSelectionModel().getSelectedIndex());
               //prod.setCod_produto(pr.getCod_produto());
               //prod.setCod_produto(pr.getCod_produto());
               //prod.Cod_produto= pr.getCod_produto();
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
  
      public void fecharTela(){
          Stage stage =(Stage) buttonAdicionarProduto.getScene().getWindow();
        stage.close();
      }


    @FXML
    public void handleMenuItemStockAdicionarProduto() throws IOException {
      
       
    try {
         //produto.setCod_produto(Integer.parseInt(labelCodProduto.getText()));
        // String codUsuario=this.labelUsuario.getText();
         String codProduto = this.labelCodProduto.getText();
          
         if (labelCodProduto.getText().isEmpty()){
          handleMenuAlert1();

         }
         else{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/CadastroStock1.fxml"));
        //AnchorPane page = (AnchorPane) loader.load();
          
        //    CadStockController cadStockController= loader.getController();
        //    cadStockController.UserInfo(codProduto, codUsua);
         Parent root = (Parent) loader.load();           
         CadStockController cadStockController= loader.<CadStockController> getController();
         
         servicoProdutos = new ProdutosServicos();
         usuarioServicos = new UsuarioServicos();
         Produto selectedProduto = new Produto();
         Usuario selectedUsuario = new Usuario();
         
         selectedProduto= servicoProdutos.getDetalhesProduto(Integer.parseInt(codProduto));
         //selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        cadStockController.ReceberDadosProduto(selectedProduto);
        cadStockController.initModel(stockModel);
        // Scene scene = new Scene(root);
           
       
        cadStockController.receberdadosUsuario(usuario);
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registo de Stock");
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.setMaximized(false);
        dialogStage.setResizable(false);
        
        // Mostra o Dialog e espera atÃ© que o usuÃ¡rio o feche
        dialogStage.show();
        
         Stage stage =(Stage) buttonAdicionarProduto.getScene().getWindow();
        stage.close();
    }
    }
    catch (Exception ex) {
      System.out.println("" + ex + ex.getLocalizedMessage());
                System.out.println("" + ex.toString());
    }
    
      
   }
    
    public void handleMenuAlert1() {
             
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/alert1.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

       
      
        
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("ALERTA");
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
    
     public void mudacorAdicionarProduto(){
       buttonAdicionarProduto.setStyle("-fx-background-color: #FFF");
       buttonAdicionarProduto.setStyle("-fx-background-radius: 13");
    }
     
    public void exitAdicionar(){
       buttonAdicionarProduto.setStyle("-fx-background-color: #FFF");
       buttonAdicionarProduto.setStyle("-fx-background-radius: 13");
    }
    
     public void mudacorcancel(){
       buttonCancelar.setStyle("-fx-background-color: #FFF");
       buttonCancelar.setStyle("-fx-background-radius: 13");
    }
     
    public void mouseexitbuttonCancel(){
       buttonCancelar.setStyle("-fx-background-color: #FFF");
       buttonCancelar.setStyle("-fx-background-radius: 13");
   }
}
