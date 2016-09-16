<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login | SiGLa</title>
        <link rel="stylesheet" href="css/reset.css">
        <!-- <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/bootstrap-responsive.min.css" /> -->
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
        <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/estilo.css">
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <%
            if ((String) request.getAttribute("login") != null) {
                if ((String) request.getAttribute("login") == "false") {
        %>
        <script>
            $(document).ready(function () {
                $('#error-login').toggle();
            });
        </script>
        <%
        } else if ((String) request.getAttribute("login") == "acesso") {
        %>
        <script>
            $(document).ready(function () {
                $('#error-access').toggle();
            });
        </script>
        <%
                }
            }
        %>
    </head>
    <body class="login" onkeypress="if (event.keyCode == 13)
                document.login - form.confirm.click();">
        <div class="pen-title">
            <img src="img/logo.png" style="width: 15%;" />
        </div>
        <div class="module form-module">
            <div class="form">
                <h2>Login de Usuário</h2>
                <div class="alerta alerta-erro" id="error-login" style="display:none;">
                    <span class="forte">Eita!</span> <span>Usuário ou senha incorreto</span>
                </div>
                <div class="alerta alerta-erro" id="error-access" style="display:none;">
                    <span class="forte">Eita!</span> <span>Você não tem permissão de acesso</span>
                </div>
                <form action="LoginController" method="post">
                    <input type="text" autocomplete="off" placeholder="Usuário" name="username" required />
                    <input type="password" autocomplete="off" placeholder="Senha" name="password" required />
                    <input type="submit" name="acao" value="Entrar" />
                </form>
            </div>
            <!-- <div class="cta"><a href="http://andytran.me">Forgot your password?</a></div> -->
        </div>        
        <script src='http://codepen.io/andytran/pen/vLmRVp.js'></script>
        <script src="js/index.js"></script>
    </body>
</html>

