/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.Date;
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
                JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\Neusia Hilario\\Documents\\NetBeansProjects\\sedv\\src\\relatorios\\Vendas_em_um_Periodo.jrxml");

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
