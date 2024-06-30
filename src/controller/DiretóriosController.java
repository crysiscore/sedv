/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataAcessLayer.DiretorioDAO;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class DiretóriosController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button buttonContinuar;

    @FXML
    private ComboBox<String> comboboxListaDiretorios;

    @FXML
    private Text labelRegistoProdutos;



    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
     public void populacomboUnidade() {

        ObservableList<String> listaDiretorio = FXCollections.observableArrayList();
        try {
            DiretorioDAO Dd = new DiretorioDAO();
            ResultSet rs = Dd.popularDiretorio();
            while (rs.next()) {
                listaDiretorio.add(rs.getString("Descricao_Diretorio"));

            }

        } catch (Exception e) {
            System.out.println("" + e);

        }

        comboboxListaDiretorios.setItems(null);
        comboboxListaDiretorios.setItems(listaDiretorio);
    }
    
}
