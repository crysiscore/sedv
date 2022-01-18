/*     */ package util;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.io.InputStream;
/*     */ import java.sql.Connection;
/*     */ import java.util.Map;
/*     */ import javax.swing.JFrame;
/*     */ import net.sf.jasperreports.engine.JRDataSource;
/*     */ import net.sf.jasperreports.engine.JRException;
/*     */ import net.sf.jasperreports.engine.JasperFillManager;
/*     */ import net.sf.jasperreports.engine.JasperPrint;
/*     */ import net.sf.jasperreports.swing.JRViewer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReportUtils
/*     */ {
/*     */   public static void openReport(String titulo, InputStream inputStream, Map parametros, Connection conexao) throws JRException {
/*  65 */     JasperPrint print = JasperFillManager.fillReport(inputStream, parametros, conexao);
/*     */ 
/*     */ 
/*     */     
/*  69 */     viewReportFrame(titulo, print);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void openReport(String titulo, InputStream inputStream, Map parametros, JRDataSource dataSource) throws JRException {
/* 111 */     JasperPrint print = JasperFillManager.fillReport(inputStream, parametros, dataSource);
/*     */ 
/*     */     
/* 114 */     viewReportFrame(titulo, print);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void viewReportFrame(String titulo, JasperPrint print) {
/* 132 */     JRViewer viewer = new JRViewer(print);
/*     */     
/* 134 */     JFrame frameRelatorio = new JFrame(titulo);
/*     */     
/* 136 */     frameRelatorio.add((Component)viewer, "Center");
/*     */     
/* 138 */     frameRelatorio.setSize(500, 500);
/*     */     
/* 140 */     frameRelatorio.setExtendedState(6);
/* 141 */     frameRelatorio.setDefaultCloseOperation(2);
/*     */     
/* 143 */     frameRelatorio.setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar\\util\ReportUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */