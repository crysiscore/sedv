/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcessLayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Neusia Hilario
 */
public class DiretorioDAO {

    private ResultSet rs;
    private Connection connect;
    private CallableStatement cs = null;

    
    
    public ResultSet popularDiretorio() throws SQLException {
        this.cs = this.connect.prepareCall("{call PopulaDiretorio()}");

        this.rs = this.cs.executeQuery();

        return this.rs;
    }

}
