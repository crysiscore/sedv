/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataAcessLayer.VendaDao;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class Lista_De_Venda_Por_PeriodoController implements Initializable {

    
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
    
       public void SairButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) buttonCancelar.getScene().getWindow();

        stage.close();
    }


    public void handlePrintVendaPorData() {
        LocalDate dataVendaInicialValue = DatePickerDataInicial.getValue();
        LocalDate dataVendaFinalValue = DatePickerDataFinal.getValue();

        if (dataVendaInicialValue == null || dataVendaFinalValue == null) {
            handleMenuAlert7();
        } else {
            java.sql.Date dataVendaInicial = convertToDateSql(dataVendaInicialValue);
            java.sql.Date dataVendaFinal = convertToDateSql(dataVendaFinalValue);

            try {
                VendaDao vendaDao = new VendaDao();
                ResultSet rs = vendaDao.VendaPorPeriodo(dataVendaInicial, dataVendaFinal);

                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Vendas_em_um_Periodo");

                // Crie um estilo de fonte com negrito
                XSSFFont font = workbook.createFont();
                font.setBold(true);

                // Crie um estilo e atribua a fonte negrito a ele
                CellStyle boldStyle = workbook.createCellStyle();
                boldStyle.setFont(font);

                // Crie o cabeçalho da tabela no Excel
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("REFERÊNCIA");
                headerRow.createCell(1).setCellValue("DATA");
                headerRow.createCell(2).setCellValue("TOTAL DA VENDA");
                headerRow.createCell(3).setCellValue("NOME DO CLIENTE");
                headerRow.createCell(4).setCellValue("FUNCIONÁRIO");
                headerRow.createCell(5).setCellValue("PAGAMENTO");

                // Aplique o estilo negrito às células do cabeçalho
                for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                    headerRow.getCell(i).setCellStyle(boldStyle);
                    // Defina a largura da coluna
                    sheet.setColumnWidth(i, 21 * 256);
                }

                int numeroLinha = 2; // Começar da segunda linha

                while (rs.next()) {
                    Row row = sheet.createRow(numeroLinha++);
                    row.createCell(0).setCellValue(rs.getInt("Codigo"));

                    // Ajuste do formato da data
                    
                 // Substitua este trecho no seu código onde a conversão da data é feita
SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
java.util.Date utilDate = dateFormat.parse(rs.getString("data_venda"));
java.sql.Date dataVenda = new java.sql.Date(utilDate.getTime());
row.createCell(1).setCellValue(dataVenda != null ? dataVenda.toLocalDate().toString() : "");


                    row.createCell(2).setCellValue(rs.getDouble("total_venda"));
                    row.createCell(3).setCellValue(rs.getString("nome_cliente"));
                    row.createCell(4).setCellValue(rs.getString("nome"));
                    row.createCell(5).setCellValue(rs.getString("Forma_Pagamento"));
                    // Adicione mais colunas conforme necessário
                }

                // Write the workbook to the file system
                File file = new File("C:\\sedv\\Relatorios\\Lista_De_Vendas_Num_Periodo.xlsx");
                try (FileOutputStream out = new FileOutputStream(file)) {
                    workbook.write(out);
                }

                JOptionPane.showMessageDialog(null, "Relatório de Vendas em um Período criado com sucesso.");

                // Abrir o arquivo Excel automaticamente
                Desktop.getDesktop().open(file);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private java.sql.Date convertToDateSql(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
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

        } catch (Exception ex) {
            System.out.println("" + ex + ex.getLocalizedMessage());
            System.out.println("" + ex.toString());
        }
    }
}
     
