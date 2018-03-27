/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.dao;

import br.com.pirassununga.projetosites.banco.ConexaoBD;
import br.com.pirassununga.projetosites.interfaces.UsuarioDaoIf;
import br.com.pirassununga.projetosites.value.Endereco;
import br.com.pirassununga.projetosites.value.Usuario;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph
 */
public class UsuarioDao implements UsuarioDaoIf {

    private ConexaoBD conex;

    public UsuarioDao() {
    }

    @Override
    public boolean adicionar(Usuario usuario) {
        boolean resultado = false;

        PreparedStatement pst = null;
        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("INSERT into usuario(cpf_cnpj,rg,nome,email,data_nascimento,tipo"
                    + ",uf,cidade,rua,numero,senha)VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, usuario.getCpf());
            pst.setString(2, usuario.getRg());
            pst.setString(3, usuario.getNome());
            pst.setString(4, usuario.getEmail());
            pst.setString(5, usuario.getDataNasc());
            pst.setString(6, usuario.getTipo());
            pst.setString(7, usuario.getEndereco().getUf());
            pst.setString(8, usuario.getEndereco().getCidade());
            pst.setString(9, usuario.getEndereco().getRua());
            pst.setInt(10, usuario.getEndereco().getNumero());
            pst.setString(11, usuario.getSenha());
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
    public boolean excluir(Usuario usuario) {
        boolean resultado = false;
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("DELETE FROM usuario WHERE id=?");
            pst.setInt(1, usuario.getId());
            pst.execute();
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
    public Usuario buscaPorCPF(String cpf) {

        PreparedStatement pst = null;
        Usuario usuario = null;
        Endereco endereco = null;
        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("SELECT * FROM usuario where cpf_cnpj=" + cpf + "'");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf_cnpj"));
                usuario.setRg(rs.getString("rg"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setDataNasc(rs.getString("data_nascimento"));

                endereco = new Endereco();
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getInt("numero"));
                usuario.setEndereco(endereco);
            }
        } catch (SQLException ex) {

        } catch (ClassNotFoundException | IOException ex) {
            
        }
        return usuario;
    }

    @Override
    public List<Usuario> buscarTodos() {
        List<Usuario> lista = new ArrayList<>();
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("SELECT * FROM usuario order by id");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                Endereco endereco = new Endereco();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf_cnpj"));
                usuario.setRg(rs.getString("rg"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setDataNasc(rs.getString("data_nascimento"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getInt("numero"));
                usuario.setEndereco(endereco);
                lista.add(usuario);
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
    public List<Usuario> buscarGerenteAg() {
        List<Usuario> lista = new ArrayList<>();
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("SELECT * FROM usuario where tipo='Gerente da agencia'");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                Endereco endereco = new Endereco();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf_cnpj"));
                usuario.setRg(rs.getString("rg"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setDataNasc(rs.getString("data_nascimento"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getInt("numero"));
                usuario.setEndereco(endereco);
                lista.add(usuario);
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
    public List<Usuario> buscarUsuarioAg(int idAg) {
        List<Usuario> lista = new ArrayList<>();
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("select u.nome,u.cpf_cnpj\n"
                    + "from usuario u join titular_conta tc on tc.cpf_usuario = u.cpf_cnpj join  conta c on tc.num_conta = c.numero join agencia a on c.id_ag=a.id \n"
                    + "join gerente_agencia ag on a.id='"+idAg+"'");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf_cnpj"));
                lista.add(usuario);
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
    public Usuario autenticacao(Usuario usuario) {
        PreparedStatement pst = null;
        Usuario usuarioRetorno = null;
        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("SELECT *FROM usuario WHERE email=? and senha=?");
            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getSenha());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Endereco endereco = new Endereco();
                usuarioRetorno = new Usuario();
                usuarioRetorno.setId(rs.getInt("id"));
                usuarioRetorno.setNome(rs.getString("nome"));
                usuarioRetorno.setCpf(rs.getString("cpf_cnpj"));
                usuarioRetorno.setRg(rs.getString("rg"));
                usuarioRetorno.setEmail(rs.getString("email"));
                usuarioRetorno.setTipo(rs.getString("tipo"));
                usuarioRetorno.setDataNasc(rs.getString("data_nascimento"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getInt("numero"));
                usuarioRetorno.setEndereco(endereco);
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarioRetorno;
    }

}
