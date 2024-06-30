/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Unidade;
import BussinessLogic.Usuario;
import Service.UnidadeServicos;
import Service.UsuarioServicos;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class CadastroUnidadeController implements Initializable {
    
     @FXML
    private BorderPane PANESAIR;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button buttonRegistarUnidade;

    @FXML
    private Label labelCodUsuario;

    @FXML
    private Label labelNomeUsuario;

    @FXML
    private Text labelRegistoProdutos;

    @FXML
    private Label labelcodigoUsuario;

    @FXML
    private TextField textfieldNomeUnidade;
    
    
    @FXML
    private Button buttonCancelar;


      
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
   @FXML
    void SairButtonOnAction(MouseEvent event) {
  
    Stage stage = (Stage) PANESAIR.getScene().getWindow();
    stage.close();
    }
    
    
    
    @FXML
   void handleMenuCadastrarUnidade(ActionEvent event) throws SQLException {
    try {
        if (textfieldNomeUnidade.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, insira o nome da unidade.");
            alert.showAndWait();
            return;
        }

        String nome = textfieldNomeUnidade.getText();

        Unidade unidade = new Unidade();
        unidade.setDescricao_Unidade(nome);

        UnidadeServicos unidadeServicos = new UnidadeServicos();
        boolean status = unidadeServicos.registrarUnidade(unidade);

        if (status) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Unidade '" + unidade.getDescricao_Unidade()+ "' cadastrada com sucesso.");
            textfieldNomeUnidade.clear();
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao registrar a unidade. Tente novamente.");
            alert.showAndWait();
        }
    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("Erro ao gravar a unidade: " + e.getMessage());
        alert.showAndWait();
    }
}

}
