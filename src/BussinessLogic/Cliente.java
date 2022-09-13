/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLogic;

import javax.persistence.*;


/**
 *
 * @author Neusia
 */

@Entity
@Table(name = "cliente")

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_cliente")
    private Integer cod_cliente;
    @Column(name="nome")
    private String nome;
    @Column(name="nuit")
    private Integer nuit;
    @Column(name="endereco")
    private String endereco;
    @Column(name="email")
    private String email;
    @Column(name="contacto")
    private Integer contacto;
    
     @OneToMany (mappedBy ="cliente" )
     private Factura factura;
     
     
      public Cliente(Integer cod_cliente, String nome, Integer nuit, String endereco, String email, Integer contacto, Factura factura) {
        this.cod_cliente = cod_cliente;
        this.nome = nome;
        this.nuit = nuit;
        this.endereco = endereco;
        this.email = email;
        this.contacto = contacto;
        this.factura = factura;
    }
     

     
         public Integer getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(Integer cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNuit() {
        return nuit;
    }

    public void setNuit(Integer nuit) {
        this.nuit = nuit;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getContacto() {
        return contacto;
    }

    public void setContacto(Integer contacto) {
        this.contacto = contacto;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }


  
    
    
    
}
 