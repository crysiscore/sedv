/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.DetalhesVenda;
import BussinessLogic.Produto;
import BussinessLogic.Stock;
import BussinessLogic.StockLevel;
import BussinessLogic.Usuario;
import BussinessLogic.Venda;
import DataAcessLayer.ProdutoDAO;
import Model.DetalhesVendaModel;
import Service.ProdutosServicos;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class BuscaProdutoVendaController implements Initializable {
    
    @FXML
    private Pane PanePane;

    @FXML
    private Button buttonAdicionarProduto;

    @FXML
    private Button buttonCancelar;
    
     @FXML
    private TableView<Produto> tableviewProdutos;

    @FXML
    private TableColumn<Produto, String> columnCodigoProduto;

    @FXML
    private TableColumn<Produto, String> columnNomeProduto;
    
    @FXML
    private TableColumn<Produto, Double> columnPreco;
    
    @FXML
    private TableColumn<Produto, Double> columnStock;
    
    @FXML
    private ImageView imageviewFotoProduto;

    @FXML
    private Label labelCodProduto;

    @FXML
    private Label labelUsuario;
    
    @FXML
    private Label labelNomeProduto;

    @FXML
    private Label labelPrecoProduto;
    
    @FXML
    private Label labelsubtotal;
    @FXML
    private Label stock1;
    
    @FXML
    private Label labelStock;

    @FXML
    private TextField textfieldProcurarProduto;
    
    @FXML
    private TextField textfieldQuantidadeProduto;

    Stock stock;
    Usuario usuario; 
    Produto produto;
    Venda venda;
    private DetalhesVendaModel detalhesVendaModel;
    
    ProdutosServicos servicoProdutos;
    ObservableList <Produto> produtoObservableList =FXCollections.observableArrayList();
   

    
     public void initModel(DetalhesVendaModel model) {
        if (this.detalhesVendaModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.detalhesVendaModel = model;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        labelPrecoProduto.setVisible(false);
        labelStock.setVisible(false);
        textfieldQuantidadeProduto.setVisible(false);
        setCellValueFromTableToTextField();
        
        ProdutoDAO produtodao = new ProdutoDAO();
             try {
                 //produtodao.ListarProdutos();
                 ResultSet rs=produtodao.ListagemdeProduto();
                 
                while(rs.next()){
                 Integer QueryProductId= rs.getInt("Cod_produto");
                 String QueryCodigoManual= rs.getString("codigo_manual");
                 String QueryNome = rs.getString("Nome");
                 String QueryCategoria = rs.getString("Categoria");
                 String QueryUnidade = rs.getString("Unidade");
                 String QueryDescricao = rs.getString("Descricao");
                 Double QueryPreco = rs.getDouble("Preco_unitario");
                 Double QueryPrecoCompra = rs.getDouble("Preco_De_Compra");
                 String  foto= rs.getString("foto"); 
                 //Double Queryunidade_stock=rs.getDouble("unidades_stock");
                 
                 StockLevel stock = new StockLevel();
                 stock.unidades_stock =rs.getDouble("unidades_stock");
                
                 produtoObservableList.add(new Produto(QueryProductId,QueryCodigoManual,QueryNome,QueryCategoria,QueryUnidade,QueryDescricao,QueryPreco,QueryPrecoCompra,foto,stock));
                 }
                 columnCodigoProduto.setCellValueFactory(new PropertyValueFactory<>("codigo_manual"));
                 columnNomeProduto.setCellValueFactory(new PropertyValueFactory<>("Nome"));
                 columnPreco.setCellValueFactory(new PropertyValueFactory<>("Preco_unitario"));
                // columnStock.setCellValueFactory(new PropertyValueFactory<>("unidades_stock"));
                columnStock.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produto, Double>, ObservableValue<Double>>() {
                     @Override
                     public ObservableValue<Double> call(TableColumn.CellDataFeatures<Produto, Double> param) {
                         return new SimpleDoubleProperty(param.getValue().getStock().getUnidades_stock()).asObject();
                     }
                 });
                 columnStock.setCellFactory(column -> new TableCell<Produto, Double>() {
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

                 tableviewProdutos.setItems(produtoObservableList);
                
                 
                 FilteredList<Produto> filteredData =new FilteredList<> (produtoObservableList, b ->true);
                 
                 textfieldProcurarProduto.textProperty().addListener((observable, oldValue, newValue) ->{
                 filteredData.setPredicate(produto -> {
                     if(newValue.isEmpty()|| newValue == null){
                         
                         return true;
                     }
                     
                     
                     String searchKeyword=newValue.toLowerCase();
                     
                     if(produto.getCod_produto().toString().indexOf(searchKeyword)>-1){
                         return true;
                     }else if(produto.getNome().toLowerCase().indexOf(searchKeyword)>-1){
                         return true;
                          }else if(produto.getCodigo_manual().toLowerCase().indexOf(searchKeyword)>-1){
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
    
     
    private void setCellValueFromTableToTextField() {

        tableviewProdutos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Produto pr = tableviewProdutos.getItems().get(tableviewProdutos.getSelectionModel().getSelectedIndex());
                //prod.setCod_produto(pr.getCod_produto());
                //prod.setCod_produto(pr.getCod_produto());
                //prod.Cod_produto= pr.getCod_produto();
                labelCodProduto.setText(String.valueOf(pr.getCod_produto()));
                labelNomeProduto.setText(pr.getNome());
                labelPrecoProduto.setText(String.valueOf(pr.getPreco_unitario()));
                 labelStock.setText(pr.stock.getUnidades_stock().toString());
                labelCodProduto.setVisible(false);
                labelPrecoProduto.setVisible(false);
                labelNomeProduto.setVisible(false);
                labelStock.setVisible(false);
                 Image image= new Image("file:"+pr.getFoto());
                 imageviewFotoProduto.setImage(image);
                
            }
        });
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   @FXML
public void handleMenuItemAdicionarProdutoNaTabela() throws IOException {
    int selectedIndex = tableviewProdutos.getSelectionModel().getSelectedIndex();

    // Verifica se um produto foi selecionado na tabela
    if (selectedIndex < 0) {
        handleMenuAlert1(); // Função que exibe um alerta informando que um produto deve ser selecionado
        return;
    }

    Produto pr = tableviewProdutos.getItems().get(selectedIndex);

    textfieldQuantidadeProduto.setText("1");

    try {
        if (textfieldQuantidadeProduto.getText().isEmpty()) {
            handleMenuAlert3();
        } else if (pr.getStock().getUnidades_stock() >= Double.parseDouble(textfieldQuantidadeProduto.getText())) {

            // Obtém os detalhes do produto selecionado
            int produtoCodigo = pr.getCod_produto();
            double produtoPreco = Double.parseDouble(labelPrecoProduto.getText());
            double produtoQuantidade = Double.parseDouble(textfieldQuantidadeProduto.getText());
            String produtoNome = labelNomeProduto.getText();
            double produtoSubtotal = produtoPreco * produtoQuantidade;
            double produtoStock = pr.stock.getUnidades_stock(); // Estoque do produto

            // Cria um novo detalhe de venda com os detalhes do produto selecionado
            DetalhesVenda detalhesVenda = new DetalhesVenda(produtoCodigo, produtoPreco, produtoStock, produtoQuantidade, produtoSubtotal, produtoNome);

            // Adiciona o detalhe de venda ao modelo de detalhes de venda
            detalhesVendaModel.addStock(detalhesVenda);
  
            
            // Feche a janela
            Stage stage = (Stage) buttonAdicionarProduto.getScene().getWindow();
            stage.close();
        } else {
            handleMenuAlert4();
        }
    } catch (Exception ex) {
        System.out.println("" + ex.toString());
    }
}

              
    public void CancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) buttonCancelar.getScene().getWindow();
        stage.close();
    }

    public void handleMenuAlert3() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/alert3.fxml"));
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

        } catch (Exception ex) {
            System.out.println("" + ex + ex.getLocalizedMessage());
            System.out.println("" + ex.toString());
        }
    }
    
    
    public void handleMenuAlert4() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/alert4.fxml"));
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

        } catch (Exception ex) {
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

        } catch (Exception ex) {
            System.out.println("" + ex + ex.getLocalizedMessage());
            System.out.println("" + ex.toString());
        }
    }
}
