/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.commands;

import br.com.pirassununga.projetosites.factory.DaoFactoryBD;
import br.com.pirassununga.projetosites.factory.DaoFactoryIf;
import br.com.pirassununga.projetosites.interfaces.ContaIf;
import br.com.pirassununga.projetosites.value.Conta;
import br.com.pirassununga.projetosites.value.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joseph
 */
public class ConsultaSaldo implements Command {

    Conta conta=new Conta();
    Usuario usuario=new Usuario();
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoFactoryIf fabrica = new DaoFactoryBD();
        ContaIf contaDao = fabrica.gerarDaoConta();

        usuario = (Usuario)request.getSession().getAttribute("usuarioAutenticado");
        conta = contaDao.consultarSaldo(usuario);
        
        
        if (conta == null) {
            request.setAttribute("sucesso", false);
            request.setAttribute("mensagem", "nao foi possivel verificar o saldo");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showsaldo.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("sucesso", true);
            conta = contaDao.consultarSaldo(usuario);
            request.setAttribute("saldo", conta.getSaldo());
            request.getRequestDispatcher("showsaldo.jsp").forward(request, response);
        }

    }

}
