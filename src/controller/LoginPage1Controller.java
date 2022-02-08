/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Previlegio;
import DataAcessLayer.UsuarioDAO;
import Service.UsuarioServicos;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label labelUsuario;

    @FXML
    private Label labelSenha;

    @FXML
    private TextField tfUsuario;

    @FXML
    private PasswordField passwordFieldSenha;

    @FXML
    private ImageView imageViewLock;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnCancel;
    
    @FXML
    private Label labelLoginPageErro;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage =(Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
    
    
    public void loginButtonOnAction (ActionEvent event){
        
           String username = this.tfUsuario.getText();
/*     */     
/* 153 */     String password = this.passwordFieldSenha.getText();
/* 154 */     UsuarioServicos us = new UsuarioServicos();
/* 155 */     this.labelLoginPageErro.setText("");
/* 156 */     //this.imageViewLock.setIcon(new ImageIcon(getClass().getResource("/images/lock_off.png")));
/*     */     
/*     */     try {
/* 159 */       if (us.AutenticarUsuario(username, password) == true)
/*     */       {
/*     */         
/* 162 */         UsuarioDAO userDao = new UsuarioDAO();
/* 163 */         this.rs = userDao.getCategoriaUsuario(username, password);
/* 164 */         this.rs.next();
/* 165 */         String categoriaUsuario = this.rs.getString("Categoria");
/* 166 */         String codUsuario = this.rs.getString("Cod_Funcionario");
/*     */         
/* 168 */         if (categoriaUsuario.contentEquals("Funcionario"))
/*     */         {
/* 170 */           Previlegio prev = us.VerificarPrevilegios();
/* 171 */           us.Login(username, password);
/* 172 */           FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/Trick.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Vendas");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
          
/*     */         }
/* 178 */         else if (categoriaUsuario.contentEquals("Gerente"))
/*     */         {
/* 180 */           
/* 181 */            Previlegio prev = us.VerificarPrevilegios();
                    us.Login(username, password);
/* 172 */           FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/Trick.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Vendas");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
/*     */         // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
/*     */         }
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/*     */         
/* 196 */         this.labelLoginPageErro.setText("Nome e senha do usuario nao correspondem");
/* 197 */        // this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/images/lock.png")));
/*     */       }
/*     */     
/*     */     }
/* 201 */     catch (Exception ex) {
/* 202 */       System.out.println("" + ex + ex.getLocalizedMessage());
                System.out.println("" + ex.toString());
/*     */     } 
/*     */   }
    
    
  
/*     */ }