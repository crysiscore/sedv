/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Inventario;
import BussinessLogic.Usuario;
import Model.InventarioModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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

    }

    
    
     @FXML
    private void imprimirExcel(ActionEvent event) {
        try {
            File tempFile = File.createTempFile("detalhes_inventario", ".xlsx");
            exportToExcel(tableViewListaProdutosInventario, tempFile);
            openExcelFile(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportToExcel(TableView<Inventario> tableView, File file) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(); 
             FileOutputStream fos = new FileOutputStream(file)) {
            Sheet sheet = workbook.createSheet("Detalhes_Inventario");

            // Cabeçalho
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < tableView.getColumns().size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(tableView.getColumns().get(i).getText());
            }

            // Dados
            for (int rowIndex = 0; rowIndex < tableView.getItems().size(); rowIndex++) {
                Row row = sheet.createRow(rowIndex + 1);
                for (int colIndex = 0; colIndex < tableView.getColumns().size(); colIndex++) {
                    TableColumn<Inventario, ?> column = tableView.getColumns().get(colIndex);
                    Cell cell = row.createCell(colIndex);
                    // Aqui verificamos se o valor da célula é nulo para evitar NullPointerException
                    Object value = column.getCellData(rowIndex);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                }
            }

            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openExcelFile(File file) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", file.getAbsolutePath());
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
        
      
    
    
