/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Produto;
import java.sql.ResultSet;
import BussinessLogic.Stock;
import BussinessLogic.Usuario;
import DataAcessLayer.StockDAO;
import Model.StockModel;
import Service.ProdutosServicos;
import Service.StockServicos;
import com.mysql.cj.protocol.Resultset;
import com.sun.javafx.collections.MappingChange.Map;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Window;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Neusia
 */
public class CadStockController implements Initializable {
    
    
    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private DatePicker DatePickerDataEntrada;

    @FXML
    private TextField TextFieldCodigoFuncionario;

    @FXML
    private TextField TextFieldCodigoProduto;

    @FXML
    private TextField TextFieldFabricante;

    @FXML
    private TextField TextFieldNumeroLote;

    @FXML
    private TextField TextFieldlQuantidadeRecebida;

    @FXML
    private Button buttonAdicionarStockNaTabela;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Label labelNomeFuncionario;

    @FXML
    private TextField textfieldNomeProduto;
    
     @FXML
    private ImageView imageViewFotoProduto;
 
  
       
    Usuario usuario;
    ProdutosServicos prodServ;
    StockServicos stServico;
    private Produto produto;
    Stock stock;
    private Stage dialogStage2;
    private StockModel stockModel;

    private StockDAO dao;
    //private ObservableList<Stock> stock;
    StockServicos stockServicos;
    ProdutosServicos servicoProdutos;

   

    public Produto getProduto() {
        return produto;
    }

    public void initModel(StockModel model) {
        if (this.stockModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.stockModel = model;
    }

    public void ReceberDadosProduto(Produto produto) {
        this.produto = produto;

        //  this.TextFieldCodigoFuncionario.setText();
        //this.TextFieldCodigoFuncionario.setText(String.valueOf(user.getCod_Funcionario()));
        this.TextFieldCodigoProduto.setText(produto.getCod_produto().toString());
        this.TextFieldCodigoProduto.setVisible(true);
         this.TextFieldCodigoProduto.setEditable(false);
        this.textfieldNomeProduto.setText(produto.getNome());
        this.textfieldNomeProduto.setEditable(false);
        Image image= new Image("file:"+produto.getFoto());
        this.imageViewFotoProduto.setImage(image);
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;

        this.TextFieldCodigoFuncionario.setText(usuario.getCod_Funcionario().toString());
        this.TextFieldCodigoFuncionario.setVisible(false);
        this.labelNomeFuncionario.setText(usuario.getNome());
        
        // return usuario;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        dao = new StockDAO();
        
        DatePickerDataEntrada.setValue(LocalDate.now());
        
        
        // Definindo o filtro com uma expressão regular para permitir apenas números inteiros ou doubles
        TextFormatter<Integer> textFormatter = new TextFormatter<>(
                new IntegerStringConverter(),
                0,
                c -> {
                    if (c.getControlNewText().matches("\\d*|\\d+\\.\\d*")) {
                        return c;
                    } else {
                        return null;
                    }
                });

        TextFieldlQuantidadeRecebida.setTextFormatter(textFormatter);

        // Adicionando um evento de escuta para verificar se o texto contém letras
        textFormatter.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null && !TextFieldlQuantidadeRecebida.getText().isEmpty()) {
                // Exibe uma mensagem de erro caso letras sejam inseridas
                JOptionPane.showMessageDialog(null,"Digite apenas números inteiros ou decimais válidos!");
                TextFieldlQuantidadeRecebida.setText(oldValue.toString()); // Restaura o valor anterior
            }
        });

    }

 


    public void CancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) buttonCancelar.getScene().getWindow();
        stage.close();
    }

        public boolean verificadados() {
        boolean fds = false;

        if (this.TextFieldlQuantidadeRecebida.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "INTRODUZA A QUANTIDADE RECEBIDA ");
            fds = true;

        } else if (DatePickerDataEntrada.getValue() == null) {
            JOptionPane.showMessageDialog(null, "INTRODUZA  A DATA DE REGISTO");
            fds = true;
        } else if (this.TextFieldCodigoProduto.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "INTRODUZA O CODIGO DO PRODUTO ");
            fds = true;

        } else if (this.TextFieldNumeroLote.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "INTRODUZA O NUMERO DO LOTE ");
            fds = true;

        } else if (this.TextFieldFabricante.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "INTRODUZA O NOME DO FABRICANTE ");
            fds = true;

        }

        return fds;
    }



                @FXML
                public void handleMenuItemStockAdicionarStockNaTabela() throws IOException {

         
                 if (!verificadados()) {
                    
                   
                 try {
                 
        
                Integer produto_Cod_Produto = Integer.parseInt(TextFieldCodigoProduto.getText());
                Integer quantidade_recebida = Integer.parseInt(TextFieldlQuantidadeRecebida.getText());
                Date data_entrada = java.sql.Date.valueOf(DatePickerDataEntrada.getValue());

                //Integer prod =Integer.parseInt(TextFieldCodigoProduto.getText());
                Integer user = Integer.parseInt(TextFieldCodigoFuncionario.getText());
                String numero_lote = TextFieldNumeroLote.getText();
                String fabricante = TextFieldFabricante.getText();
                
               
             
                Stock s = new Stock(Integer.parseInt(TextFieldCodigoProduto.getText()), Integer.parseInt(TextFieldlQuantidadeRecebida.getText()),
                        java.sql.Date.valueOf(DatePickerDataEntrada.getValue()), Integer.parseInt(TextFieldCodigoFuncionario.getText()),
                        TextFieldNumeroLote.getText(), TextFieldFabricante.getText());
                        stockModel.addStock(s);

                        Stage stage =(Stage) buttonAdicionarStockNaTabela.getScene().getWindow();
                        stage.close();
                        
 
                       }  catch (Exception ex) {
         
                         System.out.println("" + ex.toString());
                        }
            
                   }
             }

     public void mudacorAdicionarEntered(){
         buttonAdicionarStockNaTabela.setStyle("-fx-background-color: #FFF");
         buttonAdicionarStockNaTabela.setStyle("-fx-background-radius: 13");
    }
    
     public void mouseexitbuttonAdicionar(){
       buttonAdicionarStockNaTabela.setStyle("-fx-background-color: #FFF");
       buttonAdicionarStockNaTabela.setStyle("-fx-background-radius: 13");
   }
    
     public void mudacorcancel(){
          buttonCancelar.setStyle("-fx-background-color: #FFF");
       buttonCancelar.setStyle("-fx-background-radius: 13");
    }
     
     public void mouseexitbuttonCancel(){
       buttonCancelar.setStyle("-fx-background-color: #FFF");
       buttonCancelar.setStyle("-fx-background-radius: 13");
   }
   
}
