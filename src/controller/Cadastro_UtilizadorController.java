/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Usuario;
import Service.UsuarioServicos;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class Cadastro_UtilizadorController implements Initializable {

        
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button buttonRegistarUtilizador;

    @FXML
    private Button buttonVerUtilizador;
    
    @FXML
    private Button buttonEditarUtilizador;

    @FXML
    private ComboBox<String> comboBoxCategoria;
      @FXML
    private ComboBox<String> comboBoxStatus;

    @FXML
    private ImageView imagemCarrinhoProduto;

    @FXML
    private Text labelRegistoProdutos;

    @FXML
    private TextField textFieldSenha;

    @FXML
    private TextField textfieldNomeUtilizador;
    
    @FXML
    private Label labelCodUsuario;

    @FXML
    private Label labelNomeUsuario;
    
    @FXML
    private Label labelcodigoUsuario;
    
    @FXML
    private BorderPane PANESAIR;
    
    
    
    Usuario usuario;
    UsuarioServicos usuarioServicos;

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
     
         
         
         public void  receberdadosUsuarioedicao(Usuario usuario) {
        this.usuario = usuario;
    
        
        this.textFieldSenha.setText(usuario.getSenha());
        this.textfieldNomeUtilizador.setText(usuario.getNome());
        this.comboBoxCategoria.setValue(usuario.getCategoria());
        this.comboBoxStatus.setValue(usuario.getStatus());
        this.labelcodigoUsuario.setText(usuario.getCod_Funcionario().toString());
        labelcodigoUsuario.setVisible(false);
        
         }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelcodigoUsuario.setVisible(false);
         ObservableList<String> Status = FXCollections.observableArrayList(
            "Ativo", 
            "Inativo"
          
        );

        // Adicione os itens ao ComboBox
        comboBoxStatus.setItems(Status);
        
    
        
        
         ObservableList<String> categoria = FXCollections.observableArrayList(
            "Gerente", 
            "Funcionario"
          
        );

        // Adicione os itens ao ComboBox
        comboBoxCategoria.setItems(categoria);
    }
    
    
    
 public static String hashPassword(String password) {
        try {
            // Obter uma instância do algoritmo de hash SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Aplicar o hash à senha
            byte[] hash = digest.digest(password.getBytes());
            // Converter o hash em uma representação hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Tratar erro de algoritmo de hash não encontrado
            e.printStackTrace();
            return null;
        }
    }



public void handleMenuItemcadastroUtilizador() {
    try {
        // Verificar se os campos foram preenchidos
        if (textfieldNomeUtilizador.getText().isEmpty() || 
            textFieldSenha.getText().isEmpty() || 
            comboBoxCategoria.getSelectionModel().isEmpty()|| 
            comboBoxStatus.getSelectionModel().isEmpty() ) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta ");
                alert.setContentText("Por favor, preencha todos os campos antes de cadastrar o usuário.");
                alert.showAndWait();
                return; // Retorna aqui para evitar continuar o processamento se os campos não estiverem preenchidos
        }

        // Campos preenchidos, continuar com o cadastro
        String nome = this.textfieldNomeUtilizador.getText();
        String senha = this.textFieldSenha.getText();
        String categoria = this.comboBoxCategoria.getValue(); // Obtém o valor selecionado diretamente como uma String
        String status1 = this.comboBoxStatus.getValue(); 
        String senhaHash = Cadastro_UtilizadorController.hashPassword(senha); // Hash da senha
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senhaHash); // Define a senha como o hash
        usuario.setCategoria(categoria);
        usuario.setStatus(status1);// Armazena a categoria como uma string

        UsuarioServicos usuariosServicos = new UsuarioServicos();
        boolean status = usuariosServicos.RegistarUsuario(usuario);

        limparcampos();

        if (status) {
            JOptionPane.showMessageDialog(null, "Usuário '" + usuario.getNome() + "' cadastrado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao registrar o usuário. Tente novamente.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao gravar o usuário: " + e.getMessage());
    }
}



public void handleMenuEditartroUtilizador() {
    try {
        // Verificar se os campos foram preenchidos
        if (textfieldNomeUtilizador.getText().isEmpty() || 
            textFieldSenha.getText().isEmpty() || 
            comboBoxCategoria.getSelectionModel().isEmpty()|| 
            comboBoxStatus.getSelectionModel().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta ");
            alert.setContentText("Preencha todos os campos!");
            alert.showAndWait();
            return;
        }

        // Campos preenchidos, continuar com a edição
        String codigo = labelcodigoUsuario.getText();
        String nome = this.textfieldNomeUtilizador.getText();
        String senha = this.textFieldSenha.getText();
        String categoria = this.comboBoxCategoria.getValue(); // Obtém o valor selecionado diretamente como uma String
        String status = this.comboBoxStatus.getValue(); 
        String senhaHash = Cadastro_UtilizadorController.hashPassword(senha); // Hash da senha

        Usuario usuario = new Usuario();
        usuario.setCod_Funcionario(Integer.parseInt(codigo));
        usuario.setNome(nome);
        usuario.setSenha(senhaHash); // Define a senha como o hash
        usuario.setCategoria(categoria);
        usuario.setStatus(status);// Armazena a categoria como uma string

        UsuarioServicos usuariosServicos = new UsuarioServicos();
        usuariosServicos.EditarUtilizador(usuario);

        limparcampos();

        JOptionPane.showMessageDialog(null, "Usuário '" + usuario.getNome() + "' editado com sucesso.");
      
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao editar o usuário: " + e.getMessage());
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
         Stage stage =(Stage) buttonVerUtilizador.getScene().getWindow();
        stage.close();
   }

private void limparcampos() {
    this.textfieldNomeUtilizador.setText("");
    this.textFieldSenha.setText("");
    this.comboBoxCategoria.setValue(null);
    this.comboBoxStatus.setValue(null);
}

 @FXML
    void SairButtonOnAction() {
    Stage stage = (Stage) PANESAIR.getScene().getWindow();
    stage.close();
    }

    public void OcultarBotoes() {
        buttonRegistarUtilizador.setVisible(false);
        labelRegistoProdutos.setText("ACTUALIZAÇÃO DOS DADOS DO UTILIZADOR");
        this.buttonVerUtilizador.setVisible(false);
        labelNomeUsuario.setVisible(false);
        labelCodUsuario.setVisible(false);

    }
    
        public void ocultarbotaoeditar(){
             this.buttonEditarUtilizador.setVisible(false);
         
         }
}
