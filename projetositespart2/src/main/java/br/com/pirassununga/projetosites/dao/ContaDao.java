/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.dao;

import br.com.pirassununga.projetosites.banco.ConexaoBD;
import br.com.pirassununga.projetosites.interfaces.ContaIf;
import br.com.pirassununga.projetosites.value.Conta;
import br.com.pirassununga.projetosites.value.Usuario;
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
public class  ContaDao implements ContaIf {

    private ConexaoBD conex;

    @Override
    public boolean adicionar(Conta conta) {
        boolean resultado = false;
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("INSERT into conta(data_abertura,saldo,id_ag)"
                    + "VALUES (?,?,?)");

            pst.setString(1, conta.getDataAbertura());
            pst.setDouble(2, conta.getSaldo());
            pst.setInt(3, conta.getIdAg());

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
    public boolean excluir(Conta conta) {
        boolean resultado = false;
        PreparedStatement pst = null;
        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("DELETE FROM conta WHERE id=?");
            pst.setInt(1, conta.getId());
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
    public List<Conta> buscarTodos() {

        List<Conta> lista = new ArrayList<>();
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("SELECT * FROM conta order by numero");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Conta conta = new Conta();
                conta.setId(rs.getInt("numero"));
                conta.setIdAg(rs.getInt("id_ag"));
                conta.setDataAbertura(rs.getString("data_abertura"));
                conta.setSaldo(rs.getDouble("saldo"));
                lista.add(conta);
            }
            rs.close();
            pst.close();
            conex.desconecta();
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Conta buscaPorId(int numero) {

        PreparedStatement pst = null;
        Conta conta = null;
        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("SELECT * FROM conta where numero='" + numero + "'");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                conta = new Conta();
                conta.setId(rs.getInt("numero"));
                conta.setDataAbertura(rs.getString("data_abertura"));
                conta.setIdAg(rs.getInt("id_ag"));
                conta.setSaldo(rs.getDouble("saldo"));
                return conta;
            }
            rs.close();
            pst.close();
            conex.desconecta();
        } catch (SQLException ex) {

        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conta;
    }

    @Override
    public boolean atualizarSaldo(Conta conta) {
        boolean resultado = false;

        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("UPDATE conta set saldo=? WHERE numero=?");
            pst.setDouble(1, conta.getSaldo());
            pst.setInt(2, conta.getId());
            if (pst.executeUpdate() > 0) {
                resultado = true;
                pst.close();
            }
        } catch (SQLException e) {

        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        conex.desconecta();
        return resultado;
    }

    @Override
    public int obterProximoNumConta() {
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("SELECT MAX(numero)  proximo FROM conta");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int proximo = rs.getInt("proximo");
                return proximo;
            }
            pst.close();
            rs.close();
            conex.desconecta();
        } catch (SQLException ex) {

        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 1;
    }

    @Override
    public Conta consultarSaldo(Usuario usuario) {
        PreparedStatement pst = null;
        Conta conta = null;
        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("select distinct c.saldo \n"
                    + "from conta c,usuario u,titular_conta tc\n"
                    + "where tc.cpf_usuario='"+usuario.getCpf()+"'and tc.num_conta = c.numero");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                conta = new Conta();
                conta.setSaldo(rs.getDouble("saldo"));
                return conta;
            }
            rs.close();
            pst.close();
            conex.desconecta();
        } catch (SQLException ex) {

        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conta;
    }

    @Override
    public Conta buscarPorUsuario(Usuario usuario) {
         PreparedStatement pst = null;
        Conta conta = null;
        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("select distinct c.saldo,c.numero \n"
                    + "from conta c,usuario u,titular_conta tc\n"
                    + "where tc.cpf_usuario='"+usuario.getCpf()+"'and tc.num_conta = c.numero");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                conta = new Conta();
                conta.setSaldo(rs.getDouble("saldo"));
                conta.setId(rs.getInt("numero"));
            }
            rs.close();
            pst.close();
            conex.desconecta();
        } catch (SQLException ex) {

        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conta;
    }
    
}
