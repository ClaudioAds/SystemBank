/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.dao;

import br.com.pirassununga.projetosites.banco.ConexaoBD;
import br.com.pirassununga.projetosites.interfaces.DependenteIf;
import br.com.pirassununga.projetosites.value.Conta;
import br.com.pirassununga.projetosites.value.Usuario;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph
 */
public class DependenteDao implements DependenteIf{

     private ConexaoBD conex;
    
    @Override
    public boolean adicionar(Conta conta, Usuario usuario) {
         boolean resultado = false;
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("INSERT into titular_conta(cpf_usuario,num_conta,nome_usuario)"
                    + "VALUES (?,?,?)");
            pst.setString(1, usuario.getCpf());
            pst.setInt(2, conta.getId());
            pst.setString(3, usuario.getNome());
             if (pst.executeUpdate() > 0) {
                resultado = true;
                pst.close();
            }
        } catch (SQLException ex) {
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        conex.desconecta();
        return resultado;
    }
    
}
