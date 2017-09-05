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
        Calendar cal = Calendar.getInstance();

        if (session.getAttribute("ad") != null) {
            response.sendRedirect(request.getContextPath() + "/pagina/home");
        }

        String login = "null";
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
        <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/img/favicon.png">
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
        <script src="${pageContext.request.contextPath}/js/install.js" type="text/javascript"></script>
        
        <script>
            $(document).ready(function () {
                notify("<%=login%>", "<%=status%>", "Aviso!");
            });
        </script>       
    </head>
    <body>
        <div class="pen-title">
            <img src="${pageContext.request.contextPath}/img/logo_horizontal.png" style="width: 30%;"/>
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
            $('#login').click(function () {
                loadPage();
            });
        </script>
    </body>
</html>
