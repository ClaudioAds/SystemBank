/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.dao;

import br.com.pirassununga.projetosites.banco.ConexaoBD;
import br.com.pirassununga.projetosites.interfaces.MovimentacaoIf;
import br.com.pirassununga.projetosites.value.Movimentacao;
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
public class MovimentacaoDao implements MovimentacaoIf {

    private ConexaoBD conex;

    @Override
    public boolean adicionar(Movimentacao movimentacao) {
        boolean resultado = false;
        PreparedStatement pst = null;
        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("INSERT into movimentacao(data,tipo,cpf_titular,valor,"
                    + "num_conta)VALUES (?,?,?,?,?)");
            pst.setString(1, movimentacao.getData());
            pst.setString(2, movimentacao.getTipo());
            pst.setString(3, movimentacao.getTitular());
            pst.setDouble(4, movimentacao.getValor());
            pst.setInt(5, movimentacao.getConta());
            if (pst.executeUpdate() > 0) {
                resultado = true;
                pst.close();
            }
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        conex.desconecta();
        return resultado;
    }

    @Override
    public List<Movimentacao> buscarTodos() {
        List<Movimentacao> lista = new ArrayList<>();
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("SELECT * FROM usuario order by id");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Movimentacao movimentacao = new Movimentacao();

                movimentacao.setId(rs.getInt("id"));
                movimentacao.setData(rs.getString("data"));
                movimentacao.setTitular(rs.getString("cpf_titular"));
                movimentacao.setTipo(rs.getString("tipo"));
                movimentacao.setValor(rs.getDouble("valor"));
                movimentacao.setConta(rs.getInt("num_conta"));
                lista.add(movimentacao);
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
    public List<Movimentacao> buscarPorUsuario(Usuario usuario) {
        List<Movimentacao> lista = new ArrayList<>();
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("select m.data,m.tipo,m.valor,m.cpf_titular\n"
                    + "from movimentacao m join usuario u on m.cpf_titular=u.cpf_cnpj\n"
                    + "where m.cpf_titular='"+usuario.getCpf()+"'");
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setData(rs.getString("data"));
                movimentacao.setTitular(rs.getString("cpf_titular"));
                movimentacao.setTipo(rs.getString("tipo"));
                movimentacao.setValor(rs.getDouble("valor"));
                lista.add(movimentacao);
            }
            rs.close();
            pst.close();
            conex.desconecta();
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(MovimentacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
