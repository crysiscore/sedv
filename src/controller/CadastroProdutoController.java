/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Categoria;
import DataAcessLayer.ProdutoDAO;
import Service.ProdutosServicos;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Neusia
 */
public class CadastroProdutoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button buttonSalvar;

    @FXML
    private Button buttonVerProdutos;

    @FXML
    private ComboBox<String> comboBoxCategoria;

    @FXML
    private ComboBox<String> comboBoxUnidade;

    @FXML
    private ImageView imageViewFoto;

    @FXML
    private TextField textFieldDescricao;

    @FXML
    private TextField textFieldPreco;

    @FXML
    private TextField textfieldNomeProduto;

    @FXML
    private ImageView imageViewCloseWindow;
    
    private String caminhoFoto;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         populacomboUnidade();
         populacomboCategoria();
         
         imageViewFoto.setOnMouseClicked((MouseEvent e)->{
        selecionarFoto();
    });
         
    
    }    
    
    
    
    
   
      public void CancelButtonOnAction(){
        Stage stage =(Stage) imageViewCloseWindow.getScene().getWindow();
        stage.close();
    }
  
    
        public void populacomboUnidade() {
            
            ObservableList<String> listaUnidade = FXCollections.observableArrayList();
             try {
                ProdutoDAO pd = new ProdutoDAO();
                ResultSet rs = pd.popularunidade();
                while (rs.next())
                {
                listaUnidade.add(rs.getString("Descricao_Unidade"));
    
                }
     
                }
               catch (Exception e) {
               System.out.println("" + e);

                 }

                 comboBoxUnidade.setItems(null);
                 comboBoxUnidade.setItems(listaUnidade);
                    }
        
        
           public void populacomboCategoria() {
            
            ObservableList<String> listaCategoria = FXCollections.observableArrayList();
             try {
               ProdutoDAO pd = new ProdutoDAO();
             ResultSet rs = pd.populacombocategoria();
                while (rs.next())
                    {
                listaCategoria.add(rs.getString("Nome"));

                  }
                 }
                catch (Exception e) {
                 System.out.println("" + e);

                 }

                 comboBoxCategoria.setItems(null);
                 comboBoxCategoria.setItems(listaCategoria);
                    }
        
           
                       
    public void handleButtonVerProdutosCadastrados(ActionEvent e){
         
          
        try {
      
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ListadeProdutosController.class.getResource("/Presentation/ListadeProdutos.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        
         ListadeProdutosController listadeProdutosController= loader.<ListadeProdutosController>getController();
        
         
        //usuarioServicos = new UsuarioServicos();
        //Usuario selectedUsuario = new Usuario();
       // selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        //ListadeProdutosController.receberdadosUsuario(selectedUsuario);
        
        
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registo de Produtos");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setMaximized(false);
        dialogStage.setResizable(false);
        // Mostra o Dialog e espera atÃ© que o usuÃ¡rio o feche
        dialogStage.show();
           }
             catch (Exception ex) {
          System.out.println("" + ex + ex.getLocalizedMessage());
                System.out.println("" + ex.toString());
          } 
            }

    
    
        private void selecionarFoto() {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new ExtensionFilter("Pictures", "*.jpg", "*.png", "*.jpeg"));
        File file = f.showOpenDialog(new Stage());
        if (file !=null){
        imageViewFoto.setImage(new Image("file:///"+file.getAbsolutePath()));
        caminhoFoto =file.getAbsolutePath();
        }
    }
        
        
                
              public boolean verificadados() {
                boolean fds = false;
                
                if (this.textfieldNomeProduto.getText().equals("")) {
        
               JOptionPane.showMessageDialog(null, "INTRODUZA O NOME DO PRODUTO ");
               fds = true;
                 }
                else if (this.textFieldPreco.getText().equals("")) {
       
                JOptionPane.showMessageDialog(null, "INTRODUZA O PRECO UNITARIO ");
               fds = true;
     
              }
                
               else if (this.textFieldDescricao.getText().equals("")) {
       
                JOptionPane.showMessageDialog(null, "DESCREVA O PRODUTO ");
               fds = true;
     
              }
               else if (this.comboBoxCategoria.getSelectionModel().getSelectedItem().isEmpty()) {
       
             JOptionPane.showMessageDialog(null, "SELECIONE A CATEGORIA DO PRODUTO");
               fds = true;
      
             }
            else if (this.comboBoxUnidade.getSelectionModel().getSelectedItem().isEmpty()) {
        
            JOptionPane.showMessageDialog(null, "SELECIONE A UNIDADE DO PRODUTO");
            fds = true;
            } 
      
             return fds;
             }
           
              
              
              public void cadastroProduto() throws SQLException {
                  
              Double preco_unitario = Double.valueOf(0.0D);
              

              String nome = this.textfieldNomeProduto.getText();
              preco_unitario = Double.valueOf(Double.parseDouble(this.textFieldPreco.getText()));
     
              ProdutosServicos ps = new ProdutosServicos();
              Integer unidade = Integer.valueOf(ps.CapturaIdUnidade(this.comboBoxUnidade.getSelectionModel().getSelectedItem().toString()));
              Integer categoria = Integer.valueOf(ps.CapturaIdCategoria(this.comboBoxCategoria.getSelectionModel().getSelectedItem().toString()));
              String descricao = this.textFieldDescricao.getText();
              //Blob foto = new Image(imageViewFoto.getBlob("foto").getBinaryStream(), false);
                      
              ProdutoDAO dao = new ProdutoDAO();
              dao.cadastrarprodutos(nome, preco_unitario.doubleValue(), unidade.intValue(), categoria.intValue(), descricao);
      
              JOptionPane.showMessageDialog(null, "O Produto foi Cadastrado com Sucesso");
             }
              
              
              public void handleButtonCadastrarProdutos (ActionEvent E) {
                  if (!verificadados()) {
       
                try {
                cadastroProduto();
        
             
               } catch (Exception e) {
          
             JOptionPane.showMessageDialog(null, "O Produto Nao foi Cadastrado com Sucesso" + e);
              } 
             }
             }
              
           
                }
