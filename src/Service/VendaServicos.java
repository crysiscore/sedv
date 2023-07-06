/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import BussinessLogic.DetalhesVenda;
import BussinessLogic.Venda;
import DataAcessLayer.VendaDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neusia Hilario
 */
public class VendaServicos {
    ResultSet rs;
    
    private Venda venda;
    private VendaDao vendaDao = new VendaDao();
    
    
    
               public boolean RegistarVenda(Venda venda) throws SQLException{
            boolean status = false;
            rs = vendaDao.RegistarVenda(venda);
                      rs.next();
                          if( rs.getString(1).contentEquals("Sucesso")){
                                    status = true;
                          }                  
        /*     */
        return status;
        }
               
    public List<Venda> getDetalhesVenda(int Cod_Venda) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        this.rs = this.vendaDao.getDetalhesVenda(Cod_Venda);

        while (this.rs.next()) {
            Venda venda = new Venda();
            DetalhesVenda Dv = new DetalhesVenda();

            venda.setCod_Venda(Integer.valueOf(this.rs.getInt("Codigo")));
            Dv.setProduto_Cod_produto(this.rs.getInt("Cod_Produto"));
            Dv.setNome_Produto(this.rs.getString("Nome_Produto"));
            Dv.setPreco(Double.valueOf(this.rs.getDouble("Preco")));
            Dv.setQuantidade(Double.valueOf(this.rs.getDouble("Quantidade")));
            Dv.setSubtotal(Double.valueOf(this.rs.getDouble("Subtotal")));

            venda.setDetalhesVenda(Dv);
            vendas.add(venda);
        }

        return vendas;
    }

}
