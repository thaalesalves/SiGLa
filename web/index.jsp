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
   along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login | SiGLa</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
        <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <%
            if (session.getAttribute("pessoa") != null) {
                response.sendRedirect(request.getContextPath() + "/pagina/home");
            }

            String login = null;
            if ((String) session.getAttribute("login") != null) {
                login = (String) session.getAttribute("login");
            }
        %>
        <script>
            var jslogin = "<%=login%>";

            <% session.invalidate(); %>

            $(document).ready(function () {
                if (jslogin == "false") {
                    $('#error-senha').toggle();
                } else if (jslogin == "acesso") {
                    $('#error-access').toggle();
                } else if (jslogin == "auth") {
                    $('#error-auth').toggle();
                }
            });
        </script>
    </head>
    <body id="fullPage" class="login corpo-login" onkeypress="if (event.keyCode == 13)
                document.login - form.confirm.click();">
        <div class="pen-title">
            <img src="${pageContext.request.contextPath}/img/logo.png" style="width: 15%;" />
        </div>
        <div class="module form-module">
            <div class="form">
                <h2>Login de Usuário</h2>
                <div class="alerta alerta-erro" id="error-senha" style="display:none;">
                    <span class="forte">Eita!</span> <span>Usuário ou senha incorreto</span>
                </div>
                <div class="alerta alerta-erro" id="error-access" style="display:none;">
                    <span class="forte">Eita!</span> <span>Você não tem permissão de acesso</span>
                </div>
                <div class="alerta alerta-erro" id="error-auth" style="display:none;">
                    <span class="forte">Eita!</span> <span>Parece que o AD está com problema!</span>
                </div>
                <form action="${pageContext.request.contextPath}/AlmightyController" method="post">
                    <input type="text" autocomplete="off" placeholder="Usuário" name="username" required autofocus />
                    <input type="password" autocomplete="off" placeholder="Senha" name="password" required />
                    <button value="Login" name="acao" type="submit">Entrar</button>
                </form>
            </div>
            <!-- <div class="cta"><a href="#">Esqueceu a senha?</a></div> -->
        </div>
        <% login = null;%>
    </body>
</html>

