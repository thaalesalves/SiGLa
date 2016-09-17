<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <title>404 | SiGLa</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Mandali|Overlock+SC|Raleway+Dots|Roboto" rel="stylesheet">
    </head>
    <body class="corpo">
        <div class="text-center">
            <h1 class="error-code">404</h1><br />
            <hr class="separator" />
            <h2 class="error-title">Deu ruim!</h2><br />
            <p class="error-description">Parece que você tentou acessar um recurso que não está aqui!</p><br />
            <a href="../" class="error-link">Voltar</a> | <a href="mailto:suporte.lab.mc@umc.br" class="error-link">Reportar</a>
        </div>
    </body>
</html>