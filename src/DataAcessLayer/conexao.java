package DataAcessLayer;

import controller.TrickController;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;




 

public class conexao {

    private static String host;
    private static String port;
    private static String bd;
    private static String user;
    private static String password;

    public static String getBd() {
        return bd;
    }

    public static void setBd(String bd) {
        conexao.bd = bd;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        conexao.host = host;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        conexao.password = password;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        conexao.port = port;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        conexao.user = user;
    }


    public static PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    ReadWriteTextFile rwTextFile = new ReadWriteTextFile();

   // final static String ficheiro_gravado = "C:\\sedv\\credenciaisConn.txt";
    
    public static String getDiretorioFicheiro() {
        String osName = System.getProperty("os.name").toLowerCase();
        String ficheiroGravado;

        if (osName.contains("win")) {
            ficheiroGravado = "C:\\sedv\\credenciaisConn.txt";
        } else if (osName.contains("mac")) {
            ficheiroGravado = "/opt/sedv/credenciaisConn.txt";
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
            ficheiroGravado = "/home/sedv/credenciaisConn.txt";
        } else {
            ficheiroGravado = "Diretório não suportado para este sistema operativo.";
        }

        return ficheiroGravado;
    }
     final static String ficheiro_gravado = getDiretorioFicheiro();
    
   // String ficheiro_gravado = obterCaminhoDoArquivo();

    // definir uma lista de frutas
    List<String> credenciais;

    List<String> credenciaisConn;

    private List<String> LerDadosDeConexao(final String loction) {

        try {
            credenciaisConn = rwTextFile.readSmallTextFile(ficheiro_gravado);

        } catch (Exception e) {
            System.out.println("Ficheiro nao encontrado");
        }
        return credenciaisConn;
    }

    public conexao(String host, String port, String bd, String user, String password) {
        conexao.host = host;
        conexao.port = port;
        conexao.bd = bd;
        conexao.user = user;
        conexao.password = password;

    }

    public conexao() {
        // ler o ficheiro
        // extrair os valore lidos

        credenciaisConn = LerDadosDeConexao(ficheiro_gravado);
        // Lipara a textarea

        host = credenciaisConn.get(0).substring(5);
        port = credenciaisConn.get(1).substring(5);
        bd = credenciaisConn.get(2).substring(3);
        user = credenciaisConn.get(3).substring(9);
        password = credenciaisConn.get(4).substring(9);

        if (host.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Nome do servidor em falta!");
        } else if (port.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Numero da porta em falta");

        } else if (bd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome da base de dados em falta!");

        } else if (user.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Nome do User em falta!");
        } else if (password.isEmpty()) {

            JOptionPane.showMessageDialog(null, "password em falta");

        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + bd, user, password);
    }
}


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\DataAcessLayer\conexao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
