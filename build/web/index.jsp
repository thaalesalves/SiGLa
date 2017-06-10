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
<!-- saved from url=(0023)http://intranet.umc.br/ -->
<html lang="pt-br"><head><meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <%
            Calendar cal = Calendar.getInstance();

            if (session.getAttribute("ad") != null) {
                response.sendRedirect(request.getContextPath() + "/pagina/home");
            }

            String login = "nada";
            if ((String) session.getAttribute("msg") != null) {
                login = (String) session.getAttribute("msg");
                session.removeAttribute("msg");
            }
        %>

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login | SiGLa</title>
        <link href="./login/css" rel="stylesheet" type="text/css">
        <link href="./login/login.css" rel="stylesheet" type="text/css">
        <script src="./login/jquery.min.js.download"></script>
        <script>
            $(document).ready(function () {
                var msg = "<%=login%>";

                if (msg != "nada") {
                    $('#errormsg span').html(msg);
                    $('#errormsg').fadeIn(200);
                    document.frm_login.username.focus();
                    return false;
                }
            });


            function valida() {

                $('#errormsg').hide();
                if (document.frm_login.username.value == "") {
                    $('#errormsg span').html('Informe seu Usu&aacute;rio');
                    $('#errormsg').fadeIn(200);
                    document.frm_login.username.focus();
                    return false;
                } else if (document.frm_login.password.value == "") {
                    $('#errormsg span').html('Informe sua Senha');
                    $('#errormsg').fadeIn(200);
                    document.frm_login.password.focus();
                    return false;
                }
                return true;
            }

            function toggleBody() {
                if ($(".help-btn").html() == 'X') {
                    $(".help-btn").html('i');
                    $("form[name='frm_login']").fadeIn(200);
                    $("#help").hide();
                    $("form[name='frm_esqueceu']").hide();
                    $("#title").fadeIn(200);
                } else {
                    $(".help-btn").html('X');
                    $("form[name='frm_login']").hide();
                    $("#help").fadeIn(200);
                    $("#title").hide();
                }
            }
            function toggleEsqueceu() {
                $(".help-btn").html('X');
                $("form[name='frm_login']").hide();
                $("form[name='frm_esqueceu']").fadeIn(200);
                $("#title").hide();
            }
            function toggleSidemenu() {
                $("body").toggleClass('show-sidemenu');
                $(".overlay").toggle();
            }
        </script>
    </head>
    <body cz-shortcut-listen="true">
        <div id="sidemenu" class="sidemenu">
            <a class="sidemenu-btn" href="javascript:void(0)" onclick="toggleSidemenu()"><img src="./login/menu.svg" width="24"></a>
            <div class="sidemenu-space"></div>
            <div class="menu-box">
                <h3 class="title">Portais</h3>
                <a href="http://www.umc.br/" target="_blank" title="" class="link">UMC</a>
                <a href="http://aluno.umc.br/" target="_blank" title="" class="link">Aluno</a>
                <a href="https://docentes.umc.br/" target="_blank" title="" class="link">Docente</a>
                <a href="http://www.umctec.com.br/" target="_blank" title="" class="link">UMCTEC</a>
                <a href="http://aluno.umctec.com.br/" target="_blank" title="" class="link">Aluno UMCTEC</a>
            </div>
            <div class="menu-box">
                <h3 class="title">Serviços</h3>
                <a href="http://www.intranet.umc.br/" target="_blank" title="Intranet" class="link">Intranet</a>
                <a href="http://www.faep.org.br/" target="_blank" title="Fundação de Amparo ao Ensino e Pesquisa" class="link">FAEP</a>
                <a href="http://portal.office.com/" target="_blank" title="" class="link">Webmail - UMC</a>
                <a href="https://sp.umc.br/" target="_blank" title="Solicitação de Pagamento" class="link">SP</a>
                <a href="http://pgmdiscip.umc.br/" target="_blank" title="Plano de disciplinas" class="link">Plano Disciplinas</a>
                <a href="https://siss.umc.br/" target="_blank" title="Sistema Integrado de Solicitação de Serviços" class="link">SISS</a>
                <a href="https://sape.umc.br/" target="_blank" title="Sistema de agendamento de prova eletrônica" class="link">SAPE</a>
                <a href="http://siscoa.umc.br/" target="_blank" title="Sistema de Controle de Agendamentos" class="link">SISCOA</a>
                <a href="http://pergfr.umc.br/" target="_blank" title="Perguntas Frequentes" class="link">PERGFR</a>
                <a href="http://pergfr.umc.br/externo/perguntasfrequentes.faces" target="_blank" title="Perguntas Frequentes - Consulta" class="link">PERGFR-Consulta</a>
                <a href="http://sisvot.umc.br/" target="_blank" title="Sistema de Controle de Votações em Concursos" class="link">SISVOT</a>
                <a href="http://avalinst.umc.br/" target="_blank" title="Sistema de Avaliação Institucional da CPA" class="link">AVALINST</a>
            </div>
        </div>
        <div class="overlay" onclick="toggleSidemenu()"></div>
        <div class="wrap">
            <a class="sidemenu-btn" href="javascript:void(0)" onclick="toggleSidemenu()"><img src="./login/menu.svg" width="24"></a>
            <a class="help-btn" href="javascript:void(0)" onclick="toggleBody()" title="Objetivo">i</a>
            <img class="logo" src="./login/logo.svg" width="140"><br>
            <h2 id="title" style="display: block;">SiGLa</h2>
            <form action="${pageContext.request.contextPath}/AlmightyController" method="post" name="frm_login" onsubmit="return valida();" style="display: block;">
                <div id="errormsg" class="errormsg">
                    <img alt="Erro" src="./login/warning.svg" height="16"> <span></span>
                </div>
                <div class="umc-field">
                    <input type="text" autocomplete="off"  placeholder="Usuário" name="username" autofocus>
                </div>
                <div class="umc-field">
                    <input type="password" placeholder="Senha" name="password">
                </div>
                <input type="hidden" name="txtLocal" value="1">
                <div class="umc-field">
                    <button value="Login" name="acao" type="submit">Acessar</button>
                </div>
            </form>
            <div id="help" class="help" style="display: none;">
                <h2>Intranet</h2>
                <p>O <b>SiGLa</b> é uma área de acesso restrito a professores, coordenadores e funcionários do departamento de TI para solicitação, agendamento, controle e gestão dos <b>laboratórios de informática</b>.</p>
            </div>
            <div class="text-small copyright"><span>Copyright © 2000 - <% out.println(cal.get(Calendar.YEAR));%> UMC. Todos os direitos reservados. All rights reserved.</span></div>
        </div>

        <script type="text/javascript">
            function fechar(id) {
                document.getElementById(id).style.display = 'none';
            }
        </script>		<script type="text/javascript">
            document.frm_login.txtUsuario.focus();
        </script>


    </body>
</html>