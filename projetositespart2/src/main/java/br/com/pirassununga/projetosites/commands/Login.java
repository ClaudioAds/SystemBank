/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.commands;

import br.com.pirassununga.projetosites.factory.DaoFactoryBD;
import br.com.pirassununga.projetosites.factory.DaoFactoryIf;
import br.com.pirassununga.projetosites.interfaces.UsuarioDaoIf;
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
public class Login implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Usuario usuario=new Usuario();
        DaoFactoryIf fabrica = new DaoFactoryBD();
        UsuarioDaoIf usuarioDao = fabrica.criaUsuarioDAO();
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
         
        usuario.setEmail(email);
        usuario.setSenha(senha);
        Usuario usuarioAutenticado= usuarioDao.autenticacao(usuario);
        if (usuarioAutenticado != null && usuarioAutenticado.getTipo().equals("Admin")) {
            request.setAttribute("sucesso", true);
            request.getSession(true);
            request.getSession().setAttribute("usuarioAutenticado",usuarioAutenticado);
            request.getRequestDispatcher("homeadmin.jsp").forward(request, response);
        } else if(usuarioAutenticado != null && usuarioAutenticado.getTipo().equals("Cliente")) {
            request.setAttribute("sucesso", true);
            request.getSession(true);
            request.getSession().setAttribute("usuarioAutenticado",usuarioAutenticado);
            request.getRequestDispatcher("homecliente.jsp").forward(request, response);
        }else if(usuarioAutenticado != null && usuarioAutenticado.getTipo().equals("Gerente Geral")) {
            request.setAttribute("sucesso", true);
            request.getSession(true);
            request.getSession().setAttribute("usuarioAutenticado",usuarioAutenticado);
            request.getRequestDispatcher("homegerentegeral.jsp").forward(request, response);
        }else if(usuarioAutenticado != null && usuarioAutenticado.getTipo().equals("Gerente da agencia")) {
            request.setAttribute("sucesso", true);
            request.getSession(true);
            request.getSession().setAttribute("usuarioAutenticado",usuarioAutenticado);
            request.getRequestDispatcher("homegerenteag.jsp").forward(request, response);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        request.setAttribute("sucesso", false);
        request.setAttribute("errorMsg", "Login ou senha incorretos");
        dispatcher.forward(request, response);
    }

}
