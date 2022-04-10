/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import BussinessLogic.Produto;
import java.sql.ResultSet;
import BussinessLogic.Stock;
import BussinessLogic.Usuario;
import DataAcessLayer.StockDAO;
import Service.ProdutosServicos;
import Service.StockServicos;
import com.mysql.cj.protocol.Resultset;
import com.sun.javafx.collections.MappingChange.Map;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Window;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Neusia
 */
public class CadStockController implements Initializable {
    
    Usuario usuario;
    ProdutosServicos prodServ;
    StockServicos stServico;
    private Produto produto;
    Stock stock;
     private Stage dialogStage2;
     

    @FXML
    private TextField textFieldIdStock;

    public Produto getProduto() {
        return produto;
    }

    public void ReceberDadosProduto(Produto produto) {
        this.produto = produto;
        
          //  this.TextFieldCodigoFuncionario.setText();
              //this.TextFieldCodigoFuncionario.setText(String.valueOf(user.getCod_Funcionario()));
              this.TextFieldCodigoProduto.setText(produto.getCod_produto().toString());
              this.TextFieldCodigoProduto.setVisible(false);
              
              this.textfieldNomeProduto.setText(produto.getNome());
             //this.TextFieldCodigoProduto.setText(String.valueOf(produto.getCod_produto()));
              //this.TextFieldCodigoFuncionario.setVisible(true);
    }
   
    public Usuario getUsuario(){
        return usuario;
    }
    
     public void receberdadosUsuario(Usuario usuario){
         this.usuario= usuario;
         
         this.TextFieldCodigoFuncionario.setText(usuario.getCod_Funcionario().toString());
        
         
         this.labelNomeFuncionario.setText(usuario.getNome());
       // return usuario;
    }
    
     
     private void writeObject(ObjectOutputStream out)
            throws IOException {
        out.writeObject(stock.getProduto_Cod_Produto());
        out.writeObject(stock.getQuantidade_recebida());
         out.writeObject(stock.getData_entrada());
          out.writeObject(stock.getUsuario_Cod_Funcionario());
           out.writeObject(stock.getNumero_lote());
            out.writeObject(stock.getFabricante());
    }

    
     
     
    @FXML
    private TextField TextFieldCodigoProduto;

    
    @FXML
    private TextField TextFieldCodigoFuncionario;

    @FXML
    private TextField TextFieldNumeroLote;

    @FXML
    private TextField TextFieldFabricante;

    @FXML
    private DatePicker DatePickerDataEntrada;

    @FXML
    private TextField TextFieldlQuantidadeRecebida;

    @FXML
    private Button buttonCancelar;
     
        @FXML
    private TextField textfieldNomeProduto;

    @FXML
    private Button buttonAdicionarStockNaTabela;

        @FXML
    private Label labelNomeFuncionario;
    
    private StockDAO dao;
    //private ObservableList<Stock> stock;
   StockServicos stockServicos;
 ProdutosServicos servicoProdutos;
// ReadWriteTextFile rwTextFile = new ReadWriteTextFile();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        // TODO
        dao=new StockDAO();
     
        
      
    }
       public void UserInfo(String codProduto, String codUsua){
    // Produto pr = new Produto();
              //Integer Cod_produto = pr.getCod_produto();
            //  this.TextFieldCodigoFuncionario.setText(c);
              //this.TextFieldCodigoFuncionario.setText(String.valueOf(user.getCod_Funcionario()));
             // this.TextFieldCodigoProduto.setText(codProduto);
             //this.TextFieldCodigoProduto.setText(String.valueOf(produto.getCod_produto()));
            //  this.TextFieldCodigoFuncionario.setVisible(true);
            //this.TextFieldCodigoProduto.setText(String.valueOf(produto.getCod_produto()));
       }
     

    

      public void CancelButtonOnAction(ActionEvent event){
        Stage stage =(Stage) buttonCancelar.getScene().getWindow();
        stage.close();
    }
  
       public boolean verificadados() {
/*  77 */     boolean fds = false;
/*     */ 
/*  87 */    if (this.TextFieldlQuantidadeRecebida.getText().equals("")) {
/*     */       
/*  94 */       JOptionPane.showMessageDialog(null, "INTRODUZA A QUANTIDADE RECEBIDA ");
/*  95 */       fds = true;
/*     */ 
/*     */     }
/* 102 */    // else if (this.DatePickerDataEntrada.getValue().equals("")) {
/*     */       
/* 104 */       //JOptionPane.showMessageDialog(null, "INTRODUZA  A DATA DE REGISTO");
/* 105 */       //fds = true;
                //}
/* 106 */   else if (this.TextFieldCodigoProduto.getText().equals("")) {
/*     */       
/*  94 */       JOptionPane.showMessageDialog(null, "INTRODUZA O CODIGO DO PRODUTO ");
/*  95 */       fds = true;
/*     */ 
/*     */      }
          
/*     */    
               else if (this.TextFieldNumeroLote.getText().equals("")) {
/*     */       
/*  94 */       JOptionPane.showMessageDialog(null, "INTRODUZA O NUMERO DO LOTE ");
/*  95 */       fds = true;
/*     */ 
/*     */     }
/*     */ 
/*     */  else if (this.TextFieldFabricante.getText().equals("")) {
/*     */       
/*  94 */       JOptionPane.showMessageDialog(null, "INTRODUZA O NOME DO FABRICANTE ");
/*  95 */       fds = true;
/*     */ 
/*     */     }
/*     */ 
/* 122 */     return fds;
/*     */   }


      @FXML
      void submit (ActionEvent event){
           if (!verificadados()) {
   
                  //Stock stock= new Stock(Integer.parseInt(TextFieldlQuantidadeRecebida.getText()),
                  //Date.valueOf(DatePickerDataEntrada.getValue()),
                 // Integer.parseInt(TextFieldCodigoProduto.getText()),
                  //Integer.parseInt(TextFieldCodigoFuncionario.getText()),
                  //TextFieldNumeroLote.getText(),
                          
                 //Date data_entrada = Date.from(DatePickerDataEntrada.atStartOfDay(ZoneId.systemDefault()).toInstant());
                  //LocalDate data_Entrada= LocalDate.parse(DatePickerDataEntrada.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                 // TextFieldFabricante.getText());
          
          
          //ObservableList <Stock> stocks = tableViewStock.getItems();
          //stocks.add(stock);
          
         // tableViewStock.setItems(stocks);
       //registoStock();
 
       
      }
      }
      public void registoStock() throws SQLException {
           Integer idstock= Integer.parseInt(textFieldIdStock.getText());
            Integer quantidade_recebida = Integer.parseInt(TextFieldlQuantidadeRecebida.getText());
                Date data_entrada = java.sql.Date.valueOf(DatePickerDataEntrada.getValue());
               // String data_entrada =stockss.getData_entrada().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                 //data_entrada = DatePickerDataEntrada.getValue();   
                 
                 //StockServicos ss =new StockServicos();
                 Integer produto_Cod_Produto =Integer.parseInt(TextFieldCodigoProduto.getText());
                 Integer usuario_Cod_Funcionario =  Integer.parseInt(TextFieldCodigoFuncionario.getText());
                 String numero_lote= TextFieldNumeroLote.getText();
                 String fabricante =TextFieldFabricante.getText();
                 
                  //StockDAO dao = new StockDAO();
                
              //dao.RegistarStock(idstock,quantidade_recebida, data_entrada, produto_Cod_Produto, usuario_Cod_Funcionario, numero_lote, fabricante);
            }
      
      
      
      ObservableList<Stock> lista(){
      ObservableList<Stock> lista = FXCollections.observableArrayList();
      
        // lista.add(new Stock(Integer.parseInt(TextFieldlQuantidadeRecebida.getText()),java.sql.Date.valueOf(DatePickerDataEntrada.getValue()),TextFieldNumeroLote.getText(),TextFieldFabricante.getText()));
           
           return lista;
              }
      
      @FXML
    public void handleMenuItemStockAdicionarStockNaTabela() throws IOException {

                  //Integer idstock= Integer.parseInt(textFieldIdStock.getText());
          
                 
        if (!verificadados()) {
            
        
/*     */     try {
                Integer produto_Cod_Produto = Integer.parseInt(TextFieldCodigoProduto.getText());
/* 166 */      Integer quantidade_recebida = Integer.parseInt(TextFieldlQuantidadeRecebida.getText());
               Date data_entrada = java.sql.Date.valueOf(DatePickerDataEntrada.getValue());
            
                 //Integer prod =Integer.parseInt(TextFieldCodigoProduto.getText());
                Integer user =  Integer.parseInt(TextFieldCodigoFuncionario.getText());
                 String numero_lote= TextFieldNumeroLote.getText();
                 String fabricante =TextFieldFabricante.getText();
                 
                 
/*     */      
            // FXMLLoader loader = FXMLLoader.load(getClass().getResource("/Presentation/TabelaAdicaoProduto.fxml"));
            
              
               //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/TabelaAdicaoProduto.fxml"));
               //loader.setLocation(TrickController.class.getResource("/Presentation/TabelaAdicaoProduto.fxml"));
               // Parent root = (Parent) loader.load();
              // AnchorPane page = (AnchorPane) loader.load();
        
            TabelaAdicaoProdutoController tabelaAdicaoProdutoController= loader.<TabelaAdicaoProdutoController>getController();
           
            
            
            
           servicoProdutos = new ProdutosServicos();
          // Produto selectedProduto = new Produto();
         //  selectedProduto= servicoProdutos.getDetalhesProduto(produto_Cod_Produto);
           
           ObservableList<Stock> lista = FXCollections.observableArrayList();
           
   
           lista.add(new Stock(Integer.parseInt(TextFieldCodigoProduto.getText()),Integer.parseInt(TextFieldlQuantidadeRecebida.getText()),
                   java.sql.Date.valueOf(DatePickerDataEntrada.getValue()),Integer.parseInt(TextFieldCodigoFuncionario.getText()),
                   TextFieldNumeroLote.getText(),TextFieldFabricante.getText()));
           
          

           
         tabelaAdicaoProdutoController.setItems(lista);
          
           
          // tabelaAdicaoProdutoController.ReceberDadosProduto(selectedProduto);
         Stage stage =(Stage) buttonAdicionarStockNaTabela.getScene().getWindow();
        stage.close();
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
         dialogStage.setTitle("Registo de Stock");
      Scene scene = new Scene(root);
       dialogStage.setScene(scene);
        dialogStage.setMaximized(false);
        dialogStage.setResizable(false);
        // Mostra o Dialog e espera atÃ© que o usuÃ¡rio o feche
       // dialogStage.toFront();
        dialogStage.showAndWait();
     
      
/*     */     }
/* 201 */     catch (Exception ex) {
/* 202 */       System.out.println("" + ex + ex.getLocalizedMessage());
                System.out.println("" + ex.toString());
/*     */     } 
/*     */   
    }
    }
      
    
 public ObservableList<Stage> getAllShowingStages() {
 ObservableList<Stage> stages = FXCollections.observableArrayList();
 Window.getWindows().forEach(w -> {
 if (w instanceof Stage) {
 stages.add((Stage) w);
  }
 });
 return stages;
}
  
 @FXML
    public void handleMenuItemStockAdicionarStockNaTabela2(ActionEvent event) throws IOException {
              
          //URL location = getClass().getResource("MainScreen.fxml");
       
    try {
  
      //if (dialogStage == null) {
  

   if (dialogStage.isShowing()) {
        
      dialogStage.toFront();
    } else {
              
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/TabelaAdicaoProduto.fxml"));
             
                Parent root = (Parent) loader.load();
          
        
            TabelaAdicaoProdutoController tabelaAdicaoProdutoController= loader.<TabelaAdicaoProdutoController>getController();
           
     
           servicoProdutos = new ProdutosServicos();
       
           
           ObservableList<Stock> lista = FXCollections.observableArrayList();
           
   
           lista.add(new Stock(Integer.parseInt(TextFieldCodigoProduto.getText()),Integer.parseInt(TextFieldlQuantidadeRecebida.getText()),
                   java.sql.Date.valueOf(DatePickerDataEntrada.getValue()),Integer.parseInt(TextFieldCodigoFuncionario.getText()),
                   TextFieldNumeroLote.getText(),TextFieldFabricante.getText()));
           
          

           
         tabelaAdicaoProdutoController.setItems(lista);
          
             Stage stage =(Stage) buttonAdicionarStockNaTabela.getScene().getWindow();
        stage.close();
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        //Stage dialogStage = new Stage();
         dialogStage.setTitle("Registo de Stock");
      Scene scene = new Scene(root);
       dialogStage.setScene(scene);
        dialogStage.setMaximized(false);
        dialogStage.setResizable(false);
        // Mostra o Dialog e espera atÃ© que o usuÃ¡rio o feche
       // dialogStage.toFront();
        dialogStage.showAndWait();
      //dialogStage.show();
      
    }
  
/*     */     }
/* 201 */     catch (Exception ex) {
/* 202 */       System.out.println("" + ex + ex.getLocalizedMessage());
                System.out.println("" + ex.toString());
/*     */     } 
/*     */   
    }
  }

