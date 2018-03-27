<%-- 
    Document   : home
    Created on : 21/09/2016, 15:00:56
    Author     : Joseph
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <form action="FrontController?action=CadastroConta" method="post">

                        <fieldset>
                            <legend><h2><span class="glyphicon glyphicon-user"> Informações de usuario </span></h2></legend>
                            <div class="form-group has-feedback">
                                <input type="text" name="nome" class="form-control" placeholder="Digite seu nome">
                                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="email" name="email" class="form-control" placeholder="Digite seu email">
                                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="password" name="senha" class="form-control" placeholder="Password">
                                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="text" class="form-control" id="cpf" name="cpf"  placeholder="Digite seu cpf">
                                <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="text" class="form-control" id="rg" name="rg"  placeholder="Digite seu rg">
                                <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
                            </div>
                            <div class="form-group">
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input type="date" name="data-nascimento" class="form-control pull-right" id="datepicker">
                                </div>
                                <!-- /.input group -->
                            </div>
                        </fieldset>

                        <fieldset>
                            <legend><h2><span class="glyphicon glyphicon-globe"> Endereço do usuário</span></h2></legend>
                            <div class="form-group">
                                <select class="form-control select2" id="uf" name="uf" style="width: 100%;">
                                    <option value="AC">Acre (AC)</option>
                                    <option value="AL">Alagoas (AL)</option>
                                    <option value="AP">Amapá (AP)</option>
                                    <option value="AM">Amazonas (AM)</option>
                                    <option value="BA">Bahia (BA)</option>
                                    <option value="CE">Ceará (CE)</option>
                                    <option value="DF">Distrito Federal (DF)</option>
                                    <option value="ES">Espírito Santo (ES)</option>
                                    <option value="GO">Goiás (GO)</option>
                                    <option value="MA">Maranhão (MA)</option>
                                    <option value="MT">Mato Grosso (MT)</option>
                                    <option value="MS">Mato Grosso do Sul (MS)</option>
                                    <option value="MG">Minas Gerais (MG)</option>
                                    <option value="PA">Pará (PA)</option>
                                    <option value="PB">Paraíba (PB)</option>
                                    <option value="PR">Paraná (PR)</option>
                                    <option value="PE">Pernambuco (PE)</option>
                                    <option value="PI">Piauí (PI)</option>
                                    <option value="RJ">Rio de Janeiro (RJ)</option>
                                    <option value="RN">Rio Grande do Norte (RN)</option>
                                    <option value="RS">Rio Grande do Sul (RS)</option>
                                    <option value="RO">Rondônia (RO)</option>
                                    <option value="RR">Roraima (RR)</option>
                                    <option value="SC">Santa Catarina (SC)</option>
                                    <option value="SP">São Paulo (SP)</option>
                                    <option value="SE">Sergipe (SE)</option>
                                    <option value="TO">Tocantins (TO)</option>
                                </select>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="text" class="form-control" id="cidade" name="cidade"  placeholder="Digite sua cidade">
                                <span class="glyphicon glyphicon-globe form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="text" class="form-control" id="rua" name="rua"  placeholder="Digite sua rua">
                                <span class="glyphicon glyphicon-road form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="number" class="form-control" id="numero" name="numero"  placeholder="Digite o numero da sua casa">
                                <span class="glyphicon glyphicon-road form-control-feedback"></span>
                            </div>
                        </fieldset>
                        <fieldset>
                            <legend><h2><span class="glyphicon glyphicon-credit-card"> Informações da conta</span></h2></legend>
                            <div class="form-group">
                                <select  class="form-control select2" id="Agencia" name="Agencia" style="width: 100%;">
                                    <%
                                        DaoFactoryIf fabrica = new DaoFactoryBD();
                                        AgenciaIf agenciaDao = fabrica.gerarDaoAgencia();
                                        List<Agencia> listaAgencias = agenciaDao.buscarTodos();
                                        for (Agencia u : listaAgencias) {
                                    %>
                                    <option value="<%= u.getId()%>"><%= u.getNome()%></option>           
                                    <%
                                        }
                                    %>
                                </select>
                                <div class="form-group has-feedback">
                                    <input type="text" class="form-control" id="saldo" name="saldo"  placeholder="Digite a quania de dinheiro inicial">
                                    <span class="glyphicon glyphicon-road form-control-feedback"></span>
                                </div>
                            </div>
                        </fieldset>
                        <div class="row">
                            <!-- /.col -->
                            <div class="col-xs-4">
                                <button type="submit" class="btn btn-primary btn-block btn-flat">Concluir</button>
                            </div>
                            <!-- /.col -->
                        </div>
                    </form>
                    <c:if test="${requestScope.sucesso == false}">
                        <div id="errorMsg" class="alert alert-danger" style="margin: 10px 10px 5px 10px;">
                            <a class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Erro!</strong> ${requestScope.mensagem}
                        </div>
                    </c:if>
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

