/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.value;

import java.sql.Date;

/**
 *
 * @author Joseph
 */
public class Usuario {

/**
 *
 * @author Joseph
 */
       
        private int id;
        private String cpf;
        private String rg;
        private String nome;
        private String email;
        private String senha;
        private String dataNasc;
        private String tipo;
        private Endereco endereco;

//    public Usuario( String cpf, String rg, String nome, String email, String senha, String dataNasc, String tipo, Endereco endereco) {
//        this.cpf = cpf;
//        this.rg = rg;
//        this.nome = nome;
//        this.email = email;
//        this.senha = senha;
//        this.dataNasc = dataNasc;
//        this.tipo = tipo;
//        this.endereco = endereco;
//    }
  
    public Usuario() {
    }

   
        
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", cpf=" + cpf + ", rg=" + rg + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", dataNasc=" + dataNasc + ", tipo=" + tipo + ", endereco=" + endereco + '}';
    }
    
   
}


