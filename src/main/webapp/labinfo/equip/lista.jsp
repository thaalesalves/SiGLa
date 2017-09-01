<%-- 
    Document   : lista
    Created on : Aug 26, 2017, 4:46:28 PM
    Author     : thaal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>           
        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/equipamento.js" type="text/javascript"></script>
        <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/img/favicon.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table id="teste">
            <tr>
                <th>Fabricante</th>
                <th>Software</th>
                <th>VAI</th>
            </tr>
        </table>   
        <script>
            $(document).ready(function() {
                contextPath = "<%=request.getContextPath()%>";
                carrega();   
            });            
        </script>
    </body>
</html>