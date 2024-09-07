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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
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
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    private TableColumn<DetalhesVenda, Double> tableColumnStock;
    
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
        // Vincula a propriedade empty da tabela às propriedades disable dos botões

      
        textFieldQuantty.setVisible(false);
        labelprice.setVisible(false);
        labelCod.setVisible(false);
        labelSub.setVisible(false);
        labelname.setVisible(false);
         tableViewListaProdutos.setEditable(true);
        btnCalcular.setVisible(false);
        //DatePickerDATA.setDisable(true);
        //DatePickerDATA.setVisible(false);
        labelCash.textProperty().bind(textfieldPago.textProperty());
        comboBoxFormaPagamento.setItems(FXCollections.observableArrayList(
                "MPESA", "CASH", "CARTÃO DE CRÉDITO"));
        // TODO
        this.tableColumnNome.setCellValueFactory(new PropertyValueFactory<DetalhesVenda, String>("Nome_Produto"));
        this.tableColumnPreco.setCellValueFactory(new PropertyValueFactory<DetalhesVenda, Double>("Preco"));
        this.tableColumnStock.setCellValueFactory(new PropertyValueFactory<DetalhesVenda, Double>("Stock"));

        tableColumnQuantidade.getTableView().setEditable(true);
        tableColumnQuantidade.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        tableColumnQuantidade.setOnEditCommit(event -> {
             DetalhesVenda dv = event.getRowValue();
            double novoValor = event.getNewValue();

            // Verifica se o novo valor é menor ou igual ao estoque existente
            if (novoValor <= dv.getStock()) {
                dv.setQuantidade(novoValor);
            } else {
                // Exibe uma mensagem de erro caso o novo valor seja maior que o estoque
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("A quantidade introduzida é maior do que o estoque disponível!");
                alert.showAndWait();
                // Rejeita a edição
                dv.setQuantidade(event.getOldValue());
            }
      
    
           
          //  dv.setQuantidade(event.getNewValue()); // Update the model directly
            Double subtotal1 = dv.getPreco() * dv.getQuantidade();
            dv.setSubtotal(subtotal1);
            
            Double somaSubtotal = 0.0;
            ObservableList<DetalhesVenda> dt = tableViewListaProdutos.getItems();
            tableColumnSubtotal = (TableColumn<DetalhesVenda, Double>) tableViewListaProdutos.getColumns().get(4);
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
       
        BooleanProperty tabelaVazia = new SimpleBooleanProperty(true);

       // Vincula a propriedade empty da tabela às propriedades disable dos botões
        btnRemoverProduto.disableProperty().bind(tabelaVazia);
        btnFinalizarVenda.disableProperty().bind(tabelaVazia);

       // Adicione um listener à lista detalhesVenda para atualizar a propriedade tabelaVazia
        detalhesVenda.addListener((ListChangeListener<DetalhesVenda>) change -> {
            tabelaVazia.set(detalhesVenda.isEmpty());
        });
      tableViewListaProdutos.setItems(detalhesVenda);


        // Add a change listener to the data list
        detalhesVenda.addListener((ListChangeListener<DetalhesVenda>) change -> {
            while (change.next()) {
                
                if (change.wasAdded()) {
                    Double somaSubtotal = 0.0;
                    ObservableList<DetalhesVenda> dt = tableViewListaProdutos.getItems();
                    tableColumnSubtotal = (TableColumn<DetalhesVenda, Double>) tableViewListaProdutos.getColumns().get(4);
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

            } else {
                servicoProdutos = new ProdutosServicos();
                Produto selectedProduto = servicoProdutos.getDetalhesProdutoComCodigoManual(codProduto);

                if (selectedProduto == null) {
                
                    handleMenuAlert8();
                    limparcampos();

                    usuarioServicos = new UsuarioServicos();
                  
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
                Double.parseDouble(labelprice.getText()),Double.parseDouble(produto.stock.getUnidades_stock().toString()),
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
            dialogStage.setTitle("ADICÃO DE PRODUTO");
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

    
    public void showMessageDialog(String message, String title) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(message);
        
        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

   @FXML
public void handleMenuItemRegistarVenda() throws IOException, SQLException {

    if (verificadados()) {
        // Lógica se verificadados() retornar verdadeiro
    } else {
        VendaDao dao = new VendaDao();

        // Obtém a data selecionada no DatePicker
        LocalDate dataSelecionada = DatePickerDATA.getValue();

        // Verifica se a data não foi selecionada e se os campos de cliente estão vazios
        if (dataSelecionada == null && textfieldNuitCliente.getText().isEmpty() && textfieldNomeCliente.getText().isEmpty()) {
            // Se a data não foi selecionada, usa a data atual
            dataSelecionada = LocalDate.now();
            DatePickerDATA.setValue(dataSelecionada); // Opcional: Atualiza o DatePicker para mostrar a data atual
        }

        // Converte a data selecionada para o formato java.sql.Date
        Date Data_Venda = java.sql.Date.valueOf(dataSelecionada);

        // Continua com o processamento da venda
        Double Total = Double.parseDouble(labelTotal.getText());
        Integer Cod_Usuario = Integer.parseInt(labelNomeUsuario.getText());
        String Forma_Pagamento = comboBoxFormaPagamento.getSelectionModel().getSelectedItem();

        // Verifica se a tabela de produtos está vazia
        if (tableViewListaProdutos.getItems().isEmpty()) {
            DialogUtil.showErrorMessage("SELECIONE UM PRODUTO!", "AVISO");
        } 
        // Verifica se a forma de pagamento foi selecionada
        else if (this.comboBoxFormaPagamento.getSelectionModel().isEmpty()) {
            DialogUtil.showInfoMessage("SELECIONE A FORMA DE PAGAMENTO!", "Info");
        } 
        else {
            try {
                // Registra os detalhes da venda
                dao.RegistarDetalhesVenda(tableViewListaProdutos.getItems());

                // Cria um objeto Venda e define seus atributos
                Venda venda = new Venda();
                venda.setData_Venda(Data_Venda);
                venda.setTotal_Venda(Total);
                venda.setUsuario_Cod_Funcionario(Cod_Usuario);
                venda.setForma_Pagamento(Forma_Pagamento);

                // Verifica se os campos de nome do cliente e nuit estão vazios para determinar o registro correto
                if (textfieldNomeCliente.getText().isEmpty() && textfieldNuitCliente.getText().isEmpty()) {
                    // Registra venda sem cliente e nuit
                    ResultSet rs = dao.RegistarVendasemnuitesemnome(venda);
                } else {
                    // Adiciona dados do cliente na venda
                    String Cliente = textfieldNomeCliente.getText();
                    Integer Nuit = Integer.parseInt(textfieldNuitCliente.getText());
                    venda.setNome_cliente(Cliente);
                    venda.setNuit_cliente(Nuit);

                    // Registra venda com cliente e nuit
                    dao.RegistarVenda(venda);
                }

                // Limpa a tabela de produtos e campos após o registro
                tableViewListaProdutos.getItems().clear();
                limparcampos();
                DialogUtil.showInfoMessage("A venda foi Cadastrada com Sucesso!", "Info");

                // TODO: printRecibo();
                
            } catch (Exception e) {
                DialogUtil.showErrorMessage("Erro ao registar venda: " + e.getMessage(), "ERRO");
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
            
          
            DialogUtil.showInfoMessage("SELECIONE A FORMA DE PAGAMENTO!", "Info");

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
            dialogStage.setTitle("LISTA DE VENDAS");
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

        boolean response = ConfirmDialog.show("Confirmacao", "", " Deseja Remover o  Produto?");

        if (response) {

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

            }
        }


     }
    

       public void printRecibo(){
  
        
        try{
            
            JasperDesign jDesign = JRXmlLoader.load("src\\relatorios\\Recibo2.jrxml");
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, ConnectionFactory.getSakilaConnection());
            
            JasperViewer viewer = new JasperViewer(jPrint, false);
            
            viewer.setTitle("Recibo");
            viewer.show();
            
        }catch(Exception e){DialogUtil.showErrorMessage(" Erro  : "+ e.getMessage(), "ERRO");}
    }


}
