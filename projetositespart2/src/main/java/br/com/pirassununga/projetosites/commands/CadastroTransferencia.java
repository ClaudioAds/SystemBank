/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirassununga.projetosites.commands;

import br.com.pirassununga.projetosites.factory.DaoFactoryBD;
import br.com.pirassununga.projetosites.factory.DaoFactoryIf;
import br.com.pirassununga.projetosites.interfaces.ContaIf;
import br.com.pirassununga.projetosites.interfaces.MovimentacaoIf;
import br.com.pirassununga.projetosites.value.Conta;
import br.com.pirassununga.projetosites.value.Movimentacao;
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
public class CadastroTransferencia implements Command {

    Conta conta = new Conta();
    Conta contaCreditada=new Conta();
    Usuario usuario = new Usuario();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoFactoryIf fabrica = new DaoFactoryBD();
        ContaIf contaDao = fabrica.gerarDaoConta();
        MovimentacaoIf movimentacaoDao = fabrica.gerarDaoMovimentacao();
        
        String valor = request.getParameter("valor");
        String numConta = request.getParameter("numero-conta");

        
        double valorTransacao = Double.parseDouble(valor);
        contaCreditada = contaDao.buscaPorId(Integer.parseInt(numConta));
        
        
        usuario = (Usuario) request.getSession().getAttribute("usuarioAutenticado");
        conta = contaDao.buscarPorUsuario(usuario);
        
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTitular(usuario.getCpf());
        movimentacao.setConta(conta.getId());
        movimentacao.setValor(valorTransacao);
        movimentacao.setTipo("Transferencia");
        
        if(debitarSaldo(conta, valorTransacao)){
            creditarSaldo(contaCreditada, valorTransacao);
            movimentacaoDao.adicionar(movimentacao);
            contaDao.atualizarSaldo(conta);
            contaDao.atualizarSaldo(contaCreditada);
            request.setAttribute("sucesso", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("homecliente.jsp");
            dispatcher.forward(request, response);
        }else{
            request.setAttribute("sucesso", false);
            request.setAttribute("mensagem", "Não foi possivel completar a transação");
            RequestDispatcher dispatcher = request.getRequestDispatcher("paginasaque.jsp");
            dispatcher.forward(request, response);
        }

    }

    public boolean debitarSaldo(Conta conta, double valor) {
        boolean resultado = false;
        if (valor <= 0) {
            resultado = false;
        } else if (conta.getSaldo() > 0 && valor < conta.getSaldo()) {
            double saldo = conta.getSaldo() - valor;
            conta.setSaldo(saldo);
            resultado = true;
        }
        return resultado;
    }
    
    public boolean creditarSaldo(Conta conta, double valor) {
        boolean resultado = false;
        if(valor <=0){
            resultado = false;
        }else{
            double saldo=conta.getSaldo() + valor;
            conta.setSaldo(saldo);
            resultado = true;
        }
        return resultado;
    }
}
