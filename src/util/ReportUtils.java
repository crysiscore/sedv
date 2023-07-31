     package util;
     
     import java.awt.Component;
     import java.io.InputStream;
     import java.sql.Connection;
     import java.util.Map;
     import javax.swing.JFrame;
     import net.sf.jasperreports.engine.JRDataSource;
     import net.sf.jasperreports.engine.JRException;
     import net.sf.jasperreports.engine.JasperFillManager;
     import net.sf.jasperreports.engine.JasperPrint;
     import net.sf.jasperreports.swing.JRViewer;
     
     
     
     
     
     
     public class ReportUtils
     {
       public static void openReport(String titulo, InputStream inputStream, Map parametros, Connection conexao) throws JRException {
       JasperPrint print = JasperFillManager.fillReport(inputStream, parametros, conexao);
     
     
         
       viewReportFrame(titulo, print);
       }
     
     

       
       public static void openReport(String titulo, InputStream inputStream, Map parametros, JRDataSource dataSource) throws JRException {
      JasperPrint print = JasperFillManager.fillReport(inputStream, parametros, dataSource);
     
         
      viewReportFrame(titulo, print);
       }
     
       
       private static void viewReportFrame(String titulo, JasperPrint print) {
      JRViewer viewer = new JRViewer(print);
         
      JFrame frameRelatorio = new JFrame(titulo);
         
      frameRelatorio.add((Component)viewer, "Center");
         
      frameRelatorio.setSize(500, 500);
         
      frameRelatorio.setExtendedState(6);
      frameRelatorio.setDefaultCloseOperation(2);
         
      frameRelatorio.setVisible(true);
       }
     }

