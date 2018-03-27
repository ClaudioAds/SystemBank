/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.interfaces;

import br.com.pirassununga.projetosites.value.Usuario;
import java.util.List;

/**
 *
 * @author Joseph
 */
public interface UsuarioDaoIf {
     
    public boolean adicionar(Usuario usuario);

    public boolean excluir(Usuario usuario);
    
    public List<Usuario> buscarTodos();
    
    public Usuario autenticacao(Usuario usuario);
    
    public Usuario buscaPorCPF(String cpf);
    
    public List<Usuario> buscarGerenteAg();
    
     public List<Usuario> buscarUsuarioAg(int idAg);
}
