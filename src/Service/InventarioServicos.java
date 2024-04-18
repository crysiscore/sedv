/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import BussinessLogic.Inventario;
import DataAcessLayer.InventarioDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neusia Hilario
 */
public class InventarioServicos {
    
    ResultSet rs;

    private Inventario inventario;
    private InventarioDAO inventarioDao = new InventarioDAO();

  public List<Inventario> getDetalhesInventario(int Cod_Inventario) throws SQLException {
    List<Inventario> inventarios = new ArrayList<>();
    this.rs = this.inventarioDao.getDetalhesInventario(Cod_Inventario);

    while (this.rs.next()) {
        Inventario inventario = new Inventario();
        Inventario Inv = new Inventario();

        inventario.setCod_Inventario(Integer.valueOf(this.rs.getInt("Cod_Inventario")));

        Inv.setCod_Produto(Integer.valueOf(this.rs.getInt("Cod_Produto")));

        Inv.setNome(this.rs.getString("Nome"));
        Inv.setStock(Double.valueOf(this.rs.getDouble("Stock")));
        Inv.setQuantidade_Contada(Double.valueOf(this.rs.getDouble("Quantidade_Contada")));
        Inv.setDiferenca_Existente_Contada(Double.valueOf(this.rs.getDouble("Diferenca_Existente_Contada")));

        // Adicionando instruções de log para verificar os valores dos dados
     //   System.out.println("Cod_Inventario: " + inventario.getCod_Inventario());
      //  System.out.println("Cod_Produto: " + Inv.getCod_Produto());
      //  System.out.println("Nome: " + Inv.getNome());
       // System.out.println("Stock: " + Inv.getStock());
       // System.out.println("Quantidade_Contada: " + Inv.getQuantidade_Contada());
      //  System.out.println("Diferenca_Existente_Contada: " + Inv.getDiferenca_Existente_Contada());

        inventario.setDetalhesInventario(Inv);
        inventarios.add(inventario);
    }

    return inventarios;
}

}
