/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Inventario;
import BussinessLogic.Usuario;
import DataAcessLayer.InventarioDAO;
import Service.InventarioServicos;
import Service.UsuarioServicos;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class Lista_De_InventarioController implements Initializable {
    
    
    @FXML
    private Button BtnSair;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnDetalhesInventario;

    @FXML
    private Label labelCodFuncionario;

    @FXML
    private Label labelCodProduto;

    @FXML
    private Label labelCodInventario;

    @FXML
    private Label labelNomeFuncionario;

    @FXML
    private Label labelcliente;
    @FXML
   private Label labelTipodeInventario;

    @FXML
    private TableColumn<Inventario, Integer> tableColumnCodigoInventario;

    @FXML
    private TableColumn<Inventario, Date> tableColumnData;

    @FXML
    private TableColumn<Inventario, String> tableColumnTIpoInventario;

    @FXML
    private TableColumn<Inventario, String> tableColumnUsuario;

    @FXML
    private TableView<Inventario> tableViewListaInventario;

    @FXML
    private TextField textFieldPesquisaProdutos;
    Usuario usuario;
    InventarioServicos inventarioServicos;
    UsuarioServicos usuarioServicos;
    
       public Usuario getUsuario() {
        return usuario;
    }
       
    ObservableList<Inventario> inventarioObservableList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelTipodeInventario.setVisible(false);
        
         InventarioDAO inventarioDao = new InventarioDAO();
        try {

            ResultSet rs = inventarioDao.Lista_Inventario();

            while (rs.next()) {
                Integer QueryinventarioId = rs.getInt("ID_inventario");
                Date QueryData = rs.getDate("data");
                String QueryTipoInventario = rs.getString("tipo_inventario");
                Usuario user = new Usuario();
                user.setNome(rs.getString("nome"));

              

                inventarioObservableList.add(new Inventario(QueryinventarioId, QueryData, QueryTipoInventario, user));
            }

            tableColumnCodigoInventario.setCellValueFactory(new PropertyValueFactory<>("Cod_Inventario"));
            tableColumnData.setCellValueFactory(new PropertyValueFactory<>("Data"));
            tableColumnTIpoInventario.setCellValueFactory(new PropertyValueFactory<>("Tipo_inventario"));
       

            tableColumnUsuario.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inventario, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Inventario, String> param) {
                    return new SimpleStringProperty(param.getValue().getUser().getNome());
                }
            });

            tableColumnUsuario.setCellFactory(column -> new TableCell<Inventario, String>() {
                @Override
                protected void updateItem(String user, boolean empty) {
                    super.updateItem(user, empty);
                    if (user == null || empty) {
                        setText(null);
                    } else {
                        setText(user);
                    }
                }
            });

        
            
                inventarioObservableList.sort(Comparator.comparing(Inventario::getData).reversed());
            tableViewListaInventario.setItems(inventarioObservableList);

            FilteredList<Inventario> filteredData = new FilteredList<>(inventarioObservableList, b -> true);

            textFieldPesquisaProdutos.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(inventario -> {
                    if (newValue.isEmpty() || newValue == null) {

                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (inventario.getData().toString().toLowerCase().contains(searchKeyword)) {
                        return true;
                   
                    } else if (inventario.getCod_Inventario().toString().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else if (inventario.getTipo_inventario().toLowerCase().contains(searchKeyword)) {
                        return true;

                    } else {
                        return false;
                    }

                });

            });

            SortedList<Inventario> sortdData = new SortedList<>(filteredData);

            sortdData.comparatorProperty().bind(tableViewListaInventario.comparatorProperty());
            
                 // Ordenar a tabela pelas últimas inserções (ordem decrescente de data de venda)
            //Comparator<Venda> ultimasInsercoesComparator = Comparator.comparing(Venda::getData_Venda).reversed();
            //sortdData.setComparator(ultimasInsercoesComparator);

            tableViewListaInventario.setItems(sortdData);
       

        } catch (SQLException ex) {
            Logger.getLogger(BuscaProdutosController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    
    @FXML
    public void handleDetalhesInventario() throws IOException {
        
        int selectedIndex = tableViewListaInventario.getSelectionModel().getSelectedIndex();

        // Verifica se um produto foi selecionado na tabela
        if (selectedIndex < 0) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("SELECIONE O INVENTÁRIO!");
                alert.showAndWait();
            return;
        }
        try {

            Inventario inventario =tableViewListaInventario.getItems().get(tableViewListaInventario.getSelectionModel().getSelectedIndex());
            labelCodInventario.setText(String.valueOf(inventario.getCod_Inventario()));
            //produto.setCod_produto(Integer.parseInt(labelCodProduto.getText()));
            labelCodInventario.setVisible(false);

         
            Integer Cod_Inventario= Integer.parseInt(this.labelCodInventario.getText());
            if (labelCodInventario.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("INVENTÁRIO NÃO SELECIONADO!");
                alert.showAndWait();
            return;

            } else {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(TrickController.class.getResource("/Presentation/Detalhes_Inventario.fxml"));

                Parent root = (Parent) loader.load();
                Detalhes_InventarioController detalhes_Inventariocontroller = loader.<Detalhes_InventarioController>getController();

                inventarioServicos = new InventarioServicos();
                usuarioServicos = new UsuarioServicos();
                List<Inventario> inventariosList = inventarioServicos.getDetalhesInventario(Cod_Inventario);
                detalhes_Inventariocontroller.ReceberDadosInventario(inventariosList);
                detalhes_Inventariocontroller.ReceberDadoInventario(inventario);

                // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Detalhes do Inventário");
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

    

      
    
    
      public void receberdadosUsuario(Usuario usuario) {
      //  this.usuario = usuario;
        
      //  this.labelNomeFuncionario.setText(usuario.getNome());
       // this.labelNomeFuncionario.setVisible(true);
       // this.labelCodFuncionario.setText(usuario.getCod_Funcionario().toString());
       // this.labelCodFuncionario.setVisible(false);
    }
      
    public void SairButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) BtnSair.getScene().getWindow();

        stage.close();

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
    
     public void handleMenuAlert9() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/alert9.fxml"));
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

