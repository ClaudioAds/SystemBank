/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.dao;

import br.com.pirassununga.projetosites.banco.ConexaoBD;
import br.com.pirassununga.projetosites.interfaces.GerenteAgIf;
import br.com.pirassununga.projetosites.value.Usuario;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph
 */
public class GerenteAgDao implements GerenteAgIf {

    private ConexaoBD conex;

    @Override
    public boolean adicionar(int idAg, String cpfGerente) {
        boolean resultado = false;
        PreparedStatement pst = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("INSERT into gerente_agencia(cpf_gerente,id_ag)"
                    + "VALUES (?,?)");
            pst.setString(1, cpfGerente);
            pst.setInt(2, idAg);
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
    public int buscaAgencia(String cpf) {

        PreparedStatement pst = null;
        Usuario usuario = null;

        try {
            conex = new ConexaoBD();
            pst = conex.getConnection().prepareStatement("select ag.id_ag\n"
                    + "from usuario u,gerente_agencia ag \n"
                    + "where ag.cpf_gerente = u.cpf_cnpj");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int idAg=rs.getInt("id_ag");
                return idAg;
            }
        } catch (SQLException ex) {

        } catch (ClassNotFoundException | IOException ex) {
           
        }
        return 0;
    }
}
