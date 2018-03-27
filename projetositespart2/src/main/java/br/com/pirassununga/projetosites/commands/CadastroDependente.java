/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.commands;

import br.com.pirassununga.projetosites.factory.DaoFactoryBD;
import br.com.pirassununga.projetosites.factory.DaoFactoryIf;
import br.com.pirassununga.projetosites.interfaces.DependenteIf;
import br.com.pirassununga.projetosites.interfaces.UsuarioDaoIf;
import br.com.pirassununga.projetosites.value.Conta;
import br.com.pirassununga.projetosites.value.Endereco;
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
public class CadastroDependente implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        DaoFactoryIf fabrica = new DaoFactoryBD();
        UsuarioDaoIf usuarioDao = fabrica.criaUsuarioDAO();
        DependenteIf dependenteDao=fabrica.gerarDaoDependente();
        
        //conta
         String contaId=request.getParameter("conta");

        //usuario
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String nascimento = request.getParameter("data-nascimento");

        //endereco
        String rua = request.getParameter("rua");
        String numero = request.getParameter("numero");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        
    

        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        usuario.setRg(rg);
        usuario.setDataNasc(nascimento);
        usuario.setTipo("Cliente");

        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setCidade(cidade);
        endereco.setUf(uf);
        endereco.setNumero(Integer.parseInt(numero));
        usuario.setEndereco(endereco);
        
        usuarioDao.adicionar(usuario);
        
        
        Conta conta=new Conta();
        conta.setId(Integer.parseInt(contaId));
        
        if(dependenteDao.adicionar(conta, usuario)){
            request.setAttribute("sucesso", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("homeadmin.jsp");
            dispatcher.forward(request, response);
        }else {
            request.setAttribute("sucesso", false);
            request.setAttribute("mensagem", "Não foi possivel inserir o usuario e a conta");
            RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrodependente.jsp");
            dispatcher.forward(request, response);
        }
        
    }
    
}
