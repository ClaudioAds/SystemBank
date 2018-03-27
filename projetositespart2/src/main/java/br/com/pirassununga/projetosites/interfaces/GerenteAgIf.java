/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.interfaces;

/**
 *
 * @author Joseph
 */
public interface GerenteAgIf {
    
    public boolean adicionar(int idAg,String cpfGerente);
    
    public int buscaAgencia(String cpf);
}
