/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.commands;

import br.com.pirassununga.projetosites.factory.DaoFactoryBD;
import br.com.pirassununga.projetosites.factory.DaoFactoryIf;
import br.com.pirassununga.projetosites.interfaces.GerenteAgIf;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joseph
 */
public class CadastroGerenteAgencia implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        DaoFactoryIf fabrica = new DaoFactoryBD();
        GerenteAgIf gerenteAgDao=fabrica.gerarDaoGerenteAg();
        
        String agenciaId=request.getParameter("Agencia");
        String cpf=request.getParameter("gerente");
        
        int idAg=Integer.parseInt(agenciaId);
        
        if(gerenteAgDao.adicionar(idAg, cpf)){
            request.setAttribute("sucesso", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("homeadmin.jsp");
            dispatcher.forward(request, response);
        }else{
            request.setAttribute("sucesso", false);
            request.setAttribute("mensagem", "Não foi possivel completar a operação");
            RequestDispatcher dispatcher = request.getRequestDispatcher("adicionargerenteag.jsp");
            dispatcher.forward(request, response);
        }
    }
    
}
