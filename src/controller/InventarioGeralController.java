/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.DetalhesVenda;
import BussinessLogic.Inventario;
import BussinessLogic.InventarioPrincipal;
import BussinessLogic.Produto;
import BussinessLogic.Stock;
import BussinessLogic.StockLevel;
import BussinessLogic.Usuario;
import BussinessLogic.Venda;
import DataAcessLayer.InventarioDAO;
import DataAcessLayer.ProdutoDAO;
import DataAcessLayer.StockDAO;
import Model.DetalhesVendaModel;
import Model.InventarioModel;
import Service.ProdutosServicos;
import Service.UsuarioServicos;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class InventarioGeralController implements Initializable {
    
    
   

    @FXML
    private DatePicker DatePickerDATA;

    @FXML
    private Pane PanePane;

    @FXML
    private Button btnActualiar;

    @FXML
    private Button btnGravar;

    @FXML
    private Button btnRemover;

    @FXML
    private TableView<Inventario> tableViewListaProdutos;

    @FXML
    private TableColumn<Inventario, Integer> columnCodigoProduto;

    @FXML
    private TableColumn<Inventario, String> columnNomeProduto;

    @FXML
    private TableColumn<Inventario, Double> columnQuantidadeExistenteContada;

    @FXML
    private TableColumn<Inventario, Double> columnStockExistente;
    
      @FXML
    private TableColumn<Inventario, Double> ColumnDiferenca;


    @FXML
    private ImageView imageviewFotoProduto;

    @FXML
    private Label labelCodProduto;

    @FXML
    private Label labelNomeProduto;

    @FXML
    private Label labelPrecoProduto;

    @FXML
    private Label labelUsuario;

    @FXML
    private Label labelsubtotal;

    @FXML
    private Label labelsubtotal2;
    
    @FXML
    private Label labelDiferenca;
    
     @FXML
    private Label labelQuantidadecontada;


    @FXML
    private TextField textfieldProcurarProduto;

    @FXML
    private TextField textfieldQuantidadeProduto;


    /**
     * Initializes the controller class.
     */

    
  UsuarioServicos usuarioServicos;

    Inventario inv;
    Stock stock;
    Usuario usuario;
    Produto produto;
    Venda venda;
   
    
    private InventarioModel inventarioModel= new InventarioModel();

    
    ProdutosServicos servicoProdutos;
   // ObservableList<Produto> produtoObservableList = FXCollections.observableArrayList();
    ObservableList<Inventario> iventario = FXCollections.observableArrayList();

     
    
    public void initModel(InventarioModel model) {
        if (this.inventarioModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.inventarioModel = model;
    }

     public Usuario getUsuario() {
        return usuario;
    }

    public void receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;

       // this.labelCodigoUser.setText(usuario.getCod_Funcionario().toString());
        //this.labelCodigoUser.setVisible(false);
        //this.labelNomeUser.setText(usuario.getNome());

    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         DatePickerDATA.setVisible(false);
      //   labelParcial.setVisible(false);
        
        this.columnCodigoProduto.setCellValueFactory(new PropertyValueFactory<Inventario, Integer>("Cod_Produto"));
        this.columnNomeProduto.setCellValueFactory(new PropertyValueFactory<Inventario, String>("Nome"));
        this.columnStockExistente.setCellValueFactory(new PropertyValueFactory<Inventario, Double>("Stock"));
     
       

        columnQuantidadeExistenteContada.getTableView().setEditable(true);
            columnQuantidadeExistenteContada.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            
              columnQuantidadeExistenteContada.setOnEditCommit(event -> {
            
               Inventario i = event.getRowValue();
                      
            i.setQuantidade_Contada(event.getNewValue()); // Update the model directly
              Double diferenca = i.getStock() - i.getQuantidade_Contada();
            i.setDiferenca_Existente_Contada(diferenca);
             //tableViewListaProdutos.refresh();
            });
            
        
            
            this.columnQuantidadeExistenteContada.setCellValueFactory(new PropertyValueFactory<Inventario, Double>("Quantidade_Contada"));

            this.ColumnDiferenca.setCellValueFactory(new PropertyValueFactory<Inventario, Double>("Diferenca_Existente_Contada"));
                            
          
            iventario = inventarioModel.getInventarioList();

            tableViewListaProdutos.setItems(iventario);

          
                tableViewListaProdutos.refresh();
       
       
            
             }
             
 
     
    



    @FXML
    public void handleMenuItemAdicionarProduto() throws IOException {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/InventarioParcial.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            InventarioParcialController inventarioParcialController = loader.<InventarioParcialController>getController();
            
            usuarioServicos = new UsuarioServicos();
            
            inventarioParcialController.receberdadosUsuario(usuario);
            inventarioParcialController.initModel(inventarioModel);

            // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Adicionar Produto");
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
    
    
    
   @FXML
void removerProdutodaTabela() {
    int response = JOptionPane.showConfirmDialog(null, "O Produto foi selecionado???", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

    if (response == JOptionPane.YES_OPTION) {
        Inventario selecionada = tableViewListaProdutos.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            // Remove o item selecionado da lista iventario
            iventario.remove(selecionada);
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
    public void ActualizarStock() throws IOException, SQLException {

       StockDAO dao = new StockDAO();

        if (tableViewListaProdutos.getItems().isEmpty()) {
         //  handleMenuAlert();

     } else {

            int response = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja actuaizar o Stock introduzido na tabela???", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {

               dao.ActualizarStock(tableViewListaProdutos.getItems());
          //  tableViewListaProdutos.getItems().clear();
                JOptionPane.showMessageDialog(null, "O Stock foi actuaizado com Sucesso!");

            } else if (response == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "O Stock não foi actuaizado!");

            } else if (response == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Escolha uma das opções!");
            }
        }
    }
    
    
    
    
     
    @FXML
    public void handleMenuItemRegistarInventario() throws IOException, SQLException {
        InventarioDAO dao = new InventarioDAO();
        
    //   labelParcial.setText("Parcial");
        DatePickerDATA.setValue(LocalDate.now());
   
                        
        
        Date Data_Venda = java.sql.Date.valueOf(DatePickerDATA.getValue());
     //   Integer Cod_Usuario = Integer.parseInt(labelCodigoUser.getText());
       // String tipo_de_inventario = labelParcial.getText();
        
        if (tableViewListaProdutos.getItems().isEmpty()) {
            handleMenuAlert();
            
        } else {
            
            int response = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja registrar o Inventário???", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (response == JOptionPane.YES_OPTION) {
                
                
                dao.RegistarInventarioItem(tableViewListaProdutos.getItems());
                //tableViewListaProdutos.getItems().clear();
                InventarioPrincipal inventarioPrincipal = new InventarioPrincipal();
                inventarioPrincipal.setData(Data_Venda);
                inventarioPrincipal.setUser(usuario);
            //    inventarioPrincipal.setTipo_De_Inventario(tipo_de_inventario);
                
                
                dao.RegistarInventario(inventarioPrincipal);
                JOptionPane.showMessageDialog(null, "O Inventário foi Cadastrado com Sucesso!");
                tableViewListaProdutos.getItems().clear();
         
                
            } else if (response == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "O Inventário não foi Cadastrado!");
                
            } else if (response == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Escolha uma das opções!");
            }
        }
  
        
    }
    
    public void handleMenuAlert() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/alert.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //BuscaProdutosController buscaProdutosController= loader.getController();
            AlertController alertController = loader.<AlertController>getController();

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


