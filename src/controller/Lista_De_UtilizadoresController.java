/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Usuario;
import DataAcessLayer.UsuarioDAO;
import Service.UsuarioServicos;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class Lista_De_UtilizadoresController implements Initializable {
        @FXML
    private TableView<Usuario> tableviewListaUtilizadores;

    @FXML
    private TableColumn<Usuario, String> TableColumnCategoriaUtilizador;

    @FXML
    private TableColumn<Usuario, String> TableColumnNomeUtilizador;

    @FXML
    private TableColumn<Usuario, String> TableColumnSenhaUtilizador;
     @FXML
    private TableColumn<Usuario, String> TableColumnStatus;


    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonEditarUtilizador;

    @FXML
    private ImageView imagemCarrinhoProduto;

    @FXML
    private Text labelRegistoProdutos;
    
    @FXML
    private Label labelCodUsuario;

    @FXML
    private Label labelNomeUsuario;
    @FXML
    private Label labelselectedusuario;
     @FXML
    private TextField textFieldPesquisaUtilizador;


   Usuario usuario;
   
   UsuarioServicos usuarioServicos;
    ObservableList <Usuario> usuarioObservableList =FXCollections.observableArrayList();
        public Usuario getUsuario() {
        return usuario;
    }
       
         public void  receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;
      
        this.labelCodUsuario.setText(usuario.getCod_Funcionario().toString());
        this.labelCodUsuario.setVisible(false);
        this.labelNomeUsuario.setText(usuario.getNome());
        this.labelNomeUsuario.setVisible(false);
        
         }



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         UsuarioDAO usuarioDAO = new UsuarioDAO();
             try {
                
                 ResultSet rs=usuarioDAO.ListarUsuarios();
                 
                 while(rs.next()){
                 Integer QueryCodigo= rs.getInt("Cod_Funcionario");
                 String QueryNome=rs.getString("Nome");
                 String QuerySenha = rs.getString("Senha");
                 String QueryCategoria = rs.getString("Categoria");
                 String QueryStatus=rs.getString("Status");
           
             
                 usuarioObservableList.add(new Usuario(QueryCodigo,QueryNome,QuerySenha,QueryCategoria, QueryStatus));
                 }
                 
                 
                 TableColumnNomeUtilizador.setCellValueFactory(new PropertyValueFactory<>("Nome"));
                 TableColumnSenhaUtilizador.setCellValueFactory(new PropertyValueFactory<>("Senha"));
                 TableColumnCategoriaUtilizador.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
                 TableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

                 
                

                 tableviewListaUtilizadores.setItems(usuarioObservableList);
                  
              FilteredList<Usuario> filteredData =new FilteredList<> (usuarioObservableList, b ->true);
                 
                 textFieldPesquisaUtilizador.textProperty().addListener((observable, oldValue, newValue) ->{
                 filteredData.setPredicate(usuario -> {
                     if(newValue.isEmpty()|| newValue == null){
                         
                         return true;
                     }
                     
                     
                     String searchKeyword=newValue.toLowerCase();
                     
                     if(usuario.getNome().toLowerCase().indexOf(searchKeyword)>-1){
                         return true;
                     }else if(usuario.getCategoria().toLowerCase().indexOf(searchKeyword)>-1){
                         return true;
                         
                         
                                
                     }else
                         
                     return false;
                     
                 });
                 
                 });
                 
  
                
                 SortedList <Usuario> sortdData = new SortedList <>(filteredData);
                 
                 sortdData.comparatorProperty().bind(tableviewListaUtilizadores.comparatorProperty());
                 
                 tableviewListaUtilizadores.setItems(sortdData);
                 
    }       catch (SQLException ex) {    
                Logger.getLogger(Lista_De_UtilizadoresController.class.getName()).log(Level.SEVERE, null, ex);
            }    
    
}
    
    
        private void setCellValueFromTableToTextField() {
        tableviewListaUtilizadores.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Usuario us = tableviewListaUtilizadores.getItems().get(tableviewListaUtilizadores.getSelectionModel().getSelectedIndex());
                //labelselectedusuario.setText(String.valueOf(us.getCod_Funcionario()));
            }
        });
    }
    
public void handleMenuItemEdicaoUtilizadores() throws SQLException, IOException {
    try {
        Usuario selectedUsuario = tableviewListaUtilizadores.getSelectionModel().getSelectedItem();
        if (selectedUsuario == null) {
            Alert alert = new Alert(AlertType.INFORMATION);

            alert.setTitle("Alerta ");
            //alert.setHeaderText("Exemplo de Alerta");
            alert.setContentText("Utilizador não foi selecionado!.");
            alert.showAndWait();
        }

    
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/Cadastro_Utilizador.fxml"));

        AnchorPane page = (AnchorPane) loader.load();
        Cadastro_UtilizadorController cadastro_UtilizadorController = loader.<Cadastro_UtilizadorController>getController();

        // Não é necessário mais obter o código do usuário aqui, pois já temos o usuário selecionado
        cadastro_UtilizadorController.receberdadosUsuarioedicao(selectedUsuario);
        cadastro_UtilizadorController.OcultarBotoes();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("EDIÇÃO DE USUÁRIO");
        dialogStage.setMaximized(false);
        dialogStage.setResizable(false);

        // Criando uma nova cena com a página carregada
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.show();

       // Stage stage = (Stage) buttonEditarUtilizador.getScene().getWindow();
      //  stage.close();
    } catch (Exception ex) {
        System.out.println("" + ex + ex.getLocalizedMessage());
        System.out.println("" + ex.toString());
    }
}


    
    
          public void CancelButtonOnAction(ActionEvent event){
        Stage stage =(Stage) buttonCancelar.getScene().getWindow();
        stage.close();
    }
        
}