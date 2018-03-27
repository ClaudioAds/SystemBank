/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.value;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Joseph
 */
public class Movimentacao {
     
    private int id;
    private String data;
    private int conta;
    private String titular;
    private String tipo;
    private double valor;

    public String getData() {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        data = simpleDateFormat.format(new Date());
        return data;
    }

    public String getDataBanco() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Movimentacao{" + "data=" + data + ", conta=" + conta + ", titular=" + titular + ", tipo=" + tipo + ", valor=" + valor + '}';
    } 
}
