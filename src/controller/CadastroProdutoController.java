/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Categoria;
import BussinessLogic.Produto;
import BussinessLogic.Stock;
import DataAcessLayer.LeituraImagens;
import DataAcessLayer.ProdutoDAO;
import DataAcessLayer.StockDAO;
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
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
    
    @FXML
    private TextField textfieldCodigoProduto;
      
    @FXML
    private TextField textfieldQuantidadeDeStock;
    
    @FXML
    private TextField textfieldPrecoDeCompra;
    
    @FXML
    private TextField textfieldCodigoProdutoManual;
    
    @FXML
    private Label labelCodigoProduto;

    @FXML
    private Label labelQuantidadeStock;
    @FXML
    private Text labelRegistoProdutos;
    
      public void trocartextoregistrarprodutos(){
      labelRegistoProdutos.setText("DETALHES DO PRODUTO");
    }
     

     public void trocartextoeditarproduto(){
      labelRegistoProdutos.setText("EDITAR PRODUTO");
    }
    
    private String caminhoFoto;
    private Produto produto;
    private Stock stock;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //this.textfieldPrecoDeCompra.setText("0.0");
        textfieldImageNome.setVisible(false);
       
         populacomboUnidade();
         populacomboCategoria();
         
         imageViewFoto.setOnMouseClicked((MouseEvent e)->{
        selecionarFoto();
        
    });

         
        // Definindo o filtro com uma expressão regular para permitir apenas números inteiros ou doubles
        TextFormatter<Double> textFormatter = new TextFormatter<>(
                new DoubleStringConverter(),
                0.0,
                c -> {
                    if (c.getControlNewText().matches("\\d*|\\d+\\.\\d*")) {
                        return c;
                    } else {
                        return null;
                    }
                });

        textFieldPreco.setTextFormatter(textFormatter);

        // Adicionando um evento de escuta para verificar se o texto contém letras
        textFormatter.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null && !textFieldPreco.getText().isEmpty()) {
                // Exibe uma mensagem de erro caso letras sejam inseridas
                JOptionPane.showMessageDialog(null,"Digite apenas números inteiros ou decimais válidos!");
                textFieldPreco.setText(oldValue.toString()); // Restaura o valor anterior
            }
        });
        
        TextFormatter<Double> textFormatter1 = new TextFormatter<>(
                new DoubleStringConverter(),
                0.0,
                c -> {
                    if (c.getControlNewText().matches("\\d*|\\d+\\.\\d*")) {
                        return c;
                    } else {
                        return null;
                    }
                });

        textfieldPrecoDeCompra.setTextFormatter(textFormatter1);

        // Adicionando um evento de escuta para verificar se o texto contém letras
        textFormatter1.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null && !textfieldPrecoDeCompra.getText().isEmpty()) {
                // Exibe uma mensagem de erro caso letras sejam inseridas
                JOptionPane.showMessageDialog(null,"Digite apenas números inteiros ou decimais válidos!");
                textfieldPrecoDeCompra.setText(oldValue.toString()); // Restaura o valor anterior
            }
        });
        
         
    }    

   
      public void CancelButtonOnAction(){
        Stage stage =(Stage) imageViewCloseWindow.getScene().getWindow();
        stage.close();
    }
     
      
        public void OcultarBotaoEditar(){
         buttonEditar.setVisible(false);
          textfieldCodigoProduto.setVisible(false);
          textfieldQuantidadeDeStock.setVisible(false);
          labelCodigoProduto.setVisible(true);
          labelQuantidadeStock.setVisible(false);
    }
    
         public void OcultarBotaoSalvar(){
         buttonSalvar.setVisible(false);
        textfieldCodigoProduto.setVisible(false);
        textfieldQuantidadeDeStock.setVisible(false);
         labelCodigoProduto.setVisible(true);
          labelQuantidadeStock.setVisible(false);
         
    }   
         
         public void camposBloqueiados(){
         buttonSalvar.setVisible(false);
         buttonEditar.setVisible(false);
         buttonVerProdutos.setVisible(false);
         textfieldQuantidadeDeStock.setVisible(true);
         textFieldDescricao.setEditable(false);
         textFieldPreco.setEditable(false);
         textfieldNomeProduto.setEditable(false);
         textfieldNomeProduto.setEditable(false);
         textfieldCodigoProduto.setEditable(false);
         textfieldCodigoProduto.setVisible(false);
         textfieldQuantidadeDeStock.setEditable(false);
         comboBoxCategoria.setEditable(false);
         comboBoxUnidade.setEditable(false);
        
         }

    public void populacomboUnidade() {

        ObservableList<String> listaUnidade = FXCollections.observableArrayList();
        try {
            ProdutoDAO pd = new ProdutoDAO();
            ResultSet rs = pd.popularunidade();
            while (rs.next()) {
                listaUnidade.add(rs.getString("Descricao_Unidade"));

            }

        } catch (Exception e) {
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
            while (rs.next()) {
                listaCategoria.add(rs.getString("Nome"));

            }
        } catch (Exception e) {
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
        Stage stage =(Stage) buttonVerProdutos.getScene().getWindow();
        stage.close();
        
        } catch (Exception ex) {
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

            if (file != null) {
                imageViewFoto.setImage(new Image("file:///" + file.getAbsolutePath()));
                imagemCarrinhoProduto.setImage(new Image("file:///" + file.getAbsolutePath()));
                caminhoFoto = file.getAbsolutePath();
                textfieldImageNome.setText(file.getName());
                imagem = ImageIO.read(file);
                textfieldImageNome.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

     
              public boolean verificadados() {
              boolean fds = false;
                
                
              if (this.textfieldNomeProduto.getText().equals("")) {
        
               //JOptionPane.showMessageDialog(null, "INTRODUZA O NOME DO PRODUTO ");
               handleMenuAlert2();
               fds = true;
                 }
                else if (this.textFieldPreco.getText().equals("")) {
       
               JOptionPane.showMessageDialog(null, "INTRODUZA O PREÇO UNITÁRIO ");
             
               fds = true;
     
              }
                 else if (this.textfieldPrecoDeCompra.getText().equals("")) {
       
               JOptionPane.showMessageDialog(null, "INTRODUZA O PREÇO UNITÁRIO ");
             
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
                
                 File arquivo1 = null;
                  File arquivo2=null;
                  
              String osName = System.getProperty("os.name").toLowerCase();
              String ficheiroGravado;
              LeituraImagens location = new LeituraImagens();
              String loc=location.getDirectorio_Imagens();
              
          if (osName.contains("win")) {
             arquivo1 = new File(loc +  "icons\\produtoSemIm.png");
             arquivo2 = new File(loc + "icons\\produts.jpg");
        } else if (osName.contains("mac")) {
            arquivo1 = new File(loc +  "icons/produtoSemIm.png");
             arquivo2 = new File(loc + "icons/produts.jpg");
         } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
             arquivo1 = new File(loc +  "icons/produtoSemIm.png");
             arquivo2 = new File(loc + "icons/produts.jpg");  
        }
                                 
               
                Image image1= new Image("file:"+arquivo1.getAbsolutePath());
                Image image2= new Image("file:"+arquivo2.getAbsolutePath());
     
                textfieldNomeProduto.setText("");
                textFieldPreco.setText("");
                textFieldDescricao.setText("");
                textfieldCodigoProdutoManual.setText("");
                textfieldPrecoDeCompra.setText("0.0");
                comboBoxCategoria.setValue(null);
                comboBoxUnidade.setValue(null);
                imageViewFoto.setImage(image1);
                imagemCarrinhoProduto.setImage(image2);
            }
              
              
              public void cadastroProduto() {
               
                  
                   if (caminhoFoto==null){
                      try {  
                 
              Double preco_unitario = Double.valueOf(0.0D);
              Double Preco_De_Compra = Double.valueOf(0.0D);
              String CodigoManual=textfieldCodigoProdutoManual.getText();
              String nome = this.textfieldNomeProduto.getText();
              preco_unitario = Double.valueOf(Double.parseDouble(this.textFieldPreco.getText()));
              Preco_De_Compra= Double.valueOf(Double.parseDouble(this.textfieldPrecoDeCompra.getText()));
              ProdutosServicos ps = new ProdutosServicos();
              Integer unidade = Integer.valueOf(ps.CapturaIdUnidade(this.comboBoxUnidade.getSelectionModel().getSelectedItem().toString()));
              Integer categoria = Integer.valueOf(ps.CapturaIdCategoria(this.comboBoxCategoria.getSelectionModel().getSelectedItem().toString()));
              String descricao = this.textFieldDescricao.getText();
              // Blob foto = new Image(imageViewFoto.getBlob("foto").getBinaryStream(), false);
              // Read the file    
              
              
              Produto product = new Produto();
              product.setCodigo_manual(CodigoManual);
              product.setNome(nome);
              product.setPreco_unitario(preco_unitario);
              product.setPreco_De_Compra(Preco_De_Compra);
              product.setUnidade(unidade.toString());
              product.setCategoria(categoria.toString());
              product.setDescricao(descricao);
             
                
              ProdutosServicos  produtoServico= new ProdutosServicos();
              boolean status =  produtoServico.RegistarProdutoSemFoto(product);
              
              limparcampos();
    
              if (status) {
              JOptionPane.showMessageDialog(null, " Produto : " + product.getNome() + " Registado com sucesso");

              } else {
              JOptionPane.showMessageDialog(null, " Erro ao registar o Produto. Tente novamente");
              }
                      } catch (Exception ex) {
                               ex.printStackTrace();
                                     JOptionPane.showMessageDialog(null, " Erro ao registar o Produto. Tente novamente");
                                     
                           
                       }
  
                      } else {
         
              try{
              Double preco_unitario = Double.valueOf(0.0D);
              Double Preco_De_Compra = Double.valueOf(0.0D);
              String CodigoManual=textfieldCodigoProdutoManual.getText();
              String nome = this.textfieldNomeProduto.getText();
              preco_unitario = Double.valueOf(Double.parseDouble(this.textFieldPreco.getText()));
              Preco_De_Compra= Double.valueOf(Double.parseDouble(this.textfieldPrecoDeCompra.getText()));

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
              product.setCodigo_manual(CodigoManual);
              product.setNome(nome);
              product.setPreco_unitario(preco_unitario);
              product.setPreco_De_Compra(Preco_De_Compra);
              product.setUnidade(unidade.toString());
              product.setCategoria(categoria.toString());
              product.setDescricao(descricao);
              product.setFoto(Location);
              
              ProdutosServicos  produtoServico= new ProdutosServicos();
              boolean status =  produtoServico.RegistarProduto(product);
              
              limparcampos();

              if (status) {

              mostrarMensagem(" Produto : " + product.getNome() + " Registado com sucesso");

                    // JOptionPane.showMessageDialog(null, " Produto : " + product.getNome() + " Registado com sucesso");
                } else {

                    //JOptionPane.showMessageDialog(null, " Erro ao registar o Produto. Tente novamente");
                    mostrarMensagem("Erro ao registrar o produto. Tente novamente.");

                }
                              

            } catch (Exception e) {

               e.printStackTrace();
                JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " + e.getMessage().toString());
            } } 

           
    
              } 
    public void handleButtonCadastrarProdutos(ActionEvent E) {
        if (!verificadados()) {
            cadastroProduto();

        }
    }
              
              
       private static void mostrarMensagem(String mensagem) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(mensagem));
        JDialog dialog = new JDialog();
        dialog.setModal(true); // Torna o JDialog modal
        //dialog.setTitle(titulo);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
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
         this.textfieldPrecoDeCompra.setText(produto.getPreco_De_Compra().toString());
        this.textfieldNomeProduto.setText(produto.getNome());
        this.textfieldCodigoProdutoManual.setText(produto.getCodigo_manual());
        this.comboBoxCategoria.setValue(produto.getCategoria());
        this.comboBoxUnidade.setValue(produto.getUnidade());
        
        }else{
            
        this.textFieldDescricao.setText(produto.getDescricao());
        this.textFieldPreco.setText(produto.getPreco_unitario().toString());
        this.textfieldPrecoDeCompra.setText(produto.getPreco_De_Compra().toString());
        this.textfieldCodigoProdutoManual.setText(produto.getCodigo_manual());
        this.textfieldNomeProduto.setText(produto.getNome());
        this.comboBoxCategoria.setValue(produto.getCategoria());
        this.comboBoxUnidade.setValue(produto.getUnidade());
        this.textfieldImageNome.setText(produto.getFoto());
        Image image= new Image("file:"+produto.getFoto());
        imageViewFoto.setImage(image);
        imagemCarrinhoProduto.setImage(image);
        }
        }
        
        public void detalhesProdutos(Produto produto) throws SQLException{
            
        this.produto = produto;
        this.textFieldDescricao.setText(produto.getDescricao());
        this.textFieldPreco.setText(produto.getPreco_unitario().toString());
        this.textfieldPrecoDeCompra.setText(produto.getPreco_De_Compra().toString());
        this.textfieldNomeProduto.setText(produto.getNome());
        this.textfieldCodigoProdutoManual.setText(produto.getCodigo_manual());
        this.comboBoxCategoria.setValue(produto.getCategoria());
        this.comboBoxUnidade.setValue(produto.getUnidade());
        this.textfieldImageNome.setText(produto.getFoto());
        Image image= new Image("file:"+produto.getFoto());
        imageViewFoto.setImage(image);
        imagemCarrinhoProduto.setImage(image);
        this.textfieldCodigoProduto.setText(produto.getCod_produto().toString());
        this.textfieldQuantidadeDeStock.setText(produto.getStock().getUnidades_stock().toString());

    }

    public void EditarProduto() {

        if (textfieldImageNome.getText().equals("")) {
            try {
              
              Double preco_unitario = Double.valueOf(0.0D);
              Double Preco_De_Compra = Double.valueOf(0.0D);
              String CodigoManual=textfieldCodigoProdutoManual.getText();
              String nome = this.textfieldNomeProduto.getText();
              preco_unitario = Double.valueOf(Double.parseDouble(this.textFieldPreco.getText()));
              Preco_De_Compra= Double.valueOf(Double.parseDouble(this.textfieldPrecoDeCompra.getText()));
              ProdutosServicos ps = new ProdutosServicos();
              Integer unidade = Integer.valueOf(ps.CapturaIdUnidade(this.comboBoxUnidade.getSelectionModel().getSelectedItem().toString()));
              Integer categoria = Integer.valueOf(ps.CapturaIdCategoria(this.comboBoxCategoria.getSelectionModel().getSelectedItem().toString()));
              String descricao = this.textFieldDescricao.getText();
   
              produto.setCodigo_manual(CodigoManual);
              produto.setNome(nome);
              produto.setPreco_unitario(preco_unitario);
              produto.setPreco_De_Compra(Preco_De_Compra);
              produto.setUnidade(unidade.toString());
              produto.setCategoria(categoria.toString());
              produto.setDescricao(descricao);
              produto.setFoto(produto.getFoto());
                
              ProdutosServicos  produtoServico= new ProdutosServicos();
              produtoServico.EditarProdutoComFoto(produto);
                limparcampos();
                 
                          Alert alert = new Alert(Alert.AlertType.ERROR);
                          alert.setTitle("Erro");
                          alert.setHeaderText(null);
                          alert.setContentText("Produto Editado com sucesso!");
                          alert.showAndWait();
                   //JOptionPane.showMessageDialog(null, " Produto Editado com sucesso");
                  
                  
                   //TODO FECHAR A TELA 
                   Stage stage =(Stage) buttonEditar.getScene().getWindow();
                   stage.close();
                       
                  } catch (NumberFormatException e) {
                      //TODO  Tratar erro de introducao de preco 
                       
                  } catch (Exception e) {
                      e.printStackTrace();
                     JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " +  e.getCause().toString());
                      JOptionPane.showMessageDialog(null, " Erro ao gravar o produto: " +  e.getMessage().toString());
                  }  
                      
                      
                   }else if(caminhoFoto==null){
                       
                       
                       
                  try {  
                      
              
              Double preco_unitario = Double.valueOf(0.0D);
              Double Preco_De_Compra = Double.valueOf(0.0D);
              String nome = this.textfieldNomeProduto.getText();
              preco_unitario = Double.valueOf(Double.parseDouble(this.textFieldPreco.getText()));
              Preco_De_Compra= Double.valueOf(Double.parseDouble(this.textfieldPrecoDeCompra.getText()));
              ProdutosServicos ps = new ProdutosServicos();
              Integer unidade = Integer.valueOf(ps.CapturaIdUnidade(this.comboBoxUnidade.getSelectionModel().getSelectedItem().toString()));
              Integer categoria = Integer.valueOf(ps.CapturaIdCategoria(this.comboBoxCategoria.getSelectionModel().getSelectedItem().toString()));
              String descricao = this.textFieldDescricao.getText();
            
            
              produto.setNome(nome);
              produto.setPreco_unitario(preco_unitario);
              produto.setPreco_De_Compra(Preco_De_Compra);
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
              
    

   public void mudacorSalvararEntered(){
       
       buttonSalvar.setStyle("-fx-background-color: #FFFF");
       buttonSalvar.setStyle("-fx-background-radius: 12");
    } 
    
       public void mouseexitbuttonSalvar(){
       buttonSalvar.setStyle("-fx-background-color: #E9EEEE");
       buttonSalvar.setStyle("-fx-background-radius: 12");
   }
       
       public void mudacorEditarEntered(){
       buttonEditar.setStyle("-fx-background-color: #FFFF");
       buttonEditar.setStyle("-fx-background-radius: 12");
    } 
    
       public void mouseexitEditarRegistar(){
       buttonEditar.setStyle("-fx-background-color: #E9EEEE");
       buttonEditar.setStyle("-fx-background-radius: 12");
   }
       public void mudacorVerProdutosEntered(){
       buttonVerProdutos.setStyle("-fx-background-color: #FFFF");
       buttonVerProdutos.setStyle("-fx-background-radius: 12");
    } 
    
       public void mouseexitVerProdutos(){
       buttonVerProdutos.setStyle("-fx-background-color: #E9EEEE");
       buttonVerProdutos.setStyle("-fx-background-radius: 12");
       }
       
       public void handleMenuAlert2() {
             
        try {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/alert2.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
            // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
            Stage dialogStage = new Stage();
            dialogStage.setTitle("ALERTA");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setMaximized(false);
            dialogStage.setResizable(false);

            // Mostra o Dialog e espera atÃ© que o usuÃ¡rio o feche
            dialogStage.show();

        } catch (Exception ex) {
            System.out.println("" + ex + ex.getLocalizedMessage());
        }
    }
}
