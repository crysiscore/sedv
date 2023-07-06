/*    */ package BussinessLogic;

import java.sql.Date;

/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 

/*    */ public class Venda {

    
      private Integer Cod_Venda;
/*    */   public Date Data_Venda;
/*    */   public Double Total_Venda;
/*    */   public String Nome_cliente;
           public Integer Nuit_cliente;
           public Usuario User;
           String usuario;
           public Integer usuario_Cod_Funcionario;
           public Produto Prod;
           public Double Desconto;
           public Double Iva;
           public String Forma_Pagamento;
           
           public DetalhesVenda detalhesVenda;

    public Venda(Integer Cod_Venda, Date Data_Venda, Double Total_Venda, String Nome_cliente, Integer Nuit_cliente, String usuario, String Forma_Pagamento) {
        this.Cod_Venda = Cod_Venda;
        this.Data_Venda = Data_Venda;
        this.Total_Venda = Total_Venda;
        this.Nome_cliente = Nome_cliente;
        this.Nuit_cliente = Nuit_cliente;
        this.usuario = usuario;
        this.Forma_Pagamento = Forma_Pagamento;
    }
           

        public Venda(Integer Cod_Venda, Date Data_Venda, Double Total_Venda, String Nome_cliente, Integer Nuit_cliente, Integer usuario_Cod_Funcionario, String Forma_Pagamento) {
        this.Cod_Venda = Cod_Venda;
        this.Data_Venda = Data_Venda;
        this.Total_Venda = Total_Venda;
        this.Nome_cliente = Nome_cliente;
        this.Nuit_cliente = Nuit_cliente;
        this.usuario_Cod_Funcionario = usuario_Cod_Funcionario;
        this.Forma_Pagamento = Forma_Pagamento;
    }
       
        public Venda(){
            
        }
 
           
           

    public DetalhesVenda getDetalhesVenda() {
        return detalhesVenda;
    }

    public void setDetalhesVenda(DetalhesVenda detalhesVenda) {
        this.detalhesVenda = detalhesVenda;
    }
           
           
      public Integer getNuit_cliente() {
        return Nuit_cliente;
    }

    public void setNuit_cliente(Integer Nuit_cliente) {
        this.Nuit_cliente = Nuit_cliente;
    }

    public Integer getUsuario_Cod_Funcionario() {
        return usuario_Cod_Funcionario;
    }

    public void setUsuario_Cod_Funcionario(Integer usuario_Cod_Funcionario) {
        this.usuario_Cod_Funcionario = usuario_Cod_Funcionario;
    }
           

    public Integer getCod_Venda() {
        return Cod_Venda;
    }

    public void setCod_Venda(Integer Cod_Venda) {
        this.Cod_Venda = Cod_Venda;
    }

    public Date getData_Venda() {
        return Data_Venda;
    }

    public void setData_Venda(Date Data_Venda) {
        this.Data_Venda = Data_Venda;
    }

    public Double getTotal_Venda() {
        return Total_Venda;
    }

    public void setTotal_Venda(Double Total_Venda) {
        this.Total_Venda = Total_Venda;
    }

    public String getNome_cliente() {
        return Nome_cliente;
    }

    public void setNome_cliente(String Nome_cliente) {
        this.Nome_cliente = Nome_cliente;
    }

    public Usuario getUser() {
        return User;
    }

    public void setUser(Usuario User) {
        this.User = User;
    }

    public Produto getProd() {
        return Prod;
    }

    public void setProd(Produto Prod) {
        this.Prod = Prod;
    }

    public Double getDesconto() {
        return Desconto;
    }

    public void setDesconto(Double Desconto) {
        this.Desconto = Desconto;
    }

    public Double getIva() {
        return Iva;
    }

    public void setIva(Double Iva) {
        this.Iva = Iva;
    }

    public String getForma_Pagamento() {
        return Forma_Pagamento;
    }

    public void setForma_Pagamento(String Forma_Pagamento) {
        this.Forma_Pagamento = Forma_Pagamento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }




           
           
           
           
           
}


