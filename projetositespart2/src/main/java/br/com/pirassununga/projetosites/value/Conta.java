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
 * @author claudio
 */
public class Conta {
    private int id;
    private double saldo;
    private String dataAbertura;
    private int idAg;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDataAbertura() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dataAbertura = simpleDateFormat.format(new Date());
        return dataAbertura;
    }

    public String getDataBanco() {
        return dataAbertura;
    }
    
    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAg() {
        return idAg;
    }

    public void setIdAg(int idAg) {
        this.idAg = idAg;
    }



    @Override
    public String toString() {
        return "Conta{" + "Numero=" + id + ", saldo=" + saldo + ", dataAbertura=" + dataAbertura + ", idAg=" + idAg + '}';
    }
    
    
}
