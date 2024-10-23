/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataAcessLayer.VendaDao;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import jdbc.ConnectionFactory;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class Datas_VendaController implements Initializable {
    
   @FXML
    private Button ButtonImprimirVenda;

    @FXML
    private DatePicker DatePickerDataFinal;

    @FXML
    private DatePicker DatePickerDataInicial;

    @FXML
    private Button buttonCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
   public void handlePrintVendaPorData() {

        LocalDate dataVendaInicialValue = DatePickerDataInicial.getValue();
        LocalDate dataVendaFinalValue = DatePickerDataFinal.getValue();

        if (dataVendaInicialValue == null || dataVendaFinalValue == null) {
           handleMenuAlert7() ;
        } else {
            Date dataVendaInicial = java.sql.Date.valueOf(dataVendaInicialValue);
            Date dataVendaFinal = java.sql.Date.valueOf(dataVendaFinalValue);

            try {
                JasperDesign jDesign = JRXmlLoader.load("src\\relatorios\\Vendas_em_um_Periodo.jrxml");

                JasperReport jReport = JasperCompileManager.compileReport(jDesign);

                Map<String, Object> parametros = new HashMap<>();
                parametros.put("DataInicio", dataVendaInicial);
                parametros.put("DataFim", dataVendaFinal);

                JasperPrint jPrint = JasperFillManager.fillReport(jReport, parametros, ConnectionFactory.getSakilaConnection());

                JasperViewer viewer = new JasperViewer(jPrint, false);

                viewer.setTitle("Detalhes de Venda");
                viewer.show();
                 DatePickerDataInicial.setValue(null);
                 DatePickerDataFinal.setValue(null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
   
   public void handlePrintVendaPorData1() {

    LocalDate dataVendaInicialValue = DatePickerDataInicial.getValue();
    LocalDate dataVendaFinalValue = DatePickerDataFinal.getValue();

    if (dataVendaInicialValue == null || dataVendaFinalValue == null) {
        handleMenuAlert7();
    } else {
        java.sql.Date dataVendaInicial = convertToDateSql(dataVendaInicialValue);
        java.sql.Date dataVendaFinal = convertToDateSql(dataVendaFinalValue);

        try {
            // Instanciar a classe que gerencia a conexão e chama o procedimento armazenado
            VendaDao vendaDao = new VendaDao();
            ResultSet rs = vendaDao.Detalhes_Vendas_Periodo(dataVendaInicial, dataVendaFinal);

            // Criar um arquivo Excel
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Detalhes de Vendas");

            // Crie um estilo de fonte com negrito
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            CellStyle boldStyle = workbook.createCellStyle();
            boldStyle.setFont(font);

            // Configurar cabeçalhos
            Row headerRow = sheet.createRow(0);
            String[] colunas = {"# Venda","Data" , "Nome_Produto", "Preço", "Quantidade", "Subtotal"};
            for (int i = 0; i < colunas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(colunas[i]);
                cell.setCellStyle(boldStyle);
                sheet.setColumnWidth(i, 21 * 256); // Ajuste da largura da coluna
            }

            // Escrever dados no Excel
            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rs.getInt("Codigo"));
                row.createCell(1).setCellValue(rs.getString("data_venda"));
                row.createCell(2).setCellValue(rs.getString("Nome_Produto"));
                row.createCell(3).setCellValue(rs.getDouble("Preco"));
                row.createCell(4).setCellValue(rs.getInt("Quantidade"));
                row.createCell(5).setCellValue(rs.getDouble("Subtotal"));
            }

            // Write the workbook to a byte array output stream
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();

            // Convert byte array output stream to input stream
            ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());

            // Save the file to a temporary location
            File tempFile = File.createTempFile("DetalhesVendasPeriodo", ".xlsx");
            tempFile.deleteOnExit();
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }

            // Tentar abrir o arquivo Excel automaticamente
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.OPEN)) {
                        desktop.open(tempFile);
                    } else {
                        showManualOpenMessage(tempFile);
                    }
                } else {
                    openFileMacLinux(tempFile);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                showManualOpenMessage(tempFile);
            }

            // Limpar campos de data
            DatePickerDataInicial.setValue(null);
            DatePickerDataFinal.setValue(null);

            // Fechar o ResultSet
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

private java.sql.Date convertToDateSql(LocalDate localDate) {
    return java.sql.Date.valueOf(localDate);
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
    // Substituir com a implementação de diálogo apropriada para sua aplicação
    System.out.println("Não foi possível abrir o arquivo automaticamente. Por favor, encontre o arquivo em: " + file.getAbsolutePath());
}
    public void handleMenuAlert7() {
             
        try {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TrickController.class.getResource("/Presentation/alert7.fxml"));
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
        
          }
          catch (Exception ex) {
          System.out.println("" + ex + ex.getLocalizedMessage());
          System.out.println("" + ex.toString());
              } 
                }
   
     
     
       public void SairButtonOnAction(ActionEvent event){
      Stage stage =(Stage) buttonCancelar.getScene().getWindow();
     
        stage.close();
    }
}
