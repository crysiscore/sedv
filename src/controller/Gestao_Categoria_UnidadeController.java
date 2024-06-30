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
public class Gestao_Categoria_UnidadeController implements Initializable {
    


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
    
    UsuarioServicos usuarioServicos;
    Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;

        this.labelCodUsuario.setText(usuario.getCod_Funcionario().toString());
        this.labelCodUsuario.setVisible(false);
        this.labelNomeUsuario.setText(usuario.getNome());
        this.labelNomeUsuario.setVisible(false);

    }
    

    @FXML
    void SairButtonOnAction(ActionEvent event) {
    Stage stage = (Stage) buttonCancelar11.getScene().getWindow();
    stage.close();
    }

    @FXML
    void handleMenuItemCadastroUnidade(ActionEvent event) {

          
        try {
       
        String codUsuario = this.labelCodUsuario.getText();
          
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/CadastroUnidade.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        
        CadastroUnidadeController cadastroUnidadeController= loader.<CadastroUnidadeController>getController();
       
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        cadastroUnidadeController.receberdadosUsuario(selectedUsuario);
        
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("CADASTRO DE UNIDADE");
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
    void handleMenuItemCadastroCategoria(ActionEvent event) {
        
         try {
       
        String codUsuario = this.labelCodUsuario.getText();
          
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/CadastroCategoria.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        
        CadastroCategoriaController cadastroCategoriaController= loader.<CadastroCategoriaController>getController();
       
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        cadastroCategoriaController.receberdadosUsuario(selectedUsuario);
        
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("CADASTRO DE CATEGORIA");
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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
