/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataAcessLayer.ProdutoDAO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
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

            // Create a sheet named "Employee Data"
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

            int numeroLinha = 3; // Start from the second row

            while (rs.next()) {
                Object[] linha = new Object[]{
                        rs.getInt("Cod_Produto"),
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

            // Write the workbook to the file system
            File file = new File("C:\\sedv\\Relatorios\\Produtos_Com_Pouco_Stock.xlsx");
            try (FileOutputStream out = new FileOutputStream(file)) {
                workbook.write(out);
            }

            JOptionPane.showMessageDialog(null, "Produtos_Com_Pouco_Stock.xlsx written successfully on disk.");

            // Try to open the Excel file automatically
            try {
                Desktop.getDesktop().open(file);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Unable to open the file automatically. Please find the file at: " + file.getAbsolutePath());
            }

        } catch (Exception e) {
            e.printStackTrace();
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
            headerRow.createCell(0).setCellValue("PRODUTO");
            headerRow.createCell(1).setCellValue("PREÇO");
            headerRow.createCell(2).setCellValue("CATEGORIA");
            headerRow.createCell(3).setCellValue("UNIDADE");
            headerRow.createCell(4).setCellValue("DESCRIÇÃO");
            headerRow.createCell(5).setCellValue("STOCK");
            headerRow.createCell(6).setCellValue("PREÇO DE AQUISIÇAÕ");

            // Defina a largura da coluna antes de aplicar o estilo negrito
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                sheet.setColumnWidth(i, 21 * 256);
            }

            // Crie um estilo de fonte com negrito
            XSSFFont font = workbook.createFont();
            font.setBold(true);

            // Crie um estilo e atribua a fonte negrito a ele
            CellStyle boldStyle = workbook.createCellStyle();
            boldStyle.setFont(font);

            // Aplique o estilo negrito apenas às células do cabeçalho
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                Cell cell = headerRow.getCell(i);
                cell.setCellStyle(boldStyle);
            }

            // This data needs to be written (Object[])
            Map<String, Object[]> data = new TreeMap<>();
            data.put("1", new Object[]{"PRODUTO", "PREÇO", "CATEGORIA", "UNIDADE", "DESCRIÇÃO", "STOCK", "PREÇO DE AQUISIÇAÕ"});

            int numeroLinha = 2; // Começar da segunda linha

            while (rs.next()) {
                Object[] linha = new Object[]{
                        rs.getString("Nome"),
                        rs.getDouble("Preco_unitario" ),
                        rs.getString("Categoria"),
                        rs.getString("Unidade"),
                        rs.getString("Descricao"),
                        rs.getDouble("unidades_stock"),
                        rs.getDouble("Preco_De_Compra"),
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
                        // Se o valor for nulo, você pode lidar com isso de acordo com sua lógica
                        cell.setCellValue(""); // Ou use cell.setBlank() dependendo da versão do Apache POI
                    }
                }
            }

            // Write the workbook to the file system
            File file = new File("C:\\sedv\\Relatorios\\Lista_De_Produtos_Existentes.xlsx");
            try (FileOutputStream out = new FileOutputStream(file)) {
                workbook.write(out);
            } finally {
                workbook.close(); // Certifique-se de fechar o workbook
            }

            JOptionPane.showMessageDialog(null, "Lista_De_Produtos_Existentes.xlsx written successfully on disk.");

            // Abrir o arquivo Excel automaticamente
            Desktop.getDesktop().open(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Outros métodos e membros da classe podem ser colocados aqui




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