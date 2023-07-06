/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Usuario;
import BussinessLogic.Venda;
import DataAcessLayer.VendaDao;
import Service.UsuarioServicos;
import Service.VendaServicos;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class Lista_VendaController implements Initializable {
    
     @FXML
    private TableView<Venda> tableViewListaVenda;
    
    @FXML
    private TableColumn<Venda, String> Forma_Pagamento;

    @FXML
    private TableColumn<Venda, String> tableColumnCliente;

    @FXML
    private TableColumn<Venda, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Venda, Date> tableColumnData;

    @FXML
    private TableColumn<Venda, Integer> tableColumnNuit;

    @FXML
    private TableColumn<Venda, Double> tableColumnTotal;

    @FXML
    private TableColumn<Venda, String> tableColumnUsuario;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField textFieldPesquisaProdutos;
    
    @FXML
    private Label labelCodFuncionario;

    @FXML
    private Label labelNomeFuncionario;

    @FXML
    private Label labelCodVenda;
    
    @FXML
    private Label labelcliente;

    @FXML
    private Button btnDetalhesVenda;
    
    @FXML
    private Button BtnSair;
    
    VendaServicos vendaServicos;
    UsuarioServicos usuarioServicos;
    Usuario usuario;
    ObservableList <Venda> vendaObservableList =FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        

           VendaDao vendaDao = new VendaDao();
             try {
                
                 ResultSet rs=vendaDao.Listagem_Venda();
                 
                 while(rs.next()){
                 Integer QueryVendaId= rs.getInt("Cod_Venda");
                 Date QueryData = rs.getDate("Data_Venda");
                 Double QueryToatalVenda = rs.getDouble("Total_Venda");
                 String QueryCliente = rs.getString("Nome_cliente");
                 Integer QueryNuit = rs.getInt("Nuit_cliente");
                // Integer QueryUser = rs.getInt("usuario_Cod_Funcionario");
                // Usuario user = new Usuario();
                // user.setNome(rs.getString("Nome"));
                 String Queryuser = rs.getString("nome");
                 String  QueryFormaPagamento= rs.getString("Forma_Pagamento"); 
                 
                
                 vendaObservableList.add(new Venda(QueryVendaId,QueryData,QueryToatalVenda,QueryCliente,QueryNuit,Queryuser,QueryFormaPagamento));
                 }
                 
                 
                 tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("Cod_Venda"));
                 tableColumnData.setCellValueFactory(new PropertyValueFactory<>("Data_Venda"));
                 tableColumnTotal.setCellValueFactory(new PropertyValueFactory<>("Total_Venda"));
                 tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("Nome_cliente"));
                 tableColumnNuit.setCellValueFactory(new PropertyValueFactory<>("Nuit_cliente"));
                // tableColumnUsuario.setCellValueFactory(new PropertyValueFactory<>("Nome"));
                 tableColumnUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));                 
                 Forma_Pagamento.setCellValueFactory(new PropertyValueFactory<>("Forma_Pagamento"));

                 tableViewListaVenda.setItems(vendaObservableList);
                  
            
                 
                 FilteredList<Venda> filteredData =new FilteredList<> (vendaObservableList, b ->true);
                 
                 textFieldPesquisaProdutos.textProperty().addListener((observable, oldValue, newValue) ->{
                 filteredData.setPredicate(venda -> {
                     if(newValue.isEmpty()|| newValue == null){
                         
                         return true;
                     }
                     
                     
                     String searchKeyword = newValue.toLowerCase();

                     if (venda.getData_Venda().toString().toLowerCase().contains(searchKeyword)) {
                         return true;
                     } else if (venda.getNuit_cliente().toString().toLowerCase().contains(searchKeyword)) {
                         return true;

                     }else
                         
                     return false;
                     
                 });
                 
                 });
                 
  
                
                 SortedList <Venda> sortdData = new SortedList <>(filteredData);
                 
                 sortdData.comparatorProperty().bind(tableViewListaVenda.comparatorProperty());
                 
                 tableViewListaVenda.setItems(sortdData);
                 
             } catch (SQLException ex) {
                 Logger.getLogger(BuscaProdutosController.class.getName()).log(Level.SEVERE, null, ex);
                 
       
              } 
    }
    

    
    @FXML
    public void handleDetalhesVenda() throws IOException {

        try {
            
            Venda venda =tableViewListaVenda.getItems().get(tableViewListaVenda.getSelectionModel().getSelectedIndex());
            labelCodVenda.setText(String.valueOf(venda.getCod_Venda()));
            //produto.setCod_produto(Integer.parseInt(labelCodProduto.getText()));
            labelCodVenda.setVisible(false);
         
            Integer codVenda = Integer.parseInt(this.labelCodVenda.getText());
            if (labelCodVenda.getText().isEmpty()) {

                handleMenuAlert5();

            } else {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(TrickController.class.getResource("/Presentation/DetalhesVenda.fxml"));

                Parent root = (Parent) loader.load();
                DetalhesVendaController detalhesVendacontroller = loader.<DetalhesVendaController>getController();

                vendaServicos = new VendaServicos();
                usuarioServicos = new UsuarioServicos();
               List<Venda> vendasList = vendaServicos.getDetalhesVenda(codVenda);
               detalhesVendacontroller.ReceberDadosVenda(vendasList); 
                detalhesVendacontroller.ReceberDadoVenda(venda);

                // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Detalhes de Venda");
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);
                dialogStage.setMaximized(false);
                dialogStage.setResizable(false);
                // Mostra o Dialog e espera atÃ© que o usuÃ¡rio o feche
                dialogStage.show();

               // Stage stage = (Stage) btnDetalhesVenda.getScene().getWindow();
                //stage.close();

            }
       
        
         } catch (IOException ex) {
        // Handle the IOException (e.g., show an error message to the user)
        ex.printStackTrace();
    } catch (NumberFormatException ex) {
        // Handle the NumberFormatException (e.g., show an error message to the user)
        ex.printStackTrace();
    } catch (SQLException ex) {
        // Handle the SQLException (e.g., show an error message to the user)
        ex.printStackTrace();
    }

    }

    
    
    public Usuario getUsuario() {
        return usuario;
    }
     public void receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;
        
        this.labelNomeFuncionario.setText(usuario.getNome());
        this.labelNomeFuncionario.setVisible(true);
        this.labelCodFuncionario.setText(usuario.getCod_Funcionario().toString());
        this.labelCodFuncionario.setVisible(false);
    }
     public void handleMenuItemVendaAdicionarVenda() {

        try {

            String codUsuario = this.labelCodFuncionario.getText();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/Venda.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            VendaController vendaController = loader.<VendaController>getController();
            usuarioServicos = new UsuarioServicos();
            Usuario selectedUsuario = new Usuario();
            selectedUsuario = usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
            vendaController.receberdadosUsuario(selectedUsuario);
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Vendas");
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

     
     public void handleMenuItemDetalhesVenda() {

        try {

            String codUsuario = this.labelCodFuncionario.getText();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/DetalhesVenda.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            DetalhesVendaController detalhesvendaController = loader.<DetalhesVendaController>getController();
            
            usuarioServicos = new UsuarioServicos();
            Usuario selectedUsuario = new Usuario();
            selectedUsuario = usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
            detalhesvendaController.receberdadosUsuario(selectedUsuario);
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Vendas");
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

     
     
    public void handleMenuAlert5() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/alert5.fxml"));
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
    
        public void CancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) BtnSair.getScene().getWindow();
        stage.close();
    }
}

     

    

