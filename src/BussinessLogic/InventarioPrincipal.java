/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLogic;

import java.sql.Date;

/**
 *
 * @author Neusia Hilario
 */
public class InventarioPrincipal {

    public Date Data;
    public Usuario User;
    public String Tipo_De_Inventario;

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public Usuario getUser() {
        return User;
    }

    public void setUser(Usuario User) {
        this.User = User;
    }

    public String getTipo_De_Inventario() {
        return Tipo_De_Inventario;
    }

    public void setTipo_De_Inventario(String Tipo_De_Inventario) {
        this.Tipo_De_Inventario = Tipo_De_Inventario;
    }
    
    
    
    
}
