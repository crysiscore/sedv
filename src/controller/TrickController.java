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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author Neusia
 */
public class TrickController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private MenuItem menuItemStockNovoProduto;

    @FXML
    private MenuItem menuItemStockProcurarProduto;

    @FXML
    private MenuItem menuItemStockAdicionarStock;

    @FXML
    private MenuItem menuItemStockListaProdutos;

    @FXML
    private MenuItem menuItemStockSair;

    @FXML
    private MenuItem menuItemVendasNovaVenda;

    @FXML
    private MenuItem menuItemVendasListaVendas;

    @FXML
    private MenuItem menuItemRelatoriosVendasRecentes;

    @FXML
    private MenuItem menuItemRelatoriosVendasPorData;

    @FXML
    private MenuItem menuItemRelatoriosProdutosMaisVendidos;

    @FXML
    private MenuItem menuItemOutrrosUnidadesProdutos;

    @FXML
    private MenuItem menuItemOutrosCategoriasProdutos;

    @FXML
    private MenuItem menuItemOutrosUsuarios;

    @FXML
    private MenuItem menuItemOutrosPrevilegios;

    @FXML
    private MenuItem menuItemAjudaSobre;

    @FXML
    private Button buttonsSair;

    @FXML
    private Label labelProdutos;

    @FXML
    private Label labelVendas;

    @FXML
    private Button buttonNovoProduto;

    @FXML
    private Button buttonAdicionarStock;

    @FXML
    private Button buttonListaProdutos;

    @FXML
    private Button buttonNovaVenda;

    @FXML
    private Button buttonVendasRecentes;

    @FXML
    private Button ButtonProdutosMaisVendidos;

    @FXML
    private ImageView imageViewTrickGif;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
