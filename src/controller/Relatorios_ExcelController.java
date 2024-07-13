/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataAcessLayer.ProdutoDAO;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class Relatorios_ExcelController implements Initializable {
    
    
    @FXML
    private Button buttonCancelar11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
       public void SairButtonOnAction(ActionEvent event){
      Stage stage =(Stage) buttonCancelar11.getScene().getWindow();
     
        stage.close();
    }

    public void handleImprimirProdutosComPoucoStockExcel() {
        ProdutoDAO produtodao = new ProdutoDAO();

        try {
            ResultSet rs = produtodao.ProdutoCompoucoStock();

            // Create a new workbook
            Workbook workbook = new XSSFWorkbook();

            // Create a sheet named "Produtos com pouco stock"
            Sheet sheet = workbook.createSheet("Produtos com pouco stock");

            // Create a font style with bold
            Font font = workbook.createFont();
            font.setBold(true);

            // Create a style and assign the bold font to it
            CellStyle boldStyle = workbook.createCellStyle();
            boldStyle.setFont(font);

            // This data needs to be written (Object[])
            Map<String, Object[]> data = new TreeMap<>();
            data.put("1", new Object[]{"CÓDIGO", "NOME DO PRODUTO", "PREÇO", "UNIDADES NO STOCK"});

            int numeroLinha = 2; // Start from the second row

            while (rs.next()) {
                Object[] linha = new Object[]{
                        rs.getString("codigo_manual"),
                        rs.getString("Nome"),
                        rs.getDouble("Preco_unitario"),
                        rs.getDouble("unidades_stock"),
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
            String relativePath = "Produtos_Com_Pouco_Stock.xlsx";
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
                        showManualOpenMessage(file);
                    }
                } else {
                    
                    
                }}     
        } catch (Exception e) {
            e.printStackTrace();
               JOptionPane.showMessageDialog(null,e.getMessage());

            
        }  
    }
    // Other methods and class members can be placed here


 public void handleImprimirListaDeProdutos() {
        ProdutoDAO produtodao = new ProdutoDAO();

        try {
            ResultSet rs = produtodao.ProdutosExistentes();
            XSSFWorkbook workbook = new XSSFWorkbook();

            // Create a blank sheet
            XSSFSheet sheet = workbook.createSheet("Lista de Produtos");

            // Crie o cabeçalho da tabela no Excel
            Row headerRow = sheet.createRow(0);
            String[] headerTitles = {"CODIGO", "PRODUTO", "PREÇO", "CATEGORIA", "UNIDADE", "DESCRIÇÃO", "STOCK", "PREÇO DE AQUISIÇÃO"};
            for (int i = 0; i < headerTitles.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headerTitles[i]);
                sheet.setColumnWidth(i, 21 * 256);
            }

            // Crie um estilo de fonte com negrito
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            CellStyle boldStyle = workbook.createCellStyle();
            boldStyle.setFont(font);

            // Aplique o estilo negrito às células do cabeçalho
            for (Cell cell : headerRow) {
                cell.setCellStyle(boldStyle);
            }

            // Este mapa armazenará os dados
            Map<String, Object[]> data = new TreeMap<>();
            int rowIndex = 1;

            while (rs.next()) {
                Object[] linha = {
                        rs.getString("codigo_manual"),
                        rs.getString("Nome"),
                        rs.getDouble("Preco_unitario"),
                        rs.getString("Categoria"),
                        rs.getString("Unidade"),
                        rs.getString("Descricao"),
                        rs.getDouble("unidades_stock"),
                        rs.getDouble("Preco_De_Compra"),
                };
                data.put(String.valueOf(rowIndex++), linha);
            }

            // Iterar sobre os dados e escrevê-los na planilha
            Set<String> keyset = data.keySet();
            int rownum = 1;
            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj != null) {
                        if (obj instanceof String) {
                            cell.setCellValue((String) obj);
                        } else if (obj instanceof Double) {
                            cell.setCellValue((Double) obj);
                        }
                    } else {
                        cell.setCellValue("");
                    }
                }
            }

            // Write the workbook to a byte array output stream
            //ByteArrayOutputStream out = new ByteArrayOutputStream();
            //workbook.write(out);
            //workbook.close();

            // Convert byte array output stream to input stream
            //InputStream inputStream = new ByteArrayInputStream(out.toByteArray());
   String relativePath = "Lista_De_Produtos_Existentes.xlsx";
            File file = new File(relativePath);

            // Cria diretório caso não exista
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Write the workbook to the file system
            try (FileOutputStream out = new FileOutputStream(file)) {
                workbook.write(out);
            } catch (Exception ex) {
                ex.printStackTrace();
                // showManualOpenMessage(tempFile);
            }            
 

            
         String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {    
            
            //  String [] cmd = {"open" , "-a", "/Applications/Microsoft Excel.app", file.getAbsolutePath()};
             String [] cmd = {"/usr/bin/open" , "-a", "/Applications/Microsoft Excel.app", file.getAbsolutePath()};
             ProcessBuilder pb = new ProcessBuilder(cmd);
             Process p = pb.start();
        } else {    // Windows or Unix    
         
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.OPEN)) {
                        desktop.open(file);
                    } else {
                        showManualOpenMessage(file);
                    }
                } else {
                    
                    
                }}     
        } catch (Exception e) {
            e.printStackTrace();
               JOptionPane.showMessageDialog(null,e.getMessage());

            
        }     
    }

    private void openFileMacLinux(File file) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            Runtime.getRuntime().exec("open " + file.getAbsolutePath());
        } else if (os.contains("nix") || os.contains("nux")) {
            Runtime.getRuntime().exec("xdg-open " + file.getAbsolutePath());
        } else {
            showManualOpenMessage(file);
        }
    }

    private void showManualOpenMessage(File file) {
        JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo automaticamente. Por favor, encontre o arquivo em: " + file.getAbsolutePath());
    }



    public void handlePrintVendaPorPeriodo(){
         try {
           
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/Lista_De_Venda_Por_Periodo.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            Lista_De_Venda_Por_PeriodoController lista_De_Venda_Por_PeriodoController = loader.<Lista_De_Venda_Por_PeriodoController>getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Vendas Por Período");
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
}