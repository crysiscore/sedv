/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.DetalhesVenda;
import BussinessLogic.Produto;
import BussinessLogic.StockLevel;
import BussinessLogic.Usuario;
import BussinessLogic.Venda;
import DataAcessLayer.ProdutoDAO;
import DataAcessLayer.VendaDao;
import Model.DetalhesVendaModel;
import Model.VendaModel;
import Service.ProdutosServicos;
import Service.UsuarioServicos;
import Service.VendaServicos;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
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
 * @author Neusia
 */
public class VendaController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private AnchorPane anchorPane1;
    
    @FXML
    private Button btnAdicionarProduto;
    
    @FXML
    private Button btnCancelarVenda;
    
    @FXML
    private Button btnFinalizarVenda;
    
    @FXML
    private Button btnCalcular;
    
    @FXML
    private Button btnRemoverProduto;
    
    @FXML
    private Button btnLista_Vendas;

    
    @FXML
    private Pane buttonCancelaVenda;
    
    @FXML
    private Label labelCash;
    
    @FXML
    private Label labelDesconto;
    
    @FXML
    private Label labelFuncionario;
    
    @FXML
    private Label labelSubtotal;
    
    @FXML
    private Label labelTotal;
    
    @FXML
    private Label labelUsuario;
    
    @FXML
    private Label labelNomeUsuario;
    
    @FXML
    private Label labelname;

    @FXML
    private Label labelprice;

    @FXML
    private Label labelSub;
    @FXML
    private Label labelCod;
    @FXML
    private TextField textFieldQuantty;
    @FXML
    private DatePicker DatePickerDATA;
    
    @FXML
    private TableView<DetalhesVenda> tableViewListaProdutos;
    @FXML
    private TableColumn<DetalhesVenda, String> tableColumnNome;
    
    @FXML
    private TableColumn<DetalhesVenda, Double> tableColumnPreco;
    
    @FXML
    private TableColumn<DetalhesVenda, Double> tableColumnQuantidade;
    
    @FXML
    private TableColumn<DetalhesVenda, Double> tableColumnSubtotal;
    
    @FXML
    private TextField textFieldPesquisaProdutos1;
   
    
    @FXML
    private TextField textfieldNomeCliente;
    
    @FXML
    private TextField textfieldNuitCliente;
    
    @FXML
    private TextField textfieldPago;
    
    @FXML
    private ImageView ImageViewCard;
    
    @FXML
    private ImageView ImageViewCash;
    
    @FXML
    private ImageView ImageViewMpesa;
    
    @FXML
    private ImageView imageviewFotoProduto;
    
    @FXML
    private ComboBox<String> comboBoxFormaPagamento;
    
    Produto produto;
    DetalhesVenda DVenda;
    Venda venda;
    Usuario usuario;
    UsuarioServicos usuarioServicos;
    ProdutosServicos servicoProdutos;
    private List<Produto> listProdutos;
    ObservableList <Produto> produtoObservableList =FXCollections.observableArrayList();
    private DetalhesVendaModel detalhesVendaModel = new DetalhesVendaModel();
    private VendaModel vendaModel = new VendaModel();
    
    ObservableList<DetalhesVenda> detalhesVenda = FXCollections.observableArrayList();
    
    public void initModel(DetalhesVendaModel model) {
        if (this.detalhesVendaModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.detalhesVendaModel = model;
    }
    
    public void initModel(VendaModel model) {
        if (this.detalhesVendaModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.vendaModel = model;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldQuantty.setVisible(false);
        labelprice.setVisible(false);
        labelCod.setVisible(false);
        labelSub.setVisible(false);
        labelname.setVisible(false);
         tableViewListaProdutos.setEditable(true);
        btnCalcular.setVisible(false);
        DatePickerDATA.setDisable(true);
        DatePickerDATA.setVisible(false);
        labelCash.textProperty().bind(textfieldPago.textProperty());
        comboBoxFormaPagamento.setItems(FXCollections.observableArrayList(
                "MPESA", "CASH", "CARTÃO DE CRÉDITO"));
        // TODO
        this.tableColumnNome.setCellValueFactory(new PropertyValueFactory<DetalhesVenda, String>("Nome_Produto"));
        this.tableColumnPreco.setCellValueFactory(new PropertyValueFactory<DetalhesVenda, Double>("Preco"));

        tableColumnQuantidade.getTableView().setEditable(true);
        tableColumnQuantidade.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        tableColumnQuantidade.setOnEditCommit(event -> {
            
            DetalhesVenda dv = event.getRowValue();
            dv.setQuantidade(event.getNewValue()); // Update the model directly
            Double subtotal1 = dv.getPreco() * dv.getQuantidade();
            dv.setSubtotal(subtotal1);
            
            Double somaSubtotal = 0.0;
            ObservableList<DetalhesVenda> dt = tableViewListaProdutos.getItems();
            tableColumnSubtotal = (TableColumn<DetalhesVenda, Double>) tableViewListaProdutos.getColumns().get(3);
            for (int i = 0; i < dt.size(); i++) {
                somaSubtotal += tableColumnSubtotal.getCellData(dt.get(i));
                tableViewListaProdutos.refresh();
            }

            labelSubtotal.setText(somaSubtotal.toString());
            labelTotal.setText(somaSubtotal.toString());
            textfieldPago.setText(labelTotal.getText());
 

            
        });
    
            
             

         this.tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<DetalhesVenda, Double>("Quantidade"));

        
        this.tableColumnSubtotal.setCellValueFactory(new PropertyValueFactory<DetalhesVenda, Double>("Subtotal"));
        detalhesVenda = detalhesVendaModel.getDetalhesVendaList();
        
        tableViewListaProdutos.setItems(detalhesVenda);

        
        
        // Add a change listener to the data list
        detalhesVenda.addListener((ListChangeListener<DetalhesVenda>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    Double somaSubtotal = 0.0;
                    ObservableList<DetalhesVenda> dt = tableViewListaProdutos.getItems();
                    tableColumnSubtotal = (TableColumn<DetalhesVenda, Double>) tableViewListaProdutos.getColumns().get(3);
                    for (int i = 0; i < dt.size(); i++) {
                        somaSubtotal += tableColumnSubtotal.getCellData(dt.get(i));
                    }

                    labelSubtotal.setText(somaSubtotal.toString());
                    labelTotal.setText(somaSubtotal.toString());
                    textfieldPago.setText(labelTotal.getText());

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

        this.textFieldPesquisaProdutos1.textProperty().addListener((observable, oldValue, newValue) -> {
            // Verifica se o novo valor inserido no TextField é diferente de vazio
            if (!newValue.isEmpty()) {
                scheduleButtonAction(); //quando quiser que o botao acione rapido, é so comentar essa parte e descomentar a parte a seguir
              // btnCalcular.fire(); 

            }
        });
    }
    
     private void scheduleButtonAction() {
        // Cria um executor com apenas uma thread para agendar tarefas
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // Agenda a tarefa de acionar o botão após 2 segundos
        executor.schedule(() -> {
            Platform.runLater(() -> btnCalcular.fire());
            executor.shutdown(); // Encerra o executor após a tarefa ser executada
        }, 1, TimeUnit.SECONDS);
    }

    @FXML
    public void adicionarprodutonatabela() {
        textFieldQuantty.setText("1");
        try {
            String codProduto = this.textFieldPesquisaProdutos1.getText();
            if (textFieldPesquisaProdutos1.getText().isEmpty()) {

               // JOptionPane.showMessageDialog(null, "Introduza o codigo do produto!");

            } else {
                servicoProdutos = new ProdutosServicos();
                Produto selectedProduto = servicoProdutos.getDetalhesProduto(Integer.parseInt(codProduto));

                if (selectedProduto == null) {
                    // O produto não foi encontrado no banco de dados
                    //JOptionPane.showMessageDialog(null, "O produto não foi encontrado!");
                    handleMenuAlert8();
                    limparcampos();

                    usuarioServicos = new UsuarioServicos();
                    // Produto selectedProduto = new Produto();
                    // Stock selectedProduto1  = new Stock();    

                } else if (selectedProduto.getStock().unidades_stock < Double.parseDouble(textFieldQuantty.getText())) {
                    handleMenuAlert4();

                } else {

                    this.detalhesProdutos(selectedProduto);

                }

            }

        } catch (Exception ex) {
            System.out.println("" + ex + ex.getLocalizedMessage());
            System.out.println("" + ex.toString());
        }
    }



    public void detalhesProdutos(Produto produto) throws SQLException {

        this.produto = produto;
        
        Double preco = produto.getPreco_unitario();
        String nome = produto.getNome();
        Double stock = produto.getStock().getUnidades_stock();
        Double quanty=Double.parseDouble(textFieldQuantty.getText());
        Double subt=preco  * quanty;
        labelprice.setText(produto.getPreco_unitario().toString());
        labelCod.setText(produto.getCod_produto().toString());
        labelSub.setText(subt.toString());
        labelname.setText(nome);
        
        DetalhesVenda DV = new DetalhesVenda(Integer.parseInt(labelCod.getText()),
                Double.parseDouble(labelprice.getText()),
                Double.parseDouble(textFieldQuantty.getText()), Double.parseDouble(labelSub.getText()),
                labelname.getText());
        detalhesVendaModel.addStock(DV);
            
        textFieldPesquisaProdutos1.clear();
               // limparcampos();
              
    }
        
    public void limparcampos(){
        textFieldPesquisaProdutos1.clear();
              labelSubtotal.setText("");
            labelTotal.setText("");
            textfieldPago.setText("");
 
        
    }
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;
        
        this.labelFuncionario.setText(usuario.getNome());
        this.labelFuncionario.setVisible(true);
        this.labelNomeUsuario.setText(usuario.getCod_Funcionario().toString());
        this.labelNomeUsuario.setVisible(false);
    }
    
    @FXML
    public void handleMenuItemAdicionarProduto() throws IOException {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/BuscaProdutoVenda.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            BuscaProdutoVendaController buscaProdutoVendaController = loader.<BuscaProdutoVendaController>getController();
            
            usuarioServicos = new UsuarioServicos();
            
            buscaProdutoVendaController.receberdadosUsuario(usuario);
            buscaProdutoVendaController.initModel(detalhesVendaModel);

            // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Adicionar Produto");
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
    
    @FXML
    public void ButtonCalcular() {
        
        Double somaSubtotal = 0.0;
        ObservableList<DetalhesVenda> dt = tableViewListaProdutos.getItems();
        tableColumnSubtotal = (TableColumn<DetalhesVenda, Double>) tableViewListaProdutos.getColumns().get(3);
        for (int i = 0; i < dt.size(); i++) {
            somaSubtotal += tableColumnSubtotal.getCellData(dt.get(i));
        }
        labelSubtotal.setText(somaSubtotal.toString());
        labelTotal.setText(somaSubtotal.toString());
        textfieldPago.setText(labelTotal.getText());
        
    }
    
    @FXML
    public void handleMenuItemRegistarVenda() throws IOException, SQLException {
        VendaDao dao = new VendaDao();
        
        verificadados();
        DatePickerDATA.setValue(LocalDate.now());
        
        if(textfieldNomeCliente.getText().isEmpty() && textfieldNuitCliente.getText().isEmpty()){
        Date Data_Venda = java.sql.Date.valueOf(DatePickerDATA.getValue());
        Double Total = Double.parseDouble(labelTotal.getText());
        Integer Cod_Usuario = Integer.parseInt(labelNomeUsuario.getText());
        String Forma_Pagamento = comboBoxFormaPagamento.getSelectionModel().getSelectedItem();
        
        if (tableViewListaProdutos.getItems().isEmpty()) {
            handleMenuAlert();
            
        } else {
            
            int response = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja registrar A venda sem nome e sem nuit do cliente???", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (response == JOptionPane.YES_OPTION) {
                
                
                dao.RegistarDetalhesVenda(tableViewListaProdutos.getItems());
                //tableViewListaProdutos.getItems().clear();
                Venda venda = new Venda();
                venda.setData_Venda(Data_Venda);
                venda.setTotal_Venda(Total); 
                venda.setUsuario_Cod_Funcionario(Cod_Usuario);
                venda.setForma_Pagamento(Forma_Pagamento);
              
                
                dao.RegistarVendasemnuitesemnome(venda);
                JOptionPane.showMessageDialog(null, "A venda foi Cadastrada com Sucesso!");
                tableViewListaProdutos.getItems().clear();
                limparcampos();
                 printRecibo();
                
            } else if (response == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "A venda não foi Cadastrada!");
                
            } else if (response == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Escolha uma das opções!");
            }
        }
        
        }else if(!textfieldNomeCliente.getText().isEmpty() && textfieldNuitCliente.getText().isEmpty()) {
         JOptionPane.showMessageDialog(null, "Introduza o Nuit também ou deixe os dois campos vazios!");
         
        }else if (textfieldNomeCliente.getText().isEmpty() && !textfieldNuitCliente.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Introduza o Nome do Cliente também ou deixe os dois campos vazios!");
        }else if(!textfieldNomeCliente.getText().isEmpty() && !textfieldNuitCliente.getText().isEmpty()){
                    
                        
        
        Date Data_Venda = java.sql.Date.valueOf(DatePickerDATA.getValue());
        Double Total = Double.parseDouble(labelTotal.getText());
        String Cliente = textfieldNomeCliente.getText();
        Integer Cod_Usuario = Integer.parseInt(labelNomeUsuario.getText());
        Integer Nuit = Integer.parseInt(textfieldNuitCliente.getText());
        String Forma_Pagamento = comboBoxFormaPagamento.getSelectionModel().getSelectedItem();
        
        if (tableViewListaProdutos.getItems().isEmpty()) {
            handleMenuAlert();
            
        } else {
            
            int response = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja registrar A venda???", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (response == JOptionPane.YES_OPTION) {
                
                
                dao.RegistarDetalhesVenda(tableViewListaProdutos.getItems());
                //tableViewListaProdutos.getItems().clear();
                Venda venda = new Venda();
                venda.setData_Venda(Data_Venda);
                venda.setTotal_Venda(Total);
                venda.setNome_cliente(Cliente);
                venda.setUsuario_Cod_Funcionario(Cod_Usuario);
                venda.setNuit_cliente(Nuit);
                venda.setForma_Pagamento(Forma_Pagamento);
                
                dao.RegistarVenda(venda);
                JOptionPane.showMessageDialog(null, "A venda foi Cadastrada com Sucesso!");
                tableViewListaProdutos.getItems().clear();
                 printRecibo();
                limparcampos();
                
            } else if (response == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "A venda não foi Cadastrada!");
                
            } else if (response == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Escolha uma das opções!");
            }
        }
  
        
    }
        
       
    }
    
    public void handleMenuAlert() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/alert.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //BuscaProdutosController buscaProdutosController= loader.getController();
            AlertController alertController = loader.<AlertController>getController();

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
     public void handleMenuAlert6() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/alert6.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //BuscaProdutosController buscaProdutosController= loader.getController();
            Alert6Controller alertController = loader.<Alert6Controller>getController();

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
    
    public void handleMenuAlert3() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/alert3.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //BuscaProdutosController buscaProdutosController= loader.getController();
            Alert3Controller alert3Controller = loader.<Alert3Controller>getController();

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
    
    public void handleMenuAlert4() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/alert4.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //BuscaProdutosController buscaProdutosController= loader.getController();
            Alert4Controller alert4Controller = loader.<Alert4Controller>getController();

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
    
        public void handleMenuAlert8() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/alert8.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //BuscaProdutosController buscaProdutosController= loader.getController();
            Alert8Controller alert8Controller = loader.<Alert8Controller>getController();

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
    
    public boolean verificadados() {
        boolean fds = false;
        
        if (this.comboBoxFormaPagamento.getSelectionModel().isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "SELECIONE A FORMA DE PAGAMENTO!");
            
            fds = true;
            
        } else if (this.labelNomeUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "NOME DO USUARIO EM FALTA! ");
            fds = true;
            
        } else if (this.labelTotal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "TOTAL DA VENDA EM FALTA! ");
            fds = true;
            
        }
        
        return fds;
    }
    
    public void CancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) buttonCancelaVenda.getScene().getWindow();
        stage.close();
    }
    
    
    public void handleMenuItemListaVendas() {

        try {
            String codUsuario = this.labelNomeUsuario.getText();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/Lista_Venda.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            Lista_VendaController listaVendaController = loader.<Lista_VendaController>getController();
            
            usuarioServicos = new UsuarioServicos();
            Usuario selectedUsuario = new Usuario();
            selectedUsuario = usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
            listaVendaController.receberdadosUsuario(selectedUsuario);
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Lista de Vendas");
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
    
    
     @FXML
    void removerProdutodaTabela() {

        int response = JOptionPane.showConfirmDialog(null, "O Produto foi selecionado???", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {

            DetalhesVenda selecionada = tableViewListaProdutos.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                int selectedID = tableViewListaProdutos.getSelectionModel().getSelectedIndex();
                tableViewListaProdutos.getItems().remove(selectedID);
                 Double somaSubtotal = 0.0;
            ObservableList<DetalhesVenda> dt = tableViewListaProdutos.getItems();
            tableColumnSubtotal = (TableColumn<DetalhesVenda, Double>) tableViewListaProdutos.getColumns().get(3);
            for (int i = 0; i < dt.size(); i++) {
                somaSubtotal += tableColumnSubtotal.getCellData(dt.get(i));
                tableViewListaProdutos.refresh();
            }

            labelSubtotal.setText(somaSubtotal.toString());
            labelTotal.setText(somaSubtotal.toString());
            textfieldPago.setText(labelTotal.getText());

                JOptionPane.showMessageDialog(null, "O Produto foi removido!");
            } else {
                JOptionPane.showMessageDialog(null, "O Item não foi selecionado!");
            }

        } else if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Selecione o Produto se desejar removê-lo!");

        } else if (response == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "Escolha uma das opções!");
        }

      

     }
    
        
       public void printRecibo(){
  
        
        try{
            
             JasperDesign jDesign = JRXmlLoader.load("src\\relatorios\\Recibo2.jrxml");
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, ConnectionFactory.getSakilaConnection());
            
            JasperViewer viewer = new JasperViewer(jPrint, false);
            
            viewer.setTitle("Lista de Produtos");
            viewer.show();
            
        }catch(Exception e){}
    }
        
}
