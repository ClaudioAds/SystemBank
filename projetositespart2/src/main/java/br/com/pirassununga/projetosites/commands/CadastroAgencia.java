/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.commands;

import br.com.pirassununga.projetosites.factory.DaoFactoryBD;
import br.com.pirassununga.projetosites.factory.DaoFactoryIf;
import br.com.pirassununga.projetosites.interfaces.AgenciaIf;
import br.com.pirassununga.projetosites.value.Agencia;
import br.com.pirassununga.projetosites.value.Endereco;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joseph
 */
public class CadastroAgencia implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        DaoFactoryIf fabrica = new DaoFactoryBD();
        AgenciaIf agenciaDao= fabrica.gerarDaoAgencia();
        
        //agencia
        String nome= request.getParameter("nome");
        String numeroAg= request.getParameter("numero-ag");
        
        //endereco
        String rua= request.getParameter("rua");
        String cidade= request.getParameter("cidade");
        String uf= request.getParameter("uf");
        String numero= request.getParameter("numero");
        
        
        
      
        
        Agencia agencia=new Agencia();
        agencia.setNome(nome);
        agencia.setNumero(numeroAg);
        
        Endereco endereco= new Endereco();
        endereco.setRua(rua);
        endereco.setCidade(cidade);
        endereco.setUf(uf);
        endereco.setNumero(Integer.parseInt(numero));
        agencia.setEndereco(endereco);
        
        agenciaDao.adicionar(agencia);
        
         if (agenciaDao.adicionar(agencia)) {
            request.setAttribute("sucesso", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("homeadmin.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("sucesso", false);
            request.setAttribute("mensagem", "Não foi possivel inserir a agencia");
            RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroagencias.jsp");
            dispatcher.forward(request, response);
        }
    }
    
}
