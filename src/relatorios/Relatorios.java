/*     */ package relatorios;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import jdbc.ConnectionFactory;
/*     */ import net.sf.jasperreports.engine.JRException;
/*     */ import util.ReportUtils;
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
/*     */ public class Relatorios
/*     */ {
/*     */   public void abrirRelatorioVendasHoje() {
/*  42 */     InputStream inputStream = getClass().getResourceAsStream("TodaySales.jasper");
/*     */     
/*  44 */     Map<Object, Object> parametros = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  51 */       ReportUtils.openReport("TodaySales", inputStream, parametros, ConnectionFactory.getSakilaConnection());
/*     */ 
/*     */     
/*     */     }
/*  55 */     catch (SQLException exc) {
/*     */       
/*  57 */       exc.printStackTrace();
/*  58 */     } catch (JRException exc) {
/*     */       
/*  60 */       exc.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void abrirRelatorioVendasPorData(Date dataV) {
/*  68 */     InputStream inputStream = getClass().getResourceAsStream("VendasData.jasper");
/*  69 */     Map<Object, Object> parametros = new HashMap<Object, Object>();
/*  70 */     parametros.put("datavenda", dataV);
/*     */ 
/*     */     
/*     */     try {
/*  74 */       ReportUtils.openReport("VendasData", inputStream, parametros, ConnectionFactory.getSakilaConnection());
/*     */     
/*     */     }
/*  77 */     catch (SQLException exc) {
/*     */       
/*  79 */       exc.printStackTrace();
/*  80 */     } catch (JRException exc) {
/*     */       
/*  82 */       exc.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ImprimirRecibo() {
/*  92 */     InputStream inputStream = getClass().getResourceAsStream("Receipt.jasper");
/*     */     
/*  94 */     Map<Object, Object> parametros = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 100 */       ReportUtils.openReport("Receipt", inputStream, parametros, ConnectionFactory.getSakilaConnection());
/*     */ 
/*     */     
/*     */     }
/* 104 */     catch (SQLException exc) {
/*     */       
/* 106 */       exc.printStackTrace();
/* 107 */     } catch (JRException exc) {
/*     */       
/* 109 */       exc.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void abrirRelatorioProdutosMaisVendidos() {
/* 119 */     InputStream inputStream = getClass().getResourceAsStream("MostSoldProducts.jasper");
/*     */     
/* 121 */     Map<Object, Object> parametros = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 128 */       ReportUtils.openReport("MostSoldProducts", inputStream, parametros, ConnectionFactory.getSakilaConnection());
/*     */ 
/*     */     
/*     */     }
/* 132 */     catch (SQLException exc) {
/*     */       
/* 134 */       exc.printStackTrace();
/* 135 */     } catch (JRException exc) {
/*     */       
/* 137 */       exc.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\relatorios\Relatorios.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */