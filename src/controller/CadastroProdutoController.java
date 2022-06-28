/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Categoria;
import BussinessLogic.Produto;
import DataAcessLayer.LeituraImagens;
import DataAcessLayer.ProdutoDAO;
import Service.ProdutosServicos;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javax.imageio.ImageIO;
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
    private Button buttonEditar;

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
    
    @FXML
    private TextField textfieldImageNome;
    
    @FXML
    private ImageView imagemCarrinhoProduto;

    
    private String caminhoFoto;
    private Produto produto;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textfieldImageNome.setVisible(false);
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
     
      
        public void OcultarBotaoEditar(){
         buttonEditar.setVisible(false);
    }
    
         public void OcultarBotaoSalvar(){
         buttonSalvar.setVisible(false);
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

        BufferedImage imagem;
        private void selecionarFoto() {
      
      FileChooser f = new FileChooser();
      File file = f.showOpenDialog(new Stage());
        f.getExtensionFilters().add(new ExtensionFilter("Pictures", "*.jpg", "*.png", "*.jpeg"));
 
            try {
                      
                        if (file !=null){
        imageViewFoto.setImage(new Image("file:///"+file.getAbsolutePath()));
        imagemCarrinhoProduto.setImage(new Image("file:///"+file.getAbsolutePath()));
        caminhoFoto =file.getAbsolutePath();
                textfieldImageNome.setText(file.getName());
                 imagem= ImageIO.read(file);
                 textfieldImageNome.setVisible(false);
            }
            }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
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
               else if (this.comboBoxCategoria.getSelectionModel().isEmpty()) {
       
             JOptionPane.showMessageDialog(null, "SELECIONE A CATEGORIA DO PRODUTO");
               fds = true;
      
             }
            else if (this.comboBoxUnidade.getSelectionModel().isEmpty()) {
        
            JOptionPane.showMessageDialog(null, "SELECIONE A UNIDADE DO PRODUTO");
            fds = true;
            } 
                
           
             return fds;
             }
              
              
            private void limparcampos(){
              //  Image image1= new Image("file:"+"@..\\icons\\produts.jpg");
                textfieldNomeProduto.setText("");
                textFieldPreco.setText("");
                textFieldDescricao.setText("");
                comboBoxCategoria.setValue(null);
                comboBoxUnidade.setValue(null);
               // imageViewFoto.setImage(image1);
            }
              
              
              public void cadastroProduto() {
               
                  
                   if (caminhoFoto==null){
                      try {  
                 
              Double preco_unitario = Double.valueOf(0.0D);
              String nome = this.textfieldNomeProduto.getText();
              preco_unitario = Double.valueOf(Double.parseDouble(this.textFieldPreco.getText()));
              ProdutosServicos ps = new ProdutosServicos();
              Integer unidade = Integer.valueOf(ps.CapturaIdUnidade(this.comboBoxUnidade.getSelectionModel().getSelectedItem().toString()));
              Integer categoria = Integer.valueOf(ps.CapturaIdCategoria(this.comboBoxCategoria.getSelectionModel().getSelectedItem().toString()));
              String descricao = this.textFieldDescricao.getText();
              // Blob foto = new Image(imageViewFoto.getBlob("foto").getBinaryStream(), false);
              // Read the file    
              
              
              Produto product = new Produto();
              product.setNome(nome);
              product.setPreco_unitario(preco_unitario);
              product.setUnidade(unidade.toString());
              product.setCategoria(categoria.toString());
              product.setDescricao(descricao);
             
                
              ProdutosServicos  produtoServico= new ProdutosServicos();
              boolean status =  produtoServico.RegistarProdutoSemFoto(product);
              
              limparcampos();
              
                 if(status){
                   JOptionPane.showMessageDialog(null, " Produto : " + product.getNome() + " Registado com sucesso");
                  
                   
                 }else {
                 JOptionPane.showMessageDialog(null, " Erro ao registar o Produto. Tente novamente");
                 }       
                  } catch (NumberFormatException e) {
                      //TODO  Tratar erro de introducao de preco 
                       
                  } catch (Exception e) {
                      
                       JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " +  e.getCause().toString());
                       JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " +  e.getMessage().toString());
                  } 
                 
                   
              
                  } else{
                  try {  
                 
              Double preco_unitario = Double.valueOf(0.0D);
              String nome = this.textfieldNomeProduto.getText();
              preco_unitario = Double.valueOf(Double.parseDouble(this.textFieldPreco.getText()));
              ProdutosServicos ps = new ProdutosServicos();
              Integer unidade = Integer.valueOf(ps.CapturaIdUnidade(this.comboBoxUnidade.getSelectionModel().getSelectedItem().toString()));
              Integer categoria = Integer.valueOf(ps.CapturaIdCategoria(this.comboBoxCategoria.getSelectionModel().getSelectedItem().toString()));
              String descricao = this.textFieldDescricao.getText();
              // Blob foto = new Image(imageViewFoto.getBlob("foto").getBinaryStream(), false);
              // Read the file    
              
              LeituraImagens location = new LeituraImagens();
              String loc=location.getDirectorio_Imagens();
              
              String Location =loc+textfieldImageNome.getText();
              String format="PNG";
              ImageIO.write(imagem, format, new File(Location));
              
              Produto product = new Produto();
              product.setNome(nome);
              product.setPreco_unitario(preco_unitario);
              product.setUnidade(unidade.toString());
              product.setCategoria(categoria.toString());
              product.setDescricao(descricao);
              product.setFoto(Location);
              
              ProdutosServicos  produtoServico= new ProdutosServicos();
              boolean status =  produtoServico.RegistarProduto(product);
              
              limparcampos();
              
                 if(status){
                   JOptionPane.showMessageDialog(null, " Produto : " + product.getNome() + " Registado com sucesso");
                  
                   
                 }else {
                 JOptionPane.showMessageDialog(null, " Erro ao registar o Produto. Tente novamente");
                 }       
                  } catch (NumberFormatException e) {
                      //TODO  Tratar erro de introducao de preco 
                       
                  } catch (Exception e) {
                      
                       JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " +  e.getCause().toString());
                       JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " +  e.getMessage().toString());
                  } 
                 
                   }
             }
              
              
              public void handleButtonCadastrarProdutos (ActionEvent E) {
              if (!verificadados()) {
                cadastroProduto();
        

             }
             }
              
              
        public Produto getProduto() {
            
        return produto;
        
        }   
           
        public void ReceberDadosProduto(Produto produto)   {
        
        this.produto = produto;
         textfieldImageNome.setVisible(false);
        
        if(produto.getFoto()==null){
            
        this.textFieldDescricao.setText(produto.getDescricao());
        this.textFieldPreco.setText(produto.getPreco_unitario().toString());
        this.textfieldNomeProduto.setText(produto.getNome());
        this.comboBoxCategoria.setValue(produto.getCategoria());
        this.comboBoxUnidade.setValue(produto.getUnidade());
        
        }else{
            
        this.textFieldDescricao.setText(produto.getDescricao());
        this.textFieldPreco.setText(produto.getPreco_unitario().toString());
        this.textfieldNomeProduto.setText(produto.getNome());
        this.comboBoxCategoria.setValue(produto.getCategoria());
        this.comboBoxUnidade.setValue(produto.getUnidade());
        this.textfieldImageNome.setText(produto.getFoto());
        Image image= new Image("file:"+produto.getFoto());
        imageViewFoto.setImage(image);
        imagemCarrinhoProduto.setImage(image);
        }

                }
   
              public void EditarProduto()  {
               
                           
                   if (textfieldImageNome.getText().equals("")){
                      try {  
              
              Double preco_unitario = Double.valueOf(0.0D);
              String nome = this.textfieldNomeProduto.getText();
              preco_unitario = Double.valueOf(Double.parseDouble(this.textFieldPreco.getText()));
              ProdutosServicos ps = new ProdutosServicos();
              Integer unidade = Integer.valueOf(ps.CapturaIdUnidade(this.comboBoxUnidade.getSelectionModel().getSelectedItem().toString()));
              Integer categoria = Integer.valueOf(ps.CapturaIdCategoria(this.comboBoxCategoria.getSelectionModel().getSelectedItem().toString()));
              String descricao = this.textFieldDescricao.getText();
   
              
              produto.setNome(nome);
              produto.setPreco_unitario(preco_unitario);
              produto.setUnidade(unidade.toString());
              produto.setCategoria(categoria.toString());
              produto.setDescricao(descricao);
              produto.setFoto(produto.getFoto());
                
              ProdutosServicos  produtoServico= new ProdutosServicos();
              produtoServico.EditarProdutoComFoto(produto);
                limparcampos();
                 
                     
                   JOptionPane.showMessageDialog(null, " Produto Editado com sucesso");
                  
                  
                   //TODO FECHAR A TELA 
                   Stage stage =(Stage) buttonEditar.getScene().getWindow();
                   stage.close();
                       
                  } catch (NumberFormatException e) {
                      //TODO  Tratar erro de introducao de preco 
                       
                  } catch (Exception e) {
                      
                     JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " +  e.getCause().toString());
                      JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " +  e.getMessage().toString());
                  }  
                      
                      
                   }else if(caminhoFoto==null){
                       
                       
                       
                  try {  
                      
              
              Double preco_unitario = Double.valueOf(0.0D);
              String nome = this.textfieldNomeProduto.getText();
              preco_unitario = Double.valueOf(Double.parseDouble(this.textFieldPreco.getText()));
              ProdutosServicos ps = new ProdutosServicos();
              Integer unidade = Integer.valueOf(ps.CapturaIdUnidade(this.comboBoxUnidade.getSelectionModel().getSelectedItem().toString()));
              Integer categoria = Integer.valueOf(ps.CapturaIdCategoria(this.comboBoxCategoria.getSelectionModel().getSelectedItem().toString()));
              String descricao = this.textFieldDescricao.getText();
            
            
              produto.setNome(nome);
              produto.setPreco_unitario(preco_unitario);
              produto.setUnidade(unidade.toString());
              produto.setCategoria(categoria.toString());
              produto.setDescricao(descricao);
              produto.setFoto(produto.getFoto());
              
                  
              ProdutosServicos  produtoServico= new ProdutosServicos();
              produtoServico.EditarProdutoComFoto(produto);
              JOptionPane.showMessageDialog(null, " Produto : " + produto.getNome() + " Editado com sucesso");
              Stage stage =(Stage) buttonEditar.getScene().getWindow();
              stage.close();
              
               } catch (NumberFormatException e) {
                      //TODO  Tratar erro de introducao de preco 
                       
                  } catch (Exception e) {
                      
                     JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " +  e.getCause().toString());
                      JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " +  e.getMessage().toString());
                  }  
              }else if (caminhoFoto!=null){
                  try {  
                      
              
              Double preco_unitario = Double.valueOf(0.0D);
              String nome = this.textfieldNomeProduto.getText();
              preco_unitario = Double.valueOf(Double.parseDouble(this.textFieldPreco.getText()));
              ProdutosServicos ps = new ProdutosServicos();
              Integer unidade = Integer.valueOf(ps.CapturaIdUnidade(this.comboBoxUnidade.getSelectionModel().getSelectedItem().toString()));
              Integer categoria = Integer.valueOf(ps.CapturaIdCategoria(this.comboBoxCategoria.getSelectionModel().getSelectedItem().toString()));
              String descricao = this.textFieldDescricao.getText();
              
              LeituraImagens location = new LeituraImagens();
              String loc=location.getDirectorio_Imagens();
              
              String Location =loc+textfieldImageNome.getText();
              //String Location ="C:\\Users\\Neusia\\Documents\\NetBeansProjects\\sedv\\src\\ImagensDeProdutos\\"+textfieldImageNome.getText();
              String format="PNG";
              ImageIO.write(imagem, format, new File(Location));
               
            
              
              produto.setNome(nome);
              produto.setPreco_unitario(preco_unitario);
              produto.setUnidade(unidade.toString());
              produto.setCategoria(categoria.toString());
              produto.setDescricao(descricao);
              produto.setFoto(Location);
            
        
                
              ProdutosServicos  produtoServico= new ProdutosServicos();
              produtoServico.EditarProdutoComFoto(produto);
              
              limparcampos();
              
                
                   JOptionPane.showMessageDialog(null, " Produto : " + produto.getNome() + " Editado com sucesso");
                   Stage stage =(Stage) buttonEditar.getScene().getWindow();
                   stage.close();
                      
                  } catch (NumberFormatException e) {
                      //TODO  Tratar erro de introducao de preco 
                       
                  } catch (Exception e) {
                      
             JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " +  e.getCause().toString());
             JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " +  e.getMessage().toString());
                  } 
                 
                   }
              }
              
                
              
 }
