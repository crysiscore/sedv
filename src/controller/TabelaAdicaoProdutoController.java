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
import DataAcessLayer.StockDAO;
import Model.StockModel;
import Service.StockServicos;
import Service.UsuarioServicos;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
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
public class TabelaAdicaoProdutoController implements Initializable {
     
     @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Pane PanePane;

    
    @FXML
    private TableView<Stock> tableviewAdicionarStock;

    @FXML
    private TableColumn<Stock, Integer> columnCodigoProduto;

    @FXML
    private TableColumn<Stock, Date> columnData;

    @FXML
    private TableColumn<Stock, String> columnFabricante;

    @FXML
    private TableColumn<Stock, String> columnNumeroLote;

    @FXML
    private TableColumn<Stock, Integer> columnQuantidade;

    @FXML
    private Label labelCodUsuario;

    @FXML
    private Label labelcopr;

    
    @FXML
    private Button buittonRegistarStock;

    @FXML
    private Button buttonAdicionarStock;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonRemover;
    
    @FXML
    private Label labelNomeUsuario;
    
    @FXML
    private TextField textfieldcodfunc;
    
    
    
    UsuarioServicos usuarioServicos ;
  // ObservableList <Stock> stockList ;
    StockServicos stServico;
    //Stock stock;
     Usuario usuario;
     private CadStockController cadStockController;
     private Produto produto;
     private StockModel stockModel = new StockModel();
    ObservableList <Stock> stock =FXCollections.observableArrayList();
   
 //final static String ficheiro_gravado = "C:\\app\\stock.txt";
       
  
     public void initModel(StockModel model) {
        if (this.stockModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.stockModel = model;
    }


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
        this.labelNomeUsuario.setText(usuario.getNome());
        
         }
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    
      this.columnCodigoProduto.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("produto_Cod_Produto"));
      
       this.columnQuantidade.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("quantidade_recebida"));
       
       this.columnData.setCellValueFactory(new PropertyValueFactory<Stock, Date>("data_entrada"));
       this.columnNumeroLote.setCellValueFactory(new PropertyValueFactory<Stock, String>("numero_lote"));
       this.columnFabricante.setCellValueFactory(new PropertyValueFactory<Stock, String>("fabricante"));
      
       stock=stockModel.getStockList();
       tableviewAdicionarStock.setItems(stock);
       
       buittonRegistarStock.disableProperty().bind(Bindings.size(stock).isEqualTo(0));
       buttonRemover.disableProperty().bind(Bindings.size(stock).isEqualTo(0));
       
     
       }
    

    
    @FXML
    void removerStockdaTabela() {

        int response = JOptionPane.showConfirmDialog(null, "O Produto foi selecionado???", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {

            Stock selecionada = tableviewAdicionarStock.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                int selectedID = tableviewAdicionarStock.getSelectionModel().getSelectedIndex();
                tableviewAdicionarStock.getItems().remove(selectedID);

                JOptionPane.showMessageDialog(null, "O Produto foi removido!");
            } else {
                JOptionPane.showMessageDialog(null, "O Item não foi selecionado!");
            }

        } else if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Selecione o Produto se desejar removê-lo!");

        } else if (response == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "Escolha uma das opções!");
        }

      

     }
         

         @FXML
    public void handleMenuItemStockAdicionar() throws IOException {
             
         try {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/BuscaProduto1.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
         //BuscaProdutosController buscaProdutosController= loader.getController();
         
         BuscaProdutosController buscaProdutosController= loader.<BuscaProdutosController>getController();
         
         //buscaProdutosController.UserInfo(codUsua);
         usuarioServicos = new UsuarioServicos();
       
        buscaProdutosController.receberdadosUsuario(usuario);
        buscaProdutosController.initModel(stockModel);
        
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("PESQUISAR O PRODUTO");
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
    
    
    
        public void CancelarButtonOnAction(ActionEvent event){
        Stage stage =(Stage) buttonCancelar.getScene().getWindow();
        stage.close();
        }
        

         @FXML
        public void handleMenuItemRegistarStock() throws IOException, SQLException {
        StockDAO dao = new StockDAO();
         
       if (tableviewAdicionarStock.getItems().isEmpty()) {
       handleMenuAlert();
         
        
          } else {
          
           int response =JOptionPane.showConfirmDialog(null,"Tem a certeza que deseja registrar o Stock introduzido na tabela???", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
          
           if(response==JOptionPane.YES_OPTION){
               
                dao.RegistarStock(tableviewAdicionarStock.getItems());
            tableviewAdicionarStock.getItems().clear();
           JOptionPane.showMessageDialog(null, "O Stock foi Cadastrado com Sucesso!");
           
           
           }else if(response==JOptionPane.NO_OPTION){
               JOptionPane.showMessageDialog(null, "O Stock não foi Cadastrado!");
           
           
           }else if(response==JOptionPane.CLOSED_OPTION){
               JOptionPane.showMessageDialog(null, "Escolha uma das opções!");
           }
       }
         }
  
           
           
       
         @FXML
    public void handleMenuAlert() {
             
/*     */     try {
/* 159 */       
/* 165 */          //String codUsuario = this.labelCodUsuario.getText();
/* 166 */        
/*     */     FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/alert.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
         //BuscaProdutosController buscaProdutosController= loader.getController();
         
        AlertController alertController= loader.<AlertController>getController();
      
        
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
    
       
        //for(int i = 0; i < tableviewAdicionarStock.getItems().size(); i++) {
        
             
       //  Integer produto_Cod_Produto = tableviewAdicionarStock.getItems().get(i).produto_Cod_Produto;
         //    Date data_entrada=tableviewAdicionarStock.getItems().get(i).data_entrada;
           //  Integer usuario_Cod_Funcionario = stockModel.getStockList().get(i).usuario_Cod_Funcionario;
          //   String numero_lote = tableviewAdicionarStock.getItems().get(i).numero_lote;
           //  String fabricante =tableviewAdicionarStock.getItems().get(i).fabricante;
        
               
        public void handleMenuAlert1() {
             
/*     */     try {
        
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
    
         

      public void mudacorRegistarEntered(){
        buittonRegistarStock.setStyle("-fx-background-color: #FFF");
         buittonRegistarStock.setStyle("-fx-background-radius: 12");
    } 
    
       public void mouseexitbuttonRegistar(){
       buittonRegistarStock.setStyle("-fx-background-color: #FFF");
       buittonRegistarStock.setStyle("-fx-background-radius: 12");
   }
       
       public void mudacorAdicionarEntered(){
       buttonAdicionarStock.setStyle("-fx-background-color: #FFF");
       buttonAdicionarStock.setStyle("-fx-background-radius: 12");
    } 
    
       public void mouseexitAdicionarRegistar(){
       buttonAdicionarStock.setStyle("-fx-background-color: #FFF");
       buttonAdicionarStock.setStyle("-fx-background-radius: 12");
   }
       public void mudacorCancelarEntered(){
       buttonCancelar.setStyle("-fx-background-color: #FFF");
       buttonCancelar.setStyle("-fx-background-radius: 12");
    } 
    
       public void mouseexitCancelarRegistar(){
       buttonCancelar.setStyle("-fx-background-color: #FFF");
       buttonCancelar.setStyle("-fx-background-radius: 12");
       }
       
       public void mudacorRemoverEntered(){
       buttonRemover.setStyle("-fx-background-color: #FFF");
       buttonRemover.setStyle("-fx-background-radius: 12");
    } 
    
       public void mouseexitRemoverRegistar(){
       buttonRemover.setStyle("-fx-background-color: #FFF");
       buttonRemover.setStyle("-fx-background-radius: 12");
       }
    }  

