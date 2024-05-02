/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Inventario;
import BussinessLogic.Produto;
import BussinessLogic.Stock;
import BussinessLogic.StockLevel;
import BussinessLogic.Usuario;
import BussinessLogic.Venda;
import DataAcessLayer.ProdutoDAO;
import Model.DetalhesVendaModel;
import Model.InventarioModel;
import Service.ProdutosServicos;
import Service.UsuarioServicos;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class IniciarInventaarioController implements Initializable {
    
  
    @FXML
    private Button ButtonInventarioParcial;

    @FXML
    private Button buttonInventarioGeral;
    @FXML
    private Button buttonCancelar;

    @FXML
    private Label labelCodigoUser;

    @FXML
    private Label labelNomeUser;


    Usuario usuario;
    UsuarioServicos usuarioServicos;

   
    Stock stock;
    
    Produto produto;
    Venda venda;
    private DetalhesVendaModel detalhesVendaModel;
    private InventarioModel inventarioModel;
    ProdutosServicos servicoProdutos;
    ObservableList <Produto> produtoObservableList =FXCollections.observableArrayList();
    
         public void initModel(InventarioModel model) {
        if (this.inventarioModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.inventarioModel = model;
    }


    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            handleMenuItemAdicionarProdutoNaTabela();
        } catch (IOException ex) {
            Logger.getLogger(IniciarInventaarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
         ProdutoDAO produtodao = new ProdutoDAO();
             try {
                 //produtodao.ListarProdutos();
                 ResultSet rs=produtodao.ListagemdeProduto();
                 
                while(rs.next()){
                 Integer QueryProductId= rs.getInt("Cod_produto");
                 String QueryNome = rs.getString("Nome");
                 String QueryCategoria = rs.getString("Categoria");
                 String QueryUnidade = rs.getString("Unidade");
                 String QueryDescricao = rs.getString("Descricao");
                 Double QueryPreco = rs.getDouble("Preco_unitario");
                 Double QueryPrecoCompra = rs.getDouble("Preco_De_Compra");
                 String  foto= rs.getString("foto"); 
                 //Double Queryunidade_stock=rs.getDouble("unidades_stock");
                 
                 StockLevel stock = new StockLevel();
                 stock.unidades_stock =rs.getDouble("unidades_stock");
                
                 produtoObservableList.add(new Produto(QueryProductId,QueryNome,QueryCategoria,QueryUnidade,QueryDescricao,QueryPreco,QueryPrecoCompra,foto,stock));
                 }
 
    }   catch (SQLException ex) {
            Logger.getLogger(IniciarInventaarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;

        this.labelCodigoUser.setText(usuario.getCod_Funcionario().toString());
        this.labelCodigoUser.setVisible(false);
        this.labelNomeUser.setText(usuario.getNome());

    }
    
          public void SairButtonOnAction(ActionEvent event){
      Stage stage =(Stage) buttonCancelar.getScene().getWindow();
     
        stage.close();
    }
          
          
         public void handleInventarioParcial(){
         
          
        try {
       
        String codUsuario = this.labelCodigoUser.getText();
          
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/InventarioComProdutos.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        
        InventarioComProdutosController inventarioComProdutosController= loader.<InventarioComProdutosController>getController();

         
       usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
      selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
      inventarioComProdutosController.receberdadosUsuario(selectedUsuario);
        // tabelaAdicaoProdutoController.desabilitabotoes();
        //buscaProdutosController.receberdadosUsuario(selectedUsuario);
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Vendas");
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

          
   
     
     
     @FXML
public void handleInventarioGeral() throws IOException, SQLException {

        
        try {
            
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/InventarioGeral.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            InventarioGeralController inventarioGeralController = loader.<InventarioGeralController>getController();
            
            usuarioServicos = new UsuarioServicos();
            
            //inventarioGeralController.receberdadosUsuario(usuario);
           // inventarioGeralController.initModel(inventarioModel);

            // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Adicionar Produto");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setMaximized(false);
            dialogStage.setResizable(false);

            // Mostra o Dialog e espera atÃ© que o usuÃ¡rio o feche
            dialogStage.show();
             for (Produto produto : produtoObservableList) {
        // Para cada produto na lista, crie uma instância de Inventario e adicione-a à sua model
        Inventario inventario = new Inventario(produto.getCod_produto(), produto.getNome(), produto.getStock().getUnidades_stock(), 0.0, 0.0);
        inventarioModel.addInventario(inventario);
    }
    
        } catch (Exception ex) {
            System.out.println("" + ex + ex.getLocalizedMessage());
            System.out.println("" + ex.toString());
        }
        
       
      
}


@FXML
    public void handleMenuItemAdicionarProdutoNaTabela() throws IOException {

        try {

         
  for (Produto produto : produtoObservableList) {
        // Para cada produto na lista, crie uma instância de Inventario e adicione-a à sua model
        Inventario inventario = new Inventario(produto.getCod_produto(), produto.getNome(), produto.getStock().getUnidades_stock(), 0.0, 0.0);
        inventarioModel.addInventario(inventario);
    }
    
            
        } catch (Exception ex) {

            System.out.println("" + ex.toString());
        }

    }
    
      
   

  


    }
    
    



