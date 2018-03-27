<%-- 
    Document   : paginatransferencia
    Created on : 04/10/2016, 00:27:51
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="cabecalhocliente.jsp" %>
    
    <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        olá 
                        <small> ${sessionScope.usuarioAutenticado.getNome()}  bem vindo a página de transferências 
                            do sistema.</small>
                    </h1>
                </section>

                <!-- Main content -->
                <section class="content">
                    <form action="FrontController?action=CadastroTransferencia" method="post">
                        <fieldset>
                            <legend><h2><span class="glyphicon glyphicon-usd"> Dados da transação</span></h2></legend>
                       
                        <div class="form-group has-feedback">
                            <input type="text" name="valor" class="form-control" placeholder="Digite o valor da operação">
                            <span class="glyphicon glyphicon-piggy-bank form-control-feedback"></span>
                        </div>

                        <div class="form-group has-feedback">
                            <input type="text" name="numero-conta" class="form-control" placeholder="Digite o numero da conta">
                            <span class="glyphicon glyphicon-pencil form-control-feedback"></span>
                        </div>

                        <div class="form-group has-feedback">
                            <input type="text" disabled name="titular" value="${sessionScope.usuarioAutenticado.getCpf()}"class="form-control" placeholder="Digite seu cpf">
                            <span class="glyphicon glyphicon-user form-control-feedback"></span>
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
