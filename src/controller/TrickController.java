/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Usuario;
import DataAcessLayer.UsuarioDAO;
import Model.StockModel;
import Service.ProdutosServicos;
import Service.UsuarioServicos;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jdbc.ConnectionFactory;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 * FXML Controller class
 *
 * @author Neusia
 */
public class TrickController implements Initializable {
    ResultSet rs;

 
    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private ImageView ImageViewAdicionarProduto;

    @FXML
    private Pane PnaePane;

    @FXML
    private Button buttnSair;

    @FXML
    private MenuItem buttonSair;

    @FXML
    private ImageView imageViewAdicionarStock;

    @FXML
    private ImageView imageViewListaProdutos;

    @FXML
    private ImageView imageViewVenda;

    @FXML
    private Label labelUserCod;

    @FXML
    private Label labelUsername;

    @FXML
    private MenuItem menuItemAjudaSobre;

    @FXML
    private MenuItem menuItemOutrosCategoriasProdutos;

    @FXML
    private MenuItem menuItemOutrosPrevilegios;

    @FXML
    private MenuItem menuItemOutrosUsuarios;

    @FXML
    private MenuItem menuItemOutrrosUnidadesProdutos;

    @FXML
    private MenuItem menuItemRelatoriosProdutosMaisVendidos;

    @FXML
    private MenuItem menuItemRelatoriosVendasPorData;

    @FXML
    private MenuItem menuItemRelatoriosVendasRecentes;
    
    @FXML
    private MenuItem menuItemRelatoriosProdutosPoucoStock;
    
    @FXML
    private MenuItem menuItemProdutosExistentes;

    @FXML
    private MenuItem menuItemStockAdicionarStock;

    @FXML
    private MenuItem menuItemStockListaProdutos;

    @FXML
    private MenuItem menuItemStockNovoProduto;

    @FXML
    private MenuItem menuItemStockProcurarProduto;

    @FXML
    private MenuItem menuItemVendasListaDeVendas;

    @FXML
    private MenuItem menuItemVendasNovaVenda;
    
    @FXML
    private MenuBar menubarvendas;
    
    @FXML
    private Button btnSair;
    
    @FXML
    private DatePicker DatepickerData;

   


    UsuarioServicos usuarioServicos ;
    Usuario usuario;
    ProdutosServicos servicoProdutos;

    public Usuario getProduto() {
        return usuario;
    }

    public Usuario receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;

        this.labelUserCod.setText(usuario.getCod_Funcionario().toString());
        this.labelUsername.setText(usuario.getNome());
        this.labelUserCod.setVisible(false);
        return usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      DatepickerData.setVisible(false);
     
    }    
     public void SairButtonOnAction(ActionEvent event){
      Stage stage =(Stage) btnSair.getScene().getWindow();
     
        stage.close();
    }
   
     
             
 
    
        
    
    public void handleMenuItemStockAdicionarStock (){
         
          
        try {
       
        String codUsuario = this.labelUserCod.getText();
          
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/TabelaAdicaoProduto1.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        
         TabelaAdicaoProdutoController tabelaAdicaoProdutoController= loader.<TabelaAdicaoProdutoController>getController();
       
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        tabelaAdicaoProdutoController.receberdadosUsuario(selectedUsuario);
        
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registo de Stock");
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

   
    public void handleMenuItemVendaAdicionarVenda(){
         
          
        try {
       
        String codUsuario = this.labelUserCod.getText();
          
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/Venda.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        
        VendaController vendaController= loader.<VendaController>getController();

         
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        vendaController.receberdadosUsuario(selectedUsuario);
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

    
    public void handleMenuItemProdutoListaProduto (){
         
          
         try {
       
      
        String codUsuario = this.labelUserCod.getText();
         
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/ListadeProdutos.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        
         ListadeProdutosController listadeProdutosController= loader.<ListadeProdutosController>getController();
        
         
        usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
        selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
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

    
    public void handleMenuItemProdutoRegistrarProduto (){
         
          
        try {
       String codUsuario = this.labelUserCod.getText();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/CadastroProduto.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        
         CadastroProdutoController cadastroProdutoProdutoController= loader.<CadastroProdutoController>getController();
        
         
        //usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
       // selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
        //CadastroProdutoController.receberdadosUsuario(selectedUsuario);
        
       
        
        cadastroProdutoProdutoController.OcultarBotaoEditar();
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
    
    
     public void handleMenuItemListaVendas() {

        try {
           
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/Lista_Venda.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            Lista_VendaController listaVendaController = loader.<Lista_VendaController>getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Registo de Produtos");
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
    }

    
 
     
    public void mouseexitbuttonCancel(){
       buttnSair.setStyle("-fx-background-color: #FFF");
       buttnSair.setStyle("-fx-background-radius: 13");
   }
    
    
     public void handlePrintProdutosComPoucoStock(){

        try{
            JasperDesign jDesign = JRXmlLoader.load("src\\relatorios\\Produtos_Com_Pouco_Stock.jrxml");
        
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, ConnectionFactory.getSakilaConnection());
            
            JasperViewer viewer = new JasperViewer(jPrint, false);
            
            viewer.setTitle("Lista de Produtos com Pouco Stock");
            viewer.show();
            
        }catch(Exception e){}
    }
     
     
       
     public void handlePrintVendaPorData(){
         try {
           
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/DataVenda1.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            DataVendaController dataVendaController = loader.<DataVendaController>getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Data de Venda");
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
    }
     
       public void handlePrintVendaPorPeriodo(){
         try {
           
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/Datas_Venda.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            Datas_VendaController datas_VendaController = loader.<Datas_VendaController>getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Data de Venda");
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
    }
     
       public void handleprintProdutosExistentes(){
  
        
        try{
            JasperDesign jDesign = JRXmlLoader.load("src\\relatorios\\ListaProdutos_Blue.jrxml");
        
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, ConnectionFactory.getSakilaConnection());
            
            JasperViewer viewer = new JasperViewer(jPrint, false);
            
            viewer.setTitle("Lista de Produtos Existentes");
            viewer.show();
            
        }catch(Exception e){}
    }
       
     
             
    public void handlePrintVendaRecente() {
        DatepickerData.setValue(LocalDate.now());
        Date Data_Venda = java.sql.Date.valueOf(DatepickerData.getValue());
        
        try {
            JasperDesign jDesign = JRXmlLoader.load("src\\relatorios\\Venda_Recente.jrxml");

            JasperReport jReport = JasperCompileManager.compileReport(jDesign);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Data_Venda", Data_Venda); 

            JasperPrint jPrint = JasperFillManager.fillReport(jReport, parametros, ConnectionFactory.getSakilaConnection());

            JasperViewer viewer = new JasperViewer(jPrint, false);

            viewer.setTitle("Detalhes de Venda de Hoje");
            viewer.show();

        } catch (Exception e) {
            e.printStackTrace();
    }
        

       
       
    }
}
