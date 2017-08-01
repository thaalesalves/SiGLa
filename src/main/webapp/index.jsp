<!--
Copyright (C) 2016 Thales Alves Pereira

  This file is part of SiGla.

   SiGla is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   SiGla is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
-->
<%@page import="util.SiGLa"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        if (SiGLa.getDomain().equals("null")) {
            response.sendRedirect(request.getContextPath() + "/admin/install");
        }

        Calendar cal = Calendar.getInstance();

        if (session.getAttribute("ad") != null) {
            response.sendRedirect(request.getContextPath() + "/pagina/home");
        }

        String login = "nada";
        String status = null;
        if ((String) session.getAttribute("msg") != null) {
            login = (String) session.getAttribute("msg");
            session.removeAttribute("msg");
            status = (String) session.getAttribute("status");
            session.removeAttribute("status");
        }
    %>
    <head>
        <meta charset="UTF-8">
        <title>Login | SiGLa</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
        <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
        <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

        <!-- NOTIFICAÇÕES -->
        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/css/pnotify.custom.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/js/pnotify.custom.js" type="text/javascript"></script> 
        <link href="${pageContext.request.contextPath}/css/waitMe.css" rel="stylesheet" type="text/css"/>
        
        <script>
            $(document).ready(function () {
                var msg = "<%=login%>";
                var status = "<%=status%>";

                if (msg != "nada") {
                    new PNotify({
                        title: 'Erro ao fazer login',
                        text: msg,
                        type: status,
                        addclass: 'stack-bottomright',
                        animate: {
                            animate: true,
                            in_class: 'slideInUp',
                            out_class: 'slideOutDown'
                        }
                    });
                }

                var permanotice = false;
                if (permanotice) {
                    permanotice.open();
                } else {
                    permanotice = new PNotify({
                        title: 'Demo do SiGLa',
                        text: 'Bem vindo! Para acessar o SiGLa, basta utilizar como usuário e senha o cargo desejado.\n\n(professor, estagiario, funcionario, admin, coordenador)!',
                        type: 'notice',
                        hide: false,
                        addclass: "stack-topleft",
                        buttons: {
                            closer: false,
                            sticker: false
                        },
                        mobile: {
                            swipe_dismiss: false
                        }
                    });
                }
            });
        </script>
    </head>
    <body>
        <div class="pen-title">
            <h1>SiGLa</h1>
        </div>
        <div class="module form-module">
            <div class="form">
                <center><h2>Login no SiGLa</h2></center>
                <form action="${pageContext.request.contextPath}/AlmightyController" method="post" name="frm_login">
                    <input autofocus autocomplete="off" name="username" id="username" required type="text" placeholder="Usuário"/>
                    <input autocomplete="off" name="password" id="password" required type="password" placeholder="Senha"/>
                    <button id="login" class="btn btn-default submit" value="Login" name="acao" type="submit">Entrar</button>
                </form>
            </div>
        </div>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="${pageContext.request.contextPath}/js/index.js"></script>
        <script src="${pageContext.request.contextPath}/js/waitMe.js" type="text/javascript"></script>  
        <script>
            $('#login').click(function() {
                loadPage();
            });
        </script>
    </body>
</html>
