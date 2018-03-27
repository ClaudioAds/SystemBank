/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.interfaces;

import br.com.pirassununga.projetosites.value.Movimentacao;
import br.com.pirassununga.projetosites.value.Usuario;
import java.util.List;

/**
 *
 * @author Joseph
 */
public interface MovimentacaoIf {
    
     public boolean adicionar(Movimentacao movimentacao);
     
     public List<Movimentacao> buscarTodos();
     
     public List<Movimentacao> buscarPorUsuario(Usuario usuario);
}
