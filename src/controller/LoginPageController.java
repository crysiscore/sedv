/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sedv.model.dao.UsuarioDAO;
import sedv.model.database.Database;
import sedv.model.database.DatabaseFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Neusia
 */
public class LoginPageController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
    ResultSet rs;
    @FXML
    private TextField textFieldUsuario;

    @FXML
    private PasswordField passwordFieldSenha;

    @FXML
    private Label labelUsuario;

    @FXML
    private Label labelSenha;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonCancel;

    @FXML
    private ImageView imageViewLock;
    
        @FXML
    private Label labelMessage;
        
     
    @FXML
    private AnchorPane anchorPane;   
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
  
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
      
 
}


