/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLogic;

import java.sql.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Neusia
 */

@Entity
@Table(name = "factura")

public class Factura {
    
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="cod_factura")
private Integer cod_factura; 

@Column(name="data_emissao")
private Date data_emissao;

@Column(name="validade_factura")
private Date validade_factura;

@Column(name="numero_pedido")
private Integer numero_pedido;

@OneToOne
@MapsId
@JoinColumn(name = "cliente")
public Cliente cliente;

@Column(name="estado")
private String estado;

@Column(name="total_factura")
private Double total_factura;


@OneToOne
@MapsId
@JoinColumn(name = "usuario")
public Usuario usuario_Cod_Funcionario;

}
