/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Inventario;
import BussinessLogic.Usuario;
import DataAcessLayer.ProdutoDAO;
import Model.InventarioModel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class Detalhes_InventarioController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;

  @FXML
    private Button ButtonCancelar;

    @FXML
    private Button ButtonVisualizarProdutosNoExcel;

    @FXML
    private Label labelCliente;

    @FXML
    private Label labelCodProduto;

    @FXML
    private Label labelCodigoInventario;

    @FXML
    private Label labelFuncionario;

    @FXML
    private Label labelIDFuncionario;

    @FXML
    private Label labelTipodeinventario;
    
    @FXML
    private Label labelNomeUtilizador;

    @FXML
    private Label labelTotal;
    
    @FXML
    private TableView<Inventario> tableViewListaProdutosInventario;
    
    @FXML
    private TableColumn<Inventario, Integer> columnCodProduto;

    @FXML
    private TableColumn<Inventario, Double> tableColumnDiferenca;

    @FXML
    private TableColumn<Inventario, String> tableColumnNome;

    @FXML
    private TableColumn<Inventario, Double> tableColumnQuantidadeContada;

    @FXML
    private TableColumn<Inventario, Double> tableColumnQuantidadeQueExistia;

    Usuario usuario;
    
    
     private InventarioModel inventarioModel = new InventarioModel();
      ObservableList<Inventario> inventario = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
     
     
       public void initModel(InventarioModel model) {
        if (this.inventarioModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.inventarioModel = model;
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           
        columnCodProduto.setCellValueFactory(new PropertyValueFactory<>("Cod_Produto"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tableColumnQuantidadeQueExistia.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        tableColumnQuantidadeContada.setCellValueFactory(new PropertyValueFactory<>("Quantidade_Contada"));
        tableColumnDiferenca.setCellValueFactory(new PropertyValueFactory<>("Diferenca_Existente_Contada"));
        

        inventario = inventarioModel.getInventarioList();
        
        tableViewListaProdutosInventario.setItems(inventario);
 
           // Add a change listener to the data list

        }

    
    
       public Usuario getUsuario() {
        return usuario;
    }
       
     public void receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;
        
        this.labelFuncionario.setText(usuario.getNome());
        this.labelFuncionario.setVisible(true);
        this.labelIDFuncionario.setText(usuario.getCod_Funcionario().toString());
        this.labelIDFuncionario.setVisible(false);
    }
     
       public void CancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) ButtonCancelar.getScene().getWindow();
        stage.close();
    }

    public void ReceberDadosInventario(List<Inventario> inventarios) {

        for (Inventario inventario : inventarios) {
            labelCodigoInventario.setText(inventario.getCod_Inventario().toString());
            //labelNomeUtilizador.setText(inventario.getDetalhesInventario().getUser().getNome());
              
             


            Inventario inv = new Inventario(inventario.getDetalhesInventario().getCod_Produto(), inventario.getDetalhesInventario().getNome(),
                    inventario.getDetalhesInventario().getStock(),
                    inventario.getDetalhesInventario().getQuantidade_Contada(), inventario.getDetalhesInventario().getDiferenca_Existente_Contada()
                    );
            inventarioModel.addInventario(inv);
           
        }
    }
    

    
    public void ReceberDadoInventario(Inventario inventario) {
        this.labelCodigoInventario.setText(inventario.getCod_Inventario().toString());
      labelTipodeinventario.setText(inventario.getTipo_inventario());
      labelNomeUtilizador.setText(inventario.getUser().getNome());


    }

    
    
     @FXML
    private void imprimirExcel(ActionEvent event) {
        try {
       
            exportToExcel(tableViewListaProdutosInventario);

        } catch (Exception e) {
            e.printStackTrace();
            DialogUtil.showErrorMessage(" Erro  : "+ e.getMessage(), "ERRO");

        }
    }


    private void exportToExcel(TableView<Inventario> tableView) {


        try {
      
            // Create a new workbook
            Workbook workbook = new XSSFWorkbook();

            // Create a sheet named "Produtos com pouco stock"
            Sheet sheet = workbook.createSheet("DETALHES DO INVENTARIO");

            // Create a font style with bold
            Font font = workbook.createFont();
            font.setBold(true);

            // Create a style and assign the bold font to it
            CellStyle boldStyle = workbook.createCellStyle();
            boldStyle.setFont(font);

            // This data needs to be written (Object[])
            Map<String, Object[]> data = new TreeMap<>();
            data.put("1", new Object[]{"CÓDIGO", "NOME DO PRODUTO", "QTD. ANTERIOR", "QTD. CONTADA", "DIFERENCA"});

            int numeroLinha = 2; // Start from the second row

            
                    // Dados
            for (int rowIndex = 0; rowIndex < tableView.getItems().size(); rowIndex++) {

                    TableColumn<Inventario, ?> columnCod = tableView.getColumns().get(0);
                    TableColumn<Inventario, ?> columnNomeProd = tableView.getColumns().get(1);
                    TableColumn<Inventario, ?> columnQtdAnt = tableView.getColumns().get(2);
                    TableColumn<Inventario, ?> columnQtdCont = tableView.getColumns().get(3);
                    TableColumn<Inventario, ?> columnDiff = tableView.getColumns().get(4);

                    // Aqui verificamos se o valor da célula é nulo para evitar NullPointerException
                    Object valueCod = columnCod.getCellData(rowIndex);
                    Object valueNomeProd = columnNomeProd.getCellData(rowIndex);
                    Object valueQtdAnt = columnQtdAnt.getCellData(rowIndex);
                    Object valueQtdCont = columnQtdCont.getCellData(rowIndex);
                    Object valueDiff = columnDiff.getCellData(rowIndex);
                    Object[] linha = new Object[]{
                        valueCod,
                        valueNomeProd,
                        valueQtdAnt,
                        valueQtdCont,
                        valueDiff,
                };
               
                
                data.put(String.valueOf(numeroLinha++), linha);
            }

            
           
            // Iterate over data and write to sheet
            Set<String> keyset = data.keySet();
            int rownum = 0;
            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj != null) {
                        if (obj instanceof String) {
                            cell.setCellValue((String) obj);
                        } else if (obj instanceof Integer) {
                            cell.setCellValue((Integer) obj);
                        } else if (obj instanceof Double) {
                            cell.setCellValue((Double) obj);
                        }
                    } else {
                        // If the value is null, handle it according to your logic
                        cell.setCellValue(""); // Or use cell.setBlank() depending on the Apache POI version
                    }

                    // Apply bold style to header cells
                    if (rownum == 1) {
                        cell.setCellStyle(boldStyle);
                    }
                }
            }

            // Auto-size columns after adding data
            for (int i = 0; i < data.get("1").length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Usando um caminho relativo
            String relativePath = "Detalhes_do_inventario.xlsx";
            File file = new File(relativePath);

            // Cria diretório caso não exista
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Write the workbook to the file system
            try (FileOutputStream out = new FileOutputStream(file)) {
                workbook.write(out);
            }

          

                    String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
               String [] cmd = {"/usr/bin/open" , "-a", "/Applications/Microsoft Excel.app", file.getAbsolutePath()};
             ProcessBuilder pb = new ProcessBuilder(cmd);
             Process p = pb.start();
              
             
            // Runtime.getRuntime().exec(cmd );  
        } else {    // Windows or Unix    
         
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.OPEN)) {
                        desktop.open(file);
                    } else {
                      //  showManualOpenMessage(file);
                    }
                } else {
                    
                    
                }}     
        } catch (Exception e) {
            e.printStackTrace();
            DialogUtil.showErrorMessage(" Erro  : "+ e.getMessage(), "ERRO");

            
        }  
    }
    

}
        
      
    
    
