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
     UsuarioServicos usuarioServicos ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
            passwordFieldSenha.setOnKeyPressed(event ->{
                  if(event.getCode() == KeyCode.ENTER){
                      loginComEnter();
                  }
                        
              });
            
               tfUsuario.setOnKeyPressed(event ->{
                  if(event.getCode() == KeyCode.ENTER){
                      loginComEnterUser();
                  }
                        
              });
    }    
    
    
    
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage =(Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
    
 public void mudacorloginEntered(){
         btnLogin.setStyle("-fx-background-color: #FFF");
         btnLogin.setStyle("-fx-background-radius: 13");
    }
    
    public void mouseexitbuttonlogin(){
       btnLogin.setStyle("-fx-background-color: #FFF");
       btnLogin.setStyle("-fx-background-radius: 13");
   }
    
     public void mudacorcancel(){
          btnCancel.setStyle("-fx-background-color: #FFF");
       btnCancel.setStyle("-fx-background-radius: 13");
    }
   
     
  
      
    
     
    public void loginButtonOnAction (ActionEvent event){
         // Usuario user = new Usuario();
         
       

          
           String username = this.tfUsuario.getText();
/*     */     TrickController Trick = new TrickController();
               
/* 153 */     String password = this.passwordFieldSenha.getText();
/* 154 */     UsuarioServicos us = new UsuarioServicos();
/* 155 */     this.labelLoginPageErro.setText("");
/* 156 */     //this.imageViewLock.setIcon(new ImageIcon(getClass().getResource("/images/lock_off.png")));
/*     */     /*     */     
          
/*     */     try {
/* 159 */       if (us.AutenticarUsuario(username, password) == true)
/*     */       {
/*     */         
 
/* 162 */         UsuarioDAO userDao = new UsuarioDAO();
/* 163 */         this.rs = userDao.getCategoriaUsuario(username, password);
/* 164 */         this.rs.next();
/* 165 */         String categoriaUsuario = this.rs.getString("Categoria");
/* 166 */         String codUsuario = this.rs.getString("Cod_Funcionario");
                  //user.setCod_Funcionario(Integer.parseInt(codUsuario));
                 // user.setCod_Funcionario(Integer.valueOf(codUsuario));
/*     */         //user.setCod_Funcionario(Integer.parseInt(this.rs.getString("Cod_Funcionario")));
/* 168 */         if (categoriaUsuario.contentEquals("Funcionario"))
/*     */         {
/* 170 */           Previlegio prev = us.VerificarPrevilegios();
/* 171 */           us.Login(username, password);
                    
                    //Trick.UserInfo(username, codUsuario);
/* 172 */           FXMLLoader loader = new FXMLLoader();
                    
         //Parent root = (Parent) loader.load();           
         //CadStockController cadStockController= loader.<CadStockController> getController();
        // servicoProdutos = new ProdutosServicos();
        // Produto selectedProduto = new Produto();
         //selectedProduto= servicoProdutos.getDetalhesProduto(Integer.parseInt(codProduto));
       // cadStockController.ReceberDadosProduto(selectedProduto);

        loader.setLocation(TrickController.class.getResource("/Presentation/Trick1.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        TrickController trickController =loader.<TrickController>getController();
        
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        trickController.receberdadosUsuario(selectedUsuario);
       // cadStockController.setUsuario(selectedUsuario);
        //trickController.UserInfo(username,codUsuario);
       
         Stage stage =(Stage) btnLogin.getScene().getWindow();
        stage.close();
       
        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Vendas");
        dialogStage.setAlwaysOnTop(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        
        //dialogStage.setFullScreen(true);
        dialogStage.setMaximized(true);
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
          
/*     */         }
/* 178 */         else if (categoriaUsuario.contentEquals("Gerente"))
/*     */         {
/* 180 */           

                         
/* 181 */            Previlegio prev = us.VerificarPrevilegios();
                    us.Login(username, password);
                    
                   // Trick.UserInfo(username, codUsuario);
/* 172 */           FXMLLoader loader = new FXMLLoader();
                  
         loader.setLocation(TrickController.class.getResource("/Presentation/Trick1.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        TrickController trickController =loader.<TrickController>getController();
       // CadStockController cadStockController = loader.<CadStockController>getController();
        
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        
        trickController.receberdadosUsuario(selectedUsuario);
       // cadStockController.setUsuario(selectedUsuario);
        Stage stage =(Stage) btnLogin.getScene().getWindow();
        stage.close();
        
        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Vendas");
        dialogStage.setAlwaysOnTop(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        //dialogStage.setFullScreen(true);
           dialogStage.setMaximized(true);
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
/* 196 */         this.labelLoginPageErro.setText("Nome e senha do usuário não correspondem!");
/* 197 */        // this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/images/lock.png")));
/*     */       }
/*     */     
/*     */     }
/* 201 */     catch (Exception ex) {
/* 202 */       System.out.println("" + ex + ex.getLocalizedMessage());
                System.out.println("" + ex.toString());
/*     */     } 
/*     */   }
    
    
       public void loginComEnter(){
         
         
            
           String username = this.tfUsuario.getText();
/*     */     TrickController Trick = new TrickController();
               
/* 153 */     String password = this.passwordFieldSenha.getText();
/* 154 */     UsuarioServicos us = new UsuarioServicos();
/* 155 */     this.labelLoginPageErro.setText("");
/* 156 */     //this.imageViewLock.setIcon(new ImageIcon(getClass().getResource("/images/lock_off.png")));
/*     */     /*     */     
          
/*     */     try {
/* 159 */       if (us.AutenticarUsuario(username, password) == true)
/*     */       {
/*     */         
 
/* 162 */         UsuarioDAO userDao = new UsuarioDAO();
/* 163 */         this.rs = userDao.getCategoriaUsuario(username, password);
/* 164 */         this.rs.next();
/* 165 */         String categoriaUsuario = this.rs.getString("Categoria");
/* 166 */         String codUsuario = this.rs.getString("Cod_Funcionario");
                  //user.setCod_Funcionario(Integer.parseInt(codUsuario));
                 // user.setCod_Funcionario(Integer.valueOf(codUsuario));
/*     */         //user.setCod_Funcionario(Integer.parseInt(this.rs.getString("Cod_Funcionario")));
/* 168 */         if (categoriaUsuario.contentEquals("Funcionario"))
/*     */         {
/* 170 */           Previlegio prev = us.VerificarPrevilegios();
/* 171 */           us.Login(username, password);
                    
                    //Trick.UserInfo(username, codUsuario);
/* 172 */           FXMLLoader loader = new FXMLLoader();
                    
         //Parent root = (Parent) loader.load();           
         //CadStockController cadStockController= loader.<CadStockController> getController();
        // servicoProdutos = new ProdutosServicos();
        // Produto selectedProduto = new Produto();
         //selectedProduto= servicoProdutos.getDetalhesProduto(Integer.parseInt(codProduto));
       // cadStockController.ReceberDadosProduto(selectedProduto);

        loader.setLocation(TrickController.class.getResource("/Presentation/Trick1.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        TrickController trickController =loader.<TrickController>getController();
        
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        trickController.receberdadosUsuario(selectedUsuario);
       // cadStockController.setUsuario(selectedUsuario);
        //trickController.UserInfo(username,codUsuario);
       
         Stage stage =(Stage) btnLogin.getScene().getWindow();
        stage.close();
       
        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Vendas");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        
        //dialogStage.setFullScreen(true);
        dialogStage.setMaximized(true);
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
          
/*     */         }
/* 178 */         else if (categoriaUsuario.contentEquals("Gerente"))
/*     */         {
/* 180 */           

                         
/* 181 */            Previlegio prev = us.VerificarPrevilegios();
                    us.Login(username, password);
                    
                   // Trick.UserInfo(username, codUsuario);
/* 172 */           FXMLLoader loader = new FXMLLoader();
                  
         loader.setLocation(TrickController.class.getResource("/Presentation/Trick1.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        TrickController trickController =loader.<TrickController>getController();
       // CadStockController cadStockController = loader.<CadStockController>getController();
        
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        
        trickController.receberdadosUsuario(selectedUsuario);
       // cadStockController.setUsuario(selectedUsuario);
        Stage stage =(Stage) btnLogin.getScene().getWindow();
        stage.close();
        
        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Vendas");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        //dialogStage.setFullScreen(true);
           dialogStage.setMaximized(true);
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
/* 196 */         this.labelLoginPageErro.setText("Nome e senha do usuário não correspondem!");
/* 197 */        // this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/images/lock.png")));
/*     */       }
/*     */     
/*     */     }
/* 201 */     catch (Exception ex) {
/* 202 */       System.out.println("" + ex + ex.getLocalizedMessage());
                System.out.println("" + ex.toString());
/*     */     }
     }
    
       
       public void loginComEnterUser(){
           String username = this.tfUsuario.getText();
/*     */     TrickController Trick = new TrickController();
               
/* 153 */     String password = this.passwordFieldSenha.getText();
/* 154 */     UsuarioServicos us = new UsuarioServicos();
/* 155 */     this.labelLoginPageErro.setText("");
/* 156 */     //this.imageViewLock.setIcon(new ImageIcon(getClass().getResource("/images/lock_off.png")));
/*     */     /*     */     
          
/*     */     try {
/* 159 */       if (us.AutenticarUsuario(username, password) == true)
/*     */       {
/*     */         
 
/* 162 */         UsuarioDAO userDao = new UsuarioDAO();
/* 163 */         this.rs = userDao.getCategoriaUsuario(username, password);
/* 164 */         this.rs.next();
/* 165 */         String categoriaUsuario = this.rs.getString("Categoria");
/* 166 */         String codUsuario = this.rs.getString("Cod_Funcionario");
                  //user.setCod_Funcionario(Integer.parseInt(codUsuario));
                 // user.setCod_Funcionario(Integer.valueOf(codUsuario));
/*     */         //user.setCod_Funcionario(Integer.parseInt(this.rs.getString("Cod_Funcionario")));
/* 168 */         if (categoriaUsuario.contentEquals("Funcionario"))
/*     */         {
/* 170 */           Previlegio prev = us.VerificarPrevilegios();
/* 171 */           us.Login(username, password);
                    
                    //Trick.UserInfo(username, codUsuario);
/* 172 */           FXMLLoader loader = new FXMLLoader();
                    
         //Parent root = (Parent) loader.load();           
         //CadStockController cadStockController= loader.<CadStockController> getController();
        // servicoProdutos = new ProdutosServicos();
        // Produto selectedProduto = new Produto();
         //selectedProduto= servicoProdutos.getDetalhesProduto(Integer.parseInt(codProduto));
       // cadStockController.ReceberDadosProduto(selectedProduto);

        loader.setLocation(TrickController.class.getResource("/Presentation/Trick1.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        TrickController trickController =loader.<TrickController>getController();
        
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        trickController.receberdadosUsuario(selectedUsuario);
       // cadStockController.setUsuario(selectedUsuario);
        //trickController.UserInfo(username,codUsuario);
       
         Stage stage =(Stage) btnLogin.getScene().getWindow();
        stage.close();
       
        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Vendas");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        
        //dialogStage.setFullScreen(true);
        dialogStage.setMaximized(true);
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
          
/*     */         }
/* 178 */         else if (categoriaUsuario.contentEquals("Gerente"))
/*     */         {
/* 180 */           

                         
/* 181 */            Previlegio prev = us.VerificarPrevilegios();
                    us.Login(username, password);
                    
                   // Trick.UserInfo(username, codUsuario);
/* 172 */           FXMLLoader loader = new FXMLLoader();
                  
         loader.setLocation(TrickController.class.getResource("/Presentation/Trick1.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        TrickController trickController =loader.<TrickController>getController();
       // CadStockController cadStockController = loader.<CadStockController>getController();
        
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        
        trickController.receberdadosUsuario(selectedUsuario);
       // cadStockController.setUsuario(selectedUsuario);
        Stage stage =(Stage) btnLogin.getScene().getWindow();
        stage.close();
        
        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Vendas");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        //dialogStage.setFullScreen(true);
           dialogStage.setMaximized(true);
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
/* 196 */         this.labelLoginPageErro.setText("Nome e senha do usuário não correspondem!");
/* 197 */        // this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/images/lock.png")));
/*     */       }
/*     */     
/*     */     }
/* 201 */     catch (Exception ex) {
/* 202 */       System.out.println("" + ex + ex.getLocalizedMessage());
                System.out.println("" + ex.toString());
/*     */     }
           
       }
  
/*     */ }