/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.interfaces;

import br.com.pirassununga.projetosites.value.Agencia;
import java.util.List;

/**
 *
 * @author Joseph
 */
public interface AgenciaIf {
    
    public boolean adicionar(Agencia agencia);
    
    public boolean excluir(Agencia agencia);
    
    public List<Agencia> buscarTodos();
    
    public Agencia buscaPorId(int id) ;
}
