/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Previlegio;
import BussinessLogic.Usuario;
import DataAcessLayer.UsuarioDAO;
import Service.UsuarioServicos;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Neusia
 */
public class LoginPage1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    ResultSet rs;
    Usuario usuario;

    @FXML
    private Pane PanePane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnLogin;

    @FXML
    private ImageView imageViewLock;

    @FXML
    private Label labelLoginPageErro;

    @FXML
    private PasswordField passwordFieldSenha;

    @FXML
    private TextField tfUsuario;
    UsuarioServicos usuarioServicos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        passwordFieldSenha.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                loginComEnter();
            }

        });

        tfUsuario.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                loginComEnterUser();
            }

        });
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void mudacorloginEntered() {
        btnLogin.setStyle("-fx-background-color: #FFF");
        btnLogin.setStyle("-fx-background-radius: 13");
    }

    public void mouseexitbuttonlogin() {
        btnLogin.setStyle("-fx-background-color: #FFF");
        btnLogin.setStyle("-fx-background-radius: 13");
    }

    public void mudacorcancel() {
        btnCancel.setStyle("-fx-background-color: #FFF");
        btnCancel.setStyle("-fx-background-radius: 13");
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


    public void loginButtonOnAction(ActionEvent event) {
        // Usuario user = new Usuario();

        String username = this.tfUsuario.getText();
        TrickController Trick = new TrickController();
  
        String password1 = this.passwordFieldSenha.getText();
        String password = LoginPage1Controller.hashPassword(password1);
        
        UsuarioServicos us = new UsuarioServicos();
        this.labelLoginPageErro.setText("");
        
        if(passwordFieldSenha.getText().isEmpty() &&tfUsuario.getText().isEmpty()){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("PREENCHA OS CAMPOS");
        alert.showAndWait();
        }else{

        try {
            if (us.AutenticarUsuario(username, password) == true) {

                UsuarioDAO userDao = new UsuarioDAO();
                this.rs = userDao.getCategoriaUsuario(username, password);
                this.rs.next();
                String categoriaUsuario = this.rs.getString("Categoria");
                String codUsuario = this.rs.getString("Cod_Funcionario");
                String status = this.rs.getString("Status");

                if (categoriaUsuario.contentEquals("Funcionario"))  {
                    Previlegio prev = us.VerificarPrevilegios();
                    us.Login(username, password);
                    if (status.equals("Inativo")) {
                        Alert alerta = new Alert(AlertType.INFORMATION);
                        alerta.setTitle("Informação");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Não é possível acesar pois o Utilizador encontra-se Inativo!");
                        alerta.showAndWait();
                    } else {
                        //Trick.UserInfo(username, codUsuario);
                          FXMLLoader loader = new FXMLLoader();

                        loader.setLocation(TrickController.class.getResource("/Presentation/Trick1.fxml"));

                        AnchorPane page = (AnchorPane) loader.load();
                        TrickController trickController = loader.<TrickController>getController();

                        usuarioServicos = new UsuarioServicos();
                        Usuario selectedUsuario = new Usuario();
                        selectedUsuario = usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
                        trickController.receberdadosUsuario(selectedUsuario);

                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();

                        // Criando um Estágio de Diálogo (Stage Dialog)
                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("REGISTO DE VENDAS");
                        dialogStage.setAlwaysOnTop(false);
                        Scene scene = new Scene(page);
                        dialogStage.setScene(scene);
                        dialogStage.setResizable(false);

                        //dialogStage.setFullScreen(true);
                        dialogStage.setMaximized(true);
                        // Mostra o Dialog e espera até que o usuário o feche
                        dialogStage.showAndWait();
                    }
                } else if (categoriaUsuario.contentEquals("Gerente")) {

                    Previlegio prev = us.VerificarPrevilegios();
                    us.Login(username, password);

                    // Trick.UserInfo(username, codUsuario);
                    FXMLLoader loader = new FXMLLoader();

                    loader.setLocation(TrickController.class.getResource("/Presentation/Trick1.fxml"));
                    AnchorPane page = (AnchorPane) loader.load();
                    TrickController trickController = loader.<TrickController>getController();
                    // CadStockController cadStockController = loader.<CadStockController>getController();

                    usuarioServicos = new UsuarioServicos();
                    Usuario selectedUsuario = new Usuario();
                    selectedUsuario = usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));

                    trickController.receberdadosUsuario(selectedUsuario);

                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.close();

                    // Criando um Estágio de Diálogo (Stage Dialog)
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("REGISTO DE VENDAS");
                    dialogStage.setAlwaysOnTop(false);
                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);
                    dialogStage.setResizable(false);
                    //dialogStage.setFullScreen(true);
                    dialogStage.setMaximized(true);
                    // Mostra o Dialog e espera até que o usuário o feche
                    dialogStage.showAndWait();

                }

            } else {
                this.labelLoginPageErro.setText("Nome e senha do usuário não correspondem!");
                // this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/images/lock.png")));
            }

        } catch (Exception ex) {
            System.out.println("" + ex + ex.getLocalizedMessage());
            System.out.println("" + ex.toString());
        }
        }
    }

    public void loginComEnter() {

        String username = this.tfUsuario.getText();
        TrickController Trick = new TrickController();

        String password1 = this.passwordFieldSenha.getText();
        String password = LoginPage1Controller.hashPassword(password1);
        UsuarioServicos us = new UsuarioServicos();
        this.labelLoginPageErro.setText("");
        //this.imageViewLock.setIcon(new ImageIcon(getClass().getResource("/images/lock_off.png")));
        
         if(passwordFieldSenha.getText().isEmpty() &&tfUsuario.getText().isEmpty()){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("PREENCHA OS CAMPOS");
        alert.showAndWait();
        }else{

        try {
            if (us.AutenticarUsuario(username, password) == true) {

                UsuarioDAO userDao = new UsuarioDAO();
                this.rs = userDao.getCategoriaUsuario(username, password);
                this.rs.next();
                String categoriaUsuario = this.rs.getString("Categoria");
                String codUsuario = this.rs.getString("Cod_Funcionario");
                String status = this.rs.getString("Status");
                if (status.equals("Inativo")) {
                    Alert alerta = new Alert(AlertType.INFORMATION);
                    alerta.setTitle("Informação");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Não é possível acesar pois o Utilizador encontra-se Inativo!");
                    alerta.showAndWait();

                } else {

                    if (categoriaUsuario.contentEquals("Funcionario")) {
                        Previlegio prev = us.VerificarPrevilegios();
                        us.Login(username, password);

                        if (status.equals("Inativo")) {
                            Alert alerta = new Alert(AlertType.INFORMATION);
                            alerta.setTitle("Informação");
                            alerta.setHeaderText(null);
                            alerta.setContentText("Não é possível acesar pois o Utilizador encontra-se Inativo!");
                            alerta.showAndWait();
                        } else {

                            //Trick.UserInfo(username, codUsuario);
                            FXMLLoader loader = new FXMLLoader();

                            loader.setLocation(TrickController.class.getResource("/Presentation/Trick1.fxml"));

                            AnchorPane page = (AnchorPane) loader.load();
                            TrickController trickController = loader.<TrickController>getController();

                            usuarioServicos = new UsuarioServicos();
                            Usuario selectedUsuario = new Usuario();
                            selectedUsuario = usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
                            trickController.receberdadosUsuario(selectedUsuario);
                            // cadStockController.setUsuario(selectedUsuario);
                            //trickController.UserInfo(username,codUsuario);

                            Stage stage = (Stage) btnLogin.getScene().getWindow();
                            stage.close();

                            // Criando um Estágio de Diálogo (Stage Dialog)
                            Stage dialogStage = new Stage();
                            dialogStage.setTitle("REGISTO DE VENDAS");
                            Scene scene = new Scene(page);
                            dialogStage.setScene(scene);
                            dialogStage.setResizable(false);

                            //dialogStage.setFullScreen(true);
                            dialogStage.setMaximized(true);
                            // Mostra o Dialog e espera até que o usuário o feche
                            dialogStage.showAndWait();
                        }
                    } else if (categoriaUsuario.contentEquals("Gerente")) {

                        Previlegio prev = us.VerificarPrevilegios();
                        us.Login(username, password);

                        // Trick.UserInfo(username, codUsuario);
                        FXMLLoader loader = new FXMLLoader();

                        loader.setLocation(TrickController.class.getResource("/Presentation/Trick1.fxml"));
                        AnchorPane page = (AnchorPane) loader.load();
                        TrickController trickController = loader.<TrickController>getController();
                        // CadStockController cadStockController = loader.<CadStockController>getController();

                        usuarioServicos = new UsuarioServicos();
                        Usuario selectedUsuario = new Usuario();
                        selectedUsuario = usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));

                        trickController.receberdadosUsuario(selectedUsuario);
                        // cadStockController.setUsuario(selectedUsuario);
                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();

                        // Criando um Estágio de Diálogo (Stage Dialog)
                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("REGISTO DE VENDAS");
                        Scene scene = new Scene(page);
                        dialogStage.setScene(scene);
                        dialogStage.setResizable(false);
                        //dialogStage.setFullScreen(true);
                        dialogStage.setMaximized(true);
                        // Mostra o Dialog e espera até que o usuário o feche
                        dialogStage.showAndWait();
                    }
                }

            } else {

                this.labelLoginPageErro.setText("Nome e senha do usuário não correspondem!");
                // this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/images/lock.png")));
            }

        } catch (Exception ex) {
            System.out.println("" + ex + ex.getLocalizedMessage());
            System.out.println("" + ex.toString());
        }
         }
    }

    public void loginComEnterUser() {
        String username = this.tfUsuario.getText();
        TrickController Trick = new TrickController();

        String password1 = this.passwordFieldSenha.getText();
        String password = LoginPage1Controller.hashPassword(password1);
        UsuarioServicos us = new UsuarioServicos();
        this.labelLoginPageErro.setText("");
        
         if(passwordFieldSenha.getText().isEmpty() &&tfUsuario.getText().isEmpty()){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("PREENCHA OS CAMPOS");
        alert.showAndWait();
        }else{


        try {
            if (us.AutenticarUsuario(username, password) == true) {

                UsuarioDAO userDao = new UsuarioDAO();
                this.rs = userDao.getCategoriaUsuario(username, password);
                this.rs.next();
                String categoriaUsuario = this.rs.getString("Categoria");
                String codUsuario = this.rs.getString("Cod_Funcionario");
                String status = this.rs.getString("Status");

                if (categoriaUsuario.contentEquals("Funcionario")) {
                    Previlegio prev = us.VerificarPrevilegios();
                    us.Login(username, password);

                    if (status.equals("Inativo")) {
                        Alert alerta = new Alert(AlertType.INFORMATION);
                        alerta.setTitle("Informação");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Não é possível acesar pois o Utilizador encontra-se Inativo!");
                        alerta.showAndWait();
                    } else {
                        //Trick.UserInfo(username, codUsuario);
                        FXMLLoader loader = new FXMLLoader();

                        loader.setLocation(TrickController.class.getResource("/Presentation/Trick1.fxml"));

                        AnchorPane page = (AnchorPane) loader.load();
                        TrickController trickController = loader.<TrickController>getController();

                        usuarioServicos = new UsuarioServicos();
                        Usuario selectedUsuario = new Usuario();
                        selectedUsuario = usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
                        trickController.receberdadosUsuario(selectedUsuario);

                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();

                        // Criando um Estágio de Diálogo (Stage Dialog)
                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("REGISTO DE VENDAS");
                        Scene scene = new Scene(page);
                        dialogStage.setScene(scene);
                        dialogStage.setResizable(false);

                        //dialogStage.setFullScreen(true);
                        dialogStage.setMaximized(true);
                        // Mostra o Dialog e espera até que o usuário o feche
                        dialogStage.showAndWait();
                    }
                } else if (categoriaUsuario.contentEquals("Gerente")) {

                    Previlegio prev = us.VerificarPrevilegios();
                    us.Login(username, password);

                    // Trick.UserInfo(username, codUsuario);
                    FXMLLoader loader = new FXMLLoader();

                    loader.setLocation(TrickController.class.getResource("/Presentation/Trick1.fxml"));
                    AnchorPane page = (AnchorPane) loader.load();
                    TrickController trickController = loader.<TrickController>getController();
                    // CadStockController cadStockController = loader.<CadStockController>getController();

                    usuarioServicos = new UsuarioServicos();
                    Usuario selectedUsuario = new Usuario();
                    selectedUsuario = usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));

                    trickController.receberdadosUsuario(selectedUsuario);
                    // cadStockController.setUsuario(selectedUsuario);
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.close();

                    // Criando um Estágio de Diálogo (Stage Dialog)
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("REGISTO DE VENDAS");
                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);
                    dialogStage.setResizable(false);
                    //dialogStage.setFullScreen(true);
                    dialogStage.setMaximized(true);
                    // Mostra o Dialog e espera até que o usuário o feche
                    dialogStage.showAndWait();

                }

            } else {

                this.labelLoginPageErro.setText("Nome e senha do usuário não correspondem!");
                // this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/images/lock.png")));
            }

        } catch (Exception ex) {
            System.out.println("" + ex + ex.getLocalizedMessage());
            System.out.println("" + ex.toString());
        }

    }
    }

}
