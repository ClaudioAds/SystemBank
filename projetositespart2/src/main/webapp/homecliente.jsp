<%-- 
    Document   : home
    Created on : 21/09/2016, 15:00:56
    Author     : Joseph
--%>

<%@page import="java.util.List"%>
<%@page import="br.com.pirassununga.projetosites.value.Movimentacao"%>
<%@page import="br.com.pirassununga.projetosites.interfaces.MovimentacaoIf"%>
<%@page import="br.com.pirassununga.projetosites.factory.DaoFactoryBD"%>
<%@page import="br.com.pirassununga.projetosites.factory.DaoFactoryIf"%>
<%@page import="br.com.pirassununga.projetosites.value.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
            <%@include file="cabecalhocliente.jsp" %>
            

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        olá  ${sessionScope.usuarioAutenticado.getNome()}
                        <small>Optional description</small>
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
                                    <h3 class="box-title">Extrato</h3>
                                </div>

                                <!-- /.box-header -->
                                <div class="box-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>tipo</th>
                                                <th>data</th>
                                                <th>valor</th>
                                                <th>titular</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <%
                                                Usuario usuario=new Usuario();
                                                DaoFactoryIf fabrica = new DaoFactoryBD();
                                                MovimentacaoIf movimentacaoDao = fabrica.gerarDaoMovimentacao();
                                                usuario = (Usuario) request.getSession().getAttribute("usuarioAutenticado");
                                                List<Movimentacao> listaMovimentacao = movimentacaoDao.buscarPorUsuario(usuario);
                                                for (Movimentacao u : listaMovimentacao) {
                                            %>
                                            <tr>          
                                                <td><%= u.getTipo()%></td>
                                                <td><%= u.getDataBanco()%></td>
                                                <td><%= u.getValor()%></td>
                                                <td><%= u.getTitular()%></td> 
                                            </tr>
                                            <%
                                                }
                                            %>

                                        </tbody>
                                    </table>

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

