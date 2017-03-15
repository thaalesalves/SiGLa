<%@page import="dao.ReservaDAO"%>
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
<!DOCTYPE html>
<%
    String qtdSoli = "";

    if ((qtdSoli = (String) session.getAttribute("qtd-soli")) == null) {
        response.sendRedirect(request.getContextPath() +"/AlmightyController?acao=ContagemSolicitacoes");
    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contagem de Reservas</title>
    </head>
    <body>
        <div id="qtd-soli"><% out.println(qtdSoli);%></div>
    </body>
</html>
