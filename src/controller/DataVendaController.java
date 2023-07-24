/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
public class DataVendaController implements Initializable {
    
    @FXML
    private Button ButtonImprimirVenda;

    @FXML
    private Button buttonCancelar;
    
    @FXML
    private DatePicker DatePickerData;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     public void handlePrintVendaPorData(){
        

         

         Date Data_Venda = java.sql.Date.valueOf(DatePickerData.getValue());
    try {
        JasperDesign jDesign = JRXmlLoader.load("src\\relatorios\\Venda_Por_Data.jrxml");

        JasperReport jReport = JasperCompileManager.compileReport(jDesign);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Data_Venda", Data_Venda);  // Defina o valor do parâmetro Cod_Venda

        JasperPrint jPrint = JasperFillManager.fillReport(jReport, parametros, ConnectionFactory.getSakilaConnection());

        JasperViewer viewer = new JasperViewer(jPrint, false);

        viewer.setTitle("Detalhes de Venda");
        viewer.show();

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
     
     
     
       public void SairButtonOnAction(ActionEvent event){
      Stage stage =(Stage) buttonCancelar.getScene().getWindow();
     
        stage.close();
    }
   
}
