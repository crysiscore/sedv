/*    */ package BussinessLogic;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Venda
/*    */ {
/*    */   private Integer Cod_Venda;
/*    */   public Date Data_Venda;
/*    */   public Double Total_Venda;
/*    */   public String Nome_cliente;
/*    */   public Integer usuario_Cod_Funcionario;
/*    */   
/*    */   public Integer getCod_Venda() {
/* 22 */     return this.Cod_Venda;
/*    */   }
/*    */   
/*    */   public void setCod_Venda(Integer Cod_Venda) {
/* 26 */     this.Cod_Venda = Cod_Venda;
/*    */   }
/*    */   
/*    */   public Date getData_Venda() {
/* 30 */     return this.Data_Venda;
/*    */   }
/*    */   
/*    */   public void setData_Venda(Date Data_Venda) {
/* 34 */     this.Data_Venda = Data_Venda;
/*    */   }
/*    */   
/*    */   public String getNome_cliente() {
/* 38 */     return this.Nome_cliente;
/*    */   }
/*    */   
/*    */   public void setNome_cliente(String Nome_cliente) {
/* 42 */     this.Nome_cliente = Nome_cliente;
/*    */   }
/*    */   
/*    */   public Double getTotal_Venda() {
/* 46 */     return this.Total_Venda;
/*    */   }
/*    */   
/*    */   public void setTotal_Venda(Double Total_Venda) {
/* 50 */     this.Total_Venda = Total_Venda;
/*    */   }
/*    */   
/*    */   public Integer getUsuario_Cod_Funcionario() {
/* 54 */     return this.usuario_Cod_Funcionario;
/*    */   }
/*    */   
/*    */   public void setUsuario_Cod_Funcionario(Integer usuario_Cod_Funcionario) {
/* 58 */     this.usuario_Cod_Funcionario = usuario_Cod_Funcionario;
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Sistema de vendas\SysVendas.jar!\BussinessLogic\Venda.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */