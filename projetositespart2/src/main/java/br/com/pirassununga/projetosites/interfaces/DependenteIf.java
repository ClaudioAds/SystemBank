/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.interfaces;

import br.com.pirassununga.projetosites.value.Conta;
import br.com.pirassununga.projetosites.value.Usuario;

/**
 *
 * @author Joseph
 */
public interface DependenteIf {
    
    public boolean adicionar(Conta conta, Usuario usuario);
    
}
