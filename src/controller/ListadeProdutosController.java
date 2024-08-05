/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Produto;
import BussinessLogic.Stock;
import BussinessLogic.StockLevel;
import BussinessLogic.Usuario;
import DataAcessLayer.ProdutoDAO;
import Service.ProdutosServicos;
import Service.StockServicos;
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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableCell;
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
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import jdbc.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import util.ReportUtils;

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
    private Button buttonDetalhes;
    
    @FXML
    private ImageView imageViewAdicionarProduto;
    
    @FXML
    private TableView<Produto> tableViewListaProdutos;

    @FXML
    private TableColumn<Produto, String> tableColumnCategoria;

    @FXML
    private TableColumn<Produto, String> tableColumnCodigo;

    @FXML
    private TableColumn<Produto, String> tableColumnDescricao;

    @FXML
    private TableColumn<Produto, String> tableColumnNome;

    @FXML
    private TableColumn<Produto, Double> tableColumnPreco;

    @FXML
    private TableColumn<Produto, String> tableColumnUnidade;
    
    @FXML
    private TableColumn<Produto, Double> tablecolumnStock;
    
     @FXML
    private TableColumn<Produto, Double> tableColumnPrecoDeCompra;


    @FXML
    private TextField textFieldPesquisaProdutos;
    
    @FXML
    private Label labelCodProduto;
    
    @FXML
    private Button buttonImprimir;

    
     @FXML
    private ImageView imagevie1;
    
    Produto produto;
    Stock stock;
    ProdutosServicos servicoProdutos;
    StockServicos stockServicos;
    UsuarioServicos usuarioServicos;
    ObservableList <Produto> produtoObservableList =FXCollections.observableArrayList();
    ObservableList <Stock> stockObservableList = FXCollections.observableArrayList();
    private Image image;
     Usuario usuario;
     
     
     
    
        public Usuario receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;

        if (!usuario.getNome().equals("Admin")) {

            buttonEditarProduto.setDisable(true);

        }
        return usuario;

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setCellValueFromTableToTextField();
        

           ProdutoDAO produtodao = new ProdutoDAO();
             try {
                
                 ResultSet rs=produtodao.ListagemdeProduto();
                 
                 while(rs.next()){
                 Integer QueryProductId= rs.getInt("Cod_produto");
                 String QueryCodigoManual=rs.getString("codigo_manual");
                 String QueryNome = rs.getString("Nome");
                 String QueryCategoria = rs.getString("Categoria");
                 String QueryUnidade = rs.getString("Unidade");
                 String QueryDescricao = rs.getString("Descricao");
                 Double QueryPreco = rs.getDouble("Preco_unitario");
                 Double QueryPrecoCompra = rs.getDouble("Preco_De_Compra");
                 String  foto= rs.getString("foto"); 
                 //Double Queryunidade_stock=rs.getDouble("unidades_stock");
             
                 
                 StockLevel stock = new StockLevel();
                 stock.setUnidades_stock(rs.getDouble("unidades_stock"));
                 
                 produtoObservableList.add(new Produto(QueryProductId,QueryCodigoManual,QueryNome,QueryCategoria,QueryUnidade,QueryDescricao,QueryPreco,QueryPrecoCompra,foto,stock));
                 }
                 
                 
                 tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo_manual"));
                 tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
                 tableColumnCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
                 tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
                 tableColumnUnidade.setCellValueFactory(new PropertyValueFactory<>("Unidade"));
                 tableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("Preco_unitario"));
                // tableColumnPrecoDeCompra.setCellValueFactory(new PropertyValueFactory<>("Cod_produto"));
                 //    tablecolumnStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

                 tablecolumnStock.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produto, Double>, ObservableValue<Double>>() {
                     @Override
                     public ObservableValue<Double> call(TableColumn.CellDataFeatures<Produto, Double> param) {
                         return new SimpleDoubleProperty(param.getValue().getStock().getUnidades_stock()).asObject();
                     }
                 });
                 tablecolumnStock.setCellFactory(column -> new TableCell<Produto, Double>() {
                     @Override
                     protected void updateItem(Double stock, boolean empty) {
                         super.updateItem(stock, empty);
                         if (stock == null || empty) {
                             setText(null);
                         } else {
                             setText(String.valueOf(stock));
                         }
                     }
                 });

                 tableViewListaProdutos.setItems(produtoObservableList);
                  
            
                 
                 FilteredList<Produto> filteredData =new FilteredList<> (produtoObservableList, b ->true);
                 
                 textFieldPesquisaProdutos.textProperty().addListener((observable, oldValue, newValue) ->{
                 filteredData.setPredicate(produto -> {
                     if(newValue.isEmpty()|| newValue == null){
                         
                         return true;
                     }
                     
                     
                     String searchKeyword=newValue.toLowerCase();
                     
                     if(produto.getCodigo_manual().toLowerCase().indexOf(searchKeyword)>-1){
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
        dialogStage.setTitle("REGISTO DE PRODUTO");
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
   
                 DialogUtil.showInfoMessage("O  Produto Não foi Selecionado!!", "Info");
                 Stage stage =(Stage)buttonEditarProduto.getScene().getWindow();
                 stage.setAlwaysOnTop(true);
               
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
        cadastroProdutoController.trocartextoeditarproduto();

        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("EDIÇÃO DE PRODUTO");
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

       
        @FXML
       public void handleDetalhesProduto() throws IOException {
       
        try {
        String codProduto = this.labelCodProduto.getText();
            if (labelCodProduto.getText().isEmpty()){
                
               handleMenuAlert1();
            
               
                }else{
      
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/CadastroProduto.fxml"));
       
         Parent root = (Parent) loader.load();           
         CadastroProdutoController cadastroProdutoController= loader.<CadastroProdutoController> getController();
         
         servicoProdutos = new ProdutosServicos();
         usuarioServicos = new UsuarioServicos();
         Produto selectedProduto = new Produto();
        // Stock selectedProduto1  = new Stock();    
         
         selectedProduto= servicoProdutos.getDetalhesProduto(Integer.parseInt(codProduto));
         //selectedProduto1 = stockServicos.getDetalhesStockTotal(Integer.parseInt(codProduto));
         //selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        cadastroProdutoController.detalhesProdutos(selectedProduto);
       // cadastroProdutoController.detalhesStockProdutos(selectedProduto1);
        
        cadastroProdutoController.camposBloqueiados();
        cadastroProdutoController.trocartextoregistrarprodutos();

        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("DETALHES DO PRODUTO");
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.setMaximized(false);
        dialogStage.setResizable(false);
        // Mostra o Dialog e espera atÃ© que o usuÃ¡rio o feche
        dialogStage.show();
        
       // Stage stage =(Stage)buttonEditarProduto.getScene().getWindow();
       // stage.close();
        
            
            }          
    }
    catch (Exception ex) {
      System.out.println("" + ex + ex.getLocalizedMessage());
                System.out.println("" + ex.toString());
    }
    
      
   }
       
       public void mudacorAnularEntered(){
       buttonAnular.setStyle("-fx-background-color: #FFF");
       buttonAnular.setStyle("-fx-background-radius: 13");
    } 
    
       public void mouseexitbuttonAnular(){
       buttonAnular.setStyle("-fx-background-color: #FFF");
       buttonAnular.setStyle("-fx-background-radius: 13");
   }
       
       public void mudacorEditarEntered(){
       buttonEditarProduto.setStyle("-fx-background-color: #FFF");
       buttonEditarProduto.setStyle("-fx-background-radius: 13");
    } 
    
       public void mouseexitEditar(){
       buttonEditarProduto.setStyle("-fx-background-color: #FFF");
       buttonEditarProduto.setStyle("-fx-background-radius: 13");
   }
       public void mudacorCancelarEntered(){
       buttonCancelar.setStyle("-fx-background-color: #FFF");
       buttonCancelar.setStyle("-fx-background-radius: 13");
    } 
    
       public void mouseexitCancelarRegistar(){
       buttonCancelar.setStyle("-fx-background-color: #FFF");
       buttonCancelar.setStyle("-fx-background-radius: 13");
       }
       
       public void mudacorDetalhesEntered(){
       buttonDetalhes.setStyle("-fx-background-color: #FFF");
       buttonDetalhes.setStyle("-fx-background-radius: 13");
    } 
    
       public void mouseexitDetalhes(){
       buttonDetalhes.setStyle("-fx-background-color: #FFF");
       buttonDetalhes.setStyle("-fx-background-radius: 13");
       }
       
       public void mudacorImprimirEntered(){
       buttonImprimir.setStyle("-fx-background-color: #FFF");
       buttonImprimir.setStyle("-fx-background-radius: 13");
    } 
    
       public void mouseexitImprimir(){
       buttonImprimir.setStyle("-fx-background-color: #FFF");
       buttonImprimir.setStyle("-fx-background-radius: 13");
       }
       
         public void OpenProdutoReport() {
             
              String jasperFilePath = "src\\relatorios\\ListaProdutos_Blue.jasper";

         
         try {
             //  InputStream inputStream = getClass().getResourceAsStream(jasperFilePath);
             FileInputStream inputStream = new FileInputStream(jasperFilePath);
             
                     Map<Object, Object> parametros = new HashMap<Object, Object>();
              
                   parametros.put("cod_produto",11);
                ReportUtils.openReport("Produto Report", inputStream, parametros, ConnectionFactory.getSakilaConnection());
      

          }
       catch (Exception exc) {
            
  
            
          exc.printStackTrace();
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
        
          }
          catch (Exception ex) {
          System.out.println("" + ex + ex.getLocalizedMessage());
          System.out.println("" + ex.toString());
              } 
                }
       
       public void handleImprimir() throws JRException{
                   
           OpenProdutoReport();

    }
       
       public void print(){
  
        
        try{
             
            JasperDesign jDesign = JRXmlLoader.load("src\\relatorios\\ListaProdutos_Blue.jrxml");
        
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, ConnectionFactory.getSakilaConnection());
            
            JasperViewer viewer = new JasperViewer(jPrint, false);
            
            viewer.setTitle("Lista de Produtos");
            viewer.show();
            
        }catch(Exception e){}
    }
        
       }
          
    

