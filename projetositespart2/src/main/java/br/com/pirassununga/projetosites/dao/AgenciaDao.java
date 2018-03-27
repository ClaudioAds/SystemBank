/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.dao;

import br.com.pirassununga.projetosites.banco.ConexaoBD;
import br.com.pirassununga.projetosites.interfaces.AgenciaIf;
import br.com.pirassununga.projetosites.value.Agencia;
import br.com.pirassununga.projetosites.value.Endereco;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph
 */
public class AgenciaDao implements AgenciaIf {

    private ConexaoBD conex;
    Agencia agencia = new Agencia();

    @Override
    public boolean adicionar(Agencia agencia) {
        boolean resultado = false;
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("INSERT into agencia(nome,numero_ag,uf,cidade,rua,numero)"
                    + "VALUES (?,?,?,?,?,?)");
            pst.setString(1, agencia.getNome());
            pst.setString(2, agencia.getNumero());
            pst.setString(3, agencia.getEndereco().getUf());
            pst.setString(4, agencia.getEndereco().getCidade());
            pst.setString(5, agencia.getEndereco().getRua());
            pst.setInt(6, agencia.getEndereco().getNumero());
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

    @Override
    public boolean excluir(Agencia agencia) {
        boolean resultado = false;
        PreparedStatement pst = null;
        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("DELETE FROM agencia WHERE id=?");
            pst.setInt(1, agencia.getId());
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

    @Override
    public List<Agencia> buscarTodos() {
        List<Agencia> lista = new ArrayList<>();
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("SELECT * FROM agencia");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                agencia = new Agencia();
                Endereco endereco = new Endereco();
                agencia.setId(rs.getInt("id"));
                agencia.setNome(rs.getString("nome"));
                agencia.setNumero(rs.getString("numero_ag"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getInt("numero"));
                agencia.setEndereco(endereco);
                lista.add(agencia);
            }
            rs.close();
            pst.close();
            conex.desconecta();
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    @Override
     public Agencia buscaPorId(int id) {

        PreparedStatement pst = null;
        Agencia agencia = null;
        Endereco endereco = null;
        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("SELECT * FROM agencia where id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.first();
           
            agencia=new Agencia();
            agencia.setId(rs.getInt("id"));
            agencia.setNome(rs.getString("nome"));
            agencia.setNumero(rs.getString("numero_ag"));
            
            endereco=new Endereco();
            endereco.setCidade(rs.getString("cidade"));
            endereco.setUf(rs.getString("uf"));
            endereco.setRua(rs.getString("rua"));
            endereco.setNumero(rs.getInt("numero"));
            agencia.setEndereco(endereco);
            
        } catch (SQLException ex) {

        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agencia;
    }
}
