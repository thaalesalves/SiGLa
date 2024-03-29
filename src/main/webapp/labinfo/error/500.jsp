<%@page import="util.SiGLa"%>
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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <title>500 | SiGLa</title>
        <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/img/icon.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Mandali|Overlock+SC|Raleway+Dots|Roboto" rel="stylesheet">
    </head>
    <body class="corpo">
        <div class="text-center">
            <h1 class="error-code">500</h1><br />
            <hr class="separator" />
            <h2 class="error-title">Eita!</h2><br />
            <p class="error-description">Bad, bad server! No donut for you!</p><br />
            <a href="${pageContext.request.contextPath}" class="error-link">Voltar</a> <p class="error-link">|</p> <a href="mailto:<%=SiGLa.getMailGroup()%>" class="error-link">Reportar</a>
        </div>
    </body>
</html>