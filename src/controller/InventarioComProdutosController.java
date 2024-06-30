/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BussinessLogic.Inventario;
import BussinessLogic.InventarioPrincipal;
import BussinessLogic.Produto;
import BussinessLogic.Stock;
import BussinessLogic.StockLevel;
import BussinessLogic.Usuario;
import BussinessLogic.Venda;
import DataAcessLayer.InventarioDAO;
import DataAcessLayer.ProdutoDAO;
import DataAcessLayer.StockDAO;
import DataAcessLayer.conexao;
import Model.DetalhesVendaModel;
import Model.InventarioModel;
import Service.ProdutosServicos;
import Service.UsuarioServicos;
import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author Neusia Hilario
 */
public class InventarioComProdutosController implements Initializable {
    
    
      @FXML
    private DatePicker DatePickerDATA;

    @FXML
    private AnchorPane anchorPane1;

    @FXML
    private Button btnAdicionarProduto;

    @FXML
    private Button btnActualiar;
   
    @FXML
    private Button Imprimir_folha_inventário;

    @FXML
    private Button btnRemover;

    @FXML
    private Pane buttonCancelaVenda;
    
    @FXML
    private Button buttonConcluir;
    
    @FXML
    private Button ButtonLista_Inventario;

    @FXML
    private Label labelCod;

    @FXML
    private Label labelFuncionario;

    @FXML
    private Label labelNomeUsuario;

    @FXML
    private Label labelSub;

    @FXML
    private Label labelname;

    @FXML
    private Label labelprice;
    
    @FXML
    private Label labelCodigoUser;
    @FXML
    private Label labelParcial;
  

      @FXML
    private Label labelNomeUser;
      
      @FXML
    private TableColumn<Inventario, Integer> tableColumnCodigoBD;


    @FXML
    private TableColumn<Inventario, String> tableColumnCodigo;

    @FXML
    private TableColumn<Inventario, Double> tableColumnDiferença;

    @FXML
    private TableColumn<Inventario, String> tableColumnNome;

    @FXML
    private TableColumn<Inventario, Double> tableColumnStcokExistente;

    @FXML
    private TableColumn<Inventario, Double> tableColumnStcokExistenteContada;

    @FXML
    private TableView<Inventario> tableViewListaProdutos;

    @FXML
    private TextField textFieldPesquisaProdutos1;
 
    UsuarioServicos usuarioServicos;

    Inventario inv;
    Stock stock;
    Usuario usuario;
    Produto produto;
    Venda venda;
   
    
    private InventarioModel inventarioModel= new InventarioModel();

    
    ProdutosServicos servicoProdutos;
   // ObservableList<Produto> produtoObservableList = FXCollections.observableArrayList();
    ObservableList<Inventario> iventario = FXCollections.observableArrayList();

     
    
    public void initModel(InventarioModel model) {
        if (this.inventarioModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.inventarioModel = model;
    }

     public Usuario getUsuario() {
        return usuario;
    }

    public void receberdadosUsuario(Usuario usuario) {
        this.usuario = usuario;

        this.labelCodigoUser.setText(usuario.getCod_Funcionario().toString());
        this.labelCodigoUser.setVisible(false);
        this.labelNomeUser.setText(usuario.getNome());

    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         DatePickerDATA.setVisible(false);
         labelParcial.setVisible(false);
        this.tableColumnCodigoBD.setCellValueFactory(new PropertyValueFactory<Inventario, Integer>("Cod_Produto"));
        this.tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<Inventario, String>("Cod_ProdutoManual"));
        this.tableColumnNome.setCellValueFactory(new PropertyValueFactory<Inventario, String>("Nome"));
        this.tableColumnStcokExistente.setCellValueFactory(new PropertyValueFactory<Inventario, Double>("Stock"));
     
       

        tableColumnStcokExistenteContada.getTableView().setEditable(true);
            tableColumnStcokExistenteContada.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            
              tableColumnStcokExistenteContada.setOnEditCommit(event -> {
            
               Inventario i = event.getRowValue();
            i.setQuantidade_Contada(event.getNewValue()); // Update the model directly
              Double diferenca = i.getStock() - i.getQuantidade_Contada();
            i.setDiferenca_Existente_Contada(diferenca);
             tableViewListaProdutos.refresh();
            
            
        
             });
            this.tableColumnStcokExistenteContada.setCellValueFactory(new PropertyValueFactory<Inventario, Double>("Quantidade_Contada"));

            this.tableColumnDiferença.setCellValueFactory(new PropertyValueFactory<Inventario, Double>("Diferenca_Existente_Contada"));
          
            iventario = inventarioModel.getInventarioList();
            
                 
        BooleanProperty tabelaVazia = new SimpleBooleanProperty(true);

       // Vincula a propriedade empty da tabela às propriedades disable dos botões
        btnActualiar.disableProperty().bind(tabelaVazia);
        Imprimir_folha_inventário.disableProperty().bind(tabelaVazia);
        btnRemover.disableProperty().bind(tabelaVazia);
     

       // Adicione um listener à lista detalhesVenda para atualizar a propriedade tabelaVazia
        iventario.addListener((ListChangeListener<Inventario>) change -> {
            tabelaVazia.set(iventario.isEmpty());
        });

            tableViewListaProdutos.setItems(iventario);

          
                tableViewListaProdutos.refresh();
            
        FilteredList<Inventario> filteredData =new FilteredList<> (iventario, b ->true);
                 
                 textFieldPesquisaProdutos1.textProperty().addListener((observable, oldValue, newValue) ->{
                 filteredData.setPredicate(inv -> {
                     if(newValue.isEmpty()|| newValue == null){
                         
                         return true;
                     }
                     
                     
                     String searchKeyword=newValue.toLowerCase();
                     
                    if(inv.getNome().toLowerCase().indexOf(searchKeyword)>-1){
                         return true;
                          }else if(inv.getCod_ProdutoManual().toLowerCase().indexOf(searchKeyword)>-1){
                         return true;
                                
                     }else
                         
                     return false;
                     
                 });
                 
             });
                 
                 SortedList <Inventario> sortdData = new SortedList <>(filteredData);
                 
                 sortdData.comparatorProperty().bind(tableViewListaProdutos.comparatorProperty());
                 
                 tableViewListaProdutos.setItems(sortdData);
            
             }
             
 
     
    



    @FXML
    public void handleMenuItemAdicionarProduto() throws IOException {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/InventarioParcial.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            InventarioParcialController inventarioParcialController = loader.<InventarioParcialController>getController();
            
            usuarioServicos = new UsuarioServicos();
            
            inventarioParcialController.receberdadosUsuario(usuario);
            inventarioParcialController.initModel(inventarioModel);

            // Criando um EstÃ¡gio de DiÃ¡logo (Stage Dialog)
            Stage dialogStage = new Stage();
            dialogStage.setTitle("ADIÇÃO DE PRODUTO");
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
        Inventario selecionada = tableViewListaProdutos.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            // Remove o item selecionado da lista iventario
            iventario.remove(selecionada);
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


    
    @FXML
    public void ActualizarStock() throws IOException, SQLException {

       StockDAO dao = new StockDAO();

        if (tableViewListaProdutos.getItems().isEmpty()) {
         //  handleMenuAlert();

     } else {

            int response = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja Actualizar e Gravar o Stock introduzido na tabela???", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                ObservableList <Inventario> stock = tableViewListaProdutos.getItems();
                
                // verificar se existe algum produto que cuja quantidade nao foi alterada
//                for (Inventario inventario : stock) {
//                    
//                    if(inventario.getQuantidade_Contada()==0.0){
//                          JOptionPane.showMessageDialog(null, "O produto: " + inventario.getNome() + " nao foi modificado! ") ;
//                                  break;
//                    }
//                    
//                }

               dao.ActualizarStock(tableViewListaProdutos.getItems());
          //  tableViewListaProdutos.getItems().clear();
                //JOptionPane.showMessageDialog(null, "O Stock foi actuaizado com Sucesso!");

            } else if (response == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "O Stock não foi actuaizado!");
                  return;
            } else if (response == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Escolha uma das opções!");
            }
            
            handleMenuItemRegistarInventario();
        }
    }
    
    
    
    
     
    @FXML
    public void handleMenuItemRegistarInventario() throws IOException, SQLException {
        InventarioDAO dao = new InventarioDAO();
        String Tipo_De_Iventario = inventarioModel.getTipoDeInventario();
       labelParcial.setText("Parcial");
        DatePickerDATA.setValue(LocalDate.now());
   
                        
        
        Date Data_Venda = java.sql.Date.valueOf(DatePickerDATA.getValue());
        Integer Cod_Usuario = Integer.parseInt(labelCodigoUser.getText());
        //String tipo_de_inventario = labelParcial.getText();
        
        if (tableViewListaProdutos.getItems().isEmpty()) {
            handleMenuAlert();
            
        } else {
            
           // int response = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja registrar o Inventário???", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            //if (response == JOptionPane.YES_OPTION) {
                
                
                dao.RegistarInventarioItem(tableViewListaProdutos.getItems());
                //tableViewListaProdutos.getItems().clear();
                InventarioPrincipal inventarioPrincipal = new InventarioPrincipal();
                inventarioPrincipal.setData(Data_Venda);
                inventarioPrincipal.setUser(usuario);
                inventarioPrincipal.setTipo_De_Inventario(Tipo_De_Iventario);
                
                
                dao.RegistarInventario(inventarioPrincipal);
                JOptionPane.showMessageDialog(null, "O Inventário foi Cadastrado com Sucesso!");
                
         
                
           // } else if (response == JOptionPane.NO_OPTION) {
               // JOptionPane.showMessageDialog(null, "O Inventário não foi Cadastrado!");
                
           // } else if (response == JOptionPane.CLOSED_OPTION) {
           //     JOptionPane.showMessageDialog(null, "Escolha uma das opções!");
           // }
        }
    tableViewListaProdutos.setItems(FXCollections.observableArrayList());

  
        
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
    
           public void SairButtonOnAction(ActionEvent event){
      Stage stage =(Stage)buttonConcluir.getScene().getWindow();
     
        stage.close();
       
    }
           
    public void handlePrintPre_Inventario() {
        try {
            // Obtenha a lista de itens da TableView
            ObservableList<Inventario> items = tableViewListaProdutos.getItems();

            // Crie uma lista para armazenar os códigos dos produtos
            List<Integer> codigosProdutos = new ArrayList<>();

            for (Inventario product : items) {
                int codigo = product.getCod_Produto();
                codigosProdutos.add(codigo);
            }

            // Carregue o design do relatório
            JasperDesign jDesign = JRXmlLoader.load("src\\relatorios\\Pre_Inventario_1.jrxml");

            // Compile o relatório
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);

            // Crie um mapa de parâmetros
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Cod_Produto", codigosProdutos);  // Defina a lista de códigos dos produtos como parâmetro

            // Preencha o relatório com os parâmetros
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, parametros, ConnectionFactory.getSakilaConnection());

            // Crie uma visualização do relatório
            JasperViewer viewer = new JasperViewer(jPrint, false);

            viewer.setTitle("Iventario");
            viewer.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @FXML
public void handlePrintPre_InventarioExcel() {
    try {
        // Obtenha a lista de itens da TableView
        ObservableList<Inventario> items = tableViewListaProdutos.getItems();

        // Crie um novo workbook do Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Pre_Inventario");

        // Crie um estilo de fonte com negrito
        Font font = workbook.createFont();
       // font.setBold(true);

        // Crie um estilo e atribua a fonte negrito a ele
        CellStyle boldStyle = workbook.createCellStyle();
        boldStyle.setFont(font);

        // Crie o cabeçalho da tabela no Excel e aplique o estilo negrito
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("CÓDIGO");
        headerRow.createCell(1).setCellValue("DESCRIÇÃO");
        headerRow.createCell(2).setCellValue("STOCK");
        headerRow.createCell(3).setCellValue("STOCK FÍSICO");
        headerRow.createCell(4).setCellValue("DIFERENÇA");

        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            headerRow.getCell(i).setCellStyle(boldStyle);
        }

        // Preencha os dados da TableView no Excel
        int rowNum = 2;
        for (Inventario product : items) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(product.getCod_Produto());
            row.createCell(1).setCellValue(product.getNome());
            row.createCell(2).setCellValue(product.getStock());
            // Adicione mais colunas conforme necessário
        }

        // Ajuste automático da largura das colunas com base no conteúdo
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Salve o workbook como um arquivo Excel
        String filePath = "C:\\sedv\\Relatorios\\Pre_Inventario.xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        // Abra o arquivo automaticamente usando a classe Desktop
        try {
            Desktop.getDesktop().open(new java.io.File(filePath));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível abrir automaticamente o arquivo. Por favor, encontre o arquivo em: " + filePath);
        }

        // Feche o workbook para liberar recursos
       // workbook.close();

        // Exiba um JOptionPane informando que o pré-inventário foi adicionado
        JOptionPane.showMessageDialog(null, "O pré-inventário foi adicionado ao arquivo: " + filePath);

    } catch (IOException e) {
        e.printStackTrace();
    }
}


 public void handleMenuItemListaInventario() {

        try {
            String codUsuario = this.labelNomeUsuario.getText();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TrickController.class.getResource("/Presentation/Lista_De_Inventario.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            Lista_De_InventarioController lista_De_InventarioController = loader.<Lista_De_InventarioController>getController();
            
            usuarioServicos = new UsuarioServicos();
            Usuario selectedUsuario = new Usuario();
           // selectedUsuario = usuarioServicos.getDetalhesUsuario(Integer.parseInt(codUsuario));
           // lista_De_InventarioController.receberdadosUsuario(selectedUsuario);
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Lista de Inventários");
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

