<%@page import="util.SiGLa"%>
<%@page import="java.util.Properties"%>
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

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
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
        <title>Login | SiGLa</title> 

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/icheck/flat/green.css" rel="stylesheet"> 
        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>

        <!-- NOTIFICAÇÕES -->
        <link href="${pageContext.request.contextPath}/css/pnotify.custom.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/js/pnotify.custom.js" type="text/javascript"></script> 

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
                        type: 'info',
                        hide: false,
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
    <body style="background:#F7F7F7;">
        <div class="">
            <a class="hiddenanchor" id="toregister"></a>
            <a class="hiddenanchor" id="tologin"></a>
            <div id="wrapper">
                <div id="login" class="animate form">
                    <section class="login_content">
                        <form action="${pageContext.request.contextPath}/AlmightyController" method="post" name="frm_login" onsubmit="return valida();" style="display: block;">
                            <h1>Login de Usuário</h1>
                            <div>
                                <input autofocus autocomplete="off" name="username" type="text" class="form-control" placeholder="Usuário" required/>
                            </div>
                            <div>
                                <input autocomplete="off" name="password" type="password" class="form-control" placeholder="Senha" required/>
                            </div>
                            <div>
                                <button class="btn btn-default submit" value="Login" name="acao" type="submit">Acessar</button>
                                <!--a class="reset_pass" href="#">Lost your password?</a-->
                            </div>
                            <div class="clearfix"></div>
                            <div class="separator">
                                <div class="clearfix"></div>
                                <br />
                                <div>
                                    <h1><i class="fa fa-paw" style="font-size: 26px;"></i> Gentelella Alela!</h1>
                                    <p>©<% out.println(cal.get(Calendar.YEAR));%> All Rights Reserved.</p>
                                </div>
                            </div>
                        </form>
                    </section>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/waitMe.js" type="text/javascript"></script>
    </body>
</html>

