/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Usuario;
import Service.UsuarioServicos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class Gestao_De_UtilizadoresController implements Initializable {

    
    
    
    @FXML
    private Button ButtonListarUtilizador;

    @FXML
    private Button buttonCadastrarUtilizador;

    @FXML
    private Button buttonCancelar11;
    
    @FXML
    private Label labelCodUsuario;

    @FXML
    private Label labelNomeUsuario;
    UsuarioServicos usuarioServicos ;
    Usuario usuario;
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
    }    
    


    @FXML
    void SairButtonOnAction(ActionEvent event) {
    Stage stage = (Stage) buttonCancelar11.getScene().getWindow();
    stage.close();
    }

    
     public void handleMenuItemCadastroUtilizadores (){
         
          
        try {
       
        String codUsuario = this.labelCodUsuario.getText();
          
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/Cadastro_Utilizador.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        
        Cadastro_UtilizadorController cadastro_UtilizadorController= loader.<Cadastro_UtilizadorController>getController();
       
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        cadastro_UtilizadorController.receberdadosUsuario(selectedUsuario);
        cadastro_UtilizadorController.ocultarbotaoeditar();
        
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("CADASTRO DE UTILIZADORES");
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
     
     
          public void handleMenuItemListaUtilizadores (){
         
          
        try {
       
        String codUsuario = this.labelCodUsuario.getText();
          
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/Lista_De_Utilizadores.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        
        Lista_De_UtilizadoresController lista_De_UtilizadoresController= loader.<Lista_De_UtilizadoresController>getController();
       
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        lista_De_UtilizadoresController.receberdadosUsuario(selectedUsuario);
        
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("LISTA DE UTILIZADORES");
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
