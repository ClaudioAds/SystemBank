<%-- 
    Document   : index
    Created on : 04/10/2016, 03:22:05
    Author     : claudio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Banco da Gente</title>

        <!-- Bootstrap Core CSS -->
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../dist/css/logo-nav.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">
                        <img src="../dist/img/banco.png" alt="">
                    </a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">                   
                        <li>
                            <a href="login.jsp">Login</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>Banco da Gente</h1>
                </div>
            </div>
            <div class="container-simulador">
                
                <form class="form-horizontal" name="calcula" method="post">
                    <fieldset>

                        <!-- Form Name -->
                        <legend>Simulador</legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="montante">Montante:</label>  
                            <div class="col-md-6">
                                <input id="montante" name="montante" type="text" placeholder="montante" class="form-control input-md" required="">

                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="parcelas">Parcelas:</label>  
                            <div class="col-md-6">
                                <input id="parcelas" name="parcelas" type="text" placeholder="parcelas" class="form-control input-md" required="">

                            </div>
                        </div>

                        
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="total">Total a Pagar:</label>  
                            <div class="col-md-6">
                                                                                <input id="total" name="total" type="text" placeholder="totalpagar" class="form-control input-md" value="${requestScope.total}">

                            </div>
                        </div>

                         
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="singlebutton"></label>
                            <div class="col-md-6">
                                <button id="singlebutton" name="singlebutton" type="submit"  class="btn btn-success">Calcular</button>
                            </div>
                        </div>

                    </fieldset>
                </form>

                <%
                    if((request.getParameter("montante") !=null) && (request.getParameter("parcelas") !=null)){
                            
                            
                    int montante = Integer.parseInt(request.getParameter("montante"));
                    int parcelas = Integer.parseInt(request.getParameter("parcelas"));
                    double totalparcial= montante * 0.15;
                    double total = totalparcial * parcelas * parcelas+ 0.25;

                    out.println("\n"
                            + "******************************\nO Valor a Pagar é:\n"+total+"\n**********************************");
                    }
                %>
            </div>
        </div>
        <!-- /.container -->


        
        
        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
        
        
    </body>

</html>

