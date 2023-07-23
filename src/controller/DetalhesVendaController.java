/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.DetalhesVenda;
import BussinessLogic.Usuario;
import BussinessLogic.Venda;
import Model.DetalhesVendaModel;
import Service.UsuarioServicos;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
 * @author Neusia Hilario
 */
public class DetalhesVendaController implements Initializable {
    @FXML
    private AnchorPane anchorPane;

   @FXML
    private Button btnCancel;;

    @FXML
    private Label labelCodProduto;

    @FXML
    private Label labelFuncionario;

    @FXML
    private Label labelIDFuncionario;
    
    @FXML
    private Label labelCodProd;
    
    @FXML
    private Label labelDetalheVenda;
    
    @FXML
    private Label labelCliente;
    
    @FXML
    private Label labelTotal;

    
    @FXML
    private TableView<DetalhesVenda> tableViewListaProdutos;
    
    @FXML
    private TableColumn<DetalhesVenda, Integer> columnCodVenda;

    @FXML
    private TableColumn<DetalhesVenda, String> tableColumnNome;

    @FXML
    private TableColumn<DetalhesVenda, Double> tableColumnPreco;

    @FXML
    private TableColumn<DetalhesVenda, Double> tableColumnQUantidade;

    @FXML
    private TableColumn<DetalhesVenda, Double> tableColumnsubtotal;

    
    
    UsuarioServicos usuarioServicos;
    private DetalhesVendaModel detalhesVendaModel = new DetalhesVendaModel();
    ObservableList<DetalhesVenda> detalhesVenda = FXCollections.observableArrayList();
    Usuario usuario;
    Venda venda;
    /**
     * Initializes the controller class.
     */
    
    public void initModel(DetalhesVendaModel model) {
        if (this.detalhesVendaModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.detalhesVendaModel = model;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
        columnCodVenda.setCellValueFactory(new PropertyValueFactory<>("Venda_Cod_venda"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("Nome_Produto"));
        tableColumnQUantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("Preco"));
        tableColumnsubtotal.setCellValueFactory(new PropertyValueFactory<>("Subtotal"));
        

        detalhesVenda = detalhesVendaModel.getDetalhesVendaList();
        
        tableViewListaProdutos.setItems(detalhesVenda);
 
           // Add a change listener to the data list
        detalhesVenda.addListener((ListChangeListener<DetalhesVenda>) change -> {

            while (change.next()) {
                if (change.wasAdded()) {
                    Double somaSubtotal = 0.0;
                    ObservableList<DetalhesVenda> dt = tableViewListaProdutos.getItems();
                    tableColumnsubtotal = (TableColumn<DetalhesVenda, Double>) tableViewListaProdutos.getColumns().get(4);
                    for (int i = 0; i < dt.size(); i++) {
                        somaSubtotal += tableColumnsubtotal.getCellData(dt.get(i));
                    }

                    labelTotal.setText(somaSubtotal.toString());
                   

                    // System.out.println("Items added: " + change.getAddedSubList());
                }
                if (change.wasRemoved()) {
                    //System.out.println("Items removed: " + change.getRemoved());
                }
                if (change.wasUpdated()) {
                    // System.out.println("Items updated: " + change.getList().subList(change.getFrom(), change.getTo()));
                }
                // Handle other change types if needed (replaced, permutated)
            }
        });

    
    }    
    
       public Usuario getUsuario() {
        return usuario;
    }
     public void receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;
        
        this.labelFuncionario.setText(usuario.getNome());
        this.labelFuncionario.setVisible(true);
        this.labelIDFuncionario.setText(usuario.getCod_Funcionario().toString());
        this.labelIDFuncionario.setVisible(false);
    }
     
       public void CancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
       
       
       public void ReceberDadosVenda(List<Venda> vendas)   {
        
         for (Venda venda : vendas) {
         labelDetalheVenda.setText(venda.getCod_Venda().toString());
        
         DetalhesVenda DV = new DetalhesVenda(venda.getDetalhesVenda().getProduto_Cod_produto(),venda.getCod_Venda(),
                venda.getDetalhesVenda().getPreco(),
                venda.getDetalhesVenda().getQuantidade(), venda.getDetalhesVenda().getSubtotal(),
                venda.getDetalhesVenda().getNome_Produto());
        detalhesVendaModel.addStock(DV);
}
}
       
        public void ReceberDadoVenda(Venda venda)   {
            this.labelCliente.setText(venda.getNome_cliente());
            
        }
        
        
        public void print() {
            
            int codigoVenda = Integer.parseInt(labelDetalheVenda.getText());
    try {
        JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\Neusia Hilario\\Documents\\NetBeansProjects\\sedv\\src\\relatorios\\Detalhes_Venda.jrxml");

        JasperReport jReport = JasperCompileManager.compileReport(jDesign);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Cod_Venda", codigoVenda);  // Defina o valor do parâmetro Cod_Venda

        JasperPrint jPrint = JasperFillManager.fillReport(jReport, parametros, ConnectionFactory.getSakilaConnection());

        JasperViewer viewer = new JasperViewer(jPrint, false);

        viewer.setTitle("Detalhes de Venda");
        viewer.show();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
