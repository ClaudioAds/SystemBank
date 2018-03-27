<%-- 
    Document   : home
    Created on : 21/09/2016, 15:00:56
    Author     : Joseph
--%>

<%@page import="br.com.pirassununga.projetosites.value.Conta"%>
<%@page import="br.com.pirassununga.projetosites.interfaces.ContaIf"%>
<%@page import="br.com.pirassununga.projetosites.value.Agencia"%>
<%@page import="br.com.pirassununga.projetosites.interfaces.AgenciaIf"%>
<%@page import="br.com.pirassununga.projetosites.interfaces.UsuarioDaoIf"%>
<%@page import="br.com.pirassununga.projetosites.factory.DaoFactoryBD"%>
<%@page import="br.com.pirassununga.projetosites.factory.DaoFactoryIf"%>
<%@page import="java.util.List"%>
<%@page import="br.com.pirassununga.projetosites.value.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
    
        <%@include file="cabecalhoadmin.jsp" %>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        olá 
                        <small> ${sessionScope.usuarioAutenticado.getNome()}  bem vindo a página de adiministração 
                            do sistema.</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
                        <li class="active">Here</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">Usuarios</h3>
                                </div>

                                <!-- /.box-header -->
                                <div class="box-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>nome</th>
                                                <th>email</th>
                                                <th>cpf</th>
                                                <th>rg</th>
                                                <th>tipo</th>
                                                <th>nascimento</th>
                                                <th>Endereco</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <%
                                                DaoFactoryIf fabrica = new DaoFactoryBD();
                                                UsuarioDaoIf usuarioDao = fabrica.criaUsuarioDAO();
                                                List<Usuario> listaUsuarios = usuarioDao.buscarTodos();
                                                for (Usuario u : listaUsuarios) {
                                            %>
                                            <tr>          
                                                <td> <%= u.getId()%> </td>
                                                <td><%= u.getNome()%></td>
                                                <td><%= u.getEmail()%></td>
                                                <td><%= u.getCpf()%></td>
                                                <td><%= u.getRg()%></td>
                                                <td><%= u.getTipo()%></td>
                                                <td><%= u.getDataNasc()%></td>
                                                <td><%= u.getEndereco()%></td>
                                            </tr>
                                            <%
                                                }
                                            %>

                                        </tbody>
                                    </table>

                                    <div class="box-header">
                                        <h3 class="box-title">Agências</h3>
                                    </div>
                                     <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>nome</th>
                                                <th>numero</th>
                                                <th>Endereco</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <%
                                                AgenciaIf agenciaDao = fabrica.gerarDaoAgencia();
                                                List<Agencia> listaAgencias = agenciaDao.buscarTodos();
                                                for (Agencia u : listaAgencias) {
                                            %>
                                            <tr>          
                                                <td> <%= u.getId()%> </td>
                                                <td><%= u.getNome()%></td>
                                                <td><%= u.getNumero()%></td>
                                                <td><%= u.getEndereco()%></td>
                                            </tr>
                                            <%
                                                }
                                            %>

                                        </tbody>
                                    </table>
                                     
                                     <div class="box-header">
                                        <h3 class="box-title">Contas</h3>
                                    </div>
                                     <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>data abertura</th>
                                                <th>Numero Agencia</th>
                                                <th>Saldo</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <%
                                                ContaIf contaDao = fabrica.gerarDaoConta();
                                                List<Conta> listaContas = contaDao.buscarTodos();
                                                for (Conta u : listaContas) {
                                            %>
                                            <tr>          
                                                <td> <%= u.getId()%> </td>
                                                <td><%= u.getDataBanco()%></td>
                                                <td><%= u.getIdAg()%></td>
                                                <td><%= u.getSaldo()%></td>
                                            </tr>
                                            <%
                                                }
                                            %>

                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                        <!-- /.col -->
                    </div>
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

            <!-- Main Footer -->
            <footer class="main-footer">
                <!-- To the right -->
                <div class="pull-right hidden-xs">
                    Anything you want
                </div>
                <!-- Default to the left -->
                <strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.
            </footer>
        </div>
        <!-- ./wrapper -->

        <!-- REQUIRED JS SCRIPTS -->

        <!-- jQuery 2.2.3 -->
        <script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/app.min.js"></script>

        <!-- Optionally, you can add Slimscroll and FastClick plugins.
             Both of these plugins are recommended to enhance the
             user experience. Slimscroll is required when using the
             fixed layout. -->
    </body>
</html>

