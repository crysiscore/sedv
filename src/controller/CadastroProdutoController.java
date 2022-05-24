/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Neusia
 */
public class CadastroProdutoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button buttonSalvar;

    @FXML
    private Button buttonVerProdutos;

    @FXML
    private ComboBox<?> comboBoxCategoria;

    @FXML
    private ComboBox<?> comboBoxUnidade;

    @FXML
    private ImageView imageViewFoto;

    @FXML
    private TextField textFieldDescricao;

    @FXML
    private TextField textFieldPreco;

    @FXML
    private TextField textfieldNomeProduto;
    
    
    @FXML
    private ImageView imageViewCloseWindow;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  
    
    
    }    
    
      public void CancelButtonOnAction(){
        Stage stage =(Stage) imageViewCloseWindow.getScene().getWindow();
        stage.close();
    }
  
    
}
