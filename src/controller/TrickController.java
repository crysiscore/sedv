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
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
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


// POI
 import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
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
    private DatePicker DatepickerData;

    @FXML
    private ImageView ImageViewAdicionarProduto;

    @FXML
    private Pane PnaePane;

    @FXML
    private Button btnSair;

    @FXML
    private Button buttnSair;

    @FXML
    private MenuItem buttonSair1;

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
    private MenuItem menuItemProdutosExistentes;

    @FXML
    private MenuItem menuItemRelatoriosProdutosPoucoStock;

    @FXML
    private MenuItem menuItemRelatoriosVendasPorData;

    @FXML
    private MenuItem menuItemRelatoriosVendasRecentes;

    @FXML
    private MenuItem menuItemStockAdicionarStock;

    @FXML
    private MenuItem menuItemStockListaProdutos1;

    @FXML
    private MenuItem menuItemStockNovoProduto1;

    @FXML
    private MenuItem menuItemVendasListaDeVendas;

    @FXML
    private MenuItem menuItemVendasNovaVenda;

    @FXML
    private MenuItem menuItemVendasPorPeriodo;

    @FXML
    private MenuBar menubarvendas;

    @FXML
    private Menu menumAdministracao;

    @FXML
    private MenuItem menumItemInventario;

    @FXML
    private MenuItem menumitemParametrizacao;

    @FXML
    private MenuItem menumitemgestaoUtilizadores;

    @FXML
    private Menu menumstock;


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
                if(!usuario.getNome().equals("Admin")){
                      menuItemStockAdicionarStock.setDisable(true);
    menuItemStockNovoProduto1.setDisable(true);
    menumItemInventario.setDisable(true);
    menumitemParametrizacao.setDisable(true);
    menumitemgestaoUtilizadores.setDisable(true);
    imageViewAdicionarStock.setDisable(true);
            
        }
        return usuario;

        
    }

  @Override
public void initialize(URL url, ResourceBundle rb) {
    // TODO
//desabilitar_Botoes();

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
         listadeProdutosController.receberdadosUsuario(selectedUsuario);
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
       
       
        public void handleMenumItemInventario(){
          
        try {
          String codUsuario = this.labelUserCod.getText();
      
          
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
        dialogStage.setTitle("Inventário");
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
        
          public void handleImprimirExcel() {
     
              
  try {
        XSSFWorkbook workbook = new XSSFWorkbook();

    //Create a blank sheet
    XSSFSheet sheet = workbook.createSheet("Employee Data");

    //This data needs to be written (Object[])
    Map<String, Object[]> data = new TreeMap<String, Object[]>();
    data.put("1", new Object[]{"ID", "NAME", "LASTNAME"});
    data.put("2", new Object[]{1, "Amit", "Shukla"});
    data.put("3", new Object[]{2, "Lokesh", "Gupta"});
    data.put("4", new Object[]{3, "John", "Adwards"});
    data.put("5", new Object[]{4, "Brian", "Schultz"});

    //Iterate over data and write to sheet
    Set<String> keyset = data.keySet();
    int rownum = 0;
    for (String key : keyset) {
      Row row = sheet.createRow(rownum++);
      Object[] objArr = data.get(key);
      int cellnum = 0;
      for (Object obj : objArr) {
        Cell cell = row.createCell(cellnum++);
        if (obj instanceof String)
          cell.setCellValue((String) obj);
        else if (obj instanceof Integer)
          cell.setCellValue((Integer) obj);
      }
    }
  
      //Write the workbook in file system
     // URL url = ReadExcelDemo.class.getClassLoader().getResource(
        //  "howtodoinjava_demo.xlsx");
        
      File file= new File("C:\\sedv\\POI.xlsx");
      FileOutputStream out = new FileOutputStream(file);
      workbook.write(out);
      out.close();

      System.out.println("howtodoinjava_demo.xlsx written successfully on " +
          "disk.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  
       
    }
          
          
   public void desabilitar_Botoes() {
    String username = usuario.getNome(); // Obtenha o nome de usuário da instância usuario
    boolean isAdmin = username.equals("Admin");
    
    menuItemStockAdicionarStock.setDisable(!isAdmin);
    menuItemStockNovoProduto1.setDisable(!isAdmin);
    menumItemInventario.setDisable(!isAdmin);
    menumitemParametrizacao.setDisable(!isAdmin);
    menumitemgestaoUtilizadores.setDisable(!isAdmin);
}
   
   
    public void handleMenumRelatoriosExcel(){
          
        try {
          String codUsuario = this.labelUserCod.getText();
      
          
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/Relatorios_Excel.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        
        Relatorios_ExcelController relatorios_ExcelController= loader.<Relatorios_ExcelController>getController();

         
       usuarioServicos = new UsuarioServicos();
        Usuario selectedUsuario = new Usuario();
      selectedUsuario =usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
   
        // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Relatorios em Excel");
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


}
